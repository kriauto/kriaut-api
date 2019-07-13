package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_parameter")
public class Parameter {
	
	@Id
	private Long id;
	  
	@Column(name="carid")
	private Long carid;
	  
	@Column(name="techcontrol")
	private String techcontrol;
	  
	@Column(name="emptyingkm")
	private Double emptyingkm;
	  
    @Column(name="insuranceend")
	private String insuranceend;
	  
	@Column(name="circulationend")
	private String circulationend;
	  
	@Column(name="maxspeed")
	private Double maxspeed;
	  
	@Column(name="maxcourse")
	private Double maxcourse;
	  
	@Column(name="maxcarburantpri")
	private Double maxcarburantpri;
	  
	@Column(name="maxcarburantsec")
	private Double maxcarburantsec;
	  
	@Column(name="mincarburantpri")
	private Double mincarburantpri;
	  
	@Column(name="mincarburantsec")
	private Double mincarburantsec;
	  
	@Column(name="maxtempengine")
	private Double maxtempengine;
	  
	@Column(name="mintempengine")
	private Double mintempengine;
	  
	@Column(name="maxtempfrigot")
	private Double maxtempfrigot;
	  
	@Column(name="mintempfrigot")
	private Double mintempfrigot;

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

	public String getTechcontrol() {
		return techcontrol;
	}

	public void setTechcontrol(String techcontrol) {
		this.techcontrol = techcontrol;
	}

	public Double getEmptyingkm() {
		return emptyingkm;
	}

	public void setEmptyingkm(Double emptyingkm) {
		this.emptyingkm = emptyingkm;
	}

	public String getInsuranceend() {
		return insuranceend;
	}

	public void setInsuranceend(String insuranceend) {
		this.insuranceend = insuranceend;
	}

	public String getCirculationend() {
		return circulationend;
	}

	public void setCirculationend(String circulationend) {
		this.circulationend = circulationend;
	}

	public Double getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Double maxspeed) {
		this.maxspeed = maxspeed;
	}

	public Double getMaxcourse() {
		return maxcourse;
	}

	public void setMaxcourse(Double maxcourse) {
		this.maxcourse = maxcourse;
	}

	public Double getMaxcarburantpri() {
		return maxcarburantpri;
	}

	public void setMaxcarburantpri(Double maxcarburantpri) {
		this.maxcarburantpri = maxcarburantpri;
	}

	public Double getMaxcarburantsec() {
		return maxcarburantsec;
	}

	public void setMaxcarburantsec(Double maxcarburantsec) {
		this.maxcarburantsec = maxcarburantsec;
	}

	public Double getMincarburantpri() {
		return mincarburantpri;
	}

	public void setMincarburantpri(Double mincarburantpri) {
		this.mincarburantpri = mincarburantpri;
	}

	public Double getMincarburantsec() {
		return mincarburantsec;
	}

	public void setMincarburantsec(Double mincarburantsec) {
		this.mincarburantsec = mincarburantsec;
	}

	public Double getMaxtempengine() {
		return maxtempengine;
	}

	public void setMaxtempengine(Double maxtempengine) {
		this.maxtempengine = maxtempengine;
	}

	public Double getMintempengine() {
		return mintempengine;
	}

	public void setMintempengine(Double mintempengine) {
		this.mintempengine = mintempengine;
	}

	public Double getMaxtempfrigot() {
		return maxtempfrigot;
	}

	public void setMaxtempfrigot(Double maxtempfrigot) {
		this.maxtempfrigot = maxtempfrigot;
	}

	public Double getMintempfrigot() {
		return mintempfrigot;
	}

	public void setMintempfrigot(Double mintempfrigot) {
		this.mintempfrigot = mintempfrigot;
	}


	@Override
	public String toString() {
		return "Parameter [id=" + id + ", carid=" + carid + ", techcontrol="
				+ techcontrol + ", emptyingkm=" + emptyingkm
				+ ", insuranceend=" + insuranceend + ", circulationend="
				+ circulationend + ", maxspeed=" + maxspeed + ", maxcourse="
				+ maxcourse + ", maxcarburantpri=" + maxcarburantpri
				+ ", maxcarburantsec=" + maxcarburantsec + ", mincarburantpri="
				+ mincarburantpri + ", mincarburantsec=" + mincarburantsec
				+ ", maxtempengine=" + maxtempengine + ", mintempengine="
				+ mintempengine + ", maxtempfrigot=" + maxtempfrigot
				+ ", mintempfrigot=" + mintempfrigot +  "]";
	}
}
