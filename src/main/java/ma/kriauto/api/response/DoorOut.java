package ma.kriauto.api.response;

public class DoorOut extends CommonOut {

	private Integer openingnumber;
	private Integer closingnumber;
	private Integer isrolling;
	 
	public Integer getOpeningnumber() {
		return openingnumber;
	}
	public void setOpeningnumber(Integer openingnumber) {
		this.openingnumber = openingnumber;
	}
	public Integer getClosingnumber() {
		return closingnumber;
	}
	public void setClosingnumber(Integer closingnumber) {
		this.closingnumber = closingnumber;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "DoorOut [openingnumber=" + openingnumber + ", closingnumber=" + closingnumber + ", isrolling="
				+ isrolling + ", getCarid()=" + getCarid() + ", getMark()=" + getMark() + ", getModel()=" + getModel()
				+ ", getImmatriculation()=" + getImmatriculation() + ", getHtmlColor()=" + getHtmlColor()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
}
