package ma.kriauto.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.kriauto.api.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
	@Query("SELECT c FROM Car c WHERE c.agencyId=:id ORDER BY mark,model,immatriculation")
	public List<Car> fetchAllCarByAgencyId(@Param("id") Long id);
	
	@Query("SELECT c FROM Car c WHERE c.id=:id")
	public Car fetchCarById(@Param("id") Long id);
	
	public Car save(Car car);
	
}
