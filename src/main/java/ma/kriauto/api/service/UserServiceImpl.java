package ma.kriauto.api.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import ma.kriauto.api.dto.UserDTO;
import ma.kriauto.api.model.User;
import ma.kriauto.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
    private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public User fetchUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.fetchUserById(id);
	}

	@Override
	public User fetchUserByLogin(String login) {
		// TODO Auto-generated method stub
		return userRepository.fetchUserByLogin(login);
	}
	
	@Override
	public String generateToken(User user){
		String textToHash = user.getLogin()+":"+user.getPassword(), encoded = null;
		MessageDigest digest;
        try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] byteOfTextToHash = textToHash.getBytes("UTF-8");
		    byte[] hashedByetArray = digest.digest(byteOfTextToHash);
		    encoded = Base64.getEncoder().encodeToString(hashedByetArray);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return encoded;
	}
}
