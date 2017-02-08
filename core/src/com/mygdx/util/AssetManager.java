package com.mygdx.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;

/**
 * @author Sean Peel
 */
public class AssetManager {

    private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();
    private static HashMap<String, Animation> animationsMap = new HashMap<String, Animation>();

    public Texture linkWalkUpSheet = new Texture(Gdx.files.internal("link_walk_up.png"));
    public Texture linkWalkDownSheet = new Texture(Gdx.files.internal("link_walk_down.png"));
    public Texture linkWalkSideSheet = new Texture(Gdx.files.internal("link_walk_side.png"));

    
    //TODO - Modify
    private static Animation createAnimation(TextureAtlas textureAtlas, String[] regionNames) {

        TextureRegion[] runningFrames = new TextureRegion[regionNames.length];

        for (int i = 0; i < regionNames.length; i++) {
            String path = regionNames[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }

        return new Animation(0.1f, runningFrames);

    }
}
