package ma.kriauto.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.kriauto.api.model.Zone;
import ma.kriauto.api.repository.ZoneRepository;

@Service
public class ZoneServiceImpl implements ZoneService {
	
	@Autowired
    private ZoneRepository zoneRepository;

	@Override
	public Zone fetchZoneById(Long id) {
		return zoneRepository.fetchZoneById(id);
	}

	@Override
	public Zone fetchZoneByCarId(Long carid) {
		return zoneRepository.fetchZoneByCarId(carid);
	}

	@Override
	public Zone fetchZoneByCarIdAndRank(Long carid, Integer rank) {
		return zoneRepository.fetchZoneByCarIdAndRank(carid, rank);
	}

	@Override
	public Zone save(Zone zone) {
		return zoneRepository.save(zone);
	}

}
