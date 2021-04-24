package com.telnet.project.Entities;

public class Change {
	private String prevValue;
	private String newValue;
	private String column;
	private String date;

	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrevValue() {
		return prevValue;
	}
	public void setPrevValue(String prevValue) {
		this.prevValue = prevValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public Change() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Change(String prevValue, String newValue, String column, String date) {
		super();
		this.prevValue = prevValue;
		this.newValue = newValue;
		this.column = column;
		this.date = date;
	}
	
	
}
