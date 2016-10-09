package com.ondro.knight.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Ondrej on 17.9.2016.
 */
public class Animation {
    //private Array<TextureRegion> frames;
    private Array<Sprite> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;



    //public Animation(TextureRegion region, int frameCount, float cycleTime, float ratioToScreenHeight){
    public Animation(TextureRegion region, TextureProperties[] textureProperties, float cycleTime, float ratioToScreenHeight){

        frameCount = textureProperties.length;
        frames = new Array<Sprite>();
        for(int i = 0; i < frameCount; i++){
            TextureRegion frameTextureRegion = new TextureRegion(
                    region,
                    textureProperties[i].xPoistion,
                    textureProperties[i].yPosition,
                    textureProperties[i].width,
                    textureProperties[i].height);

            Sprite frameSprite = new Sprite(frameTextureRegion);
            //frameSprite.setPosition(50, 50);
            float heightOfSprite = Gdx.graphics.getHeight() * ratioToScreenHeight;
            float originalToRealSizeRatio = frameTextureRegion.getRegionHeight() / heightOfSprite;
            frameSprite.setSize(frameTextureRegion.getRegionHeight() / originalToRealSizeRatio, heightOfSprite);

            frames.add(frameSprite);
        }
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    public void update (float dt){
        currentFrameTime += dt;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount)
            frame = 0;
    }

    public Sprite getFrame(){
        return frames.get(frame);
    }
}