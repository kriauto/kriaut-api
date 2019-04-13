package ma.kriauto.api.service;

import ma.kriauto.api.model.ActiveNotif;

public interface ActiveNotifService {
	
    public ActiveNotif fetchActiveNotifByCarId(Long carid);	
	public ActiveNotif save(ActiveNotif activenotif);
}
