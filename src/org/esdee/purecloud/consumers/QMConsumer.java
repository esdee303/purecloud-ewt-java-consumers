package org.esdee.purecloud.consumers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import com.mypurecloud.sdk.v2.ApiException;

import org.esdee.purecloud.utilities.ArgumentGetter;
import org.esdee.purecloud.utilities.TokenGetter;
import org.esdee.purecloud.parsers.QMParser;

public class QMConsumer {
		
	public static void consumer(String queueId) throws ClassNotFoundException, SQLException {
		
		try {
			String token = TokenGetter.purecloudToken();
			System.out.println(token);
			System.out.println("Token OK!");
			
			String jsonString = "{\"filter\": {\"type\": \"or\", \"predicates\": [{\"type\": \"dimension\", \"dimension\": \"queueId\", "
					+ "\"operator\": \"matches\", \"value\": \"" + queueId + "\"}]}, \"metrics\": [\"oOnqueueUsers\", \"oOffQueueUsers\"]}";
			
			URL url = new URL(ArgumentGetter.apiQueueUrl());
			HttpURLConnection conn =(HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);			
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + token);
			
			OutputStream os = conn.getOutputStream();
            os.write(jsonString.getBytes("UTF-8"));
            os.close();
            
            InputStream in = new BufferedInputStream(conn.getInputStream());
            String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
            System.out.println("Starting Parser...");
            QMParser.parseJSON(queueId, result);
            System.out.println("This means that everything is ok");
            System.out.println("QueueMetricsConsumer all done !");
            System.out.println("Ending... bye");
            
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
}
