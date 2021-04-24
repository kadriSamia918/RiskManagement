package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Personne;

public interface ActifService {
	List<Actif> findAll();
	List<Actif> findActifByProprietaire(Personne proprietaire);
	List<Actif> changeProprietaire(List<Actif> actifList,Personne proprietaire);
	Map<String, Boolean> delete(String id);
}
