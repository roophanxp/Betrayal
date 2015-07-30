package com.jnv.betrayal.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jnv.betrayal.handlers.Content;
import com.jnv.betrayal.handlers.GameStateManager;
import com.jnv.betrayal.utilities.TextureLoader;

public class Betrayal extends Game {

	private SpriteBatch sb;
	private OrthographicCamera worldCam;
	public static int WIDTH = 720;
	public static int HEIGHT = 1280;
	private StretchViewport stretchViewport;
	private Stage stage;
	private static FreeTypeFontGenerator generator;

	public static Content res;

	public GameStateManager gsm;
	
	public void create() {
		worldCam = new OrthographicCamera();
		stretchViewport = new StretchViewport(WIDTH, HEIGHT, worldCam);
		stretchViewport.apply();
		sb = new SpriteBatch();
        stage = new Stage(stretchViewport, sb);
		Gdx.input.setInputProcessor(stage);
		stage.setDebugAll(true);

        worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);

        res = new Content();
        TextureLoader.loadAll();

		generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/HURTMOLD.ttf"));

		gsm = new GameStateManager(this);
		gsm.setState(GameStateManager.State.SPLASH);
	}
	public void dispose() {
		super.dispose();
		stage.dispose();
	}
	public void pause() {
		super.pause();
	}
	public void resume() {
		super.resume();
	}
	public void render () {
		worldCam.update();
		sb.setProjectionMatrix(worldCam.combined);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render();
	}
	public void resize(int width, int height) {
		super.resize(width, height);
		stretchViewport.update(width, height);
		worldCam.position.set(worldCam.viewportWidth / 2, worldCam.viewportHeight / 2, 0);
	}

    // Getters
    public SpriteBatch getBatch() {
        return sb;
    }
    public OrthographicCamera getCamera() {
        return worldCam;
    }
    public StretchViewport getStretchViewport() { return stretchViewport; }
    public Screen getScreen() {
        return super.getScreen();
    }
	public Stage getStage() { return stage; }
	public static FreeTypeFontGenerator getHurtmoldFontGenerator() { return generator; }

    // Setters
    public void setScreen(Screen screen) {
        super.setScreen(screen);
    }

}