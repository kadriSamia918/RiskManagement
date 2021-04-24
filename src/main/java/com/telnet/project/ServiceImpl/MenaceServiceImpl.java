package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Menace;
import com.telnet.project.Entities.SourceMenace;
import com.telnet.project.Entities.TypeMenace;
import com.telnet.project.Entities.Vulnerabilite;
import com.telnet.project.Repository.MenaceRepository;
import com.telnet.project.Repository.SourceMenaceRepository;
import com.telnet.project.Repository.TypeMenaceRepository;
import com.telnet.project.Repository.VulnerabiliteRepository;
import com.telnet.project.Services.MenaceService;

@Service
public class MenaceServiceImpl implements MenaceService{

	 @Autowired
	 private MenaceRepository menaceRepository;
	
	 @Autowired
	 private TypeMenaceRepository typeMenaceRepository;
	 
	 @Autowired
	 private SourceMenaceRepository sourceMenaceRepository;
	 
	 @Autowired
	 private VulnerabiliteRepository vulnerabiliteRepository;
	 
	 @Autowired
	 MongoTemplate mongoTemplate; 
	 
	@Override
	public Map<String, Menace> createMenace(Menace menaceEntry) {
		Map<String, Menace> response = new HashMap<>();
		 Menace menace= new Menace();
		 	long count = menaceRepository.count( )+1;
		 	
		 	String code="M"+count;
			
			menace.setCode(code);
			menace.setDescription(menaceEntry.getDescription());
			menace.setFamille(menaceEntry.getFamille());
			SourceMenace sourceMenace = new SourceMenace();
			sourceMenace=sourceMenaceRepository.findSourceMenaceByDescription(menaceEntry.getSource().getDescription());
			
			menace.setSource(sourceMenace);
			TypeMenace typeMenace = new TypeMenace();
			typeMenace=typeMenaceRepository.findTypeMenaceByDescription(menaceEntry.getType().getDescription());
			menace.setType(typeMenace);
			List<Vulnerabilite> vlEntry=new ArrayList<Vulnerabilite>();
			List<Vulnerabilite> vlist=new ArrayList<Vulnerabilite>();
			vlEntry=menaceEntry.getVulnerabilite();
			if(vlEntry != null) {
			for(Vulnerabilite vl:vlEntry) {
				
				vlist.add(vulnerabiliteRepository.findVulnerabiliteByCode(vl.getCode()));
				
			}
			menace.setVulnerabilite(vlist);}
			menace.setDelibere(menaceEntry.isDelibere());
			menace.setAccidentelle(menaceEntry.isAccidentelle());
			menace.setEnvironnementale(menaceEntry.isEnvironnementale());
			System.out.println(menaceEntry.isRetenu());
			menace.setRetenu(menaceEntry.isRetenu());
			menaceRepository.save(menace);
			
		        response.put("Menace_Ajoutée",menace);
		        System.out.println(response);
		        return response ;
		        
			
		/*	else {
			        response.put("Menace_Existe", null);
			        System.out.println(response);
			        return response;
			}*/
	}
	
	@Override
	public Map<String, Boolean> delete(String id) {
		Menace deleted = menaceRepository.findMenaceById(id);
		Query query = Query.query(Criteria.where("$id").is(new ObjectId(deleted.getId())));
		Update update = new Update().pull("menace", query);
		mongoTemplate.updateMulti(new Query(), update, Famille.class);
		menaceRepository.delete(deleted);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}
	public List<Menace>afficheMenaceParFamille(Famille nomFamille) {
		List<Menace> listMenace=new ArrayList<Menace>();
		List<Menace> listMenaceAffiche=new ArrayList<Menace>();
		listMenace=menaceRepository.findAll();
		for(Menace menace:listMenace) {
			System.out.println( menace.getFamille().toString());
			
			if(menace.getFamille().equals(nomFamille.getNom()))
			{
				
				listMenaceAffiche.add(menace);
			}
		}
		System.out.println(listMenaceAffiche.size());
		;
		
		return listMenaceAffiche;
	}
	
	@Override
	public List<Menace> findAll() {
		
		return menaceRepository.findAll();
	}
   
	@Override
	public Menace update(Menace famille) {
		// TODO Auto-generated method stub
		return null;
	}


}
