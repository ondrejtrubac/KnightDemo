package com.ondro.knight.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ondro.knight.sprites.SimpleButton;
import com.ondro.knight.sprites.SoundButton;

/**
 * Created by Ondrej on 16.9.2016.
 */
public class MenuState extends State implements InputProcessor {

    public static final String TAG = "MyAwesomeTag";

    private static final int SOUND_BUTTON_DISTANCE_FROM_CORNER = 50;
    private static final int SOUND_BUTTON_DIMENSION = 100;
    public static final float BACKGROUND_MUSIC_VOLUME = 0.3f;

    private Texture backgroundTexture;
    private Texture playButtonTexture;
    private SimpleButton playButton;

    private Sprite background;
    private SoundButton soundButton;
    private Music backgroundMusic;

    public MenuState(GameStateManager gsm){
        super(gsm);
        //ortho - nezobrazuje vzdialenosti medzi objektami
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        backgroundTexture = new Texture("Menu_1920x1080.png");

        background = new Sprite(backgroundTexture);
        background.setPosition(0, 0);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        soundButton = new SoundButton(Gdx.graphics.getWidth() - SOUND_BUTTON_DIMENSION - SOUND_BUTTON_DISTANCE_FROM_CORNER, SOUND_BUTTON_DISTANCE_FROM_CORNER, SOUND_BUTTON_DIMENSION, SOUND_BUTTON_DIMENSION);
        playButtonTexture = new Texture("start.png");
        playButton = new SimpleButton(playButtonTexture, Gdx.graphics.getWidth() - 200 - 200, Gdx.graphics.getHeight() - 200 - 100, 200, 200);

        Gdx.input.setInputProcessor(this);


        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("Castle_Bard.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(BACKGROUND_MUSIC_VOLUME);
        backgroundMusic.play();
    }

    @Override
    public void update(float dt) {
        if (soundButton.soundOn){
            backgroundMusic.setVolume(0.5f);
        }
        else{
            backgroundMusic.setVolume(0.0f);
        }
        if(playButton.wasClicked){
            gsm.set(new PlayState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        //hovori SpriteBatch kde ma projectnut veci ... do OrtographicCamera
        //point of zero [0, 0] JE V STREDE!!! ... neviem preco u mna je vlavo dole
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        background.draw(sb);
        soundButton.render(sb);
        playButton.render(sb);
        sb.end();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        playButtonTexture.dispose();
        playButton.dispose();
        soundButton.dispose();
        backgroundMusic.dispose();
        Gdx.app.log(TAG, "MenuStateDisposed");
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        soundButton.update(screenX , Gdx.graphics.getHeight() - 1 - screenY);
        playButton.update(screenX , Gdx.graphics.getHeight() - 1 - screenY);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }
}
