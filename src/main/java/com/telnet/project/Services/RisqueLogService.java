package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.Entities.RisqueLog;

public interface RisqueLogService {
	
	List<RisqueLog> findAll();

	List<RisqueLog> findLogByRisqueId(String risqueId);

}
