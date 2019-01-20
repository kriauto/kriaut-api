package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import ma.kriauto.api.dto.UserDTO;

@Entity
@Table(name = "getway_user")
public class User extends Audit {
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator_user")
	@SequenceGenerator(name="generator_user",sequenceName="getway_user_sequence",allocationSize=1)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;
    
    @Column
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
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", token=" + token + "]";
	}
}
