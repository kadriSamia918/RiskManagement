package com.telnet.project.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.Actif;
import com.telnet.project.Entities.Famille;
import com.telnet.project.Entities.Privilege;
import com.telnet.project.Entities.RisqueDetail;
import com.telnet.project.Entities.Role;
import com.telnet.project.Entities.SousFamille;
import com.telnet.project.Entities.User;
import com.telnet.project.Repository.PrivilegeRepository;
import com.telnet.project.Repository.RoleRepository;
import com.telnet.project.Services.RolesService;

@Service
public class RolesServiceImpl implements RolesService{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PrivilegeRepository privilegeRepository;
	

	 @Autowired
	 MongoTemplate mongoTemplate; 
	

	@Override
	public List<Role> findAll() {
	
		return roleRepository.findAll();
	}

	@Override
	public  Map<String, Role>  save(Role role) {
		Map<String, Role> response = new HashMap<>();
		Role saved = new Role();
		List<Role> allRoles=new ArrayList<Role>();
		allRoles=roleRepository.findAll();
		for(Role r:allRoles) {
			if(r.getName().contentEquals(role.getName())) 
			{
				 response.put("RoleName_Existe", null);
				 System.out.println(response);
				  return response;
			}
			/*else {
				Set<Privilege> privileges = new HashSet<Privilege>();
				Set<Privilege> privilegesEntry = new HashSet<Privilege>();
				privileges=r.getPrivileges();
				privilegesEntry=role.getPrivileges();
				int n=0;
				for(Privilege pr:privileges) {
					
					for(Privilege prEntry:privilegesEntry)
					{
					
							if(prEntry.isAjouter() == pr.isAjouter() &&
									prEntry.isLire() == pr.isLire() &&
									prEntry.isModifier() == pr.isModifier()&&
									prEntry.isSupprimer() == pr.isSupprimer()) 
							{
							System.out.println(n);
							n++;
							}
						
					}
				}
				if(n == role.getPrivileges().size()) {
					response.put("RoleExiste", null);
					 System.out.println(response);
					  return response;
				}
			
			}*/
			
		}
		saved.setName(role.getName());
		saved.setDescription(role.getDescription());
		Set<Privilege> privileges = new HashSet<Privilege>();
		Set<Privilege> privilegeSavedList = new HashSet<Privilege>();
		privileges=role.getPrivileges();
		for(Privilege pr:privileges) {
			privilegeSavedList.add(pr);
		}
		
		saved.setPrivileges(privilegeSavedList);
		 response.put("RoleAjouter", saved);
		 System.out.println(response);
		 roleRepository.save(saved);
		  return response;
	
	}
	
	public Map<String, Boolean> delete(String id) 
{
		Role deleted = roleRepository.findRoleById(id);
	    
		 Query query = Query.query(Criteria.where("$id").is(new ObjectId(deleted.getId())));
		 Update update = new Update().pull("roles", query);
		 mongoTemplate.updateMulti(new Query(), update, User.class);
		 roleRepository.delete(deleted);
		 Map<String, Boolean> response = new HashMap<>();
	     response.put("deleted", Boolean.TRUE);
	     return response;
	}
	public Map<String, Boolean> update(Role roleEntry) 
{
	    
		Map<String, Boolean> response = new HashMap<>();

		 Query query = new Query();
		 Role role = roleRepository.findRoleById(roleEntry.getId());
		 if(role != null) {
			query.addCriteria(Criteria.where("id").is(role.getId()));
			Update update = new Update();
			update.set("name", roleEntry.getName());
			update.set("description",roleEntry.getDescription());
			update.set("privileges",roleEntry.getPrivileges());
			mongoTemplate.updateFirst(query, update, Role.class);
	     response.put("updated", Boolean.TRUE);}
		 else {
			 response.put("updated", Boolean.FALSE);
		 }
	     return response;
	}
}
