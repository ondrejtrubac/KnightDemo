package com.ondro.knight.sprites;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ondro.knight.states.MenuState;
import com.ondro.knight.states.PlayState;
import com.sun.glass.ui.Menu;
/**
 * Created by Ondrej on 24.9.2016.
 */
public class InvisibleHoldButton {

    private Texture buttonTexture;
    private Sprite buttonSprite;
    private float xOffset;
    private float yOffset;
    public boolean isPushed;
    private Camera stateCamera;

    public InvisibleHoldButton(Texture texture, float xOffset, float yOffset, float width, float height, Camera cam) {
        buttonTexture = texture;
        buttonSprite = new Sprite(buttonTexture);

        this.xOffset = xOffset;
        this.yOffset = yOffset;
        buttonSprite.setPosition(xOffset, yOffset);
        buttonSprite.setSize(width, height);
        stateCamera = cam;
    }

    public void render(SpriteBatch sb) {
        buttonSprite.draw(sb);
    }

    public void updateState(int input_x, int input_y) {
        if(wasClicked(input_x, input_y)){
            isPushed = true;
        }
    }

    public void updatePosition(){
        setPosition(stateCamera.position.x - (stateCamera.viewportWidth / 2) + xOffset,
                stateCamera.position.y - (stateCamera.viewportHeight / 2) + yOffset);
    }

    public void release(){
        isPushed = false;
    }

    private boolean wasClicked(int ix, int iy) {
        //if (ix > buttonSprite.getX() && ix < buttonSprite.getX() + buttonSprite.getWidth()) {
        //    if (iy > buttonSprite.getY() && iy < buttonSprite.getY() + buttonSprite.getHeight()) {
        if (ix > xOffset && ix < xOffset + buttonSprite.getWidth()) {
            if (iy > yOffset && iy < yOffset + buttonSprite.getHeight()) {
                return true;
            }
        }
        return false;
    }

    public void setPosition(float x, float y){
        buttonSprite.setPosition(x, y);
    }

    public void dispose(){
        buttonTexture.dispose();
    }
}