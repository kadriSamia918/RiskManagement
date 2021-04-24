package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.CategorieAction;

@Repository
public interface CategorieActionRepository extends MongoRepository<CategorieAction, String>  {

	List<CategorieAction> findAll();

}
