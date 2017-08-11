package org.hyatt.model;
// Generated 2017-8-10 18:56:03 by Hibernate Tools 5.2.3.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class MatchObj.
 * @see org.hyatt.model.MatchObj
 * @author Hibernate Tools
 */
@Stateless
public class MatchObjHome {

	private static final Log log = LogFactory.getLog(MatchObjHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(MatchObj transientInstance) {
		log.debug("persisting MatchObj instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(MatchObj persistentInstance) {
		log.debug("removing MatchObj instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public MatchObj merge(MatchObj detachedInstance) {
		log.debug("merging MatchObj instance");
		try {
			MatchObj result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public MatchObj findById(long id) {
		log.debug("getting MatchObj instance with id: " + id);
		try {
			MatchObj instance = entityManager.find(MatchObj.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
