package ma.kriauto.api.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ma.kriauto.api.model.ActiveNotif;
import ma.kriauto.api.model.Car;
import ma.kriauto.api.model.Contact;
import ma.kriauto.api.model.Parameter;
import ma.kriauto.api.model.Position;
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
	List<Car> fetchAllCarByAgencyIdAndStatus(Long id, Integer status);
	List<Car> fetchAllCar();
	List<Position> fetchAllPositionByDeviceIdAndPeriode(String date, Integer deviceid);
	List<Position> fetchAllPositionByDeviceIdAndDate(String date,Integer deviceid);
	void deletePositions(String date, Integer deviceid, Integer id);
	Car fetchCarById(Long id);
	Car save(Car car);
	
	/*** menu acces ***/
	List<LastPositionOut> fetchLastPositionByAgencyIdAndDate(Long id, String date) throws ParseException ;
	List<HistoryOut> fetchHistoryByAgencyId(Long id) throws ParseException ;
	List<MaxSpeedOut> fetchCarMaxSpeedByAgencyIdAndDate(Long id, String date) throws ParseException;
	List<MaxCourseOut> fetchCarMaxCourseByAgencyIdAndDate(Long id, String date) throws ParseException;
	List<FuelOut> fetchCarFuelPrincipaleByAgencyIdAndDate(Long id, String date) throws ParseException;
	List<FuelOut> fetchCarFuelSecondaireByAgencyId(Long id, String date) throws ParseException;
	List<MaxTemperatureOut> fetchCarTemperatureMoByAgencyId(Long id, String date);
	List<MaxTemperatureOut> fetchCarTemperatureFrByAgencyId(Long id, String date);
	List<ZoneOut> fetchCarZoneByAgencyIdAndNumber(Long id, Integer number) throws ParseException;
	List<NotificationOut> fetchCarNotificationConsulByAgencyId(Long id, String date) throws ParseException;
	List<NotificationOut> fetchCarNotificationConfigByAgencyId(Long id, String date) throws ParseException;
	List<DoorOut> fetchCarDoorByAgencyId(Long id, String date);
	List<DriverOut> fetchCarDriverByAgencyId(Long id, String date);
	List<ParametersOut> fetchCarParametersByAgencyId(Long id, String date) throws ParseException;
	List<StartStopOut> fetchCarStartStopByAgencyId(Long id, String date) throws ParseException;
	AccountOut fetchAccountByAgencyId(Long id);
	List<Contact> fetchContacts();
	
	/*** data by car access ***/
	List<HistoryLocationOut> fetchHistoryCarLocationsByCarIdAndDate(Long id, String date) throws ParseException;
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
	double calculateDailyDistance(Integer deviceid, String date);
	void initDailyDistance();
	void calculateTotalDistance();
	double distance(double lat1, double lon1, double lat2, double lon2, char unit);
	List<Position> fetchMaxSpeedByDeviceIdAndPeriod(String date, Integer deviceid);
	List<Position> fetchLastPositionByDeviceId(Integer deviceid);
	List<Position> fetchCarIgnition(String date, Integer deviceid);

	
	
	
	
	
	
}
