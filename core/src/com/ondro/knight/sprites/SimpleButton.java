package com.ondro.knight.sprites;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ondro.knight.states.MenuState;
import com.sun.glass.ui.Menu;

/**
 * Created by Ondrej on 16.9.2016.
 */
public class SimpleButton {

    private Sprite buttonSprite;
    private Texture buttonTexture;
    public boolean wasClicked;

    public SimpleButton(Texture texture, float x, float y, float width, float height) {
        wasClicked = false;
        buttonTexture = texture;
        buttonSprite = new Sprite(buttonTexture);

        buttonSprite.setPosition(x, y);
        buttonSprite.setSize(width, height);
    }

    public void render(SpriteBatch sb) {
        buttonSprite.draw(sb);
    }

    public void update(int input_x, int input_y) {
        if(wasClicked(input_x, input_y)){
            wasClicked = !wasClicked;
        }
    }

    private boolean wasClicked(int ix, int iy) {
        if (ix > buttonSprite.getX() && ix < buttonSprite.getX() + buttonSprite.getWidth()) {
            if (iy > buttonSprite.getY() && iy < buttonSprite.getY() + buttonSprite.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public void dispose(){
        buttonTexture.dispose();
    }
}