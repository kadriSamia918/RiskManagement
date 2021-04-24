package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.CritereMenace;
import com.telnet.project.Entities.DegreCriticite;
import com.telnet.project.Entities.FaciliteExplVuln;
import com.telnet.project.Entities.ProbaRisk;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Repository.CritereMenaceRepository;
import com.telnet.project.Repository.DegreCriticiteRepository;
import com.telnet.project.Repository.FaciliteExplVulnRepository;
import com.telnet.project.Repository.ProbaRiskRepository;
import com.telnet.project.Services.ProbaRiskService;

@Service
public class ProbaRiskImpl implements ProbaRiskService {
	
	@Autowired
	private  ProbaRiskRepository probaRiskRepository;
	
	@Autowired
    private  CritereMenaceRepository critereMenaceRepository;
	
	@Autowired
    private  FaciliteExplVulnRepository faciliteExplVulnRepository;
	
	@Autowired
    private DegreCriticiteRepository degreCriticiteRepository;
	
	@Override
	public Map<String, Boolean> delete(String id) {
		return null;
	}
	
	@Override
	public List<ProbaRisk> findAll() {
		return probaRiskRepository.findAll();
	}

	@Override
	public ProbaRisk update(ProbaRisk probaRisk) {
		return null;
	}
	public List<ProbaRisk> updateProbaRiskList(List<ProbaRisk> probaListEntry ){
		
		List<ProbaRisk> listProba = new ArrayList<ProbaRisk>();
		listProba =  probaRiskRepository.findAll(); 
		
		for(int i=0;i<=probaListEntry.size()-1;i++ ) {
		ProbaRisk probaRisk= (ProbaRisk)probaListEntry.toArray()[i];
		for(ProbaRisk prb:listProba)
		{   
		
			if(
				(prb.getFacilExpVul().getNiveau().equals( probaRisk.getFacilExpVul().getNiveau() )) )
			 { 	
			
				if((prb.getValMenace().getNiveau().equals(probaRisk.getValMenace().getNiveau() )	
				)) {
			DegreCriticite degreCriticite=new DegreCriticite();
			degreCriticite=probaRisk.getDegreCriticite();
			prb.setDegreCriticite(degreCriticite);
			}
				}
			
		}}
		
		return probaRiskRepository.saveAll(listProba);
	}
		public List<ProbaRisk> ajoutProbaRiskList(List<ProbaRisk> probaRiskList ){
		return probaRiskRepository.saveAll(probaRiskList);
	}
	public List<ProbaRisk> getProbaByVraissemblance (String criterMenace){
		List<ProbaRisk> list = new ArrayList<ProbaRisk>();
		for(ProbaRisk proba: probaRiskRepository.findAll() ) {
			if(proba.getValMenace().getNiveau().equals(criterMenace)) {
				list.add(proba);
			}
		}
		return list;

	}
	public List<ProbaRisk> getProbaByFaciliteVul (String faciliteVul){
		List<ProbaRisk> list = new ArrayList<ProbaRisk>();
		for(ProbaRisk proba: probaRiskRepository.findAll() ) {
			if(proba.getFacilExpVul().getNiveau().equals(faciliteVul)) {
				list.add(proba);
			}
		}
		return list;

	}
	public Map<String, List<ProbaRisk>> updateProbaByVraissemblance(String vraissemblance, List<ProbaRisk> probaList){
		
		Map<String, List<ProbaRisk>> response = new HashMap<>();
		List<ProbaRisk> allProbaList = new ArrayList<ProbaRisk>();
		allProbaList= getProbaByVraissemblance(vraissemblance);
		for(ProbaRisk proba:probaList )
		{
			for(ProbaRisk allproba:allProbaList) {
				if(allproba.getFacilExpVul().getNiveau().equals(proba.getFacilExpVul().getNiveau())) {
					allproba.setDegreCriticite(proba.getDegreCriticite());
				}
			}
		}
	    probaRiskRepository.saveAll(allProbaList);
		response.put("success", allProbaList);
		
		return response;
	}
	public Map<String, List<ProbaRisk>> updateProbaByfacilte(String faciliteVul, List<ProbaRisk> probaList){
		
		Map<String, List<ProbaRisk>> response = new HashMap<>();
		List<ProbaRisk> allProbaList = new ArrayList<ProbaRisk>();
		allProbaList= getProbaByFaciliteVul(faciliteVul);
		for(ProbaRisk proba:probaList )
		{
			for(ProbaRisk allproba:allProbaList) {
				if(allproba.getValMenace().getNiveau().equals(proba.getValMenace().getNiveau())) {
					allproba.setDegreCriticite(proba.getDegreCriticite());
				}
			}
		}
	    probaRiskRepository.saveAll(allProbaList);
		response.put("success", allProbaList);
		
		return response;
	}
	public ProbaRisk createProbaRisk(ProbaRisk probaRiskEntry) 
	{   
		ProbaRisk probaRisk=new ProbaRisk();
		CritereMenace val_menace=probaRiskEntry.getValMenace();
		//System.out.println(probaRiskEntry.getFacilExpVul());
		FaciliteExplVuln  facilExpVul=probaRiskEntry.getFacilExpVul();
		DegreCriticite degreCriticite=probaRiskEntry.getDegreCriticite();
		probaRisk.setValMenace(critereMenaceRepository.findCritereMenaceByNiveau(val_menace.getNiveau()));
		System.out.println(facilExpVul.getNiveau());
		probaRisk.setFacilExpVul(faciliteExplVulnRepository.
				findFaciliteExplVulnByNiveau(facilExpVul.getNiveau()));
		//System.out.println(probaRisk.getFacilExpVul());
	//	System.out.println(degreCriticite.getDegre());
		probaRisk.setDegreCriticite(degreCriticiteRepository.findDegreCriticiteRepositoryByDegre(degreCriticite.getDegre()));
		return probaRiskRepository.save(probaRisk);
		
	}
}
