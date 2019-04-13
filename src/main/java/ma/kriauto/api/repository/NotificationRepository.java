package ma.kriauto.api.repository;

import ma.kriauto.api.model.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	@Query("SELECT n FROM Notification n WHERE n.carid=:carid AND n.creationdate=:date")
	public Notification fetchNotificationByCarIdAndCreationdate(@Param("carid") Long carid,@Param("date") Long date);
	
	public Notification save(NotificationRepository notification);

}
