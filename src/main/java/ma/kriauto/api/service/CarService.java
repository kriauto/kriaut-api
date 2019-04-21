package ma.kriauto.api.service;

import java.util.List;

import ma.kriauto.api.model.ActiveNotif;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Contact;
import ma.kriauto.api.model.Parameter;
import ma.kriauto.api.model.Zone;
import ma.kriauto.api.response.AccountOut;
import ma.kriauto.api.response.DetailOut;
import ma.kriauto.api.response.DoorOut;
import ma.kriauto.api.response.DriverOut;
import ma.kriauto.api.response.FuelOut;
import ma.kriauto.api.response.HistoryLocationOut;
import ma.kriauto.api.response.HistoryOut;
import ma.kriauto.api.response.LastPositionOut;
import ma.kriauto.api.response.MaxCourseOut;
import ma.kriauto.api.response.MaxSpeedOut;
import ma.kriauto.api.response.MaxTemperatureOut;
import ma.kriauto.api.response.NotifMessageOut;
import ma.kriauto.api.response.NotificationOut;
import ma.kriauto.api.response.ParametersOut;
import ma.kriauto.api.response.StartStopOut;
import ma.kriauto.api.response.ZoneOut;

public interface CarService {
	
	/*** common ***/
	public List<Car> fetchAllCarByAgencyId(Long id);
	public Car fetchCarById(Long id);
	public Car save(Car car);
	
	/*** menu acces ***/
	public List<LastPositionOut> fetchLastPositionByAgencyIdAndDate(Long id, String date);
	public List<HistoryOut> fetchHistoryByAgencyId(Long id);
	public List<MaxSpeedOut> fetchCarMaxSpeedByAgencyId(Long id, String date);
	public List<MaxCourseOut> fetchCarMaxCourseByAgencyId(Long id, String date);
	public List<FuelOut> fetchCarFuelPrincipaleByAgencyId(Long id, String date);
	public List<FuelOut> fetchCarFuelSecondaireByAgencyId(Long id, String date);
	public List<MaxTemperatureOut> fetchCarTemperatureMoByAgencyId(Long id, String date);
	public List<MaxTemperatureOut> fetchCarTemperatureFrByAgencyId(Long id, String date);
	public List<ZoneOut> fetchCarZoneByAgencyIdAndNumber(Long id, Integer number);
	public List<NotificationOut> fetchCarNotificationConsulByAgencyId(Long id, String date);
	public List<NotificationOut> fetchCarNotificationConfigByAgencyId(Long id, String date);
	public List<DoorOut> fetchCarDoorByAgencyId(Long id, String date);
	public List<DriverOut> fetchCarDriverByAgencyId(Long id, String date);
	public List<ParametersOut> fetchCarParametersByAgencyId(Long id, String date);
	public List<StartStopOut> fetchCarStartStopByAgencyId(Long id, String date);
	public AccountOut fetchAccountByAgencyId(Long id);
	public List<Contact> fetchContacts();
	
	/*** data by car access ***/
	public List<HistoryLocationOut> fetchHistoryCarLocationsByCarIdAndDate(Long id, String date);
	public DetailOut fetchMaxSpeedByCarIdAndDate(Long id, String date);
	public DetailOut fetchCourseByCarIdAndDate(Long id, String date);
	public DetailOut fetchCarFuelPrincipaleByCarIdAndDate(Long id, String date);
	public DetailOut fetchCarFuelSecondaireByCarIdAndDate(Long id, String date);
	public List<NotifMessageOut> fetchCarNotificationMessageByCarIdAndDate(Long id, String date);
	public ActiveNotif fetchCarActiveNotifByCarId(Long id);
	public Zone fetchCarZoneByCarIdAndNumber(Long id, Integer number);
	public DetailOut fetchCarTemperatureMoByCarIdAndDate(Long id, String date);
	public DetailOut fetchCarTemperatureFrByCarIdAndDate(Long id, String date);
	public DetailOut fetchCarDoorByCarIdAndDate(Long id, String date);
	public DetailOut fetchCarDriverByCarIdAndDate(Long id, String date);
	public Parameter fetchCarParametersByCarId(Long id);
	
	
	
	
	
	
}
