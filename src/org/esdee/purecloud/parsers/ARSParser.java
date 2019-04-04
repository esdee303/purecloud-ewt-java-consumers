package org.esdee.purecloud.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.esdee.purecloud.utilities.ArgumentGetter;
import org.esdee.purecloud.utilities.DBUtil;
import org.esdee.purecloud.utilities.Utilities;
import org.esdee.purecloud.models.Agent;

public class ARSParser {
	
	private static final DateTimeFormatter yyyy_MM_ddHHmmss = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	public static void parseJSON(String jsonString, Agent agent) throws ClassNotFoundException, SQLException {
		
		JSONParser parser = new JSONParser();
		
		try  {
			System.out.println("Creating Agent Routing Status object from json string...");
			System.out.println(jsonString);
			UUID uuid = UUID.randomUUID();
			DateTime dateTime = new DateTime();
			System.out.println("Parsing json string into JSON Object...");
			JSONObject jsonObj = (JSONObject) parser.parse(jsonString);
			agent.setTimestamp(yyyy_MM_ddHHmmss.print(dateTime));
			agent.setUuid(uuid.toString());
			agent.setAgentStatus((String) jsonObj.get("status"));
			agent.setStatusStartTime(Utilities.purecloudDateTimeParserAdd1h((String) jsonObj.get("startTime")));
			System.out.println("Creating SQL Statement for INSERT operation...");
			String query = ArgumentGetter.arsSqlInsertStatement() + " VALUES ('" + agent.getUuid() + "','" + agent.getTimestamp() 
					+ "','" + agent.getAgentId() + "','" + agent.getAgentName() + "','"
					+ agent.getAgentStatus() + "','" + agent.getStatusStartTime() + "')";
			System.out.println("INSERT AgentRoutingStatus of Agent " + agent.getAgentId() + " into DB...");
			DBUtil.dbExecuteUpdate(query);
			System.out.println("AgentRoutingStatus succesfully inserted !");
			System.out.println(query);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("PARSER + DB UPDATE ALL DONE !");
	}
}
