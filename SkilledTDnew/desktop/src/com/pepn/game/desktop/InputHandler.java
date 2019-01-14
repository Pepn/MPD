package com.pepn.game.desktop;
import com.badlogic.gdx.Gdx;
import com.pepn.game.controller.IInputHandler;

public class InputHandler implements IInputHandler {
    public InputHandler()
    {
        //Gdx.app.debug("desktop", "inputhandler");
    }
    public void check()
    {
        //Gdx.app.debug("desktop", "inputhandler");
    }

    public boolean CheckInput(int key) //unused
    {
        return Gdx.input.justTouched();
    }

    public int GetPlatformNumber()
    {
        return 1;
    }
}
