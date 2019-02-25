package ma.kriauto.api.response;

public class MaxTemperatureOut extends CommonOut {
	
	private Double temperature;
	private Integer isrolling;
	
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "MaxTemperature [temperature=" + temperature + ", isrolling=" + isrolling + "]";
	}
	
	

}
