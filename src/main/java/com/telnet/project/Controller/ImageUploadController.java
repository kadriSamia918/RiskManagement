package com.telnet.project.Controller;


import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.expression.spel.ast.Selection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.DBRef;
import com.telnet.project.Entities.ImageModel;
import com.telnet.project.Entities.User;
import com.telnet.project.Repository.ImageRepository;
import com.telnet.project.Repository.UserRepository;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class ImageUploadController {

	@Autowired
	ImageRepository imageRepository;
	
	@Autowired UserRepository userRepository;
	
	 @Autowired
	 MongoTemplate mongoTemplate; 

	@PostMapping("/upload")
	public BodyBuilder uplaodImage(@RequestPart("imageFile") MultipartFile file, @RequestPart("user") String userString) throws IOException {
	
		User user = userRepository.findUserByUsername(userString);
		System.out.println(user.getEmail());
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		//System.out.println(user.getUsername());
		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		img.setName(user.getUsername());
		imageRepository.save(img);
		System.out.print("gggggggggggggggggggg");
		//user.setProfilePic(img.getName());
		Query query = new Query();
    	query.addCriteria(Criteria.where("id").is(new ObjectId(user.getId())));
    	Update update = new Update();
    			update.set("profilePic", new DBRef("image", new ObjectId((imageRepository.save(img)).getId())));
    	mongoTemplate.updateFirst(query, update, User.class);
    	//userRepository.save(user);
    
		return ResponseEntity.status(HttpStatus.OK);
	}
	@GetMapping(path = { "/get/{imageName}" })
	public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
		System.out.println(imageName + "hi");
		final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
				decompressBytes(retrievedImage.get().getPicByte()));
		return img;
	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}
