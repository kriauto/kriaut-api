package ma.kriauto.api.service;

import java.util.List;

import ma.kriauto.api.model.Car;
import ma.kriauto.api.response.DoorOut;
import ma.kriauto.api.response.DriverOut;
import ma.kriauto.api.response.FuelOut;
import ma.kriauto.api.response.HistoryLocationOut;
import ma.kriauto.api.response.HistoryOut;
import ma.kriauto.api.response.LastPositionOut;
import ma.kriauto.api.response.MaxCourseOut;
import ma.kriauto.api.response.MaxSpeedOut;
import ma.kriauto.api.response.MaxTemperatureOut;
import ma.kriauto.api.response.NotificationOut;
import ma.kriauto.api.response.ParametersOut;
import ma.kriauto.api.response.StartStopOut;
import ma.kriauto.api.response.ZoneOut;

public interface CarService {
	
	/*** menu acces ***/
	public List<LastPositionOut> fetchLastPositionByAgencyIdAndDate(Long id, String date);
	public List<HistoryOut> fetchHistoryByAgencyId(Long id);
	public List<HistoryLocationOut> fetchHistoryCarLocationsByCarIdAndDate(Long id, String date);
	public List<MaxSpeedOut> fetchCarMaxSpeedByAgencyId(Long id, String date);
	public List<MaxCourseOut> fetchCarMaxCourseByAgencyId(Long id, String date);
	public List<FuelOut> fetchCarFuelPrincipaleByAgencyId(Long id, String date);
	public List<FuelOut> fetchCarFuelSecondaireByAgencyId(Long id, String date);
	public List<MaxTemperatureOut> fetchCarTemperatureMoByAgencyId(Long id, String date);
	public List<MaxTemperatureOut> fetchCarTemperatureFrByAgencyId(Long id, String date);
	public List<ZoneOut> fetchCarZoneByAgencyIdAndNumber(Long id, Integer number);
	public List<NotificationOut> fetchCarNotificationByAgencyIdAndNumber(Long id, String date);
	public List<DoorOut> fetchCarDoorByAgencyIdAndNumber(Long id, String date);
	public List<DriverOut> fetchCarDriverByAgencyIdAndNumber(Long id, String date);
	public List<ParametersOut> fetchCarParametersByAgencyIdAndNumber(Long id, String date);
	public List<StartStopOut> fetchCarStartStopByAgencyIdAndNumber(Long id, String date);
	
	/*** data by car access ***/
	public List<Car> fetchAllCarByAgencyId(Long id);
	public Car fetchCarById(Long id);	
	
	
	
	
	
}
