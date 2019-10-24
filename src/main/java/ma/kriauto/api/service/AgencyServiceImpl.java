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
		return agencyRepository.save(agency);
	}

	@Override
	public void completeAgency(Agency agencyin, Agency agencyout) {
		agencyout.setName(agencyin.getName() != null ? agencyin.getName() : agencyout.getName());
		agencyout.setCity(agencyin.getCity() != null ? agencyin.getCity() : agencyout.getCity());
		agencyout.setAddress(agencyin.getAddress() != null ? agencyin.getAddress() : agencyout.getAddress());
		agencyout.setPhone(agencyin.getPhone() != null ? agencyin.getPhone() : agencyout.getPhone());
		agencyout.setFax(agencyin.getFax() != null ? agencyin.getFax() : agencyout.getFax());
	}

}
