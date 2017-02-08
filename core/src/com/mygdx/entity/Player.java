package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.util.AssetManager;
import com.mygdx.util.Constants;
import com.mygdx.util.Enumerations.Direction;
import java.util.InputMismatchException;

/**
 * @author Sean Peel
 */
public class Player {

    private Animation walkingAnim;
    private Direction dir;
    private float stateTime;
    private float playerX = 100;
    private float playerY = 100;
    private float playerSpeed = Constants.LINK_WALKING_SPEED;

    public Player() {
        dir = Direction.UP;
    }

    private void checkInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dir = Direction.LEFT;
            stateTime += Gdx.graphics.getDeltaTime();
            playerX -= playerSpeed * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            dir = Direction.RIGHT;
            stateTime += Gdx.graphics.getDeltaTime();
            playerX += playerSpeed * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dir = Direction.DOWN;
            stateTime += Gdx.graphics.getDeltaTime();
            playerY -= playerSpeed * Gdx.graphics.getDeltaTime();
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dir = Direction.UP;
            stateTime += Gdx.graphics.getDeltaTime();
            playerY += playerSpeed * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            stateTime = 0;
            playerSpeed = 0;
        }

        setWalkingAnimation(dir);
    }

    public void render(SpriteBatch batch) {
        checkInput();

        TextureRegion currentFrame = walkingAnim.getKeyFrame(stateTime, true);

        batch.begin();
        batch.draw(currentFrame, playerX, playerY, Constants.SPRITE_SIZE, Constants.SPRITE_SIZE);
        batch.end();
    }

    public void dispose() {

    }

    private void setWalkingAnimation(Direction dir) {
        playerSpeed = Constants.LINK_WALKING_SPEED;

        switch (dir) {
            case UP:
                walkingAnim = AssetManager.getAnimation(Constants.LINK_WALKING_UP_ASSETS_ID);
                break;
            case RIGHT:
                walkingAnim = AssetManager.getAnimation(Constants.LINK_WALKING_RIGHT_ASSETS_ID);
                break;
            case DOWN:
                walkingAnim = AssetManager.getAnimation(Constants.LINK_WALKING_DOWN_ASSETS_ID);
                break;
            case LEFT:
                walkingAnim = AssetManager.getAnimation(Constants.LINK_WALKING_LEFT_ASSETS_ID);
                break;
            default:
                throw new InputMismatchException("Direction must be up, down, right, or left.");
        }

    }
}
