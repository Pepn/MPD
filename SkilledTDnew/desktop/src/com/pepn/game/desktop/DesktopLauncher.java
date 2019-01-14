package com.pepn.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pepn.game.SkilledTD;
import com.pepn.game.controller.IInputHandler;

public class DesktopLauncher {
	public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = SkilledTD.WIDTH;
        config.height = SkilledTD.HEIGHT;
        config.title = SkilledTD.TITLE;
        IInputHandler ih = new InputHandler();
        new LwjglApplication(new SkilledTD(ih), config);
    }
}
