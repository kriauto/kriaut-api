package ma.kriauto.api.model;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
	  
	  @Column(name="simnumber")
	  private String simnumber;
	  
	  @Column(name="creationdate")
	  private Date creationdate;
	  
	  @Column(name="totaldistance")
	  private Double totaldistance;
	  
	  @Column(name="indexemptyingkm")
	  private Integer indexemptyingkm;
	  
	  @Column(name="inzoneone")
	  private Boolean inzoneone;
	  
	  @Column(name="inzonetwo")
	  private Boolean inzonetwo;
	  
	  @Column(name="lastlat")
	  private Double lastlat;
	  
	  @Column(name="lastlon")
	  private Double lastlon;
	  
	  @Column(name="lastspeed")
	  private Double lastspeed;
	  
	  @Column(name="lastfixtime")
	  private Timestamp lastfixtime;
	  
	  @Column(name="dailydistance")
	  private Double dailydistance;
	  
	  @Column(name="color")
	  private String color;

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

	public String getSimnumber() {
		return simnumber;
	}

	public void setSimnumber(String simnumber) {
		this.simnumber = simnumber;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Double getTotaldistance() {
		return totaldistance;
	}

	public void setTotaldistance(Double totaldistance) {
		this.totaldistance = totaldistance;
	}

	public Integer getIndexemptyingkm() {
		return indexemptyingkm;
	}

	public void setIndexemptyingkm(Integer indexemptyingkm) {
		this.indexemptyingkm = indexemptyingkm;
	}

	public Boolean getInzoneone() {
		return inzoneone;
	}

	public void setInzoneone(Boolean inzoneone) {
		this.inzoneone = inzoneone;
	}

	public Boolean getInzonetwo() {
		return inzonetwo;
	}

	public void setInzonetwo(Boolean inzonetwo) {
		this.inzonetwo = inzonetwo;
	}

	public Double getLastlat() {
		return lastlat;
	}

	public void setLastlat(Double lastlat) {
		this.lastlat = lastlat;
	}

	public Double getLastlon() {
		return lastlon;
	}

	public void setLastlon(Double lastlon) {
		this.lastlon = lastlon;
	}

	public Double getLastspeed() {
		return lastspeed;
	}

	public void setLastspeed(Double lastspeed) {
		this.lastspeed = lastspeed;
	}

	public Timestamp getLastfixtime() {
		return lastfixtime;
	}

	public void setLastfixtime(Timestamp lastfixtime) {
		this.lastfixtime = lastfixtime;
	}

	public Double getDailydistance() {
		return dailydistance;
	}

	public void setDailydistance(Double dailydistance) {
		this.dailydistance = dailydistance;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", mark=" + mark + ", model=" + model + ", immatriculation=" + immatriculation
				+ ", htmlColor=" + htmlColor + ", agencyId=" + agencyId + ", consumption=" + consumption + ", deviceId="
				+ deviceId + ", simnumber=" + simnumber + ", creationdate=" + creationdate + ", totaldistance="
				+ totaldistance + ", indexemptyingkm=" + indexemptyingkm + ", inzoneone=" + inzoneone + ", inzonetwo="
				+ inzonetwo + ", lastlat=" + lastlat + ", lastlon=" + lastlon + ", lastspeed=" + lastspeed
				+ ", lastfixtime=" + lastfixtime + ", dailydistance=" + dailydistance + ", color=" + color + "]";
	} 
}
