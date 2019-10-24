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

	@Override
	public void completeActiveNotif(ActiveNotif activenotifin, ActiveNotif activenotifout) {
		activenotifout.setMaxcarburantpri(activenotifin.getMaxcarburantpri() != null ? activenotifin.getMaxcarburantpri() : activenotifout.getMaxcarburantpri());
		activenotifout.setMincarburantpri(activenotifin.getMincarburantpri() != null ? activenotifin.getMincarburantpri() : activenotifout.getMincarburantpri());
		activenotifout.setMaxcarburantsec(activenotifin.getMaxcarburantsec() != null ? activenotifin.getMaxcarburantsec() : activenotifout.getMaxcarburantsec());
		activenotifout.setMincarburantsec(activenotifin.getMincarburantsec() != null ? activenotifin.getMincarburantsec() : activenotifout.getMincarburantsec());
		activenotifout.setMaxtempengine(activenotifin.getMaxtempengine() != null ? activenotifin.getMaxtempengine() : activenotifout.getMaxtempengine());
		activenotifout.setMintempengine(activenotifin.getMintempengine() != null ? activenotifin.getMintempengine() : activenotifout.getMintempengine());
		activenotifout.setMaxtempfrigot(activenotifin.getMaxtempfrigot() != null ? activenotifin.getMaxtempfrigot() : activenotifout.getMaxtempfrigot());
		activenotifout.setMintempfrigot(activenotifin.getMaxtempfrigot() != null ? activenotifin.getMaxtempfrigot() : activenotifout.getMaxtempfrigot());
		activenotifout.setClosedoor(activenotifin.getClosedoor() != null ? activenotifin.getClosedoor() : activenotifout.getClosedoor());
		activenotifout.setOpendoor(activenotifin.getOpendoor() != null ? activenotifin.getOpendoor() : activenotifout.getOpendoor());
		activenotifout.setDrivertracking(activenotifin.getDrivertracking() != null ? activenotifin.getDrivertracking() : activenotifout.getDrivertracking());
	}

	@Override
	public ActiveNotif fetchActiveNotifById(Long id) {
		return activenotifRepository.fetchActiveNotifById(id);
	}

}
