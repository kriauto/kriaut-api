package ma.kriauto.api.batch;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import lombok.extern.slf4j.Slf4j;
import ma.kriauto.api.model.ActiveNotif;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Notification;
import ma.kriauto.api.model.Parameter;
import ma.kriauto.api.model.Position;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.PushNotif;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.service.ActiveNotifService;
import ma.kriauto.api.service.AgencyService;
import ma.kriauto.api.service.CarService;
import ma.kriauto.api.service.NotificationService;
import ma.kriauto.api.service.ParameterService;
import ma.kriauto.api.service.ProfileService;
import ma.kriauto.api.service.PushNotifService;
import ma.kriauto.api.service.SenderService;
import ma.kriauto.api.service.UtilityService;
import ma.kriauto.api.service.ZoneService;

@Configuration
@EnableScheduling
@Slf4j
public class SpringBootScheduler {
	
	@Autowired
    private ProfileService profileService;
	
	@Autowired
    private PushNotifService pushNotifService;
	
	@Autowired
    private AgencyService agencyService;
	
	@Autowired
    private CarService carService;
	
	@Autowired
    private ParameterService parameteService;
	
	@Autowired
    private ActiveNotifService activeNotifService;
	
	@Autowired
    private NotificationService notificationService;
	
	@Autowired
    private ZoneService zoneService;
	
	@Autowired
    private SenderService senderService;
	
	@Autowired
    private UtilityService utilityService;
	
	@Bean
	public TaskScheduler taskScheduler() {
	    return new ConcurrentTaskScheduler();
	}
	
	/** controle Technique 
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "13 00 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void technicalControleNotif() throws ParseException, IOException {
		log.info("==> Start technicalControleNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Parameter parameter = parameteService.fetchParameterByCarId(c.getId());
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					if(null != activenotif && activenotif.getTechcontrol() && null != parameter && null != parameter.getTechcontrol()) {
					   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
					   Date now = new Date();
   				       Date currentdate = sdf.parse(sdf.format(now));
   				       Date technicaldate = sdf.parse(parameter.getTechcontrol());
					   long diffInMillies = technicaldate.getTime() - currentdate.getTime();
                       long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                       if(0<= diff && diff <=15){
                    	 String message1 = "Controle technique à faire pour la date "+parameter.getTechcontrol()+" :"+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Controle technique à faire pour la date "+parameter.getTechcontrol();
	    				 for(PushNotif pushnotif : pushnotifs){
	    					if(null != pushnotif && null != pushnotif.getPushToken()){
	    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					}
	    				 }
	    				 Notification notification = new Notification();
 						 notification.setCarid(c.getId());
 						 notification.setMessage(message2);
 						 notificationService.save(notification);
                       }
					}
				}
			}
		}
		log.info("==> End technicalControleNotif");
 	}
	
	/** vidange 
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "13 30 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void emptyingkmNotif() throws ParseException, IOException {
		log.info("==> Start emptyingkmNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Parameter parameter = parameteService.fetchParameterByCarId(c.getId());
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					if(null != activenotif && activenotif.getEmptyingkm() && null != parameter && null != parameter.getEmptyingkm() && Math.round(c.getTotaldistance()/parameter.getEmptyingkm()) > c.getIndexemptyingkm()) {
					  
                    	 String message1 = "Vidange à faire pour : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Vidange à faire pour ";
	    				 for(PushNotif pushnotif : pushnotifs){
	    					if(null != pushnotif && null != pushnotif.getPushToken()){
	    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					}
	    				 }
	    				 Notification notification = new Notification();
 						 notification.setCarid(c.getId());
 						 notification.setMessage(message2);
 						 notificationService.save(notification);
 						 c.setIndexemptyingkm(c.getIndexemptyingkm()+1);
 						 carService.save(c);
                   }
				}
			}
		}
		log.info("==> End emptyingkmNotif");
 	}
	
	/** Assurance
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "14 00 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void insuranceEndNotif() throws ParseException, IOException {
		log.info("==> Start insuranceEndNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Parameter parameter = parameteService.fetchParameterByCarId(c.getId());
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					if(null != activenotif && activenotif.getInsuranceend() && null != parameter && null != parameter.getInsuranceend()) {
					   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
					   Date now = new Date();
   				       Date currentdate = sdf.parse(sdf.format(now));
   				       Date insuranceenddate = sdf.parse(parameter.getInsuranceend());
					   long diffInMillies = insuranceenddate.getTime() - currentdate.getTime();
                       long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                       if(0<= diff && diff <=15){
                    	 String message1 = "Fin d'assurance pour la date "+parameter.getInsuranceend()+" :"+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Fin d'assurance pour la date "+parameter.getInsuranceend();
	    				 for(PushNotif pushnotif : pushnotifs){
	    					if(null != pushnotif && null != pushnotif.getPushToken()){
	    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					}
	    				 }
	    				 Notification notification = new Notification();
 						 notification.setCarid(c.getId());
 						 notification.setMessage(message2);
 						 notificationService.save(notification);
                       }
					}
				}
			}
		}
		log.info("==> End insuranceEndNotif");
 	}
	
	/** Circulation
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "14 30 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void circulationEndNotif() throws ParseException, IOException {
		log.info("==> Start circulationEndNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Parameter parameter = parameteService.fetchParameterByCarId(c.getId());
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					if(null != activenotif && activenotif.getCirculationend() && null != parameter && null != parameter.getCirculationend()) {
					   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
					   Date now = new Date();
   				       Date currentdate = sdf.parse(sdf.format(now));
   				       Date circulationenddate = sdf.parse(parameter.getCirculationend());
					   long diffInMillies = circulationenddate.getTime() - currentdate.getTime();
                       long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                       if(0<= diff && diff <=15){
                    	 String message1 = "Fin d'assurance pour la date "+parameter.getCirculationend()+" :"+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Fin d'assurance pour la date "+parameter.getCirculationend();
	    				 for(PushNotif pushnotif : pushnotifs){
	    					if(null != pushnotif && null != pushnotif.getPushToken()){
	    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					}
	    				 }
	    				 Notification notification = new Notification();
 						 notification.setCarid(c.getId());
 						 notification.setMessage(message2);
 						 notificationService.save(notification);
                       }
					}
				}
			}
		}
		log.info("==> End circulationEndNotif");
 	}
	
	/** Vitesse
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(fixedDelay = 3600000)
	public void vitesseMaxNotif() throws ParseException, IOException {
		log.info("==> Start vitesseMaxNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Parameter parameter = parameteService.fetchParameterByCarId(c.getId());
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					if(null != activenotif && activenotif.getMaxspeed() && null != parameter && null != parameter.getMaxspeed()) {
					   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
					   Date now = new Date();
   				       Calendar cal = Calendar.getInstance();
   				       // remove next line if you're always using the current time.
   				       cal.setTime(now);
   				       cal.add(Calendar.HOUR, -1);
   				       String currentdate =sdf.format(cal.getTime());
   				       List<Position> positions = carService.fetchMaxSpeedByDeviceIdAndPeriod(currentdate, c.getDeviceId());
                       if(null != positions && positions.size() > 0 && positions.get(0).getSpeed() * 1.85 >= parameter.getMaxspeed()){
                    	 String message1 = "Dépassement de vitesse à "+positions.get(0).getFixtime()+" :"+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Dépassement de vitesse à "+positions.get(0).getFixtime();
	    				 for(PushNotif pushnotif : pushnotifs){
	    					if(null != pushnotif && null != pushnotif.getPushToken()){
	    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					}
	    				 }
	    				 Notification notification = new Notification();
 						 notification.setCarid(c.getId());
 						 notification.setMessage(message2);
 						 notificationService.save(notification);
                       }
					}
				}
			}
		}
		log.info("==> End vitesseMaxNotif");
 	}
	
	/** Distance
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "23 59 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void distanceMaxNotif() throws ParseException, IOException {
		log.info("==> Start distanceMaxNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Parameter parameter = parameteService.fetchParameterByCarId(c.getId());
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					if(null != activenotif && activenotif.getMaxcourse() && null != parameter && null != parameter.getMaxcourse() && c.getDailydistance() >= parameter.getMaxcourse()) {
                    	 String message1 = "Dépassement de distance journalière : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Dépassement de distance journalière ";
	    				 for(PushNotif pushnotif : pushnotifs){
	    					if(null != pushnotif && null != pushnotif.getPushToken()){
	    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					}
	    				 }
	    				 Notification notification = new Notification();
 						 notification.setCarid(c.getId());
 						 notification.setMessage(message2);
 						 notificationService.save(notification);
					}
				}
			}
		}
		log.info("==> End distanceMaxNotif");
 	}
	
	/** Zone une
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(fixedDelay = 60000)
	public void zoneUneNotif() throws ParseException, IOException {
		log.info("==> Start zoneUneNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Zone zone = zoneService.fetchZoneByCarIdAndRank(c.getId(), 1);
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					List<Position> positions = carService.fetchLastPositionByDeviceId(c.getDeviceId());
					if(null != activenotif && activenotif.getZoneonein() && null != positions && positions.size() > 0 && null != zone) {
					  if(utilityService.isInZone(zone, positions.get(0).getLatitude(), positions.get(0).getLongitude()) && !c.getInzoneone()){
                    	 String message1 = "Entrée en zone "+zone.getLabel()+" : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Entrée en zone "+zone.getLabel();
	    				 for(PushNotif pushnotif : pushnotifs){
	    					if(null != pushnotif && null != pushnotif.getPushToken()){
	    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					}
	    				 }
	    				 Notification notification = new Notification();
 						 notification.setCarid(c.getId());
 						 notification.setMessage(message2);
 						 notificationService.save(notification);
					  }
					}
					if(null != activenotif && activenotif.getZoneoneout() && null != positions && positions.size() >0 && null != zone) {
					  if(!utilityService.isInZone(zone, positions.get(0).getLatitude(), positions.get(0).getLongitude()) && c.getInzoneone()){
	                    	 String message1 = "Sortie de la zone "+zone.getLabel()+" : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
	                    	 String message2 = "Sortie de la zone  "+zone.getLabel();
		    				 for(PushNotif pushnotif : pushnotifs){
		    					if(null != pushnotif && null != pushnotif.getPushToken()){
		    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
		    					}
		    				 }
		    				 Notification notification = new Notification();
	 						 notification.setCarid(c.getId());
	 						 notification.setMessage(message2);
	 						 notificationService.save(notification);
						  }
					}
				}
			}
		}
		log.info("==> End zoneUneNotif");
 	}
	
	/** Zone deux
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(fixedDelay = 60000)
	public void zoneTwoNotif() throws ParseException, IOException {
		log.info("==> Start zoneTwoNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Zone zone = zoneService.fetchZoneByCarIdAndRank(c.getId(), 2);
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					List<Position> positions = carService.fetchLastPositionByDeviceId(c.getDeviceId());
					if(null != activenotif && activenotif.getZonetwoin() && null != positions && positions.size() > 0 && null != zone) {
					  if(utilityService.isInZone(zone, positions.get(0).getLatitude(), positions.get(0).getLongitude()) && !c.getInzoneone()){
                    	 String message1 = "Entrée en zone "+zone.getLabel()+" : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Entrée en zone "+zone.getLabel();
	    				 for(PushNotif pushnotif : pushnotifs){
	    					if(null != pushnotif && null != pushnotif.getPushToken()){
	    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					}
	    				 }
	    				 Notification notification = new Notification();
 						 notification.setCarid(c.getId());
 						 notification.setMessage(message2);
 						 notificationService.save(notification);
					  }
					}
					if(null != activenotif && activenotif.getZonetwoin() && null != positions && positions.size() > 0 && null != zone) {
					  if(!utilityService.isInZone(zone, positions.get(0).getLatitude(), positions.get(0).getLongitude()) && c.getInzoneone()){
	                    	 String message1 = "Sortie de la zone "+zone.getLabel()+" : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
	                    	 String message2 = "Sortie de la zone  "+zone.getLabel();
		    				 for(PushNotif pushnotif : pushnotifs){
		    					if(null != pushnotif && null != pushnotif.getPushToken()){
		    					    senderService.sendPushNotification(pushnotif.getPushToken(), message1);
		    					}
		    				 }
		    				 Notification notification = new Notification();
	 						 notification.setCarid(c.getId());
	 						 notification.setMessage(message2);
	 						 notificationService.save(notification);
						  }
					}
				}
			}
		}
		log.info("==> End zoneTwoNotif");
 	}
	
	/** Moteur allumé/coupé
	 * @throws ParseException 
	 * @throws IOException **/
	//@Scheduled(fixedDelay = 600000)
	public void engineStartStopNotif() throws ParseException, IOException {
		log.info("==> Start engineStartStopNotif");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Parameter parameter = parameteService.fetchParameterByCarId(c.getId());
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					if(null != activenotif && activenotif.getEnginestop()) {
					   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
					   Date now = new Date();
   				       Calendar cal = Calendar.getInstance();
   				       // remove next line if you're always using the current time.
   				       cal.setTime(now);
   				       cal.add(Calendar.MINUTE, -10);
   				       String currentdate =sdf.format(cal.getTime());
   				       List<Position> positions = carService.fetchCarIgnition(currentdate, c.getDeviceId());
                       if(null != positions && positions.size() > 0){
                    	   boolean ignition = positions.get(0).getAttributes().contains("\"ignition\":true") ? true : false ;
                    	 for(Position po : positions) {
                    	   if(po.getAttributes().contains("\"ignition\":false") && !ignition) {
                    		  ignition = true;
                    	      String message1 = "Moteur coupé à "+positions.get(0).getFixtime()+" :"+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	      String message2 = "Moteur coupé à "+positions.get(0).getFixtime();
	    				     for(PushNotif pushnotif : pushnotifs){
	    					   if(null != pushnotif && null != pushnotif.getPushToken()){
	    					     senderService.sendPushNotification(pushnotif.getPushToken(), message1);
	    					   }
	    				     }
	    				     Notification notification = new Notification();
 						     notification.setCarid(c.getId());
 						     notification.setMessage(message2);
 						     notificationService.save(notification);
                    	   }
                    	   if(po.getAttributes().contains("\"ignition\":true") && ignition){
                    		  ignition = false;
                    		  String message1 = "Moteur allumé à "+positions.get(0).getFixtime()+" :"+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                     	      String message2 = "Moteur allumé à "+positions.get(0).getFixtime();
 	    				     for(PushNotif pushnotif : pushnotifs){
 	    					   if(null != pushnotif && null != pushnotif.getPushToken()){
 	    					     senderService.sendPushNotification(pushnotif.getPushToken(), message1);
 	    					   }
 	    				     }
 	    				     Notification notification = new Notification();
  						     notification.setCarid(c.getId());
  						     notification.setMessage(message2);
  						     notificationService.save(notification);  
                    	   }
                        }
                      }
					}
				}
			}
		}
		log.info("==> End engineStartStopNotif");
 	}
		
	
	@Scheduled(fixedDelay = 900000)
    public void calculateDailyDistance() throws ParseException {
       log.info("==> Start calculateDailyDistance");
       carService.calculateDailyDistance();
       log.info("==> Finished calculateDailyDistance");
    }
	
	@Scheduled(cron = "00 00 00 * * *")
    public void initDailyDistance() throws ParseException {
       log.info("==> Start calculateDailyDistance");
       carService.initDailyDistance();
       log.info("==> Finished calculateDailyDistance");
    }
	
	//@Scheduled(cron = "00 00 05 * * *")
    @Scheduled(fixedDelay = 900000)
    public void calculateTotalDistance() throws ParseException {
       log.info("==> Start calculateDailyDistance");
       carService.calculateTotalDistance();
       log.info("==> Finished calculateDailyDistance");
    }

}
