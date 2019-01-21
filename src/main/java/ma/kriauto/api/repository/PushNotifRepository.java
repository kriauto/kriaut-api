package ma.kriauto.api.repository;

import ma.kriauto.api.model.PushNotif;

public interface PushNotifRepository {
	
	public PushNotif save(PushNotif pushnotif);
	
	public void delte(PushNotif pushnotif);

}
