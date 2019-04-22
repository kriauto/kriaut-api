package ma.kriauto.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.kriauto.api.model.ActiveNotif;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Contact;
import ma.kriauto.api.model.Notification;
import ma.kriauto.api.model.Parameter;
import ma.kriauto.api.model.Payment;
import ma.kriauto.api.model.Position;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.repository.ActiveNotifRepository;
import ma.kriauto.api.repository.AgencyRepository;
import ma.kriauto.api.repository.CarRepository;
import ma.kriauto.api.repository.ContactRepository;
import ma.kriauto.api.repository.NotificationRepository;
import ma.kriauto.api.repository.ParameterRepository;
import ma.kriauto.api.repository.PaymentRepository;
import ma.kriauto.api.repository.PositionRepository;
import ma.kriauto.api.repository.ProfileRepository;
import ma.kriauto.api.repository.ZoneRepository;
import ma.kriauto.api.response.AccountOut;
import ma.kriauto.api.response.HistoryOut;
import ma.kriauto.api.response.ItemOut;
import ma.kriauto.api.response.DetailOut;
import ma.kriauto.api.response.DoorOut;
import ma.kriauto.api.response.DriverOut;
import ma.kriauto.api.response.FuelOut;
import ma.kriauto.api.response.HistoryLocationOut;
import ma.kriauto.api.response.LastPositionOut;
import ma.kriauto.api.response.Location;
import ma.kriauto.api.response.MaxCourseOut;
import ma.kriauto.api.response.MaxSpeedOut;
import ma.kriauto.api.response.MaxTemperatureOut;
import ma.kriauto.api.response.NotifMessageOut;
import ma.kriauto.api.response.NotificationOut;
import ma.kriauto.api.response.ParametersOut;
import ma.kriauto.api.response.StartStopOut;
import ma.kriauto.api.response.ZoneOut;

@Service
public class CarServiceImpl implements CarService {
	
	@Autowired
    private CarRepository carRepository;
	
	@Autowired
    private PositionRepository positionRepository;
	
	@Autowired
    private AgencyRepository agencyRepository;
	
	@Autowired
    private ZoneRepository zoneRepository;
	
	@Autowired
    private NotificationRepository notificationRepository;
	
	@Autowired
    private ActiveNotifRepository activenotifRepository;
	
	@Autowired
    private ParameterRepository parameterRepository;
	
	@Autowired
    private ProfileRepository profileRepository;
	
	@Autowired
    private PaymentRepository paymentRepository;
	
	@Autowired
    private ContactRepository contactRepository;
	
	@Autowired
    private UtilityService utilityService;

	/*** menu acces ***/
	@Override
	public List<LastPositionOut> fetchLastPositionByAgencyIdAndDate(Long id, String date) {
		List<LastPositionOut> lsatpositions = new ArrayList<LastPositionOut>();
		Agency agency = agencyRepository.fetchAgencyByProfileId(id);
		List<Car> cars = carRepository.fetchAllCarByAgencyId(agency.getId());
		for(int i=0; i<cars.size() ;i++){
			Car car = cars.get(i);
			List<Position> positions = positionRepository.fetchLastPositionByDeviceIdAndDate(car.getDeviceId(), date);
			Position position = positions.get(0);
			LastPositionOut lastposition = new LastPositionOut();		
			lastposition.setMark(car.getMark());
			lastposition.setModel(car.getModel());
			lastposition.setImmatriculation(car.getImmatriculation());
			lastposition.setHtmlColor(car.getHtmlColor());
			lastposition.setSpeed(position.getSpeed());
			lastposition.setDate(utilityService.getYyyyMmDdFromFixTime(position.getFixtime()));
			lastposition.setHour(utilityService.getHhMmSsFromFixTime(position.getFixtime()));		
			lastposition.setMarkertype(position.getSpeed() > 0 ? 0 : 1);
			lastposition.setLatitude(position.getLatitude());
			lastposition.setLongitude(position.getLongitude());
			lsatpositions.add(lastposition);
		}
		return lsatpositions;
	}
	
	@Override
	public List<HistoryOut> fetchHistoryByAgencyId(Long id) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<HistoryOut> historys = new ArrayList<HistoryOut>();
		for(int i=0; i<cars.size(); i++) {
			HistoryOut hystory = new HistoryOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position position = lasts.get(0);
			hystory.setCarid(car.getId());
			hystory.setMark(car.getMark());
			hystory.setModel(car.getModel());
			hystory.setImmatriculation(car.getImmatriculation());
			hystory.setHtmlColor(car.getHtmlColor());
			hystory.setIsrolling(position.getSpeed() > 0 ? 0 : 1);
			hystory.setLatitude(position.getLatitude());
			hystory.setLongitude(position.getLongitude());
			historys.add(hystory);
		}
		return historys;
	}
	
	@Override
	public List<ZoneOut> fetchCarZoneByAgencyIdAndNumber(Long id, Integer number) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<ZoneOut> locations = new ArrayList<ZoneOut>();
		for(int i=0; i<cars.size(); i++) {
			ZoneOut location = new ZoneOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position position = lasts.get(0);
			Zone zone = zoneRepository.fetchZoneByCarIdAndRank(id, number);
			location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(position.getSpeed() > 0 ? 0 : 1);
			location.setInzone(true);
			locations.add(location);
		}
		return locations;
	}

	@Override
	public List<HistoryLocationOut> fetchHistoryCarLocationsByCarIdAndDate(Long id, String date) {
		List<HistoryLocationOut> historylocations = new ArrayList<HistoryLocationOut>();
		Car car = carRepository.fetchCarById(id);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size() ;i++){
			Position position = positions.get(i);
			HistoryLocationOut historylocation = new HistoryLocationOut();
			historylocation.setSpeed(position.getSpeed());
			historylocation.setHour(utilityService.getHhMmSsFromFixTime(position.getFixtime()));
			historylocation.setMarkertype(0);
			historylocation.setMarkerdisplay(0);
			historylocation.setLatitude(position.getLatitude());
			historylocation.setLongitude(position.getLongitude());
			historylocations.add(historylocation);
		}
		return historylocations;
	}
	
	@Override
	public List<MaxSpeedOut> fetchCarMaxSpeedByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<MaxSpeedOut> locations = new ArrayList<MaxSpeedOut>();
		for(int i=0; i<cars.size(); i++) {
			MaxSpeedOut location = new MaxSpeedOut();
			Car car = cars.get(i);
			List<Position> maxspeeds = positionRepository.fetchMaxSpeedDeviceIdAndDate(date, car.getDeviceId());
			Position maxspeed = maxspeeds.get(0);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setSpeed(maxspeed.getSpeed());
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<MaxCourseOut> fetchCarMaxCourseByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<MaxCourseOut> locations = new ArrayList<MaxCourseOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			MaxCourseOut location = new MaxCourseOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setTotalcourse(course);
			location.setDailycourse(course/10);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<FuelOut> fetchCarFuelPrincipaleByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<FuelOut> locations = new ArrayList<FuelOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			FuelOut location = new FuelOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setCurrentlevel(12.0);
			location.setDailyconsumption(15.6);
			locations.add(location);
		}
		return locations;
	}

	@Override
	public List<FuelOut> fetchCarFuelSecondaireByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<FuelOut> locations = new ArrayList<FuelOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			FuelOut location = new FuelOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setCurrentlevel(12.0);
			location.setDailyconsumption(15.6);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<MaxTemperatureOut> fetchCarTemperatureMoByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<MaxTemperatureOut> locations = new ArrayList<MaxTemperatureOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			MaxTemperatureOut location = new MaxTemperatureOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setTemperature(12.0);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<MaxTemperatureOut> fetchCarTemperatureFrByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<MaxTemperatureOut> locations = new ArrayList<MaxTemperatureOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			MaxTemperatureOut location = new MaxTemperatureOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setTemperature(15.0);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<NotificationOut> fetchCarNotificationConsulByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<NotificationOut> locations = new ArrayList<NotificationOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			NotificationOut location = new NotificationOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setNumber(15);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<NotificationOut> fetchCarNotificationConfigByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<NotificationOut> locations = new ArrayList<NotificationOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			NotificationOut location = new NotificationOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setNumber(10);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<DoorOut> fetchCarDoorByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<DoorOut> locations = new ArrayList<DoorOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			DoorOut location = new DoorOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setOpeningnumber(14);
			location.setClosingnumber(10);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<DriverOut> fetchCarDriverByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<DriverOut> locations = new ArrayList<DriverOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			DriverOut location = new DriverOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setDrivingduration(12.5);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<ParametersOut> fetchCarParametersByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<ParametersOut> locations = new ArrayList<ParametersOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			ParametersOut location = new ParametersOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setParametersnumber(14);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<StartStopOut> fetchCarStartStopByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<StartStopOut> locations = new ArrayList<StartStopOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			StartStopOut location = new StartStopOut();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
            location.setCarid(car.getId());
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setStatus(0);
			location.setAction(1);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public DetailOut fetchMaxSpeedByCarIdAndDate(Long carid, String date) {
		List<ItemOut> byday = new ArrayList<ItemOut>();
		List<ItemOut> byhour = new ArrayList<ItemOut>();
		DetailOut detail = new DetailOut();
		String unit = " km/h";
		double valueday = 0.0;ItemOut itemday = new ItemOut("Total","0.0"+unit);byday.add(itemday);
		double value00 = 0.0;double value01 = 0.0;double value02 = 0.0;double value03 = 0.0;
		ItemOut item00 = new ItemOut("00h00min-00h59min","0.0"+unit);ItemOut item01 = new ItemOut("01h00min-01h59min","0.0"+unit);ItemOut item02 = new ItemOut("02h00min-02h59min","0.0"+unit);ItemOut item03 = new ItemOut("03h00min-03h59min","0.0"+unit);
		byhour.add(item00);byhour.add(item01);byhour.add(item02);byhour.add(item03);
		double value04 = 0.0;double value05 = 0.0;double value06 = 0.0;double value07 = 0.0;
		ItemOut item04 = new ItemOut("04h00min-04h59min","0.0"+unit);ItemOut item05 = new ItemOut("05h00min-05h59min","0.0"+unit);ItemOut item06 = new ItemOut("06h00min-06h59min","0.0"+unit);ItemOut item07 = new ItemOut("07h00min-07h59min","0.0"+unit);
		byhour.add(item04);byhour.add(item05);byhour.add(item06);byhour.add(item07);
		double value08 = 0.0;double value09 = 0.0;double value10 = 0.0;double value11 = 0.0;
		ItemOut item08 = new ItemOut("08h00min-08h59min","0.0"+unit);ItemOut item09 = new ItemOut("09h00min-09h59min","0.0"+unit);ItemOut item10 = new ItemOut("10h00min-10h59min","0.0"+unit);ItemOut item11 = new ItemOut("11h00min-11h59min","0.0"+unit);
		byhour.add(item08);byhour.add(item09);byhour.add(item10);byhour.add(item11);
		double value12 = 0.0;double value13 = 0.0;double value14 = 0.0;double value15 = 0.0;
		ItemOut item12 = new ItemOut("12h00min-12h59min","0.0"+unit);ItemOut item13 = new ItemOut("13h00min-13h59min","0.0"+unit);ItemOut item14 = new ItemOut("14h00min-14h59min","0.0"+unit);ItemOut item15 = new ItemOut("15h00min-15h59min","0.0"+unit);
		byhour.add(item12);byhour.add(item13);byhour.add(item14);byhour.add(item15);
		double value16 = 0.0;double value17 = 0.0;double value18 = 0.0;double value19 = 0.0;
		ItemOut item16 = new ItemOut("16h00min-16h59min","0.0"+unit);ItemOut item17 = new ItemOut("17h00min-17h59min","0.0"+unit);ItemOut item18 = new ItemOut("18h00min-18h59min","0.0"+unit);ItemOut item19 = new ItemOut("19h00min-19h59min","0.0"+unit);
		byhour.add(item16);byhour.add(item17);byhour.add(item18);byhour.add(item19);
		double value20 = 0.0;double value21 = 0.0;double value22 = 0.0;double value23 = 0.0;
		ItemOut item20 = new ItemOut("20h00min-20h59min","0.0"+unit);ItemOut item21 = new ItemOut("21h00min-21h59min","0.0"+unit);ItemOut item22 = new ItemOut("22h00min-22h59min","0.0"+unit);ItemOut item23 = new ItemOut("23h00min-23h59min","0.0"+unit);
		byhour.add(item20);byhour.add(item21);byhour.add(item22);byhour.add(item23);
		Car car = carRepository.fetchCarById(carid);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size(); i++) {
			Position position = positions.get(i);
			if(position.getSpeed() > valueday) {
				valueday = position.getSpeed();
				itemday.setValue(String.valueOf(valueday)+unit);
			}
			String hour = utilityService.getHhFromFixTime(position.getFixtime());
			if(hour.equals("00") && position.getSpeed() >= value00) {
				value00 = position.getSpeed();
				item00.setValue(String.valueOf(value00)+unit);
			}
			if(hour.equals("01") && position.getSpeed() >= value01) {
				value01 = position.getSpeed();
				item01.setValue(String.valueOf(value01)+unit);
			}
			if(hour.equals("02") && position.getSpeed() >= value02) {
				value02 = position.getSpeed();
				item02.setValue(String.valueOf(value02)+unit);
			}
			if(hour.equals("03") && position.getSpeed() >= value03) {
				value03 = position.getSpeed();
				item03.setValue(String.valueOf(value03)+unit);
			}
			if(hour.equals("04") && position.getSpeed() >= value04) {
				value04 = position.getSpeed();
				item04.setValue(String.valueOf(value04)+unit);
			}
			if(hour.equals("05") && position.getSpeed() >= value05) {
				value05 = position.getSpeed();
				item05.setValue(String.valueOf(value05)+unit);
			}
			if(hour.equals("06") && position.getSpeed() >= value06) {
				value06 = position.getSpeed();
				item06.setValue(String.valueOf(value06)+unit);
			}
			if(hour.equals("07") && position.getSpeed() >= value07) {
				value07 = position.getSpeed();
				item07.setValue(String.valueOf(value07)+unit);
			}
			if(hour.equals("08") && position.getSpeed() >= value08) {
				value08 = position.getSpeed();
				item08.setValue(String.valueOf(value08)+unit);
			}
			if(hour.equals("09") && position.getSpeed() >= value09) {
				value09 = position.getSpeed();
				item09.setValue(String.valueOf(value09)+unit);
			}
			if(hour.equals("10") && position.getSpeed() >= value10) {
				value10 = position.getSpeed();
				item10.setValue(String.valueOf(value10)+unit);
			}
			if(hour.equals("11") && position.getSpeed() >= value11) {
				value11 = position.getSpeed();
				item11.setValue(String.valueOf(value11)+unit);
			}
			if(hour.equals("12") && position.getSpeed() >= value12) {
				value12 = position.getSpeed();
				item12.setValue(String.valueOf(value12)+unit);
			}
			if(hour.equals("13") && position.getSpeed() >= value13) {
				value13 = position.getSpeed();
				item13.setValue(String.valueOf(value13)+unit);
			}
			if(hour.equals("14") && position.getSpeed() >= value14) {
				value14 = position.getSpeed();
				item14.setValue(String.valueOf(value14)+unit);
			}
			if(hour.equals("15") && position.getSpeed() >= value15) {
				value15 = position.getSpeed();
				item15.setValue(String.valueOf(value15)+unit);
			}
			if(hour.equals("16") && position.getSpeed() >= value16) {
				value16 = position.getSpeed();
				item16.setValue(String.valueOf(value16)+unit);
			}
			if(hour.equals("17") && position.getSpeed() >= value17) {
				value17 = position.getSpeed();
				item17.setValue(String.valueOf(value17)+unit);
			}
			if(hour.equals("18") && position.getSpeed() >= value18) {
				value18 = position.getSpeed();
				item18.setValue(String.valueOf(value18)+unit);
			}
			if(hour.equals("19") && position.getSpeed() >= value19) {
				value19 = position.getSpeed();
				item19.setValue(String.valueOf(value19)+unit);
			}
			if(hour.equals("20") && position.getSpeed() >= value20) {
				value20 = position.getSpeed();
				item20.setValue(String.valueOf(value20)+unit);
			}
			if(hour.equals("21") && position.getSpeed() >= value21) {
				value21 = position.getSpeed();
				item21.setValue(String.valueOf(value21)+unit);
			}
			if(hour.equals("22") && position.getSpeed() >= value22) {
				value22 = position.getSpeed();
				item22.setValue(String.valueOf(value22)+unit);
			}
			if(hour.equals("23") && position.getSpeed() >= value23) {
				value23 = position.getSpeed();
				item23.setValue(String.valueOf(value23)+unit);
			}
		}
		detail.setByday(byday);
		detail.setByhour(byhour);
		return detail;
	}
	
	@Override
	public DetailOut fetchCourseByCarIdAndDate(Long carid, String date) {
		List<ItemOut> byday = new ArrayList<ItemOut>();
		List<ItemOut> byhour = new ArrayList<ItemOut>();
		DetailOut detail = new DetailOut();
		String unit = " km";
		double valueday = 0.0;ItemOut itemday = new ItemOut("Total","0.0"+unit);byday.add(itemday);
		double value00 = 0.0;double value01 = 0.0;double value02 = 0.0;double value03 = 0.0;
		ItemOut item00 = new ItemOut("00h00min-00h59min","0.0"+unit);ItemOut item01 = new ItemOut("01h00min-01h59min","0.0"+unit);ItemOut item02 = new ItemOut("02h00min-02h59min","0.0"+unit);ItemOut item03 = new ItemOut("03h00min-03h59min","0.0"+unit);
		byhour.add(item00);byhour.add(item01);byhour.add(item02);byhour.add(item03);
		double value04 = 0.0;double value05 = 0.0;double value06 = 0.0;double value07 = 0.0;
		ItemOut item04 = new ItemOut("04h00min-04h59min","0.0"+unit);ItemOut item05 = new ItemOut("05h00min-05h59min","0.0"+unit);ItemOut item06 = new ItemOut("06h00min-06h59min","0.0"+unit);ItemOut item07 = new ItemOut("07h00min-07h59min","0.0"+unit);
		byhour.add(item04);byhour.add(item05);byhour.add(item06);byhour.add(item07);
		double value08 = 0.0;double value09 = 0.0;double value10 = 0.0;double value11 = 0.0;
		ItemOut item08 = new ItemOut("08h00min-08h59min","0.0"+unit);ItemOut item09 = new ItemOut("09h00min-09h59min","0.0"+unit);ItemOut item10 = new ItemOut("10h00min-10h59min","0.0"+unit);ItemOut item11 = new ItemOut("11h00min-11h59min","0.0"+unit);
		byhour.add(item08);byhour.add(item09);byhour.add(item10);byhour.add(item11);
		double value12 = 0.0;double value13 = 0.0;double value14 = 0.0;double value15 = 0.0;
		ItemOut item12 = new ItemOut("12h00min-12h59min","0.0"+unit);ItemOut item13 = new ItemOut("13h00min-13h59min","0.0"+unit);ItemOut item14 = new ItemOut("14h00min-14h59min","0.0"+unit);ItemOut item15 = new ItemOut("15h00min-15h59min","0.0"+unit);
		byhour.add(item12);byhour.add(item13);byhour.add(item14);byhour.add(item15);
		double value16 = 0.0;double value17 = 0.0;double value18 = 0.0;double value19 = 0.0;
		ItemOut item16 = new ItemOut("16h00min-16h59min","0.0"+unit);ItemOut item17 = new ItemOut("17h00min-17h59min","0.0"+unit);ItemOut item18 = new ItemOut("18h00min-18h59min","0.0"+unit);ItemOut item19 = new ItemOut("19h00min-19h59min","0.0"+unit);
		byhour.add(item16);byhour.add(item17);byhour.add(item18);byhour.add(item19);
		double value20 = 0.0;double value21 = 0.0;double value22 = 0.0;double value23 = 0.0;
		ItemOut item20 = new ItemOut("20h00min-20h59min","0.0"+unit);ItemOut item21 = new ItemOut("21h00min-21h59min","0.0"+unit);ItemOut item22 = new ItemOut("22h00min-22h59min","0.0"+unit);ItemOut item23 = new ItemOut("23h00min-23h59min","0.0"+unit);
		byhour.add(item20);byhour.add(item21);byhour.add(item22);byhour.add(item23);
		Car car = carRepository.fetchCarById(carid);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size(); i++) {
			Position position = positions.get(i);
		    valueday = valueday+position.getCourse();
			itemday.setValue(String.valueOf(valueday)+unit);

			String hour = utilityService.getHhFromFixTime(position.getFixtime());
			if(hour.equals("00")) {
				value00 = value00+position.getCourse()*1.85;
				item00.setValue(String.valueOf(value00)+unit);
			}
			if(hour.equals("01")) {
				value01 = +value01+position.getCourse();
				item01.setValue(String.valueOf(value01)+unit);
			}
			if(hour.equals("02")) {
				value02 = value02+position.getCourse();
				item02.setValue(String.valueOf(value02)+unit);
			}
			if(hour.equals("03")) {
				value03 = value03+position.getCourse();
				item03.setValue(String.valueOf(value03)+unit);
			}
			if(hour.equals("04")) {
				value04 = value04+position.getCourse();
				item04.setValue(String.valueOf(value04)+unit);
			}
			if(hour.equals("05")) {
				value05 = value05+position.getCourse();
				item05.setValue(String.valueOf(value05)+unit);
			}
			if(hour.equals("06")) {
				value06 = value06+position.getCourse();
				item06.setValue(String.valueOf(value06)+unit);
			}
			if(hour.equals("07")) {
				value07 = value07+position.getCourse();
				item07.setValue(String.valueOf(value07)+unit);
			}
			if(hour.equals("08")) {
				value08 = value08+position.getCourse();
				item08.setValue(String.valueOf(value08)+unit);
			}
			if(hour.equals("09")) {
				value09 = value09+position.getCourse();
				item09.setValue(String.valueOf(value09)+unit);
			}
			if(hour.equals("10")) {
				value10 = value10+position.getCourse();
				item10.setValue(String.valueOf(value10)+unit);
			}
			if(hour.equals("11")) {
				value11 = value11+position.getCourse();
				item11.setValue(String.valueOf(value11)+unit);
			}
			if(hour.equals("12")) {
				value12 = value12+position.getCourse();
				item12.setValue(String.valueOf(value12)+unit);
			}
			if(hour.equals("13")) {
				value13 = value13+position.getCourse();
				item13.setValue(String.valueOf(value13)+unit);
			}
			if(hour.equals("14")) {
				value14 = value14+position.getCourse();
				item14.setValue(String.valueOf(value14)+unit);
			}
			if(hour.equals("15")) {
				value15 = value15+position.getCourse();
				item15.setValue(String.valueOf(value15)+unit);
			}
			if(hour.equals("16")) {
				value16 = value16+position.getCourse();
				item16.setValue(String.valueOf(value16)+unit);
			}
			if(hour.equals("17")) {
				value17 = value17+position.getSpeed();
				item17.setValue(String.valueOf(value17)+unit);
			}
			if(hour.equals("18")) {
				value18 = value18+position.getCourse();
				item18.setValue(String.valueOf(value18)+unit);
			}
			if(hour.equals("19")) {
				value19 = value19+position.getCourse();
				item19.setValue(String.valueOf(value19)+unit);
			}
			if(hour.equals("20")) {
				value20 = value20+position.getCourse();
				item20.setValue(String.valueOf(value20)+unit);
			}
			if(hour.equals("21")) {
				value21 = value21+position.getCourse();
				item21.setValue(String.valueOf(value21)+unit);
			}
			if(hour.equals("22")) {
				value22 = value22+position.getCourse();
				item22.setValue(String.valueOf(value22)+unit);
			}
			if(hour.equals("23")) {
				value23 = value23+position.getCourse();
				item23.setValue(String.valueOf(value23)+unit);
			}
		}
		detail.setByday(byday);
		detail.setByhour(byhour);
		return detail;
	}
	
	/*** data by car access ***/
	@Override
	public List<Car> fetchAllCarByAgencyId(Long id) {
		return carRepository.fetchAllCarByAgencyId(id);
	}

	@Override
	public Car fetchCarById(Long id) {
		return carRepository.fetchCarById(id);
	}

	@Override
	public DetailOut fetchCarFuelPrincipaleByCarIdAndDate(Long carid, String date) {
		List<ItemOut> byday = new ArrayList<ItemOut>();
		List<ItemOut> byhour = new ArrayList<ItemOut>();
		DetailOut detail = new DetailOut();
		String unit = " L";
		double valueday = 0.0;ItemOut itemday = new ItemOut("Total","0.0"+unit);byday.add(itemday);
		double value00 = 0.0;double value01 = 0.0;double value02 = 0.0;double value03 = 0.0;
		ItemOut item00 = new ItemOut("00h00min-00h59min","0.0"+unit);ItemOut item01 = new ItemOut("01h00min-01h59min","0.0"+unit);ItemOut item02 = new ItemOut("02h00min-02h59min","0.0"+unit);ItemOut item03 = new ItemOut("03h00min-03h59min","0.0"+unit);
		byhour.add(item00);byhour.add(item01);byhour.add(item02);byhour.add(item03);
		double value04 = 0.0;double value05 = 0.0;double value06 = 0.0;double value07 = 0.0;
		ItemOut item04 = new ItemOut("04h00min-04h59min","0.0"+unit);ItemOut item05 = new ItemOut("05h00min-05h59min","0.0"+unit);ItemOut item06 = new ItemOut("06h00min-06h59min","0.0"+unit);ItemOut item07 = new ItemOut("07h00min-07h59min","0.0"+unit);
		byhour.add(item04);byhour.add(item05);byhour.add(item06);byhour.add(item07);
		double value08 = 0.0;double value09 = 0.0;double value10 = 0.0;double value11 = 0.0;
		ItemOut item08 = new ItemOut("08h00min-08h59min","0.0"+unit);ItemOut item09 = new ItemOut("09h00min-09h59min","0.0"+unit);ItemOut item10 = new ItemOut("10h00min-10h59min","0.0"+unit);ItemOut item11 = new ItemOut("11h00min-11h59min","0.0"+unit);
		byhour.add(item08);byhour.add(item09);byhour.add(item10);byhour.add(item11);
		double value12 = 0.0;double value13 = 0.0;double value14 = 0.0;double value15 = 0.0;
		ItemOut item12 = new ItemOut("12h00min-12h59min","0.0"+unit);ItemOut item13 = new ItemOut("13h00min-13h59min","0.0"+unit);ItemOut item14 = new ItemOut("14h00min-14h59min","0.0"+unit);ItemOut item15 = new ItemOut("15h00min-15h59min","0.0"+unit);
		byhour.add(item12);byhour.add(item13);byhour.add(item14);byhour.add(item15);
		double value16 = 0.0;double value17 = 0.0;double value18 = 0.0;double value19 = 0.0;
		ItemOut item16 = new ItemOut("16h00min-16h59min","0.0"+unit);ItemOut item17 = new ItemOut("17h00min-17h59min","0.0"+unit);ItemOut item18 = new ItemOut("18h00min-18h59min","0.0"+unit);ItemOut item19 = new ItemOut("19h00min-19h59min","0.0"+unit);
		byhour.add(item16);byhour.add(item17);byhour.add(item18);byhour.add(item19);
		double value20 = 0.0;double value21 = 0.0;double value22 = 0.0;double value23 = 0.0;
		ItemOut item20 = new ItemOut("20h00min-20h59min","0.0"+unit);ItemOut item21 = new ItemOut("21h00min-21h59min","0.0"+unit);ItemOut item22 = new ItemOut("22h00min-22h59min","0.0"+unit);ItemOut item23 = new ItemOut("23h00min-23h59min","0.0"+unit);
		byhour.add(item20);byhour.add(item21);byhour.add(item22);byhour.add(item23);
		Car car = carRepository.fetchCarById(carid);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size(); i++) {
			Position position = positions.get(i);
		    valueday = valueday+position.getCourse();
			itemday.setValue(String.valueOf(valueday)+unit);

			String hour = utilityService.getHhFromFixTime(position.getFixtime());
			if(hour.equals("00")) {
				value00 = value00+position.getCourse()*1.85;
				item00.setValue(String.valueOf(value00)+unit);
			}
			if(hour.equals("01")) {
				value01 = +value01+position.getCourse();
				item01.setValue(String.valueOf(value01)+unit);
			}
			if(hour.equals("02")) {
				value02 = value02+position.getCourse();
				item02.setValue(String.valueOf(value02)+unit);
			}
			if(hour.equals("03")) {
				value03 = value03+position.getCourse();
				item03.setValue(String.valueOf(value03)+unit);
			}
			if(hour.equals("04")) {
				value04 = value04+position.getCourse();
				item04.setValue(String.valueOf(value04)+unit);
			}
			if(hour.equals("05")) {
				value05 = value05+position.getCourse();
				item05.setValue(String.valueOf(value05)+unit);
			}
			if(hour.equals("06")) {
				value06 = value06+position.getCourse();
				item06.setValue(String.valueOf(value06)+unit);
			}
			if(hour.equals("07")) {
				value07 = value07+position.getCourse();
				item07.setValue(String.valueOf(value07)+unit);
			}
			if(hour.equals("08")) {
				value08 = value08+position.getCourse();
				item08.setValue(String.valueOf(value08)+unit);
			}
			if(hour.equals("09")) {
				value09 = value09+position.getCourse();
				item09.setValue(String.valueOf(value09)+unit);
			}
			if(hour.equals("10")) {
				value10 = value10+position.getCourse();
				item10.setValue(String.valueOf(value10)+unit);
			}
			if(hour.equals("11")) {
				value11 = value11+position.getCourse();
				item11.setValue(String.valueOf(value11)+unit);
			}
			if(hour.equals("12")) {
				value12 = value12+position.getCourse();
				item12.setValue(String.valueOf(value12)+unit);
			}
			if(hour.equals("13")) {
				value13 = value13+position.getCourse();
				item13.setValue(String.valueOf(value13)+unit);
			}
			if(hour.equals("14")) {
				value14 = value14+position.getCourse();
				item14.setValue(String.valueOf(value14)+unit);
			}
			if(hour.equals("15")) {
				value15 = value15+position.getCourse();
				item15.setValue(String.valueOf(value15)+unit);
			}
			if(hour.equals("16")) {
				value16 = value16+position.getCourse();
				item16.setValue(String.valueOf(value16)+unit);
			}
			if(hour.equals("17")) {
				value17 = value17+position.getSpeed();
				item17.setValue(String.valueOf(value17)+unit);
			}
			if(hour.equals("18")) {
				value18 = value18+position.getCourse();
				item18.setValue(String.valueOf(value18)+unit);
			}
			if(hour.equals("19")) {
				value19 = value19+position.getCourse();
				item19.setValue(String.valueOf(value19)+unit);
			}
			if(hour.equals("20")) {
				value20 = value20+position.getCourse();
				item20.setValue(String.valueOf(value20)+unit);
			}
			if(hour.equals("21")) {
				value21 = value21+position.getCourse();
				item21.setValue(String.valueOf(value21)+unit);
			}
			if(hour.equals("22")) {
				value22 = value22+position.getCourse();
				item22.setValue(String.valueOf(value22)+unit);
			}
			if(hour.equals("23")) {
				value23 = value23+position.getCourse();
				item23.setValue(String.valueOf(value23)+unit);
			}
		}
		detail.setByday(byday);
		detail.setByhour(byhour);
		return detail;
	}

	@Override
	public DetailOut fetchCarFuelSecondaireByCarIdAndDate(Long carid, String date) {
		List<ItemOut> byday = new ArrayList<ItemOut>();
		List<ItemOut> byhour = new ArrayList<ItemOut>();
		DetailOut detail = new DetailOut();
		String unit = " L";
		double valueday = 0.0;ItemOut itemday = new ItemOut("Total","0.0"+unit);byday.add(itemday);
		double value00 = 0.0;double value01 = 0.0;double value02 = 0.0;double value03 = 0.0;
		ItemOut item00 = new ItemOut("00h00min-00h59min","0.0"+unit);ItemOut item01 = new ItemOut("01h00min-01h59min","0.0"+unit);ItemOut item02 = new ItemOut("02h00min-02h59min","0.0"+unit);ItemOut item03 = new ItemOut("03h00min-03h59min","0.0"+unit);
		byhour.add(item00);byhour.add(item01);byhour.add(item02);byhour.add(item03);
		double value04 = 0.0;double value05 = 0.0;double value06 = 0.0;double value07 = 0.0;
		ItemOut item04 = new ItemOut("04h00min-04h59min","0.0"+unit);ItemOut item05 = new ItemOut("05h00min-05h59min","0.0"+unit);ItemOut item06 = new ItemOut("06h00min-06h59min","0.0"+unit);ItemOut item07 = new ItemOut("07h00min-07h59min","0.0"+unit);
		byhour.add(item04);byhour.add(item05);byhour.add(item06);byhour.add(item07);
		double value08 = 0.0;double value09 = 0.0;double value10 = 0.0;double value11 = 0.0;
		ItemOut item08 = new ItemOut("08h00min-08h59min","0.0"+unit);ItemOut item09 = new ItemOut("09h00min-09h59min","0.0"+unit);ItemOut item10 = new ItemOut("10h00min-10h59min","0.0"+unit);ItemOut item11 = new ItemOut("11h00min-11h59min","0.0"+unit);
		byhour.add(item08);byhour.add(item09);byhour.add(item10);byhour.add(item11);
		double value12 = 0.0;double value13 = 0.0;double value14 = 0.0;double value15 = 0.0;
		ItemOut item12 = new ItemOut("12h00min-12h59min","0.0"+unit);ItemOut item13 = new ItemOut("13h00min-13h59min","0.0"+unit);ItemOut item14 = new ItemOut("14h00min-14h59min","0.0"+unit);ItemOut item15 = new ItemOut("15h00min-15h59min","0.0"+unit);
		byhour.add(item12);byhour.add(item13);byhour.add(item14);byhour.add(item15);
		double value16 = 0.0;double value17 = 0.0;double value18 = 0.0;double value19 = 0.0;
		ItemOut item16 = new ItemOut("16h00min-16h59min","0.0"+unit);ItemOut item17 = new ItemOut("17h00min-17h59min","0.0"+unit);ItemOut item18 = new ItemOut("18h00min-18h59min","0.0"+unit);ItemOut item19 = new ItemOut("19h00min-19h59min","0.0"+unit);
		byhour.add(item16);byhour.add(item17);byhour.add(item18);byhour.add(item19);
		double value20 = 0.0;double value21 = 0.0;double value22 = 0.0;double value23 = 0.0;
		ItemOut item20 = new ItemOut("20h00min-20h59min","0.0"+unit);ItemOut item21 = new ItemOut("21h00min-21h59min","0.0"+unit);ItemOut item22 = new ItemOut("22h00min-22h59min","0.0"+unit);ItemOut item23 = new ItemOut("23h00min-23h59min","0.0"+unit);
		byhour.add(item20);byhour.add(item21);byhour.add(item22);byhour.add(item23);
		Car car = carRepository.fetchCarById(carid);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size(); i++) {
			Position position = positions.get(i);
		    valueday = valueday+position.getCourse();
			itemday.setValue(String.valueOf(valueday)+unit);

			String hour = utilityService.getHhFromFixTime(position.getFixtime());
			if(hour.equals("00")) {
				value00 = value00+position.getCourse()*1.85;
				item00.setValue(String.valueOf(value00)+unit);
			}
			if(hour.equals("01")) {
				value01 = +value01+position.getCourse();
				item01.setValue(String.valueOf(value01)+unit);
			}
			if(hour.equals("02")) {
				value02 = value02+position.getCourse();
				item02.setValue(String.valueOf(value02)+unit);
			}
			if(hour.equals("03")) {
				value03 = value03+position.getCourse();
				item03.setValue(String.valueOf(value03)+unit);
			}
			if(hour.equals("04")) {
				value04 = value04+position.getCourse();
				item04.setValue(String.valueOf(value04)+unit);
			}
			if(hour.equals("05")) {
				value05 = value05+position.getCourse();
				item05.setValue(String.valueOf(value05)+unit);
			}
			if(hour.equals("06")) {
				value06 = value06+position.getCourse();
				item06.setValue(String.valueOf(value06)+unit);
			}
			if(hour.equals("07")) {
				value07 = value07+position.getCourse();
				item07.setValue(String.valueOf(value07)+unit);
			}
			if(hour.equals("08")) {
				value08 = value08+position.getCourse();
				item08.setValue(String.valueOf(value08)+unit);
			}
			if(hour.equals("09")) {
				value09 = value09+position.getCourse();
				item09.setValue(String.valueOf(value09)+unit);
			}
			if(hour.equals("10")) {
				value10 = value10+position.getCourse();
				item10.setValue(String.valueOf(value10)+unit);
			}
			if(hour.equals("11")) {
				value11 = value11+position.getCourse();
				item11.setValue(String.valueOf(value11)+unit);
			}
			if(hour.equals("12")) {
				value12 = value12+position.getCourse();
				item12.setValue(String.valueOf(value12)+unit);
			}
			if(hour.equals("13")) {
				value13 = value13+position.getCourse();
				item13.setValue(String.valueOf(value13)+unit);
			}
			if(hour.equals("14")) {
				value14 = value14+position.getCourse();
				item14.setValue(String.valueOf(value14)+unit);
			}
			if(hour.equals("15")) {
				value15 = value15+position.getCourse();
				item15.setValue(String.valueOf(value15)+unit);
			}
			if(hour.equals("16")) {
				value16 = value16+position.getCourse();
				item16.setValue(String.valueOf(value16)+unit);
			}
			if(hour.equals("17")) {
				value17 = value17+position.getSpeed();
				item17.setValue(String.valueOf(value17)+unit);
			}
			if(hour.equals("18")) {
				value18 = value18+position.getCourse();
				item18.setValue(String.valueOf(value18)+unit);
			}
			if(hour.equals("19")) {
				value19 = value19+position.getCourse();
				item19.setValue(String.valueOf(value19)+unit);
			}
			if(hour.equals("20")) {
				value20 = value20+position.getCourse();
				item20.setValue(String.valueOf(value20)+unit);
			}
			if(hour.equals("21")) {
				value21 = value21+position.getCourse();
				item21.setValue(String.valueOf(value21)+unit);
			}
			if(hour.equals("22")) {
				value22 = value22+position.getCourse();
				item22.setValue(String.valueOf(value22)+unit);
			}
			if(hour.equals("23")) {
				value23 = value23+position.getCourse();
				item23.setValue(String.valueOf(value23)+unit);
			}
		}
		detail.setByday(byday);
		detail.setByhour(byhour);
		return detail;
	}

	@Override
	public List<NotifMessageOut> fetchCarNotificationMessageByCarIdAndDate(
			Long id, String date) {
		List<Notification> notifs = notificationRepository.fetchNotificationByCarIdAndDate(id, date);
		List<NotifMessageOut> notifmessages = new ArrayList<NotifMessageOut>();
		for(int i=0; i<notifs.size(); i++){
			NotifMessageOut message = new NotifMessageOut();
			Notification notif = notifs.get(i);
			message.setHour(utilityService.getHhMmSsFromFixTime(notif.getCreationdate()));
			message.setMessage(notif.getMessage());
			notifmessages.add(message);
		}
		return notifmessages;
	}

	@Override
	public ActiveNotif fetchCarActiveNotifByCarId(Long id) {
		// TODO Auto-generated method stub
		return activenotifRepository.fetchActiveNotifByCarId(id);
	}

	@Override
	public Zone fetchCarZoneByCarIdAndNumber(Long id, Integer number) {
		// TODO Auto-generated method stub
		return zoneRepository.fetchZoneByCarIdAndRank(id, number);
	}

	@Override
	public DetailOut fetchCarTemperatureMoByCarIdAndDate(Long carid, String date) {
		List<ItemOut> byday = new ArrayList<ItemOut>();
		List<ItemOut> byhour = new ArrayList<ItemOut>();
		DetailOut detail = new DetailOut();
		String unit = " C°";
		double valueday = 0.0;ItemOut itemday = new ItemOut("Total","0.0"+unit);byday.add(itemday);
		double value00 = 0.0;double value01 = 0.0;double value02 = 0.0;double value03 = 0.0;
		ItemOut item00 = new ItemOut("00h00min-00h59min","0.0"+unit);ItemOut item01 = new ItemOut("01h00min-01h59min","0.0"+unit);ItemOut item02 = new ItemOut("02h00min-02h59min","0.0"+unit);ItemOut item03 = new ItemOut("03h00min-03h59min","0.0"+unit);
		byhour.add(item00);byhour.add(item01);byhour.add(item02);byhour.add(item03);
		double value04 = 0.0;double value05 = 0.0;double value06 = 0.0;double value07 = 0.0;
		ItemOut item04 = new ItemOut("04h00min-04h59min","0.0"+unit);ItemOut item05 = new ItemOut("05h00min-05h59min","0.0"+unit);ItemOut item06 = new ItemOut("06h00min-06h59min","0.0"+unit);ItemOut item07 = new ItemOut("07h00min-07h59min","0.0"+unit);
		byhour.add(item04);byhour.add(item05);byhour.add(item06);byhour.add(item07);
		double value08 = 0.0;double value09 = 0.0;double value10 = 0.0;double value11 = 0.0;
		ItemOut item08 = new ItemOut("08h00min-08h59min","0.0"+unit);ItemOut item09 = new ItemOut("09h00min-09h59min","0.0"+unit);ItemOut item10 = new ItemOut("10h00min-10h59min","0.0"+unit);ItemOut item11 = new ItemOut("11h00min-11h59min","0.0"+unit);
		byhour.add(item08);byhour.add(item09);byhour.add(item10);byhour.add(item11);
		double value12 = 0.0;double value13 = 0.0;double value14 = 0.0;double value15 = 0.0;
		ItemOut item12 = new ItemOut("12h00min-12h59min","0.0"+unit);ItemOut item13 = new ItemOut("13h00min-13h59min","0.0"+unit);ItemOut item14 = new ItemOut("14h00min-14h59min","0.0"+unit);ItemOut item15 = new ItemOut("15h00min-15h59min","0.0"+unit);
		byhour.add(item12);byhour.add(item13);byhour.add(item14);byhour.add(item15);
		double value16 = 0.0;double value17 = 0.0;double value18 = 0.0;double value19 = 0.0;
		ItemOut item16 = new ItemOut("16h00min-16h59min","0.0"+unit);ItemOut item17 = new ItemOut("17h00min-17h59min","0.0"+unit);ItemOut item18 = new ItemOut("18h00min-18h59min","0.0"+unit);ItemOut item19 = new ItemOut("19h00min-19h59min","0.0"+unit);
		byhour.add(item16);byhour.add(item17);byhour.add(item18);byhour.add(item19);
		double value20 = 0.0;double value21 = 0.0;double value22 = 0.0;double value23 = 0.0;
		ItemOut item20 = new ItemOut("20h00min-20h59min","0.0"+unit);ItemOut item21 = new ItemOut("21h00min-21h59min","0.0"+unit);ItemOut item22 = new ItemOut("22h00min-22h59min","0.0"+unit);ItemOut item23 = new ItemOut("23h00min-23h59min","0.0"+unit);
		byhour.add(item20);byhour.add(item21);byhour.add(item22);byhour.add(item23);
		Car car = carRepository.fetchCarById(carid);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size(); i++) {
			Position position = positions.get(i);
			if(position.getSpeed() > valueday) {
				valueday = position.getSpeed();
				itemday.setValue(String.valueOf(valueday)+unit);
			}
			String hour = utilityService.getHhFromFixTime(position.getFixtime());
			if(hour.equals("00") && position.getSpeed() >= value00) {
				value00 = position.getSpeed();
				item00.setValue(String.valueOf(value00)+unit);
			}
			if(hour.equals("01") && position.getSpeed() >= value01) {
				value01 = position.getSpeed();
				item01.setValue(String.valueOf(value01)+unit);
			}
			if(hour.equals("02") && position.getSpeed() >= value02) {
				value02 = position.getSpeed();
				item02.setValue(String.valueOf(value02)+unit);
			}
			if(hour.equals("03") && position.getSpeed() >= value03) {
				value03 = position.getSpeed();
				item03.setValue(String.valueOf(value03)+unit);
			}
			if(hour.equals("04") && position.getSpeed() >= value04) {
				value04 = position.getSpeed();
				item04.setValue(String.valueOf(value04)+unit);
			}
			if(hour.equals("05") && position.getSpeed() >= value05) {
				value05 = position.getSpeed();
				item05.setValue(String.valueOf(value05)+unit);
			}
			if(hour.equals("06") && position.getSpeed() >= value06) {
				value06 = position.getSpeed();
				item06.setValue(String.valueOf(value06)+unit);
			}
			if(hour.equals("07") && position.getSpeed() >= value07) {
				value07 = position.getSpeed();
				item07.setValue(String.valueOf(value07)+unit);
			}
			if(hour.equals("08") && position.getSpeed() >= value08) {
				value08 = position.getSpeed();
				item08.setValue(String.valueOf(value08)+unit);
			}
			if(hour.equals("09") && position.getSpeed() >= value09) {
				value09 = position.getSpeed();
				item09.setValue(String.valueOf(value09)+unit);
			}
			if(hour.equals("10") && position.getSpeed() >= value10) {
				value10 = position.getSpeed();
				item10.setValue(String.valueOf(value10)+unit);
			}
			if(hour.equals("11") && position.getSpeed() >= value11) {
				value11 = position.getSpeed();
				item11.setValue(String.valueOf(value11)+unit);
			}
			if(hour.equals("12") && position.getSpeed() >= value12) {
				value12 = position.getSpeed();
				item12.setValue(String.valueOf(value12)+unit);
			}
			if(hour.equals("13") && position.getSpeed() >= value13) {
				value13 = position.getSpeed();
				item13.setValue(String.valueOf(value13)+unit);
			}
			if(hour.equals("14") && position.getSpeed() >= value14) {
				value14 = position.getSpeed();
				item14.setValue(String.valueOf(value14)+unit);
			}
			if(hour.equals("15") && position.getSpeed() >= value15) {
				value15 = position.getSpeed();
				item15.setValue(String.valueOf(value15)+unit);
			}
			if(hour.equals("16") && position.getSpeed() >= value16) {
				value16 = position.getSpeed();
				item16.setValue(String.valueOf(value16)+unit);
			}
			if(hour.equals("17") && position.getSpeed() >= value17) {
				value17 = position.getSpeed();
				item17.setValue(String.valueOf(value17)+unit);
			}
			if(hour.equals("18") && position.getSpeed() >= value18) {
				value18 = position.getSpeed();
				item18.setValue(String.valueOf(value18)+unit);
			}
			if(hour.equals("19") && position.getSpeed() >= value19) {
				value19 = position.getSpeed();
				item19.setValue(String.valueOf(value19)+unit);
			}
			if(hour.equals("20") && position.getSpeed() >= value20) {
				value20 = position.getSpeed();
				item20.setValue(String.valueOf(value20)+unit);
			}
			if(hour.equals("21") && position.getSpeed() >= value21) {
				value21 = position.getSpeed();
				item21.setValue(String.valueOf(value21)+unit);
			}
			if(hour.equals("22") && position.getSpeed() >= value22) {
				value22 = position.getSpeed();
				item22.setValue(String.valueOf(value22)+unit);
			}
			if(hour.equals("23") && position.getSpeed() >= value23) {
				value23 = position.getSpeed();
				item23.setValue(String.valueOf(value23)+unit);
			}
		}
		detail.setByday(byday);
		detail.setByhour(byhour);
		return detail;
	}

	@Override
	public DetailOut fetchCarTemperatureFrByCarIdAndDate(Long carid, String date) {
		List<ItemOut> byday = new ArrayList<ItemOut>();
		List<ItemOut> byhour = new ArrayList<ItemOut>();
		DetailOut detail = new DetailOut();
		String unit = " C°";
		double valueday = 0.0;ItemOut itemday = new ItemOut("Total","0.0"+unit);byday.add(itemday);
		double value00 = 0.0;double value01 = 0.0;double value02 = 0.0;double value03 = 0.0;
		ItemOut item00 = new ItemOut("00h00min-00h59min","0.0"+unit);ItemOut item01 = new ItemOut("01h00min-01h59min","0.0"+unit);ItemOut item02 = new ItemOut("02h00min-02h59min","0.0"+unit);ItemOut item03 = new ItemOut("03h00min-03h59min","0.0"+unit);
		byhour.add(item00);byhour.add(item01);byhour.add(item02);byhour.add(item03);
		double value04 = 0.0;double value05 = 0.0;double value06 = 0.0;double value07 = 0.0;
		ItemOut item04 = new ItemOut("04h00min-04h59min","0.0"+unit);ItemOut item05 = new ItemOut("05h00min-05h59min","0.0"+unit);ItemOut item06 = new ItemOut("06h00min-06h59min","0.0"+unit);ItemOut item07 = new ItemOut("07h00min-07h59min","0.0"+unit);
		byhour.add(item04);byhour.add(item05);byhour.add(item06);byhour.add(item07);
		double value08 = 0.0;double value09 = 0.0;double value10 = 0.0;double value11 = 0.0;
		ItemOut item08 = new ItemOut("08h00min-08h59min","0.0"+unit);ItemOut item09 = new ItemOut("09h00min-09h59min","0.0"+unit);ItemOut item10 = new ItemOut("10h00min-10h59min","0.0"+unit);ItemOut item11 = new ItemOut("11h00min-11h59min","0.0"+unit);
		byhour.add(item08);byhour.add(item09);byhour.add(item10);byhour.add(item11);
		double value12 = 0.0;double value13 = 0.0;double value14 = 0.0;double value15 = 0.0;
		ItemOut item12 = new ItemOut("12h00min-12h59min","0.0"+unit);ItemOut item13 = new ItemOut("13h00min-13h59min","0.0"+unit);ItemOut item14 = new ItemOut("14h00min-14h59min","0.0"+unit);ItemOut item15 = new ItemOut("15h00min-15h59min","0.0"+unit);
		byhour.add(item12);byhour.add(item13);byhour.add(item14);byhour.add(item15);
		double value16 = 0.0;double value17 = 0.0;double value18 = 0.0;double value19 = 0.0;
		ItemOut item16 = new ItemOut("16h00min-16h59min","0.0"+unit);ItemOut item17 = new ItemOut("17h00min-17h59min","0.0"+unit);ItemOut item18 = new ItemOut("18h00min-18h59min","0.0"+unit);ItemOut item19 = new ItemOut("19h00min-19h59min","0.0"+unit);
		byhour.add(item16);byhour.add(item17);byhour.add(item18);byhour.add(item19);
		double value20 = 0.0;double value21 = 0.0;double value22 = 0.0;double value23 = 0.0;
		ItemOut item20 = new ItemOut("20h00min-20h59min","0.0"+unit);ItemOut item21 = new ItemOut("21h00min-21h59min","0.0"+unit);ItemOut item22 = new ItemOut("22h00min-22h59min","0.0"+unit);ItemOut item23 = new ItemOut("23h00min-23h59min","0.0"+unit);
		byhour.add(item20);byhour.add(item21);byhour.add(item22);byhour.add(item23);
		Car car = carRepository.fetchCarById(carid);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size(); i++) {
			Position position = positions.get(i);
			if(position.getSpeed() > valueday) {
				valueday = position.getSpeed();
				itemday.setValue(String.valueOf(valueday)+unit);
			}
			String hour = utilityService.getHhFromFixTime(position.getFixtime());
			if(hour.equals("00") && position.getSpeed() >= value00) {
				value00 = position.getSpeed();
				item00.setValue(String.valueOf(value00)+unit);
			}
			if(hour.equals("01") && position.getSpeed() >= value01) {
				value01 = position.getSpeed();
				item01.setValue(String.valueOf(value01)+unit);
			}
			if(hour.equals("02") && position.getSpeed() >= value02) {
				value02 = position.getSpeed();
				item02.setValue(String.valueOf(value02)+unit);
			}
			if(hour.equals("03") && position.getSpeed() >= value03) {
				value03 = position.getSpeed();
				item03.setValue(String.valueOf(value03)+unit);
			}
			if(hour.equals("04") && position.getSpeed() >= value04) {
				value04 = position.getSpeed();
				item04.setValue(String.valueOf(value04)+unit);
			}
			if(hour.equals("05") && position.getSpeed() >= value05) {
				value05 = position.getSpeed();
				item05.setValue(String.valueOf(value05)+unit);
			}
			if(hour.equals("06") && position.getSpeed() >= value06) {
				value06 = position.getSpeed();
				item06.setValue(String.valueOf(value06)+unit);
			}
			if(hour.equals("07") && position.getSpeed() >= value07) {
				value07 = position.getSpeed();
				item07.setValue(String.valueOf(value07)+unit);
			}
			if(hour.equals("08") && position.getSpeed() >= value08) {
				value08 = position.getSpeed();
				item08.setValue(String.valueOf(value08)+unit);
			}
			if(hour.equals("09") && position.getSpeed() >= value09) {
				value09 = position.getSpeed();
				item09.setValue(String.valueOf(value09)+unit);
			}
			if(hour.equals("10") && position.getSpeed() >= value10) {
				value10 = position.getSpeed();
				item10.setValue(String.valueOf(value10)+unit);
			}
			if(hour.equals("11") && position.getSpeed() >= value11) {
				value11 = position.getSpeed();
				item11.setValue(String.valueOf(value11)+unit);
			}
			if(hour.equals("12") && position.getSpeed() >= value12) {
				value12 = position.getSpeed();
				item12.setValue(String.valueOf(value12)+unit);
			}
			if(hour.equals("13") && position.getSpeed() >= value13) {
				value13 = position.getSpeed();
				item13.setValue(String.valueOf(value13)+unit);
			}
			if(hour.equals("14") && position.getSpeed() >= value14) {
				value14 = position.getSpeed();
				item14.setValue(String.valueOf(value14)+unit);
			}
			if(hour.equals("15") && position.getSpeed() >= value15) {
				value15 = position.getSpeed();
				item15.setValue(String.valueOf(value15)+unit);
			}
			if(hour.equals("16") && position.getSpeed() >= value16) {
				value16 = position.getSpeed();
				item16.setValue(String.valueOf(value16)+unit);
			}
			if(hour.equals("17") && position.getSpeed() >= value17) {
				value17 = position.getSpeed();
				item17.setValue(String.valueOf(value17)+unit);
			}
			if(hour.equals("18") && position.getSpeed() >= value18) {
				value18 = position.getSpeed();
				item18.setValue(String.valueOf(value18)+unit);
			}
			if(hour.equals("19") && position.getSpeed() >= value19) {
				value19 = position.getSpeed();
				item19.setValue(String.valueOf(value19)+unit);
			}
			if(hour.equals("20") && position.getSpeed() >= value20) {
				value20 = position.getSpeed();
				item20.setValue(String.valueOf(value20)+unit);
			}
			if(hour.equals("21") && position.getSpeed() >= value21) {
				value21 = position.getSpeed();
				item21.setValue(String.valueOf(value21)+unit);
			}
			if(hour.equals("22") && position.getSpeed() >= value22) {
				value22 = position.getSpeed();
				item22.setValue(String.valueOf(value22)+unit);
			}
			if(hour.equals("23") && position.getSpeed() >= value23) {
				value23 = position.getSpeed();
				item23.setValue(String.valueOf(value23)+unit);
			}
		}
		detail.setByday(byday);
		detail.setByhour(byhour);
		return detail;
	}

	@Override
	public DetailOut fetchCarDoorByCarIdAndDate(Long carid, String date) {
		List<ItemOut> byday = new ArrayList<ItemOut>();
		List<ItemOut> byhour = new ArrayList<ItemOut>();
		DetailOut detail = new DetailOut();
		String unit = "";
		double valueday1 = 0.0;ItemOut itemday1 = new ItemOut("Total ouvertures","0.0"+unit);byday.add(itemday1);
		double valueday2 = 0.0;ItemOut itemday2 = new ItemOut("Total fermetures","0.0"+unit);byday.add(itemday2);
		double value00 = 0.0;double value01 = 0.0;double value02 = 0.0;double value03 = 0.0;
		ItemOut item00 = new ItemOut("00h00min-00h59min","0.0"+unit);ItemOut item01 = new ItemOut("01h00min-01h59min","0.0"+unit);ItemOut item02 = new ItemOut("02h00min-02h59min","0.0"+unit);ItemOut item03 = new ItemOut("03h00min-03h59min","0.0"+unit);
		byhour.add(item00);byhour.add(item01);byhour.add(item02);byhour.add(item03);
		double value04 = 0.0;double value05 = 0.0;double value06 = 0.0;double value07 = 0.0;
		ItemOut item04 = new ItemOut("04h00min-04h59min","0.0"+unit);ItemOut item05 = new ItemOut("05h00min-05h59min","0.0"+unit);ItemOut item06 = new ItemOut("06h00min-06h59min","0.0"+unit);ItemOut item07 = new ItemOut("07h00min-07h59min","0.0"+unit);
		byhour.add(item04);byhour.add(item05);byhour.add(item06);byhour.add(item07);
		double value08 = 0.0;double value09 = 0.0;double value10 = 0.0;double value11 = 0.0;
		ItemOut item08 = new ItemOut("08h00min-08h59min","0.0"+unit);ItemOut item09 = new ItemOut("09h00min-09h59min","0.0"+unit);ItemOut item10 = new ItemOut("10h00min-10h59min","0.0"+unit);ItemOut item11 = new ItemOut("11h00min-11h59min","0.0"+unit);
		byhour.add(item08);byhour.add(item09);byhour.add(item10);byhour.add(item11);
		double value12 = 0.0;double value13 = 0.0;double value14 = 0.0;double value15 = 0.0;
		ItemOut item12 = new ItemOut("12h00min-12h59min","0.0"+unit);ItemOut item13 = new ItemOut("13h00min-13h59min","0.0"+unit);ItemOut item14 = new ItemOut("14h00min-14h59min","0.0"+unit);ItemOut item15 = new ItemOut("15h00min-15h59min","0.0"+unit);
		byhour.add(item12);byhour.add(item13);byhour.add(item14);byhour.add(item15);
		double value16 = 0.0;double value17 = 0.0;double value18 = 0.0;double value19 = 0.0;
		ItemOut item16 = new ItemOut("16h00min-16h59min","0.0"+unit);ItemOut item17 = new ItemOut("17h00min-17h59min","0.0"+unit);ItemOut item18 = new ItemOut("18h00min-18h59min","0.0"+unit);ItemOut item19 = new ItemOut("19h00min-19h59min","0.0"+unit);
		byhour.add(item16);byhour.add(item17);byhour.add(item18);byhour.add(item19);
		double value20 = 0.0;double value21 = 0.0;double value22 = 0.0;double value23 = 0.0;
		ItemOut item20 = new ItemOut("20h00min-20h59min","0.0"+unit);ItemOut item21 = new ItemOut("21h00min-21h59min","0.0"+unit);ItemOut item22 = new ItemOut("22h00min-22h59min","0.0"+unit);ItemOut item23 = new ItemOut("23h00min-23h59min","0.0"+unit);
		byhour.add(item20);byhour.add(item21);byhour.add(item22);byhour.add(item23);
		Car car = carRepository.fetchCarById(carid);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size(); i++) {
			Position position = positions.get(i);
			if(position.getSpeed() > valueday1) {
				valueday1 = position.getSpeed();
				itemday1.setValue(String.valueOf(valueday1)+unit);
				valueday2 = position.getSpeed();
				itemday2.setValue(String.valueOf(valueday2)+unit);
			}
			String hour = utilityService.getHhFromFixTime(position.getFixtime());
			if(hour.equals("00") && position.getSpeed() >= value00) {
				value00 = position.getSpeed();
				item00.setValue(String.valueOf(value00)+unit);
			}
			if(hour.equals("01") && position.getSpeed() >= value01) {
				value01 = position.getSpeed();
				item01.setValue(String.valueOf(value01)+unit);
			}
			if(hour.equals("02") && position.getSpeed() >= value02) {
				value02 = position.getSpeed();
				item02.setValue(String.valueOf(value02)+unit);
			}
			if(hour.equals("03") && position.getSpeed() >= value03) {
				value03 = position.getSpeed();
				item03.setValue(String.valueOf(value03)+unit);
			}
			if(hour.equals("04") && position.getSpeed() >= value04) {
				value04 = position.getSpeed();
				item04.setValue(String.valueOf(value04)+unit);
			}
			if(hour.equals("05") && position.getSpeed() >= value05) {
				value05 = position.getSpeed();
				item05.setValue(String.valueOf(value05)+unit);
			}
			if(hour.equals("06") && position.getSpeed() >= value06) {
				value06 = position.getSpeed();
				item06.setValue(String.valueOf(value06)+unit);
			}
			if(hour.equals("07") && position.getSpeed() >= value07) {
				value07 = position.getSpeed();
				item07.setValue(String.valueOf(value07)+unit);
			}
			if(hour.equals("08") && position.getSpeed() >= value08) {
				value08 = position.getSpeed();
				item08.setValue(String.valueOf(value08)+unit);
			}
			if(hour.equals("09") && position.getSpeed() >= value09) {
				value09 = position.getSpeed();
				item09.setValue(String.valueOf(value09)+unit);
			}
			if(hour.equals("10") && position.getSpeed() >= value10) {
				value10 = position.getSpeed();
				item10.setValue(String.valueOf(value10)+unit);
			}
			if(hour.equals("11") && position.getSpeed() >= value11) {
				value11 = position.getSpeed();
				item11.setValue(String.valueOf(value11)+unit);
			}
			if(hour.equals("12") && position.getSpeed() >= value12) {
				value12 = position.getSpeed();
				item12.setValue(String.valueOf(value12)+unit);
			}
			if(hour.equals("13") && position.getSpeed() >= value13) {
				value13 = position.getSpeed();
				item13.setValue(String.valueOf(value13)+unit);
			}
			if(hour.equals("14") && position.getSpeed() >= value14) {
				value14 = position.getSpeed();
				item14.setValue(String.valueOf(value14)+unit);
			}
			if(hour.equals("15") && position.getSpeed() >= value15) {
				value15 = position.getSpeed();
				item15.setValue(String.valueOf(value15)+unit);
			}
			if(hour.equals("16") && position.getSpeed() >= value16) {
				value16 = position.getSpeed();
				item16.setValue(String.valueOf(value16)+unit);
			}
			if(hour.equals("17") && position.getSpeed() >= value17) {
				value17 = position.getSpeed();
				item17.setValue(String.valueOf(value17)+unit);
			}
			if(hour.equals("18") && position.getSpeed() >= value18) {
				value18 = position.getSpeed();
				item18.setValue(String.valueOf(value18)+unit);
			}
			if(hour.equals("19") && position.getSpeed() >= value19) {
				value19 = position.getSpeed();
				item19.setValue(String.valueOf(value19)+unit);
			}
			if(hour.equals("20") && position.getSpeed() >= value20) {
				value20 = position.getSpeed();
				item20.setValue(String.valueOf(value20)+unit);
			}
			if(hour.equals("21") && position.getSpeed() >= value21) {
				value21 = position.getSpeed();
				item21.setValue(String.valueOf(value21)+unit);
			}
			if(hour.equals("22") && position.getSpeed() >= value22) {
				value22 = position.getSpeed();
				item22.setValue(String.valueOf(value22)+unit);
			}
			if(hour.equals("23") && position.getSpeed() >= value23) {
				value23 = position.getSpeed();
				item23.setValue(String.valueOf(value23)+unit);
			}
		}
		detail.setByday(byday);
		detail.setByhour(byhour);
		return detail;
	}

	@Override
	public DetailOut fetchCarDriverByCarIdAndDate(Long carid, String date) {
		List<ItemOut> byday = new ArrayList<ItemOut>();
		List<ItemOut> byhour = new ArrayList<ItemOut>();
		DetailOut detail = new DetailOut();
		String unit = "";
		double valueday1 = 0.0;ItemOut itemday1 = new ItemOut("Abdelhaq","0.0"+unit);byday.add(itemday1);
		double valueday2 = 0.0;ItemOut itemday2 = new ItemOut("Anas","0.0"+unit);byday.add(itemday2);
		double value00 = 0.0;double value01 = 0.0;double value02 = 0.0;double value03 = 0.0;
		ItemOut item00 = new ItemOut("00h00min-00h59min","0.0"+unit);ItemOut item01 = new ItemOut("01h00min-01h59min","0.0"+unit);ItemOut item02 = new ItemOut("02h00min-02h59min","0.0"+unit);ItemOut item03 = new ItemOut("03h00min-03h59min","0.0"+unit);
		byhour.add(item00);byhour.add(item01);byhour.add(item02);byhour.add(item03);
		double value04 = 0.0;double value05 = 0.0;double value06 = 0.0;double value07 = 0.0;
		ItemOut item04 = new ItemOut("04h00min-04h59min","0.0"+unit);ItemOut item05 = new ItemOut("05h00min-05h59min","0.0"+unit);ItemOut item06 = new ItemOut("06h00min-06h59min","0.0"+unit);ItemOut item07 = new ItemOut("07h00min-07h59min","0.0"+unit);
		byhour.add(item04);byhour.add(item05);byhour.add(item06);byhour.add(item07);
		double value08 = 0.0;double value09 = 0.0;double value10 = 0.0;double value11 = 0.0;
		ItemOut item08 = new ItemOut("08h00min-08h59min","0.0"+unit);ItemOut item09 = new ItemOut("09h00min-09h59min","0.0"+unit);ItemOut item10 = new ItemOut("10h00min-10h59min","0.0"+unit);ItemOut item11 = new ItemOut("11h00min-11h59min","0.0"+unit);
		byhour.add(item08);byhour.add(item09);byhour.add(item10);byhour.add(item11);
		double value12 = 0.0;double value13 = 0.0;double value14 = 0.0;double value15 = 0.0;
		ItemOut item12 = new ItemOut("12h00min-12h59min","0.0"+unit);ItemOut item13 = new ItemOut("13h00min-13h59min","0.0"+unit);ItemOut item14 = new ItemOut("14h00min-14h59min","0.0"+unit);ItemOut item15 = new ItemOut("15h00min-15h59min","0.0"+unit);
		byhour.add(item12);byhour.add(item13);byhour.add(item14);byhour.add(item15);
		double value16 = 0.0;double value17 = 0.0;double value18 = 0.0;double value19 = 0.0;
		ItemOut item16 = new ItemOut("16h00min-16h59min","0.0"+unit);ItemOut item17 = new ItemOut("17h00min-17h59min","0.0"+unit);ItemOut item18 = new ItemOut("18h00min-18h59min","0.0"+unit);ItemOut item19 = new ItemOut("19h00min-19h59min","0.0"+unit);
		byhour.add(item16);byhour.add(item17);byhour.add(item18);byhour.add(item19);
		double value20 = 0.0;double value21 = 0.0;double value22 = 0.0;double value23 = 0.0;
		ItemOut item20 = new ItemOut("20h00min-20h59min","0.0"+unit);ItemOut item21 = new ItemOut("21h00min-21h59min","0.0"+unit);ItemOut item22 = new ItemOut("22h00min-22h59min","0.0"+unit);ItemOut item23 = new ItemOut("23h00min-23h59min","0.0"+unit);
		byhour.add(item20);byhour.add(item21);byhour.add(item22);byhour.add(item23);
		Car car = carRepository.fetchCarById(carid);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size(); i++) {
			Position position = positions.get(i);
			if(position.getSpeed() > valueday1) {
				valueday1 = position.getSpeed();
				itemday1.setValue(String.valueOf(valueday1)+unit);
				valueday2 = position.getSpeed();
				itemday2.setValue(String.valueOf(valueday2)+unit);
			}
			String hour = utilityService.getHhFromFixTime(position.getFixtime());
			if(hour.equals("00") && position.getSpeed() >= value00) {
				value00 = position.getSpeed();
				item00.setValue(String.valueOf(value00)+unit);
			}
			if(hour.equals("01") && position.getSpeed() >= value01) {
				value01 = position.getSpeed();
				item01.setValue(String.valueOf(value01)+unit);
			}
			if(hour.equals("02") && position.getSpeed() >= value02) {
				value02 = position.getSpeed();
				item02.setValue(String.valueOf(value02)+unit);
			}
			if(hour.equals("03") && position.getSpeed() >= value03) {
				value03 = position.getSpeed();
				item03.setValue(String.valueOf(value03)+unit);
			}
			if(hour.equals("04") && position.getSpeed() >= value04) {
				value04 = position.getSpeed();
				item04.setValue(String.valueOf(value04)+unit);
			}
			if(hour.equals("05") && position.getSpeed() >= value05) {
				value05 = position.getSpeed();
				item05.setValue(String.valueOf(value05)+unit);
			}
			if(hour.equals("06") && position.getSpeed() >= value06) {
				value06 = position.getSpeed();
				item06.setValue(String.valueOf(value06)+unit);
			}
			if(hour.equals("07") && position.getSpeed() >= value07) {
				value07 = position.getSpeed();
				item07.setValue(String.valueOf(value07)+unit);
			}
			if(hour.equals("08") && position.getSpeed() >= value08) {
				value08 = position.getSpeed();
				item08.setValue(String.valueOf(value08)+unit);
			}
			if(hour.equals("09") && position.getSpeed() >= value09) {
				value09 = position.getSpeed();
				item09.setValue(String.valueOf(value09)+unit);
			}
			if(hour.equals("10") && position.getSpeed() >= value10) {
				value10 = position.getSpeed();
				item10.setValue(String.valueOf(value10)+unit);
			}
			if(hour.equals("11") && position.getSpeed() >= value11) {
				value11 = position.getSpeed();
				item11.setValue(String.valueOf(value11)+unit);
			}
			if(hour.equals("12") && position.getSpeed() >= value12) {
				value12 = position.getSpeed();
				item12.setValue(String.valueOf(value12)+unit);
			}
			if(hour.equals("13") && position.getSpeed() >= value13) {
				value13 = position.getSpeed();
				item13.setValue(String.valueOf(value13)+unit);
			}
			if(hour.equals("14") && position.getSpeed() >= value14) {
				value14 = position.getSpeed();
				item14.setValue(String.valueOf(value14)+unit);
			}
			if(hour.equals("15") && position.getSpeed() >= value15) {
				value15 = position.getSpeed();
				item15.setValue(String.valueOf(value15)+unit);
			}
			if(hour.equals("16") && position.getSpeed() >= value16) {
				value16 = position.getSpeed();
				item16.setValue(String.valueOf(value16)+unit);
			}
			if(hour.equals("17") && position.getSpeed() >= value17) {
				value17 = position.getSpeed();
				item17.setValue(String.valueOf(value17)+unit);
			}
			if(hour.equals("18") && position.getSpeed() >= value18) {
				value18 = position.getSpeed();
				item18.setValue(String.valueOf(value18)+unit);
			}
			if(hour.equals("19") && position.getSpeed() >= value19) {
				value19 = position.getSpeed();
				item19.setValue(String.valueOf(value19)+unit);
			}
			if(hour.equals("20") && position.getSpeed() >= value20) {
				value20 = position.getSpeed();
				item20.setValue(String.valueOf(value20)+unit);
			}
			if(hour.equals("21") && position.getSpeed() >= value21) {
				value21 = position.getSpeed();
				item21.setValue(String.valueOf(value21)+unit);
			}
			if(hour.equals("22") && position.getSpeed() >= value22) {
				value22 = position.getSpeed();
				item22.setValue(String.valueOf(value22)+unit);
			}
			if(hour.equals("23") && position.getSpeed() >= value23) {
				value23 = position.getSpeed();
				item23.setValue(String.valueOf(value23)+unit);
			}
		}
		detail.setByday(byday);
		detail.setByhour(byhour);
		return detail;
	}

	@Override
	public Parameter fetchCarParametersByCarId(Long id) {
		// TODO Auto-generated method stub
		return parameterRepository.fetchParameterByCarId(id);
	}

	@Override
	public AccountOut fetchAccountByAgencyId(Long id) {
		AccountOut account = new AccountOut();
		Profile profile = new Profile();
		Agency  agency  = new Agency();
		Payment payment = new Payment();
		agency = agencyRepository.fetchAgencyById(id);
		profile = profileRepository.fetchProfileById(agency.getIdProfile());
		payment = paymentRepository.fetchPayment();
		account.setAgency(agency);
		account.setProfile(profile);
		account.setPayment(payment);
		return account;
	}

	@Override
	public List<Contact> fetchContacts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Car save(Car car) {
		// TODO Auto-generated method stub
		return carRepository.save(car);
	}
}
