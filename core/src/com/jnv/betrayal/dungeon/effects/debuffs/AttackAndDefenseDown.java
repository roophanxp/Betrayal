package com.jnv.betrayal.dungeon.effects.debuffs;


import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.effects.Effect;
import com.jnv.betrayal.dungeon.effects.EventType;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/*
 * Copyright (c) 2016. JNV Games.
 * Co-authors: Vincent Wang, Joseph Phan
 */

public class AttackAndDefenseDown extends Effect {

	private static final EventType startEventType = EventType.DEBUFF_ATTACK_DEFENSE;
	private static final EventType endEventType = EventType.E_DEBUFF_ATTACK_DEFENSE;
	private int attack;
	private int defense;

	public AttackAndDefenseDown(int attack, int defense, int turns) {
		super(startEventType, turns, endEventType);
		this.attack = attack;
		this.defense = defense;
		isHostile = true;
		description = "Attack and Defense Debuff\nLowers your attack by " + attack + "\n"
				+ "and defense by " + defense + "\n" + "for " + turns + " turns.";
	}

	// JSON Constructor
	public AttackAndDefenseDown(JSONObject values, int turns, Card src, List<Card> dest) {
		super(startEventType, turns, endEventType);
		try {
			this.attack = values.getInt("attack");
			this.defense = values.getInt("defense");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = true;
		description = "Attack and Defense Debuff\nLowers your attack by " + attack + "\n"
				+ "and defense by " + defense + "\n" + "for " + turns + " turns.";
		init(src, dest);

	}

	@Override
	public void startEffect(Card destCard) {
		destCard.decreaseCurrentAttack(attack);
		destCard.decreaseCurrentDefense(defense);
	}

	@Override
	public void endEffect(Card destCard) {
		destCard.increaseCurrentAttack(attack);
		destCard.increaseCurrentDefense(defense);
	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject(JSONObject values) throws JSONException {
		values.put("attack", attack);
		values.put("defense", defense);
	}
}
