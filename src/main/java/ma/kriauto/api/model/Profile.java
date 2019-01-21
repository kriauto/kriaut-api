package ma.kriauto.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "api_profile")
public class Profile {
	
    
    @Id
    private Long id;
    
    @Column(name="login")
    private String login;
    
    @Column(name="password")
    private String password;
    
    @Column(name="authtoken")
    private String authToken;
    
    @Column(name="name")
    private String name;
    
    @Column(name="phone")
    private String phone;
    
    @Column(name="mail")
    private String mail;
    
    @Column(name="islastposition")
    private Boolean isLastPosition;
    
    @Column(name="ishistory")
    private Boolean isHistory;
    
    @Column(name="isspeedmax")
    private Boolean isSpeedMax;
    
    @Column(name="iscourse")
    private Boolean isCourse;
    
    @Column(name="iscarburantp")
    private Boolean isCarburantP;
    
    @Column(name="iscarburants")
    private Boolean isCarburantS;
    
    @Column(name="isnotifcons")
    private Boolean isNotifCons;
    
    @Column(name="isnotifconf")
    private Boolean isNotifConf;
    
    @Column(name="iszoneone")
    private Boolean isZoneOne;
    
    @Column(name="iszonetwo")
    private Boolean isZoneTwo;
    
    @Column(name="istempm")
    private Boolean isTempM;
    
    @Column(name="istempf")
    private Boolean isTempF;
    
    @Column(name="isdoor")
    private Boolean isDoor;
    
    @Column(name="isdriver")
    private Boolean isDriver;
    
    @Column(name="isparameters")
    private Boolean isParameters;
    
    @Column(name="isstartstop")
    private Boolean isStartStop;
    
    @Column(name="ismyaccount")
    private Boolean isMyAccount;
    
    @Transient
    private String notifToken;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Boolean getIsLastPosition() {
		return isLastPosition;
	}

	public void setIsLastPosition(Boolean isLastPosition) {
		this.isLastPosition = isLastPosition;
	}

	public Boolean getIsHistory() {
		return isHistory;
	}

	public void setIsHistory(Boolean isHistory) {
		this.isHistory = isHistory;
	}

	public Boolean getIsSpeedMax() {
		return isSpeedMax;
	}

	public void setIsSpeedMax(Boolean isSpeedMax) {
		this.isSpeedMax = isSpeedMax;
	}

	public Boolean getIsCourse() {
		return isCourse;
	}

	public void setIsCourse(Boolean isCourse) {
		this.isCourse = isCourse;
	}

	public Boolean getIsCarburantP() {
		return isCarburantP;
	}

	public void setIsCarburantP(Boolean isCarburantP) {
		this.isCarburantP = isCarburantP;
	}

	public Boolean getIsCarburantS() {
		return isCarburantS;
	}

	public void setIsCarburantS(Boolean isCarburantS) {
		this.isCarburantS = isCarburantS;
	}

	public Boolean getIsNotifCons() {
		return isNotifCons;
	}

	public void setIsNotifCons(Boolean isNotifCons) {
		this.isNotifCons = isNotifCons;
	}

	public Boolean getIsNotifConf() {
		return isNotifConf;
	}

	public void setIsNotifConf(Boolean isNotifConf) {
		this.isNotifConf = isNotifConf;
	}

	public Boolean getIsZoneOne() {
		return isZoneOne;
	}

	public void setIsZoneOne(Boolean isZoneOne) {
		this.isZoneOne = isZoneOne;
	}

	public Boolean getIsZoneTwo() {
		return isZoneTwo;
	}

	public void setIsZoneTwo(Boolean isZoneTwo) {
		this.isZoneTwo = isZoneTwo;
	}

	public Boolean getIsTempM() {
		return isTempM;
	}

	public void setIsTempM(Boolean isTempM) {
		this.isTempM = isTempM;
	}

	public Boolean getIsTempF() {
		return isTempF;
	}

	public void setIsTempF(Boolean isTempF) {
		this.isTempF = isTempF;
	}

	public Boolean getIsDoor() {
		return isDoor;
	}

	public void setIsDoor(Boolean isDoor) {
		this.isDoor = isDoor;
	}

	public Boolean getIsDriver() {
		return isDriver;
	}

	public void setIsDriver(Boolean isDriver) {
		this.isDriver = isDriver;
	}

	public Boolean getIsParameters() {
		return isParameters;
	}

	public void setIsParameters(Boolean isParameters) {
		this.isParameters = isParameters;
	}

	public Boolean getIsStartStop() {
		return isStartStop;
	}

	public void setIsStartStop(Boolean isStartStop) {
		this.isStartStop = isStartStop;
	}

	public Boolean getIsMyAccount() {
		return isMyAccount;
	}

	public void setIsMyAccount(Boolean isMyAccount) {
		this.isMyAccount = isMyAccount;
	}

	public String getNotifToken() {
		return notifToken;
	}

	public void setNotifToken(String notifToken) {
		this.notifToken = notifToken;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", login=" + login + ", password="
				+ password + ", authToken=" + authToken + ", name=" + name
				+ ", phone=" + phone + ", mail=" + mail + ", isLastPosition="
				+ isLastPosition + ", isHistory=" + isHistory + ", isSpeedMax="
				+ isSpeedMax + ", isCourse=" + isCourse + ", isCarburantP="
				+ isCarburantP + ", isCarburantS=" + isCarburantS
				+ ", isNotifCons=" + isNotifCons + ", isNotifConf="
				+ isNotifConf + ", isZoneOne=" + isZoneOne + ", isZoneTwo="
				+ isZoneTwo + ", isTempM=" + isTempM + ", isTempF=" + isTempF
				+ ", isDoor=" + isDoor + ", isDriver=" + isDriver
				+ ", isParameters=" + isParameters + ", isStartStop="
				+ isStartStop + ", isMyAccount=" + isMyAccount
				+ ", notifToken=" + notifToken + "]";
	}
}
