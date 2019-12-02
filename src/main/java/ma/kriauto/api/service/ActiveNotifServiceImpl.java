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
		activenotifout.setTechcontrol(activenotifin.getTechcontrol() != null ? activenotifin.getTechcontrol() : activenotifout.getTechcontrol());
		activenotifout.setEmptyingkm(activenotifin.getEmptyingkm() != null ? activenotifin.getEmptyingkm() : activenotifout.getEmptyingkm());
		activenotifout.setInsuranceend(activenotifin.getInsuranceend() != null ? activenotifin.getInsuranceend() : activenotifout.getInsuranceend());
		activenotifout.setCirculationend(activenotifin.getCirculationend() != null ? activenotifin.getCirculationend() : activenotifout.getCirculationend());
		activenotifout.setMaxspeed(activenotifin.getMaxspeed() != null ? activenotifin.getMaxspeed() : activenotifout.getMaxspeed());
		activenotifout.setMaxcourse(activenotifin.getMaxcourse() != null ? activenotifin.getMaxcourse() : activenotifout.getMaxcourse());
		activenotifout.setMaxcarburantpri(activenotifin.getMaxcarburantpri() != null ? activenotifin.getMaxcarburantpri() : activenotifout.getMaxcarburantpri());
		activenotifout.setMincarburantpri(activenotifin.getMincarburantpri() != null ? activenotifin.getMincarburantpri() : activenotifout.getMincarburantpri());
		activenotifout.setMaxcarburantsec(activenotifin.getMaxcarburantsec() != null ? activenotifin.getMaxcarburantsec() : activenotifout.getMaxcarburantsec());
		activenotifout.setMincarburantsec(activenotifin.getMincarburantsec() != null ? activenotifin.getMincarburantsec() : activenotifout.getMincarburantsec());
		activenotifout.setMaxtempengine(activenotifin.getMaxtempengine() != null ? activenotifin.getMaxtempengine() : activenotifout.getMaxtempengine());
		activenotifout.setMintempengine(activenotifin.getMintempengine() != null ? activenotifin.getMintempengine() : activenotifout.getMintempengine());
		activenotifout.setMaxtempfrigot(activenotifin.getMaxtempfrigot() != null ? activenotifin.getMaxtempfrigot() : activenotifout.getMaxtempfrigot());
		activenotifout.setMintempfrigot(activenotifin.getMaxtempfrigot() != null ? activenotifin.getMaxtempfrigot() : activenotifout.getMaxtempfrigot());
		activenotifout.setZoneonein(activenotifin.getZoneonein() != null ? activenotifin.getZoneonein() : activenotifout.getZoneonein());
		activenotifout.setZoneoneout(activenotifin.getZoneoneout() != null ? activenotifin.getZoneoneout() : activenotifout.getZoneoneout());
		activenotifout.setZonetwoin(activenotifin.getZonetwoin() != null ? activenotifin.getZonetwoin() : activenotifout.getZonetwoin());
		activenotifout.setZonetwoout(activenotifin.getZonetwoout() != null ? activenotifin.getZonetwoout() : activenotifout.getZonetwoout());
		activenotifout.setEnginestart(activenotifin.getEnginestart() != null ? activenotifin.getEnginestart() : activenotifout.getEnginestart());
		activenotifout.setEnginestop(activenotifin.getEnginestop() != null ? activenotifin.getEnginestop() : activenotifout.getEnginestop());
		activenotifout.setMail(activenotifin.getMail() != null ? activenotifin.getMail() : activenotifout.getMail());
		activenotifout.setClosedoor(activenotifin.getClosedoor() != null ? activenotifin.getClosedoor() : activenotifout.getClosedoor());
		activenotifout.setOpendoor(activenotifin.getOpendoor() != null ? activenotifin.getOpendoor() : activenotifout.getOpendoor());
		activenotifout.setDrivertracking(activenotifin.getDrivertracking() != null ? activenotifin.getDrivertracking() : activenotifout.getDrivertracking());
	}

	@Override
	public ActiveNotif fetchActiveNotifById(Long id) {
		return activenotifRepository.fetchActiveNotifById(id);
	}

}
