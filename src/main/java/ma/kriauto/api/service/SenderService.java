package ma.kriauto.api.service;

import java.io.IOException;


public interface SenderService {
	public int sendSms(String from, String to, String message);
	public int sendMail(String from, String to, String subject, String content);
	public int sendPushNotification(String pushToken, String message) throws IOException;
}
