package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.Famille;

public interface EvenementDetailService {
	Map<String, Boolean> delete(String id);

	EvenementDetail setEtat(EvenementDetail evenement);

	EvenementDetail updateEtat(EvenementDetail evenement);

	List<EvenementDetail> afficheIncidentParFamille(@Valid Famille nomFamille);


}
