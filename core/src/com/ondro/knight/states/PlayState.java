package com.ondro.knight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.ondro.knight.sprites.Character;
import com.ondro.knight.sprites.CollisionObject;
import com.ondro.knight.sprites.Ground;
import com.ondro.knight.sprites.InvisibleHoldButton;

import java.util.ArrayList;

import sun.security.acl.GroupImpl;

/**
 * Created by Ondrej on 17.9.2016.
 */
public class PlayState extends State implements InputProcessor {

    private static final int GROUND_Y_OFFSET = -50;
    public static final float CAM_X_MOVEMENT = 75;


    private Character knight;
    private Texture backgroundTexture, ground, invisibleButtonTexture;
    private Sprite background;
    private Ground ground1, ground2, ground4;
    //private Vector2 groundPos1, groundPos2, groundPos3, groundPos4;
    private Vector2 camPosition;
    private InvisibleHoldButton attackButton, jumpButton;
    private ArrayList<CollisionObject> collisionObjects;

    public PlayState(GameStateManager gsm) {
        super(gsm);

        //camera
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camPosition = new Vector2(cam.position.x, cam.position.y);

        //background
        backgroundTexture = new Texture("playBackground.png");
        background = new Sprite(backgroundTexture);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //ground
        ground = new Texture("ground.png");
        ground1 = new Ground(ground, cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
        ground2 = new Ground(ground, (cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
        //ground3 = new Ground(ground, (cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() * 2), GROUND_Y_OFFSET);
        ground4 = new Ground(ground, (cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() * 3), GROUND_Y_OFFSET);

        collisionObjects = new ArrayList<CollisionObject>(){
            {
                add(ground1);
                add(ground2);
                //add(ground3);
                add(ground4);
            }};

//        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET);
//        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth / 2) + ground.getWidth(), GROUND_Y_OFFSET);
//        groundPos3 = new Vector2((cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() * 2), GROUND_Y_OFFSET);
//        groundPos4 = new Vector2((cam.position.x - cam.viewportWidth / 2) + (ground.getWidth() * 3), GROUND_Y_OFFSET);

        //characters
        knight = new Character();

        //buttons
        invisibleButtonTexture = new Texture("invisible_button.png");
        attackButton = new InvisibleHoldButton(invisibleButtonTexture, 800, 100, 100, 100, cam);
        jumpButton = new InvisibleHoldButton(invisibleButtonTexture, 100, 100, 100, 100, cam);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update(float dt) {
        //camera
        updateCamera(dt);

        //ground
        updateGround();

        //buttons
        attackButton.updatePosition();
        if(attackButton.isPushed){
        }

        jumpButton.updatePosition();
        if(jumpButton.isPushed){
            knight.jump();
        }

        //characters
        knight.update(dt, collisionObjects);
        //TODO toto je cele zle, nepaci sa mi to
        if(knight.getTexture().getY() < - 50){
            gsm.set(new PlayState(gsm));
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        renderBackground(sb);
        renderGround(sb);
        knight.draw(sb);
        attackButton.render(sb);
        jumpButton.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        ground.dispose();
        knight.dispose();
        attackButton.dispose();
        jumpButton.dispose();
    }

    private void updateCamera(float dt){
        if(knight.position.x <= (camPosition.x + (cam.viewportWidth / 2) - knight.getWidth() * 2.5f)){
            camPosition.add(CAM_X_MOVEMENT * dt, 0);
        }
        else{
            camPosition.add(knight.velocity.x * dt, 0);
        }
        cam.position.x = camPosition.x;
        cam.position.y = camPosition.y;
        cam.update();
    }

    private void updateGround(){
//        if (cam.position.x - (cam.viewportWidth / 2) > groundPos1.x + ground.getWidth())
//            groundPos1.add(ground.getWidth() * 4, 0);
//        if (cam.position.x - (cam.viewportWidth / 2) > groundPos2.x + ground.getWidth())
//            groundPos2.add(ground.getWidth() * 4, 0);
//        if (cam.position.x - (cam.viewportWidth / 2) > groundPos3.x + ground.getWidth())
//            groundPos3.add(ground.getWidth() * 4, 0);
//        if (cam.position.x - (cam.viewportWidth / 2) > groundPos4.x + ground.getWidth())
//            groundPos4.add(ground.getWidth() * 4, 0);

        if (cam.position.x - (cam.viewportWidth / 2) > ground1.position.x + ground1.getWidth()){
            ground1.position.add(ground1.getWidth() * 4, 0);
            ground1.bounds.setPosition(ground1.position.x, ground1.position.y);
        }

        if (cam.position.x - (cam.viewportWidth / 2) > ground2.position.x + ground2.getWidth()){
            ground2.position.add(ground2.getWidth() * 4, 0);
            ground2.bounds.setPosition(ground2.position.x, ground2.position.y);
        }

        //if (cam.position.x - (cam.viewportWidth / 2) > ground3.position.x + ground3.getWidth()){
        //      ground3.position.add(ground3.getWidth() * 4, 0);
        //      ground3.bounds.setPosition(ground3.position.x, ground3.position.y);
        // }
        if (cam.position.x - (cam.viewportWidth / 2) > ground4.position.x + ground4.getWidth()){
            ground4.position.add(ground4.getWidth() * 4, 0);
            ground4.bounds.setPosition(ground4.position.x, ground4.position.y);
        }

    }

    private void renderBackground(SpriteBatch sb){
        background.setPosition(cam.position.x - cam.viewportWidth / 2, cam.position.y - cam.viewportHeight / 2);
        background.draw(sb);
    }
    private void renderGround(SpriteBatch sb){
//        sb.draw(ground, groundPos1.x, groundPos1.y);
//        sb.draw(ground, groundPos2.x, groundPos2.y);
//        sb.draw(ground, groundPos3.x, groundPos3.y);
//        sb.draw(ground, groundPos4.x, groundPos4.y);
        sb.draw(ground, ground1.position.x, ground1.position.y);
        sb.draw(ground, ground2.position.x, ground2.position.y);
        //sb.draw(ground, ground3.position.x, ground3.position.y);
        sb.draw(ground, ground4.position.x, ground4.position.y);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        attackButton.updateState(screenX , Gdx.graphics.getHeight() - 1 - screenY);
        jumpButton.updateState(screenX , Gdx.graphics.getHeight() - 1 - screenY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        attackButton.release();
        jumpButton.release();
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
