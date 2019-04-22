package ma.kriauto.api.repository;

import ma.kriauto.api.model.Agency;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {
	
	@Query("SELECT a FROM Agency a WHERE a.idProfile=:id")
	public Agency fetchAgencyByProfileId(@Param("id") Long id);
	
	@Query("SELECT a FROM Agency a WHERE a.id=:id")
	public Agency fetchAgencyById(@Param("id") Long id);
	
	public Agency save(Agency agency);
}
