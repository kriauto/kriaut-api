package ma.kriauto.api.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

@Service
public class UtilityServiceImpl implements UtilityService {
	
	public final static String AUTH_KEY_FCM = "AAAAGCh6u8g:APA91bGM-jPzZI1BIasa0IdW6SUNCXAa78mWXI0mACvYXmawU5ptyT3iCIjcEhS1_b7V6XaEwsuL-rppJ_AgH_O1Q_XBXttUYoVIlwVamJEr6grmo4qxWGWPMELZar1bRsXCpJCaEaFq";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	@Override
	public String getHourFromFixTime(Timestamp fixtime) {
		// TODO Auto-generated method stub
		SimpleDateFormat std = new SimpleDateFormat("HH:mm:ss");
		String hour = std.format(fixtime);
		return hour;
	}
	
	@Override
	public String getAddress(Double Lat, Double Lng){
		GeoApiContext gtx = new GeoApiContext().setApiKey("AIzaSyD-w27Lhidw00LPBW7UWHp1TBPv4O3v650");
		GeocodingResult[] gResp = null ;
		try 
		  {
		    gResp = GeocodingApi.newRequest(gtx).latlng(new LatLng(Lat, Lng)).await();
		    if(null != gResp && gResp[0] != null)
		      System.out.println(gResp[0].formattedAddress);
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		String address = (null != gResp && gResp[0] != null ? gResp[0].formattedAddress : "Unnamed Road, Morocco");
	    return address;
	}

	@Override
	public String getDateFromFixTime(Timestamp fixtime) {
		// TODO Auto-generated method stub
		SimpleDateFormat std = new SimpleDateFormat("YYYY-MM-DD");
		String date = std.format(fixtime);
		return date;
	}

}
