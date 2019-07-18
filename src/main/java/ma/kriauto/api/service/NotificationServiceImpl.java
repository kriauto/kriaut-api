package ma.kriauto.api.service;

import java.util.List;

import ma.kriauto.api.model.Notification;
import ma.kriauto.api.repository.AgencyRepository;
import ma.kriauto.api.repository.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
    private NotificationRepository notificationRepository;

	@Override
	public List<Notification> fetchNotificationByCarIdAndDate(Long carid,
			String date) {
		return notificationRepository.fetchNotificationByCarIdAndDate(carid, date);
	}

	@Override
	public Notification save(Notification notification) {
		return notificationRepository.save(notification);
	}

}
