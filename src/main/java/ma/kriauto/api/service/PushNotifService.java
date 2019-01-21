package ma.kriauto.api.service;

import org.springframework.data.repository.query.Param;

import ma.kriauto.api.model.PushNotif;

public interface PushNotifService {
	
    public PushNotif save(PushNotif pushnotif);	
	public void delete(PushNotif pushnotif);	
	public PushNotif fetchDeviceByPushToken(@Param("push") String push);
}
