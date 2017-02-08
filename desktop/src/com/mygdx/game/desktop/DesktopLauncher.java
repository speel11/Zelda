package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGame;

public class DesktopLauncher {

    public static void main(String[] arg) {
        int width = 256;
        int height = 240;        
        int scale = 4;
        
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = width * scale;
        config.height = height * scale;
        config.resizable = false;
        
        new LwjglApplication(new MyGame(), config);
    }
}
