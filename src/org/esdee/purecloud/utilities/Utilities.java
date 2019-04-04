package org.esdee.purecloud.utilities;

import java.util.ArrayList;
import java.util.List;

import org.esdee.purecloud.models.Agent;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Utilities {
	
	public static DateTimeFormatter yyyy_MM_ddHHmmss = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	public static String millisToTime(String millis) {
		
		int milliseconds = Integer.valueOf(millis);
		int seconds = (int) (milliseconds / 1000) % 60;
		int minutes = (int) ((milliseconds / (1000*60)) % 60);
		int hours = (int) ((milliseconds / (1000*60*60)) % 24);
		String timeString = (String.valueOf(hours) + "h " + String.valueOf(minutes) + "m " + String.valueOf(seconds) + "s");
		return timeString;
	}
	
	public static String secsToTime(String secs) {
		
		int totalSeconds = Integer.valueOf(secs);
		int hours = (int) totalSeconds / 3600;
		int remainder = (int) totalSeconds - hours * 3600;
		int minutes = (int) remainder / 60;
		remainder = remainder - minutes * 60;
		int seconds = remainder;
		String timeString = (String.valueOf(hours) + "h " + String.valueOf(minutes) + "m " + String.valueOf(seconds) + "s");
		return timeString;
	}
	
	public static String purecloudDateTimeParser(String dateTimeFromJson) {
		String callStartDateTime = "";
		String date = dateTimeFromJson.substring(0,10);
		String hour =dateTimeFromJson.substring(11,13);
		String minutes = dateTimeFromJson.substring(13,17);
		String seconds = dateTimeFromJson.substring(17,19);
		callStartDateTime = date + " " + hour + minutes + seconds;
		
		return callStartDateTime;
	}
	
	public static String purecloudDateTimeParserAdd1h(String dateTimeFromJson) {
		String callStartDateTime = "";
		String date = dateTimeFromJson.substring(0,10);
		String hour = "";
		int hourInt =Integer.valueOf((dateTimeFromJson).substring(11,13)) + 1;
		if(hourInt < 10) {
			hour = "0" + String.valueOf(hourInt);
		} else {
			hour = String.valueOf(hourInt);
		}
		String minutes = dateTimeFromJson.substring(13,17);
		String seconds = dateTimeFromJson.substring(17,19);
		callStartDateTime = date + " " + hour + minutes + seconds;
		
		return callStartDateTime;
	}
	
	public static List<Agent> populateQuantList() {
		List<Agent> agentList = new ArrayList<Agent>();

		for (int i = 0; i < 1; i++) {
			Agent agent = new Agent();
			agentList.add(agent);
		}

		agentList.get(0).setAgentName("Quant ICT Support");
		agentList.get(0).setAgentId("a738e2ff-a336-4ad3-bb1e-e23fb52d7a0e");

		return agentList;
	}
	
	
}
