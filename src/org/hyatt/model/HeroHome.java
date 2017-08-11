package org.hyatt.model;
// Generated 2017-8-10 18:56:03 by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Hero.
 * @see org.hyatt.model.Hero
 * @author Hibernate Tools
 */
@Stateless
public class HeroHome {

	private static final Log log = LogFactory.getLog(HeroHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Hero transientInstance) {
		log.debug("persisting Hero instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Hero persistentInstance) {
		log.debug("removing Hero instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Hero merge(Hero detachedInstance) {
		log.debug("merging Hero instance");
		try {
			Hero result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Hero findById(long id) {
		log.debug("getting Hero instance with id: " + id);
		try {
			Hero instance = entityManager.find(Hero.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
