package ma.kriauto.api.controller;

import java.util.List;

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
import ma.kriauto.api.dto.Location;
import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.service.AgencyService;
import ma.kriauto.api.service.CarService;
import ma.kriauto.api.service.ProfileService;

@RestController
public class CarController {
	
	private static Logger logger = LoggerFactory.getLogger(CarController.class);
	
	@Autowired
    private ProfileService profileService;
	
	@Autowired
    private AgencyService agencyService;
	
	@Autowired
    private CarService carService;
	
	@CrossOrigin
    @PostMapping("/menuhistory")
    public ResponseEntity<?> menuhistory(@RequestHeader(value="Authorization") String authorization) {
      logger.info("--> Start menuhistory "+authorization);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
  	  List<Location> cars = carService.fetchCarHistoryByAgencyId(agency.getId());
  	  logger.info("--> End menuhistory");
  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
    }
	
	
    @CrossOrigin
	@PostMapping("/menulastposition")
    public ResponseEntity<?> menulastposition(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("-- Start menulastposition : "+authorization+" :"+car);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  List<Location> locations = carService.fetchLocationsByAgencyIdAndDate(agency.getId(),car.getDate());
  	  logger.info("-- End menulastposition --");
      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }
    
    @CrossOrigin
	@PostMapping("/menumaxspeed")
    public ResponseEntity<?> menumaxspeed(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("-- Start menumaxspeed : "+authorization+" :"+car);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  List<Location> locations = carService.fetchCarMaxSpeedByAgencyId(agency.getId(),car.getDate());
  	  logger.info("-- End menumaxspeed --");
      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }
    
    @CrossOrigin
	@PostMapping("/menumaxcourse")
    public ResponseEntity<?> menumaxcourse(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("-- Start menumaxcourse : "+authorization+" :"+car);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  List<Location> locations = carService.fetchCarMaxCourseByAgencyId(agency.getId(),car.getDate());
  	  logger.info("-- End menumaxcourse --");
      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }
    
    @CrossOrigin
	@PostMapping("/menuprincipalfuel")
    public ResponseEntity<?> menuprincipalfuel(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("-- Start menuprincipalfuel : "+authorization+" :"+car);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  List<Location> locations = carService.fetchCarFuelPrincipaleByAgencyId(agency.getId(),car.getDate());
  	  logger.info("-- End menuprincipalfuel --");
      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }
    
    @CrossOrigin
	@PostMapping("/menusecondaryfuel")
    public ResponseEntity<?> menusecondaryfuel(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("-- Start menusecondaryfuel : "+authorization+" :"+car);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  List<Location> locations = carService.fetchCarFuelSecondaireByAgencyId(agency.getId(),car.getDate());
  	  logger.info("-- End menusecondaryfuel --");
      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/menuzone")
    public ResponseEntity<?> menuzone(@RequestHeader(value="Authorization") String authorization,@RequestBody Zone zone) {
      logger.info("--> Start menuzone "+authorization);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
  	  List<Location> cars = carService.fetchCarZoneByAgencyIdAndRank(agency.getId(),zone.getRank());
  	  logger.info("--> End menuzone");
  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/menutemperaturemo")
    public ResponseEntity<?> menutemperaturemo(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("--> Start menutemperaturemo "+authorization);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
  	  List<Location> cars = carService.fetchCarTemperatureMoByAgencyId(agency.getId(),car.getDate());
  	  logger.info("--> End menutemperaturemo");
  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
    }
    
    @CrossOrigin
    @PostMapping("/menutemperaturefr")
    public ResponseEntity<?> menutemperaturefr(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("--> Start menutemperaturefr "+authorization);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
  	  List<Location> cars = carService.fetchCarTemperatureFrByAgencyId(agency.getId(),car.getDate());
  	  logger.info("--> End menutemperaturefr");
  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
    }
	
    @CrossOrigin
	@PostMapping("/loadcarlocations")
    public ResponseEntity<?> loadcarlocations(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("-- Start loadcarlocations : "+authorization+" :"+car);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  List<Location> locations = carService.fetchLocationsByCarId(car.getId(), car.getDate());
  	  logger.info("-- End loadcarlocations --");
      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }
}
