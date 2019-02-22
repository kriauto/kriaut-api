package ma.kriauto.api.service;

import java.util.List;

import ma.kriauto.api.dto.HistoryDTO;
import ma.kriauto.api.dto.HistoryLocationDTO;
import ma.kriauto.api.dto.LastPositionDTO;
import ma.kriauto.api.dto.Location;
import ma.kriauto.api.model.Car;

public interface CarService {
	
	public List<LastPositionDTO> fetchLastPositionByAgencyIdAndDate(Long id, String date);
	public List<HistoryDTO> fetchHistoryByAgencyId(Long id);
	public List<HistoryLocationDTO> fetchHistoryCarLocationsByCarIdAndDate(Long id, String date);
	
	public List<Car> fetchAllCarByAgencyId(Long id);
	public Car fetchCarById(Long id);	
	public List<Location> fetchCarZoneByAgencyIdAndRank(Long id, Integer rank);
	public List<Location> fetchCarMaxSpeedByAgencyId(Long id, String date);
	public List<Location> fetchCarMaxCourseByAgencyId(Long id, String date);
	public List<Location> fetchCarFuelPrincipaleByAgencyId(Long id, String date);
	public List<Location> fetchCarFuelSecondaireByAgencyId(Long id, String date);
	public List<Location> fetchCarTemperatureMoByAgencyId(Long id, String date);
	public List<Location> fetchCarTemperatureFrByAgencyId(Long id, String date);
	
	
}
