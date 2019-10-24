package ma.kriauto.api.service;

import ma.kriauto.api.model.Agency;

public interface AgencyService {

	public Agency fetchAgencyByProfileId(Long id);
	public Agency save(Agency agency);
	public void completeAgency(Agency agencyin, Agency agencyout);
}
