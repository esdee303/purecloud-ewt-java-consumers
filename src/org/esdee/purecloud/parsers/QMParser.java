package org.esdee.purecloud.parsers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.esdee.purecloud.utilities.ArgumentGetter;
import org.esdee.purecloud.utilities.DBUtil;
import org.esdee.purecloud.utilities.Utilities;
import org.esdee.purecloud.models.Queue;

public class QMParser {
	
	

	public static void parseJSON(String queueId, String jsonString) throws ClassNotFoundException, SQLException {
		
		JSONParser parser = new JSONParser();
		
		try  {
			System.out.println("Creating QueueMetrics object from json string...");
			Queue q = new Queue();
			UUID uuid = UUID.randomUUID();
			q.setUuid(uuid.toString());
			q.setQueueId(queueId);
			DateTime dateTime = new DateTime();
			q.setDateTime(Utilities.yyyy_MM_ddHHmmss.print(dateTime));
			System.out.println("Parsing jsonString into JSONObject...");
			JSONObject parentsObj = (JSONObject) parser.parse(jsonString);
			System.out.println("Iterating the JSONObject...");
			int offQueue = 0;
			for (int i = 0; i < 12; i++) {
				JSONObject resultsObj = (JSONObject) (((JSONArray) parentsObj.get("results")).get(0));
				JSONObject dataObj = (JSONObject) (((JSONArray) resultsObj.get("data")).get(i));
				JSONObject statsObj = (JSONObject) dataObj.get("stats");
				long count = (Long) statsObj.get("count");
				String qualifier = (String) dataObj.get("qualifier");
				
				if (qualifier.equals("INTERACTING")) {
					q.setInteracting((int) count);
				} else if (qualifier.equals("IDLE")) {
					q.setIdle((int) count);
				} else if (qualifier.equals("COMMUNICATING")) {
					q.setCommunicating((int) count);
				} else if (qualifier.equals("NOT_RESPONDING")) {
					q.setNotResponding((int) count);
				} else {
					offQueue = offQueue + (int) count;
				}
				q.setOffQueue(offQueue);
				System.out.println("Iterating " + (i + 1) + " of 12");
			}
			q.setOnQueue(q.getCommunicating() + q.getIdle() + q.getInteracting() + q.getNotResponding());
			
			System.out.println("Creating SQL Statement for INSERT operation...");
			String query = ArgumentGetter.qmSqlInsertStatement()
					+ " VALUES ('" + q.getUuid() + "','" + q.getDateTime() + "','" + q.getQueueId() + "'," + q.getOnQueue() + "," + q.getInteracting() + ","
					+ q.getIdle() + "," + q.getCommunicating() + "," + q.getNotResponding() + "," + q.getOffQueue()
					+ ")";
			System.out.println("INSERTING QueryMetrics into DB...");
			DBUtil.dbExecuteUpdate(query);
			System.out.println("QueueMetrics succesfully inserted !");
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
