package ma.kriauto.api.repository;

import ma.kriauto.api.model.Parameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {
	
	@Query("SELECT p FROM Parameter p WHERE p.carid=:carid")
	public Parameter fetchParameterByCarId(@Param("carid") Long carid);
	
	public Parameter save(Parameter parameter);
}
