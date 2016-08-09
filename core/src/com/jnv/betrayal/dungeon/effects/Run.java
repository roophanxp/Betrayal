package com.jnv.betrayal.dungeon.effects;

import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.effects.actions.FailedToFlee;
import com.jnv.betrayal.dungeon.effects.actions.Flee;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Run extends Effect {

	private int fleeChance;

	public Run(int fleeChance) {
		super(EventType.FLEE);
		this.fleeChance = fleeChance;
		isHostile = false;
	}

	// JSON Constructor
	public Run(JSONObject data, int turns, Card src, List<Card> dest) {
		super(EventType.FLEE, turns);
		try {
			this.fleeChance = data.getInt("fleeChance");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isHostile = false;
		init(src, dest);
	}

	@Override
	public void startEffect(Card destCard) {
		if (PlayerCard.canFlee(fleeChance / 25)) {
			Effect flee = new Flee(destCard);
			destCard.getField().roundManager.addEvent(flee, flee.startType);
		}
		else {
			Effect failToFlee = new FailedToFlee(destCard);
			destCard.getField().roundManager.addEvent(failToFlee, failToFlee.startType);
		}
	}

	@Override
	public void endEffect(Card destCard) {

	}

	@Override
	public void consistentEffect(Card destCard) {

	}

	@Override
	protected void addToObject() {

	}
}
