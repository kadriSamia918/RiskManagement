package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Vulnerabilite;

@Repository
public interface VulnerabiliteRepository extends MongoRepository<Vulnerabilite, String> {

	List<Vulnerabilite> findAll();

	Vulnerabilite findVulnerabiliteByCode(String code);

	Vulnerabilite findVulnerabiliteById(String id);

	List<Vulnerabilite> findVulnerabiliteByFamille(String nom);

}
