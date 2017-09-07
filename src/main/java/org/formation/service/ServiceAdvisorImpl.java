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
	 * Cette méthode permet d'ajouter un nouveau conseiller en base de données. Elle
	 * fait appel à l'interface AdvisorDao.
	 *
	 */
	@Override
	public void persist(Advisor a) throws Exception {
		advisor.persist(a);

	}

	/**
	 * Cette méthode permet de mettre à jour un conseiller en base de données. Elle
	 * fait appel à l'interface AdvisorDao.
	 *
	 */
	@Override
	public void merge(Advisor a) throws Exception {
		advisor.merge(a);

	}

	/**
	 * Cette méthode permet de supprimer un conseiller en base de données. Elle fait
	 * appel à l'interface AdvisorDao.
	 *
	 */
	@Override
	public void remove(Long id) throws Exception {
		advisor.remove(id);

	}

	/**
	 * Cette méthode permet de trouver un conseiller en base de données via son id,
	 * et de le transmettre à la méthode qui l'appelle. Elle s'appuie sur
	 * l'interface AdvisorDao.
	 *
	 */
	@Override
	public Advisor findById(Long id) throws Exception {

		return advisor.findById(id);
	}

	/**
	 * Cette méthode permet de transmettre la liste de tous les conseillers
	 * existants en base. Elle s'appuie sur l'interface AdvisorDao.
	 *
	 */
	@Override
	public List<Advisor> findAll() throws Exception {

		return advisor.findAll();
	}

}
