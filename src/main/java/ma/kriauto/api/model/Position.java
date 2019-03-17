package ma.kriauto.api.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "positions")
public class Position {
	
	  @Id
	  private Integer id;
	  
	  @Column(name="protocol")
	  private String protocol;
	  
	  @Column(name="deviceid")
	  private Integer deviceid;
	  
	  @Column(name="servertime")
	  private Timestamp servertime;
	  
	  @Column(name="devicetime")
	  private Timestamp devicetime;
	  
	  @Column(name="fixtime")
	  private Timestamp fixtime;
	  
	  @Column(name="valid")
	  private Boolean valid;
	  
	  @Column(name="latitude")
	  private Double latitude;
	  
	  @Column(name="longitude")
	  private Double longitude;
	  
	  @Column(name="altitude")
	  private Double altitude;
	  
	  @Column(name="speed")
	  private Double speed;
	  
	  @Column(name="course")
	  private Double course;
	  
	  @Column(name="address")
	  private String address;
	  
	  @Column(name="attributes")
	  private String attributes;
	  
	  @Column(name="accuracy")
	  private Double accuracy;
	  
	  @Column(name="network")
	  private String network;
	  
	  @Transient
	  private String date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public Integer getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Integer deviceid) {
		this.deviceid = deviceid;
	}

	public Timestamp getServertime() {
		return servertime;
	}

	public void setServertime(Timestamp servertime) {
		this.servertime = servertime;
	}

	public Timestamp getDevicetime() {
		return devicetime;
	}

	public void setDevicetime(Timestamp devicetime) {
		this.devicetime = devicetime;
	}

	public Timestamp getFixtime() {
		return fixtime;
	}

	public void setFixtime(Timestamp fixtime) {
		this.fixtime = fixtime;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public Double getCourse() {
		return course;
	}

	public void setCourse(Double course) {
		this.course = course;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public Double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(Double accuracy) {
		this.accuracy = accuracy;
	}

	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", protocol=" + protocol + ", deviceid="
				+ deviceid + ", servertime=" + servertime + ", devicetime="
				+ devicetime + ", fixtime=" + fixtime + ", valid=" + valid
				+ ", latitude=" + latitude + ", longitude=" + longitude
				+ ", altitude=" + altitude + ", speed=" + speed + ", course="
				+ course + ", address=" + address + ", attributes="
				+ attributes + ", accuracy=" + accuracy + ", network="
				+ network + "]";
	}
}
