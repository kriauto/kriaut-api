package ma.kriauto.api.repository;

import java.util.List;

import ma.kriauto.api.model.Notification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
	
	@Query("SELECT n FROM Notification n WHERE n.carid=:carid AND to_char(n.creationdate,'YYYY-MM-DD')=:date")
	public List<Notification> fetchNotificationByCarIdAndDate(@Param("carid") Long carid,@Param("date") String date);

	@Query("SELECT COUNT(n) FROM Notification n WHERE n.carid=:carid AND to_char(n.creationdate,'YYYY-MM-DD')=:date")
	public Integer fetchNumberNotificationByCarIdAndDate(@Param("carid") Long carid,@Param("date") String date);
	
	public Notification save(NotificationRepository notification);

}
