package com.telnet.project.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.DegreCriticite;
import com.telnet.project.Entities.ProbaRisk;
import com.telnet.project.Repository.CritereMenaceRepository;
import com.telnet.project.Repository.DegreCriticiteRepository;
import com.telnet.project.Services.DegreCriticiteService;

@Service
public class DegreCriticiteImpl implements DegreCriticiteService {
	

	@Autowired
    private  DegreCriticiteRepository degreCriticiteRepository;

	@Override
	public Map<String, Boolean> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DegreCriticite> findAll() {
		return degreCriticiteRepository.findAll();
	}

	@Override
	public DegreCriticite update(DegreCriticite degreCriticite) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public DegreCriticite createDegreCriticite( DegreCriticite  degreCriticite) {
		return  degreCriticiteRepository.save(degreCriticite);
		
		
	}

}
