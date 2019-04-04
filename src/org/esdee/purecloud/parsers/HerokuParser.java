package org.esdee.purecloud.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.esdee.purecloud.utilities.ArgumentGetter;
import org.esdee.purecloud.utilities.DBUtil;
import org.esdee.purecloud.utilities.Utilities;
import org.esdee.purecloud.models.Ewt;

public class HerokuParser {
	
	@SuppressWarnings("unchecked")
	public static void parseJSON(String jsonString) throws FileNotFoundException {
		
		// JSON parser object to parse read file
		JSONParser parser = new JSONParser();
		try  {
			
			System.out.println("Creating Ewt object from json string...");
			System.out.println(jsonString);
			JSONObject jsonObj = (JSONObject) parser.parse(jsonString);
			System.out.println("parsing done...");
			System.out.println("casting into JSONArray...");
			JSONArray ewtList = (JSONArray) (((JSONArray) jsonObj.get("ewts")));
			System.out.println("Iterate over ewt array and write to database");
			ewtList.forEach(emp -> {
				try {
					writeEwt( (JSONObject) emp );
				} catch (ClassNotFoundException | SQLException | IOException e) {
					e.printStackTrace();
				}
			});
	
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeEwt(JSONObject jsonObj) throws ClassNotFoundException, FileNotFoundException, SQLException, IOException {
				
		String id = (String) jsonObj.get("_id");
		
		ResultSet rs = DBUtil.dbExecuteQuery(ArgumentGetter.herokuSqlSelectStatement() + id + "'");
		
		if(rs.next() == false) {
			System.out.println("_id not found, object " + id + " is new and will be inserted");
			Ewt ewt = new Ewt();
			if(jsonObj.containsKey("timestamp")) ewt.setTimestamp(Utilities.purecloudDateTimeParser((String) jsonObj.get("timestamp")));
			ewt.setCallStartDateTime(Utilities.purecloudDateTimeParserAdd1h((String) jsonObj.get("callStartDateTime")));
			ewt.setId(id);
			ewt.setSuccess((Boolean) jsonObj.get("success"));
			ewt.setCallAni((String) jsonObj.get("callAni"));
			ewt.setCalledAddressOriginal((String) jsonObj.get("calledAddressOriginal"));
			ewt.setInteractionId((String) jsonObj.get("interactionId"));
			ewt.setCallCurrentQueue((String) jsonObj.get("callCurrentQueue"));
			
			String rwt = jsonObj.get("realWaitTime").toString();
			ewt.setRealWaitTime(Utilities.millisToTime(rwt.substring(0, rwt.indexOf(" "))));
				
			ewt.setCallEstWaitTime(Utilities.millisToTime((String) jsonObj.get("callEstWaitTime")));
					
			String awt = jsonObj.get("apiEstWaitTimeInSeconds").toString();
			ewt.setApiEstWaitTimeInSeconds(Utilities.secsToTime(awt.substring(0, awt.indexOf(" "))));
					
			ewt.setCallPositionInQueue(String.valueOf((Long) jsonObj.get("callPositionInQueue")));
			ewt.setCallLanguage((String) jsonObj.get("callLanguage"));
					
			String query = ArgumentGetter.herokuSqlInsertStatement()
					+ " VALUES ('" + ewt.getId() + "'," + ewt.getSuccess() + ",'" + ewt.getTimestamp() + "','" + ewt.getCallStartDateTime() + "',"
					+ "'" + ewt.getCallAni() + "','" + ewt.getCalledAddressOriginal() + "','" + ewt.getInteractionId() + "',"
					+ "'" + ewt.getCallCurrentQueue() + "','" + ewt.getRealWaitTime() + "','" +ewt.getCallEstWaitTime() + "',"
					+ "'" + ewt.getApiEstWaitTimeInSeconds() + "','" + ewt.getCallPositionInQueue() + "',"
					+ "'" + ewt.getCallLanguage() + "')";
			
			System.out.println(query);
			DBUtil.dbExecuteUpdate(query);
		} else {
			System.out.println("ID " + id + " already found, skipping record...");
		}
		
	}
}
