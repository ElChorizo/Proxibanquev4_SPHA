package org.formation.service;

import java.util.List;

import org.formation.model.Customer;

public interface IServiceCustomer {
	void persist(Customer c) throws Exception;

	void merge(Customer c) throws Exception;

	void remove(Long id) throws Exception;

	Customer findById(Long id) throws Exception;

	List<Customer> findAll() throws Exception;

	List<Customer> findByProperty(String prop, Object val) throws Exception;

}
