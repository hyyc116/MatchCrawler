package org.hyatt.crawler;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hyatt.model.Hero;
import org.hyatt.model.HeroHome;

public class MatchCrawler {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		// HeroHome hh = new HeroHome();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for (int i = 0; i < 10; i++) {
			Hero hero = new Hero();
			hero.setId(121323459+i);
			hero.setLocalized_name("自然之灵");
			hero.setName("TH");
			// hh.persist(hero);
			session.save(hero);
		}

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}

}
