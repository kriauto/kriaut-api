package ma.kriauto.api.response;

public class AgencyOut {
	
    private Long id;
    private String name;
    private String city;
    private String address;
    private String phone;
    private String fax;
    
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
	
	@Override
	public String toString() {
		return "AgencyOut [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", phone=" + phone
				+ ", fax=" + fax + "]";
	}
}
