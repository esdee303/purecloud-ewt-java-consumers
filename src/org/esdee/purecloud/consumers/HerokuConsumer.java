package org.esdee.purecloud.consumers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.parser.ParseException;

import org.esdee.purecloud.parsers.HerokuParser;
import org.esdee.purecloud.utilities.ArgumentGetter;

public class HerokuConsumer {
	
	public static void consumer(String queue) throws ParseException, ClassNotFoundException {
		try {
			InputStream is = null;
			StringBuffer sb = new StringBuffer();
			String result = null;
			System.out.println("Heroku consumer: processing GET request...");
			URL url = new URL(ArgumentGetter.herokuAppUrl() + queue);
			HttpURLConnection conn =(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			
			if(conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed: HTTP error code: " + conn.getResponseCode());
			}
			
			is = new BufferedInputStream(conn.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String inputLine = "";
			while((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			result = sb.toString();
			
			HerokuParser.parseJSON(result);
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
