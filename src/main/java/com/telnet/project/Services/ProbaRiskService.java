package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.ProbaRisk;

public interface ProbaRiskService {
	
	 
	Map<String, Boolean> delete(String id);

	List<ProbaRisk> findAll();

    ProbaRisk update(ProbaRisk probaRisk);

}
