package com.pepn.game;

import com.badlogic.gdx.Gdx;
import com.pepn.game.controller.IInputHandler;

public class InputHandler implements IInputHandler {
    InputHandler()
    {
        //Gdx.app.debug("android", "Android Input Handler");
    }

    public void check()
    {

    }

    public boolean CheckInput(int key)
    {
        return Gdx.input.justTouched();
    }

    public int GetPlatformNumber()
    {
        return 2;
    }
}
