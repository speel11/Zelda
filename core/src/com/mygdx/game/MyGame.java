package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.entity.Player;
import com.mygdx.util.AssetManager;

public class MyGame extends Game {

    private SpriteBatch batch;
    private Camera camera;
    private Player player;
    private FPSLogger logger = new FPSLogger();

    //TODO - ADD CAMERA
    @Override
    public void create() {
        AssetManager.loadAssets();

        camera = new OrthographicCamera();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        camera = new OrthographicCamera(256, 256 * (h / w));

        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        System.out.println(camera.position);
        
        camera.update();
        batch = new SpriteBatch();
        player = new Player();

    }

    @Override
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        player.render(batch);
        
        
        logger.log();
    }

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
    }
}
