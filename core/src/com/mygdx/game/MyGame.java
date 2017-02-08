package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entity.Player;

public class MyGame extends Game {

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;

    private SpriteBatch batch;
    private Player player;
    private FPSLogger logger = new FPSLogger();

    @Override
    public void create() {
        SCREEN_WIDTH = Gdx.graphics.getWidth();
        SCREEN_HEIGHT = Gdx.graphics.getHeight();
        
        batch = new SpriteBatch();
        player = new Player();

    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //logger.log();
        player.render(batch);

    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
