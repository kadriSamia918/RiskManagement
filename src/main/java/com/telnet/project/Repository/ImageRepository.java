package com.telnet.project.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.telnet.project.Entities.ImageModel;

public interface ImageRepository extends MongoRepository <ImageModel, String>  {
	Optional<ImageModel> findByName(String name);

 ImageModel findImageByName(String username);
}
