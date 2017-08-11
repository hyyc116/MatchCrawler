package org.hyatt.model;
// Generated 2017-8-10 18:56:03 by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class League.
 * @see org.hyatt.model.League
 * @author Hibernate Tools
 */
@Stateless
public class LeagueHome {

	private static final Log log = LogFactory.getLog(LeagueHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(League transientInstance) {
		log.debug("persisting League instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(League persistentInstance) {
		log.debug("removing League instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public League merge(League detachedInstance) {
		log.debug("merging League instance");
		try {
			League result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public League findById(long id) {
		log.debug("getting League instance with id: " + id);
		try {
			League instance = entityManager.find(League.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
