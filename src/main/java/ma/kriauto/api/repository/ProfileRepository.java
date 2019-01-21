package ma.kriauto.api.repository;

import ma.kriauto.api.model.Profile;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository {
	
	@Query("SELECT p FROM Profile p WHERE p.login=:login")
	public Profile fetchProfileByLogin(@Param("login") String login);
	
	@Query("SELECT p FROM Profile p WHERE p.authtoken=:token")
	public Profile fetchProfileByToken(@Param("token") String token);

}
