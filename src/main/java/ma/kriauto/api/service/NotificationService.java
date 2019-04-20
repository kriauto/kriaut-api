package ma.kriauto.api.service;

import java.util.List;

import ma.kriauto.api.model.Notification;
import ma.kriauto.api.repository.NotificationRepository;

public interface NotificationService {

	public List<Notification> fetchNotificationByCarIdAndDate(Long carid, String date);
	public Notification save(NotificationRepository notification);
}
