package ma.kriauto.api.repository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.kriauto.api.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	
	@Query("SELECT p FROM Position p where p.fixtime = (SELECT MAX(p.fixtime) FROM Position p WHERE p.deviceid=:deviceid AND to_char(p.fixtime,'YYYY-MM-DD') <=:date) AND p.deviceid=:deviceid AND to_char(p.fixtime,'YYYY-MM-DD') <=:date")
	List<Position> fetchLastPositionByDeviceIdAndDate(@Param("deviceid") Integer deviceid, @Param("date") String date);
	
	@Query("SELECT p FROM Position p where p.fixtime = (SELECT MAX(p.fixtime) FROM Position p WHERE p.deviceid=:deviceid) AND p.deviceid=:deviceid")
	List<Position> fetchLastPositionByDeviceId(@Param("deviceid") Integer deviceid);
	
	@Query("SELECT p  FROM Position p where to_char(p.fixtime,'YYYY-MM-DD')=:date AND p.deviceid=:deviceid AND p.valid = true ORDER BY p.fixtime")
	List<Position> fetchAllPositionByDeviceIdAndDate(@Param("date") String date, @Param("deviceid") Integer deviceid);
	
	@Query("SELECT p  FROM Position p where p.speed = (SELECT MAX(p.speed) FROM Position p WHERE to_char(p.fixtime,'YYYY-MM-DD') =:date AND p.deviceid=:deviceid) AND to_char(p.fixtime,'YYYY-MM-DD') =:date AND p.deviceid=:deviceid")
	List<Position> fetchMaxSpeedDeviceIdAndDate(@Param("date") String date, @Param("deviceid") Integer deviceid);
	
	@Query("SELECT p  FROM Position p where p.speed = (SELECT MAX(p.speed) FROM Position p WHERE to_char(p.fixtime,'YYYY-MM-DD HH:mm:ss') >=:date AND p.deviceid=:deviceid) AND p.deviceid=:deviceid")
	List<Position> fetchMaxSpeedByDeviceIdAndPeriod(@Param("date") String date, @Param("deviceid") Integer deviceid);
	
	@Query("SELECT p.attributes, p.fixtime  FROM Position p where p.attributes LIKE '%\"ignition\":true%' OR p.attributes LIKE '\"ignition\":false'  AND to_char(p.fixtime,'YYYY-MM-DD HH:mm:ss') >=:date AND p.deviceid=:deviceid ORDER BY fixtime")
	List<Position> fetchCarIgnition(@Param("date") String date, @Param("deviceid") Integer deviceid);
	
}
