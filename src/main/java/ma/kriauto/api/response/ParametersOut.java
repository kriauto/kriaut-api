package ma.kriauto.api.response;

public class ParametersOut extends CommonOut  {
	
	private Integer parametersnumber;
	private Integer isrolling;
	
	public Integer getParametersnumber() {
		return parametersnumber;
	}
	public void setParametersnumber(Integer parametersnumber) {
		this.parametersnumber = parametersnumber;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "ParametersOut [parametersnumber=" + parametersnumber + ", isrolling=" + isrolling + ", getCarid()="
				+ getCarid() + ", getMark()=" + getMark() + ", getModel()=" + getModel() + ", getImmatriculation()="
				+ getImmatriculation() + ", getHtmlColor()=" + getHtmlColor() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
