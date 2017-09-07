package org.formation.service;

import java.util.List;

import org.formation.dao.CustomerDao;
import org.formation.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AL, SRL, PHL
 *
 */
@Service("serviceCustomer")
public class ServiceCustomerImpl implements IServiceCustomer {

	@Autowired
	private CustomerDao customer;

	/**
	 * Cette méthode permet d'ajouter un nouveau client en base de données. Elle
	 * fait appel à l'interface CustomerDao.
	 *
	 */
	@Override
	public void persist(Customer c) throws Exception {
		customer.persist(c);

	}

	/**
	 * Cette méthode permet de mettre à jour un client en base de données. Elle fait
	 * appel à l'interface CustomerDao.
	 *
	 */
	@Override
	public void merge(Customer c) throws Exception {
		customer.merge(c);

	}

	/**
	 * Cette méthode permet de supprimer un client en base de données. Elle fait
	 * appel à l'interface CustomerDao.
	 *
	 */
	@Override
	public void remove(Long id) throws Exception {
		customer.remove(id);

	}

	/**
	 * Cette méthode permet de trouver un client en base de données via son id, et
	 * de le transmettre à la méthode qui l'appelle. Elle s'appuie sur l'interface
	 * CustomerDao.
	 *
	 */
	@Override
	public Customer findById(Long id) throws Exception {

		return customer.findById(id);
	}

	/**
	 * Cette méthode permet de transmettre la liste de tous les clients existants en
	 * base. Elle s'appuie sur l'interface CustomerDao.
	 *
	 */
	@Override
	public List<Customer> findAll() throws Exception {

		return customer.findAll();
	}

	/**
	 * Cette méthode permet de trouver des clients en base de données en fonction de
	 * la valeur d'un de leurs attributs, et de les retourner à la méthode qui
	 * l'appelle. Elle s'appuie sur l'interface CustomerDao.
	 *
	 */
	@Override
	public List<Customer> findByProperty(String prop, Object val) throws Exception {

		return customer.findByProperty(prop, val);
	}

}
