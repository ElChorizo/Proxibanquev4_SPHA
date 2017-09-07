package org.formation.tests;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formation.dao.AccountDao;
import org.formation.model.Account;
import org.formation.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;




@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class persistenceTests {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Autowired
    AccountDao accountDao;
	
	@Test
	@Transactional
	public void testSaveCustomerWithAccount() throws Exception {
		Customer c = new Customer();
		c.getListAccount().add(new Account());
		entityManager.persist(c);
		entityManager.flush();
		assertNotNull(c.getId());
	}

	
	
	
}
