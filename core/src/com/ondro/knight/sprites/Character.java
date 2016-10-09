package com.ondro.knight.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

/**
 * Created by Ondrej on 17.9.2016.
 */
public class Character {

    private static final float GRAVITY = -15;
    private static final float X_MOVEMENT = 150;

    private static final float RATIO_TO_SCREEN_HEIGHT = 0.3f;

    private static final float IDLE_LENGTH = 0.9f;
    private static final float RUN_LENGTH = 0.6f;
    private static final float JUMP_LENGTH = 0.65f;

    private static final int STATE_RUN = 1;
    private static final int STATE_IDLE = 2;
    private static final int STATE_JUMP = 3;

    private Animation characterAnimation;

    private Animation idleAnimation;
    private Animation walkAnimation;
    private Animation runAnimation;
    private Animation jumpAnimation;
    private Animation jumpAttackAnimation;
    private Animation attackAnimation;
    private Animation deadAnimation;

    private Texture idleTexture;
    private Texture walkTexture;
    private Texture runTexture;
    private Texture jumpTexture;
    private Texture jumpAttackTexture;
    private Texture attackTexture;
    private Texture deadTexture;

    private TextureProperties[] idleTextureProperties;
    private TextureProperties[] walkTextureProperties;
    private TextureProperties[] runTextureProperties;
    private TextureProperties[] jumpTextureProperties;
    private TextureProperties[] jumpAttackTextureProperties;
    private TextureProperties[] attackTextureProperties;
    private TextureProperties[] deadTextureProperties;

    public Vector2 position;
    public Vector2 velocity;
    public Rectangle bounds;

    public int characterState;
    public boolean inCollision;

    public Sprite getTexture() {
        return characterAnimation.getFrame();
    }

    public Character(){

        //TODO pozor, aby hned po narodeni neumrel kvoli konfliktu so zemou
        position = new Vector2(200, 200);
        velocity = new Vector2(0, 0);
        characterState = STATE_RUN;
        inCollision = false;

        idleTexture = new Texture("knight_idle.png");
        runTexture = new Texture("knight_run.png");
        jumpTexture = new Texture("knight_jump.png");

        runTextureProperties = getRunTextureProperties();
        idleTextureProperties = getIdleTextureProperties();
        jumpTextureProperties = getJumpTextureProperties();

        runAnimation = new Animation(new TextureRegion(runTexture), runTextureProperties, RUN_LENGTH, RATIO_TO_SCREEN_HEIGHT);
        idleAnimation = new Animation(new TextureRegion(idleTexture), idleTextureProperties, IDLE_LENGTH, RATIO_TO_SCREEN_HEIGHT);
        jumpAnimation = new Animation(new TextureRegion(jumpTexture), jumpTextureProperties, JUMP_LENGTH, RATIO_TO_SCREEN_HEIGHT);

        characterAnimation = runAnimation;
        bounds = new Rectangle(position.x, position.y, this.getTexture().getWidth(), this.getTexture().getHeight());
    }

    public void update(float dt, ArrayList<CollisionObject> collisionObjects){

        //***** ANIMATION *****//
        switch (characterState){
            case STATE_IDLE:
                characterAnimation = idleAnimation;
                break;
            case STATE_RUN:
                characterAnimation = runAnimation;
                break;
            case STATE_JUMP:
                characterAnimation = jumpAnimation;
            default:
                break;
        }
        characterAnimation.update(dt);

        //***** MOVEMENT *****//
//        position.add(X_MOVEMENT * dt, 0);
//        if(position.y > 50){
//            velocity.add(0, GRAVITY);
//        }
//        velocity.scl(dt);
//        position.add(0, velocity.y);
//        velocity.scl(1/dt);
//
//        if(position.y < 50){
//            position.y = 50;
//            characterState = STATE_RUN;
//        }
        //position.add(X_MOVEMENT * dt, 0);
        velocity.x = X_MOVEMENT;
        position.add(velocity.x * dt, 0);
        bounds.setPosition(position.x, position.y);

        if(!inCollision){
            velocity.add(0, GRAVITY);
        }
        else{
            velocity.y = 0;
            characterState = STATE_RUN;
        }
        velocity.scl(dt);
        position.add(0, velocity.y);
        bounds.setPosition(position.x, position.y);
        velocity.scl(1/dt);

        //if(inCollision){
            //position.y = 50;
            //characterState = STATE_RUN;
        //}
        inCollision = false;
        for(CollisionObject collisionObject : collisionObjects){
            if(this.collides(collisionObject.getBounds())){
                inCollision = true;
            }
        }
    }

    public void draw(SpriteBatch sb){
        Sprite currentTexture = this.getTexture();
        currentTexture.setPosition(position.x, position.y);
        currentTexture.draw(sb);
    }

    public void dispose(){
        idleTexture.dispose();
        runTexture.dispose();
    }

    public boolean collides(Rectangle collisionObject){
        return collisionObject.overlaps(bounds);
    }
    public void run(){
        characterState = STATE_RUN;
    }

    public void idle(){
        characterState = STATE_IDLE;
    }

    public void jump(){
        if(inCollision){
            //TODO toto tu nemoze byt
            inCollision = false;
            characterState = STATE_JUMP;
            velocity.y = 800;
        }
    }

    public float getWidth(){
        return this.getTexture().getWidth();
    }

    private TextureProperties[] getRunTextureProperties(){
        TextureProperties[] textureProperties = new TextureProperties[10];

        textureProperties[0] = new TextureProperties(0, 0, 587, 707);
        textureProperties[1] = new TextureProperties(1176, 0, 587, 707);
        textureProperties[2] = new TextureProperties(0, 708, 587, 707);
        textureProperties[3] = new TextureProperties(588, 708, 587, 707);
        textureProperties[4] = new TextureProperties(1176, 708, 587, 707);
        textureProperties[5] = new TextureProperties(1764, 0, 587, 707);
        textureProperties[6] = new TextureProperties(2352, 0, 587, 707);
        textureProperties[7] = new TextureProperties(1764, 708, 587, 707);
        textureProperties[8] = new TextureProperties(2940, 0, 587, 707);
        textureProperties[9] = new TextureProperties(588, 0, 587, 707);

        return textureProperties;
    }

    private TextureProperties[] getIdleTextureProperties(){
        TextureProperties[] textureProperties = new TextureProperties[10];

        textureProperties[0] = new TextureProperties(0, 0, 587, 707);
        textureProperties[1] = new TextureProperties(1176, 0, 587, 707);
        textureProperties[2] = new TextureProperties(0, 708, 587, 707);
        textureProperties[3] = new TextureProperties(588, 708, 587, 707);
        textureProperties[4] = new TextureProperties(1176, 708, 587, 707);
        textureProperties[5] = new TextureProperties(1764, 0, 587, 707);
        textureProperties[6] = new TextureProperties(2352, 0, 587, 707);
        textureProperties[7] = new TextureProperties(1764, 708, 587, 707);
        textureProperties[8] = new TextureProperties(2940, 0, 587, 707);
        textureProperties[9] = new TextureProperties(588, 0, 587, 707);
        return textureProperties;
    }

    private TextureProperties[] getJumpTextureProperties(){
        TextureProperties[] textureProperties = new TextureProperties[10];

        textureProperties[0] = new TextureProperties(0, 0, 587, 707);
        textureProperties[1] = new TextureProperties(1176, 0, 587, 707);
        textureProperties[2] = new TextureProperties(0, 708, 587, 707);
        textureProperties[3] = new TextureProperties(588, 708, 587, 707);
        textureProperties[4] = new TextureProperties(1176, 708, 587, 707);
        textureProperties[5] = new TextureProperties(1764, 0, 587, 707);
        textureProperties[6] = new TextureProperties(2352, 0, 587, 707);
        textureProperties[7] = new TextureProperties(1764, 708, 587, 707);
        textureProperties[8] = new TextureProperties(2940, 0, 587, 707);
        textureProperties[9] = new TextureProperties(588, 0, 587, 707);
        return textureProperties;
    }
}
