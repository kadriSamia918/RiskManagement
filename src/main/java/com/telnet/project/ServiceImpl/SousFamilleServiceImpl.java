package com.telnet.project.ServiceImpl;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.DTO.SousFamilleDTO;
import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Repository.SousFamilleRepository;
import com.telnet.project.Services.SousFamilleService;

@Service
public class SousFamilleServiceImpl implements SousFamilleService {
	
    private  SousFamilleRepository sousfamilleRepository; 
	

    @Autowired
    SousFamilleServiceImpl(SousFamilleRepository sousfamilleRepository) {
        this.sousfamilleRepository = sousfamilleRepository;
    }


	@Override
	public SousFamilleDTO createFamille(SousFamilleDTO famille) {
		SousFamille persisted = SousFamille.getBuilder()
                .nom(famille.getNom())
                .description(famille.getDescription())
                .build();
        persisted = sousfamilleRepository.save(persisted);
        return convertSousFamille(persisted);
	}
    
	public SousFamille ajoutSousFamille(SousFamille sousFamilleEntry) {
		SousFamille sousFamille=new SousFamille();
		sousFamille.setNom(sousFamilleEntry.getNom());
		sousFamille.setDescription(sousFamilleEntry.getDescription());
		List<Actif> actifs = new ArrayList<Actif>();
		sousFamille.setActifs(actifs);
		sousfamilleRepository.save(sousFamille);
		return sousFamille;
	}

	@Override
	public void delete(SousFamille famille) {
		sousfamilleRepository.delete(famille);		
	}


	@Override
	public List<SousFamilleDTO> findAll() {
		 List<SousFamille> familleEntries = sousfamilleRepository.findAll();
	        return convertSousFamilles(familleEntries);
	}


	@Override
	public SousFamille update(SousFamille famille) {
		Optional<SousFamille> updated = sousfamilleRepository.findById(famille.getId());
		SousFamille v =updated.get();
		v.setDescription(famille.getDescription());
		v.setNom(famille.getNom());
		
		v.setActifs(famille.getActifs());
		return sousfamilleRepository.save(v);
	}
	
    private List<SousFamilleDTO> convertSousFamilles(List<SousFamille> models) {
        return models.stream()
                .map(this::convertSousFamille)
                .collect(toList());
    }
    
    private SousFamilleDTO convertSousFamille(SousFamille model) {
    	SousFamilleDTO dto = new  SousFamilleDTO();
	 
	        dto.setId(model.getId());
	        dto.setNom(model.getNom());
	        dto.setDescription(model.getDescription());
	 
	        return dto;
	    }


	public SousFamille afficherSFparNom(String sousFamille) {
	    SousFamille sf = new SousFamille();
	    sf=sousfamilleRepository.findSousFamilleByNom(sousFamille)	;	
		return sf;
	}
}
