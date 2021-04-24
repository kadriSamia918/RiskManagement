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
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.AttributActif;
import com.telnet.project.Entities.CategorieIncident;
import com.telnet.project.Entities.CritereImpact;
import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.Entities.Evenement;
import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.EvenementNature;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.IncidentImpact;
import com.telnet.project.Entities.Personne;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Entities.Statistique;
import com.telnet.project.Entities.Vulnerabilite;
import com.telnet.project.Repository.ActionRepository;
import com.telnet.project.Repository.AttributActifRepository;
import com.telnet.project.Repository.EvenementDetailRepository;
import com.telnet.project.Repository.PersonneRepository;
import com.telnet.project.Services.EvenementDetailService;
@Service
public class EvenementDetailServiceImp implements EvenementDetailService {

	@Autowired
	private EvenementDetailRepository evenementDetailRepository;
	 
	 @Autowired
	private MongoTemplate  mongoTemplate;

	private EvenementDetailRepository incidentRepository;
	@Autowired
	private ActionRepository actionRepository;
	
	
	private PersonneRepository personneRepository;
	
	private  AttributActifRepository attributActifRepository ;

	public Map<String, EvenementDetail> ajoutDetailEvenement(@Valid EvenementDetail evenementEntry) {
			Map<String, EvenementDetail> response = new HashMap<>();
			EvenementDetail evenementDetail = new EvenementDetail();
			evenementDetail.setCode(evenementEntry.getCode());
			evenementDetail.setSource(evenementEntry.getSource());
			CategorieIncident categorie = evenementEntry.getCategorie();
			evenementDetail.setCategorie(categorie);
			evenementDetail.setDate(evenementEntry.getDate());
			evenementDetail.setEtat(evenementEntry.getEtat());
			evenementDetail.setCreatedAt(evenementEntry.getCreatedAt());

			
			evenementDetail.setCmntr(evenementEntry.getCmntr());
			evenementDetail.setDescription(evenementEntry.getDescription());

			evenementDetail.setCause(evenementEntry.getCause());
			List<Actif> actifListEntry= new ArrayList<Actif>();
			List<Actif> actifList= new ArrayList<Actif>();
			actifListEntry=evenementEntry.getActif();
			if(actifListEntry != null) 
			{
			for(Actif actif:actifListEntry) 
			{
				actifList.add(actif);
			}
			}
			evenementDetail.setActif(actifList);				Famille famille=evenementEntry.getFamille();
				String detecteur=evenementEntry.getDetecteur();
				
				evenementDetail.setFamille(famille);
				String evenementNature =evenementEntry.getNatureEvenement();
				evenementDetail.setDetecteur(detecteur);
				
				evenementDetail.setCriterImpact(evenementEntry.getCriterImpact());
						evenementDetail.setNatureEvenement(evenementNature);
						
						
						if(evenementEntry.getAction().getCodeAction() != null) {
							System.out.println(evenementEntry.getAction().getCodeAction()+"here");
							Action actionExiste = new Action();
							actionExiste=actionRepository.findActionByCodeAction(evenementEntry.getAction().getCodeAction());
							System.out.println(actionExiste.getId() + " Id found");
							List<String> list = new ArrayList<String>();
							list=actionExiste.getCodesIncident();
							list.add(evenementEntry.getCode());
							actionExiste.setCodesRisque(list);
							actionRepository.save(actionExiste);
							
							evenementDetail.setAction(actionExiste);

						}
			
			
		
			evenementDetailRepository.save(evenementDetail);
			 response.put("evenement_Ajoutée",evenementDetail);
			 System.out.println(response);
		  /*      if (LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY){*/
		  
			return response;}
	
	
	

	@Override
	public List<EvenementDetail> afficheIncidentParFamille(@Valid Famille nomFamille) {
		
		return evenementDetailRepository.findEvenementDetailByFamille(nomFamille);
				}
	
	
	
	

	public long countAllIncident() {
		
			long a;
			a=evenementDetailRepository.count();
			return a;
		}
		
	 
	public List<EvenementDetail> findQuery() {
			Query query = new Query();
			query.fields().include("code").include("actif").include("cause").include("criterImpact").
			include("cmntr").include("natureEvenement").include("famille").include("etat").include("description").include("source").include("detecteur").include("date");
			return mongoTemplate.find(query, EvenementDetail.class);
		}
	
	public Map<String, Boolean> updateIncident(EvenementDetail evenementEntry) {
		
		Map<String, Boolean> response = new HashMap<>();
		EvenementDetail evenement = evenementDetailRepository.findEvenementDetailById(evenementEntry.getId());
		
		evenement.setCause(evenementEntry.getCause());
		Actif	 attributActif=new Actif();
		evenement.setActif(evenementEntry.getActif());

		evenementDetailRepository.save(evenement);
		return response;
	
	
	}




	public EvenementDetail updateEvenementDetail(@Valid EvenementDetail actifEntry) {
		
		EvenementDetail evenementDetail = evenementDetailRepository.findEvenementDetailByCode(actifEntry.getCode());
		String prop= actifEntry.getDetecteur();
		String nature=actifEntry.getNatureEvenement();
		evenementDetail.setDetecteur(prop);

        evenementDetailRepository.save(evenementDetail);
        
    return evenementDetail;
	}



	@Override
	public Map<String, Boolean> delete(String id) {
		
		EvenementDetail deleted = evenementDetailRepository.findEvenementDetailById(id);
	    
		
		evenementDetailRepository.delete(deleted);
		
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}


	public List<EvenementDetail> getIncidentParCategorie(String cat) {
		 Query query = new Query();
			query.addCriteria(Criteria.where("categorie").is(cat));
			List<EvenementDetail> ev=mongoTemplate.find(query, EvenementDetail.class);
			return ev;
	}

	public List<EvenementDetail> getIncidentParImpact(String impact) {
		 Query query = new Query();
			query.addCriteria(Criteria.where("criterImpact").is(impact));
			List<EvenementDetail> ev=mongoTemplate.find(query, EvenementDetail.class);
			return ev;
	}




	
		@Override
		 public EvenementDetail setEtat(EvenementDetail evenement) {
		        Optional< EvenementDetail > evenementDb = evenementDetailRepository.findById(evenement.getId());

		        
		        	EvenementDetail evenementUpdate = evenementDb.get();
		        	evenementUpdate.setId(evenement.getId());
		        	evenementUpdate.setEtat(evenement.getEtat());

		        	evenementDetailRepository.save(evenementUpdate);

		            return evenementUpdate;
		        
		       
		    }




		

	    @Override
		public EvenementDetail updateEtat(EvenementDetail evenement) {
			 Optional< EvenementDetail > evenementDb = this.evenementDetailRepository.findById(evenement.getId());
			    EvenementDetail evenementUpdate = evenementDb.get();
	        	evenementUpdate.setId(evenement.getId());
	        	evenementUpdate.setEtat("En_Cours_Traitement");
	        	evenementUpdate.setDetecteur(evenement.getDetecteur());
	        	evenementUpdate.setFamille(evenement.getFamille());
	        	evenementUpdate.setNatureEvenement(evenement.getNatureEvenement());
	        	evenementUpdate.setCriterImpact(evenement.getCriterImpact());
	        	evenementUpdate.setCause(evenement.getCause());
	        	evenementUpdate.setActif(evenement.getActif());
	        	evenementUpdate.setDescription(evenement.getDescription());
	        	evenementDetailRepository.save(evenementUpdate);
	            return evenementUpdate;
	    }
	    
	    public List<IncidentImpact> IncidentParMois() {
			
			List<IncidentImpact> result = new ArrayList<IncidentImpact>();

			 List<EvenementDetail> risques = new ArrayList<EvenementDetail>();
			  int valueV=0;
			  int valueS=0;
			  int valueM=0;
			
		    risques=findQuery();
		    	for(EvenementDetail risque:risques) {
		    		
		    		if(risque.getCriterImpact().equalsIgnoreCase( "Impact vital")) {
		    			valueV++;
		    			System.out.print(valueV);
		    		}
		    		if(risque.getCriterImpact().equalsIgnoreCase( "Impact Sérieux")) {
		    			valueS++;
		    		}
		    		if(risque.getCriterImpact().equalsIgnoreCase( "Impact Mineur")) {
		    			valueM++;
		    		}
		    	
		    	}
		    	IncidentImpact tauxRisque1=new IncidentImpact();
			 tauxRisque1.setName("Impact vital");
			 tauxRisque1.setValue(valueV);
			 result.add(tauxRisque1);
			 
			 IncidentImpact tauxRisque2=new IncidentImpact();
			 tauxRisque2.setName("Impact Serieux");
			 tauxRisque2.setValue(valueS);
			 result.add(tauxRisque2);
			 

			 IncidentImpact tauxRisque3=new IncidentImpact();
			 tauxRisque3.setName("Impact Mineur");
			 tauxRisque3.setValue(valueM);
			 result.add(tauxRisque3);
			 
			 return result;
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    /*	
			List<Statistique> result = new ArrayList<Statistique>();
			List<EvenementDetail> allEvenements = new ArrayList<EvenementDetail>();
			allEvenements = evenementDetailRepository.findEvenementDetailByCriterImpact("Impact vital");
			
			
		
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
			for(EvenementDetail evenement:allEvenements) {
				
				String mois=(String)  evenement.getCreatedAt().subSequence(3, 5);
						
				System.out.println(mois);
				switch(mois)  
				{case "01":
				    allJanv =allJanv+10;
				    
				    break;
				  case "02":
				    allfev=allfev+10;
				    break;
				  case "03":
				    allmar=allmar+10;
				    break;
				  case "04":
				    allavr=allavr+10;
				    break;
				  case "05":
				    allmai=allmai+10;
				    break;
				  case "06":
				   alljuin=alljuin+10;
				    break;
				  case "07":
				  alljul=alljul+10;
				    break;
				    
				  case "08":
					   allaout=allaout+10;
					   break;
				  case "09":
					
					allsep=allsep+10;
					    break;
				  case "10":
					 
					   alloct=alloct+10;
					    break;
				  case "11":
					  
					   allnov=allnov+10;
					    break;
				  case "12":
					 
					  alldec=alldec+10;
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
			return result;*/
		}


public EvenementDetail getIncidentById(String code) {
			
			return evenementDetailRepository.findEvenementDetailByCode(code);
		}


		public List<EvenementDetail> getIncidentByImpact(String impact) {
			return evenementDetailRepository.findEvenementDetailByCriterImpact(impact);
		}
	
	
}
