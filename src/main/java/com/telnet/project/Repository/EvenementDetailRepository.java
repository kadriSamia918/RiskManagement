package com.telnet.project.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.EvenementDetail;
import com.telnet.project.Entities.Famille;
@Repository
public interface EvenementDetailRepository extends MongoRepository<EvenementDetail,String>  {
	EvenementDetail findEvenementDetailByCode(String code);
	List<EvenementDetail> findEvenementDetailByFamille(Famille famille);

	EvenementDetail findEvenementDetailById(String id);
	List<EvenementDetail> findEvenementDetailByCriterImpact(String impact);

}
