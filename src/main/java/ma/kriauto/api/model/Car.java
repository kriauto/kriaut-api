package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "api_car")
public class Car {
	
	  @Id
	  private Long id;
	  
	  @Column(name="mark")
	  private String mark;
	  
	  @Column(name="model")
	  private String model;
	  
	  @Column(name="immatriculation")
	  private String immatriculation;
	  
	  @Column(name="htmlcolor")
	  private String htmlColor;
	  
	  @Column(name="agencyid")
	  private Long agencyId;
	  
	  @Column(name="consumption")
	  private Float consumption;
	  
	  @Column(name="deviceid")
	  private Integer deviceId;
	  
	  @Transient
	  private String date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getHtmlColor() {
		return htmlColor;
	}

	public void setHtmlColor(String htmlColor) {
		this.htmlColor = htmlColor;
	}

	public Long getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(Long agencyId) {
		this.agencyId = agencyId;
	}

	public Float getConsumption() {
		return consumption;
	}

	public void setConsumption(Float consumption) {
		this.consumption = consumption;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", mark=" + mark + ", model=" + model + ", immatriculation=" + immatriculation
				+ ", htmlColor=" + htmlColor + ", agencyId=" + agencyId + ", consumption=" + consumption + ", deviceId="
				+ deviceId + ", date=" + date + "]";
	}
}
