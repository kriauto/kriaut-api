package ma.kriauto.api.dto;

public class HistoryDTO extends CommonDTO {
	
	private Integer isrolling;
	private Double latitude;
	private Double longitude;
	
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
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
		return "HistoryDTO [isrolling=" + isrolling + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", getIsrolling()=" + getIsrolling() + ", getLatitude()=" + getLatitude() + ", getLongitude()="
				+ getLongitude() + "]";
	}
}
