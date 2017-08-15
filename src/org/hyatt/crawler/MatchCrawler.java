package org.hyatt.crawler;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hyatt.model.MatchObj;
import org.hyatt.model.Player;

import util.DOTA2_API;

public class MatchCrawler {

	public static SessionFactory sessionFactory;
	public static Session session;
	public static Transaction tx;
	public static long db_index;
	public static long start_seq;
	private final static Logger LOGGER = Logger.getLogger(MatchCrawler.class.getName());
	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
		db_index = (Long) session.createQuery("select count(match_id) from MatchObj").list().get(0);
		if (db_index > 0) {
			start_seq = (Long) session.createQuery("select max(matchSeqNum) from MatchObj").list().get(0);
		} else {
			start_seq = -1;
		}
		LOGGER.info("Initialize session factory! There are " + db_index
				+ " matches existing in database; starting seq num:" + start_seq);
	}

	public static void setSession() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	}

	public static void submit() {
		session.flush();
		session.clear();
		tx.commit();
	}

	public static void closeSession() {
		submit();
		LOGGER.info("Session closing! index:" + db_index);
		session.close();
	}

	public static void closeFactory() {
		sessionFactory.close();
	}

	public static long get_matches_by_seq(long start_seq) {
		long last_seq = -1;
		List<models.MatchObj> matches = DOTA2_API.get_match_history_by_seq_num(start_seq, 100);
		for (int mi = 1; mi < matches.size(); mi++) {
			models.MatchObj match = matches.get(mi);
			if (match.getHuman_players() != 10)
				continue;
			MatchObj obj = new MatchObj(match);
			session.saveOrUpdate(obj);
			// System.out.println("matchid:"+obj.getMatchId());
			// LOGGER.info("Match ID:"+obj.getMatchId());
			for (int i = 0; i < match.getPlayers().size(); i++) {
				models.Player player = match.getPlayers().get(i);
				Long matchid = match.getMatch_id();
				Long id = Long.parseLong(System.currentTimeMillis() +""+ (mi + 1)+""+(i+1));
//				System.out.println("match id:" + matchid + "\t" + id);
				Player pobj = new Player(player, matchid, id);
				session.save(pobj);
			}
			db_index += 1;
			last_seq = obj.getMatchSeqNum();
		}
		return last_seq;
	}

	public static void fecth_matches() {
		while (true) {
			// 每5轮对数据库进行一次提交
			if (db_index % 5 == 0) {
				closeSession();
				setSession();
			}
			// 根据match_seq_num抓取历史记录
			start_seq = get_matches_by_seq(start_seq);
			// 休息5秒钟
			try {
				LOGGER.info("Matches crawled:" + db_index + ", 10 seconds sleep after one request!");
				Thread.sleep(1000*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 每5000条休息
			if (db_index % 100 == 0) {
				try {
					LOGGER.info("Matches Crawled:" + db_index + ", 10 minutes sleep after 100 request!");
					Thread.sleep(1000 * 60 * 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		// get_matches_by_seq();
		fecth_matches();
	}

}
