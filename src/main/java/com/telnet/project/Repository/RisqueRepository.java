package com.telnet.project.Repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Echelle_Criticite;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Risque;

@Repository

public interface RisqueRepository extends MongoRepository<Risque,String> {
	  List<Risque> findAll();
	Risque findRisqueByCode(String code);
	List<Risque> findRisqueByFamille(@Valid String nomFamille);
	List<Risque> findBylistActifContaining(Actif actifs);

}
