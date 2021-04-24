package com.telnet.project.Entities;

public class IncidentImpact {
	
	private String name;
	private int value;
	public String getName() {
		return name;
	}
	public void setName(String impact) {
		this.name = impact;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public IncidentImpact(String impact, int value) {
		super();
		this.name = impact;
		this.value = value;
	}
	public IncidentImpact() {
		super();
	}
	
	

}




