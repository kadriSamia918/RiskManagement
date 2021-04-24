package com.telnet.project.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.Photo;

public interface PhotoRepository extends MongoRepository<Photo , String> { }