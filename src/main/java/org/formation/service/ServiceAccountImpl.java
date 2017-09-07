package org.formation.service;

import java.util.List;

import org.formation.dao.AccountDao;
import org.formation.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceAccount")
public class ServiceAccountImpl implements IServiceAccount {

	@Autowired
	private AccountDao accountDao;
	
	
	@Override
	public void persist(Account account) throws Exception {
		accountDao.persist(account);

	}

	@Override
	public void merge(Account account) throws Exception {
		accountDao.merge(account);

	}

	@Override
	public void remove(Long id) throws Exception {
		accountDao.remove(id);

	}

	@Override
	public Account findById(Long id) throws Exception {
		return accountDao.findById(id);
	}

	@Override
	public List<Account> findAll() throws Exception {
		return accountDao.findAll();
	}


	@Override
	public List<Account> findByProperty(String prop, Object val) throws Exception {
		return accountDao.findByProperty(prop, val);
		
	}

}
