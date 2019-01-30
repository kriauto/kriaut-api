package ma.kriauto.api.service;

import java.util.List;

import ma.kriauto.api.dto.Location;
import ma.kriauto.api.model.Profile;

public interface ProfileService {
	
	public Profile fetchProfileByLogin(String login);
	public Profile fetchProfileByToken(String token);
	public Profile fetchProfileByMail(String mail);
	public Profile fetchProfileById(Long id);
	public List<Location> fetchLocationsByProfileId(Long id, String date);
}
