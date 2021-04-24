package com.telnet.project.Entities;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

  @Id
  private String id;

  private String name;
  
  private String description;
  

 // @DBRef
  private Set<Privilege> privileges = new HashSet<>();
  
  public Set<Privilege> getPrivileges() {
	return privileges;
}

public void setPrivileges(Set<Privilege> privileges) {
	this.privileges = privileges;
}

public Role() {

  }

  public Role(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Role(String id, String name, String description, Set<Privilege> privileges) {
	super();
	this.id = id;
	this.name = name;
	this.description = description;
	this.privileges = privileges;
}
  

}
