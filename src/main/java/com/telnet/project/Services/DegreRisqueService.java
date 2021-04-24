package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import com.telnet.project.Entities.DegreRisque;

public interface DegreRisqueService {
	
	Map<String, Boolean> delete(String id);
	 
    List<DegreRisque> findAll();
 

    DegreRisque update(DegreRisque degreRisque);

}
