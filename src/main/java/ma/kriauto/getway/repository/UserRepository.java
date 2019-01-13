package ma.kriauto.getway.repository;

import ma.kriauto.getway.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	public User save(User user);
	
	@Query("SELECT u FROM User u WHERE u.id=:id")
	public User fetchUserById(@Param("id") Long id);
	
	@Query("SELECT u FROM User u WHERE u.login=:login")
	public User fetchUserByLogin(@Param("login") String login);
}
