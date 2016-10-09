package com.ondro.knight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.ondro.knight.KnightDemo;

/**
 * Created by Ondrej on 16.9.2016.
 */
public abstract class State {

    //camera will let me see something on the screen
    protected OrthographicCamera cam;
    //Decides, which state gets rendered and updated
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public abstract void update(float dt);

    public abstract void render(SpriteBatch sb);

    public abstract void dispose();
}