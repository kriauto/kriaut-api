package ma.kriauto.api.response;

public class AccountOut {

	private ProfileOut profile;
	private AgencyOut  agency;
	
	public ProfileOut getProfile() {
		return profile;
	}
	public void setProfile(ProfileOut profile) {
		this.profile = profile;
	}
	public AgencyOut getAgency() {
		return agency;
	}
	public void setAgency(AgencyOut agency) {
		this.agency = agency;
	}
	
	@Override
	public String toString() {
		return "AccountOut [profile=" + profile + ", agency=" + agency + "]";
	}
}
