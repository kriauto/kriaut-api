package ma.kriauto.api.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import ma.kriauto.api.common.Mailin;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;

@Service
public class SenderServiceImpl implements SenderService {
	
	public final static String AUTH_KEY_FCM = "AAAAGCh6u8g:APA91bGM-jPzZI1BIasa0IdW6SUNCXAa78mWXI0mACvYXmawU5ptyT3iCIjcEhS1_b7V6XaEwsuL-rppJ_AgH_O1Q_XBXttUYoVIlwVamJEr6grmo4qxWGWPMELZar1bRsXCpJCaEaFq";
	public final static String API_URL_FCM = "https://fcm.googleapis.com/fcm/send";

	@Override
	public int sendSms(String from, String to, String message) {
		System.out.println("from -> "+from+"to -> "+to+"message -> "+message);
		AuthMethod auth = new TokenAuthMethod("9db9ffd9", "DKADpFPTj0RJbRq2");
		NexmoClient client = new NexmoClient(auth);
		System.out.println("FROM_NUMBER");

		SmsSubmissionResult[] responses;
		try {
			responses = client.getSmsClient().submitMessage(new TextMessage(
			        "kriauto",
			        to,
			        message));
		} catch (IOException | NexmoClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for (SmsSubmissionResult response : responses) {
//		    System.out.println(response);
//		}
		//if(responses. == 200){
		   return 0;
//		}else{
//		   return 1;
//		}
	}

	@Override
	public int sendMail(String _from, String _to, String _subject, String _content) {
		
		Mailin http = new Mailin("https://api.sendinblue.com/v2.0","UfsbL9W8tCO3FQA0");
		Map < String, String > to = new HashMap < String, String > ();
			to.put(_to, _to);
//			Map < String, String > cc = new HashMap < String, String > ();
//				cc.put("contact@kriauto.ma", "contact@kriauto.ma");
//			Map < String, String > bcc = new HashMap < String, String > ();
//				bcc.put("contact@kriauto.ma", "contact@kriauto.ma");
//			Map < String, String > attachment = new HashMap < String, String > ();
//	    		attachment.put("myfilename.pdf", "your_pdf_files_base64_encoded_chunk_data");
		Map < String, String > headers = new HashMap < String, String > ();
			headers.put("Content-Type", "text/html; charset=iso-8859-1");
			headers.put("X-param1", "value1");
			headers.put("X-param2", "value2");
			headers.put("X-Mailin-custom", "my custom value");
			headers.put("X-Mailin-IP", "102.102.1.2");
			headers.put("X-Mailin-Tag", "My tag");
//			Map < String, String > inline_image = new HashMap < String, String > ();
//					inline_image.put("myinlineimage1.png", "your_png_files_base64_encoded_chunk_data");
//					inline_image.put("myinlineimage2.jpg", "your_jpg_files_base64_encoded_chunk_data");
		Map < String, Object > data = new HashMap < String, Object > ();
			data.put("to", to);
//				data.put("cc", cc);
//				data.put("bcc", bcc);
			data.put("replyto", new String [] {_from,_from});
			data.put("from", new String [] {_from,_from});
			data.put("subject", _subject);
			data.put("html", _content);
//					" This is inline image 1.<br/>"
//					<img src=\"{myinlineimage1.png}\" alt=\"image1\" border=\"0\"><br/>
//					Some text<br/>
//					This is inline image 2.<br/>
//					<img src=\"{myinlineimage2.jpg}\" alt=\"image2\" border=\"0\"><br/>
//					Some more text<br/>
//					Re-used inline image 1.<br/>
//					<img src=\"{myinlineimage1.png}\" alt=\"image3\" border=\"0\">");
//				data.put("text", "This is text");
			//data.put("attachment", attachment);
			//data.put("headers", headers);
			//data.put("inline_image", inline_image);
 
		String str = http.send_email(data);
		System.out.println(str);
		return 1;
	}

	@Override
	public int sendPushNotification(String pushToken, String message) throws IOException {
		int result = 1;
        URL url = new URL(API_URL_FCM);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
 
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
 
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
        conn.setRequestProperty("Content-Type", "application/json");
 
        
        try {
        	JSONObject json = new JSONObject();
        	 
            json.put("to", pushToken.trim());
            JSONObject info = new JSONObject();
            info.put("sound", "notification tit"); // Notification title
            info.put("body", message); // Notification
                                                                    // body
            json.put("notification", info);
            OutputStreamWriter wr = new OutputStreamWriter(
                    conn.getOutputStream());
            wr.write(json.toString());
            wr.flush();
 
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
 
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        System.out.println("GCM Notification is sent successfully");
 
        return result;
	}

}
