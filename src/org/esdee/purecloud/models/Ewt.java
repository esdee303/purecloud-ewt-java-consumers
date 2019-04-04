package org.esdee.purecloud.models;



public class Ewt {
	public String _id;
	private boolean success;
	private String timestamp;
	private String callStartDateTime;
	private String callAni;
	private String calledAddressOriginal;
	private String interactionId;
	private String callCurrentQueue;
	private String realWaitTime;
	private String callEstWaitTime;
	private String apiEstWaitTimeInSeconds;
	private String callPositionInQueue;
	private String callLanguage;
	
	public Ewt() {}
	
	
	public String getId() {
		return _id;
	}
	
	public void setId(String id) {
		this._id = id;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	public String getCallStartDateTime() {
		return callStartDateTime;
	}
	
	public void setCallStartDateTime(String callStartDateTime) {
		this.callStartDateTime = callStartDateTime;
	}
	
	public String getCallAni() {
		return callAni;
	}
	
	public void setCallAni(String callAni) {
		this.callAni = callAni;
	}
	
	public String getCalledAddressOriginal() {
		return calledAddressOriginal;
	}
	
	public void setCalledAddressOriginal(String calledAddressOriginal) {
		this.calledAddressOriginal = calledAddressOriginal;
	}
	
	public String getInteractionId() {
		return interactionId;
	}
	
	public void setInteractionId(String interactionId) {
		this.interactionId = interactionId;
	}
	
	public String getCallCurrentQueue() {
		return callCurrentQueue;
	}
	
	public void setCallCurrentQueue(String callCurrentQueue) {
		this.callCurrentQueue = callCurrentQueue;
	}
	
	public String getRealWaitTime() {
		return realWaitTime;
	}
	
	public void setRealWaitTime(String realWaitTime) {
		this.realWaitTime = realWaitTime;
	}
	
	public String getCallEstWaitTime() {
		return callEstWaitTime;
	}
	
	public void setCallEstWaitTime(String callEstWaitTime) {
		this.callEstWaitTime = callEstWaitTime;
	}
	
	public String getApiEstWaitTimeInSeconds() {
		return apiEstWaitTimeInSeconds;
	}
	
	public void setApiEstWaitTimeInSeconds(String apiEstWaitTimeInSeconds) {
		this.apiEstWaitTimeInSeconds = apiEstWaitTimeInSeconds;
	}
	
	public String getCallPositionInQueue() {
		return callPositionInQueue;
	}
	
	public void setCallPositionInQueue(String callPositionInQueue) {
		this.callPositionInQueue = callPositionInQueue;
	}
	
	public String getCallLanguage() {
		return callLanguage;
	}
	
	public void setCallLanguage(String callLanguage) {
		this.callLanguage = callLanguage;
	}
	
	
}
