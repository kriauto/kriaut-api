package ma.kriauto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.repository.AgencyRepository;
import ma.kriauto.api.repository.CarRepository;
import ma.kriauto.api.repository.PositionRepository;
import ma.kriauto.api.repository.ProfileRepository;
import ma.kriauto.api.response.AuthenticationOut;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
    private ProfileRepository profileRepository;
	
	@Autowired
    private AgencyRepository agencyRepository;
	
	@Autowired
    private CarRepository carRepository;
	
	@Autowired
    private PositionRepository positionRepository;
	
	@Autowired
    private UtilityService utilityService;
	
	@Override
	public Profile fetchProfileByLogin(String login) {
		return profileRepository.fetchProfileByLogin(login);
	}

	@Override
	public AuthenticationOut fetchMenuDtoByLogin(String login) {
		AuthenticationOut menu = new AuthenticationOut();
		Profile profile = profileRepository.fetchProfileByLogin(login);
		Agency agency = agencyRepository.fetchAgencyByProfileId(profile.getId());
		menu.setAuthToken(profile.getAuthToken());
		menu.setAgencyName(agency.getName());
		menu.setIsLastPosition(profile.getIsLastPosition());
		menu.setIsHistory(profile.getIsHistory());
		menu.setIsSpeedMax(profile.getIsSpeedMax());
		menu.setIsCourse(profile.getIsCourse());
		menu.setIsCarburantP(profile.getIsCarburantP());
		menu.setIsCarburantS(profile.getIsCarburantS());
		menu.setIsNotifCons(profile.getIsNotifCons());
		menu.setIsNotifConf(profile.getIsNotifConf());
		menu.setIsZoneOne(profile.getIsZoneOne());
		menu.setIsZoneTwo(profile.getIsZoneTwo());
		menu.setIsTempM(profile.getIsTempM());
		menu.setIsTempF(profile.getIsTempF());
		menu.setIsDoor(profile.getIsDoor());
		menu.setIsDriver(profile.getIsDriver());
		menu.setIsParameters(profile.getIsParameters());
		menu.setIsStartStop(profile.getIsStartStop());
		menu.setIsMyAccount(profile.getIsMyAccount());
		return menu;
	}

	@Override
	public Profile fetchProfileByToken(String token) {
		return profileRepository.fetchProfileByToken(token);
	}

	@Override
	public Profile fetchProfileByMail(String mail) {
		return profileRepository.fetchProfileByMail(mail);
	}
	
	@Override
	public Profile fetchProfileById(Long id) {
		return profileRepository.fetchProfileById(id);
	}

	@Override
	public Profile save(Profile profile) {
		return profileRepository.save(profile);
	}
}
