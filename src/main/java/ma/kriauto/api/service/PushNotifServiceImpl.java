package ma.kriauto.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.kriauto.api.model.PushNotif;
import ma.kriauto.api.repository.PushNotifRepository;

@Service
public class PushNotifServiceImpl implements PushNotifService {
	
	@Autowired
    private PushNotifRepository pushnotifRepository;

	@Override
	public PushNotif save(PushNotif pushnotif) {
		// TODO Auto-generated method stub
		return pushnotifRepository.save(pushnotif);
	}

	@Override
	public void delete(PushNotif pushnotif) {
		// TODO Auto-generated method stub
		pushnotifRepository.delete(pushnotif);
	}

	@Override
	public PushNotif fetchDeviceByPushToken(String push) {
		// TODO Auto-generated method stub
		return pushnotifRepository.fetchDeviceByPushToken(push);
	}

	@Override
	public List<PushNotif> fetchPushNotifByProfileId(Long profileid) {
		// TODO Auto-generated method stub
		return pushnotifRepository.fetchPushNotifByProfileId(profileid);
	}

}
