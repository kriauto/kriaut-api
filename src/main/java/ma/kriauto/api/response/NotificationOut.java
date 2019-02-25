package ma.kriauto.api.response;

public class NotificationOut extends CommonOut {
	
	private Integer number;
	private Integer isrolling;
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "NotificationOut [number=" + number + ", isrolling=" + isrolling + "]";
	}
}
