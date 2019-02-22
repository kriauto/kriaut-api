package ma.kriauto.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.kriauto.api.dto.HistoryDTO;
import ma.kriauto.api.dto.HistoryLocationDTO;
import ma.kriauto.api.dto.LastPositionDTO;
import ma.kriauto.api.dto.Location;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Position;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.repository.AgencyRepository;
import ma.kriauto.api.repository.CarRepository;
import ma.kriauto.api.repository.PositionRepository;
import ma.kriauto.api.repository.ZoneRepository;

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
	public List<LastPositionDTO> fetchLastPositionByAgencyIdAndDate(Long id, String date) {
		List<LastPositionDTO> lsatpositions = new ArrayList<LastPositionDTO>();
		Agency agency = agencyRepository.fetchAgencyByProfileId(id);
		List<Car> cars = carRepository.fetchAllCarByAgencyId(agency.getId());
		for(int i=0; i<cars.size() ;i++){
			Car car = cars.get(i);
			List<Position> positions = positionRepository.fetchLastPositionByDeviceIdAndDate(car.getDeviceId(), date);
			Position position = positions.get(0);
			LastPositionDTO lastposition = new LastPositionDTO();
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
	public List<HistoryDTO> fetchHistoryByAgencyId(Long id) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<HistoryDTO> historys = new ArrayList<HistoryDTO>();
		for(int i=0; i<cars.size(); i++) {
			HistoryDTO hystory = new HistoryDTO();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position position = lasts.get(0);
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
	public List<Location> fetchCarZoneByAgencyIdAndRank(Long id, Integer rank) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<cars.size(); i++) {
			Location location = new Location();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position position = lasts.get(0);
			Zone zone = zoneRepository.fetchZoneByCarIdAndRank(id, rank);
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(position.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			location.setIsinzone(0);
			location.setSpeed(position.getSpeed());
			location.setLabelzone(zone.getLabel());
			locations.add(location);
		}
		return locations;
	}

	@Override
	public List<HistoryLocationDTO> fetchHistoryCarLocationsByCarIdAndDate(Long id, String date) {
		List<HistoryLocationDTO> historylocations = new ArrayList<HistoryLocationDTO>();
		Car car = carRepository.fetchCarById(id);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size() ;i++){
			Position position = positions.get(i);
			HistoryLocationDTO historylocation = new HistoryLocationDTO();
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
	public List<Location> fetchCarMaxSpeedByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<cars.size(); i++) {
			Location location = new Location();
			Car car = cars.get(i);
			List<Position> maxspeeds = positionRepository.fetchMaxSpeedDeviceIdAndDate(date, car.getDeviceId());
			Position maxspeed = maxspeeds.get(0);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			//location.setTotaldistance(250);
			location.setMaxspeed(maxspeed.getSpeed());
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<Location> fetchCarMaxCourseByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			Location location = new Location();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			//location.setTotaldistance(250);
			location.setTotalcourse(course);
			location.setDailycourse(course/10);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<Location> fetchCarFuelPrincipaleByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			Location location = new Location();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			//location.setTotaldistance(250);
			location.setPrinfuelcons(course);
			locations.add(location);
		}
		return locations;
	}

	@Override
	public List<Location> fetchCarFuelSecondaireByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			Location location = new Location();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			location.setSeconfuelcons(course);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<Location> fetchCarTemperatureMoByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			Location location = new Location();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			location.setMaxtemperature(12.0);
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<Location> fetchCarTemperatureFrByAgencyId(Long id, String date) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<cars.size(); i++) {
			double course = 0.0;
			Location location = new Location();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position last = lasts.get(0);
			List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
            for(int j=0; j<positions.size(); j++) {
            	course = course + positions.get(j).getCourse();
            }
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(last.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			location.setMaxtemperature(15.0);
			locations.add(location);
		}
		return locations;
	}
}
