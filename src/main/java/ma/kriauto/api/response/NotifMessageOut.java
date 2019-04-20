package ma.kriauto.api.response;

public class NotifMessageOut {
	
	private String hour;
	private String message;
	
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "NotifMessageOut [hour=" + hour + ", message=" + message + "]";
	}
}
