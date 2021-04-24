package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.FaciliteExplVuln;

public interface FaciliteExplVulnService {
	
	 
		Map<String, Boolean> delete(String id);

	   List<FaciliteExplVuln> findAll();


	   Map<String, Boolean> update(FaciliteExplVuln faciliteExplVuln);

}
