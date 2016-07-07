package com.jnv.betrayal.dungeon.turns;

import com.badlogic.gdx.utils.Pool;
import com.jnv.betrayal.dungeon.actions.Action;
import com.jnv.betrayal.dungeon.actions.ActionType;
import com.jnv.betrayal.dungeon.cards.Card;
import com.jnv.betrayal.dungeon.cards.PlayerCard;
import com.jnv.betrayal.dungeon.mechanics.Field;
import com.jnv.betrayal.dungeon.utils.Panel;
import com.jnv.betrayal.lobby.inventory.DungeonInventory;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.ui.Button;
import com.jnv.betrayal.scene2d.ui.Label;

import java.util.ArrayList;
import java.util.List;

public class YourTurn extends Turn {

	public YourTurn(Field field, Pool<Label> panelPool, Pool<Button> buttonPool, Group panels, Betrayal game) {
		super(field, panelPool, buttonPool, panels, game);
	}

	private void drawSelectBar(final ActionType actionType) {
		panels.clearChildren();
		panels.addActor(createPanel("Done", 70, Panel.top, new Runnable() {
			public void run() {
				List<Card> dest = new ArrayList<Card>(field.getCardsSelected());
				field.actionManager.performAction(new Action(field.getCurrentCard(), dest, actionType));
				field.turnManager.nextTurn();
				field.endSelectMode();
			}
		}));
		panels.addActor(createPanel("Cancel", 70, Panel.bottom, new Runnable() {
			public void run() {
				draw();
				field.endSelectMode();
			}
		}));
	}

	@Override
	public void draw() {
		panels.clearChildren();
		panels.addActor(createPanel("Items", 70, Panel.bottomLeft, new Runnable() {
			public void run() {
				new DungeonInventory(gsm.game, field.getCurrentCard());
				// todo change input to what is needed by the specific item
			}
		}));
		panels.addActor(createPanel("Attack", 70, Panel.topLeft, new Runnable() {
			public void run() {
				field.beginSelectMode(1);
				drawSelectBar(ActionType.ATTACK);
			}
		}));
		panels.addActor(createPanel("Defend", 70, Panel.topRight, new Runnable() {
			public void run() {
				field.beginSelectMode(1);
				drawSelectBar(ActionType.DEFEND);
			}
		}));
		panels.addActor(createPanel("Flee", 70, Panel.bottomRight, new Runnable() {
			@Override
			public void run() {
				new Confirmation(gsm.game, "Flee? 25% Chance") {
					@Override
					public void doAction() {
						if (PlayerCard.canFlee(1)) {
							field.actionManager.performAction(new Action(field.getCurrentCard(), ActionType.FLEE));
						} else {
							field.actionManager.performAction(new Action(field.getCurrentCard(), ActionType.FAIL_TO_FLEE));
						}
					}
				};
			}
		}));
	}
}