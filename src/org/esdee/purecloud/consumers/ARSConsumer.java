package org.esdee.purecloud.consumers;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import com.mypurecloud.sdk.v2.ApiException;

import org.esdee.purecloud.utilities.ArgumentGetter;
import org.esdee.purecloud.utilities.TokenGetter;
import org.esdee.purecloud.parsers.ARSParser;
import org.esdee.purecloud.models.Agent;

public class ARSConsumer {

	public static void consumer() throws ClassNotFoundException, SQLException, IOException, ApiException {

		String token = TokenGetter.purecloudToken();
		System.out.println(token);
		System.out.println("Token OK!");
		List<Agent> agentList = ArgumentGetter.agentList();
		int counter = 0;
		for (Agent agent : agentList) {
			
			try {
				URL url = new URL(ArgumentGetter.apiUsersUrl() + agent.getAgentId() + "/routingstatus");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Bearer " + token);

				InputStream in = new BufferedInputStream(conn.getInputStream());
				String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
				System.out.println("Starting JSON Parser...");
				ARSParser.parseJSON(result, agent);
				System.out.println("This means that everything is ok");
				counter++;
				if(counter < 18) {
					System.out.println("Next Agent...");
				} else {
					System.out.println("All Agents done...");
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("AgentRoutingStatusConsumer has finished !");
		System.out.println("That's all, folks !");
	}
}