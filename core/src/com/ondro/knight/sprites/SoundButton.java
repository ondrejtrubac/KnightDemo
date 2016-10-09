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
public class SoundButton {

    public boolean soundOn;
    //sprite is the actual object
    private Sprite soundSprite;
    private Texture soundOnTexture;
    private Texture soundOffTexture;
    private float xPosition, yPosition;
    public float width, height;

    public SoundButton(float x, float y, float width, float height) {
        xPosition = x;
        yPosition = y;
        this.width = width;
        this.height = height;
        soundOn = true;
        soundOnTexture = new Texture("sound_on.png");
        soundOffTexture = new Texture("sound_off.png");

        setSprite();
    }

    public void render(SpriteBatch sb) {
        soundSprite.draw(sb);
    }

    public void update(int input_x, int input_y) {
        if(wasClicked(input_x, input_y)){
            soundOn = !soundOn;
        }
        setSprite();
    }

    private boolean wasClicked(int ix, int iy) {
        if (ix > soundSprite.getX() && ix < soundSprite.getX() + soundSprite.getWidth()) {
            if (iy > soundSprite.getY() && iy < soundSprite.getY() + soundSprite.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public void setSprite(){
        if(soundOn){
            soundSprite = new Sprite(soundOnTexture);
        }
        else{
            soundSprite = new Sprite(soundOffTexture);
        }
        soundSprite.setPosition(xPosition, yPosition);
        soundSprite.setSize(width, height);
    }

    public void dispose(){
        soundOnTexture.dispose();
        soundOffTexture.dispose();
    }
}