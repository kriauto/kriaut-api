package ma.kriauto.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Position;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.repository.AgencyRepository;
import ma.kriauto.api.repository.CarRepository;
import ma.kriauto.api.repository.PositionRepository;
import ma.kriauto.api.repository.ZoneRepository;
import ma.kriauto.api.response.HistoryOut;
import ma.kriauto.api.response.FuelOut;
import ma.kriauto.api.response.HistoryLocationOut;
import ma.kriauto.api.response.LastPositionOut;
import ma.kriauto.api.response.Location;
import ma.kriauto.api.response.MaxCourseOut;
import ma.kriauto.api.response.MaxSpeedOut;
import ma.kriauto.api.response.MaxTemperatureOut;
import ma.kriauto.api.response.NotificationOut;
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
    private UtilityService utilityService;

	@Override
	public List<Car> fetchAllCarByAgencyId(Long id) {
		return carRepository.fetchAllCarByAgencyId(id);
	}

	@Override
	public Car fetchCarById(Long id) {
		return carRepository.fetchCarById(id);
	}
	
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
			lastposition.setDate(utilityService.getDateFromFixTime(position.getFixtime()));
			lastposition.setHour(utilityService.getHourFromFixTime(position.getFixtime()));		
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
			historylocation.setHour(utilityService.getHourFromFixTime(position.getFixtime()));
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
	public List<NotificationOut> fetchCarNotificationByAgencyIdAndNumber(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<NotificationOut> locations = new ArrayList<NotificationOut>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			NotificationOut location = new NotificationOut();
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
			location.setNumber(15);
			locations.add(location);
		}
		return locations;
	}
}
