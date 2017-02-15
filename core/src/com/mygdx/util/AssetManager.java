package com.mygdx.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import java.util.HashMap;

/**
 * @author Sean Peel
 */
public class AssetManager {

    private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();
    private static HashMap<String, Animation> animationsMap = new HashMap<String, Animation>();
    private static HashMap<String, TiledMap> roomsMap = new HashMap<String, TiledMap>();
    
    public static void loadAssets() {
        
        texturesMap.put(Constants.SWORD_ASSETS_ID, new TextureRegion(
                new Texture(Constants.SWORD_ASSETS_PATH)));
        
        /*
        * LINK WALKING ANIMATIONS
        */
        animationsMap.put(Constants.LINK_WALKING_UP_ASSETS_ID,
                createAnimation(new Texture(Constants.LINK_WALKING_UP_ASSETS_PATH),
                        Constants.LINK_WALKING_ROWS, Constants.LINK_WALKING_COLS,
                        false, false));
        animationsMap.put(Constants.LINK_WALKING_RIGHT_ASSETS_ID,
                createAnimation(new Texture(Constants.LINK_WALKING_SIDE_ASSETS_PATH),
                        Constants.LINK_WALKING_ROWS, Constants.LINK_WALKING_COLS,
                        true, false));
        animationsMap.put(Constants.LINK_WALKING_DOWN_ASSETS_ID,
                createAnimation(new Texture(Constants.LINK_WALKING_DOWN_ASSETS_PATH),
                        Constants.LINK_WALKING_ROWS, Constants.LINK_WALKING_COLS,
                        false, false));
        animationsMap.put(Constants.LINK_WALKING_LEFT_ASSETS_ID,
                createAnimation(new Texture(Constants.LINK_WALKING_SIDE_ASSETS_PATH),
                        Constants.LINK_WALKING_ROWS, Constants.LINK_WALKING_COLS,
                        false, false));

        /*
        * LINK ATTACKING ANIMATIONS
        */
        animationsMap.put(Constants.LINK_ATTACKING_UP_ASSETS_ID,
                createAnimation(new Texture(Constants.LINK_ATTACKING_UP_ASSETS_PATH),
                        Constants.LINK_ATTACKING_ROWS, Constants.LINK_ATTACKING_COLS,
                        false, false));
        animationsMap.put(Constants.LINK_ATTACKING_RIGHT_ASSETS_ID,
                createAnimation(new Texture(Constants.LINK_ATTACKING_SIDE_ASSETS_PATH),
                        Constants.LINK_ATTACKING_ROWS, Constants.LINK_ATTACKING_COLS,
                        true, false));
        animationsMap.put(Constants.LINK_ATTACKING_DOWN_ASSETS_ID,
                createAnimation(new Texture(Constants.LINK_ATTACKING_DOWN_ASSETS_PATH),
                        Constants.LINK_ATTACKING_ROWS, Constants.LINK_ATTACKING_COLS,
                        false, false));
        animationsMap.put(Constants.LINK_ATTACKING_LEFT_ASSETS_ID,
                createAnimation(new Texture(Constants.LINK_ATTACKING_SIDE_ASSETS_PATH),
                        Constants.LINK_ATTACKING_ROWS, Constants.LINK_ATTACKING_COLS,
                        false, false));
        
        roomsMap.put(Constants.ROOM_TEST_ASSETS_ID, 
                createRoom(Constants.ROOM_TEST_PATH));
    }

    public static Animation getAnimation(String id) {
        return animationsMap.get(id);
    }

    public static TextureRegion getTexture(String id) {
        return texturesMap.get(id);
    }
    
    public static TiledMap getRoom(String id) {
        return roomsMap.get(id);
    }

    private static Animation createAnimation(Texture texture, int rows, int cols,
            boolean flipX, boolean flipY) {
        TextureRegion[] frames = new TextureRegion[rows * cols];
        TextureRegion[][] tempRegion = TextureRegion.split(texture,
                texture.getWidth() / cols,
                texture.getHeight() / rows);

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tempRegion[i][j].flip(flipX, flipY);
                frames[index++] = tempRegion[i][j];

            }
        }

        return new Animation(Constants.ANIM_SPEED, frames);
    }
    
    private static TiledMap createRoom(String path) {
        TiledMap map = new TmxMapLoader().load(path);
        
        return map;
    }

}
