package ma.kriauto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.kriauto.api.model.Zone;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
	

	@Query("SELECT z FROM Zone z WHERE z.id=:id")
	public Zone fetchZoneById(@Param("id") Long id);
	
	@Query("SELECT z FROM Zone z WHERE z.carid=:carid")
	public Zone fetchZoneByCarId(@Param("carid") Long carid);
	
	@Query("SELECT z FROM Zone z WHERE z.carid=:carid AND z.rank=:rank")
	public Zone fetchZoneByCarIdAndRank(@Param("carid") Long carid,@Param("rank") Integer rank);
	
	public Zone save(Zone zone);

}
