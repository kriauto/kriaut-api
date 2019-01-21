package ma.kriauto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ma.kriauto.api.model.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	@Query("SELECT p FROM Profile p WHERE p.login=:login")
	public Profile fetchProfileByLogin(@Param("login") String login);
	
	@Query("SELECT p FROM Profile p WHERE p.authToken=:token")
	public Profile fetchProfileByToken(@Param("token") String token);
	
	@Query("SELECT p FROM Profile p WHERE p.mail=:mail")
	public Profile fetchProfileByMail(@Param("mail") String mail);

}
