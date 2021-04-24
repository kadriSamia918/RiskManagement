package com.telnet.project.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.TypeMenace;
import com.telnet.project.Repository.TypeMenaceRepository;
import com.telnet.project.Services.TypeMenaceService;

@Service
public class TypeMenaceServiceImpl implements TypeMenaceService {
	@Autowired
	private TypeMenaceRepository typeMenaceRepository;
	@Override
	public TypeMenace createTypeMenace(TypeMenace typeMenaceEntry) {

		TypeMenace typeMenace = new TypeMenace();
		typeMenace.setDescription(typeMenaceEntry.getDescription());
		typeMenaceRepository.save(typeMenace);
		return typeMenaceRepository.save(typeMenace);
	}

	@Override
	public void delete(TypeMenace typeMenace) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TypeMenace> findAll() {
		return typeMenaceRepository.findAll();
	}

	@Override
	public TypeMenace update(TypeMenace typeMenaceEntry) {
		
		TypeMenace typeMenace =new TypeMenace();
		typeMenace=typeMenaceRepository.findTypeMenaceById(typeMenaceEntry.getId());
		
		typeMenace.setDescription(typeMenaceEntry.getDescription());
		
		return typeMenaceRepository.save(typeMenace);
	}

}
