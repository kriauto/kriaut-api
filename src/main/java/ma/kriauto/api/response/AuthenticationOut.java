package ma.kriauto.api.response;

public class AuthenticationOut {
	private String  authToken;
    private String  agencyName;
    private Boolean isLastPosition;
    private Boolean isHistory;
    private Boolean isSpeedMax;
    private Boolean isCourse;
    private Boolean isCarburantP;
    private Boolean isCarburantS;
    private Boolean isNotifCons;
    private Boolean isNotifConf;
    private Boolean isZoneOne;
    private Boolean isZoneTwo;
    private Boolean isTempM;
    private Boolean isTempF;
    private Boolean isDoor;
    private Boolean isDriver;
    private Boolean isParameters;
    private Boolean isStartStop;
    private Boolean isMyAccount;
    private Boolean isContactUs;
    
    
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
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
	public Boolean getIsContactUs() {
		return isContactUs;
	}
	public void setIsContactUs(Boolean isContactUs) {
		this.isContactUs = isContactUs;
	}
	
	@Override
	public String toString() {
		return "MenuDTO [authToken=" + authToken + ", agencyName=" + agencyName + ", isLastPosition=" + isLastPosition
				+ ", isHistory=" + isHistory + ", isSpeedMax=" + isSpeedMax + ", isCourse=" + isCourse
				+ ", isCarburantP=" + isCarburantP + ", isCarburantS=" + isCarburantS + ", isNotifCons=" + isNotifCons
				+ ", isNotifConf=" + isNotifConf + ", isZoneOne=" + isZoneOne + ", isZoneTwo=" + isZoneTwo
				+ ", isTempM=" + isTempM + ", isTempF=" + isTempF + ", isDoor=" + isDoor + ", isDriver=" + isDriver
				+ ", isParameters=" + isParameters + ", isStartStop=" + isStartStop + ", isMyAccount=" + isMyAccount
				+ ", isContactUs=" + isContactUs + "]";
	}
}
