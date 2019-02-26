package ma.kriauto.api.response;

public class ProfileOut {
	
    private Long   id;
    private String login;
    private String password;
    private String name;
    private String phone;
    private String mail;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	@Override
	public String toString() {
		return "ProfileOut [id=" + id + ", login=" + login + ", password=" + password + ", name=" + name + ", phone="
				+ phone + ", mail=" + mail + "]";
	}
}
