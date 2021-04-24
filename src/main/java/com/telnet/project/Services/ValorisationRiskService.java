package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.ValorisationRisk;

public interface ValorisationRiskService {

	
	Map<String, Boolean> delete(String id);

	List<ValorisationRisk> findAll();

	ValorisationRisk update(ValorisationRisk valorisationRisk);

}
