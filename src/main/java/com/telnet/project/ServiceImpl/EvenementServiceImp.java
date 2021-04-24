package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.DBRef;
import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.AttributActif;
import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.Entities.Category;
import com.telnet.project.Entities.Departement;
import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.Menace;
import com.telnet.project.Entities.Personne;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.RisqueDetail;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Entities.Statistique;
import com.telnet.project.Entities.TauxEvenement;
import com.telnet.project.Entities.User;
import com.telnet.project.Entities.Vulnerabilite;
import com.telnet.project.Repository.ActifRepository;
import com.telnet.project.Repository.EvenementDetailRepository;
import com.telnet.project.Repository.EvenementRepository;
import com.telnet.project.Services.EvenementService;
import com.telnet.project.webSocket.Notification;

@Service
public class EvenementServiceImp implements EvenementService {
	
	 @Autowired
	 MongoTemplate mongoTemplate; 
	 
	@Autowired
	private EvenementRepository evenementRepository;
	@Autowired
	

	private EvenementDetailRepository evenementDetailRepository;


	@Override
	public List<Evenement> findAll() {
		
		return evenementRepository.findAll();
		
	}


	@Override


	public List<Evenement> findEvenementByCategory(@Valid CategorieIncident categorie) {
		
		return evenementRepository.findEvenementByCategorie(categorie);
	}

	

	@Override
	public List<Evenement> findEvenementByDetecteur(String detecteur) {
		
		return evenementRepository.findEvenementByDetecteur(detecteur);
		
		
		
	}


	public Map<String, Evenement> ajoutEvenement(@Valid Evenement evenementEntry) {
		Map<String, Evenement> response = new HashMap<>();
		Evenement evenement = new Evenement();
		String code = evenementEntry.getCode();
		CategorieIncident categorie = evenementEntry.getCategorie();

		String source = evenementEntry.getSource();
		String departement = evenementEntry.getDepartement();
		String type = evenementEntry.getType();

		String description = evenementEntry.getDescription();
		String date = evenementEntry.getDate();
		String detecteur = evenementEntry.getDetecteur();
		evenement.setCode(code);
		evenement.setDate(date);
		evenement.setType(type);
		evenement.setDepartement(departement);
		evenement.setCategorie(categorie);

		evenement.setDescription(description);
		evenement.setDetecteur(detecteur);
	evenement.setSource(source);

		System.out.println("Code egal"+code);
		System.out.println(evenementRepository.findEvenementByCode(code));
		
	
		evenementRepository.save(evenement);
		 response.put("evenement_Ajoutée",evenement);
		 System.out.println(response);
	  /*      if (LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY){*/
	  
		return response;}

	 public TauxEvenement countTauxEvenement() {
		 TauxEvenement tauxEvenement=new TauxEvenement();
		 List<Evenement> evenements = new ArrayList<Evenement>();
		  int countEvenementIsolé=0;
		  int countIncident=0;
		  int countTraitement=0;
		
	    evenements=findAll();
	    System.out.print(evenements);
	    	for(Evenement evenement:evenements) {
	    	   

	    		if(evenement.getType().equalsIgnoreCase("Incident")) {
	    			countIncident++;
	    		}
	    		
	    		if(evenement.getType().equalsIgnoreCase("Traitement") ){
	    		
	    			countTraitement++;
	    		}
	    		if(evenement.getType().equalsIgnoreCase("Evenement")) {
	    			countEvenementIsolé++;
	    		}}
	    		
	    		tauxEvenement.setCountEvenementIsole(countEvenementIsolé);
	    		tauxEvenement.setCountTraitement(countTraitement);
	    		tauxEvenement.setCountIncident(countIncident);;
		 
		 return tauxEvenement;
	 }

    @Override


		public Evenement updateType(Evenement evenement) {
			 Optional< Evenement > evenementDb = this.evenementRepository.findById(evenement.getId());

		        
			 Evenement evenementUpdate = evenementDb.get();
	        	evenementUpdate.setId(evenement.getId());
	        	evenementUpdate.setType("Incident");

	        	evenementRepository.save(evenementUpdate);

	            return evenementUpdate;
	    }

	public Map<String, Boolean> updateEvenement(Evenement evenementEntry) {
		
			Map<String, Boolean> response = new HashMap<>();
			Evenement evenement = evenementRepository.findEvenementByCode(evenementEntry.getCode());
			Query query = new Query();
			query.addCriteria(Criteria.where("code").is(evenement.getCode()));
			Update update = new Update();
	
		
			update.set("detecteur",evenementEntry.getDetecteur());
			mongoTemplate.updateFirst(query, update, EvenementDetail.class);
			
			
			
			
			response.put("success", Boolean.TRUE);
			return response;
	}


	@Override
	public Evenement update(Evenement evenement) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Boolean> delete(String evenement) {
		
		Evenement deleted = evenementRepository.findEvenementById(evenement);
	
		Query query = Query.query(Criteria.where("$id").is(new ObjectId(deleted.getId())));

		evenementRepository.delete(deleted);
		Map<String, Boolean> response = new HashMap<>();
	    response.put("deleted", Boolean.TRUE);
	    return response;
	}
	public long countAllEvenement() {
		
		long a;
		a=evenementRepository.count();
		return a;
	}
	

	    @Override
	    public Evenement setInactive(Evenement evenement){
	        Optional< Evenement > evenementDb = this.evenementRepository.findById(evenement.getId());

	        
	        	Evenement evenementUpdate = evenementDb.get();
	        	evenementUpdate.setId(evenement.getId());
	        	evenementUpdate.setStatus(true);

	        	evenementRepository.save(evenementUpdate);

	            return evenementUpdate;
	        
	       
	    }

	    @Override
		


	  		public Evenement updateType2(Evenement evenement) {
	  			 Optional< Evenement > evenementDb = this.evenementRepository.findById(evenement.getId());

	  		        
	  			 Evenement evenementUpdate = evenementDb.get();
	  	        	evenementUpdate.setId(evenement.getId());
	  	        	evenementUpdate.setType("Eve"
	  	        			+ "nement");

	  	        	evenementRepository.save(evenementUpdate);

	  	            return evenementUpdate;
	  	    }


		public List<Statistique> EvenementParMois() {
		
			List<Statistique> result = new ArrayList<Statistique>();
			List<Evenement> allEvenements = new ArrayList<Evenement>();
			allEvenements = evenementRepository.findAll();
			long all =evenementRepository.count()*5;
			
			
		
			int allJanv=0;
			int allfev=0;
			int allmar=0;
			int allavr=0;
			int allmai = 0;
			int alljuin=0;
			int alljul=0;
			int allaout=0;
			int allsep=0;
			int alloct=0;
			int allnov=0;
			int alldec=0;
			for(Evenement evenement:allEvenements) {
				
				String mois=(String)  evenement.getDate().subSequence(evenement.getDate().length()-5, evenement.getDate().length()-3);
				System.out.println(mois);
				switch(mois)  
				{case "01":
				    allJanv ++;
				    
				    break;
				  case "02":
				    allfev++;
				    break;
				  case "03":
				    allmar++;
				    break;
				  case "04":
				    allavr++;
				    break;
				  case "05":
				    allmai++;
				    break;
				  case "06":
				   alljuin++;
				    break;
				  case "07":
				  alljul++;
				    break;
				    
				  case "08":
					   allaout++;
					   break;
				  case "09":
					
					allsep++;
					    break;
				  case "10":
					 
					   alloct++;
					    break;
				  case "11":
					  
					   allnov++;
					    break;
				  case "12":
					 
					  alldec++;
					  break;
				}
		
			
			} 
		    Statistique e1 = new Statistique();
		    e1.setMois("janvier");
		    e1.setValue( Double.toString(allJanv));
		    
			result.add(e1);
		    Statistique e2 = new Statistique();
			e2.setMois("février");
		
				e2.setValue( Double.toString(allfev));
			
			result.add(e2);
			Statistique e3 = new Statistique();
			e3.setMois("Mars");
			e3.setValue( Double.toString(allmar));
			result.add(e3);
			Statistique e4 = new Statistique();
			e4.setMois("Avril");
		e4.setValue( Double.toString(allavr));	
			result.add(e4);
			Statistique e5 = new Statistique();
			e5.setMois("Mai");
			
				e5.setValue( Double.toString(allmai));
			result.add(e5);
			Statistique e6 = new Statistique();
			e6.setMois("Juin");
				e6.setValue( Double.toString(alljuin));
			result.add(e6);
			Statistique e7 = new Statistique();
			e7.setMois("Juillet");
		
				e7.setValue( Double.toString(alljul));
			
			result.add(e7);
			Statistique e8 = new Statistique();
			e8.setMois("Aout");
			
				e8.setValue( Double.toString(allaout));
			
			result.add(e8);
			Statistique e9 = new Statistique();
			e9.setMois("Septembre");
				e9.setValue( Double.toString(allsep));
			result.add(e9);
			Statistique e10 = new Statistique();
			e10.setMois("October");
			
				e10.setValue( Double.toString(alloct));	
			
			result.add(e10);
			Statistique e11 = new Statistique();
			e11.setMois("Novembre");
				e11.setValue( Double.toString(allnov));	
			result.add(e11);
			Statistique e12 = new Statistique();
			e12.setMois("Decembre");
		
				e12.setValue( Double.toString(alldec));
			
			result.add(e12);
			return result;
		}

	

}









	
	
	
