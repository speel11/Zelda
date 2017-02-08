package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.util.AssetManager;
import com.mygdx.util.Constants;
import com.mygdx.util.Enumerations.Direction;
import com.mygdx.util.Enumerations.LinkStates;

/**
 * @author Sean Peel
 */
public class Player {

    private Animation animation;
    private Direction facing;
    private LinkStates state;
    private float stateTime;
    private float playerX;
    private float playerY;
    private float playerSpeed;

    public Player() {
        playerX = 100;
        playerY = 100;
        playerSpeed = Constants.LINK_WALKING_SPEED;
        facing = Direction.UP;
        state = LinkStates.MOVING;
    }

    private void checkInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            movePlayer(Direction.LEFT);

        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            movePlayer(Direction.RIGHT);

        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            movePlayer(Direction.DOWN);

        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            movePlayer(Direction.UP);

        } else {
            state = LinkStates.IDLE;
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            state = LinkStates.ATTACK_START;

        }

        setAnimation();
    }

    private void checkStates(SpriteBatch batch) {
        switch (state) {
            case IDLE:
                checkInput();
                break;
            case MOVING:
                checkInput();
                break;
            case ATTACK_START:
                stateTime = 0.25f;
                playerSpeed = 0;
                state = LinkStates.ATTACK_SWORD;
                break;
            case ATTACK_SWORD:
                if (stateTime <= 0.18f) {
                    drawSword(batch);
                }
                if (stateTime > 0) {
                    stateTime -= Gdx.graphics.getDeltaTime();
                } else {
                    state = LinkStates.IDLE;
                }
                break;

        }
    }

    private void movePlayer(Direction dir) {
        state = LinkStates.MOVING;
        facing = dir;
        stateTime += Gdx.graphics.getDeltaTime();

        switch (dir) {
            case UP:
                playerY += playerSpeed * Gdx.graphics.getDeltaTime();
                break;
            case RIGHT:
                playerX += playerSpeed * Gdx.graphics.getDeltaTime();
                break;
            case DOWN:
                playerY -= playerSpeed * Gdx.graphics.getDeltaTime();
                break;
            case LEFT:
                playerX -= playerSpeed * Gdx.graphics.getDeltaTime();
                break;
            default:
                break;
        }

    }

    public void render(SpriteBatch batch) {
        batch.begin();
        checkStates(batch);

        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, playerX, playerY, Constants.SPRITE_SIZE, Constants.SPRITE_SIZE);
        batch.end();
    }

    public void dispose() {

    }

    private void drawSword(SpriteBatch batch) {
        TextureRegion sword = AssetManager.getTexture(Constants.SWORD_ASSETS_ID);

        switch (facing) {
            case UP:
                batch.draw(sword, playerX, playerY + 12,
                        Constants.SWORD_ROTATION_X, Constants.SWORD_ROTATION_Y,
                        Constants.SPRITE_SIZE, Constants.SPRITE_SIZE,
                        Constants.SCALE, Constants.SCALE,
                        Constants.SWORD_ROTATION_UP);
                break;
            case LEFT:
                batch.draw(sword, playerX, playerY,
                        Constants.SWORD_ROTATION_X, Constants.SWORD_ROTATION_Y,
                        Constants.SPRITE_SIZE, Constants.SPRITE_SIZE,
                        Constants.SCALE, Constants.SCALE,
                        Constants.SWORD_ROTATION_LEFT);
                break;
            case DOWN:
                batch.draw(sword, playerX + 12, playerY,
                        Constants.SWORD_ROTATION_X, Constants.SWORD_ROTATION_Y,
                        Constants.SPRITE_SIZE, Constants.SPRITE_SIZE,
                        Constants.SCALE, Constants.SCALE,
                        Constants.SWORD_ROTATION_DOWN);
                break;
            case RIGHT:
                batch.draw(sword, playerX + 12, playerY + 12,
                        Constants.SWORD_ROTATION_X, Constants.SWORD_ROTATION_Y,
                        Constants.SPRITE_SIZE, Constants.SPRITE_SIZE,
                        Constants.SCALE, Constants.SCALE,
                        Constants.SWORD_ROTATION_RIGHT);
                break;
        }

    }

    private void setAnimation() {
        switch (state) {
            case IDLE:
                if (facing != null) {
                    switch (facing) {
                        case UP:
                            animation = AssetManager.getAnimation(Constants.LINK_WALKING_UP_ASSETS_ID);
                            break;
                        case RIGHT:
                            animation = AssetManager.getAnimation(Constants.LINK_WALKING_RIGHT_ASSETS_ID);
                            break;
                        case DOWN:
                            animation = AssetManager.getAnimation(Constants.LINK_WALKING_DOWN_ASSETS_ID);
                            break;
                        case LEFT:
                            animation = AssetManager.getAnimation(Constants.LINK_WALKING_LEFT_ASSETS_ID);
                            break;
                        default:
                            break;
                    }
                }
                break;

            case MOVING:
                playerSpeed = Constants.LINK_WALKING_SPEED;
                if (facing != null) {
                    switch (facing) {
                        case UP:
                            animation = AssetManager.getAnimation(Constants.LINK_WALKING_UP_ASSETS_ID);
                            break;
                        case RIGHT:
                            animation = AssetManager.getAnimation(Constants.LINK_WALKING_RIGHT_ASSETS_ID);
                            break;
                        case DOWN:
                            animation = AssetManager.getAnimation(Constants.LINK_WALKING_DOWN_ASSETS_ID);
                            break;
                        case LEFT:
                            animation = AssetManager.getAnimation(Constants.LINK_WALKING_LEFT_ASSETS_ID);
                            break;
                        default:
                            break;
                    }
                }
                break;
            case ATTACK_START:
                if (facing != null) {
                    switch (facing) {
                        case UP:
                            animation = AssetManager.getAnimation(Constants.LINK_ATTACKING_UP_ASSETS_ID);
                            break;
                        case RIGHT:
                            animation = AssetManager.getAnimation(Constants.LINK_ATTACKING_RIGHT_ASSETS_ID);
                            break;
                        case DOWN:
                            animation = AssetManager.getAnimation(Constants.LINK_ATTACKING_DOWN_ASSETS_ID);
                            break;
                        case LEFT:
                            animation = AssetManager.getAnimation(Constants.LINK_ATTACKING_LEFT_ASSETS_ID);
                        default:
                            break;
                    }
                }
            default:
                break;
        }

    }
}
