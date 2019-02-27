package ma.kriauto.api.request;

public class DataIn {
	
	private Long carid;
	private String date;
	private String  type;
	
	public Long getCarid() {
		return carid;
	}
	public void setCarid(Long carid) {
		this.carid = carid;
	}
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
	
	@Override
	public String toString() {
		return "DataIn [carid=" + carid + ", date=" + date + ", type=" + type + "]";
	}
	
}
