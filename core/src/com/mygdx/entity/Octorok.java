package com.mygdx.entity;

import com.mygdx.util.AssetManager;
import com.mygdx.util.Constants;
import com.mygdx.util.Enumerations.Direction;

/**
 * @author Sean Peel
 */
public class Octorok extends Enemy {

    public Octorok(float x, float y, Direction facing) {
        health = 4;
        this.x = x;
        this.y = y;
        this.facing = facing;
    }

    @Override
    public void update(float delta) {
        setAnimation(AssetManager.getAnimation(Constants.OCTOROK_RED_ASSETS_ID));
        incrementStateTime(delta);
        
        
        
        switch (getFacing()) {
            
            case UP:
                setRotation((float) Constants.ROTATION_UP);
                break;
            case RIGHT:
                setRotation((float) Constants.ROTATION_RIGHT);
                break;
            case DOWN:
                setRotation((float) Constants.ROTATION_DOWN);
                break;
            case LEFT:
                setRotation((float) Constants.ROTATION_LEFT);
                break;
            default:
        }

    }

}
