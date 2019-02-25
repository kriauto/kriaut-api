package ma.kriauto.api.response;

public class FuelOut extends CommonOut {
	
	private Double currentlevel;
	private Double dailyconsumption;
	private Integer isrolling;
	
	public Double getCurrentlevel() {
		return currentlevel;
	}
	public void setCurrentlevel(Double currentlevel) {
		this.currentlevel = currentlevel;
	}
	public Double getDailyconsumption() {
		return dailyconsumption;
	}
	public void setDailyconsumption(Double dailyconsumption) {
		this.dailyconsumption = dailyconsumption;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "FuelOut [currentlevel=" + currentlevel + ", dailyconsumption=" + dailyconsumption + ", isrolling="
				+ isrolling + "]";
	}
}
