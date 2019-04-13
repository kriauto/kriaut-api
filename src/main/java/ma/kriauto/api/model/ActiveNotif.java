package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "api_activnotif")
public class ActiveNotif {
	
	@Id
	private Long id;
	  
	@Column(name="carid")
	private Long carid;
	  
	@Column(name="techcontrol")
	private Boolean techcontrol;
	  
	@Column(name="emptyingkm")
	private Boolean emptyingkm;
	  
	@Column(name="insuranceend")
	private Boolean insuranceend;
	  
	@Column(name="circulationend")
	private Boolean circulationend;
	  
	@Column(name="maxspeed")
	private Boolean maxspeed;
	  
	@Column(name="maxcourse")
	private Boolean maxcourse;
	  
	@Column(name="maxcarburantpri")
	private Boolean maxcarburantpri;
	  
	@Column(name="maxcarburantsec")
	private Boolean maxcarburantsec;
	  
	@Column(name="mincarburantpri")
	private Boolean mincarburantpri;
	  
	@Column(name="mincarburantsec")
	private Boolean mincarburantsec;
	  
	@Column(name="maxtempengine")
	private Boolean maxtempengine;
	  
	@Column(name="mintempengine")
	private Boolean mintempengine;
	  
	@Column(name="maxtempfrigot")
	private Boolean maxtempfrigot;
	  
	@Column(name="mintempfrigot")
	private Boolean mintempfrigot;
	  
	@Column(name="zoneonein")
	private Boolean zoneonein;
	  
	@Column(name="zoneoneout")
	private Boolean zoneoneout;
	  
	@Column(name="zonetwoin")
	private Boolean zonetwoin;
	  
	@Column(name="techcontrol")
	private Boolean zonetwoout;
	  
	@Column(name="enginestop")
	private Boolean enginestop;
	  
	@Column(name="enginestart")
	private Boolean enginestart;
	  
	@Column(name="mail")
	private Boolean mail;
	  
	@Column(name="opendoor")
	private Boolean opendoor;
	  
	@Column(name="closedoor")
	private Boolean closedoor;
	  
	@Column(name="drivertracking")
	private Boolean drivertracking;

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

	public Boolean getTechcontrol() {
		return techcontrol;
	}

	public void setTechcontrol(Boolean techcontrol) {
		this.techcontrol = techcontrol;
	}

	public Boolean getEmptyingkm() {
		return emptyingkm;
	}

	public void setEmptyingkm(Boolean emptyingkm) {
		this.emptyingkm = emptyingkm;
	}

	public Boolean getInsuranceend() {
		return insuranceend;
	}

	public void setInsuranceend(Boolean insuranceend) {
		this.insuranceend = insuranceend;
	}

	public Boolean getCirculationend() {
		return circulationend;
	}

	public void setCirculationend(Boolean circulationend) {
		this.circulationend = circulationend;
	}

	public Boolean getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Boolean maxspeed) {
		this.maxspeed = maxspeed;
	}

	public Boolean getMaxcourse() {
		return maxcourse;
	}

	public void setMaxcourse(Boolean maxcourse) {
		this.maxcourse = maxcourse;
	}

	public Boolean getMaxcarburantpri() {
		return maxcarburantpri;
	}

	public void setMaxcarburantpri(Boolean maxcarburantpri) {
		this.maxcarburantpri = maxcarburantpri;
	}

	public Boolean getMaxcarburantsec() {
		return maxcarburantsec;
	}

	public void setMaxcarburantsec(Boolean maxcarburantsec) {
		this.maxcarburantsec = maxcarburantsec;
	}

	public Boolean getMincarburantpri() {
		return mincarburantpri;
	}

	public void setMincarburantpri(Boolean mincarburantpri) {
		this.mincarburantpri = mincarburantpri;
	}

	public Boolean getMincarburantsec() {
		return mincarburantsec;
	}

	public void setMincarburantsec(Boolean mincarburantsec) {
		this.mincarburantsec = mincarburantsec;
	}

	public Boolean getMaxtempengine() {
		return maxtempengine;
	}

	public void setMaxtempengine(Boolean maxtempengine) {
		this.maxtempengine = maxtempengine;
	}

	public Boolean getMintempengine() {
		return mintempengine;
	}

	public void setMintempengine(Boolean mintempengine) {
		this.mintempengine = mintempengine;
	}

	public Boolean getMaxtempfrigot() {
		return maxtempfrigot;
	}

	public void setMaxtempfrigot(Boolean maxtempfrigot) {
		this.maxtempfrigot = maxtempfrigot;
	}

	public Boolean getMintempfrigot() {
		return mintempfrigot;
	}

	public void setMintempfrigot(Boolean mintempfrigot) {
		this.mintempfrigot = mintempfrigot;
	}

	public Boolean getZoneonein() {
		return zoneonein;
	}

	public void setZoneonein(Boolean zoneonein) {
		this.zoneonein = zoneonein;
	}

	public Boolean getZoneoneout() {
		return zoneoneout;
	}

	public void setZoneoneout(Boolean zoneoneout) {
		this.zoneoneout = zoneoneout;
	}

	public Boolean getZonetwoin() {
		return zonetwoin;
	}

	public void setZonetwoin(Boolean zonetwoin) {
		this.zonetwoin = zonetwoin;
	}

	public Boolean getZonetwoout() {
		return zonetwoout;
	}

	public void setZonetwoout(Boolean zonetwoout) {
		this.zonetwoout = zonetwoout;
	}

	public Boolean getEnginestop() {
		return enginestop;
	}

	public void setEnginestop(Boolean enginestop) {
		this.enginestop = enginestop;
	}

	public Boolean getEnginestart() {
		return enginestart;
	}

	public void setEnginestart(Boolean enginestart) {
		this.enginestart = enginestart;
	}

	public Boolean getMail() {
		return mail;
	}

	public void setMail(Boolean mail) {
		this.mail = mail;
	}

	public Boolean getOpendoor() {
		return opendoor;
	}

	public void setOpendoor(Boolean opendoor) {
		this.opendoor = opendoor;
	}

	public Boolean getClosedoor() {
		return closedoor;
	}

	public void setClosedoor(Boolean closedoor) {
		this.closedoor = closedoor;
	}

	public Boolean getDrivertracking() {
		return drivertracking;
	}

	public void setDrivertracking(Boolean drivertracking) {
		this.drivertracking = drivertracking;
	}

	@Override
	public String toString() {
		return "ActiveNotif [id=" + id + ", carid=" + carid + ", techcontrol="
				+ techcontrol + ", emptyingkm=" + emptyingkm
				+ ", insuranceend=" + insuranceend + ", circulationend="
				+ circulationend + ", maxspeed=" + maxspeed + ", maxcourse="
				+ maxcourse + ", maxcarburantpri=" + maxcarburantpri
				+ ", maxcarburantsec=" + maxcarburantsec + ", mincarburantpri="
				+ mincarburantpri + ", mincarburantsec=" + mincarburantsec
				+ ", maxtempengine=" + maxtempengine + ", mintempengine="
				+ mintempengine + ", maxtempfrigot=" + maxtempfrigot
				+ ", mintempfrigot=" + mintempfrigot + ", zoneonein="
				+ zoneonein + ", zoneoneout=" + zoneoneout + ", zonetwoin="
				+ zonetwoin + ", zonetwoout=" + zonetwoout + ", enginestop="
				+ enginestop + ", enginestart=" + enginestart + ", mail="
				+ mail + ", opendoor=" + opendoor + ", closedoor=" + closedoor
				+ ", drivertracking=" + drivertracking + "]";
	}
}
