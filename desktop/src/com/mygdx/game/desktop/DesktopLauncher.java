package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;

public class DesktopLauncher {

    public static void main(String[] arg) {
        int width = 1024;
        int height = 960;
        
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = width;
        config.height = height;
        config.resizable = false;
        
        new LwjglApplication(new MyGame(), config);
    }
}
