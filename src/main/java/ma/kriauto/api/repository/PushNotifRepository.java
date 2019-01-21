package ma.kriauto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.kriauto.api.model.PushNotif;

@Repository
public interface PushNotifRepository extends JpaRepository<PushNotif, Long> {
	
	public PushNotif save(PushNotif pushnotif);
	
	public void delete(PushNotif pushnotif);
	
	@Query("SELECT p FROM PushNotif p WHERE p.pushToken=:push")
	public PushNotif fetchDeviceByPushToken(@Param("push") String push);

}
