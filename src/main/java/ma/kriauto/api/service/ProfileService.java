package ma.kriauto.api.service;

import ma.kriauto.api.model.Profile;
import ma.kriauto.api.response.AuthenticationOut;

public interface ProfileService {
	
	public Profile fetchProfileByLogin(String login);
	public AuthenticationOut fetchMenuDtoByLogin(String login);
	public Profile fetchProfileByToken(String token);
	public Profile fetchProfileByMail(String mail);
	public Profile fetchProfileById(Long id);
}
