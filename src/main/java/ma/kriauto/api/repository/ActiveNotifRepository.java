package ma.kriauto.api.repository;

import ma.kriauto.api.model.ActiveNotif;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiveNotifRepository extends JpaRepository<ActiveNotif, Long> {
	
	@Query("SELECT a FROM ActiveNotif a WHERE a.carid=:carid")
	public ActiveNotif fetchActiveNotifByCarId(@Param("carid") Long carid);
	
	public ActiveNotif save(ActiveNotif activenotif);
}
