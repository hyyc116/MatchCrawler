package org.hyatt.crawler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.client.ClientProtocolException;
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

	// statistic data
	public static Map<Integer, Integer> mode_dict = new HashMap<Integer, Integer>();
	public static Map<Integer, Integer> cluster_dict = new HashMap<Integer, Integer>();
	// total count
	public static Date start_time;
	public static Date end_time;

	static {
		LOGGER.info("-------Match Crawler, Version 3.0.------- \n" + " ====  Initialize session factory! ====");
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		// 程序启动是读取数据库中的统计数据
		readStats();
		LOGGER.info("=== " + db_index + " matches have been crawled;" + " starting seq num:" + start_seq + " ====");
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
		session.close();
	}

	public static void closeFactory() {
		sessionFactory.close();
	}

	public static long get_matches_by_seq(long start_seq) {
		long last_seq = -1;
		List<models.MatchObj> matches = null;
		try {
			matches = DOTA2_API.get_match_history_by_seq_num(start_seq, 100);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			// 如果是因为503的错误，那么需要将程序休息5分钟，然后继续执行
			try {
				LOGGER.info("Encounter 503 response, thread rest 5 minites.");
				Thread.sleep(1000 * 60 * 5);
				LOGGER.info("Re-try, and beginning from seq:" + start_seq);
				return start_seq;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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

			/*** 更新stats中的指标 ***/
			// 对于第一条记录，记录其开始时间
			if (mi == 1 && start_time == null) {
				start_time = Utility.get_date(match.getStart_time());
			}
			// 最后一场抓取比赛开始时间
			end_time = Utility.get_date(match.getStart_time());
			// 更新比赛数量
			db_index += 1;
			// 更新两个词典
			int key = match.getGame_mode();
			int mode_count = mode_dict.getOrDefault(key, 0) + 1;
			mode_dict.put(key, mode_count);

			int ckey = match.getCluster();
			int cluster_count = cluster_dict.getOrDefault(ckey, 0) + 1;
			cluster_dict.put(ckey, cluster_count);

			/**** 每1000场比赛，进行一次数据库插入操作，并将统计信息插入数据库 ***/
			// 每1000轮对数据库进行一次提交
			if (db_index % 1000 == 0) {
				// 提交1000次的数据
				closeSession();
				// 对数据库进行统计
				saveStats();
			}

			last_seq = obj.getMatchSeqNum();
		}
		return last_seq;
	}

	public static void readStats() {
		setSession();
		List<MatchObj> objs = session.createQuery("from MatchObj order by matchSeqNum").list();
		if (objs == null || objs.size() == 0) {
			db_index = 0;
			start_time = null;
			end_time = null;
			mode_dict = new HashMap<Integer, Integer>();
			cluster_dict = new HashMap<Integer, Integer>();
			start_seq = -1;
			closeSession();
			return;
		}
		// 已存在记录
		db_index = objs.size();
		start_seq = objs.get((int) db_index - 1).getMatchSeqNum();
		start_time = Utility.get_date(objs.get(0).getStartTime());
		end_time = Utility.get_date(objs.get((int) (db_index - 1)).getStartTime());
		for (MatchObj m : objs) {
			int key = m.getGameMode();
			int mode_count = mode_dict.getOrDefault(key, 0) + 1;
			mode_dict.put(key, mode_count);

			int ckey = m.getCluster();
			int cluster_count = cluster_dict.getOrDefault(ckey, 0) + 1;
			cluster_dict.put(ckey, cluster_count);
		}
		closeSession();
	}

	public static void saveStats() {
		setSession();
		// 插入后获得比赛记录数量
		Date now = new Date();
		Stats stats = new Stats();
		stats.setLastTime(end_time);
		stats.setStartTime(start_time);
		stats.setTotalcount(db_index);
		stats.setUpdatetime(now);
		stats.setId((long) 10086);
		// 两个字典
		String mode_str = Utility.mapToStr(mode_dict);
		stats.setGmDict(mode_str.toString());
		String cluster_str = Utility.mapToStr(cluster_dict);
		stats.setClusterDict(cluster_str);

		// 保存对象，每个对象的ID 都是10086
		session.saveOrUpdate(stats);
		LOGGER.info(stats.toString());
//		closeSession();
	}

	public static void fecth_matches() {
		setSession();
		while (true) {
			// 根据match_seq_num抓取历史记录
			start_seq = get_matches_by_seq(start_seq);
			// 休息5秒钟
			try {
				LOGGER.info("Matches crawled:" + db_index + ", 5 seconds sleep after one request!");
				Thread.sleep(1000 * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 每5000条休息
			if (db_index % 100 == 0) {
				try {
					LOGGER.info("Matches Crawled:" + db_index + ", 5 minutes sleep after 100 request!");
					Thread.sleep(1000 * 60 * 5);
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
