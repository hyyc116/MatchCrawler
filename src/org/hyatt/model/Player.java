package org.hyatt.model;
// Generated 2017-8-15 9:38:11 by Hibernate Tools 5.2.3.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Player generated by hbm2java
 */
@Entity
@Table(name = "Player")
public class Player implements java.io.Serializable {

	private long id;
	private long accountId;
	private int assists;
	private int backpack0;
	private int backpack1;
	private int backpack2;
	private int deaths;
	private int denies;
	private int gold;
	private int goldPerMin;
	private int goldSpent;
	private int heroDamage;
	private int heroHealing;
	private Long heroId;
	private int item0;
	private int item1;
	private int item2;
	private int item3;
	private int item4;
	private int item5;
	private int kills;
	private int lastHits;
	private int leaverStatus;
	private int level;
	private int playerSlot;
	private int scaledHeroDamage;
	private int scaledHeroHealing;
	private int scaledTowerDamage;
	private int towerDamage;
	private int xpPerMin;
	private Long matchId;
//	private MatchObj match;

	public Player() {
	}

	public Player(models.Player player,long matchid,Long id) {
		this.accountId = player.getAccount_id();
		this.assists = player.getAssists();
		this.backpack0 = player.getBackpack_0();
		this.backpack1 = player.getBackpack_1();
		this.backpack2 = player.getBackpack_2();
		this.deaths = player.getDeaths();
		this.denies = player.getDenies();
		this.gold = player.getGold();
		this.goldPerMin = player.getGold_per_min();
		this.goldSpent = player.getGold_spent();
		this.heroDamage = player.getHero_damage();
		this.heroHealing = player.getHero_healing();
		this.heroId = player.getHero_id();
		this.item0 = player.getItem_0();
		this.item1 = player.getItem_1();
		this.item2 = player.getItem_2();
		this.item3 = player.getItem_3();
		this.item4 = player.getItem_4();
		this.item5 = player.getItem_5();
		this.kills = player.getKills();
		this.lastHits = player.getLast_hits();
		this.leaverStatus = player.getLeaver_status();
		this.level = player.getLevel();
		this.playerSlot = player.getPlayer_slot();
		this.scaledHeroDamage = player.getScaled_hero_damage();
		this.scaledHeroHealing = player.getScaled_hero_healing();
		this.scaledTowerDamage = player.getScaled_tower_damage();
		this.towerDamage = player.getTower_damage();
		this.xpPerMin = player.getXp_per_min();
		this.matchId  = matchid;
		this.id = id;
	}

	public Player(long id, long accountId, int assists, int backpack0, int backpack1, int backpack2, int deaths,
			int denies, int gold, int goldPerMin, int goldSpent, int heroDamage, int heroHealing, int item0, int item1,
			int item2, int item3, int item4, int item5, int kills, int lastHits, int leaverStatus, int level,
			int playerSlot, int scaledHeroDamage, int scaledHeroHealing, int scaledTowerDamage, int towerDamage,
			int xpPerMin) {
		this.id = id;
		this.accountId = accountId;
		this.assists = assists;
		this.backpack0 = backpack0;
		this.backpack1 = backpack1;
		this.backpack2 = backpack2;
		this.deaths = deaths;
		this.denies = denies;
		this.gold = gold;
		this.goldPerMin = goldPerMin;
		this.goldSpent = goldSpent;
		this.heroDamage = heroDamage;
		this.heroHealing = heroHealing;
		this.item0 = item0;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
		this.kills = kills;
		this.lastHits = lastHits;
		this.leaverStatus = leaverStatus;
		this.level = level;
		this.playerSlot = playerSlot;
		this.scaledHeroDamage = scaledHeroDamage;
		this.scaledHeroHealing = scaledHeroHealing;
		this.scaledTowerDamage = scaledTowerDamage;
		this.towerDamage = towerDamage;
		this.xpPerMin = xpPerMin;
	}

	public Player(long id, long accountId, int assists, int backpack0, int backpack1, int backpack2, int deaths,
			int denies, int gold, int goldPerMin, int goldSpent, int heroDamage, int heroHealing, Long heroId,
			int item0, int item1, int item2, int item3, int item4, int item5, int kills, int lastHits, int leaverStatus,
			int level, int playerSlot, int scaledHeroDamage, int scaledHeroHealing, int scaledTowerDamage,
			int towerDamage, int xpPerMin) {
		this.id = id;
		this.accountId = accountId;
		this.assists = assists;
		this.backpack0 = backpack0;
		this.backpack1 = backpack1;
		this.backpack2 = backpack2;
		this.deaths = deaths;
		this.denies = denies;
		this.gold = gold;
		this.goldPerMin = goldPerMin;
		this.goldSpent = goldSpent;
		this.heroDamage = heroDamage;
		this.heroHealing = heroHealing;
		this.heroId = heroId;
		this.item0 = item0;
		this.item1 = item1;
		this.item2 = item2;
		this.item3 = item3;
		this.item4 = item4;
		this.item5 = item5;
		this.kills = kills;
		this.lastHits = lastHits;
		this.leaverStatus = leaverStatus;
		this.level = level;
		this.playerSlot = playerSlot;
		this.scaledHeroDamage = scaledHeroDamage;
		this.scaledHeroHealing = scaledHeroHealing;
		this.scaledTowerDamage = scaledTowerDamage;
		this.towerDamage = towerDamage;
		this.xpPerMin = xpPerMin;
		// this.matchId = matchId;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "account_id", nullable = false)
	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Column(name = "assists", nullable = false)
	public int getAssists() {
		return this.assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	@Column(name = "backpack_0", nullable = false)
	public int getBackpack0() {
		return this.backpack0;
	}

	public void setBackpack0(int backpack0) {
		this.backpack0 = backpack0;
	}

	@Column(name = "backpack_1", nullable = false)
	public int getBackpack1() {
		return this.backpack1;
	}

	public void setBackpack1(int backpack1) {
		this.backpack1 = backpack1;
	}

	@Column(name = "backpack_2", nullable = false)
	public int getBackpack2() {
		return this.backpack2;
	}

	public void setBackpack2(int backpack2) {
		this.backpack2 = backpack2;
	}

	@Column(name = "deaths", nullable = false)
	public int getDeaths() {
		return this.deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	@Column(name = "denies", nullable = false)
	public int getDenies() {
		return this.denies;
	}

	public void setDenies(int denies) {
		this.denies = denies;
	}

	@Column(name = "gold", nullable = false)
	public int getGold() {
		return this.gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	@Column(name = "gold_per_min", nullable = false)
	public int getGoldPerMin() {
		return this.goldPerMin;
	}

	public void setGoldPerMin(int goldPerMin) {
		this.goldPerMin = goldPerMin;
	}

	@Column(name = "gold_spent", nullable = false)
	public int getGoldSpent() {
		return this.goldSpent;
	}

	public void setGoldSpent(int goldSpent) {
		this.goldSpent = goldSpent;
	}

	@Column(name = "hero_damage", nullable = false)
	public int getHeroDamage() {
		return this.heroDamage;
	}

	public void setHeroDamage(int heroDamage) {
		this.heroDamage = heroDamage;
	}

	@Column(name = "hero_healing", nullable = false)
	public int getHeroHealing() {
		return this.heroHealing;
	}

	public void setHeroHealing(int heroHealing) {
		this.heroHealing = heroHealing;
	}

	@Column(name = "hero_id")
	public Long getHeroId() {
		return this.heroId;
	}

	public void setHeroId(Long heroId) {
		this.heroId = heroId;
	}

	@Column(name = "item_0", nullable = false)
	public int getItem0() {
		return this.item0;
	}

	public void setItem0(int item0) {
		this.item0 = item0;
	}

	@Column(name = "item_1", nullable = false)
	public int getItem1() {
		return this.item1;
	}

	public void setItem1(int item1) {
		this.item1 = item1;
	}

	@Column(name = "item_2", nullable = false)
	public int getItem2() {
		return this.item2;
	}

	public void setItem2(int item2) {
		this.item2 = item2;
	}

	@Column(name = "item_3", nullable = false)
	public int getItem3() {
		return this.item3;
	}

	public void setItem3(int item3) {
		this.item3 = item3;
	}

	@Column(name = "item_4", nullable = false)
	public int getItem4() {
		return this.item4;
	}

	public void setItem4(int item4) {
		this.item4 = item4;
	}

	@Column(name = "item_5", nullable = false)
	public int getItem5() {
		return this.item5;
	}

	public void setItem5(int item5) {
		this.item5 = item5;
	}

	@Column(name = "kills", nullable = false)
	public int getKills() {
		return this.kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	@Column(name = "last_hits", nullable = false)
	public int getLastHits() {
		return this.lastHits;
	}

	public void setLastHits(int lastHits) {
		this.lastHits = lastHits;
	}

	@Column(name = "leaver_status", nullable = false)
	public int getLeaverStatus() {
		return this.leaverStatus;
	}

	public void setLeaverStatus(int leaverStatus) {
		this.leaverStatus = leaverStatus;
	}

	@Column(name = "level", nullable = false)
	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Column(name = "player_slot", nullable = false)
	public int getPlayerSlot() {
		return this.playerSlot;
	}

	public void setPlayerSlot(int playerSlot) {
		this.playerSlot = playerSlot;
	}

	@Column(name = "scaled_hero_damage", nullable = false)
	public int getScaledHeroDamage() {
		return this.scaledHeroDamage;
	}

	public void setScaledHeroDamage(int scaledHeroDamage) {
		this.scaledHeroDamage = scaledHeroDamage;
	}

	@Column(name = "scaled_hero_healing", nullable = false)
	public int getScaledHeroHealing() {
		return this.scaledHeroHealing;
	}

	public void setScaledHeroHealing(int scaledHeroHealing) {
		this.scaledHeroHealing = scaledHeroHealing;
	}

	@Column(name = "scaled_tower_damage", nullable = false)
	public int getScaledTowerDamage() {
		return this.scaledTowerDamage;
	}

	public void setScaledTowerDamage(int scaledTowerDamage) {
		this.scaledTowerDamage = scaledTowerDamage;
	}

	@Column(name = "tower_damage", nullable = false)
	public int getTowerDamage() {
		return this.towerDamage;
	}

	public void setTowerDamage(int towerDamage) {
		this.towerDamage = towerDamage;
	}

	@Column(name = "xp_per_min", nullable = false)
	public int getXpPerMin() {
		return this.xpPerMin;
	}

	public void setXpPerMin(int xpPerMin) {
		this.xpPerMin = xpPerMin;
	}

	@Column(name = "match_id")
	public Long getMatchId() {
		return matchId;
	}

	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	
//	@ManyToOne
//	@JoinColumn(name = "match_id")
//	public MatchObj getMatch() {
//		return match;
//	}
//
//	public void setMatch(MatchObj match) {
//		this.match = match;
//	}
	
	

	
	

}
