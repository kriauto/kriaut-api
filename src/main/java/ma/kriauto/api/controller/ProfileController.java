package ma.kriauto.api.controller;


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
import ma.kriauto.api.request.AuthenticationIn;
import ma.kriauto.api.response.AuthenticationOut;
import ma.kriauto.api.service.ProfileService;
import ma.kriauto.api.service.PushNotifService;
import ma.kriauto.api.service.SenderService;
import ma.kriauto.api.service.UtilityService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProfileController { 

    @Autowired
    private ProfileService profileService;
    
    @Autowired
    private PushNotifService pushnotifService;
    
    @Autowired
    private SenderService senderService;
    
    @Autowired
    private UtilityService utilityService;
    
    @CrossOrigin
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationIn auth) {
      log.info("-- Start login : "+auth);
  	  Profile current = profileService.fetchProfileByLogin(auth.getLogin());
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!current.getPassword().equals(auth.getPassword())){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.PASSWORD_MISSING),HttpStatus.NOT_FOUND);
	  }else{ 
		if(null == current.getAuthToken()){
			String token = utilityService.hash256Profile(current);
			current.setAuthToken(token);
	  		profileService.save(current);
		}
		
		PushNotif pushnotif = new PushNotif();
		if(null == pushnotifService.fetchDeviceByPushToken(auth.getNotifToken())){
			pushnotif.setIdProfile(current.getId());
			pushnotif.setPushToken(auth.getNotifToken());
			pushnotifService.save(pushnotif);
		}
	  }
  	  log.info("-- End login --");
  	  AuthenticationOut menu = profileService.fetchMenuDtoByLogin(auth.getLogin());
      return new ResponseEntity<AuthenticationOut>(menu, HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value="Authorization") String authorization, @RequestBody AuthenticationIn auth) {
      log.info("--> Start logout "+auth);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  PushNotif pushnotif  = pushnotifService.fetchDeviceByPushToken(auth.getNotifToken());
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(null == pushnotif){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.NOTIF_TOKEN_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!pushnotif.getIdProfile().equals(current.getId())){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.NOTIF_TOKEN_MISSING),HttpStatus.NOT_FOUND);
	  }else{
		  pushnotifService.delete(pushnotif);
	  }
  	  log.info("--> End logout");
  	    return new ResponseEntity(new CustomErrorType(ErrorLabel.LOGOUT_SUCCESS),HttpStatus.OK);
    }
    
    @CrossOrigin
	@PostMapping("/loginweb")
    public ResponseEntity<?> loginweb(@RequestBody AuthenticationIn auth) {
      log.info("-- Start loginweb : "+auth);
  	  Profile current = profileService.fetchProfileByLogin(auth.getLogin());
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!current.getPassword().equals(auth.getPassword())){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.PASSWORD_MISSING),HttpStatus.NOT_FOUND);
	  }else{ 
		if(null == current.getAuthToken()){
			String token = utilityService.hash256Profile(current);
			current.setAuthToken(token);
	  		profileService.save(current);
		}
	  }
  	  log.info("-- End loginweb --");
  	  AuthenticationOut menu = profileService.fetchMenuDtoByLogin(auth.getLogin());
      return new ResponseEntity<AuthenticationOut>(menu, HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/logoutweb")
    public ResponseEntity<?> logoutweb(@RequestHeader(value="Authorization") String authorization, @RequestBody AuthenticationIn auth) {
      log.info("--> Start logoutweb "+auth);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  log.info("--> End logoutweb");
  	    return new ResponseEntity(new CustomErrorType(ErrorLabel.LOGOUT_SUCCESS),HttpStatus.OK);
    }
    
    
    @CrossOrigin
    @PostMapping("/initpassword")
    public ResponseEntity<?> initPassword(@RequestBody AuthenticationIn auth) {
    	log.info("--> Start initPassword "+auth);
    	if(null == auth.getMail()){
    		return new ResponseEntity(new CustomErrorType(ErrorLabel.MAIL_REQUIRED),HttpStatus.NOT_FOUND);
    	}
    	Profile current = profileService.fetchProfileByMail(auth.getMail());
    	if(null == current){
    		return new ResponseEntity(new CustomErrorType(ErrorLabel.MAIL_NOT_FOUND),HttpStatus.NOT_FOUND);

    	}
    	String from = "noreply@kriauto.ma";
    	String to = current.getMail();
    	String subject = "Identifiants de connexion";
    	String message = "Bonjour "+current.getName()+", <br/><br/> Veuillez trouver vos identifiants de connexion : <br/><br/> - login : "+current.getLogin()+" <br/> - Mot de passe : "+current.getPassword()+" <br/><br/> l'équipe KriAuto.";
    	senderService.sendMail(from, to, subject, message);
    	log.info("--> End initPassword "+auth);
    	return new ResponseEntity(new CustomErrorType(ErrorLabel.MAIL_SEND),HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/updateprofile")
    public ResponseEntity<?> updateprofile(@RequestHeader(value="Authorization") String authorization, @RequestBody Profile profile) {
      log.info("--> Start updateprofile "+profile);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  profileService.save(profile);
  	  log.info("--> End updateprofile");
  	  return new ResponseEntity(new CustomErrorType(ErrorLabel.DATA_SAVED),HttpStatus.OK);
    }
}
