package com.telnet.project.ServiceImpl;

import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.DTO.Echelle_CriticiteDTO;

import com.telnet.project.Entities.Critere_Securite;
import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.Repository.Critere_SecuriteRepository;
import com.telnet.project.Repository.Echelle_CriticiteRepository;
import com.telnet.project.Services.Echelle_CriticiteService;

@Service
public  class Echelle_CriticiteServiceImpl implements Echelle_CriticiteService {
	
    private  Echelle_CriticiteRepository echelle_CriticiteRepository; 
    @Autowired
    private  Critere_SecuriteRepository critere_SecuriteRepository;
	
    @Autowired
    Echelle_CriticiteServiceImpl(Echelle_CriticiteRepository echelle_CriticiteRepository) {
        this.echelle_CriticiteRepository = echelle_CriticiteRepository;
    }



	
	public Critere_Securite createCritere_Securite(Critere_Securite critere_Securite) {
		/*Echelle_Criticite persisted = Echelle_Criticite.getBuilder()
                .nom(echelle_Criticite.getNom())
                .description(echelle_Criticite.getDescription())
                .build();*/
      //  persisted = echelle_CriticiteRepository.save(persisted);
      ///  Critere_Securite critere_Securite=critere_SecuriteRepository.findByNom("confidentialite");
    
      ///  critere_Securite.getNiveau().add(persisted);
      
        
        
        return  critere_SecuriteRepository.save(critere_Securite);
	}
	private Echelle_CriticiteDTO convertEchelle_Criticite(Echelle_Criticite model) {
		Echelle_CriticiteDTO dto = new  Echelle_CriticiteDTO();
	 
	        dto.setId(model.getId());
	        dto.setNom(model.getNom());
	        dto.setDescription(model.getDescription());
	        dto.setCouleur(model.getCouleur());
	        dto.setDegre(model.getDegre());
	 
	        return dto;
	    }

	@Override
	public Map<String, Boolean> delete(String id) {
		Echelle_Criticite deleted = echelle_CriticiteRepository.findEchelle_CriticiteById(id);
		echelle_CriticiteRepository.delete(deleted);
		 Map<String, Boolean> response = new HashMap<>();
	        response.put("deleted", Boolean.TRUE);
	        return response;
	}
	@Override
	public List<Echelle_CriticiteDTO> findAll() {
		   List<Echelle_Criticite> echelleEntries = echelle_CriticiteRepository.findAll();
	        return convertEchelles(echelleEntries);
	
	}

	
	public Echelle_Criticite updateEchelle(Echelle_Criticite echelle_Criticite) {
		
		Optional<Echelle_Criticite> updated = echelle_CriticiteRepository.findById(echelle_Criticite.getId());
		Echelle_Criticite v =updated.get();
		v.setDescription(echelle_Criticite.getDescription());
		v.setNom(echelle_Criticite.getNom());
		v.setCouleur(echelle_Criticite.getCouleur());
		v.setDegre(echelle_Criticite.getDegre());
		
		
		return echelle_CriticiteRepository.save(v);
		
	}
	
	   private List<Echelle_CriticiteDTO> convertEchelles(List<Echelle_Criticite> models) {
	        return models.stream()
	                .map(this::convertEchelle_Criticite)
	                .collect(toList());
	    }
	   /*
	    * 
	    * 
		public Echelle_Criticite afficherNiveauActifConfidentialite(Actif actif)
		{	Echelle_Criticite echelle_Criticite = new Echelle_Criticite();
			Critere_Securite critere_Securite = critere_SecuriteRepository.findByNom("confidentialite");
			List<Echelle_Criticite> echelle_CriticiteList = critere_Securite.getNiveau();
			for(Echelle_Criticite niveau:echelle_CriticiteList)
			{
				if(niveau.getDegre()== actif.getConfidentialite())
				{
					echelle_Criticite=niveau;
				}
			}
			
			return echelle_Criticite;
		}
*/
	   /*
		public Echelle_Criticite afficherNiveauActifDisponibilite(Actif actif)
		{	Echelle_Criticite echelle_Criticite = new Echelle_Criticite();
			Critere_Securite critere_Securite = critere_SecuriteRepository.findByNom("disponibilite");
			List<Echelle_Criticite> echelle_CriticiteList = critere_Securite.getNiveau();
			for(Echelle_Criticite niveau:echelle_CriticiteList)
			{
				if(niveau.getDegre()== actif.getDisponibilite())
				{
					echelle_Criticite=niveau;
				}
			}
			
			return echelle_Criticite;
		}*/
	   /*
		public Echelle_Criticite afficherNiveauActifIntegrite(Actif actif)
		{	Echelle_Criticite echelle_Criticite = new Echelle_Criticite();
			Critere_Securite critere_Securite = critere_SecuriteRepository.findByNom("integrite");
			List<Echelle_Criticite> echelle_CriticiteList = critere_Securite.getNiveau();
			for(Echelle_Criticite niveau:echelle_CriticiteList)
			{
				if(niveau.getDegre()== actif.getIntegrite())
				{
					echelle_Criticite=niveau;
				}
			}
			
			return echelle_Criticite;
		}

*/


		@Override
		public Echelle_Criticite update(Echelle_Criticite echelle_Criticite) {
			// TODO Auto-generated method stub
			return null;
		}
}
