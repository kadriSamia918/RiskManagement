package com.telnet.project.ServiceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.CategorieAction;
import com.telnet.project.Entities.Personne;
import com.telnet.project.Entities.Risque;
import com.telnet.project.Entities.Statistique;
import com.telnet.project.Entities.User;
import com.telnet.project.Repository.ActionRepository;
import com.telnet.project.Repository.PersonneRepository;
import com.telnet.project.Services.ActionService;

@Service
public class ActionServiceImpl implements ActionService {
	
	@Autowired
	MongoTemplate mongoTemplate; 
	
	private ActionRepository actionRepository;
	
	@Autowired
	private  PersonneRepository personneRepository;
	
	@Autowired
	 ActionServiceImpl(ActionRepository actionRepository){
		this.actionRepository=actionRepository;
	}
	
	
	@Override
	public Action createAction(Action action) {
	Action saved = new Action();
	saved.setCodeAction(action.getCodeAction());
	saved.setCodesIncident(action.getCodesIncident());
	saved.setCodesRisque(action.getCodesRisque());
	saved.setDelaiCloture(action.getDelaiCloture());
	saved.setCategorie(action.getCategorie());
	saved.setCommentaireEfficacite(action.getCommentaireEfficacite());
	saved.setDelai(action.getDelai());
	saved.setDelaiMesureEff(action.getDelaiMesureEff());
	saved.setdG(action.getdG());
	saved.setEfficacite(action.getEfficacite());
	System.out.println(action.getResponsableMO().getName());
	saved.setdG(action.getdG());
	/*if(action.getdG().equals("refuser")){
		saved.setReason(action.getReason());
	}*/
	if(action.getResponsableMO()!= null) {
		Personne proprietaire= personneRepository.findPersonneByName(action.getResponsableMO().getName());
		saved.setResponsableMO(proprietaire);}
	//saved.setResponsableMO(action.getResponsableMO());
	saved.setEtatRealisation(action.getEtatRealisation());
	saved.setTraitement(action.getTraitement());
	saved.setSavingDate(action.getSavingDate());
	saved.setEfficacite(action.getEfficacite());
	saved.setSaverId(action.getSaverId());
		return actionRepository.save(saved);
	}

	@Override
	public Map<String, Boolean> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> findAll() {
		return actionRepository.findAll();
	}
public List<Action> findAcceptedAction(){
	List<Action> actionsList= actionRepository.findAll();
	List<Action> approvedActions = new ArrayList<Action>();
	for(Action action:actionsList) {
		if(action.getdG().equals( "approuver"))
		{
			approvedActions.add(action);
		}
	}
	return approvedActions;
}
	@Override
	public Action update(Action action) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public long countAllActions() {
		long a;
		a=actionRepository.count();
		return a;
	}
	public Map<String,Boolean> updateAction(Action actionEntry){
		Map<String, Boolean> response = new HashMap<>();
		Action action= actionRepository.findActionByCodeAction(actionEntry.getCodeAction());
		Query query = new Query();
		query.addCriteria(Criteria.where("codeAction").is(action.getCodeAction()));
		Update update = new Update();
		update.set("proprietaire",actionEntry.getResponsableMO());
		update.set("traitement",actionEntry.getTraitement());
		update.set("codesRisque",actionEntry.getCodesRisque());
		update.set("CommentaireEfficacite",actionEntry.getCommentaireEfficacite());
		update.set("delai",actionEntry.getDelai());
		update.set("delaiMesureEff",actionEntry.getDelaiMesureEff());
		update.set("efficacite",actionEntry.getEfficacite());
		update.set("etatRealisation",actionEntry.getEtatRealisation());
		mongoTemplate.updateFirst(query, update, Action.class);
		response.put("success", Boolean.TRUE);


	return response;	
	}
	public List<Statistique> efficaciteParMois(){
		List<Statistique> result = new ArrayList<Statistique>();
		List<Action> allActions = new ArrayList<Action>();
		allActions = actionRepository.findAll();
		long all =actionRepository.count()*5;
		
		
		int Janv=0;
		int fev=0;
		int mar=0;
		int avr=0;
		int mai = 0;
		int juin=0;
		int jul=0;
		int aout=0;
		int sep=0;
		int oct=0;
		int nov=0;
		int dec=0;
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
		for(Action action:allActions) {
			
			String mois=(String)  action.getDelaiMesureEff().subSequence(0, 2);
			System.out.println(mois);
			switch(mois)  
			{case "01":
			    Janv=Janv + action.getEfficacite();
			    allJanv ++;
			    
			    break;
			  case "02":
			    fev = fev + action.getEfficacite();
			    allfev++;
			    break;
			  case "03":
			    mar = mar + action.getEfficacite();
			    allmar++;
			    break;
			  case "04":
			    avr=avr + action.getEfficacite();
			    allavr++;
			    break;
			  case "05":
			    mai = mai + action.getEfficacite();
			    allmai++;
			    break;
			  case "06":
			   juin=juin + action.getEfficacite();
			   alljuin++;
			    break;
			  case "07":
			  jul=jul+ action.getEfficacite();
			  alljul++;
			    break;
			    
			  case "08":
				   aout = aout+ action.getEfficacite();
				   allaout++;
				   break;
			  case "09":
				sep=sep + action.getEfficacite();
				System.out.println(sep);
				allsep++;
				    break;
			  case "10":
				   oct = oct + action.getEfficacite();
				   alloct++;
				    break;
			  case "11":
				   nov = nov + action.getEfficacite();
				   allnov++;
				    break;
			  case "12":
				  dec=dec + action.getEfficacite();
				  alldec++;
				  break;
			}
	
		
		} 
	    Statistique e1 = new Statistique();
	    e1.setMois("janvier");
	    if(allJanv != 0) {
	    	double val1 = Janv/(allJanv*5.0)*100 ;
			e1.setValue(Double.toString(val1));}
	    else {
	    	e1.setValue("0.0");
	    }
		result.add(e1);
	    Statistique e2 = new Statistique();
		e2.setMois("février");
		if(allfev != 0) {
			double val2 = fev/(allfev*5.0)*100 ;
			e2.setValue(Double.toString(val2));}
		else {
			e2.setValue("0.0");
		}
		result.add(e2);
		Statistique e3 = new Statistique();
		e3.setMois("Mars");
		if(allmar != 0) {
			System.out.println("mar"+mar);
			System.out.println("allmar"+allmar);
			double val3 = mar/(allmar*5.0)*100 ;
			System.out.println(val3);
			e3.setValue( Double.toString(val3));}
		else {
			e3.setValue("0.0");
		}
		result.add(e3);
		Statistique e4 = new Statistique();
		e4.setMois("Avril");
		if(allavr != 0) {
			double val4 = avr/(allavr*5.0)*100 ;
			e4.setValue( Double.toString(val4));}
		else {
			e4.setValue("0.0");
		}
		result.add(e4);
		Statistique e5 = new Statistique();
		e5.setMois("Mai");
		if(allmai != 0) {
			double val5 = mai/(allmai*5.0)*100 ;
			e5.setValue( Double.toString(val5));}
		else {
			e5.setValue("0.0");
		}
		result.add(e5);
		Statistique e6 = new Statistique();
		e6.setMois("Juin");
		if(alljuin != 0) {
			double val6 = juin/(alljuin*5.0)*100 ;
			e6.setValue( Double.toString(val6));}
		else {
			e6.setValue("0.0");
		}
		result.add(e6);
		Statistique e7 = new Statistique();
		e7.setMois("Juillet");
		if(alljul != 0) {
			double val7 = jul/(alljul*5.0)*100 ;
			e7.setValue( Double.toString(val7));}
		else {
			e7.setValue("0.0");
		}
		result.add(e7);
		Statistique e8 = new Statistique();
		e8.setMois("Aout");
		if(allaout != 0) {
			double val8 = aout/(allaout*5.0)*100 ;
			e8.setValue( Double.toString(val8));}
		else {
			e8.setValue("0.0");
		}
		result.add(e8);
		Statistique e9 = new Statistique();
		e9.setMois("Septembre");
		if(allsep != 0) {
		double val9 = sep/(allsep*5.0)*100 ;
		e9.setValue( Double.toString(val9));
		}
		else {
			e9.setValue("0.0");	
		}
		result.add(e9);
		Statistique e10 = new Statistique();
		e10.setMois("October");
		if(alloct != 0) {
			double val10 = oct/(alloct*5.0)*100 ;
			e10.setValue( Double.toString(val10));}
		else {
			e10.setValue("0.0");	
		}
		result.add(e10);
		Statistique e11 = new Statistique();
		e11.setMois("Novembre");
		if(allnov != 0) {
			double val11 = nov/(allnov*5.0)*100 ;
			e11.setValue( Double.toString(val11));}
		else {
			e11.setValue("0.0");	
		}
		result.add(e11);
		Statistique e12 = new Statistique();
		e12.setMois("Decembre");
		if(alldec != 0) {
			double val12 = dec/(alldec*5.0)*100 ;
			e12.setValue( Double.toString(val12));}
		else {
			e12.setValue("0.0");	
		}
		result.add(e12);
		return result;
	}
	public List<Statistique> avancementActions(){
		List<Statistique> result = new ArrayList<Statistique>();
		List<Action> allActions = new ArrayList<Action>();
		long nbr=actionRepository.count();
		allActions = actionRepository.findAll();
		System.out.println(nbr);
		int nbrachived=0;
		int nbrinProgress=0;
		int nbrnotStarted=0;
		for(Action action:allActions) {
			System.out.println(action.getEtatRealisation());
			switch(action.getEtatRealisation()) {
			case 0:
				nbrnotStarted++;
				break;
			case 100:
				nbrachived++;
				break;
			default:
				nbrinProgress++;
				
			}
		}
		Statistique achived = new Statistique();
		achived.setMois("Achevée");
		achived.setValue(Long.toString((nbrachived*100/nbr)));
		result.add(achived);
		
		Statistique inProgress = new Statistique();
		inProgress.setMois("en cours");
		inProgress.setValue(Long.toString((nbrinProgress*100/nbr)));
		result.add(inProgress);
		
		Statistique notStarted = new Statistique();
		System.out.println(nbrnotStarted);
		notStarted.setMois("non commencée");
		
		notStarted.setValue(Long.toString((nbrnotStarted*100/nbr)));
		System.out.println((nbrnotStarted/nbr));

		result.add(notStarted);
		
		return result;
		
	}
	public Action dG_Action(String codeAction,String dG) {
		
		 Action action=actionRepository.findActionByCodeAction(codeAction);
		 action.setdG(dG);
		 actionRepository.save(action);
		
		return actionRepository.save(action);
	}
	public Map<String, Boolean> dG_Decision(String id,String decision) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Action action=mongoTemplate.findOne(query, Action.class);
		Update update = new Update();
		update.set("dG", decision);
	mongoTemplate.updateFirst(query, update, Action.class);
	Map<String, Boolean> response = new HashMap<>();
	response.put("success", Boolean.TRUE);
	return response;
	}
	 public List<Action> getActionsEnAttente(String decision){
		 Query query = new Query();
			query.addCriteria(Criteria.where("dG").is(decision));
			List<Action> actions=mongoTemplate.find(query, Action.class);
			return actions;
	 }

	@Override
	public Action getActionByCode(String code) {
		 
		    

		Action Actiondb = this.actionRepository.findActionByCodeAction(code);

		      
		            return Actiondb;
		         
		    }

	@Override
	public List<Action> getActionsByCategorie(@Valid String categorie) {
		return actionRepository.findActionByCategorie(categorie);
	}







	 

}