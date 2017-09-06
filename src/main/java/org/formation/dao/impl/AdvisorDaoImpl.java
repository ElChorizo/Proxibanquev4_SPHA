package org.formation.dao.impl;



import java.util.Collection;
import java.util.LinkedHashSet;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.formation.dao.AdvisorDao;

import org.formation.model.Advisor;
import org.formation.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AdvisorDaoImpl extends EntityDaoImpl<Advisor> implements AdvisorDao {

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ID_CLIENT")
	private Collection<Customer> customers = new LinkedHashSet<Customer>();
	
	
}
