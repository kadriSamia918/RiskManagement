package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.Entities.EvenementLog;

public interface EvenementLogService {

	List<EvenementLog> findLogByEvenementId(String evenementId);

}
