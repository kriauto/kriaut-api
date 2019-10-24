package ma.kriauto.api.service;

import ma.kriauto.api.model.ActiveNotif;
import org.springframework.data.repository.query.Param;

public interface ActiveNotifService {
	
    public ActiveNotif fetchActiveNotifByCarId(Long carid);	
	public ActiveNotif save(ActiveNotif activenotif);
    public void completeActiveNotif(ActiveNotif activenotifin, ActiveNotif activenotifout);
    public ActiveNotif fetchActiveNotifById(Long id);

}
