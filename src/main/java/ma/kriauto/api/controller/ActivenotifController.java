package ma.kriauto.api.controller;

import java.util.List;

import ma.kriauto.api.common.ErrorLabel;
import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.ActiveNotif;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.response.Location;
import ma.kriauto.api.service.ActiveNotifService;
import ma.kriauto.api.service.AgencyService;
import ma.kriauto.api.service.CarService;
import ma.kriauto.api.service.NotificationService;
import ma.kriauto.api.service.ProfileService;
import ma.kriauto.api.service.ZoneService;

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
public class ActivenotifController {
	
	private static Logger logger = LoggerFactory.getLogger(ActivenotifController.class);
	
	@Autowired
	private ActiveNotifService activenotifService;
	
	@Autowired
	private ProfileService profileService;
	
	@CrossOrigin
    @PostMapping("/updateactivenotif")
    public ResponseEntity<?> updateactivenotif(@RequestHeader(value="Authorization") String authorization, @RequestBody ActiveNotif activenotif) {
      logger.info("--> Start updateactivenotif "+activenotif);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  activenotifService.save(activenotif);
  	  logger.info("--> End updateactivenotif");
  	  return new ResponseEntity(new CustomErrorType(ErrorLabel.DATA_SAVED),HttpStatus.OK);
    }
}
