package ma.kriauto.api.dto;

public class CommonDTO {
	
	private String mark;
	private String model;
	private String immatriculation;
	private String htmlColor;
	
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
		return "CommonDTO [mark=" + mark + ", model=" + model + ", immatriculation=" + immatriculation + ", htmlColor="
				+ htmlColor + "]";
	}
}
