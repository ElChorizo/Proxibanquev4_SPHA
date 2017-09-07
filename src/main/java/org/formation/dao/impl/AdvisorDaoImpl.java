package org.formation.dao.impl;

import org.formation.dao.AdvisorDao;

import org.formation.model.Advisor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AdvisorDaoImpl extends EntityDaoImpl<Advisor> implements AdvisorDao {

}
