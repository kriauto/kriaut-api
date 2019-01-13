package ma.kriauto.getway.service;

import ma.kriauto.getway.model.Device;
import ma.kriauto.getway.repository.DeviceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {
	
	@Autowired
    private DeviceRepository deviceRepository;

	@Override
	public Device save(Device device) {
		// TODO Auto-generated method stub
		return deviceRepository.save(device);
	}

	@Override
	public void delete(Device device) {
		// TODO Auto-generated method stub
		deviceRepository.delete(device);
	}

	@Override
	public Device fetchDeviceByIdentifier(String identifier) {
		// TODO Auto-generated method stub
		return deviceRepository.fetchDeviceByIdentifier(identifier);
	}

}
