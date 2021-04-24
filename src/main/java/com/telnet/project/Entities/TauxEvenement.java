package com.telnet.project.Entities;

public class TauxEvenement {
	


		
		private int countEvenementIsole;
		private int countIncident;
		private int countTraitement;
		public int getCountEvenementIsole() {
			return countEvenementIsole;
		}
		public void setCountEvenementIsole(int countEvenementIsole) {
			this.countEvenementIsole = countEvenementIsole;
		}
		public int getCountIncident() {
			return countIncident;
		}
		public void setCountIncident(int countIncident) {
			this.countIncident = countIncident;
		}
		public int getCountTraitement() {
			return countTraitement;
		}
		public void setCountTraitement(int countTraitement) {
			this.countTraitement = countTraitement;
		}
		public TauxEvenement(int countEvenementIsole, int countIncident, int countTraitement) {
			super();
			this.countEvenementIsole = countEvenementIsole;
			this.countIncident = countIncident;
			this.countTraitement = countTraitement;
		}
		public TauxEvenement() {
			super();
		}
		
		
	}
