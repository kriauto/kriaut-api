package ma.kriauto.api.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.kriauto.api.common.ErrorLabel;
import ma.kriauto.api.exception.CustomErrorType;
import ma.kriauto.api.model.ActiveNotif;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Contact;
import ma.kriauto.api.model.Parameter;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.request.DataIn;
import ma.kriauto.api.request.MenuIn;
import ma.kriauto.api.response.AccountOut;
import ma.kriauto.api.response.DetailOut;
import ma.kriauto.api.response.DoorOut;
import ma.kriauto.api.response.DriverOut;
import ma.kriauto.api.response.FuelOut;
import ma.kriauto.api.response.HistoryLocationOut;
import ma.kriauto.api.response.HistoryOut;
import ma.kriauto.api.response.LastPositionOut;
import ma.kriauto.api.response.MaxCourseOut;
import ma.kriauto.api.response.MaxSpeedOut;
import ma.kriauto.api.response.MaxTemperatureOut;
import ma.kriauto.api.response.NotifMessageOut;
import ma.kriauto.api.response.NotificationOut;
import ma.kriauto.api.response.ParametersOut;
import ma.kriauto.api.response.StartStopOut;
import ma.kriauto.api.response.ZoneOut;
import ma.kriauto.api.service.AgencyService;
import ma.kriauto.api.service.CarService;
import ma.kriauto.api.service.ProfileService;

import ma.kriauto.api.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CarController {
	
	
	@Autowired
    private ProfileService profileService;
	
	@Autowired
    private AgencyService agencyService;
	
	@Autowired
    private CarService carService;

	@Autowired
	private SenderService senderService;
	
	/*** menu acces ***/
	@CrossOrigin
	@PostMapping("/loadmenu")
    public ResponseEntity<?> menulastposition(@RequestHeader(value="Authorization") String authorization,@RequestBody MenuIn menu) throws ParseException {
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!current.getIsActive() && !menu.getType().equals("17") && !menu.getType().equals("00")){
		  return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_ACTIVE),HttpStatus.FORBIDDEN);
	  }
  	  Agency agency = agencyService.fetchAgencyByProfileId(current.getId());
  	  current.setLastlogin(new Timestamp(System.currentTimeMillis()));
  	  profileService.save(current);
  	  if(menu.getType().equals("00")) {
		log.info("-- Start Dernière Position : "+menu+" --Profile : "+current);
		List<LastPositionOut> locations = new ArrayList<>();
		if(current.getIsActive()) {
			  locations = carService.fetchLastPositionByAgencyIdAndDate(agency.getId(), menu.getDate());
		}
  	    log.info("-- End   Dernière Position --");
  	    return new ResponseEntity<List<LastPositionOut>>(locations, HttpStatus.OK);
  	  }else if(menu.getType().equals("01")) {
		log.info("-- Start Historique : "+menu+" --Profile : "+current);
  		List<HistoryOut> cars = carService.fetchHistoryByAgencyId(agency.getId());
    	log.info("-- End   Historique --");
    	return new ResponseEntity<List<HistoryOut>>(cars, HttpStatus.OK);
      }else if (menu.getType().equals("02")){
		log.info("-- Start Vitesse : "+menu+" --Profile : "+current);
    	List<MaxSpeedOut> locations = carService.fetchCarMaxSpeedByAgencyIdAndDate(agency.getId(),menu.getDate());
      	log.info("-- End   Vitesse --");
        return new ResponseEntity<List<MaxSpeedOut>>(locations, HttpStatus.OK);
      }else if (menu.getType().equals("03")) {
		log.info("-- Start Distance : "+menu+" --Profile : "+current);
    	List<MaxCourseOut> locations = carService.fetchCarMaxCourseByAgencyIdAndDate(agency.getId(),menu.getDate());
		log.info("-- End   Distance --");
        return new ResponseEntity<List<MaxCourseOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("04")) {
		log.info("-- Start Carburant Principal : "+menu+" --Profile : "+current);
	    List<FuelOut> locations = carService.fetchCarFuelPrincipaleByAgencyIdAndDate(agency.getId(),menu.getDate());
		log.info("-- End   Carburant Principal ");
	    return new ResponseEntity<List<FuelOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("05")) {
		log.info("-- Start Carburant Secondaire : "+menu+" --Profile : "+current);
	    List<FuelOut> locations = carService.fetchCarFuelSecondaireByAgencyId(agency.getId(),menu.getDate());
		log.info("-- End   Carburant Secondaire ");
	    return new ResponseEntity<List<FuelOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("10")) {
		  /******************************
		   *     Température Moteur     *
		   *****************************/
	    List<MaxTemperatureOut> locations = carService.fetchCarTemperatureMoByAgencyId(agency.getId(),menu.getDate());
	    log.info("--> End loadmenu --");
	    return new ResponseEntity<List<MaxTemperatureOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("11")) {
		  /******************************
		   *     Température Frigot     *
		   *****************************/
	    List<MaxTemperatureOut> locations = carService.fetchCarTemperatureFrByAgencyId(agency.getId(),menu.getDate());
	    log.info("--> End loadmenu --");
	    return new ResponseEntity<List<MaxTemperatureOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("08")) {
		log.info("-- Start Zone Une : "+menu+" --Profile : "+current);
		List<ZoneOut> locations = carService.fetchCarZoneByAgencyIdAndNumber(agency.getId(),1);
		log.info("-- End   Zone Une --");
		return new ResponseEntity<List<ZoneOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("09")) {
		log.info("-- Start Zone Deux : "+menu+" --Profile : "+current);
  	  	List<ZoneOut> locations = carService.fetchCarZoneByAgencyIdAndNumber(agency.getId(),2);
		log.info("-- End   Zone Deux --");
  	  	return new ResponseEntity<List<ZoneOut>>(locations, HttpStatus.OK);
  	  }else if (menu.getType().equals("06")) {
		log.info("-- Start Notification Consultation : "+menu+" --Profile : "+current);
		List<NotificationOut> locations = carService.fetchCarNotificationConsulByAgencyId(agency.getId(),menu.getDate());
		log.info("-- End   Notification Consultation --");
		return new ResponseEntity<List<NotificationOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("07")) {
		log.info("-- Start Notification Configuration : "+menu+" --Profile : "+current);
		List<NotificationOut> locations = carService.fetchCarNotificationConfigByAgencyId(agency.getId(),menu.getDate());
		log.info("-- End   Notification Configuration --");
		return new ResponseEntity<List<NotificationOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("12")) {
		  /******************************
		   *           Portes           *
		   *****************************/
		List<DoorOut> locations = carService.fetchCarDoorByAgencyId(agency.getId(),menu.getDate());
		log.info("--> End loadmenu --");
		return new ResponseEntity<List<DoorOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("13")) {
		  /******************************
		   *         Chauffeures        *
		   *****************************/
		List<DriverOut> locations = carService.fetchCarDriverByAgencyId(agency.getId(),menu.getDate());
		log.info("--> End loadmenu --");
		return new ResponseEntity<List<DriverOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("14")) {
		log.info("-- Start Paramètres : "+menu+" --Profile : "+current);
		List<ParametersOut> locations = carService.fetchCarParametersByAgencyId(agency.getId(),menu.getDate());
		log.info("-- End   Paramètres --");
		return new ResponseEntity<List<ParametersOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("15")) {
		log.info("-- Start Arret/Démarrage : "+menu+" --Profile : "+current);
		List<StartStopOut> locations = carService.fetchCarStartStopByAgencyId(agency.getId(),menu.getDate());
		log.info("-- End   Arret/Démarrage --");
		return new ResponseEntity<List<StartStopOut>>(locations, HttpStatus.OK);
	  }else if (menu.getType().equals("16")) {
		log.info("-- Start Mon Compte : "+menu+" --Profile : "+current);
		AccountOut account = carService.fetchAccountByAgencyId(agency.getId());
		log.info("-- End   Mon Compte --");
		return new ResponseEntity<AccountOut>(account, HttpStatus.OK);
	  }else if (menu.getType().equals("17")) {
		log.info("-- Start Contacts : "+menu+" --Profile : "+current);
		List<Contact> contact = carService.fetchContacts();
		log.info("-- End   Contacts --");
		return new ResponseEntity<List<Contact>>(contact, HttpStatus.OK);
	   }else {
  		log.info("--> Action Not Find --");
  		return new ResponseEntity(new CustomErrorType(ErrorLabel.TYPE_NOT_FOUND),HttpStatus.NOT_FOUND);
  	  }
    }

	/*** data by car access ***/
	@CrossOrigin
	@PostMapping("/loaddata")
    public ResponseEntity<?> loaddata(@RequestHeader(value="Authorization") String authorization,@RequestBody DataIn data) throws ParseException {
        String token = authorization.replaceAll("Basic", "");
  	    Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		 return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }else if(!current.getIsActive()){
		  return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_ACTIVE),HttpStatus.FORBIDDEN);
	  }
  	  current.setLastlogin(new Timestamp(System.currentTimeMillis()));
  	  profileService.save(current);
  	  if(data.getType().equals("01")) {
		log.info("-- Start Historique Détail : "+data+" --Profile : "+current);
  	    List<HistoryLocationOut> historylocations = carService.fetchHistoryCarLocationsByCarIdAndDate(data.getCarid(), data.getDate());
  	    log.info("-- End   Historique Détail --");
        return new ResponseEntity<List<HistoryLocationOut>>(historylocations, HttpStatus.OK);
  	  }else if (data.getType().equals("02")) {
		log.info("-- Start Vitesse Détail : "+data+" --Profile : "+current);
  		DetailOut detail = carService.fetchMaxSpeedByCarIdAndDate(data.getCarid(), data.getDate());
		log.info("-- End   Vitesse Détail --");
  		return new ResponseEntity<DetailOut>(detail, HttpStatus.OK);
  	  }else if (data.getType().equals("03")) {
		log.info("-- Start Distance Détail : "+data+" --Profile : "+current);
    	DetailOut detail = carService.fetchCourseByCarIdAndDate(data.getCarid(), data.getDate());
		log.info("-- End   Distance Détail --");
      	return new ResponseEntity<DetailOut>(detail, HttpStatus.OK);
      }else if (data.getType().equals("04")) {
		log.info("-- Start Carburant Principal Détail : "+data+" --Profile : "+current);
    	DetailOut detail = carService.fetchCarFuelPrincipaleByCarIdAndDate(data.getCarid(), data.getDate());
		log.info("-- End   Carburant Principal Détail --");
      	return new ResponseEntity<DetailOut>(detail, HttpStatus.OK);
      }else if (data.getType().equals("05")) {
		log.info("-- Start Carburant Secondaire Détail : "+data+" --Profile : "+current);
    	DetailOut detail = carService.fetchCarFuelSecondaireByCarIdAndDate(data.getCarid(), data.getDate());
		log.info("-- End   Carburant Secondaire Détail --");
      	return new ResponseEntity<DetailOut>(detail, HttpStatus.OK);
      }else if (data.getType().equals("06")) {
		log.info("-- Start Notifications Consultation : "+data+" --Profile : "+current);
    	List<NotifMessageOut> messages = carService.fetchCarNotificationMessageByCarIdAndDate(data.getCarid(), data.getDate());
		log.info("-- End   Notifications Consultation --");
      	return new ResponseEntity<List<NotifMessageOut>>(messages, HttpStatus.OK);
      }else if (data.getType().equals("07")) {
		log.info("-- Start Notifications Configuration : "+data+" --Profile : "+current);
    	ActiveNotif activnotif = carService.fetchCarActiveNotifByCarId(data.getCarid());
		log.info("-- End   Notifications Configuration --");
      	return new ResponseEntity<ActiveNotif>(activnotif, HttpStatus.OK);
      }else if (data.getType().equals("08")) {
		log.info("-- Start Zone Une : "+data+" --Profile : "+current);
    	Zone zone = carService.fetchCarZoneByCarIdAndNumber(data.getCarid(),1);
		log.info("-- End   Zone Une --");
      	return new ResponseEntity<Zone>(zone, HttpStatus.OK);
      }else if (data.getType().equals("09")) {
		log.info("-- Start Zone Deux : "+data+" --Profile : "+current);
  	  	Zone zone = carService.fetchCarZoneByCarIdAndNumber(data.getCarid(),2);
		log.info("-- End   Zone Deux --");
  	  	return new ResponseEntity<Zone>(zone, HttpStatus.OK);
  	  	/******************************
		 *    Température Moteur      *
		 *****************************/
      }else if (data.getType().equals("10")) {
    	DetailOut detail = carService.fetchCarTemperatureMoByCarIdAndDate(data.getCarid(), data.getDate());
      	log.info("--> End loaddata --");
      	return new ResponseEntity<DetailOut>(detail, HttpStatus.OK);
      	/******************************
		 *    Température Frigot      *
		 *****************************/
      }else if (data.getType().equals("11")) {
    	DetailOut detail = carService.fetchCarTemperatureFrByCarIdAndDate(data.getCarid(), data.getDate());
      	log.info("--> End loaddata --");
      	return new ResponseEntity<DetailOut>(detail, HttpStatus.OK);
      	/******************************
		 *            Portes          *
		 *****************************/
      }else if (data.getType().equals("12")) {
    	DetailOut detail = carService.fetchCarDoorByCarIdAndDate(data.getCarid(), data.getDate());
      	log.info("--> End loaddata --");
      	return new ResponseEntity<DetailOut>(detail, HttpStatus.OK);
      	/******************************
		 *          Chauffeures       *
		 *****************************/
      }else if (data.getType().equals("13")) {
    	DetailOut detail = carService.fetchCarDriverByCarIdAndDate(data.getCarid(), data.getDate());
      	log.info("--> End loaddata --");
      	return new ResponseEntity<DetailOut>(detail, HttpStatus.OK);
      }else if (data.getType().equals("14")) {
		log.info("-- Start Paramètres : "+data+" --Profile : "+current);
    	Parameter parameter = carService.fetchCarParametersByCarId(data.getCarid());
		log.info("-- End   Paramètres --");
      	return new ResponseEntity<Parameter>(parameter, HttpStatus.OK);
      }else {
		log.info("--> Action Not Find --");
        return new ResponseEntity(new CustomErrorType(ErrorLabel.TYPE_NOT_FOUND),HttpStatus.NOT_FOUND);
      }
    }
	
	@CrossOrigin
    @PostMapping("/updatecar")
    public ResponseEntity<?> updatecar(@RequestHeader(value="Authorization") String authorization, @RequestBody Car car) {
      log.info("--> Start updatecar "+car);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  //profileService.save(profile);
  	  log.info("--> End updatecar");
  	  return new ResponseEntity(new CustomErrorType(ErrorLabel.DATA_SAVED),HttpStatus.OK);
    }
	
	@CrossOrigin
    @PostMapping("/stopcar")
    public ResponseEntity<?> stopcar(@RequestHeader(value="Authorization") String authorization, @RequestBody DataIn data) {
      log.info("-- Start stopcar "+data);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Car car = carService.fetchCarById(data.getCarid());
  	  car.setStatus(2);
  	  carService.save(car);
  	  String content = ""+car.getImmatriculation()+" "+car.getSimnumber();
  	  senderService.sendMail("contact@kriauto.ma","contact@kriauto.ma","Stop Car",content);
  	  log.info("-- End stopcar");
  	  return new ResponseEntity(new CustomErrorType(ErrorLabel.DATA_SAVED),HttpStatus.OK);
    }
	
	@CrossOrigin
    @PostMapping("/startcar")
    public ResponseEntity<?> startcar(@RequestHeader(value="Authorization") String authorization, @RequestBody DataIn data) {
      log.info("-- Start startcar "+data);
      String token = authorization.replaceAll("Basic", "");
  	  Profile current = profileService.fetchProfileByToken(token);
  	  if(null == current){
		return new ResponseEntity(new CustomErrorType(ErrorLabel.USER_NOT_FOUND),HttpStatus.NOT_FOUND);
	  }
  	  Car car = carService.fetchCarById(data.getCarid());
	  car.setStatus(3);
	  carService.save(car);
	  String content = ""+car.getImmatriculation()+" "+car.getSimnumber()+"";
	  senderService.sendMail("contact@kriauto.ma","contact@kriauto.ma","Start Car",content);
  	  log.info("-- End startcar");
  	  return new ResponseEntity(new CustomErrorType(ErrorLabel.DATA_SAVED),HttpStatus.OK);
    }
}