package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.CritereImpact;

public interface CritereImpactService {

	 
	Map<String, Boolean> delete(String id);

  List<CritereImpact> findAll();


  Map<String, Boolean> update(CritereImpact CritereImpact);
}
