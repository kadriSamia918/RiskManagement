package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.repository.Query;

import com.telnet.project.Entities.Risque;

public interface RisqueService {

	Map<String, Boolean> delete(String id);
	 
  //  List<Risque> find();
    
    
   

    Risque update(Risque risque);
}
