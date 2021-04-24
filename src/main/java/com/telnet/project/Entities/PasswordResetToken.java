package com.telnet.project.Entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document

public class PasswordResetToken {
	public PasswordResetToken(String token, User user) {
		super();
		this.token = token;
		this.user = user;
	}

	private static final int EXPIRATION = 60 * 24;
	 
    @Id
    private String id;
 
    private String token;
 
@DBRef
    private User user;
 
    private Date expiryDate;

	public PasswordResetToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public PasswordResetToken(String id, String token, User user, Date expiryDate) {
		super();
		this.id = id;
		this.token = token;
		this.user = user;
		this.expiryDate = expiryDate;
	}




	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public static int getExpiration() {
		return EXPIRATION;
	}
    
    
    
    
}
