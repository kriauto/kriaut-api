package ma.kriauto.api.service;

import java.sql.Timestamp;

public interface UtilityService {
	
	public String getHourFromFixTime(Timestamp fixtime);
	public String getDateFromFixTime(Timestamp fixtime);
	public String getAddress(Double Lat, Double Lng);

}
