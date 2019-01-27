package ma.kriauto.api.dto;

public class Location {
	
	private String mark;
	private String model;
	private String immatriculation;
	private String htmlColor;
	private String address;
	private String hour;
	private Integer markertype;
	private Integer markerdisplay;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public Integer getMarkertype() {
		return markertype;
	}
	public void setMarkertype(Integer markertype) {
		this.markertype = markertype;
	}
	public Integer getMarkerdisplay() {
		return markerdisplay;
	}
	public void setMarkerdisplay(Integer markerdisplay) {
		this.markerdisplay = markerdisplay;
	}
	@Override
	public String toString() {
		return "Location [mark=" + mark + ", model=" + model
				+ ", immatriculation=" + immatriculation + ", htmlColor=" + htmlColor
				+ ", address=" + address + ", hour=" + hour + ", markertype="
				+ markertype + ", markerdisplay=" + markerdisplay + "]";
	}
}
