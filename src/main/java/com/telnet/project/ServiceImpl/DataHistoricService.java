package com.telnet.project.ServiceImpl;


import java.util.List;
import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telnet.project.Entities.FaciliteExplVuln;
import com.telnet.project.Entities.Risque;

@Service
public class DataHistoricService {
	
	@Autowired 
	private Javers javers;
	
	public List<CdoSnapshot> readFromHistorie(){
		//QueryBuilder queryBuilder = QueryBuilder.byInstanceId(risque,Risque.class).withSnapshotTypeUpdate();
		//List<CdoSnapshot> changes = javers.findSnapshots(queryBuilder.build());
		FaciliteExplVuln risque= new FaciliteExplVuln();
		risque.setId("5eb07a5e6946076400e21e69");
		List<CdoSnapshot> snapshots = javers.findSnapshots( QueryBuilder.byInstance(risque).build());
		return snapshots;
	}

}
