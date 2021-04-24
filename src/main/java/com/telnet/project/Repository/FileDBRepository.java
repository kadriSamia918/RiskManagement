package com.telnet.project.Repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.telnet.project.Entities.FileDB;

@Repository
public interface FileDBRepository extends MongoRepository<FileDB, String> {
	 Optional<FileDB> findByName(String name);
	}

