package com.telnet.project.Repository;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.CategorieAction;

@Repository
public interface ActionRepository extends MongoRepository<Action,String>{

	Action findActionByCodeAction(String codeAction);
	Action findActionById(String id);
	List<Action> findActionByCategorie(@Valid String categorie);

}
