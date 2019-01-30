package ma.kriauto.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.kriauto.api.dto.Location;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Position;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.repository.AgencyRepository;
import ma.kriauto.api.repository.CarRepository;
import ma.kriauto.api.repository.PositionRepository;
import ma.kriauto.api.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {
	
	@Autowired
    private ProfileRepository profileRepository;
	
	@Autowired
    private AgencyRepository agencyRepository;
	
	@Autowired
    private CarRepository carRepository;
	
	@Autowired
    private PositionRepository positionRepository;
	
	@Autowired
    private UtilityService utilityService;

	@Override
	public Profile fetchProfileByLogin(String login) {
		return profileRepository.fetchProfileByLogin(login);
	}

	@Override
	public Profile fetchProfileByToken(String token) {
		return profileRepository.fetchProfileByToken(token);
	}

	@Override
	public Profile fetchProfileByMail(String mail) {
		return profileRepository.fetchProfileByMail(mail);
	}
	
	@Override
	public Profile fetchProfileById(Long id) {
		return profileRepository.fetchProfileById(id);
	}

	@Override
	public List<Location> fetchLocationsByProfileId(Long id, String date) {
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
			location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHour(utilityService.getHourFromFixTime(position.getFixtime()));
			location.setDate(utilityService.getDateFromFixTime(position.getFixtime()));
			location.setMarkertype(0);
			location.setMarkerdisplay(0);
			locations.add(location);
		}
		return locations;
	}
	
	
}
