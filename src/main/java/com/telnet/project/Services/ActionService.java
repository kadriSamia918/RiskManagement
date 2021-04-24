package com.telnet.project.Services;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.telnet.project.Entities.Action;
import com.telnet.project.Entities.CategorieAction;

public interface ActionService {
	
	Action createAction(Action action);
	 
	 Map<String, Boolean> delete(String id);

   List<Action> findAll();

   //AttributActif findById(String id);

   Action update(Action action);


Action getActionByCode(String code);


List<Action> getActionsByCategorie(@Valid String categorie);

}