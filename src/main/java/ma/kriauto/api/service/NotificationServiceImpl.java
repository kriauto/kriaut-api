package ma.kriauto.api.service;

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
	public Notification fetchNotificationByCarIdAndCreationdate(Long carid,
			Long date) {
		return notificationRepository.fetchNotificationByCarIdAndCreationdate(carid, date);
	}

	@Override
	public Notification save(NotificationRepository notification) {
		return notificationRepository.save(notification);
	}

}
