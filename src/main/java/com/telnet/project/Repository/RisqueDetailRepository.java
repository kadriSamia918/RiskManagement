package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.RisqueDetail;

@Repository
public interface RisqueDetailRepository extends MongoRepository <RisqueDetail,String> {

}
