package com.telnet.project.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.telnet.project.Entities.FileDB;
import com.telnet.project.Entities.Photo;
import com.telnet.project.Repository.FileDBRepository;
import com.telnet.project.ServiceImpl.FileStorageServiceImpl;
import com.telnet.project.ServiceImpl.PhotoService;
import com.telnet.project.payload.response.ResponseFile;
import com.telnet.project.payload.response.ResponseMessage;

@Component
@RestController
@RequestMapping("/api/pfe")
@CrossOrigin( "*")
public class FileController {

	  @Autowired
	  private FileStorageServiceImpl storageService;
	  
	/*  @Autowired
	  private PhotoService photoService;
	  @Autowired
	  private FileDBRepository  fileDBRepository;

	  @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    try {
	    	
	      storageService.store(file);
	      message = "Uploaded the file successfully: " + file.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } 
	    catch (Exception e) {
	      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	    }
	  }
	  
	   @GetMapping(path = { "/get/{imageName}" })
	   
	       public FileDB getImage(@PathVariable("imageName") String imageName) throws IOException {
	   
	           final Optional<FileDB> retrievedImage = fileDBRepository.findByName(imageName);
	   
	           FileDB img = new FileDB(retrievedImage.get().getName(), retrievedImage.get().getType(),
	   
	                   decompressBytes(retrievedImage.get().getData()));
	   
	           return img;
	   
	       }
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

	  @GetMapping("/files")
	  public ResponseEntity<List<ResponseFile>> getListFiles() {
	    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
	      String fileDownloadUri = ServletUriComponentsBuilder
	          .fromCurrentContextPath()
	          .path("/files/")
	          .path(dbFile.getId())
	          .toUriString();

	      return new ResponseFile(
	          dbFile.getName(),
	          fileDownloadUri,
	          dbFile.getType(),
	          dbFile.getData().length);
	    }).collect(Collectors.toList());

	    return ResponseEntity.status(HttpStatus.OK).body(files);
	  }

	  @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
	    FileDB fileDB = storageService.getFile(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
	        .body(fileDB.getData());
	  }
	  @PostMapping("/photos/add")
	  public String addPhoto(@RequestParam("title") String title, 
	    @RequestParam("image") MultipartFile image, Model model) 
	    throws IOException {
	      String id = photoService.addPhoto(title, image);
	      return "redirect:/photos/" + id;
	  }
		@GetMapping("/photos/{id}")
		public String getPhoto(@PathVariable String id, Model model) {
		    Photo photo = photoService.getPhoto(id);
		    model.addAttribute("title", photo.getTitle());
		    model.addAttribute("image", 
		      Base64.getEncoder().encodeToString(photo.getImage().getData()));
		    return "photos";
		}*/
	}
