package com.telnet.project.webSocket;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Notification")
public class Notification {
	private String id;
	private String message;
    private String transactionId;
    private String senderId;
    private String recieverId;
    private Boolean seen;
    
    
	public String getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public void setMessage(String content) {
		this.message = content;
	}

	public String getMessage() {
		return this.message;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getRecieverId() {
		return recieverId;
	}

	public void setRecieverId(String recieverId) {
		this.recieverId = recieverId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean getSeen() {
		return seen;
	}

	public void setSeen(Boolean seen) {
		this.seen = seen;
	}

	public Notification(String id, String message, String transactionId, String senderId, String recieverId,
			Boolean seen) {
		super();
		this.id = id;
		this.message = message;
		this.transactionId = transactionId;
		this.senderId = senderId;
		this.recieverId = recieverId;
		this.seen = seen;
	}


	
}