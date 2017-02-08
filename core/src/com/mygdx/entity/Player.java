package com.mygdx.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.util.AssetManager;
import java.util.InputMismatchException;

/**
 * @author Sean Peel
 */
public class Player {

    private enum Direction {
        UP, RIGHT, DOWN, LEFT
    };

    private TextureRegion[] walkFrames;
    private AssetManager assets;
    private Animation anim;
    private Direction dir;
    private final int WALKING_ANIM_COLS = 2;
    private final int WALKING_ANIM_ROWS = 1;
    private float stateTime;
    private float animSpeed = 0.1f;
    private float playerX = 500;
    private float playerY = 100;
    private float playerSpeed = 450.0f;
    private int scale = 4;
    private int spriteSize = 16 * scale;

    public Player() {
        assets = new AssetManager();
        walkFrames = new TextureRegion[WALKING_ANIM_COLS * WALKING_ANIM_ROWS];
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

        TextureRegion currentFrame = anim.getKeyFrame(stateTime, true);

        batch.begin();
        batch.draw(currentFrame, playerX, playerY, spriteSize, spriteSize);
        batch.end();
    }

    public void dispose() {

    }

    private void setWalkingAnimation(Direction dir) {
        Texture sheet;
        playerSpeed = 450.0f;

        switch (dir) {
            case UP:
                sheet = assets.linkWalkUpSheet;
                break;
            case RIGHT:
                sheet = assets.linkWalkSideSheet;
                break;
            case DOWN:
                sheet = assets.linkWalkDownSheet;
                break;
            case LEFT:
                sheet = assets.linkWalkSideSheet;
                break;
            default:
                throw new InputMismatchException("Direction must be up, down, right, or left.");
        }

        TextureRegion[][] tempRegion = TextureRegion.split(sheet,
                sheet.getWidth() / WALKING_ANIM_COLS, sheet.getHeight() / WALKING_ANIM_ROWS);

        int index = 0;
        for (int i = 0; i < WALKING_ANIM_ROWS; i++) {
            for (int j = 0; j < WALKING_ANIM_COLS; j++) {
                if (dir == Direction.RIGHT) {
                    tempRegion[i][j].flip(true, false);
                    walkFrames[index++] = tempRegion[i][j];
                } else {
                    walkFrames[index++] = tempRegion[i][j];
                }
            }
        }

        anim = new Animation(animSpeed, walkFrames);

    }
}
