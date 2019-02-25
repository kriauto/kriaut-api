package ma.kriauto.api.response;

public class ZoneOut extends CommonOut {
	
	private Boolean inzone;
	private Integer isrolling;
	
	public Boolean getInzone() {
		return inzone;
	}
	public void setInzone(Boolean inzone) {
		this.inzone = inzone;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "ZoneOut [inzone=" + inzone + ", isrolling=" + isrolling + "]";
	}
}
