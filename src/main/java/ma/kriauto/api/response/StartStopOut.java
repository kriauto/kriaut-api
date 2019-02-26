package ma.kriauto.api.response;

public class StartStopOut extends CommonOut {
	
	private Integer status;
	private Integer action;
	private Integer isrolling;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "StartStopOut [status=" + status + ", action=" + action + ", isrolling=" + isrolling + ", getCarid()="
				+ getCarid() + ", getMark()=" + getMark() + ", getModel()=" + getModel() + ", getImmatriculation()="
				+ getImmatriculation() + ", getHtmlColor()=" + getHtmlColor() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
