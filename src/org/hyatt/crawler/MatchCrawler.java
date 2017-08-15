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
	public static int db_index;
	private final static Logger LOGGER = Logger.getLogger(MatchCrawler.class.getName());
	static {
		LOGGER.info("Initialize session factory!");
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		db_index = 0;
	}

	public static void setSession() {
		session = sessionFactory.openSession();
		tx = session.beginTransaction();
	}
	
	public static void submit(){
		session.flush();
		session.clear();
		tx.commit(); 
	}
	
	public static void closeSession() {
		submit();
		LOGGER.info("Session closing! index:"+db_index);
		session.close();
	}
	

	public static void closeFactory() {
		sessionFactory.close();
	}

	public static long get_matches_by_seq(long start_seq) {
		long last_seq = -1;
		List<models.MatchObj> matches = DOTA2_API.get_match_history_by_seq_num(start_seq, 100);
		for (int mi=1;mi<matches.size();mi++ ) {
			models.MatchObj match = matches.get(mi);
			MatchObj obj = new MatchObj(match);
			session.saveOrUpdate(obj);
//			System.out.println("matchid:"+obj.getMatchId());
//			LOGGER.info("Match ID:"+obj.getMatchId());
			for (int i = 0; i < match.getPlayers().size(); i++) {
				models.Player player = match.getPlayers().get(i);
				Long matchid = match.getMatch_id();
				Long id = Long.parseLong(matchid+""+System.currentTimeMillis()+""+i);
//				System.out.println("match id:"+matchid+"\t"+id);
				Player pobj = new Player(player, matchid, id);
				session.save(pobj);
			}
			db_index+=1;
			last_seq = obj.getMatchSeqNum();
		}
		return last_seq;
	}
	
	public static void fecth_matches(){
		long start_seq=-1;
		int i=0;
		while(i<12){
			if(db_index%10==0){
				if(db_index>0){
					closeSession();
				}
			setSession();
			}
			start_seq = get_matches_by_seq(start_seq);
			i+=1;
		}
		closeSession();
		closeFactory();
	}

	public static void main(String[] args) {
//		get_matches_by_seq();
		fecth_matches();
	}

}
