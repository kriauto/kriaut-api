package ma.kriauto.api.service;

import ma.kriauto.api.model.Agency;
import ma.kriauto.api.repository.AgencyRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class AgencyServiceImpl implements AgencyService {
	
	@Autowired
    private AgencyRepository agencyRepository;

	@Override
	public Agency fetchAgencyByProfileId(Long id) {
		// TODO Auto-generated method stub
		return agencyRepository.fetchAgencyByProfileId(id);
	}

}
