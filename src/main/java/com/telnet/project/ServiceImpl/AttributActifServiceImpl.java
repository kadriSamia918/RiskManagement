package com.telnet.project.ServiceImpl;

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

import com.telnet.project.DTO.AttributActifDTO;
import com.telnet.project.Entities.AttributActif;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Repository.AttributActifRepository;
import com.telnet.project.Repository.FamilleRepository;
import com.telnet.project.Services.AttributActifService;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class AttributActifServiceImpl implements AttributActifService {
	
	@Autowired
	MongoTemplate mongoTemplate; 
	
    private  AttributActifRepository attributActifRepository; 
	@Autowired
    private  FamilleRepository familleRepository; 
    
    @Autowired
    AttributActifServiceImpl(AttributActifRepository attributActifRepository) {
        this.attributActifRepository = attributActifRepository;
    }

	@Override
	public AttributActifDTO createAttribut(AttributActifDTO attributActif) {
		AttributActif persisted = AttributActif.getBuilder()
                .nom(attributActif.getNom())
                .type(attributActif.getType())
                .build();
        persisted = attributActifRepository.save(persisted);
        return convertAttributActif(persisted);
    }
	
	public AttributActif createAttributActif(AttributActif attributActif) {
		AttributActif persisted = new AttributActif();
		
        persisted = attributActifRepository.save(attributActif);
        return persisted;
    }

   @Override
	public Map<String, Boolean> delete(String id) {
		AttributActif deleted = attributActifRepository.findAttributActifById(id);
		Query query = Query.query(Criteria.where("$id").is(new ObjectId(deleted.getId())));
		Update update = new Update().pull("attributActif", query);
		mongoTemplate.updateMulti(new Query(), update,Famille.class);
		attributActifRepository.delete(deleted);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}

	@Override
	public List<AttributActifDTO> findAll() {
		   List<AttributActif> attributEntries = attributActifRepository.findAll();
	        return convertAttributs(attributEntries);
	}
	public List<AttributActif> afficherParFamille(Famille familleEntry) {
		 System.out.println(familleEntry.getNom() +"here");
		   Famille	famille = familleRepository.findFamilleByNom(familleEntry.getNom());
		  
		   List<AttributActif> attributs = famille.getAttributs();
		   System.out.println(famille.getAttributs());
	        return famille.getAttributs();
	}
/*	@Override
	public AttributActif findById(String id) {
		 AttributActif found = findById(id);
        return found;
	}*/

	@Override
	public AttributActif update(AttributActif attributActif) {
		Optional<AttributActif> updated = attributActifRepository.findById(attributActif.getId());
		AttributActif v =updated.get();
		v.setType(attributActif.getType());
		v.setNom(attributActif.getNom());
		
		
		return attributActifRepository.save(v);
	}
	private AttributActifDTO convertAttributActif(AttributActif model) {
		  AttributActifDTO dto = new  AttributActifDTO();
	 
	        dto.setId_AttributActif(model.getId());
	        dto.setNom(model.getNom());
	        dto.setType(model.getType());
	 
	        return dto;
	    }
    private List<AttributActifDTO> convertAttributs(List<AttributActif> models) {
        return models.stream()
                .map(this::convertAttributActif)
                .collect(toList());
    }


	
	


	public Famille updateAttributFamille(@Valid Famille familleEntry) {
		Famille updated = familleRepository.findFamilleByNom(familleEntry.getNom());
		    List<AttributActif> listAttr = new ArrayList<AttributActif>();
		    listAttr= familleEntry.getAttributs();
		    List<AttributActif> listAttr2 = new ArrayList<AttributActif>();
		    for(AttributActif attr:listAttr ) {
		    	attributActifRepository.save(attr);
		    	listAttr2.add(attributActifRepository.save(attr));
		    }
		    updated.setAttributs(listAttr2);
		return familleRepository.save(updated);
	}


	public List<AttributActif> updateAttribute(String nom, @Valid AttributActif attributEntry) {
	
		Famille famille =familleRepository.findFamilleByNom(nom);
		
		List<AttributActif> attrList=famille.getAttributs();
		
		List<AttributActif> newList= new ArrayList<AttributActif>();
		
		for(AttributActif attr:attrList) {
			
			if(attr.getId().contentEquals(attributEntry.getId()) ) {
				
				attr.setNom(attributEntry.getNom());
				attr.setType(attributEntry.getType());
				attributActifRepository.save(attr);
				
				newList.add(attr);
			}else{
			newList.add(attr);
		}}
		
		famille.setAttributs(newList);
		
		familleRepository.save(famille);
		return famille.getAttributs();
	}
	public AttributActif ajoutAttribut(String nom, @Valid AttributActif attributEntry) {
		
		Famille famille =familleRepository.findFamilleByNom(nom);
		
		List<AttributActif> attrList=famille.getAttributs();
		attributActifRepository.save(attributEntry);
		attrList.add(attributEntry);
        
		famille.setAttributs(attrList);
		
		familleRepository.save(famille);
		return attributActifRepository.save(attributEntry);
		}
    

	


}
