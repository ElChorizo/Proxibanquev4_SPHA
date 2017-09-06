package org.formation.dao.impl;

import java.util.List;

import org.formation.dao.CustomerDao;
import org.formation.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CustomerDaoImpl extends EntityDaoImpl<Customer> implements CustomerDao {

	
}
