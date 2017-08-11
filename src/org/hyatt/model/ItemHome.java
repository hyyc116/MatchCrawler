package org.hyatt.model;
// Generated 2017-8-10 18:56:03 by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Item.
 * @see org.hyatt.model.Item
 * @author Hibernate Tools
 */
@Stateless
public class ItemHome {

	private static final Log log = LogFactory.getLog(ItemHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Item transientInstance) {
		log.debug("persisting Item instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Item persistentInstance) {
		log.debug("removing Item instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Item merge(Item detachedInstance) {
		log.debug("merging Item instance");
		try {
			Item result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Item findById(long id) {
		log.debug("getting Item instance with id: " + id);
		try {
			Item instance = entityManager.find(Item.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
