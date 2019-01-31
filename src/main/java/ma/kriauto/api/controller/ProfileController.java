package ma.kriauto.api.controller;


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

import ma.kriauto.api.common.ErrorLabel;
import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.PushNotif;
import ma.kriauto.api.service.ProfileService;
import ma.kriauto.api.service.PushNotifService;
import ma.kriauto.api.service.SenderService;



@RestController
public class ProfileController { 
	
	private static Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private PushNotifService pushnotifService;
    
    @Autowired
    private SenderService senderService;
    
    @CrossOrigin
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Profile profile) {
      logger.info("-- Start login : "+profile);
  	  Profile current = profileService.fetchProfileByLogin(profile.getLogin());
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!current.getPassword().equals(profile.getPassword())){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.PASSWORD_MISSING),HttpStatus.NOT_FOUND);
	  }else if(null == profile.getNotifToken()){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.NOTIF_TOKEN_REQUIRED),HttpStatus.NOT_FOUND);
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
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(null == pushnotif){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.NOTIF_TOKEN_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!pushnotif.getIdProfile().equals(current.getId())){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.NOTIF_TOKEN_MISSING),HttpStatus.NOT_FOUND);
	  }else{
		  pushnotifService.delete(pushnotif);
	  }
  	  logger.info("--> End logout");
  	    return new ResponseEntity(new CustomErrorType(ErrorLabel.LOGOUT_SUCCESS),HttpStatus.OK);
    }
    
    
    @CrossOrigin
    @PostMapping("/initpassword")
    public ResponseEntity<?> initPassword(@RequestBody Profile profile) {
    	logger.info("--> Start initPassword "+profile);
    	if(null == profile.getMail()){
    		return new ResponseEntity(new CustomErrorType(ErrorLabel.MAIL_REQUIRED),HttpStatus.NOT_FOUND);
    	}
    	Profile current = profileService.fetchProfileByMail(profile.getMail());
    	if(null == current){
    		return new ResponseEntity(new CustomErrorType(ErrorLabel.MAIL_NOT_FOUND),HttpStatus.NOT_FOUND);

    	}
    	String from = "contact@kriauto.ma";
    	String to = current.getMail();
    	String subject = "Identifiants de connexion";
    	String message = "Bonjour "+current.getName()+", <br/><br/> Veuillez trouver vos identifiants de connexion : <br/><br/> - login : "+current.getLogin()+" <br/> - Mot de passe : "+current.getPassword()+" <br/><br/> l'équipe KriAuto.";
    	senderService.sendMail(from, to, subject, message);
    	logger.info("--> End initPassword "+profile);
    	return new ResponseEntity(new CustomErrorType(ErrorLabel.MAIL_SEND),HttpStatus.OK);
    }
}
