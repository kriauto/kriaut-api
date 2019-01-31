package ma.kriauto.api.service;

import java.util.List;

import ma.kriauto.api.dto.Location;
import ma.kriauto.api.model.Car;

public interface CarService {
	
	public List<Car> fetchAllCarByAgencyId(Long id);
	public Car fetchCarById(Long id);
	public List<Location> fetchCarHistoryByAgencyId(Long id);
	public List<Location> fetchCarZoneByAgencyIdAndRank(Long id, Integer rank);
	public List<Location> fetchLocationsByCarId(Long id, String date);
	public List<Location> fetchLocationsByAgencyIdAndDate(Long id, String date);
}
