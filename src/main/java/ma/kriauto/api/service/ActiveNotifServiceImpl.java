package ma.kriauto.api.service;

import ma.kriauto.api.model.ActiveNotif;
import ma.kriauto.api.repository.ActiveNotifRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiveNotifServiceImpl implements ActiveNotifService {
	
	@Autowired
    private ActiveNotifRepository activenotifRepository;

	@Override
	public ActiveNotif fetchActiveNotifByCarId(Long carid) {
		return activenotifRepository.fetchActiveNotifByCarId(carid);
	}

	@Override
	public ActiveNotif save(ActiveNotif activenotif) {
		return activenotifRepository.save(activenotif);
	}

}
