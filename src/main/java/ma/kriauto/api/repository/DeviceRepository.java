package ma.kriauto.api.repository;

import ma.kriauto.api.model.Device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
	
	public Device save(Device device);
	
	public void delete(Device device);
	
	@Query("SELECT d FROM Device d WHERE d.identifier=:identifier")
	public Device fetchDeviceByIdentifier(@Param("identifier") String identifier);
}
