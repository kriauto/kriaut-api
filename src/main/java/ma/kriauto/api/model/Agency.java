package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "api_agency")
public class Agency {
    
    @Id
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="city")
    private String city;
    
    @Column(name="address")
    private String address;
    
    @Column(name="phone")
    private String phone;
    
    @Column(name="fax")
    private String fax;
    
    @Column(name="idprofile")
    private Long idProfile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Long getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(Long idProfile) {
		this.idProfile = idProfile;
	}

	@Override
	public String toString() {
		return "Agency [id=" + id + ", name=" + name + ", city=" + city
				+ ", address=" + address + ", phone=" + phone + ", fax=" + fax
				+ ", idProfile=" + idProfile + "]";
	}
}
