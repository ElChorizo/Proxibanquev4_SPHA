package org.formation.service;

import java.util.List;

import org.formation.dao.AdvisorDao;
import org.formation.model.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AL, SRL, PHL
 *
 */
@Service("serviceAdvisor")
public class ServiceAdvisorImpl implements IServiceAdvisor {

	@Autowired
	private AdvisorDao advisor;

	/**
	 * Cette m�thode permet d'ajouter un nouveau conseiller en base de donn�es. Elle
	 * fait appel � l'interface AdvisorDao.
	 *
	 */
	@Override
	public void persist(Advisor a) throws Exception {
		advisor.persist(a);

	}

	/**
	 * Cette m�thode permet de mettre � jour un conseiller en base de donn�es. Elle
	 * fait appel � l'interface AdvisorDao.
	 *
	 */
	@Override
	public void merge(Advisor a) throws Exception {
		advisor.merge(a);

	}

	/**
	 * Cette m�thode permet de supprimer un conseiller en base de donn�es. Elle fait
	 * appel � l'interface AdvisorDao.
	 *
	 */
	@Override
	public void remove(Long id) throws Exception {
		advisor.remove(id);

	}

	/**
	 * Cette m�thode permet de trouver un conseiller en base de donn�es via son id,
	 * et de le transmettre � la m�thode qui l'appelle. Elle s'appuie sur
	 * l'interface AdvisorDao.
	 *
	 */
	@Override
	public Advisor findById(Long id) throws Exception {

		return advisor.findById(id);
	}

	/**
	 * Cette m�thode permet de transmettre la liste de tous les conseillers
	 * existants en base. Elle s'appuie sur l'interface AdvisorDao.
	 *
	 */
	@Override
	public List<Advisor> findAll() throws Exception {

		return advisor.findAll();
	}

}
