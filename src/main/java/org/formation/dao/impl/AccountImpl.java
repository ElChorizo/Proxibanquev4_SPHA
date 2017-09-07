package org.formation.dao.impl;

import org.formation.dao.AccountDao;
import org.formation.model.Account;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class AccountImpl extends EntityDaoImpl<Account> implements AccountDao {

}
