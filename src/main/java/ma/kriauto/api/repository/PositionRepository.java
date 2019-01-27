package ma.kriauto.api.repository;

import java.util.List;

import ma.kriauto.api.model.Item;
import ma.kriauto.api.model.Position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	
	@Query("SELECT p FROM Position p where p.fixtime = (SELECT MAX(p.fixtime) FROM Position p WHERE p.deviceid=:deviceid and to_char(p.fixtime,'YYYY-MM-DD') <=:date)")
	public List<Position> fetchLastPositionByDeviceId(@Param("deviceid") Integer deviceid, @Param("date") String date);
	
//	@Query("SELECT p  FROM Position p where to_char(p.fixtime,'YYYY-MM-DD') IN (SELECT DISTINCT to_char(p.fixtime,'YYYY-MM-DD') FROM Position p WHERE to_char(p.fixtime,'YYYY-MM-DD') >=:date and p.deviceid IN (SELECT DISTINCT c.deviceId FROM Car c where c.agencyId=:agencyid ))")
//	public List<Position> fetchAllDatesByAgencyId(@Param("agencyid") Long agencyid, @Param("date") String date);
}
