package ma.kriauto.api.service;

import ma.kriauto.api.model.User;

public interface UserService {
	
	public User saveUser(User user);
	public User fetchUserById(Long id);
	public User fetchUserByLogin(String login);
	public String generateToken(User user);

}
