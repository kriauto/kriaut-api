package ma.kriauto.api.response;

public class MaxSpeedOut extends CommonOut {
	
	private Double speed;
	private Integer isrolling;

	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "MaxSpeedOut [speed=" + speed + ", isrolling=" + isrolling + "]";
	}
}
