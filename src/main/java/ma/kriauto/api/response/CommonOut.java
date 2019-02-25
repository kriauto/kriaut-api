package ma.kriauto.api.response;

public class CommonOut {
	
	private Long   carid;
	private String mark;
	private String model;
	private String immatriculation;
	private String htmlColor;
	
	public Long getCarid() {
		return carid;
	}
	public void setCarid(Long carid) {
		this.carid = carid;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getImmatriculation() {
		return immatriculation;
	}
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}
	public String getHtmlColor() {
		return htmlColor;
	}
	public void setHtmlColor(String htmlColor) {
		this.htmlColor = htmlColor;
	}
	
	@Override
	public String toString() {
		return "CommonOut [carid=" + carid + ", mark=" + mark + ", model=" + model + ", immatriculation="
				+ immatriculation + ", htmlColor=" + htmlColor + "]";
	}
}
