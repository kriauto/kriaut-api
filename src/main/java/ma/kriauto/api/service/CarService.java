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
	List<Car> fetchAllCarByAgencyId(Long id);
	Car fetchCarById(Long id);
	Car save(Car car);
	
	/*** menu acces ***/
	List<LastPositionOut> fetchLastPositionByAgencyIdAndDate(Long id, String date);
	List<HistoryOut> fetchHistoryByAgencyId(Long id);
	List<MaxSpeedOut> fetchCarMaxSpeedByAgencyIdAndDate(Long id, String date);
	List<MaxCourseOut> fetchCarMaxCourseByAgencyIdAndDate(Long id, String date);
	List<FuelOut> fetchCarFuelPrincipaleByAgencyIdAndDate(Long id, String date);
	List<FuelOut> fetchCarFuelSecondaireByAgencyId(Long id, String date);
	List<MaxTemperatureOut> fetchCarTemperatureMoByAgencyId(Long id, String date);
	List<MaxTemperatureOut> fetchCarTemperatureFrByAgencyId(Long id, String date);
	List<ZoneOut> fetchCarZoneByAgencyIdAndNumber(Long id, Integer number);
	List<NotificationOut> fetchCarNotificationConsulByAgencyId(Long id, String date);
	List<NotificationOut> fetchCarNotificationConfigByAgencyId(Long id, String date);
	List<DoorOut> fetchCarDoorByAgencyId(Long id, String date);
	List<DriverOut> fetchCarDriverByAgencyId(Long id, String date);
	List<ParametersOut> fetchCarParametersByAgencyId(Long id, String date);
	List<StartStopOut> fetchCarStartStopByAgencyId(Long id, String date);
	AccountOut fetchAccountByAgencyId(Long id);
	List<Contact> fetchContacts();
	
	/*** data by car access ***/
	List<HistoryLocationOut> fetchHistoryCarLocationsByCarIdAndDate(Long id, String date);
	DetailOut fetchMaxSpeedByCarIdAndDate(Long id, String date);
	DetailOut fetchCourseByCarIdAndDate(Long id, String date);
	DetailOut fetchCarFuelPrincipaleByCarIdAndDate(Long id, String date);
	DetailOut fetchCarFuelSecondaireByCarIdAndDate(Long id, String date);
	List<NotifMessageOut> fetchCarNotificationMessageByCarIdAndDate(Long id, String date);
	ActiveNotif fetchCarActiveNotifByCarId(Long id);
	Zone fetchCarZoneByCarIdAndNumber(Long id, Integer number);
	DetailOut fetchCarTemperatureMoByCarIdAndDate(Long id, String date);
	DetailOut fetchCarTemperatureFrByCarIdAndDate(Long id, String date);
	DetailOut fetchCarDoorByCarIdAndDate(Long id, String date);
	DetailOut fetchCarDriverByCarIdAndDate(Long id, String date);
	Parameter fetchCarParametersByCarId(Long id);
	
	/*** batch ***/
	void calculateDailyDistance();
	void initDailyDistance();
	void calculateTotalDistance();
	double distance(double lat1, double lon1, double lat2, double lon2, char unit);

	
	
	
	
	
	
}
