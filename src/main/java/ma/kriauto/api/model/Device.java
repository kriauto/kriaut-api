package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "getway_device")
public class Device extends Audit {
	
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="generator_device")
	@SequenceGenerator(name="generator_device",sequenceName="getway_device_sequence",allocationSize=1)
    private Long id;

    @Column
    private Long userid;

    @Column
    private String identifier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return "Device [id=" + id + ", userid=" + userid + ", identifier="
				+ identifier + "]";
	}
}
