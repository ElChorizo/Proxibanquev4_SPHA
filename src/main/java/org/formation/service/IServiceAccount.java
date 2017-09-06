package org.formation.service;

import java.util.List;

import org.formation.model.Account;


public interface IServiceAccount {
	void persist(Account account) throws Exception;

	void merge(Account account) throws Exception;

	void remove(Long id) throws Exception;

	Account findById(Long id) throws Exception;

	List<Account> findAll() throws Exception;

	List<Account> findByProperty(String prop, Object val) throws Exception;

	List<Account> findInRange(int firstResult, int maxResults) throws Exception;

	long count() throws Exception;
}
