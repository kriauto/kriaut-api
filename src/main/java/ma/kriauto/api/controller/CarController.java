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
import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.request.MenuIn;
import ma.kriauto.api.response.AccountOut;
import ma.kriauto.api.response.AgencyOut;
import ma.kriauto.api.response.DoorOut;
import ma.kriauto.api.response.DriverOut;
import ma.kriauto.api.response.FuelOut;
import ma.kriauto.api.response.HistoryOut;
import ma.kriauto.api.response.LastPositionOut;
import ma.kriauto.api.response.Location;
import ma.kriauto.api.response.MaxCourseOut;
import ma.kriauto.api.response.MaxSpeedOut;
import ma.kriauto.api.response.MaxTemperatureOut;
import ma.kriauto.api.response.NotificationOut;
import ma.kriauto.api.response.ParametersOut;
import ma.kriauto.api.response.ProfileOut;
import ma.kriauto.api.response.StartStopOut;
import ma.kriauto.api.response.ZoneOut;
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
	@PostMapping("/loadmenu")
    public ResponseEntity<?> menulastposition(@RequestHeader(value="Authorization") String authorization,@RequestBody MenuIn menu) {
      logger.info("--> Start loadmenu : "+authorization+" : "+menu);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
  	  if(menu.getType().equals("00")) {
  	    List<LastPositionOut> locations = carService.fetchLastPositionByAgencyIdAndDate(agency.getId(),menu.getDate());
  	    logger.info("--> End loadmenu --");
  	    return new ResponseEntity<List<LastPositionOut>>(locations, HttpStatus.OK);
  	  }else if(menu.getType().equals("01")) {
  		List<HistoryOut> cars = carService.fetchHistoryByAgencyId(agency.getId());
    	logger.info("--> End loadmenu");
    	return new ResponseEntity<List<HistoryOut>>(cars, HttpStatus.OK);
      }else if (menu.getType().equals("02")){
    	List<MaxSpeedOut> locations = carService.fetchCarMaxSpeedByAgencyId(agency.getId(),menu.getDate());
      	logger.info("--> End loadmenu --");
        return new ResponseEntity<List<MaxSpeedOut>>(locations, HttpStatus.OK);
      }else if (menu.getType().equals("03")) {
    	List<MaxCourseOut> locations = carService.fetchCarMaxCourseByAgencyId(agency.getId(),menu.getDate());
      	logger.info("--> End loadmenu --");
        return new ResponseEntity<List<MaxCourseOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("04")) {
	    List<FuelOut> locations = carService.fetchCarFuelPrincipaleByAgencyId(agency.getId(),menu.getDate());
	    logger.info("--> End loadmenu --");
	    return new ResponseEntity<List<FuelOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("05")) {
	    List<FuelOut> locations = carService.fetchCarFuelSecondaireByAgencyId(agency.getId(),menu.getDate());
	    logger.info("--> End loadmenu --");
	    return new ResponseEntity<List<FuelOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("06")) {
	    List<MaxTemperatureOut> locations = carService.fetchCarTemperatureMoByAgencyId(agency.getId(),menu.getDate());
	    logger.info("--> End loadmenu --");
	    return new ResponseEntity<List<MaxTemperatureOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("07")) {
	    List<MaxTemperatureOut> locations = carService.fetchCarTemperatureFrByAgencyId(agency.getId(),menu.getDate());
	    logger.info("--> End loadmenu --");
	    return new ResponseEntity<List<MaxTemperatureOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("08")) {
		List<ZoneOut> locations = carService.fetchCarZoneByAgencyIdAndNumber(agency.getId(),menu.getNumber());
		logger.info("--> End loadmenu --");
		return new ResponseEntity<List<ZoneOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("09")) {
		List<ZoneOut> locations = carService.fetchCarZoneByAgencyIdAndNumber(agency.getId(),menu.getNumber());
		logger.info("--> End loadmenu --");
		return new ResponseEntity<List<ZoneOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("10")) {
		List<NotificationOut> locations = carService.fetchCarNotificationByAgencyIdAndNumber(agency.getId(),menu.getDate());
		logger.info("--> End loadmenu --");
		return new ResponseEntity<List<NotificationOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("11")) {
		List<NotificationOut> locations = carService.fetchCarNotificationByAgencyIdAndNumber(agency.getId(),menu.getDate());
		logger.info("--> End loadmenu --");
		return new ResponseEntity<List<NotificationOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("12")) {
		List<DoorOut> locations = carService.fetchCarDoorByAgencyIdAndNumber(agency.getId(),menu.getDate());
		logger.info("--> End loadmenu --");
		return new ResponseEntity<List<DoorOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("13")) {
		List<DriverOut> locations = carService.fetchCarDriverByAgencyIdAndNumber(agency.getId(),menu.getDate());
		logger.info("--> End loadmenu --");
		return new ResponseEntity<List<DriverOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("14")) {
		List<ParametersOut> locations = carService.fetchCarParametersByAgencyIdAndNumber(agency.getId(),menu.getDate());
		logger.info("--> End loadmenu --");
		return new ResponseEntity<List<ParametersOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("15")) {
		List<StartStopOut> locations = carService.fetchCarStartStopByAgencyIdAndNumber(agency.getId(),menu.getDate());
		logger.info("--> End loadmenu --");
		return new ResponseEntity<List<StartStopOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("16")) {
		AccountOut account = new AccountOut();
		ProfileOut profileout = new ProfileOut();
		AgencyOut  agencyout  = new AgencyOut();
		profileout.setId(current.getId());
		profileout.setLogin(current.getLogin());
		profileout.setMail(current.getMail());
		profileout.setName(current.getName());
		profileout.setPassword(current.getPassword());
		profileout.setPhone(current.getPhone());
		account.setProfile(profileout);
		agencyout.setAddress(agency.getAddress());
		agencyout.setCity(agency.getCity());
		agencyout.setFax(agency.getFax());
		agencyout.setId(agency.getId());
		agencyout.setName(agency.getName());
		agencyout.setPhone(agency.getPhone());
		account.setAgency(agencyout);
		logger.info("--> End loadmenu --");
		return new ResponseEntity<AccountOut>(account, HttpStatus.OK);
	  }else {
  		logger.info("--> End loadmenu --");
  		return new ResponseEntity(new CustomErrorType(ErrorLabel.MENY_TYPE),HttpStatus.NOT_FOUND);
  	  }
    }

	
//	@CrossOrigin
//	@PostMapping("/historycarlocations")
//    public ResponseEntity<?> historycarlocations(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
//      logger.info("-- Start historycarlocations : "+authorization+" :"+car);
//      String token = authorization.replaceAll("Basic", "");
//  	  Profile current = profileService.fetchProfileByToken(token);
//  	  if(null == current){
//		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
//	  }
//  	  List<HistoryLocationDTO> historylocations = carService.fetchHistoryCarLocationsByCarIdAndDate(car.getId(), car.getDate());
//  	  logger.info("-- End historycarlocations --");
//      return new ResponseEntity<List<HistoryLocationDTO>>(historylocations, HttpStatus.OK);
//    }
    
//    @CrossOrigin
//	@PostMapping("/menumaxspeed")
//    public ResponseEntity<?> menumaxspeed(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
//      logger.info("-- Start menumaxspeed : "+authorization+" :"+car);
//      String token = authorization.replaceAll("Basic", "");
//  	  Profile current = profileService.fetchProfileByToken(token);
//  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
//  	  if(null == current){
//		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
//	  }
//  	  List<Location> locations = carService.fetchCarMaxSpeedByAgencyId(agency.getId(),car.getDate());
//  	  logger.info("-- End menumaxspeed --");
//      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
//    }
    
//    @CrossOrigin
//	@PostMapping("/menumaxcourse")
//    public ResponseEntity<?> menumaxcourse(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
//      logger.info("-- Start menumaxcourse : "+authorization+" :"+car);
//      String token = authorization.replaceAll("Basic", "");
//  	  Profile current = profileService.fetchProfileByToken(token);
//  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
//  	  if(null == current){
//		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
//	  }
//  	  List<Location> locations = carService.fetchCarMaxCourseByAgencyId(agency.getId(),car.getDate());
//  	  logger.info("-- End menumaxcourse --");
//      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
//    }
    
//    @CrossOrigin
//	@PostMapping("/menuprincipalfuel")
//    public ResponseEntity<?> menuprincipalfuel(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
//      logger.info("-- Start menuprincipalfuel : "+authorization+" :"+car);
//      String token = authorization.replaceAll("Basic", "");
//  	  Profile current = profileService.fetchProfileByToken(token);
//  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
//  	  if(null == current){
//		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
//	  }
//  	  List<Location> locations = carService.fetchCarFuelPrincipaleByAgencyId(agency.getId(),car.getDate());
//  	  logger.info("-- End menuprincipalfuel --");
//      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
//    }
//    
//    @CrossOrigin
//	@PostMapping("/menusecondaryfuel")
//    public ResponseEntity<?> menusecondaryfuel(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
//      logger.info("-- Start menusecondaryfuel : "+authorization+" :"+car);
//      String token = authorization.replaceAll("Basic", "");
//  	  Profile current = profileService.fetchProfileByToken(token);
//  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId()); 
//  	  if(null == current){
//		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
//	  }
//  	  List<Location> locations = carService.fetchCarFuelSecondaireByAgencyId(agency.getId(),car.getDate());
//  	  logger.info("-- End menusecondaryfuel --");
//      return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
//    }
//    
//    @CrossOrigin
//    @PostMapping("/menuzone")
//    public ResponseEntity<?> menuzone(@RequestHeader(value="Authorization") String authorization,@RequestBody Zone zone) {
//      logger.info("--> Start menuzone "+authorization);
//      String token = authorization.replaceAll("Basic", "");
//  	  Profile current = profileService.fetchProfileByToken(token);
//  	  if(null == current){
//		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
//	  }
//  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
//  	  List<Location> cars = carService.fetchCarZoneByAgencyIdAndRank(agency.getId(),zone.getRank());
//  	  logger.info("--> End menuzone");
//  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
//    }
//    
//    @CrossOrigin
//    @PostMapping("/menutemperaturemo")
//    public ResponseEntity<?> menutemperaturemo(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
//      logger.info("--> Start menutemperaturemo "+authorization);
//      String token = authorization.replaceAll("Basic", "");
//  	  Profile current = profileService.fetchProfileByToken(token);
//  	  if(null == current){
//		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
//	  }
//  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
//  	  List<Location> cars = carService.fetchCarTemperatureMoByAgencyId(agency.getId(),car.getDate());
//  	  logger.info("--> End menutemperaturemo");
//  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
//    }
//    
//    @CrossOrigin
//    @PostMapping("/menutemperaturefr")
//    public ResponseEntity<?> menutemperaturefr(@RequestHeader(value="Authorization") String authorization,@RequestBody Car car) {
//      logger.info("--> Start menutemperaturefr "+authorization);
//      String token = authorization.replaceAll("Basic", "");
//  	  Profile current = profileService.fetchProfileByToken(token);
//  	  if(null == current){
//		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
//	  }
//  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
//  	  List<Location> cars = carService.fetchCarTemperatureFrByAgencyId(agency.getId(),car.getDate());
//  	  logger.info("--> End menutemperaturefr");
//  	  return new ResponseEntity<List<Location>>(cars, HttpStatus.OK);
//    }
  
}
