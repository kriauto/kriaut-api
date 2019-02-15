package ma.kriauto.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.kriauto.api.model.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	
	@Query("SELECT p FROM Position p where p.fixtime = (SELECT MAX(p.fixtime) FROM Position p WHERE p.deviceid=:deviceid and to_char(p.fixtime,'YYYY-MM-DD') <=:date AND p.valid = true) AND p.deviceid=:deviceid")
	public List<Position> fetchLastPositionByDeviceIdAndDate(@Param("deviceid") Integer deviceid, @Param("date") String date);
	
	@Query("SELECT p FROM Position p where p.fixtime = (SELECT MAX(p.fixtime) FROM Position p WHERE p.deviceid=:deviceid AND p.valid = true) AND p.deviceid=:deviceid")
	public List<Position> fetchLastPositionByDeviceId(@Param("deviceid") Integer deviceid);
	
	@Query("SELECT p  FROM Position p where to_char(p.fixtime,'YYYY-MM-DD')=:date AND p.deviceid=:deviceid AND p.valid = true ORDER BY p.fixtime")
	public List<Position> fetchAllPositionByDeviceIdAndDate(@Param("date") String date, @Param("deviceid") Integer deviceid);
	
	@Query("SELECT p  FROM Position p where p.speed = (SELECT MAX(p.speed) FROM Position p WHERE to_char(p.fixtime,'YYYY-MM-DD') =:date AND p.deviceid=:deviceid AND p.valid = true) AND p.deviceid=:deviceid")
	public List<Position> fetchMaxSpeedDeviceIdAndDate(@Param("date") String date, @Param("deviceid") Integer deviceid);
}
