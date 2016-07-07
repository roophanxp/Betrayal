package com.jnv.betrayal.dungeon.popup;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.utils.Align;
import com.jnv.betrayal.dungeon.actions.Action;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.popup.Popup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.InputListener;

import java.util.Deque;

public class EventLog extends Popup {

	private Image okayButton;
	private ScrollPane scrollPane;
	private VerticalGroup verticalGroup;
	private Deque<Action> actionHistory;

	public EventLog(Betrayal game, Deque<Action> actionHistory) {
		super(game);
		loadBackground();
		this.actionHistory = actionHistory;
		verticalGroup = new VerticalGroup();
		verticalGroup.layout();
		verticalGroup.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		verticalGroup.align(Align.left);
		scrollPane = new ScrollPane(verticalGroup);
		scrollPane.setBounds(100, 100, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 200);
		scrollPane.layout();
		scrollPane.setZIndex(0);
		scrollPane.setScrollingDisabled(true, false);
		scrollPane.setOverscroll(false, false);
		popup.addActor(scrollPane);

		loadButtons();
		loadHistory();
	}

	private void loadBackground() {
		Image background = new Image(res.getTexture("confirmation-background"));
		background.layout();
		background.setBounds(100, 200, Betrayal.WIDTH - 200, Betrayal.HEIGHT - 100);
		popup.addActor(background);
	}

	private void loadButtons() {
		loadAnswer();
	}

	private void loadAnswer() {
		okayButton = new Image(res.getTexture("ok"));
		okayButton.layout();
		okayButton.setBounds(Betrayal.WIDTH / 2 - 75, 110, 150, 75);

		okayButton.addListener(new InputListener(okayButton) {
			@Override
			public void doAction() {
				remove();
			}
		});
		popup.addActor(okayButton);
	}

	private void loadHistory() {
		for (Action action : actionHistory) {
			Label newEvent = new Label(action.toString(), FontManager.getFont(40));
			verticalGroup.addActor(newEvent);
		}
	}
}