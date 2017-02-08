package com.mygdx.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.HashMap;

/**
 * @author Sean Peel
 */
public class AssetManager {

    private static HashMap<String, TextureRegion> texturesMap = new HashMap<String, TextureRegion>();
    private static HashMap<String, Animation> animationsMap = new HashMap<String, Animation>();

    public static void loadAssets() {

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
    }

    public static Animation getAnimation(String id) {
        return animationsMap.get(id);
    }

    public static TextureRegion getTexture(String id) {
        return texturesMap.get(id);
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

}
