package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.DBRef;
import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.AttributActif;
import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Personne;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Entities.Vulnerabilite;
import com.telnet.project.Repository.ActifRepository;
import com.telnet.project.Repository.Echelle_CriticiteRepository;
import com.telnet.project.Repository.FamilleRepository;
import com.telnet.project.Repository.PersonneRepository;
import com.telnet.project.Repository.SousFamilleRepository;
import com.telnet.project.Repository.VulnerabiliteRepository;
import com.telnet.project.Services.ActifService;

@Service
public class ActifServiceImpl implements ActifService {

	 @Autowired
	 MongoTemplate mongoTemplate; 
	 
	@Autowired
	private ActifRepository actifRepository;
	
	@Autowired
	private VulnerabiliteRepository vulnerabiliteRepository;
	
	
	@Autowired
	private FamilleRepository familleRepository;
	
	@Autowired
	private SousFamilleRepository sousFamilleRepository;
	@Autowired
	private Echelle_CriticiteRepository echelle_CriticiteRepository; 
	
	@Autowired
	private PersonneRepository personneRepository;
	
	@Override
	public List<Actif> findAll() {
		return actifRepository.findAll();
		
	}
	@Override
	public List<Actif> findActifByProprietaire(Personne proprietaire) {
		
		return actifRepository.findActifByProprietaire(proprietaire);
	}
	@Override
	public List<Actif> changeProprietaire(List<Actif> actifList, Personne proprietaire) {
		
		for(Actif actif:actifList) {
			
			actif.setProprietaire(proprietaire);
		}
		
	return actifList;
	}
	public List<Actif> afficherActifParFamille(Famille familleEntry) {
		   
		   Famille	famille = familleRepository.findFamilleByNom(familleEntry.getNom());
		   List<Actif> actifs = famille.getActifs();
		   System.out.println(famille.getActifs());
	        return famille.getActifs();
	}
	
	public Actif ajoutActifTree(Actif actifEntry, String nomFamille,String nomSousFamille) {
			System.out.println("tree");
			Famille famille = familleRepository.findFamilleByNom(nomFamille);
			System.out.println(famille.getNom());
			List<AttributActif> listAttr = new ArrayList<AttributActif>();
	    	System.out.println(actifEntry.getProprietaire().getName());
	    	listAttr= famille.getAttributs();
	    	//List<Actif> listActif2 = new ArrayList<Actif>();
	        System.out.println(actifEntry.getData());
	        HashMap<String, String> data = new HashMap<String, String> ();
	        System.out.println(listAttr == null);
	    	for(AttributActif attr:listAttr) {
	    	data.put(attr.getNom(), actifEntry.getData().get(attr.getNom()));
	    	}
	    	actifEntry.setData(data);
	    	Personne prop= personneRepository.findPersonneByName(actifEntry.getProprietaire().getName());
	    	actifEntry.setProprietaire(prop);
	       // System.out.println(ec.getCouleur());
	       /// echelle_CriticiteRepository.findEchelle_CriticiteByDegre(actifEntry.getConfidentialite().getDegre());
	        actifEntry.setConfidentialite( echelle_CriticiteRepository.findEchelleById(actifEntry.getConfidentialite().getId()));
	        echelle_CriticiteRepository.findEchelleById(actifEntry.getDisponibilite().getId());
	        actifEntry.setDisponibilite(echelle_CriticiteRepository.findEchelle_CriticiteById(actifEntry.getDisponibilite().getId()));
	        echelle_CriticiteRepository.findEchelleById(actifEntry.getIntegrite().getId());
	        actifEntry.setIntegrite(echelle_CriticiteRepository.findEchelleById(actifEntry.getIntegrite().getId()));
    	    //   actifRepository.save(actifEntry);
	        Actif acy=  actifRepository.save(actifEntry);
	    	List<Actif> listActif = new ArrayList<Actif>();
	    	/*if(nomSousFamille == "") {
	    		   
	   	    	listActif=famille.getActifs();
	   	        listActif.add(acy);
	   	        familleRepository.save(famille);
	    	}*/
	    	 	SousFamille sousFamille = sousFamilleRepository.findSousFamilleByNom(nomSousFamille);
	    		
	    	 	//listActif=sousFamille.getActifs();	
	    		listActif.add(acy);
	    		System.out.println(sousFamille.getActifs());
	    		sousFamille.getActifs().add(acy);
	    		sousFamilleRepository.save(sousFamille);
	      
	    	
	   
	    return acy;
	}
	public Actif ajoutActif(Actif actifEntry, String nomFamille) {
		
		Famille famille = familleRepository.findFamilleByNom(nomFamille);
		System.out.println(famille.getNom());
		List<AttributActif> listAttr = new ArrayList<AttributActif>();
    	listAttr= famille.getAttributs();
        System.out.println(actifEntry.getData());
        HashMap<String, String> data = new HashMap<String, String> ();
    	for(AttributActif attr:listAttr) {
    	data.put(attr.getNom(), actifEntry.getData().get(attr.getNom()));
    	}
    	actifEntry.setData(data);
    	Personne prop= personneRepository.findPersonneByName(actifEntry.getProprietaire().getName());
    	actifEntry.setProprietaire(prop);
        actifEntry.setConfidentialite( echelle_CriticiteRepository.findEchelleById(actifEntry.getConfidentialite().getId()));
        echelle_CriticiteRepository.findEchelleById(actifEntry.getDisponibilite().getId());
        actifEntry.setDisponibilite(echelle_CriticiteRepository.findEchelle_CriticiteById(actifEntry.getDisponibilite().getId()));
        echelle_CriticiteRepository.findEchelleById(actifEntry.getIntegrite().getId());
        actifEntry.setIntegrite(echelle_CriticiteRepository.findEchelleById(actifEntry.getIntegrite().getId()));
        Actif acy=  actifRepository.save(actifEntry);
    	List<Actif> listActif = new ArrayList<Actif>();
    	System.out.println(actifEntry.getVulnerabiliteList() == null);
    	if(actifEntry.getVulnerabiliteList() != null ) {
    	
    		List<Vulnerabilite> listVulnerabilite = new ArrayList<Vulnerabilite>();
    		listVulnerabilite=actifEntry.getVulnerabiliteList();
    		for(Vulnerabilite vl:listVulnerabilite) {
    			Vulnerabilite vulnerabilite = new Vulnerabilite();
    			vulnerabilite=vulnerabiliteRepository.findVulnerabiliteById(vl.getId());
    			System.out.println(vulnerabilite.getId());
    			List<Actif> actifList = new ArrayList<Actif>();
    			if(vulnerabilite.getActifList() != null) {
    				actifList=vulnerabilite.getActifList();
    			}
    			System.out.println(acy.getId()+"here");
    			actifList.add(acy);
    			
    			vulnerabilite.setActifList(actifList);
    			vulnerabiliteRepository.save(vulnerabilite);
    			//System.out.println(vl.());
    		}
    

    	}
    	Query query = new Query();
    	query.addCriteria(Criteria.where("id").is(new ObjectId(famille.getId())));
    	Update update = new Update().push("actif", new DBRef("Actif", new ObjectId(acy.getId())));
    	mongoTemplate.updateMulti(query, update, Famille.class);
    return acy;
}
	
public Actif updateActif(Actif actifEntry) {
	System.out.println( "***********i*******");
			Actif actif = actifRepository.findActifById(actifEntry.getId());
			System.out.println(actif.getId()+ "***********i*******");
			Personne prop= personneRepository.findPersonneByName(actifEntry.getProprietaire().getName());
			actif.setProprietaire(prop);
		    ///System.out.println(actif.getId());
	    	actif.setData(actifEntry.getData());
	        Echelle_Criticite ec=echelle_CriticiteRepository.findEchelle_CriticiteById(actifEntry.getConfidentialite().getId());
	     
	       /// echelle_CriticiteRepository.findEchelle_CriticiteByDegre(actifEntry.getConfidentialite().getDegre());
	        actif.setConfidentialite(ec);
	        
	        Echelle_Criticite ei=echelle_CriticiteRepository.findEchelle_CriticiteById(actifEntry.getIntegrite().getId());
		     
		       /// echelle_CriticiteRepository.findEchelle_CriticiteByDegre(actifEntry.getConfidentialite().getDegre());
		        actif.setIntegrite(ei);
		        
		        Echelle_Criticite ed=echelle_CriticiteRepository.findEchelle_CriticiteById(actifEntry.getDisponibilite().getId());
			     
			       /// echelle_CriticiteRepository.findEchelle_CriticiteByDegre(actifEntry.getConfidentialite().getDegre());
			        actif.setDisponibilite(ed);
	     //   actif.setDisponibilite(actifEntry.getDisponibilite());
	     //   actif.setIntegrite(actifEntry.getIntegrite());
	        
	    	if(actifEntry.getVulnerabiliteList() != null ) {
	        	
	    		List<Vulnerabilite> listVulnerabilite = new ArrayList<Vulnerabilite>();
	    		listVulnerabilite=actifEntry.getVulnerabiliteList();
	    		actifRepository.save(actif);
	    		for(Vulnerabilite vl:listVulnerabilite) {
	    			Vulnerabilite vulnerabilite = new Vulnerabilite();
	    			vulnerabilite=vulnerabiliteRepository.findVulnerabiliteById(vl.getId());
	    			System.out.println(vulnerabilite.getId());
	    			List<Actif> actifList = new ArrayList<Actif>();
	    			if(vulnerabilite.getActifList() != null) {
	    				actifList=vulnerabilite.getActifList();
	    			}
	    			System.out.println(actif.getId()+"here");
	    			actifList.add(actif);
	    			
	    			vulnerabilite.setActifList(actifList);
	    			vulnerabiliteRepository.save(vulnerabilite);
	    			//System.out.println(vl.());
	    		}
	    

	    	}
	        actifRepository.save(actif);
	    return actif;
	}
@Override
	public Map<String, Boolean> delete(String id) 
{
		Actif deleted = actifRepository.findActifById(id);
	    
		Query query = Query.query(Criteria.where("$id").is(new ObjectId(deleted.getId())));
		Update update = new Update().pull("actif", query);
		mongoTemplate.updateMulti(new Query(), update, SousFamille.class);
		mongoTemplate.updateMulti(new Query(), update, Famille.class);
		actifRepository.delete(deleted);
		
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}
public List<Actif> updateActifProprietaireList(List<Actif> actifEntrys,String id) {
	
	Personne personne=personneRepository.findPersonneById(id);
	System.out.println(personne.getName());
	 List<Actif> listActifUpdated = new ArrayList<Actif>();
	for(Actif actif:actifEntrys) {
		Actif updated=actifRepository.findActifById(actif.getId());
		System.out.println(actif.getId());
		updated.setProprietaire(personne);
		actifRepository.save(updated);
		listActifUpdated.add(updated);
	}
	return listActifUpdated;
}
public long countAllActif() {
	long a;
	a =actifRepository.count();
	return a;
}

}
