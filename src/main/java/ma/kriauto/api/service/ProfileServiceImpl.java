package ma.kriauto.api.service;

import java.util.List;

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
		menu.setIsLastPosition(profile.getIsActive() == true? profile.getIsLastPosition() : false);
		menu.setIsHistory(profile.getIsActive() == true? profile.getIsHistory() : false);
		menu.setIsSpeedMax(profile.getIsActive() == true? profile.getIsSpeedMax() : false);
		menu.setIsCourse(profile.getIsActive() == true? profile.getIsCourse() : false);
		menu.setIsCarburantP(profile.getIsActive() == true? profile.getIsCarburantP() : false);
		menu.setIsCarburantS(profile.getIsActive() == true? profile.getIsCarburantS() : false);
		menu.setIsNotifCons(profile.getIsActive() == true? profile.getIsNotifCons() : false);
		menu.setIsNotifConf(profile.getIsActive() == true? profile.getIsNotifConf() : false);
		menu.setIsZoneOne(profile.getIsActive() == true? profile.getIsZoneOne() : false);
		menu.setIsZoneTwo(profile.getIsActive() == true? profile.getIsZoneTwo() : false);
		menu.setIsTempM(profile.getIsActive() == true? profile.getIsTempM() : false);
		menu.setIsTempF(profile.getIsActive() == true? profile.getIsTempF() : false);
		menu.setIsDoor(profile.getIsActive() == true? profile.getIsDoor() : false);
		menu.setIsDriver(profile.getIsActive() == true? profile.getIsDriver() : false);
		menu.setIsParameters(profile.getIsActive() == true? profile.getIsParameters() : false);
		menu.setIsStartStop(profile.getIsActive() == true? profile.getIsStartStop() : false);
		menu.setIsMyAccount(profile.getIsActive() == true? profile.getIsMyAccount() : false);
		menu.setIsContactUs(true);
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
	public List<Profile> fetchAllProfile() {
		return profileRepository.fetchAllProfile();
	}

	@Override
	public Profile save(Profile profile) {
		return profileRepository.save(profile);
	}

	@Override
	public void completeProfile(Profile profilein, Profile profileout) {
		profileout.setLogin(profilein.getLogin() != null ? profilein.getLogin() : profileout.getLogin());
		profileout.setPassword(profilein.getPassword() != null ? profilein.getPassword() : profileout.getPassword());
		profileout.setName(profilein.getName() != null ? profilein.getName() : profileout.getName());
		profileout.setPhone(profilein.getPhone() != null ? profilein.getPhone() : profileout.getPhone());
		profileout.setMail(profilein.getMail() != null ? profilein.getMail() : profileout.getMail());
	}
}
