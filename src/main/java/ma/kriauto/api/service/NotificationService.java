package ma.kriauto.api.service;

import ma.kriauto.api.model.Notification;
import ma.kriauto.api.repository.NotificationRepository;

public interface NotificationService {

	public Notification fetchNotificationByCarIdAndCreationdate(Long carid, Long creationdate);
	public Notification save(NotificationRepository notification);
}
