package org.formation.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javassist.bytecode.SignatureAttribute.TypeVariable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formation.dao.EntityDao;
import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author AL, SRL, PHL Cette classe assure la liaison entre l'application et sa
 *         base de données. Les classes AccountImpl, AdvisorDaoImpl, et
 *         CustomerDaoImpl héritent de cette classe.
 */
public class EntityDaoImpl<E> implements EntityDao<E> {

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	protected E instance;
	private Class<E> entityClass;

	/**
	 * Cette méthode permet d'ajouter un nouvel élément en base de données.
	 */
	@Transactional
	public void persist(E e) throws HibernateException {
		getEntityManager().persist(e);
	}

	/**
	 * Cette méthode permet de mettre à jour un élément existant en base de données.
	 */
	@Transactional
	public void merge(E e) throws HibernateException {
		getEntityManager().merge(e);
	}

	/**
	 * Cette méthode permet de supprimer un élément existant en base de données.
	 */
	@Transactional
	public void remove(Object id) throws Exception {
		getEntityManager().remove((E) getEntityManager().find(getEntityClass(), id));
	}

	/**
	 * Cette méthode permet de trouver en base de données et de transmettre un
	 * élément en fonction de son id.
	 */
	@Transactional(readOnly = true)
	public E findById(Object id) throws Exception {
		return (E) getEntityManager().find(getEntityClass(), id);
	}

	/**
	 * Cette méthode permet de trouver en base de données et de transmettre tous les
	 * éléments d'une table.
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<E> findAll() throws Exception {
		return getEntityManager().createQuery("Select t from " + getEntityClass().getSimpleName() + " t")
				.getResultList();
	}

	/**
	 * Cette méthode permet de trouver des éléments en base de données en fonction
	 * de la valeur d'un de leurs attributs, et de les retourner à la méthode qui
	 * l'appelle.
	 *
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<E> findByProperty(String prop, Object val) throws Exception {
		return (List<E>) getEntityManager()
				.createQuery("select x from " + getEntityClass().getSimpleName() + " x where x." + prop + " = ?1")
				.setParameter(1, val).getResultList();
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<E> findInRange(int firstResult, int maxResults) throws Exception {
		return getEntityManager().createQuery("Select t from " + getEntityClass().getSimpleName() + " t")
				.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	}

	@Transactional(readOnly = true)
	public long count() throws Exception {
		return (Long) getEntityManager().createQuery("Select count(t) from " + getEntityClass().getSimpleName() + " t")
				.getSingleResult();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) throws Exception {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	public Class<E> getEntityClass() throws Exception {
		if (entityClass == null) {
			Type type = getClass().getGenericSuperclass();
			if (type instanceof ParameterizedType) {
				ParameterizedType paramType = (ParameterizedType) type;
				if (paramType.getActualTypeArguments().length == 2) {
					if (paramType.getActualTypeArguments()[1] instanceof TypeVariable) {
						throw new IllegalArgumentException("Can't find class using reflection");
					} else {
						entityClass = (Class<E>) paramType.getActualTypeArguments()[1];
					}
				} else {
					entityClass = (Class<E>) paramType.getActualTypeArguments()[0];
				}
			} else {
				throw new Exception("Can't find class using reflection");
			}
		}
		return entityClass;
	}

}
