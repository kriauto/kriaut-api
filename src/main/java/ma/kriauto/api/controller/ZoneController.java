package ma.kriauto.api.controller;

import java.util.List;

import ma.kriauto.api.common.ErrorLabel;
import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.response.Location;
import ma.kriauto.api.service.AgencyService;
import ma.kriauto.api.service.CarService;
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
public class ZoneController {
	
	private static Logger logger = LoggerFactory.getLogger(ZoneController.class);
	
	@Autowired
	private ZoneService zoneService;
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
    private AgencyService agencyService;
	
	@Autowired
    private CarService carService;
	
	@CrossOrigin
    @PostMapping("/loadcarzone")
    public ResponseEntity<?> loadzone(@RequestHeader(value="Authorization") String authorization, @RequestBody Zone zone) {
      logger.info("--> Start loadzone "+zone);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  zone = zoneService.fetchZoneByCarIdAndRank(zone.getCarid(), zone.getRank());
  	  logger.info("--> End loadzone");
  	    return new ResponseEntity<Zone>(zone,HttpStatus.OK);
    }
	
	@CrossOrigin
    @PostMapping("/updatecarzone")
    public ResponseEntity<?> updatezone(@RequestHeader(value="Authorization") String authorization, @RequestBody Zone zone) {
      logger.info("--> Start updatezone "+zone);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
//  	  Zone currentzone = zoneService.fetchZoneByCarIdAndRank(zone.getCarid(), zone.getRank());
//  	  zone.setId(currentzone.getId());
  	  zoneService.save(zone);
  	  logger.info("--> End updatezone");
  	  return new ResponseEntity(new CustomErrorType(ErrorLabel.DATA_SAVED),HttpStatus.OK);
    }
}
