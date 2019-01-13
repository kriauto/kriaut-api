package ma.kriauto.getway.service;

import ma.kriauto.getway.model.User;

public interface UserService {
	
	public User saveUser(User user);
	public User fetchUserById(Long id);
	public User fetchUserByLogin(String login);
	public String generateToken(User user);

}
