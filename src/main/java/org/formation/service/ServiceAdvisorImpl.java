package org.formation.service;

import java.util.List;

import org.formation.dao.AdvisorDao;
import org.formation.model.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceAdvisor")
public class ServiceAdvisorImpl implements IServiceAdvisor{

	@Autowired
	private AdvisorDao advisor;
	
	@Override
	public void persist(Advisor a) throws Exception {
		advisor.persist(a);
		
	}

	@Override
	public void merge(Advisor a) throws Exception {
		advisor.merge(a);
		
	}

	@Override
	public void remove(Long id) throws Exception {
		advisor.remove(id);
		
	}

	@Override
	public Advisor findById(Long id) throws Exception {
		
		return advisor.findById(id);
	}

	@Override
	public List<Advisor> findAll() throws Exception {
		
		return advisor.findAll();
	}

}
