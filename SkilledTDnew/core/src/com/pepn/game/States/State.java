package com.pepn.game.States;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pepn.game.controller.World;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected static World world;
    protected State (GameStateManager gsm){
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();


    }

    protected abstract void HandleInput();
    public abstract void Update(float dt);
    public abstract void Render(SpriteBatch sb);
    public abstract void Dispose();
}
