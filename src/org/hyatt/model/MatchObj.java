package org.hyatt.model;
// Generated 2017-8-15 9:38:11 by Hibernate Tools 5.2.3.Final

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MatchObj generated by hbm2java
 */
@Entity
@Table(name = "MatchObj")
public class MatchObj implements java.io.Serializable {

	private long matchId;
	private int barracksStatusDire;
	private int barracksStatusRadiant;
	private int cluster;
	private int direScore;
	private int direTeamId;
	private long duration;
	private int engine;
	private long firstBloodTime;
	private int flags;
	private int gameMode;
	private int humanPlayers;
	private int leagueid;
	private int lobbyType;
	private long matchSeqNum;
	private int negativeVotes;
	private int positiveVotes;
	private int radiantScore;
	private int radiantTeamId;
	private boolean radiantWin;
	private int seriesId;
	private int seriesType;
	private long startTime;
	private int towerStatusDire;
	private int towerStatusRadiant;
//	private List<Player> players = new ArrayList<Player>();

	public MatchObj() {
	}
	
	public MatchObj(models.MatchObj match) {
		this.barracksStatusDire = match.getBarracks_status_dire();
		this.barracksStatusRadiant = match.getBarracks_status_radiant();
		this.cluster = match.getCluster();
		this.direScore = match.getDire_score();
		this.direTeamId = match.getDire_team_id();
		this.duration = match.getDuration();
		this.engine = match.getEngine();
		this.firstBloodTime = match.getFirst_blood_time();
		this.flags = match.getFlags();
		this.gameMode = match.getGame_mode();
		this.humanPlayers = match.getHuman_players();
		this.leagueid = match.getLeagueid();
		this.lobbyType = match.getLobby_type();
		this.matchId = match.getMatch_id();
		this.matchSeqNum = match.getMatch_seq_num();
		this.negativeVotes = match.getNegative_votes();
		this.positiveVotes = match.getPositive_votes();
		this.radiantScore = match.getRadiant_score();
		this.radiantTeamId = match.getRadiant_team_id();
		this.radiantWin = match.isRadiant_win();
		this.seriesId = match.getSeries_id();
		this.seriesType = match.getSeries_type();
		this.startTime = match.getStart_time();
		this.towerStatusDire = match.getTower_status_dire();
		this.towerStatusRadiant = match.getTower_status_radiant();
	}
	

	public MatchObj(long matchId, int barracksStatusDire, int barracksStatusRadiant, int cluster, int direScore,
			int direTeamId, long duration, int engine, long firstBloodTime, int flags, int gameMode, int humanPlayers,
			int leagueid, int lobbyType, long matchSeqNum, int negativeVotes, int positiveVotes, int radiantScore,
			int radiantTeamId, boolean radiantWin, int seriesId, int seriesType, long startTime, int towerStatusDire,
			int towerStatusRadiant) {
		this.matchId = matchId;
		this.barracksStatusDire = barracksStatusDire;
		this.barracksStatusRadiant = barracksStatusRadiant;
		this.cluster = cluster;
		this.direScore = direScore;
		this.direTeamId = direTeamId;
		this.duration = duration;
		this.engine = engine;
		this.firstBloodTime = firstBloodTime;
		this.flags = flags;
		this.gameMode = gameMode;
		this.humanPlayers = humanPlayers;
		this.leagueid = leagueid;
		this.lobbyType = lobbyType;
		this.matchSeqNum = matchSeqNum;
		this.negativeVotes = negativeVotes;
		this.positiveVotes = positiveVotes;
		this.radiantScore = radiantScore;
		this.radiantTeamId = radiantTeamId;
		this.radiantWin = radiantWin;
		this.seriesId = seriesId;
		this.seriesType = seriesType;
		this.startTime = startTime;
		this.towerStatusDire = towerStatusDire;
		this.towerStatusRadiant = towerStatusRadiant;
	}

	@Id

	@Column(name = "match_id", unique = true, nullable = false)
	public long getMatchId() {
		return this.matchId;
	}

	public void setMatchId(long matchId) {
		this.matchId = matchId;
	}

	@Column(name = "barracks_status_dire", nullable = false)
	public int getBarracksStatusDire() {
		return this.barracksStatusDire;
	}

	public void setBarracksStatusDire(int barracksStatusDire) {
		this.barracksStatusDire = barracksStatusDire;
	}

	@Column(name = "barracks_status_radiant", nullable = false)
	public int getBarracksStatusRadiant() {
		return this.barracksStatusRadiant;
	}

	public void setBarracksStatusRadiant(int barracksStatusRadiant) {
		this.barracksStatusRadiant = barracksStatusRadiant;
	}

	@Column(name = "cluster", nullable = false)
	public int getCluster() {
		return this.cluster;
	}

	public void setCluster(int cluster) {
		this.cluster = cluster;
	}

	@Column(name = "dire_score", nullable = false)
	public int getDireScore() {
		return this.direScore;
	}

	public void setDireScore(int direScore) {
		this.direScore = direScore;
	}

	@Column(name = "dire_team_id", nullable = false)
	public int getDireTeamId() {
		return this.direTeamId;
	}

	public void setDireTeamId(int direTeamId) {
		this.direTeamId = direTeamId;
	}

	@Column(name = "duration", nullable = false)
	public long getDuration() {
		return this.duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Column(name = "engine", nullable = false)
	public int getEngine() {
		return this.engine;
	}

	public void setEngine(int engine) {
		this.engine = engine;
	}

	@Column(name = "first_blood_time", nullable = false)
	public long getFirstBloodTime() {
		return this.firstBloodTime;
	}

	public void setFirstBloodTime(long firstBloodTime) {
		this.firstBloodTime = firstBloodTime;
	}

	@Column(name = "flags", nullable = false)
	public int getFlags() {
		return this.flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	@Column(name = "game_mode", nullable = false)
	public int getGameMode() {
		return this.gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}

	@Column(name = "human_players", nullable = false)
	public int getHumanPlayers() {
		return this.humanPlayers;
	}

	public void setHumanPlayers(int humanPlayers) {
		this.humanPlayers = humanPlayers;
	}

	@Column(name = "leagueid", nullable = false)
	public int getLeagueid() {
		return this.leagueid;
	}

	public void setLeagueid(int leagueid) {
		this.leagueid = leagueid;
	}

	@Column(name = "lobby_type", nullable = false)
	public int getLobbyType() {
		return this.lobbyType;
	}

	public void setLobbyType(int lobbyType) {
		this.lobbyType = lobbyType;
	}

	@Column(name = "match_seq_num", nullable = false)
	public long getMatchSeqNum() {
		return this.matchSeqNum;
	}

	public void setMatchSeqNum(long matchSeqNum) {
		this.matchSeqNum = matchSeqNum;
	}

	@Column(name = "negative_votes", nullable = false)
	public int getNegativeVotes() {
		return this.negativeVotes;
	}

	public void setNegativeVotes(int negativeVotes) {
		this.negativeVotes = negativeVotes;
	}

	@Column(name = "positive_votes", nullable = false)
	public int getPositiveVotes() {
		return this.positiveVotes;
	}

	public void setPositiveVotes(int positiveVotes) {
		this.positiveVotes = positiveVotes;
	}

	@Column(name = "radiant_score", nullable = false)
	public int getRadiantScore() {
		return this.radiantScore;
	}

	public void setRadiantScore(int radiantScore) {
		this.radiantScore = radiantScore;
	}

	@Column(name = "radiant_team_id", nullable = false)
	public int getRadiantTeamId() {
		return this.radiantTeamId;
	}

	public void setRadiantTeamId(int radiantTeamId) {
		this.radiantTeamId = radiantTeamId;
	}

	@Column(name = "radiant_win", nullable = false)
	public boolean isRadiantWin() {
		return this.radiantWin;
	}

	public void setRadiantWin(boolean radiantWin) {
		this.radiantWin = radiantWin;
	}

	@Column(name = "series_id", nullable = false)
	public int getSeriesId() {
		return this.seriesId;
	}

	public void setSeriesId(int seriesId) {
		this.seriesId = seriesId;
	}

	@Column(name = "series_type", nullable = false)
	public int getSeriesType() {
		return this.seriesType;
	}

	public void setSeriesType(int seriesType) {
		this.seriesType = seriesType;
	}

	@Column(name = "start_time", nullable = false)
	public long getStartTime() {
		return this.startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	@Column(name = "tower_status_dire", nullable = false)
	public int getTowerStatusDire() {
		return this.towerStatusDire;
	}

	public void setTowerStatusDire(int towerStatusDire) {
		this.towerStatusDire = towerStatusDire;
	}

	@Column(name = "tower_status_radiant", nullable = false)
	public int getTowerStatusRadiant() {
		return this.towerStatusRadiant;
	}

	public void setTowerStatusRadiant(int towerStatusRadiant) {
		this.towerStatusRadiant = towerStatusRadiant;
	}

////	@OneToMany(mappedBy="match")
//	public List<Player> getPlayers() {
//		return players;
//	}
//
//	public void setPlayers(List<Player> players) {
//		this.players = players;
//	}
	

}
