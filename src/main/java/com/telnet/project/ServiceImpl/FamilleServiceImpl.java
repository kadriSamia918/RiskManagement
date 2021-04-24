package com.telnet.project.ServiceImpl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import com.telnet.project.DTO.FamilleDTO;
import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.AttributActif;
import com.telnet.project.Entities.Category;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Menace;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Repository.ActifRepository;
import com.telnet.project.Repository.AttributActifRepository;
import com.telnet.project.Repository.CategoryRepository;
import com.telnet.project.Repository.Echelle_CriticiteRepository;
import com.telnet.project.Repository.FamilleRepository;
import com.telnet.project.Repository.MenaceRepository;
import com.telnet.project.Repository.SousFamilleRepository;
import com.telnet.project.Services.FamilleService;
@Service
public class FamilleServiceImpl implements FamilleService {
	 @Autowired
	 MongoTemplate mongoTemplate; 
	 
	@Autowired
    private  FamilleRepository familleRepository; 
	
	@Autowired
    private  SousFamilleRepository sousFamilleRepository; 
	
	@Autowired
    private  MenaceRepository menaceRepository; 
	
	@Autowired
	private  CategoryRepository categoryRepository; 

	@Autowired
	private AttributActifRepository attributActifRepository; 
	
	@Autowired
	private ActifRepository actifRepository; 
	
	@Autowired
	private Echelle_CriticiteRepository echelle_CriticiteRepository; 
	/*
	public  Famille CreateFamille(Famille familleData) {
		Famille famille = new Famille();
		//famille.setCategory(familleData.getCategory());
		famille.setDescription(familleData.getDescription());
		famille.setNom(familleData.getNom());
	    List<AttributActif> listAttr = new ArrayList<AttributActif>();
	    listAttr= familleData.getAttributs();
	    List<AttributActif> listAttr2 = new ArrayList<AttributActif>();

	    for(AttributActif attr:listAttr) {
	
	        attributActifRepository.save(attr);
	    	listAttr2.add(attributActifRepository.save(attr));
	    	System.out.println("Added");
	    	 
	    }
	   
	    famille.setAttributs(listAttr2);
	    
        familleRepository.save(famille);
	    return famille;
		 
	}
	*/
	public  Famille updateFamille (Famille famille){
	    
		Category familleCategory = categoryRepository.findCategoryByNom(famille.getCategory().getNom());
	    famille.setCategory(familleCategory);

	    List<AttributActif> listAttr = new ArrayList<AttributActif>();
	    List<Actif> listActif = new ArrayList<Actif>();
	    listActif=famille.getActifs();
	    listAttr= famille.getAttributs();
	    List<AttributActif> listAttr2 = new ArrayList<AttributActif>();
	    
	    List<Actif> listActif2 = new ArrayList<Actif>();

	    for(AttributActif attr:listAttr) {
	
	    	attributActifRepository.save(attr);
	    	listAttr2.add(attributActifRepository.save(attr));
	    	
	    }
	    famille.setAttributs(listAttr2);
	    for(Actif act:listActif) {
	    	HashMap<String, String> data = new HashMap<String, String> ();
	    	for(AttributActif attr:listAttr) {
	    		
	    	
	    	data.put(attr.getNom(), act.getData().get(attr.getNom()));
	    	
	    	
	    	}
	    	act.setData(data);
	    	
	        echelle_CriticiteRepository.findEchelleById(act.getConfidentialite().getId());
	        
	        act.setConfidentialite(echelle_CriticiteRepository.findEchelleById(act.getConfidentialite().getId()));
	         
	        echelle_CriticiteRepository.findEchelleById(act.getDisponibilite().getId());
	        
	        act.setConfidentialite(echelle_CriticiteRepository.findEchelleById(act.getDisponibilite().getId()));
	        
	        echelle_CriticiteRepository.findEchelleById(act.getIntegrite().getId());
	        
	        act.setConfidentialite(echelle_CriticiteRepository.findEchelleById(act.getIntegrite().getId()));
	        actifRepository.save(act);
	    	
	    	listActif2.add(actifRepository.save(act));
	    }
	    famille.setActifs(listActif2);
	    return famille;
	} 
	
	public List<Famille> afficheParCat(Category category)
	{
		Category familleCategory = categoryRepository.findCategoryByNom(category.getNom());
		return  familleRepository.findFamilleByCategory(familleCategory);
	}
	/*
	@Override
	public FamilleDTO createFamille(FamilleDTO famille) {
		Category category= famille.getCategory();
		
		Famille persisted = Famille.getBuilder()
                .nom(famille.getNom())
                .description(famille.getDescription())
                .category(famille.getCategory())
                .build();
		//categoryRepository.save(category);
      familleRepository.save(persisted);
        return convertFamille(persisted);
	}
*/
	@Override
	public Map<String, Boolean> delete(String id) {
		Famille deleted = familleRepository.findFamilleById(id);
		Query query = Query.query(Criteria.where("$id").is(new ObjectId(deleted.getId())));
		Update update = new Update().pull("category", query);
		mongoTemplate.updateMulti(new Query(), update,Category.class);
		familleRepository.delete(deleted);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}
	@Override
	public List<Famille> findAll() {
		return  familleRepository.findAll();
	      
	}

	@Override
	public Famille update(Famille famille) {
		Optional<Famille> updated = familleRepository.findById(famille.getId());
		Famille v =updated.get();
		v.setDescription(famille.getDescription());
		v.setNom(famille.getNom());
		return familleRepository.save(v);
	}
    private List<FamilleDTO> convertFamilles(List<Famille> models) {
        return models.stream()
                .map(this::convertFamille)
                .collect(toList());
    }
    
    private FamilleDTO convertFamille(Famille model) {
    	FamilleDTO dto = new  FamilleDTO();
	 
	        dto.setId(model.getId());
	        dto.setNom(model.getNom());
	        dto.setDescription(model.getDescription());
	 
	        return dto;
	    }
	public Famille afficherFamilleParNom(Famille familleEntry) {
		   
		  return familleRepository.findFamilleByNom(familleEntry.getNom());
		   
	       
	}
	public Famille afficherFamilleParNomTree(Famille familleEntry) {
		   
		  Famille famille=familleRepository.findFamilleByNom(familleEntry.getNom());
		 
		  List<SousFamille> souFamilles = new ArrayList<SousFamille>();
		  List<SousFamille> souFamillesNew = new ArrayList<SousFamille>();
		  souFamilles=famille.getSousFamille();
		  for (SousFamille sf:souFamilles)
		  {   
			  List<Actif> actifs  = new ArrayList<Actif>();
			  List<Actif> actifNew  = new ArrayList<Actif>();
			  actifs=sf.getActifs();
			 
			  if(actifs.size() != 0) {
				
			 for(Actif ac:actifs){
			  {
				  HashMap<String, String> data = new HashMap<String, String> ();
				  System.out.println(ac.getId());
			     data = ac.getData();
			      if(ac.getProprietaire() != null) {
			      data.put("proprietaire", ac.getProprietaire().getName());
			      }
			      data.put("C",ac.getConfidentialite().getDegre());
			      data.put("I",ac.getIntegrite().getDegre());
			      data.put("D",ac.getDisponibilite().getDegre());
			      data.put("id",ac.getId());
			      ac.setData(data);
				  actifNew.add(ac);
			  }}
			
			  }
			  else {
				  System.out.println("vide");
			  }
			  sf.setActifs(actifNew);
			  souFamillesNew.add(sf);
		  }
		  famille.setSousFamille(souFamillesNew);
		  return famille;
	       
	}
	    public  Famille createFamille(Famille familleEntry){
	    
		Category familleCategory = categoryRepository.findCategoryByNom(familleEntry.getCategory().getNom());
		System.out.println(familleEntry.getSousFamille());
		List<Actif> actifs = new ArrayList<Actif>();
		Famille famille = new Famille();
	    famille.setCategory(familleCategory);
	    List<AttributActif> listAttr = new ArrayList<AttributActif>();
	    listAttr= familleEntry.getAttributs();
	    List<AttributActif> listAttr2 = new ArrayList<AttributActif>();
	    for(AttributActif attr:listAttr ) {
	    	attributActifRepository.save(attr);
	    	listAttr2.add(attributActifRepository.save(attr));
	    }
	    famille.setAttributs(listAttr2);
	    famille.setActifs(actifs);
	    List<SousFamille> sousFamilles = new ArrayList<SousFamille>();
	    sousFamilles=familleEntry.getSousFamille();
	    System.out.println(sousFamilles);
	    if (sousFamilles.size() != 0)
	    {
	    	sousFamilles=familleEntry.getSousFamille();
	    	List<SousFamille> sousFamilles2 = new ArrayList<SousFamille>();
	    	for(SousFamille sf:sousFamilles) {
	    		List<Actif> sfactifs = new ArrayList<Actif>();
	    		sf.setActifs(sfactifs);
	    		sousFamilleRepository.save(sf);
	    		sousFamilles2.add(sf);	
	    	}
	    	famille.setSousFamille(sousFamilles2);
	    }
	    List<Menace> menacesEntry = new ArrayList<Menace>();
	    menacesEntry=familleEntry.getMenaceList();
	    if(menacesEntry != null) {
	    List<Menace> menaces = new ArrayList<Menace>();
	    for(Menace menace:menacesEntry) {
	    	System.out.println(menace.getCode()+"here");
	    	menaces.add(menaceRepository.findMenaceByCode(menace.getCode()));
	    }
	    famille.setMenaceList(menaces);}
	    famille.setDescription(familleEntry.getDescription());
	    famille.setNom(familleEntry.getNom());
	    return familleRepository.save(famille);
		
	}
	    public long countAllFamilles() {
	    	long a;
	    	a =familleRepository.count();
	    	return a;
	    }
}
