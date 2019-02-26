package ma.kriauto.api.request;

public class MenuIn {
	
	private String  date;
	private String  type;
	private Integer number;
	private Long    carid;

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Long getCarid() {
		return carid;
	}
	public void setCarid(Long carid) {
		this.carid = carid;
	}
	
	@Override
	public String toString() {
		return "MenuIn [date=" + date + ", type=" + type + ", number=" + number + ", carid=" + carid + "]";
	}
}
