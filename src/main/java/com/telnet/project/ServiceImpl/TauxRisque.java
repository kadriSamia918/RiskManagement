package com.telnet.project.ServiceImpl;

public class TauxRisque {
	
	private int countResiduel;
	private int countTraite;
	private int countNonTraite;
	private int countAccepte;
	
	public int getCountResiduel() {
		return countResiduel;
	}
	
	public void setCountResiduel(int countResiduel) {
		this.countResiduel = countResiduel;
	}
	public int getCountTraite() {
		return countTraite;
	}
	public void setCountTraite(int countTraite) {
		this.countTraite = countTraite;
	}
	public int getCountNonTraite() {
		return countNonTraite;
	}
	public void setCountNonTraite(int countNonTraite) {
		this.countNonTraite = countNonTraite;
	}
	public int getCountAccepte() {
		return countAccepte;
	}
	public void setCountAccepte(int countAccepte) {
		this.countAccepte = countAccepte;
	}
	public TauxRisque() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TauxRisque(int countResiduel, int countTraite, int countNonTraite, int countAccepte) {
		super();
		this.countResiduel = countResiduel;
		this.countTraite = countTraite;
		this.countNonTraite = countNonTraite;
		this.countAccepte = countAccepte;
	}
	

}
