package ma.kriauto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.kriauto.api.model.Profile;
import ma.kriauto.api.repository.AgencyRepository;
import ma.kriauto.api.repository.CarRepository;
import ma.kriauto.api.repository.PositionRepository;
import ma.kriauto.api.repository.ProfileRepository;

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
}
