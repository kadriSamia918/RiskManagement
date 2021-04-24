package com.telnet.project.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.SourceMenace;
import com.telnet.project.Entities.TypeMenace;
import com.telnet.project.Repository.SourceMenaceRepository;
import com.telnet.project.Repository.TypeMenaceRepository;
import com.telnet.project.Services.SourceMenaceService;
import com.telnet.project.Services.TypeMenaceService;

@Service
public class SourceMenaceServiceImpl implements SourceMenaceService {
	@Autowired
	private SourceMenaceRepository sourceMenaceRepository;
	@Override
	public SourceMenace createSourceMenace(SourceMenace sourceMenaceEntry) {

		SourceMenace sourceMenace = new SourceMenace();
		sourceMenace.setDescription(sourceMenaceEntry.getDescription());
		sourceMenaceRepository.save(sourceMenace);
		return sourceMenaceRepository.save(sourceMenace);
	}

	@Override
	public void delete(SourceMenace typeMenace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SourceMenace> findAll() {
		return sourceMenaceRepository.findAll();
	}

	@Override
	public SourceMenace update(SourceMenace sourceMenaceEntry) {
		
		SourceMenace sourceMenace =new SourceMenace();
		sourceMenace=sourceMenaceRepository.findSourceMenaceById(sourceMenaceEntry.getId());
		
		sourceMenace.setDescription(sourceMenaceEntry.getDescription());
		
		return sourceMenaceRepository.save(sourceMenace);
	}

}
