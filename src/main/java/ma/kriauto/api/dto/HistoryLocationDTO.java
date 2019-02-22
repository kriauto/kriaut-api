package ma.kriauto.api.dto;

public class HistoryLocationDTO {
	
	private Double speed;
	private String hour;
	private Integer markertype;
	private Integer markerdisplay;
	private Double latitude;
	private Double longitude;
	
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
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
	public Integer getMarkerdisplay() {
		return markerdisplay;
	}
	public void setMarkerdisplay(Integer markerdisplay) {
		this.markerdisplay = markerdisplay;
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
		return "HistoryLocationDTO [speed=" + speed + ", hour=" + hour + ", markertype=" + markertype
				+ ", markerdisplay=" + markerdisplay + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
