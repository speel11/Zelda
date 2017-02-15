package com.mygdx.game;

import com.mygdx.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.mygdx.util.AssetManager;

public class MyGame extends Game {

    

    //private FPSLogger logger = new FPSLogger();

    
    @Override
    public void create() {
        AssetManager.loadAssets();
        //logger.log();
        this.setScreen(new GameScreen());
    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void dispose() {
    }
}
