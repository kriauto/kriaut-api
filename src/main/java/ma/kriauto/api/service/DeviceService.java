package ma.kriauto.api.service;

import ma.kriauto.api.model.Device;

import org.springframework.data.repository.query.Param;

public interface DeviceService {

	public Device save(Device device);
	public void delete(Device device);
	public Device fetchDeviceByIdentifier(@Param("identifier") String identifier);

}
