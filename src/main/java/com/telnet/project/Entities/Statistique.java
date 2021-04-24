package com.telnet.project.Entities;

public class Statistique{
	
	String mois;
	String    value;
	public String getMois() {
		return mois;
	}
	public void setMois(String mois) {
		this.mois = mois;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Statistique(String mois, String value) {
		super();
		this.mois = mois;
		this.value = value;
	}
	public Statistique() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

