package org.formation.service;

import java.util.List;


import org.formation.dao.CustomerDao;
import org.formation.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceCustomer")
public class ServiceCustomerImpl implements IServiceCustomer {

	@Autowired
	private CustomerDao customer;
	
	@Override
	public void persist(Customer c) throws Exception {
		customer.persist(c);
		
	}

	@Override
	public void merge(Customer c) throws Exception {
		customer.merge(c);
		
	}

	@Override
	public void remove(Long id) throws Exception {
		customer.remove(id);
		
	}

	@Override
	public Customer findById(Long id) throws Exception {
		
		return customer.findById(id);
	}

	@Override
	public List<Customer> findAll() throws Exception {
		
		return customer.findAll();
	}

	@Override
	public List<Customer> findByProperty(String prop, Object val) throws Exception {
		
		return customer.findByProperty(prop, val);
	}


	

}
