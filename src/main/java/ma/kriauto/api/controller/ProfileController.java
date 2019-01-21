package ma.kriauto.api.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import ma.kriauto.api.exception.ResourceNotFoundException;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.PushNotif;
import ma.kriauto.api.service.ProfileService;
import ma.kriauto.api.service.PushNotifService;


@RestController
public class ProfileController { 
	
	private static Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private PushNotifService pushnotifService;

    
//    @PostMapping("/createUser")
//    public Profile createUser(@RequestBody Profile profile) {
//      logger.info("--> Start Create User");
//  	  logger.debug("--> User : "+profile);
//  	  String token = profileService.generateToken(profile);
//  	  user.setToken(token);
//  	  logger.info("--> End Create User");
//      return profileService.saveUser(profile);
//    }
    
    @PostMapping("/login")
    public Profile login(@RequestBody Profile profile) {
      logger.info("-- Start login : "+profile);
  	  Profile current = profileService.fetchProfileByLogin(profile.getLogin());
  	  if(null == current){
		throw new ResourceNotFoundException("User not found with id ");
	  }else if(!current.getPassword().equals(profile.getPassword())){
		throw new ResourceNotFoundException("Missing password ");
	  }else if(null == profile.getNotifToken()){
		throw new ResourceNotFoundException("notif token Required");
	  }else{
		PushNotif pushnotif = new PushNotif();
		if(null == pushnotifService.fetchDeviceByPushToken(profile.getNotifToken())){
			pushnotif.setIdProfile(current.getId());
			pushnotif.setPushToken(profile.getNotifToken());
			pushnotifService.save(pushnotif);
		}
	  }
  	  logger.info("-- End login --");
      return current;
    }
    
    @PostMapping("/logout")
    public void logout(@RequestHeader  @RequestBody Profile profile) {
      logger.info("--> Start logout "+profile);
  	  Profile current = profileService.fetchProfileByToken(profile.getAuthToken());
  	  PushNotif pushnotif  = pushnotifService.fetchDeviceByPushToken(profile.getNotifToken());
  	  if(null == current){
		throw new ResourceNotFoundException("User not found with id");
	  }else if(null == pushnotif){
		throw new ResourceNotFoundException("Device not found");
	  }else if(!pushnotif.getIdProfile().equals(current.getId())){
		throw new ResourceNotFoundException("Identifier Missing");
	  }else{
		  pushnotifService.delete(pushnotif);
	  }
  	  logger.info("--> End logout");
    }

//    @GetMapping("/Users")
//    public List<User> getUsers() {
//    	logger.info("--> Start getUsers");
//        return (List<User>) userService.findAll();
//    }


//    @PostMapping("/Users")
//    public User createUser(@RequestBody User user) {
//    	logger.info("--> Start Create User");
//    	logger.info("--> User : "+user);
//        return userRepository.save(user);
//    }

//    @PutMapping("/Users/{UserId}")
//    public User updateUser(@PathVariable Long UserId, @Valid @RequestBody User UserRequest) {
//    	logger.info("--> Start updateUser");
//    	logger.info("--> User : "+UserRequest);
//    	User current = userService.fetchUserById(UserId);
//    	if(null == current){
//    		throw new ResourceNotFoundException("User not found with id " + UserId);
//    	}else{
//    		//current = userService.save(current);
//    	}
//        return current;
//    }


//    @DeleteMapping("/Users/{UserId}") 
//    public ResponseEntity<?> deleteUser(@PathVariable Long UserId) {
//    	logger.info("--> Start deleteUser");
//    	logger.info("--> User : "+UserId);
//        return userRepository.findById(UserId)
//                .map(User -> {
//                    userRepository.delete(User);
//                    return ResponseEntity.ok().build();
//                }).orElseThrow(() -> new ResourceNotFoundException("User not found with id " + UserId));
//    }
}
