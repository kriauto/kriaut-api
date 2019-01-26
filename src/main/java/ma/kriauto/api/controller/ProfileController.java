package ma.kriauto.api.controller;


import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.PushNotif;
import ma.kriauto.api.service.ProfileService;
import ma.kriauto.api.service.PushNotifService;
import ma.kriauto.api.service.SenderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ProfileController { 
	
	private static Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private PushNotifService pushnotifService;
    
    @Autowired
    private SenderService senderService;
    
    

    
//    @PostMapping("/createUser")
//    public Profile createUser(@RequestBody Profile profile) {
//      logger.info("--> Start Create User");
//  	  logger.debug("--> User : "+profile);
//  	  String token = profileService.generateToken(profile);
//  	  user.setToken(token);
//  	  logger.info("--> End Create User");
//      return profileService.saveUser(profile);
//    }
    @SuppressWarnings("unchecked")
    @CrossOrigin
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Profile profile) {
      logger.info("-- Start login : "+profile);
  	  Profile current = profileService.fetchProfileByLogin(profile.getLogin());
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType("User not found with id"),HttpStatus.NOT_FOUND);
	  }else if(!current.getPassword().equals(profile.getPassword())){
		return new ResponseEntity(new CustomErrorType("Missing password"),HttpStatus.NOT_FOUND);
	  }else if(null == profile.getNotifToken()){
		return new ResponseEntity(new CustomErrorType("notif token Required"),HttpStatus.NOT_FOUND);
	  }else{
		PushNotif pushnotif = new PushNotif();
		if(null == pushnotifService.fetchDeviceByPushToken(profile.getNotifToken())){
			pushnotif.setIdProfile(current.getId());
			pushnotif.setPushToken(profile.getNotifToken());
			pushnotifService.save(pushnotif);
		}
	  }
  	  logger.info("-- End login --");
      return new ResponseEntity<Profile>(current, HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value="Authorization") String authorization, @RequestBody Profile profile) {
      logger.info("--> Start logout "+profile);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  PushNotif pushnotif  = pushnotifService.fetchDeviceByPushToken(profile.getNotifToken());
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType("User not found with id"),HttpStatus.NOT_FOUND);
	  }else if(null == pushnotif){
		return new ResponseEntity(new CustomErrorType("Device not found"),HttpStatus.NOT_FOUND);
	  }else if(!pushnotif.getIdProfile().equals(current.getId())){
		return new ResponseEntity(new CustomErrorType("Identifier Missing"),HttpStatus.NOT_FOUND);
	  }else{
		  pushnotifService.delete(pushnotif);
	  }
  	  logger.info("--> End logout");
  	    return new ResponseEntity(new CustomErrorType("Logout Success"),HttpStatus.OK);
    }
    
    
    @CrossOrigin
    @PostMapping("/initpassword")
    public ResponseEntity<?> initPassword(@RequestBody Profile profile) {
    	logger.info("--> Start initPassword "+profile);
    	if(null == profile.getMail()){
    		return new ResponseEntity(new CustomErrorType("MAIL_REQUIRED"),HttpStatus.NOT_FOUND);
    	}
    	Profile current = profileService.fetchProfileByMail(profile.getMail());
    	if(null == current){
    		return new ResponseEntity(new CustomErrorType("MAIL_NOT_FOUND"),HttpStatus.NOT_FOUND);

    	}
    	String from = "contact@kriauto.ma";
    	String to = current.getMail();
    	String subject = "Identifiants de connexion";
    	String message = "Bonjour "+current.getName()+", <br/><br/> Veuillez trouver vos identifiants de connexion : <br/><br/> - login : "+current.getLogin()+" <br/> - Mot de passe : "+current.getPassword()+" <br/><br/> l'équipe KriAuto.";
    	senderService.sendMail(from, to, subject, message);
    	logger.info("--> End initPassword "+profile);
    	return new ResponseEntity(new CustomErrorType("MAIL_SEND"),HttpStatus.OK);
    	//return new ResponseMessage(ResponseMessage.Type.success, "PASSWORD_SEND",Constant.getLabels().get("PASSWORD_SEND").toString());
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
