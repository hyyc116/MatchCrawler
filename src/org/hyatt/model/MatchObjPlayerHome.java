package org.hyatt.model;
// Generated 2017-8-10 18:56:03 by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class MatchObjPlayer.
 * @see org.hyatt.model.MatchObjPlayer
 * @author Hibernate Tools
 */
@Stateless
public class MatchObjPlayerHome {

	private static final Log log = LogFactory.getLog(MatchObjPlayerHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MatchObjPlayer transientInstance) {
		log.debug("persisting MatchObjPlayer instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MatchObjPlayer persistentInstance) {
		log.debug("removing MatchObjPlayer instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MatchObjPlayer merge(MatchObjPlayer detachedInstance) {
		log.debug("merging MatchObjPlayer instance");
		try {
			MatchObjPlayer result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MatchObjPlayer findById(MatchObjPlayerId id) {
		log.debug("getting MatchObjPlayer instance with id: " + id);
		try {
			MatchObjPlayer instance = entityManager.find(MatchObjPlayer.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
