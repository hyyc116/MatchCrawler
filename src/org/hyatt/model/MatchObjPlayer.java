package org.hyatt.model;
// Generated 2017-8-10 18:56:01 by Hibernate Tools 5.2.3.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * MatchObjPlayer generated by hbm2java
 */
@Entity
@Table(name = "MatchObj_Player")
public class MatchObjPlayer implements java.io.Serializable {

	private MatchObjPlayerId id;

	public MatchObjPlayer() {
	}

	public MatchObjPlayer(MatchObjPlayerId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "matchesId", column = @Column(name = "matches_id", nullable = false)),
			@AttributeOverride(name = "playersId", column = @Column(name = "players_id", nullable = false)) })
	public MatchObjPlayerId getId() {
		return this.id;
	}

	public void setId(MatchObjPlayerId id) {
		this.id = id;
	}

}