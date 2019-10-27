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

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE);
	private static final SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
	
	@Bean
	public TaskScheduler taskScheduler() {
	    return new ConcurrentTaskScheduler();
	}
	
	/** controle Technique 
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "12 00 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void technicalControleNotif() throws ParseException, IOException {
		log.info("==> Start Technical Controle Notifications");
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
					   Date now = new Date();
   				       Date currentdate = sdf.parse(sdf.format(now));
   				       Date technicaldate = sdf2.parse(parameter.getTechcontrol());
					   long diffInMillies = technicaldate.getTime() - currentdate.getTime();
                       long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                       if(0<= diff && diff <=15){
                    	 String message1 = "Controle technique à faire pour la date "+parameter.getTechcontrol()+" : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
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
		log.info("==> End Technical Controle Notifications");
 	}
	
	/** vidange 
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "13 00 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void emptyingkmNotif() throws ParseException, IOException {
		log.info("==> Start Vidange Notifications");
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
		log.info("==> End Vidange Notifications");
 	}
	
	/** Assurance
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "14 00 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void insuranceEndNotif() throws ParseException, IOException {
		log.info("==> Start Assurance Notifications");
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
					   Date now = new Date();
   				       Date currentdate = sdf.parse(sdf.format(now));
   				       Date insuranceenddate = sdf2.parse(parameter.getInsuranceend());
					   long diffInMillies = insuranceenddate.getTime() - currentdate.getTime();
                       long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                       if(0<= diff && diff <=15){
                    	 String message1 = "Fin d'assurance pour la date "+parameter.getInsuranceend()+" : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
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
		log.info("==> End Assurance Notifications");
 	}
	
	/** Circulation
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "15 00 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void circulationEndNotif() throws ParseException, IOException {
		log.info("==> Start Circulations Notifications");
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
					   Date now = new Date();
   				       Date currentdate = sdf.parse(sdf.format(now));
   				       Date circulationenddate = sdf2.parse(parameter.getCirculationend());
					   long diffInMillies = circulationenddate.getTime() - currentdate.getTime();
                       long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                       if(0<= diff && diff <=15){
                    	 String message1 = "Fin de l'autorisation de circulation pour la date "+parameter.getCirculationend()+" : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
                    	 String message2 = "Fin de l'autorisation de circulation pour la date "+parameter.getCirculationend();
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
		log.info("==> End Circulations Notifications");
 	}
	
	/** Vitesse
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(fixedDelay = 3600000)
	public void vitesseMaxNotif() throws ParseException, IOException {
		log.info("==> Start Vitesse Notifications");
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
					   Date now = new Date();
   				       Calendar cal = Calendar.getInstance();
   				       // remove next line if you're always using the current time.
   				       cal.setTime(now);
   				       cal.add(Calendar.HOUR, -1);
   				       String currentdate =sdf.format(cal.getTime());
   				       List<Position> positions = carService.fetchMaxSpeedByDeviceIdAndPeriod(currentdate, c.getDeviceId());
                       if(null != positions && positions.size() > 0 && positions.get(0).getSpeed() * 1.85 >= parameter.getMaxspeed()){
                    	 String message1 = "Dépassement de vitesse à "+positions.get(0).getFixtime()+" : "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation();
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
		log.info("==> End Vitesse Notifications");
 	}
	
	/** Distance
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(cron = "23 59 00 * * *")
	//@Scheduled(fixedDelay = 60000)
	public void distanceMaxNotif() throws ParseException, IOException {
		log.info("==> Start Distance Notifications");
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		// remove next line if you're always using the current time.
		cal.setTime(now);
		String currentdate =sdf.format(cal.getTime());
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyId(agency.getId());
				for(Car c : cars) {
					Parameter parameter = parameteService.fetchParameterByCarId(c.getId());
					ActiveNotif activenotif = activeNotifService.fetchActiveNotifByCarId(c.getId());
					double course = carService.calculateDailyDistance(c.getDeviceId(),currentdate);
					if(null != activenotif && activenotif.getMaxcourse() && null != parameter && null != parameter.getMaxcourse() && course >= parameter.getMaxcourse()) {
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
		log.info("==> End Distance Notifications");
 	}

	
	/** Zone une
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(fixedDelay = 600000)
	public void zoneUneNotif() throws ParseException, IOException {
		log.info("==> Start Zone Une Notifications");
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
 						 c.setInzoneone(true);
 						 carService.save(c);
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
						     c.setInzoneone(false);
						     carService.save(c);
					  }
					}
				}
			}
		}
		log.info("==> End Zone Une Notifications");
 	}
	
	/** Zone deux
	 * @throws ParseException 
	 * @throws IOException **/
	@Scheduled(fixedDelay = 600000)
	public void zoneTwoNotif() throws ParseException, IOException {
		log.info("==> Start Zone Deux Notifications");
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
					  if(utilityService.isInZone(zone, positions.get(0).getLatitude(), positions.get(0).getLongitude()) && !c.getInzonetwo()){
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
 						 c.setInzonetwo(true);
 						 carService.save(c);
					  }
					}
					if(null != activenotif && activenotif.getZonetwoout() && null != positions && positions.size() > 0 && null != zone) {
					  if(!utilityService.isInZone(zone, positions.get(0).getLatitude(), positions.get(0).getLongitude()) && c.getInzonetwo()){
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
						     c.setInzonetwo(false);
						     carService.save(c);
					  }
					}
				}
			}
		}
		log.info("==> End Zone Deux Notifications");
 	}

	/** Stop Car
	 * @throws ParseException
	 * @throws IOException **/
	//@Scheduled(cron = "23 59 00 * * *")
	@Scheduled(fixedDelay = 60000)
	public void stopCar() throws ParseException, IOException {
		log.info("==> Start Stop Car");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if(null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyIdAndStatus(agency.getId(),2);
				for(Car c : cars) {
					List<Position> positions = carService.fetchLastPositionByDeviceId(c.getDeviceId());
					if(c.getStatus() == 2 && positions.size() > 0){
						if(positions.get(0).getSpeed() < 30){
							int status = senderService.sendSms("KriAuto.ma", c.getSimnumber(), "kauto 13579 setdigout 00");
							if(status == 0) {
								String message1 = "Arret de la voiture " + c.getMark() + " " + c.getModel() + " " + c.getImmatriculation() + " est en cours veuillez patienter 2 minutes en cas de problème veuillez nous contacter";
								String message2 = "Arret de la voiture " + c.getMark() + " " + c.getModel() + " " + c.getImmatriculation() + "";
								for (PushNotif pushnotif : pushnotifs) {
									if (null != pushnotif && null != pushnotif.getPushToken()) {
										senderService.sendPushNotification(pushnotif.getPushToken(), message1);
									}
								}
								Notification notification = new Notification();
								notification.setCarid(c.getId());
								notification.setMessage(message2);
								notificationService.save(notification);
								c.setStatus(1);
								carService.save(c);
							}else{
								String message = "l'arret de la voiture "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation()+" est impossible actuelement à cause de sa vitesse de circulation";
								for(PushNotif pushnotif : pushnotifs){
									if(null != pushnotif && null != pushnotif.getPushToken()){
										senderService.sendPushNotification(pushnotif.getPushToken(), message);
									}
								}
							}
						}else{
							String message = "l'arret de la voiture "+c.getMark()+" "+c.getModel()+" "+c.getImmatriculation()+" est impossible actuelement à cause de sa vitesse de circulation";
							for(PushNotif pushnotif : pushnotifs){
								if(null != pushnotif && null != pushnotif.getPushToken()){
									senderService.sendPushNotification(pushnotif.getPushToken(), message);
								}
							}
						}
					}
				}
			}
		}
		log.info("==> End Stop Car");
	}

	/** Start Car
	 * @throws ParseException
	 * @throws IOException **/
	//@Scheduled(cron = "23 59 00 * * *")
	@Scheduled(fixedDelay = 60000)
	public void startCar() throws ParseException, IOException {
		log.info("==> Start Start Car");
		List<Profile> profiles = profileService.fetchAllProfile();
		for(Profile p : profiles) {
			Agency agency = agencyService.fetchAgencyByProfileId(p.getId());
			List<PushNotif> pushnotifs = pushNotifService.fetchPushNotifByProfileId(p.getId());
			if (null != agency) {
				List<Car> cars = carService.fetchAllCarByAgencyIdAndStatus(agency.getId(), 3);
				for (Car c : cars) {
					if (c.getStatus() == 3) {
						int status = senderService.sendSms("KriAuto.ma", c.getSimnumber(), "kauto 13579 setdigout 11");
						if (status == 0) {
							String message1 = "Démarrage de la voiture " + c.getMark() + " " + c.getModel() + " " + c.getImmatriculation() + " est en cours veuillez patienter 2 minutes en cas de problème veuillez nous contacter";
							String message2 = "Démarrage de la voiture " + c.getMark() + " " + c.getModel() + " " + c.getImmatriculation() + "";
							for (PushNotif pushnotif : pushnotifs) {
								if (null != pushnotif && null != pushnotif.getPushToken()) {
									senderService.sendPushNotification(pushnotif.getPushToken(), message1);
								}
							}
							Notification notification = new Notification();
							notification.setCarid(c.getId());
							notification.setMessage(message2);
							notificationService.save(notification);
							c.setStatus(0);
							carService.save(c);
						} else {
							String message = "le Démarrage de la voiture " + c.getMark() + " " + c.getModel() + " " + c.getImmatriculation() + " est impossible actuelement suite à un problème de couverture réseau";
							for (PushNotif pushnotif : pushnotifs) {
								if (null != pushnotif && null != pushnotif.getPushToken()) {
									senderService.sendPushNotification(pushnotif.getPushToken(), message);
								}
							}
						}
					}
				}
			}
		}
		log.info("==> End Start Car");
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
		
	
	//@Scheduled(fixedDelay = 900000)
    public void calculateDailyDistance() throws ParseException {
       log.info("==> Start calculateDailyDistance");
       //carService.calculateDailyDistance();
       log.info("==> Finished calculateDailyDistance");
    }
	
	//@Scheduled(cron = "00 00 00 * * *")
    public void initDailyDistance() throws ParseException {
       log.info("==> Start calculateDailyDistance");
       carService.initDailyDistance();
       log.info("==> Finished calculateDailyDistance");
    }
	
	@Scheduled(cron = "01 00 00 * * *")
    //@Scheduled(fixedDelay = 900000)
    public void calculateTotalDistance() throws ParseException {
       log.info("==> Start calculateTotalDistance");
       carService.calculateTotalDistance();
       log.info("==> End calculateTotalDistance");
    }

	@Scheduled(cron = "05 00 00 * * *")
	//@Scheduled(fixedDelay = 900000)
	public void purgeDataBase() throws ParseException {
		log.info("==> Start Purge Data Base");
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.MONTH, -1);
		String currentdate =sdf.format(cal.getTime());
		List<Car> cars = carService.fetchAllCar();
		for(Car car : cars){
			List<Position> positions = carService.fetchLastPositionByDeviceId(car.getDeviceId());
			if(positions.size() > 0){
				Position position = positions.get(0);
				carService.deletePositions(currentdate,car.getDeviceId(),position.getId());
			}
		}
		log.info("==> End Purge Data Base");

	}


}
