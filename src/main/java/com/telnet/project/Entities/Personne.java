package com.telnet.project.Entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Personne")
public class Personne {
	
	@Id
	private String id;
	private String name;
	/*private String firstName;
	private String lastName;
	@Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
	private String email;
	private String password;
	private Date dateOfBirth;
	private String sex;
	private String address;
	private String tel; 
	private Boolean active;*/
	private List<Actif> listActif;
	/*public Personne(String id_user, String firstName, String lastName, String email, String password, Date dateOfBirth,
			String sex, String address, String tel, Boolean active,List<Actif> listActif) {
		super();
		this.id = id_user;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.address = address;
		this.tel = tel;
		this.active = active;
		this.listActif=listActif;
		}
	public List<Actif> getListActif() {
		return listActif;
	}
	public void setListActif(List<Actif> listActif) {
		this.listActif = listActif;
	}
	public String getId() {
		return id;
	}
	public void setId(String id_user) {
		this.id= id_user;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}
	*/
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
	public List<Actif> getListActif() {
		return listActif;
	}
	public void setListActif(List<Actif> listActif) {
		this.listActif = listActif;
	}
	public Personne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Personne(String id, String name, List<Actif> listActif) {
		super();
		this.id = id;
		this.name = name;
		this.listActif = listActif;
	}
	
	

}
