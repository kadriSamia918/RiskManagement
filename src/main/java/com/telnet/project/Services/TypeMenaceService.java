package com.telnet.project.Services;

import java.util.List;
import com.telnet.project.Entities.TypeMenace;

public interface TypeMenaceService {

	
	
	TypeMenace createTypeMenace(TypeMenace typeMenace);
	 
	void delete(TypeMenace typeMenace);
 
    List<TypeMenace> findAll();
 
    
 
    TypeMenace update(TypeMenace typeMenace);
}
