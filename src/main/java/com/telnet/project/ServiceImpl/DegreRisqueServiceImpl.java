package com.telnet.project.ServiceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.DegreRisque;
import com.telnet.project.Repository.DegreRisqueRepository;
import com.telnet.project.Services.DegreRisqueService;

@Service
public class DegreRisqueServiceImpl implements DegreRisqueService {


	@Autowired
    private  DegreRisqueRepository degreRisqueRepository;
	@Override
	public Map<String, Boolean> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DegreRisque> findAll() {
		return degreRisqueRepository.findAll();
	}

	@Override
	public DegreRisque update(DegreRisque degreRisque) {
		// TODO Auto-generated method stub
		return null;
	}

}
