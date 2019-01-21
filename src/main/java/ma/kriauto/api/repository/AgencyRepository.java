package ma.kriauto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.kriauto.api.model.Agency;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Long> {

}
