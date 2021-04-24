package com.telnet.project.ServiceImpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.DBRef;
import com.mongodb.QueryBuilder;
import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Menace;
import com.telnet.project.Entities.Personne;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.RisqueDetail;
import com.telnet.project.Entities.Role;
import com.telnet.project.Entities.SourceMenace;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Entities.Statistique;
import com.telnet.project.Entities.User;
import com.telnet.project.Entities.Vulnerabilite;
import com.telnet.project.Repository.ActionRepository;
import com.telnet.project.Repository.MenaceRepository;
import com.telnet.project.Repository.PersonneRepository;
import com.telnet.project.Repository.RisqueDetailRepository;
import com.telnet.project.Repository.RisqueRepository;
import com.telnet.project.Repository.RoleRepository;
import com.telnet.project.Repository.SourceMenaceRepository;
import com.telnet.project.Repository.UserRepository;
import com.telnet.project.Services.RisqueService;
import com.telnet.project.webSocket.Notification;

import java.util.List;

import org.springframework.stereotype.Repository;

@Service
public class RisqueServiceImpl implements RisqueService{

	@Autowired
	private RisqueRepository risqueRepository;
	
	@Autowired
	private MenaceRepository menaceRepository;
	@Autowired
	private RisqueDetailRepository risqueDetailRepository;
	
	@Autowired
	private ActionRepository actionRepository;
	
	@Autowired
	private SourceMenaceRepository sourceMenaceRepository;
	 @Autowired
	 MongoTemplate mongoTemplate; 
	 
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Map<String, Boolean> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Risque> findQuery() {
		Query query = new Query();
		query.fields().include("residuel").include("decisionTraitement");
		return mongoTemplate.find(query, Risque.class);
	}
	public List<Risque> findRisqParSourceMenaceQuerry() {
		Query query = new Query();
		query.fields().include("menace");
		return mongoTemplate.find(query, Risque.class);
	}
	public List<Statistique> findRisqParSourceMenace(){
		List<SourceMenace> listMenace = sourceMenaceRepository.findAll();
		List<Risque> listRisque =  findRisqParSourceMenaceQuerry();
		long allNbr= listRisque.size(); 
		List<Statistique> listStat = new ArrayList<Statistique>();
		for(SourceMenace menace: listMenace) {
			Statistique stat= new Statistique();
			stat.setMois(menace.getDescription());
			int nbr=0;
			for(Risque risque:listRisque) {
			if((risque.getMenace().getSource().getId()).equals(menace.getId())) {
				nbr++;
			}
			}
			stat.setValue(Double.toString((nbr*100)/allNbr) + "%");
			listStat.add(stat);
		
	}
		return listStat;
		}
	
	@Override
	public Risque update(Risque risque) {
		// TODO Auto-generated method stub
		return null;
	}
	public Map<String,Boolean> updateRisque(Risque risqueEntry){
		Map<String, Boolean> response = new HashMap<>();
		Risque risque = risqueRepository.findRisqueByCode(risqueEntry.getCode());
		Query query = new Query();
		query.addCriteria(Criteria.where("code").is(risque.getCode()));
		Update update = new Update();
		update.set("famille", risqueEntry.getFamille());
		update.set("proprietaire",risqueEntry.getProprietaire());
		Menace menace = menaceRepository.findMenaceByCode(risqueEntry.getMenace().getCode());
		update.set("menace",menace);
		update.set("vulnerabilite",risqueEntry.getVulnerabilite());
		update.set("scenarioRisque",risqueEntry.getScenarioRisque());
		update.set("consequence",risqueEntry.getConsequence());
		update.set("listActif",risqueEntry.getListActif());
risque.setListActif(new ArrayList<Actif>());
		Query querybrut = new Query();
		querybrut.addCriteria(Criteria.where("id").is(risqueEntry.getBrut().getId()));
		Update updateBrut = new Update();
		updateBrut.set("vulnerabilite",risqueEntry.getBrut().getVulnerabilite());
		updateBrut.set("critereMenace",risqueEntry.getBrut().getCritereMenace());
		updateBrut.set("probaRisq",risqueEntry.getBrut().getProbaRisq());
		updateBrut.set("pF",risqueEntry.getBrut().getpF());
		updateBrut.set("pI",risqueEntry.getBrut().getpI());
		updateBrut.set("pJ",risqueEntry.getBrut().getpJ());
		updateBrut.set("degreRisque",risqueEntry.getBrut().getDegreRisque());
		mongoTemplate.updateFirst(querybrut, updateBrut, RisqueDetail.class);
		update.set("famille", risqueEntry.getFamille());
		update.set("mesureExistante",risqueEntry.getMesureExistante());
		if(risqueEntry.getReel() == null) {
			RisqueDetail risqueReel= new RisqueDetail();
			risqueDetailRepository.save(risqueReel);
		}
		if(risque.getReel()== null) {
			RisqueDetail risqueReel= new RisqueDetail();
			risqueDetailRepository.save(risqueReel);
			update.set("reel",risqueReel);
		Query queryReel = new Query();
		queryReel.addCriteria(Criteria.where("id").is(risqueReel.getId()));
		Update updateReel = new Update();
		updateReel.set("vulnerabilite",risqueEntry.getReel().getVulnerabilite());
		updateReel.set("critereMenace",risqueEntry.getReel().getCritereMenace());
		updateReel.set("probaRisq",risqueEntry.getReel().getProbaRisq());
		updateReel.set("pF",risqueEntry.getReel().getpF());
		updateReel.set("pI",risqueEntry.getReel().getpI());
		updateReel.set("pJ",risqueEntry.getReel().getpJ());
		updateReel.set("degreRisque",risqueEntry.getReel().getDegreRisque());
		update.set("reel",risqueReel);
		mongoTemplate.updateFirst(queryReel, updateReel, RisqueDetail.class);
		update.set("decisionTraitement",risqueEntry.getDecisionTraitement());
		update.set("action",new DBRef("Action", new ObjectId(risqueEntry.getAction().getId())));
		}
		else {
			Query queryReel = new Query();
			queryReel.addCriteria(Criteria.where("id").is(risqueEntry.getReel().getId()));
			Update updateReel = new Update();
			updateReel.set("vulnerabilite",risqueEntry.getReel().getVulnerabilite());
			updateReel.set("critereMenace",risqueEntry.getReel().getCritereMenace());
			updateReel.set("probaRisq",risqueEntry.getReel().getProbaRisq());
			updateReel.set("pF",risqueEntry.getReel().getpF());
			updateReel.set("pI",risqueEntry.getReel().getpI());
			updateReel.set("pJ",risqueEntry.getReel().getpJ());
			updateReel.set("degreRisque",risqueEntry.getReel().getDegreRisque());
			if(risqueEntry.getAction() != null) {
				
			update.set("action",new DBRef("Action", new ObjectId(risqueEntry.getAction().getId())));
			}
			mongoTemplate.updateFirst(queryReel, updateReel, RisqueDetail.class);
			update.set("decisionTraitement",risqueEntry.getDecisionTraitement());
		}
		if(risque.getResiduel()!= null) {
			
		if(risque.getResiduel()== null) {
			System.out.println("Residuel is null");
			RisqueDetail risqueResiduel= new RisqueDetail();
			risqueDetailRepository.save(risqueResiduel);
			update.set("residuel",risqueResiduel);
		
			Query queryResiduel = new Query();
			queryResiduel.addCriteria(Criteria.where("id").is(risqueResiduel.getId()));
			Update updateResiduel = new Update();
			updateResiduel.set("vulnerabilite",risqueEntry.getResiduel().getVulnerabilite());
			updateResiduel.set("critereMenace",risqueEntry.getResiduel().getCritereMenace());
			updateResiduel.set("probaRisq",risqueEntry.getResiduel().getProbaRisq());
			updateResiduel.set("pF",risqueEntry.getResiduel().getpF());
			updateResiduel.set("pI",risqueEntry.getResiduel().getpI());
			updateResiduel.set("pJ",risqueEntry.getResiduel().getpJ());
			updateResiduel.set("degreRisque",risqueEntry.getResiduel().getDegreRisque());
			update.set("residuel",risqueResiduel);
			update.set("decisionAcceptation",risqueEntry.getDecisionAcceptation());
			
			mongoTemplate.updateFirst(queryResiduel, updateResiduel, RisqueDetail.class);
		}else {
		Query queryResiduel = new Query();
		queryResiduel.addCriteria(Criteria.where("id").is(risqueEntry.getResiduel().getId()));
		Update updateResiduel = new Update();
		updateResiduel.set("vulnerabilite",risqueEntry.getResiduel().getVulnerabilite());
		updateResiduel.set("critereMenace",risqueEntry.getResiduel().getCritereMenace());
		updateResiduel.set("probaRisq",risqueEntry.getResiduel().getProbaRisq());
		updateResiduel.set("pF",risqueEntry.getResiduel().getpF());
		updateResiduel.set("pI",risqueEntry.getResiduel().getpI());
		updateResiduel.set("pJ",risqueEntry.getResiduel().getpJ());
		updateResiduel.set("degreRisque",risqueEntry.getResiduel().getDegreRisque());
		update.set("decisionAcceptation",risqueEntry.getDecisionAcceptation());
		mongoTemplate.updateFirst(queryResiduel, updateResiduel, RisqueDetail.class);
		}
	}
		mongoTemplate.updateFirst(query, update, Risque.class);
		response.put("success", Boolean.TRUE);
		return response;
	}
	public  Map<String, Risque> ajoutRisque(@Valid Risque risqueEntry) {
		Map<String, Risque> response = new HashMap<>();
		Risque risque = new Risque();
		String code = risqueEntry.getCode();
		System.out.println("Code egal"+code);
		System.out.println(risqueRepository.findRisqueByCode(code));
		if(risqueRepository.findRisqueByCode(code) == null ) {
		risque.setCode(risqueEntry.getCode());
		risque.setFamille(risqueEntry.getFamille());
		List<Actif> actifListEntry= new ArrayList<Actif>();
		List<Actif> actifList= new ArrayList<Actif>();
		actifListEntry=risqueEntry.getListActif();
		if(actifListEntry != null) 
		{
		for(Actif actif:actifListEntry) 
		{
			actifList.add(actif);
		}
		}
		risque.setListActif(actifList);
		if(risqueEntry.getSousFamille().getNom() != null) {
			SousFamille sousFamille = new SousFamille();
			sousFamille=risqueEntry.getSousFamille();
			risque.setSousFamille(sousFamille);
		}
		if(risqueEntry.getMenace() != null) 
		{
		Menace menace= new Menace() ;
		menace=risqueEntry.getMenace();
	    risque.setMenace(menace);
	    }
		List<Vulnerabilite> vulnerabiliteEntry= new ArrayList <Vulnerabilite>();
		List<Vulnerabilite> vulnerabiliteList= new ArrayList<Vulnerabilite>();
		vulnerabiliteEntry=risqueEntry.getVulnerabilite();
		if(vulnerabiliteEntry!= null) {
		for(Vulnerabilite vulnerabilite:vulnerabiliteEntry) {
			vulnerabiliteList.add(vulnerabilite);
		}
		}
		risque.setVulnerabilite(vulnerabiliteList);
		risque.setScenarioRisque(risqueEntry.getScenarioRisque());
		risque.setConsequence(risqueEntry.getConsequence());
	     System.out.println("Prophere" + risqueEntry.getProprietaire());
		//Role proprietaire= roleRepository.findRoleByName(risqueEntry.getProprietaire().getName());
		risque.setProprietaire(risqueEntry.getProprietaire());
		//risque.setBrut(risqueEntry.getBrut());
		if(risqueEntry.getBrut()!= null) {
		RisqueDetail brut = new RisqueDetail();
		brut.setpF(risqueEntry.getBrut().getpF());
		brut.setpJ(risqueEntry.getBrut().getpJ());
		brut.setpI(risqueEntry.getBrut().getpI());
		brut.setCritereMenace(risqueEntry.getBrut().getCritereMenace());
		brut.setCriterImpact(risqueEntry.getBrut().getCriterImpact());
		brut.setDegreRisque(risqueEntry.getBrut().getDegreRisque());
		brut.setProbaRisq(risqueEntry.getBrut().getProbaRisq());
		brut.setVulnerabilite(risqueEntry.getBrut().getVulnerabilite());
		risqueDetailRepository.save(brut);
		//brut=risqueEntry.getBrut();
	//	System.out.println(risqueEntry.getBrut().getpF().getDegre());
		risque.setBrut(brut);
		}
		risque.setMesureExistante(risqueEntry.getMesureExistante());
		System.out.println(risqueEntry.getReel()== null);
		if(risqueEntry.getReel()!= null)
		
		{
			RisqueDetail reel = new RisqueDetail();
			reel.setpF(risqueEntry.getReel().getpF());
			reel.setpJ(risqueEntry.getReel().getpJ());
			reel.setpI(risqueEntry.getReel().getpI());
			reel.setCritereMenace(risqueEntry.getReel().getCritereMenace());
			reel.setCriterImpact(risqueEntry.getReel().getCriterImpact());
			reel.setDegreRisque(risqueEntry.getReel().getDegreRisque());
			reel.setProbaRisq(risqueEntry.getReel().getProbaRisq());
			reel.setVulnerabilite(risqueEntry.getReel().getVulnerabilite());
			risqueDetailRepository.save(reel);
			//brut=risqueEntry.getBrut();
		//	System.out.println(risqueEntry.getBrut().getpF().getDegre());
			risque.setReel(reel);
		
		}
		
		risque.setDecisionTraitement(risqueEntry.getDecisionTraitement());
		System.out.println(risqueEntry.getDecisionTraitement());
		if(risqueEntry.getResiduel()!= null) {
			if(risqueEntry.getAction().getCodeAction() != null) {
			System.out.println(risqueEntry.getAction().getCodeAction()+"here");
			Action actionExiste = new Action();
			actionExiste=actionRepository.findActionByCodeAction(risqueEntry.getAction().getCodeAction());
			System.out.println(actionExiste.getId() + " Id found");
			List<String> list = new ArrayList<String>();
			list=actionExiste.getCodesRisque();
			list.add(risqueEntry.getCode());
			actionExiste.setCodesRisque(list);
			actionRepository.save(actionExiste);
			//if(risqueEntry.getAction().getCodeAction() !="" && risqueEntry.getAction().getCodeAction() != null ) {
		/*	Action action = new Action();
				action.setCodeAction(risqueEntry.getAction().getCodeAction());
				action.setTraitement(risqueEntry.getAction().getTraitement());
				actionRepository.save(action);*/
				risque.setAction(actionExiste);
			}
			RisqueDetail residuel= new RisqueDetail();
			residuel.setpF(risqueEntry.getResiduel().getpF());
			residuel.setpJ(risqueEntry.getResiduel().getpJ());
			residuel.setpI(risqueEntry.getResiduel().getpI());
			residuel.setCritereMenace(risqueEntry.getResiduel().getCritereMenace());
			residuel.setCriterImpact(risqueEntry.getResiduel().getCriterImpact());
			residuel.setDegreRisque(risqueEntry.getResiduel().getDegreRisque());
			residuel.setProbaRisq(risqueEntry.getResiduel().getProbaRisq());
			residuel.setVulnerabilite(risqueEntry.getResiduel().getVulnerabilite());
			risqueDetailRepository.save(residuel);
			//brut=risqueEntry.getBrut();
			//	System.out.println(risqueEntry.getBrut().getpF().getDegre());
			risque.setResiduel(residuel);
			risque.setDecisionAcceptation(risqueEntry.getDecisionAcceptation());
			}
		risqueRepository.save(risque);
		 response.put("Risque_Ajoutée",risque);
		 System.out.println(response);
	  /*      if (LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY){*/
	            //List<CdoSnapshot> snapshots = javers.findSnapshots( QueryBuilder.byInstance(risque).build());
	            /*Changes changes = javers.findChanges( QueryBuilder.byInstance(risque).build());
	            System.out.println( changes.prettyPrint());*/
	      /*  }*/
		return response;}
	else {
			 response.put("Risque_Existe", null);
			 System.out.println(response);
			  return response;
		}
	}
	public List<Risque> afficheRisqueParFamille(@Valid Famille nomFamille) {
		Query query = new Query();
		query.fields().include("code").include("listActif").include("scenarioRisque").include("consequence").
		include("proprietaire").include("brut").include("reel").include("residuel").
		include("mesureExistante").include("decisionTraitement");
		query.addCriteria(Criteria.where("famille").is(nomFamille.getNom()));
		return mongoTemplate.find(query, Risque.class);
	}
	 public long countAllRisques() {
	    	long a;
	    	a =risqueRepository.count();
	    	return a;
	    }

	 public long countRisquesResiduels() {
	    	long a=0;
	    	List<Risque> risques = new ArrayList<Risque>();
	    	risques=risqueRepository.findAll();
	    	for(Risque risque:risques) {
	    		if(risque.getResiduel() != null) {
	    			a++;
	    		}	
	    	}
	    	return a;
	    }
	 public int countRisqueParFamille(Famille famille) {
		 int a=0;
		
		 List<Risque> risques = new ArrayList<Risque>();
		 Query query = new Query();
			query.fields().include("famille");
			query.addCriteria(Criteria.where("famille").is(famille.getNom()));
		risques=mongoTemplate.find(query, Risque.class);
		 for(Risque risque:risques) {
			 if(risque.getFamille().equals(famille.getNom())){
				 a++;
				 System.out.println(a);
			 }
		 }
		

		 return a;
		 
	 }
	 public List<Statistique> tauxRisque() {
		 List result = new ArrayList<Statistique>();
		  int countResiduel=0;
		  int counttraite=0;
		  int countAccepte=0;
		  List<Risque> risques = new ArrayList<Risque>();
		  
	      risques=findQuery();
		  for(Risque risque:risques) {
				if(risque.getResiduel() != null) {
	    			countResiduel++;
	    			System.out.println(countResiduel);
	    		}
				if(risque.getDecisionTraitement()!= null) {
					if(risque.getDecisionTraitement() .equalsIgnoreCase( "Traitement")) {
		    			counttraite++;
		    			System.out.println(counttraite);
		    		}
		    		if(risque.getDecisionTraitement().equalsIgnoreCase( "ACCEPTATION")) {
		    			countAccepte++;
		    			System.out.println(countAccepte);
				}
		  } 
				
		  }
		Statistique residuel =new Statistique();
		residuel.setMois("Risques Résiduels");
		residuel.setValue(Integer.toString(countResiduel));
		result.add(residuel);
		
		Statistique traite =new Statistique();
		traite.setMois("Risques Traités");
		traite.setValue(Integer.toString(counttraite));
		result.add(traite);
		
		Statistique accepte =new Statistique();
		accepte.setMois("Risques Accepté");
		accepte.setValue(Integer.toString(countAccepte));
		result.add(accepte);
		 return result;
	 }
	 public TauxRisque countTauxRisque() {
		 TauxRisque tauxRisque=new TauxRisque();
		 List<Risque> risques = new ArrayList<Risque>();
		  int countResiduel=0;
		  int counttraite=0;
		  int countAccepte=0;
		
	    risques=findQuery();
	    	for(Risque risque:risques) {
	    		if(risque.getResiduel() != null) {
	    			countResiduel++;
	    		}
	    		System.out.println(risque.getDecisionTraitement()!= null);
	    		if(risque.getDecisionTraitement()!= null) {
	    		if(risque.getDecisionTraitement() .equalsIgnoreCase( "Traitement")) {
	    			counttraite++;
	    		}
	    		if(risque.getDecisionTraitement().equalsIgnoreCase( "ACCEPTATION")) {
	    			countAccepte++;
	    		}}
	    		if(risque.getDecisionAcceptation()!= null) {
		    		if(risque.getDecisionAcceptation() .equalsIgnoreCase( "Traitement")) {
		    			counttraite++;
		    		}
		    		if(risque.getDecisionAcceptation().equalsIgnoreCase( "ACCEPTATION")) {
		    			countAccepte++;
		    		}}
	    	}
		 tauxRisque.setCountResiduel(countResiduel);
		 tauxRisque.setCountTraite(counttraite);
		 tauxRisque.setCountAccepte(countAccepte);
		 
		 return tauxRisque;
	 }


	
	public List<Risque> findAll() {
		
		return risqueRepository.findAll();
	}
	public Risque getRisqueById(String code) {
	
		return risqueRepository.findRisqueByCode(code);
	}

	public List<Risque> findRisqueByActif(Actif actifs) {
		return risqueRepository.findBylistActifContaining(actifs);
	}
	 
	
}

