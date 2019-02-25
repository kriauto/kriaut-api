package ma.kriauto.api.response;

public class LastPositionOut extends CommonOut {
	
	private Double speed;
	private String date;
	private String hour;
	private Integer markertype;
	private Double latitude;
	private Double longitude;
	
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public Integer getMarkertype() {
		return markertype;
	}
	public void setMarkertype(Integer markertype) {
		this.markertype = markertype;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "LastPositionDTO [speed=" + speed + ", date=" + date + ", hour=" + hour
				+ ", markertype=" + markertype + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", getSpeed()=" + getSpeed() + ", getDate()=" + getDate()
				+ ", getHour()=" + getHour() + ", getMarkertype()=" + getMarkertype() + ", getLatitude()="
				+ getLatitude() + ", getLongitude()=" + getLongitude() + "]";
	}
	
}
