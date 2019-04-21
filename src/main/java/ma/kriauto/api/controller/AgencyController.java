package ma.kriauto.api.controller;

import ma.kriauto.api.common.ErrorLabel;
import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Parameter;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.service.AgencyService;
import ma.kriauto.api.service.ParameterService;
import ma.kriauto.api.service.ProfileService;

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
public class AgencyController {
	
	private static Logger logger = LoggerFactory.getLogger(ParameterController.class);
	
	@Autowired
	private AgencyService agencyService;
	
	@Autowired
	private ProfileService profileService;
	
	@CrossOrigin
    @PostMapping("/updateagency")
    public ResponseEntity<?> updateagency(@RequestHeader(value="Authorization") String authorization, @RequestBody Agency agency) {
      logger.info("--> Start updateagency "+agency);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  agencyService.save(agency);
  	  logger.info("--> End updateagency");
  	  return new ResponseEntity(new CustomErrorType(ErrorLabel.DATA_SAVED),HttpStatus.OK);
    }
}
