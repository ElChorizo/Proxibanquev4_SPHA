package org.formation.service;

import java.util.List;

import org.formation.dao.AccountDao;
import org.formation.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AL, SRL, PHL
 *
 */
@Service("serviceAccount")
public class ServiceAccountImpl implements IServiceAccount {

	@Autowired
	private AccountDao accountDao;

	/**
	 * Cette m�thode permet d'ajouter un nouveau compte en base de donn�es. Elle
	 * fait appel � l'interface AccountDao.
	 *
	 */
	@Override
	public void persist(Account account) throws Exception {
		accountDao.persist(account);

	}

	/**
	 * Cette m�thode permet de mettre � jour un compte en base de donn�es. Elle fait
	 * appel � l'interface AccountDao.
	 *
	 */
	@Override
	public void merge(Account account) throws Exception {
		accountDao.merge(account);

	}

	/**
	 * Cette m�thode permet de supprimer un compte en base de donn�es. Elle fait
	 * appel � l'interface AccountDao.
	 *
	 */
	@Override
	public void remove(Long id) throws Exception {
		accountDao.remove(id);

	}

	/**
	 * Cette m�thode permet de trouver un compte en base de donn�es via son id, et
	 * de le transmettre � la m�thode qui l'appelle. Elle s'appuie sur l'interface
	 * AccountDao.
	 *
	 */
	@Override
	public Account findById(Long id) throws Exception {
		return accountDao.findById(id);
	}

	/**
	 * Cette m�thode permet de transmettre la liste de tous les comptes existants en
	 * base. Elle s'appuie sur l'interface AccountDao.
	 *
	 */
	@Override
	public List<Account> findAll() throws Exception {
		return accountDao.findAll();
	}

	/**
	 * Cette m�thode permet de trouver des comptes en base de donn�es en fonction de
	 * la valeur d'un de leurs attributs, et de les retourner � la m�thode qui
	 * l'appelle. Elle s'appuie sur l'interface AccountDao.
	 *
	 */
	@Override
	public List<Account> findByProperty(String prop, Object val) throws Exception {
		return accountDao.findByProperty(prop, val);

	}

}
