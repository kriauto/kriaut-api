package ma.kriauto.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import ma.kriauto.api.dto.Location;
import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Item;
import ma.kriauto.api.model.Position;
import ma.kriauto.api.model.Profile;
import ma.kriauto.api.repository.AgencyRepository;
import ma.kriauto.api.repository.CarRepository;
import ma.kriauto.api.repository.PositionRepository;
import ma.kriauto.api.repository.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		return profileRepository.fetchProfileByLogin(login);
	}

	@Override
	public Profile fetchProfileByToken(String token) {
		// TODO Auto-generated method stub
		return profileRepository.fetchProfileByToken(token);
	}

	@Override
	public Profile fetchProfileByMail(String mail) {
		// TODO Auto-generated method stub
		return profileRepository.fetchProfileByMail(mail);
	}
	
	@Override
	public Profile fetchProfileById(Long id) {
		// TODO Auto-generated method stub
		return profileRepository.fetchProfileById(id);
	}

	@Override
	public List<Location> fetchLocationsByProfileId(Long id, String date) {
		// TODO Auto-generated method stub
		List<Location> locations = new ArrayList<Location>();
		Agency agency = agencyRepository.fetchAgencyByProfileId(id);
		List<Car> cars = carRepository.fetchAllCarByAgencyId(agency.getId());
		for(int i=0; i<cars.size() ;i++){
			Car car = cars.get(i);
			List<Position> positions = positionRepository.fetchLastPositionByDeviceId(car.getDeviceId(), date);
			Position position = positions.get(0);
			Location location = new Location();
			location.setMark(car.getMark());
			location.setModel(car.getModel());
			location.setImmatriculation(car.getImmatriculation());
			location.setHtmlColor(car.getHtmlColor());
			location.setAddress(utilityService.getAddress(position.getLatitude(), position.getLongitude()));
			location.setHour(utilityService.getHourFromFixTime(position.getFixtime()));
			location.setMarkertype(0);
			location.setMarkerdisplay(0);
			locations.add(location);
		}
		return locations;
	}

//	@Override
//	public List<Item> fetchAllDatesByProfileId(Long id) {
//		// TODO Auto-generated method stub
//		Agency agency = agencyRepository.fetchAgencyByProfileId(id);
//		GregorianCalendar now = new GregorianCalendar();
//		now.add(Calendar.MONTH, -1);
//		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//		String date = formater.format(now.getTime());
//		List<Item> dates = new ArrayList<Item>();
//		List<Position> positions = positionRepository.fetchAllDatesByAgencyId(agency.getId(), date);
//		for(int i=0; i<positions.size(); i++){
//		   Position position = positions.get(i);
//		   Item item = new Item();
//		   item.setKey(utilityService.getDateFromFixTime(position.getFixtime()));
//		   item.setValue(utilityService.getDateFromFixTime(position.getFixtime()));
//		   dates.add(item);
//		}
//		return dates;
//	}
}
