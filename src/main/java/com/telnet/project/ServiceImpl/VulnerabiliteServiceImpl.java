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
import org.springframework.stereotype.Service;

import com.mongodb.DBRef;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Menace;
import com.telnet.project.Entities.Vulnerabilite;
import com.telnet.project.Repository.MenaceRepository;
import com.telnet.project.Repository.VulnerabiliteRepository;
import com.telnet.project.Services.VulnerabiliteService;

@Service
public class VulnerabiliteServiceImpl implements VulnerabiliteService{

	@Autowired
	private VulnerabiliteRepository vulnerabiliteRepository;
	
	@Autowired
	private MenaceRepository menaceRepository;
	
	 @Autowired
	 MongoTemplate mongoTemplate; 
	 
	@Override
	public  Vulnerabilite createVulnerabilite(Vulnerabilite vulnerabiliteEntry) {
		System.out.println("hi");
		 Map<String, Boolean> response = new HashMap<>();
		 Menace menace = new Menace();
		 menace = menaceRepository.findMenaceByCode(vulnerabiliteEntry.getMenace());
		 
		Vulnerabilite vulnerabilite= new Vulnerabilite();
		long count=vulnerabiliteRepository.count()+1;
    	char familleChar = menace.getFamille().charAt(0);
    	
		vulnerabilite.setCode("V"+familleChar+count);
		vulnerabilite.setDescription(vulnerabiliteEntry.getDescription());
		vulnerabilite.setFamille(vulnerabiliteEntry.getFamille());
		vulnerabilite.setMenace(vulnerabiliteEntry.getMenace());
		vulnerabiliteRepository.save(vulnerabilite);
	        response.put("vulnerabilite Ajoutée", Boolean.TRUE);
	        Query query = new Query();
	        query.addCriteria(Criteria.where("id").is(new ObjectId(menace.getId())));
	    	Update update = new Update().push("vulnerabilite", new DBRef("Vulnerabilite", new ObjectId(vulnerabilite.getId())));
	    	mongoTemplate.updateMulti(query, update, Menace.class);

	        return vulnerabilite;
		
		/*else {
		        response.put("Le Code de vulnerabilité existe déja", Boolean.FALSE);
		        return vulnerabilite;
		}*/
		
	}

	@Override
	public Map<String, Boolean> delete(String id) {
		Vulnerabilite deleted = vulnerabiliteRepository.findVulnerabiliteById(id);
		Query query = Query.query(Criteria.where("$id").is(new ObjectId(deleted.getId())));
		Update update = new Update().pull("vulnerabilite", query);
		mongoTemplate.updateMulti(new Query(), update, Famille.class);
		mongoTemplate.updateMulti(new Query(), update, Menace.class);
		vulnerabiliteRepository.delete(deleted);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}
	



	@Override
	public List<Vulnerabilite> findAll() {
		return vulnerabiliteRepository.findAll();
	}

	@Override
	public Vulnerabilite update(Vulnerabilite vulenerabilite) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Vulnerabilite> affichVulnerabiliteParFamille(@Valid Famille nomFamille) {
		List<Vulnerabilite> listVulnerabilite=new ArrayList<Vulnerabilite>();
		List<Vulnerabilite> listVulnerabiliteAffiche=new ArrayList<Vulnerabilite>();
		listVulnerabilite=vulnerabiliteRepository.findAll();
		for(Vulnerabilite vulnerabilite:listVulnerabilite) {
			
			if(vulnerabilite.getFamille().equals(nomFamille.getNom()))
			{
				
				listVulnerabiliteAffiche.add(vulnerabilite);
			}
		}
		
		;
		
		return listVulnerabiliteAffiche;
	}

}
