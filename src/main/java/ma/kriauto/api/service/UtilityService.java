package ma.kriauto.api.service;

import java.sql.Timestamp;

public interface UtilityService {
	
	public String getHhMmSsFromFixTime(Timestamp fixtime);
	public String getHhFromFixTime(Timestamp fixtime);
	public String getYyyyMmDdFromFixTime(Timestamp fixtime);
	public String getAddress(Double Lat, Double Lng);

}
