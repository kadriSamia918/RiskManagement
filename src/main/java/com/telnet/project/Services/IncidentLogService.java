package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.Entities.IncidentLog;

public interface IncidentLogService {
	List<IncidentLog> findLogByIncidentId(String incidentId);

}
