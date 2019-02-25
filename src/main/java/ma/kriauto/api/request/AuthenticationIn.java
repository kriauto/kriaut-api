package ma.kriauto.api.request;

public class AuthenticationIn {
	
    private String login;
    private String password;
    private String notifToken;
    private String mail;
    
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNotifToken() {
		return notifToken;
	}
	public void setNotifToken(String notifToken) {
		this.notifToken = notifToken;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return "AuthenticationDTO [login=" + login + ", password=" + password + ", notifToken=" + notifToken + ", mail="
				+ mail + "]";
	}
    
	
    

}
