package org.formation.service;

import java.util.List;

import org.formation.model.Advisor;


public interface IServiceAdvisor {
void persist(Advisor a) throws Exception;
	
	void merge(Advisor a) throws Exception;

	void remove(Long id) throws Exception;
	
	Advisor findById(Long id) throws Exception;
	
	List<Advisor> findAll() throws Exception;
	
	List<Advisor> findByProperty(String prop, Object val) throws Exception;
	
	List<Advisor> findInRange(int firstResult, int maxResults) throws Exception;
	
	long count() throws Exception;
}
