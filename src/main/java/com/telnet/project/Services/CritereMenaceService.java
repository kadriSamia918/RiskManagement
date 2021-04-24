package com.telnet.project.Services;

import java.util.List;
import java.util.Map;
import com.telnet.project.Entities.CritereMenace;

public interface CritereMenaceService {

	
	 
	Map<String, Boolean> delete(String id);

   List<CritereMenace> findAll();


   Map<String, Boolean> update(CritereMenace CritereMenace);
}
