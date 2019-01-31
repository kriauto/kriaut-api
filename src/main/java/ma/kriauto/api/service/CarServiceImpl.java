package ma.kriauto.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Location> fetchCarHistoryByAgencyId(Long id) {
		List<Car> cars = carRepository.fetchAllCarByAgencyId(id);
		List<Location> locations = new ArrayList<Location>();
		for(int i=0; i<cars.size(); i++) {
			Location location = new Location();
			Car car = cars.get(i);
			List<Position> lasts = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId());
			Position position = lasts.get(0);
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(position.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			location.setTotaldistance(250);
			locations.add(location);
		}
		return locations;
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
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHtmlColor(car.getHtmlColor());
			location.setIsrolling(position.getSpeed() > 0 ? 0 : 1);
			location.setCarid(car.getId());
			location.setIsinzone(0);
			locations.add(location);
		}
		return locations;
	}

	@Override
	public List<Location> fetchLocationsByCarId(Long id, String date) {
		List<Location> locations = new ArrayList<Location>();
		Car car = carRepository.fetchCarById(id);
		List<Position> positions = positionRepository.fetchAllPositionByDeviceIdAndDate(date, car.getDeviceId());
		for(int i=0; i<positions.size() ;i++){
			Position position = positions.get(i);
			Location location = new Location();
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHour(utilityService.getHourFromFixTime(position.getFixtime()));
			location.setDate(utilityService.getDateFromFixTime(position.getFixtime()));
			location.setMarkertype(0);
			location.setMarkerdisplay(0);
			location.setLat(position.getLatitude());
			location.setLon(position.getLongitude());
			locations.add(location);
		}
		return locations;
	}
	
	@Override
	public List<Location> fetchLocationsByAgencyIdAndDate(Long id, String date) {
		List<Location> locations = new ArrayList<Location>();
		Agency agency = agencyRepository.fetchAgencyByProfileId(id);
		List<Car> cars = carRepository.fetchAllCarByAgencyId(agency.getId());
		for(int i=0; i<cars.size() ;i++){
			Car car = cars.get(i);
			List<Position> positions = positionRepository.fetchLastPositionByDeviceIdAndDate(car.getDeviceId(), date);
			Position position = positions.get(0);
			Location location = new Location();
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			//location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHour(utilityService.getHourFromFixTime(position.getFixtime()));
			location.setDate(utilityService.getDateFromFixTime(position.getFixtime()));
			location.setMarkertype(0);
			location.setMarkerdisplay(0);
			location.setLat(position.getLatitude());
			location.setLon(position.getLongitude());
			locations.add(location);
		}
		return locations;
	}
}
