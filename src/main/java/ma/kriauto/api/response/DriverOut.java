package ma.kriauto.api.response;

public class DriverOut extends CommonOut {

	private Double  drivingduration;
	private Integer isrolling;
	
	public Double getDrivingduration() {
		return drivingduration;
	}
	public void setDrivingduration(Double drivingduration) {
		this.drivingduration = drivingduration;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "DriverOut [drivingduration=" + drivingduration + ", isrolling=" + isrolling + ", getCarid()="
				+ getCarid() + ", getMark()=" + getMark() + ", getModel()=" + getModel() + ", getImmatriculation()="
				+ getImmatriculation() + ", getHtmlColor()=" + getHtmlColor() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	
}
