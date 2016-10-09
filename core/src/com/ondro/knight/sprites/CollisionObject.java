package com.ondro.knight.sprites;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Ondrej on 2.10.2016.
 */
public class CollisionObject {

    public Rectangle bounds;

    public CollisionObject(float xPosition, float yPosition, float width, float height) {
        bounds = new Rectangle(xPosition, yPosition, width, height);
    }

    public Rectangle getBounds(){
        return bounds;
    }
}