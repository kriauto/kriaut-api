package ma.kriauto.getway.service;

import ma.kriauto.getway.model.Device;

import org.springframework.data.repository.query.Param;

public interface DeviceService {

	public Device save(Device device);
	public void delete(Device device);
	public Device fetchDeviceByIdentifier(@Param("identifier") String identifier);

}
