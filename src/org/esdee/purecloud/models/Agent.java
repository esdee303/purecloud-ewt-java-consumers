package org.esdee.purecloud.models;
public class Agent {
		
	private String uuid;
	private String timestamp;
	private String agentId;
	private String agentName;
	private String agentStatus;
	private String statusStartTime;
	
	
	public Agent() {}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getAgentId() {
		return agentId;
	}
	
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
	public String getAgentName() {
		return agentName;
	}
	
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public String getAgentStatus() {
		return agentStatus;
	}
	
	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}
	
	
	public void setStatusStartTime(String statusStartTime) {
		this.statusStartTime = statusStartTime;
	}
	
	public String getStatusStartTime() {
		return statusStartTime;
	}
	
	
}
