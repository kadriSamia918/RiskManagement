package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Critere_Securite;
import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.Repository.Critere_SecuriteRepository;
import com.telnet.project.Repository.Echelle_CriticiteRepository;
import com.telnet.project.Services.Critere_SecuriteService;
import com.telnet.project.payload.response.MessageResponse;

@Service
public class Critere_SecuriteServiceImpl implements Critere_SecuriteService {

    @Autowired
    private  Critere_SecuriteRepository critere_SecuriteRepository;
    
    @Autowired
    private  Echelle_CriticiteRepository echelle_CriticiteRepository;
    
	@Override
	public Critere_Securite createCritere_SecuriteConfidentialite() {
		
	    if(!critere_SecuriteRepository.existsByNom("confidentialite")) {
	    	Critere_Securite critere_SecuriteConfidentialite=new Critere_Securite();
	    	critere_SecuriteConfidentialite.setNom("confidentialite");
	    	List<Echelle_Criticite> echelle_Criticite= new ArrayList<Echelle_Criticite>();
	    	critere_SecuriteConfidentialite.setNiveau(echelle_Criticite);
	    	critere_SecuriteRepository.save(critere_SecuriteConfidentialite);
	    	
	    }
	    return null;
	}
	@Override
	public Critere_Securite  createCritere_SecuriteDisponibilite() {
		
	    if(!critere_SecuriteRepository.existsByNom("disponibilite")) {
	    	Critere_Securite critere_SecuriteDisponibilite=new Critere_Securite();
	    	critere_SecuriteDisponibilite.setNom("disponibilite");
	    	List<Echelle_Criticite> echelle_Criticite= new ArrayList<Echelle_Criticite>();
	    	critere_SecuriteDisponibilite.setNiveau(echelle_Criticite);
	    	critere_SecuriteRepository.save(critere_SecuriteDisponibilite);
	    	
	    }
	    return null;
	}
	@Override
	public Critere_Securite createCritere_SecuriteIntegrite() {
	    if(!critere_SecuriteRepository.existsByNom("Integrite")) {
	    	Critere_Securite critere_SecuriteIntegrite=new Critere_Securite();
	    	critere_SecuriteIntegrite.setNom("integrite");
	    	List<Echelle_Criticite> echelle_Criticite= new ArrayList<Echelle_Criticite>();
	    	critere_SecuriteIntegrite.setNiveau(echelle_Criticite);
	    	critere_SecuriteRepository.save(critere_SecuriteIntegrite);
	    	
	    }
	    return null;
	}

	
	@Override
	public List<Echelle_Criticite> updateCritere_SecuriteConfidentialite(List<Echelle_Criticite> echelle_CriticiteList) {
		try {
			List<Echelle_Criticite> Lp = new ArrayList<Echelle_Criticite>();
		
			for(Echelle_Criticite echelle:echelle_CriticiteList ) {
			
				Echelle_Criticite persisted = Echelle_Criticite.getBuilder()
                .nom(echelle.getNom())
                .description(echelle.getDescription())
                .couleur(echelle.getCouleur())
                .degre(echelle.getDegre())
                .build();
		
       /// echelle_CriticiteRepository.save(persisted);
        ///System.out.println(echelle_CriticiteRepository.save(persisted));
        Lp.add(persisted);
       
		}
        Critere_Securite critere_Securite=critere_SecuriteRepository.findByNom("confidentialite");
    
        critere_Securite.getNiveau().addAll(Lp);
      
        critere_SecuriteRepository.save(critere_Securite);   
        System.out.println("added");
		}
		catch(Exception e) {
			System.out.println("error here");
		}
        return null;
	}
	@Override
	public List<Echelle_Criticite> updateCritere_SecuriteIntegrite(List<Echelle_Criticite>echelle_CriticiteList) {
		try {
			List<Echelle_Criticite> Lp = new ArrayList<Echelle_Criticite>();
		
			for(Echelle_Criticite echelle:echelle_CriticiteList ) {
			
				Echelle_Criticite persisted = Echelle_Criticite.getBuilder()
                .nom(echelle.getNom())
                .description(echelle.getDescription())
                .couleur(echelle.getCouleur())
                .degre(echelle.getDegre())
                .build();
		
        ///persisted = echelle_CriticiteRepository.save(persisted);
        
        Lp.add(persisted);
       
		}
        Critere_Securite critere_Securite=critere_SecuriteRepository.findByNom("integrite");
    
        critere_Securite.getNiveau().addAll(Lp);
      
        critere_SecuriteRepository.save(critere_Securite);   
       
		}
		catch(Exception e) {
			
		}
        return null;
	}
 
	@Override
	public List<Echelle_Criticite> updateCritere_SecuriteDisponibilite(List<Echelle_Criticite> echelle_CriticiteList) {
		try {
			List<Echelle_Criticite> Lp = new ArrayList<Echelle_Criticite>();
		
			for(Echelle_Criticite echelle:echelle_CriticiteList ) {
			
				Echelle_Criticite persisted = Echelle_Criticite.getBuilder()
                .nom(echelle.getNom())
                .description(echelle.getDescription())
                .couleur(echelle.getCouleur())
                .degre(echelle.getDegre())
                .build();
		
     ///   persisted = echelle_CriticiteRepository.save(persisted);
        
        Lp.add(persisted);
       
		}
        Critere_Securite critere_Securite=critere_SecuriteRepository.findByNom("disponibilite");
    
        critere_Securite.getNiveau().addAll(Lp);
      
        critere_SecuriteRepository.save(critere_Securite);   
        System.out.println("added");
		}
		catch(Exception e) {
			System.out.println("error here");
		}
        return null;
	}
	@Override
	public void delete(Critere_Securite critere_Securite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Critere_Securite> findAll() {
		return critere_SecuriteRepository.findAll();
		}
	public List<Echelle_Criticite> afficherCritereDisponibilite() {
		Critere_Securite critereDisponibilite = critere_SecuriteRepository.findByNom("disponibilite");
		
		return critereDisponibilite.getNiveau();
		}
	public List<Echelle_Criticite> afficherCritereConfidentialite() {
		Critere_Securite critereDisponibilite = critere_SecuriteRepository.findByNom("confidentialite");
		
		return critereDisponibilite.getNiveau();
		}
	public List<Echelle_Criticite> afficherCritereIntegrite() {
		Critere_Securite critereDisponibilite = critere_SecuriteRepository.findByNom("integrite");
		
		return critereDisponibilite.getNiveau();
		}
	@Override
	public Critere_Securite update(Critere_Securite critere_Securite) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ResponseEntity<?> ajoutCritere_SecuriteDisponibilite(Echelle_Criticite echelle_Criticite) {
					if(echelle_CriticiteRepository.existsByNom(echelle_Criticite.getNom())) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Niveau Existe Déjà"));
				}
				if(echelle_Criticite.getNom()=="") {
					return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Niveau ne peut pas ètre vide"));
				}
				if(echelle_Criticite.getDegre() =="100") {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Degré n'est pas valide"));
				}
				Echelle_Criticite persisted = Echelle_Criticite.getBuilder()
                .nom(echelle_Criticite.getNom())
                .description(echelle_Criticite.getDescription())
                .couleur(echelle_Criticite.getCouleur())
                .degre(echelle_Criticite.getDegre())
                .build();
	
        Critere_Securite critere_Securite=critere_SecuriteRepository.findByNom("disponibilite");
        echelle_CriticiteRepository.save(persisted);
        critere_Securite.getNiveau().add(persisted);
      
        critere_SecuriteRepository.save(critere_Securite);   
        System.out.println("added");
		
        return ResponseEntity.ok(new MessageResponse("Niveau Ajouté"));
	}
	public ResponseEntity<?> ajoutCritere_SecuriteConfidentialite(Echelle_Criticite echelle_Criticite) {
		if(echelle_CriticiteRepository.existsByNom(echelle_Criticite.getNom())) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Niveau Existe Déjà"));
	}
	if(echelle_Criticite.getNom()=="") {
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Niveau ne peut pas ètre vide"));
	}
	if(echelle_Criticite.getDegre() == "100") {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Degré n'est pas valide"));
	}
	Echelle_Criticite persisted = Echelle_Criticite.getBuilder()
    .nom(echelle_Criticite.getNom())
    .description(echelle_Criticite.getDescription())
    .couleur(echelle_Criticite.getCouleur())
    .degre(echelle_Criticite.getDegre())
    .build();

Critere_Securite critere_Securite=critere_SecuriteRepository.findByNom("confidentialite");
echelle_CriticiteRepository.save(persisted);
critere_Securite.getNiveau().add(persisted);

critere_SecuriteRepository.save(critere_Securite);   
System.out.println("added");

return ResponseEntity.ok(new MessageResponse("Niveau Ajouté"));
}
	
	public ResponseEntity<?> ajoutCritere_SecuriteIntegrite(Echelle_Criticite echelle_Criticite) {
		if(echelle_CriticiteRepository.existsByNom(echelle_Criticite.getNom())) {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Niveau Existe Déjà"));
	}
	if(echelle_Criticite.getNom()=="") {
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Niveau ne peut pas ètre vide"));
	}
	if(echelle_Criticite.getDegre() == "100") {
		return ResponseEntity.badRequest()
				.body(new MessageResponse("Degré n'est pas valide"));
	}
	Echelle_Criticite persisted = Echelle_Criticite.getBuilder()
    .nom(echelle_Criticite.getNom())
    .description(echelle_Criticite.getDescription())
    .couleur(echelle_Criticite.getCouleur())
    .degre(echelle_Criticite.getDegre())
    .build();

Critere_Securite critere_Securite=critere_SecuriteRepository.findByNom("integrite");
echelle_CriticiteRepository.save(persisted);
critere_Securite.getNiveau().add(persisted);

critere_SecuriteRepository.save(critere_Securite);   
System.out.println("added");

return ResponseEntity.ok(new MessageResponse("Niveau Ajouté"));
}

}
