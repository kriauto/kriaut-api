package ma.kriauto.api.service;

import ma.kriauto.api.model.Zone;

public interface ZoneService {
    
	public Zone fetchZoneById(Long id);
	public Zone fetchZoneByCarId(Long carid);
	public Zone fetchZoneByCarIdAndRank(Long carid, Integer rank);
	public Zone save(Zone zone);
}
