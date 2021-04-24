package com.telnet.project.Entities;

import org.springframework.data.annotation.Id;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="RisqueLog")
public class RisqueLog {
	
	@Id
	private String id;
	private String codeRisque;
	private String saverId;
	private List<Change> changes;
	

	public String getSaverId() {
		return saverId;
	}
	public void setSaverId(String saverId) {
		this.saverId = saverId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public RisqueLog(String id, String codeRisque, String saverId, List<Change> changes) {
		super();
		this.id = id;
		this.codeRisque = codeRisque;
		this.saverId = saverId;
		this.changes = changes;
	}
	public String getCodeRisque() {
		return codeRisque;
	}
	public void setCodeRisque(String codeRisque) {
		this.codeRisque = codeRisque;
	}


	
	public List<Change> getChanges() {
		return changes;
	}
	public void setChanges(List<Change> changes) {
		this.changes = changes;
	}
	public RisqueLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
