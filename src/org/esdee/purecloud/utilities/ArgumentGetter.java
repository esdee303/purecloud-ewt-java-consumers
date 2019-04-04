package org.esdee.purecloud.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.esdee.purecloud.models.Agent;

public class ArgumentGetter {

	private static final String PURECLOUD_PARAMS = "c:\\eclipse-cred\\purecloud-params.txt";

	public ArgumentGetter() {}

	public static String[] DbUtilConn() {
		String[] dbParams = new String[3];
		String dbIpAddress = "";
		String dbUsername = "";
		String dbPassword = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("db-ipaddress")) {
					dbIpAddress = line.substring(line.indexOf("=") + 1, line.length());
				} else if (line.contains("db-username")) {
					dbUsername = line.substring(line.indexOf("=") + 1, line.length());
				} else if (line.contains("db-password")) {
					dbPassword = line.substring(line.indexOf("=") + 1, line.length());
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dbParams[0] = dbIpAddress;
		dbParams[1] = dbUsername;
		dbParams[2] = dbPassword;
		
		return dbParams;
	}
	
	public static String[] PureCloudConn() {
		String[] pcParams = new String[3];
		String pcClientId = "";
		String pcClientSecret = "";
		String pcUrl = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("pc-client-id")) {
					pcClientId = line.substring(line.indexOf("=") + 1, line.length());
				} else if (line.contains("pc-client-secret")) {
					pcClientSecret = line.substring(line.indexOf("=") + 1, line.length());
				} else if (line.contains("pc-url")) {
					pcUrl = line.substring(line.indexOf("=") + 1, line.length());
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		pcParams[0] = pcClientId;
		pcParams[1] = pcClientSecret;
		pcParams[2] = pcUrl;
		
		return pcParams;
	}
	
	public static List<Agent> agentList() {
		List<Agent> agentList = new ArrayList<Agent>();
		List<String> agentNames = new ArrayList<String>();
		List<String> agentIds = new ArrayList<String>();
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if(line.contains("agent-name")) {
					agentNames.add(line.substring(line.indexOf("=") + 1, line.length()));
				} else if(line.contains("agent-id")) {
					agentIds.add(line.substring(line.indexOf("=") + 1, line.length()));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < agentNames.size(); i++) {
			Agent agent = new Agent();
			agent.setAgentName(agentNames.get(i));
			agent.setAgentId(agentIds.get(i));
			agentList.add(agent);
		}
		return agentList;
	}
	
	public static String apiUsersUrl() {
		String usersUrl = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("pc-api-users-url"))
					usersUrl = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usersUrl;
	}
	
	public static String apiQueueUrl() {
		String queueUrl = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("pc-api-queue-url"))
					queueUrl = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return queueUrl;
	}
	
	public static String herokuAppUrl() {
		String herokuUrl = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("heroku-app-url"))
					herokuUrl = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return herokuUrl;
	}
	
	public static String QmQueue() {
		String queue = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("qm-queue"))
					queue = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return queue;
	}
	
	public static String HerQueue() {
		String queue = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("her-queue"))
					queue = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return queue;
	}
	
	public static String arsSqlInsertStatement() {
		String stmt = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("sql-arsp-insert"))
					stmt = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static String herokuSqlSelectStatement() {
		String stmt = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("sql-herokup-select"))
					stmt = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static String herokuSqlInsertStatement() {
		String stmt = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("sql-herokup-insert"))
					stmt = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static String qmSqlInsertStatement() {
		String stmt = "";
		String line;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(PURECLOUD_PARAMS));
			while ((line = br.readLine()) != null) {
				if (line.contains("sql-qmp-insert"))
					stmt = line.substring(line.indexOf("=") + 1, line.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stmt;
	}
}
