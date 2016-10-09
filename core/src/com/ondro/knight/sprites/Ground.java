package com.ondro.knight.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ondrej on 2.10.2016.
 */
public class Ground extends CollisionObject{

    Texture ground;
    public Vector2 position;

    public Ground(Texture ground, float xPosition, float yPosition){
        super(xPosition, yPosition, ground.getWidth(), ground.getHeight());
        this.ground = ground;
        this.position = new Vector2(xPosition, yPosition);
    }

    public int getWidth(){
        return ground.getWidth();
    }

    public int getHeight(){
        return ground.getHeight();
    }
}
