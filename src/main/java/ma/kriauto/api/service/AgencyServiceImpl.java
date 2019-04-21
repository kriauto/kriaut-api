package ma.kriauto.api.service;

import ma.kriauto.api.model.Agency;
import ma.kriauto.api.repository.AgencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgencyServiceImpl implements AgencyService {
	
	@Autowired
    private AgencyRepository agencyRepository;

	@Override
	public Agency fetchAgencyByProfileId(Long id) {
		return agencyRepository.fetchAgencyByProfileId(id);
	}

	@Override
	public Agency save(Agency agency) {
		// TODO Auto-generated method stub
		return agencyRepository.save();
	}

}
