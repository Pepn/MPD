package com.pepn.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pepn.game.SkilledTD;
import com.pepn.game.controller.PlaceTowerController;
import com.pepn.game.controller.World;
import com.pepn.game.entities.Entity;
import com.pepn.game.entities.Grid;
import com.pepn.game.entities.Mob;
import com.pepn.game.entities.Tower;

import java.util.ArrayList;
import java.util.List;


public class PlayState extends State {



    protected PlayState(GameStateManager gsm)
    {
        super(gsm);
        cam.setToOrtho(false, SkilledTD.WIDTH /2, SkilledTD.HEIGHT/2);
        world = new World(cam);

    }

    @Override
    protected void HandleInput()
    {
        world.HandleInput();
    }

    @Override
    public void Update(float dt)
    {
        HandleInput();
        world.Update(dt);

        if(world.GoToScoreState())
            gsm.Set(new ScoreState(gsm));
    }

    @Override
    public void Render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        world.Render(sb);
    }

    @Override
    public void Dispose()
    {
        world.Dispose();
    }


}
