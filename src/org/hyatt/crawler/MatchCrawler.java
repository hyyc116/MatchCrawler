package org.hyatt.crawler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hyatt.model.MatchObj;
import org.hyatt.model.Player;
import org.hyatt.model.Stats;

import util.DOTA2_API;
import util.Utility;

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
		LOGGER.info("Match Crawler, Version 2.0. \n === Initialize session factory! There are " + db_index
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
				Long id = Long.parseLong(System.currentTimeMillis() + "" + (mi + 1) + "" + (i + 1));
				// System.out.println("match id:" + matchid + "\t" + id);
				Player pobj = new Player(player, matchid, id);
				session.save(pobj);
			}

			// 每1000轮对数据库进行一次提交
			if (db_index % 1 == 0) {
				//提交1000次的数据
				closeSession();
				//对数据库进行统计
				saveStats();
				//重新打开session
				setSession();
			}
			db_index += 1;
			last_seq = obj.getMatchSeqNum();
		}
		return last_seq;
	}
	
	public static void saveStats(){
		setSession();
		//插入后获得比赛记录数量
		List<MatchObj> objs = session.createQuery("from MatchObj order by matchSeqNum").list();
		long total_count = objs.size();
		Date start_time = Utility.get_date(objs.get(0).getStartTime());
		Date end_time = Utility.get_date(objs.get((int) (total_count-1)).getStartTime());
		Date now = new Date();
		Stats stats = new Stats();
		stats.setLastTime(end_time);
		stats.setStartTime(start_time);
		stats.setTotalcount(total_count);
		stats.setUpdatetime(now);
		stats.setId(10086);
		// 数据统计结果
		Map<Integer, Integer> mode_dict = new HashMap<Integer,Integer>();
		Map<Integer, Integer> cluster_dict = new HashMap<Integer,Integer>();
		for(MatchObj m:objs){
			int key = m.getGameMode();
			int mode_count = mode_dict.getOrDefault(key, 0)+1;
			mode_dict.put(key, mode_count);
			
			int ckey = m.getCluster();
			int cluster_count = cluster_dict.getOrDefault(ckey, 0)+1;
			cluster_dict.put(ckey, cluster_count);
		}
		
		String mode_str = Utility.mapToStr(mode_dict);
		stats.setGmDict(mode_str.toString());
		
		String cluster_str = Utility.mapToStr(cluster_dict);
		stats.setClusterDict(cluster_str);
		// 保存对象，每个对象的ID 都是10086
		session.saveOrUpdate(stats);
		LOGGER.info("Generate Stats:"+stats.toString());
		closeSession();
	}

	public static void fecth_matches() {
		while (true) {
			// 根据match_seq_num抓取历史记录
			start_seq = get_matches_by_seq(start_seq);
			// 休息5秒钟
			try {
				LOGGER.info("Matches crawled:" + db_index + ", 10 seconds sleep after one request!");
				Thread.sleep(1000 * 10);
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
