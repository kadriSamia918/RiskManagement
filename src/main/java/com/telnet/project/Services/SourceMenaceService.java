package com.telnet.project.Services;

import java.util.List;

import com.telnet.project.Entities.SourceMenace;

public interface SourceMenaceService {
	
	SourceMenace createSourceMenace(SourceMenace sourceMenace);
	 
	void delete(SourceMenace sourceMenace);
 
    List<SourceMenace> findAll();
 
    
 
    SourceMenace update(SourceMenace sourceMenace);

}
