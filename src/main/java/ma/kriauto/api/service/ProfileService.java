package ma.kriauto.api.service;

import ma.kriauto.api.model.Profile;

public interface ProfileService {
	
	public Profile fetchProfileByLogin(String login);
	public Profile fetchProfileByToken(String token);
	public Profile fetchProfileByMail(String mail);
}
