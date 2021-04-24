package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.Privilege;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege,String>{

	Privilege findPrivilegeByName(String id);

}
