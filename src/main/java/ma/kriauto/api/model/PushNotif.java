package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_pushnotif")
public class PushNotif {
	
	@Id
	private Long id;
	  
	@Column(name="pushtoken")
	private String pushToken;
	
	@Column(name="idprofile")
	private Long idProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public Long getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(Long idProfile) {
		this.idProfile = idProfile;
	}

	@Override
	public String toString() {
		return "PushNotif [id=" + id + ", pushToken=" + pushToken
				+ ", idProfile=" + idProfile + "]";
	}
}
