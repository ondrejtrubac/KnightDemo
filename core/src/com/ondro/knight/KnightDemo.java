package com.ondro.knight;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ondro.knight.states.*;


public class KnightDemo extends ApplicationAdapter {

	public static final int WIDTH = 960;
	public static final int HEIGHT = 540;
	public static final String TITLE = "Knight game";

	private GameStateManager gsm;
	//lets me draw something to the screen
	SpriteBatch batch;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		gsm = new GameStateManager();
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 170, 255, 1);
		//gsm.push(new MenuState(gsm));
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		super.dispose();
		batch.dispose();
	}
}
