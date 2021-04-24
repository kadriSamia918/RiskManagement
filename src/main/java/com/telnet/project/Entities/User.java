package com.telnet.project.Entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "users")
public class User {
  @Id
  private String id;
  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @DBRef
  @Field("roles")
  @JsonProperty(access = JsonProperty.Access.AUTO)
  private Set<Role> roles;
  
  @DBRef
  @JsonProperty(access = JsonProperty.Access.AUTO)
  @Field("profilePic")
  private ImageModel profilePic;
  
  private Date lastConnection;
  
  private String name;
  
  private String lastName;
  
  private boolean active;
  
  public ImageModel getProfilePic() {
	return profilePic;
}

public void setProfilePic(ImageModel profilePic) {
	this.profilePic = profilePic;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public User() {
  }
  public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password,String name,String lastName) {
	super();

	this.username = username;
	this.email = email;
	this.password = password;
	this.name = name;
	this.lastName = lastName;
	
}

public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

public Date getLastConnection() {
	return lastConnection;
}

public void setLastConnection(Date lastConnection) {
	this.lastConnection = lastConnection;
}

public User(String id, @NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email,
		@NotBlank @Size(max = 120) String password, Set<Role> roles, ImageModel profilePic, Date lastConnection) {
	super();
	this.id = id;
	this.username = username;
	this.email = email;
	this.password = password;
	this.roles = roles;
	this.profilePic = profilePic;
	this.lastConnection = lastConnection;
}

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}
  
}