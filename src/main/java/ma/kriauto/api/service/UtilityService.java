package ma.kriauto.api.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;

import ma.kriauto.api.model.Profile;
import ma.kriauto.api.model.Zone;



public interface UtilityService {
	
	public String getHhMmSsFromFixTime(Timestamp fixtime);
	public String getHhFromFixTime(Timestamp fixtime);
	public String getYyyyMmDdFromFixTime(Timestamp fixtime);
	public String getAddress(Double Lat, Double Lng);
	public String hash256Profile(Profile profile);
	public boolean isInZone(Zone zone, double lat, double lon) ;
	public long getDiffSecondesFromNow(Timestamp fixtime) throws ParseException ;

}
