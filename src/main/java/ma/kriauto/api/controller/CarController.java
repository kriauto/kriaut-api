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
import ma.kriauto.api.model.Position;
import ma.kriauto.api.model.Profile;
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
    @PostMapping("/loadhistorycar")
    public ResponseEntity<?> loadhistorycar(@RequestHeader(value="Authorization") String authorization) {
      logger.info("--> Start loadhistorycar "+authorization);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
  	  List<Location> cars = carService.fetchCarHistoryByAgencyId(agency.getId());
  	  logger.info("--> End loadhistorycar");
  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
    }
	
	@CrossOrigin
    @PostMapping("/loadzonecar")
    public ResponseEntity<?> loadzonecar(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("--> Start loadzonecar "+authorization);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
  	  List<Location> cars = carService.fetchCarZoneByAgencyIdAndRank(agency.getId(),car.getRank());
  	  logger.info("--> End loadzonecar");
  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
    }
	
    @CrossOrigin
	@PostMapping("/loadcarslocations")
    public ResponseEntity<?> loadcarslocations(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
      logger.info("-- Start loadcarslocations : "+authorization+" :"+car);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  List<Location> locations = carService.fetchLocationsByAgencyIdAndDate(agency.getId(),car.getDate());
  	  logger.info("-- End loadcarslocations --");
      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
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
