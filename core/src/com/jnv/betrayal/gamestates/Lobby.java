/*
 * Copyright (c) 2015. JNV Games, All rights reserved.
 */

package com.jnv.betrayal.gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.jnv.betrayal.character.Character;
import com.jnv.betrayal.lobby.LobbyOptions;
import com.jnv.betrayal.lobby.inventory.Inventory;
import com.jnv.betrayal.lobby.shop.Shop;
import com.jnv.betrayal.lobby.social.PartyRoom;
import com.jnv.betrayal.lobby.stats.StatsWindow;
import com.jnv.betrayal.main.Betrayal;
import com.jnv.betrayal.online.Room;
import com.jnv.betrayal.popup.Confirmation;
import com.jnv.betrayal.popup.OKPopup;
import com.jnv.betrayal.resources.FontManager;
import com.jnv.betrayal.scene2d.Group;
import com.jnv.betrayal.scene2d.InputListener;
import com.jnv.betrayal.scene2d.ui.Label;

public class Lobby extends GameState {

	private int buttonWidth, buttonHeight, spacing;
	private Image allPlayersBackground;
	private Texture playButtonTexture, readyTexture, greenCircle, redCircle, UnReadyTexture;
	private Actor playNowButton, readyButton, UnReadyButton;
	private Group partyMembers;
	private Room room;

	public Lobby(GameStateManager gsm) {
		super(gsm);
		buttonHeight = 150;
		buttonWidth = 144;
		spacing = 5;
		room = gsm.game.getCurrentCharacter().getRoom();
		room.setLobby(this);
		partyMembers = new Group();
		loadContent();
		refresh();
	}

	public void update(float dt) {
		stage.act(dt);
	}

	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
	}

	public void dispose() {
	}

	public Betrayal getGame() {
		return gsm.game;
	}

	// Helpers
	private void loadBackground() {
		Image lobbyBackground = new Image(res.getTexture("instructions-background"));
		lobbyBackground.layout();
		lobbyBackground.setBounds(0, 0, Betrayal.WIDTH, Betrayal.HEIGHT);
		stage.addActor(lobbyBackground);
	}

	private void loadShopButton() {
		Image shopButton = new Image(res.getTexture("lobby-shop"));
		shopButton.layout();
		shopButton.setBounds(0, Betrayal.HEIGHT - buttonHeight - 5, buttonWidth, buttonHeight);
		shopButton.addListener(new InputListener(shopButton) {
			@Override
			public void doAction() {
				new Shop(game);
			}
		});
		stage.addActor(shopButton);
	}

	private void loadInventoryButton() {
		Image inventoryButton = new Image(res.getTexture("lobby-inventory"));
		inventoryButton.layout();
		inventoryButton.setBounds(buttonWidth, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		inventoryButton.addListener(new InputListener(inventoryButton) {
			@Override
			public void doAction() {
				new Inventory(game);
			}
		});
		stage.addActor(inventoryButton);
	}

	private void loadStatsButton() {
		Image statsButton = new Image(res.getTexture("lobby-stats"));
		statsButton.layout();
		statsButton.setBounds(buttonWidth * 2, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		statsButton.addListener(new InputListener(statsButton) {
			@Override
			public void doAction() {
				new StatsWindow(game);
			}
		});
		stage.addActor(statsButton);
	}

	private void loadPartyButton() {
		Image partyButton = new Image(res.getTexture("lobby-party"));
		partyButton.layout();
		partyButton.setBounds(buttonWidth * 3, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		partyButton.addListener(new InputListener(partyButton) {
			@Override
			public void doAction() {
				new PartyRoom(game) {
					@Override
					public void remove() {
						super.remove();
						Lobby.this.refresh();
					}
				};
			}
		});
		stage.addActor(partyButton);
	}

	private void loadSettingsButton() {
		Image settingsButton = new Image(res.getTexture("lobby-settings"));
		settingsButton.layout();
		settingsButton.setBounds(buttonWidth * 4, Betrayal.HEIGHT - buttonHeight - spacing,
				buttonWidth, buttonHeight);
		settingsButton.addListener(new InputListener(settingsButton) {
			@Override
			public void doAction() {
				new LobbyOptions(game);
			}
		});
		stage.addActor(settingsButton);
	}

	private void loadAllPlayersBackground() {
		allPlayersBackground = new Image(res.getTexture("player-background"));
		allPlayersBackground.layout();
		allPlayersBackground.setBounds(Betrayal.WIDTH / 2 + 10 - 100, 510, Betrayal.WIDTH / 2 - 20 + 100, Betrayal.HEIGHT / 3 + 175);
		stage.addActor(allPlayersBackground);
	}

	private void loadChatBackground() {
		Image chatBackground = new Image(res.getTexture("lobby-screen"));
		chatBackground.layout();
		chatBackground.setBounds(10, 175, Betrayal.WIDTH - 20, Betrayal.HEIGHT / 4);
		stage.addActor(chatBackground);
	}

	private void loadTower() {
		Image tower = new Image(res.getTexture("lobby-tower"));
		tower.layout();
		tower.setBounds(20, 510, 175, Betrayal.HEIGHT / 3 + 175);
		tower.addListener(new InputListener(tower) {
			@Override
			public void doAction() {
				Lobby.this.refresh();
			}
		});
		stage.addActor(tower);
	}

	private void loadPartyLevels() {
		//TODO: Finish this
	}

	private void loadContent() {
		loadTextures();
		loadBackground();
		loadAllPlayersBackground();
		loadChatBackground();
		loadTower();
		loadPartyLevels();
		loadShopButton();
		loadSettingsButton();
		loadPartyButton();
		loadStatsButton();
		loadInventoryButton();
		loadPlayNowButton();
		loadReadyButton();
		loadUnReadyButton();
	}

	private void loadTextures() {
		greenCircle = res.getTexture("green-circle");
		redCircle = res.getTexture("red-circle");
		readyTexture = res.getTexture("ready");
		playButtonTexture = res.getTexture("play-now");
		UnReadyTexture = res.getTexture("unready");
	}

	private void setReadyPlayButton() {
		if (room.getRoomID() == -1) {
			playNowButton.setVisible(true);
			readyButton.setVisible(false);
			UnReadyButton.setVisible(false);
		} else if(game.getCurrentCharacter().isReady()){
			playNowButton.setVisible(false);
			readyButton.setVisible(false);
			UnReadyButton.setVisible(true);
		}else{
			playNowButton.setVisible(false);
			readyButton.setVisible(true);
			UnReadyButton.setVisible(false);
		}
	}

	private void loadPlayNowButton() {
		playNowButton = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(playButtonTexture, playNowButton.getX(), playNowButton.getY(),
						playNowButton.getWidth(), playNowButton.getHeight());
			}
		};
		playNowButton.setWidth(512);
		playNowButton.setBounds((Betrayal.WIDTH - playNowButton.getWidth()) / 2, 20, 512, 144);
		playNowButton.addListener(new InputListener(playNowButton) {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				playButtonTexture = res.getTexture("play-now-pressed");
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				if (x >= playNowButton.getX()
						&& x <= playNowButton.getX() + playNowButton.getWidth()
						&& y >= playNowButton.getY()
						&& y <= playNowButton.getY() + playNowButton.getHeight()) {
					new Confirmation(game, "Enter Dungeon?") {
						@Override
						public void doAction() {
							gsm.setState(GameStateManager.State.DUNGEON);
						}
					};
				} else playButtonTexture = res.getTexture("play-now");
			}

			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				playButtonTexture = res.getTexture("play-now");
			}
		});
		playNowButton.setVisible(false);
		stage.addActor(playNowButton);
	}

	private void loadUnReadyButton() {
		UnReadyButton = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(UnReadyTexture, UnReadyButton.getX(), UnReadyButton.getY(),
						UnReadyButton.getWidth(), UnReadyButton.getHeight());
			}
		};
		UnReadyButton.setWidth(512);
		UnReadyButton.setBounds((Betrayal.WIDTH - UnReadyButton.getWidth()) / 2, 20, 512, 144);
		UnReadyButton.addListener(new InputListener(UnReadyButton, true) {
			@Override
			public void doAction() {
				room.ready(false);
			}
		});
		UnReadyButton.setVisible(false);
		stage.addActor(UnReadyButton);
	}


	private void loadReadyButton() {
		readyButton = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				batch.draw(readyTexture, readyButton.getX(), readyButton.getY(),
						readyButton.getWidth(), readyButton.getHeight());
			}
		};
		readyButton.setWidth(512);
		readyButton.setBounds((Betrayal.WIDTH - readyButton.getWidth()) / 2, 20, 512, 144);
		readyButton.addListener(new InputListener(readyButton, true) {
			@Override
			public void doAction() {
				room.ready(true);
			}
		});
		readyButton.setVisible(false);
		stage.addActor(readyButton);
	}


	public void refresh() {
		partyMembers.clear();
		loadRoomParty();
		setReadyPlayButton();
	}

	private void loadRoomParty() {
		float width = allPlayersBackground.getImageWidth() - 20;
		float height = (allPlayersBackground.getImageHeight() - 50) / 4;
		float x = allPlayersBackground.getX() + 10;
		float y = allPlayersBackground.getTop() - 10;
		int i = 1;
		for (Character character : room.getCharacters()) {
			addPlayerImage(character, x, (y - (i * height) - (i * 10)), width, height);
			i++;
		}
		stage.addActor(partyMembers);
	}


	private void addPlayerImage(final Character character, float x, float y, float width, float height) {
		Group player = new Group();

		//creating the teammate preview
		final float xPos = x - 20;
		final float yPos = y + (height - 72) / 2 - 115;
		final float previewWidth = 48 * 3;
		final float previewHeight = 72 * 3;

		Actor preview = new Actor() {
			@Override
			public void draw(Batch batch, float parentAlpha) {
				character.preview.drawHeadPreview(batch, xPos, yPos, previewWidth, previewHeight);
			}
		};
		preview.setWidth(previewWidth);
		preview.setHeight(previewHeight);
		preview.setX(xPos);
		preview.setY(yPos);
		preview.setTouchable(Touchable.disabled);
		player.addActor(preview);

		//create name label
		Label name = new Label(character.getName(), FontManager.getFont40());
		name.setX(x + 20 + (width - name.getPrefWidth()) / 2);
		name.setY(y + ((height - name.getPrefHeight()) / 2) + 20);
		player.addActor(name);

		//create floor Label
		Label floor = new Label("Floor: " + character.stats.getFloor(), FontManager.getFont40());
		floor.setX(x + 20 + (width - floor.getPrefWidth()) / 2);
		floor.setY(name.getY() - floor.getPrefHeight() - 5 + 15);
		player.addActor(floor);

		//Create Ready Light
		Image ready = new Image();
		if (character.isReady()) {
			ready.setDrawable(new TextureRegionDrawable(new TextureRegion(greenCircle)));
		} else {
			ready.setDrawable(new TextureRegionDrawable(new TextureRegion(redCircle)));
		}
		ready.setWidth(20);
		ready.setHeight(20);
		ready.setX(x + width - 30);
		ready.setY(y + (height + 20) / 2 - 25);
		player.addActor(ready);

		//used so player is clickable
		Image mask = new Image();
		mask.setWidth(width);
		mask.setHeight(height);
		mask.setX(x);
		mask.setY(y);
		mask.addListener(new InputListener(mask) {
			@Override
			public void doAction() {
				new OKPopup(game, "STUB");
				//new OKPopup(character.stats.getTotalAttack());
			}
		});

		partyMembers.addActor(player);
	}
}
