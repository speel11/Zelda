package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.util.Constants;
import com.mygdx.util.Enumerations.Direction;

/**
 * @author Sean Peel
 */
public abstract class Enemy {

    protected int health;
    protected float x;
    protected float y;
    private Animation animation;
    protected Direction facing;
    private float stateTime;
    private float rotation;

    public Enemy() {
        stateTime = 0;
    }

    public void render(SpriteBatch batch) {
        update(Gdx.graphics.getDeltaTime());
        batch.begin();
        
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        
        batch.draw(currentFrame, x, y, 
                Constants.SPRITE_ORIGIN_X, Constants.SPRITE_ORIGIN_Y, 
                Constants.SPRITE_SIZE, Constants.SPRITE_SIZE, 
                Constants.SCALE, Constants.SCALE
                ,rotation);
        
        batch.end();
    }

    protected void setAnimation(Animation animation) {
        this.animation = animation;
    }

    protected void setRotation(float rotate) {
        rotation = rotate;
    }

    protected void incrementStateTime(float delta) {
        this.stateTime += delta;
    }

    public Direction getFacing() {
        return facing;
    }

    public float getStateTime() {
        return stateTime;
    }

    public abstract void update(float delta);

}
