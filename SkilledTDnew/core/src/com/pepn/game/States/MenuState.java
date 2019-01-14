package com.pepn.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pepn.game.SkilledTD;

/**
 * Created by Pep on 11/20/2016.
 */

public class MenuState extends State
{
    private Texture background;
    private Texture playBtn;

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
    }

    @Override
    public void HandleInput()
    {
        if(Gdx.input.justTouched())
        {
            gsm.Set(new PlayState(gsm));
            Dispose();
        }
    }

    @Override
    public void Update(float dt)
    {
        HandleInput();
    }

    @Override
    public void Render(SpriteBatch sb)
    {
        sb.begin();
        sb.draw(background, 0, 0, SkilledTD.WIDTH, SkilledTD.HEIGHT);
        sb.draw(playBtn, (SkilledTD.WIDTH/2) - (playBtn.getWidth() /2), (SkilledTD.HEIGHT/2));
        sb.end();

    }

    @Override
    public void Dispose() {
        background.dispose();
        playBtn.dispose();
    }
}
