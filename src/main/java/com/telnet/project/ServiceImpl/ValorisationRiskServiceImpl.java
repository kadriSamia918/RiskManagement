package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.CritereImpact;
import com.telnet.project.Entities.DegreRisque;
import com.telnet.project.Entities.ProbaRisk;
import com.telnet.project.Entities.ValorisationRisk;
import com.telnet.project.Repository.CritereImpactRepository;
import com.telnet.project.Repository.DegreRisqueRepository;
import com.telnet.project.Repository.ProbaRiskRepository;
import com.telnet.project.Repository.ValorisationRiskRepository;
import com.telnet.project.Services.ValorisationRiskService;

@Service
public class ValorisationRiskServiceImpl implements ValorisationRiskService{

	@Autowired
	private ValorisationRiskRepository valorisationRiskRepository;
	
	@Autowired
	private ProbaRiskRepository probaRiskRepository;
	
	@Autowired
	private DegreRisqueRepository degreRisqueRepository;
	@Autowired
	private CritereImpactRepository critereImpactRepository;
	@Override
	public Map<String, Boolean> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ValorisationRisk> findAll() {
		return valorisationRiskRepository.findAll();
	}

	@Override
	public ValorisationRisk update(ValorisationRisk valorisationRisk) {
		// TODO Auto-generated method stub
		return null;
	}

	public ValorisationRisk createProbaRisk(@Valid ValorisationRisk valorisationRiskEntry) {
		
		ValorisationRisk valorisationRisk=new ValorisationRisk();
		
		CritereImpact criterImpact=valorisationRiskEntry.getCriterImpact();
		
		valorisationRisk.setCriterImpact(critereImpactRepository.findCriterImpactByNiveau(criterImpact.getNiveau()));
	//	System.out.println(valorisationRisk.getCriterImpact().getNiveau());
		List<ProbaRisk> listProbaRiskEntry = new ArrayList<ProbaRisk>();
		List<ProbaRisk> listProbaRisk = new ArrayList<ProbaRisk>();
		listProbaRisk= valorisationRiskEntry.getProbaRiskList();
		for(ProbaRisk pr:listProbaRiskEntry)
		{   
			
			listProbaRisk.add(probaRiskRepository.findProbaRiskById(pr.getId()));
			
		}
		valorisationRisk.setProbaRiskList(listProbaRisk);
		DegreRisque degreRisk=valorisationRiskEntry.getDegreRisk();
		
		valorisationRisk.setDegreRisk(degreRisqueRepository.findDegreRisqueByNiveau(degreRisk.getNiveau()));
		valorisationRiskRepository.save(valorisationRisk);
		return valorisationRisk;
	}
		public List<ValorisationRisk> createListValorisation(List<ValorisationRisk> valorisationListEntry){
			List<ValorisationRisk> finalList = new ArrayList<ValorisationRisk>();
			System.out.println("here");
			for(ValorisationRisk val:valorisationListEntry) {
			ValorisationRisk valorisation = new ValorisationRisk();
			valorisation.setCriterImpact(val.getCriterImpact());
			valorisation.setDegreRisk(val.getDegreRisk());
			List<ProbaRisk> listProba = new ArrayList<ProbaRisk>();
			List<ProbaRisk> listProbaSaved = new ArrayList<ProbaRisk>();
			listProba= probaRiskRepository.findAll();
			for(ProbaRisk proba:listProba) {
				if(proba.getDegreCriticite().getDegre() == val.getProbaRiskList().get(0).getDegreCriticite().getDegre()) 
				{
					listProbaSaved.add(proba);
				}
				
			}
			valorisation.setProbaRiskList(listProbaSaved);
			valorisationRiskRepository.save(valorisation);
			finalList.add(valorisationRiskRepository.save(valorisation));
			}
			return  finalList;
		}
		
		public List<ValorisationRisk> getValorisationByImpact (String criterImpact){
			List<ValorisationRisk> list = new ArrayList<ValorisationRisk>();
			for(ValorisationRisk proba: valorisationRiskRepository.findAll() ) {
				if(proba.getCriterImpact().getNiveau().equals(criterImpact)) {
					list.add(proba);
				}
			}
			return list;

		}
		public List<ValorisationRisk> setValorisationByImpact (String criterImpact, List<ValorisationRisk> valorisationList){
			List<ValorisationRisk> list = new ArrayList<ValorisationRisk>();
			for(ValorisationRisk proba: valorisationRiskRepository.findAll() ) {
				if(proba.getCriterImpact().getNiveau().equals(criterImpact)) {
					list.add(proba);
				}
			}
			
			return list;
		}
		public Map<String, List<ValorisationRisk>> updateValorisationByImpact(String impact, List<ValorisationRisk> probaList){
			
			Map<String,List<ValorisationRisk>> response = new HashMap<>();
			List<ValorisationRisk> allProbaList = new ArrayList<ValorisationRisk>();
			allProbaList= getValorisationByImpact(impact);
			for(ValorisationRisk proba:probaList )
			{
				for(ValorisationRisk allproba:allProbaList) {
					if((allproba.getProbaRiskList().get(0).getDegreCriticite().getDegre()) == ((proba.getProbaRiskList().get(0).getDegreCriticite().getDegre()))){
						System.out.println("hi");
						allproba.setDegreRisk(proba.getDegreRisk());
					}
				}
			}
		   valorisationRiskRepository.saveAll(allProbaList);
			response.put("success",allProbaList);
			
			return response;
		}
	public List<ValorisationRisk> updateValorisationList(List<ValorisationRisk> valorisationListEntry){
		List<ValorisationRisk> listValorisation = new ArrayList<ValorisationRisk>();
		listValorisation= valorisationRiskRepository.findAll();
		System.out.println("yes");
		for (ValorisationRisk entry:valorisationListEntry) {
			for(ValorisationRisk val:listValorisation) {
				System.out.println("yes1");
				System.out.println(entry.getCriterImpact().getNiveau());
				List<ProbaRisk> listProba = new ArrayList<ProbaRisk>();
				listProba=val.getProbaRiskList();
				List<ProbaRisk> listProbaEntry = new ArrayList<ProbaRisk>();
				listProbaEntry=entry.getProbaRiskList();
				if(val.getCriterImpact().getNiveau().equals(entry.getCriterImpact().getNiveau())
					&&
					listProbaEntry.get(0).getDegreCriticite().getDegre() == listProba.get(0).getDegreCriticite().getDegre())
				{
					System.out.println("yes2");
					DegreRisque degreRisk = new DegreRisque();
					degreRisk=entry.getDegreRisk();
					System.out.println(val.getDegreRisk().getNiveau());
					val.setDegreRisk(degreRisk);
					val.setCriterImpact(entry.getCriterImpact());
					
					//valorisationRiskRepository.save(val);
					
				}
			}
		}
		return valorisationRiskRepository.saveAll(listValorisation) ;
	}
}
