package ma.kriauto.api.service;

import ma.kriauto.api.model.Profile;
import ma.kriauto.api.repository.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
    private ProfileRepository profileRepository;

	@Override
	public Profile fetchProfileByLogin(String login) {
		// TODO Auto-generated method stub
		return profileRepository.fetchProfileByLogin(login);
	}

	@Override
	public Profile fetchProfileByToken(String token) {
		// TODO Auto-generated method stub
		return profileRepository.fetchProfileByToken(token);
	}

}
