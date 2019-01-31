package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_zone")
public class Zone {
	
	  @Id
	  private Long id;
	  
	  @Column(name="carid")
	  private Long carid;
	  
	  @Column(name="rank")
	  private Integer rank;
	  
	  @Column(name="label")
	  private String label;
	  
	  @Column(name="lat1")
	  private Double lat1;
	  
	  @Column(name="lon1")
	  private Double lon1;
	  
	  @Column(name="lat2")
	  private Double lat2;
	  
	  @Column(name="lon2")
	  private Double lon2;
	  
	  @Column(name="lat3")
	  private Double lat3;
	  
	  @Column(name="lon3")
	  private Double lon3;
	  
	  @Column(name="lat4")
	  private Double lat4;
	  
	  @Column(name="lon4")
	  private Double lon4;
	  
	  @Column(name="lat5")
	  private Double lat5;
	  
	  @Column(name="lon5")
	  private Double lon5;
	  
	  @Column(name="lat6")
	  private Double lat6;
	  
	  @Column(name="lon6")
	  private Double lon6;
	  
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCarid() {
		return carid;
	}
	public void setCarid(Long carid) {
		this.carid = carid;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Double getLat1() {
		return lat1;
	}
	public void setLat1(Double lat1) {
		this.lat1 = lat1;
	}
	public Double getLon1() {
		return lon1;
	}
	public void setLon1(Double lon1) {
		this.lon1 = lon1;
	}
	public Double getLat2() {
		return lat2;
	}
	public void setLat2(Double lat2) {
		this.lat2 = lat2;
	}
	public Double getLon2() {
		return lon2;
	}
	public void setLon2(Double lon2) {
		this.lon2 = lon2;
	}
	public Double getLat3() {
		return lat3;
	}
	public void setLat3(Double lat3) {
		this.lat3 = lat3;
	}
	public Double getLon3() {
		return lon3;
	}
	public void setLon3(Double lon3) {
		this.lon3 = lon3;
	}
	public Double getLat4() {
		return lat4;
	}
	public void setLat4(Double lat4) {
		this.lat4 = lat4;
	}
	public Double getLon4() {
		return lon4;
	}
	public void setLon4(Double lon4) {
		this.lon4 = lon4;
	}
	public Double getLat5() {
		return lat5;
	}
	public void setLat5(Double lat5) {
		this.lat5 = lat5;
	}
	public Double getLon5() {
		return lon5;
	}
	public void setLon5(Double lon5) {
		this.lon5 = lon5;
	}
	public Double getLat6() {
		return lat6;
	}
	public void setLat6(Double lat6) {
		this.lat6 = lat6;
	}
	public Double getLon6() {
		return lon6;
	}
	public void setLon6(Double lon6) {
		this.lon6 = lon6;
	}
	
	@Override
	public String toString() {
		return "Zone [id=" + id + ", carid=" + carid + ", rank=" + rank + ", label=" + label + ", lat1=" + lat1
				+ ", lon1=" + lon1 + ", lat2=" + lat2 + ", lon2=" + lon2 + ", lat3=" + lat3 + ", lon3=" + lon3
				+ ", lat4=" + lat4 + ", lon4=" + lon4 + ", lat5=" + lat5 + ", lon5=" + lon5 + ", lat6=" + lat6
				+ ", lon6=" + lon6 + "]";
	}
}
