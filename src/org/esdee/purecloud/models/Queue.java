package org.esdee.purecloud.models;
public class Queue {
		
	private String uuid;
	private String dateTime;
	private String queueId;
	private int interacting;
	private int idle;
	private int communicating;
	private int notResponding;
	private int offQueue;
	private int onQueue;
	
	
	public Queue() {}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	public String getQueueId() {
		return queueId;
	}
	
	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}
	
	public int getInteracting() {
		return interacting;
	}
	
	public void setInteracting(int interacting) {
		this.interacting = interacting;
	}
	
	public int getIdle() {
		return idle;
	}
	
	public void setIdle(int idle) {
		this.idle = idle;
	}
	
	public int getCommunicating() {
		return communicating;
	}
	
	public void setCommunicating(int communicating) {
		this.communicating = communicating;
	}
	
	public int getNotResponding() {
		return notResponding;
	}
	
	public void setNotResponding(int notResponding) {
		this.notResponding = notResponding;
	}
	
	public int getOnQueue() {
		return onQueue;
	}
	
	public void setOnQueue(int onQueue) {
		this.onQueue = onQueue;
	}
	
	public int getOffQueue() {
		return offQueue;
	}
	
	public void setOffQueue(int offQueue) {
		this.offQueue = offQueue;
	}
	
}
