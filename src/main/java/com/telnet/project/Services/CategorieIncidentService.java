package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.CategorieIncident;

public interface CategorieIncidentService {
	List<CategorieIncident> findAll();

	Map<String, Boolean> delete(String id);

}
