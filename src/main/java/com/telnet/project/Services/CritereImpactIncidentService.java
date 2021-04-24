package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.CritereImpactIncident;

public interface CritereImpactIncidentService {
	 
		Map<String, Boolean> delete(String id);

	  List<CritereImpactIncident> findAll();


	  Map<String, Boolean> update(CritereImpactIncident CritereImpact);

}
