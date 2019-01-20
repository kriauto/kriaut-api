package ma.kriauto.api.dto;

import ma.kriauto.api.model.User;


public class UserDTO{
	
    private Long id;
    private String login;
    private String password;
    private String identifier;
    private String token;

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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", login=" + login + ", password="
				+ password + ", identifier=" + identifier + ", token=" + token
				+ "]";
	}
}
