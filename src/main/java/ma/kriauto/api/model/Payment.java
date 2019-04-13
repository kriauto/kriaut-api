package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_payment")
public class Payment {
	
	@Id
	private Long id;
	  
	@Column(name="rib")
	private String rib;
	  
	@Column(name="socialreason")
	private String socialreason;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRib() {
		return rib;
	}

	public void setRib(String rib) {
		this.rib = rib;
	}

	public String getSocialreason() {
		return socialreason;
	}

	public void setSocialreason(String socialreason) {
		this.socialreason = socialreason;
	}

	@Override
	public String toString() {
		return "Payment [id=" + id + ", rib=" + rib + ", socialreason="
				+ socialreason + "]";
	}
}
