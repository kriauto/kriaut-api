package ma.kriauto.api.response;

import ma.kriauto.api.model.Agency;
import ma.kriauto.api.model.Payment;
import ma.kriauto.api.model.Profile;

public class AccountOut {

	private Profile profile;
	private Agency  agency;
	private Payment payment;
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	@Override
	public String toString() {
		return "AccountOut [profile=" + profile + ", agency=" + agency
				+ ", payment=" + payment + "]";
	}
}
