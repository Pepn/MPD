package com.pepn.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pepn.game.SkilledTD;
import com.pepn.game.States.PlayState;
import com.pepn.game.States.State;


public class ScoreState extends State
{
    private Texture background;
    private Texture playBtn;
    private BitmapFont font;

    public ScoreState(GameStateManager gsm)
    {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("playbtn.png");
        font = new BitmapFont();
        font.setColor(Color.BLACK);
    }

    @Override
    public void HandleInput()
    {
        if(Gdx.input.justTouched())
        {
            gsm.Set(new MenuState(gsm));
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

        sb.begin();
        font.draw(sb, ("" + world.getScore()), 100, 100);
        sb.end();

    }

    @Override
    public void Dispose() {
        background.dispose();
        playBtn.dispose();
        font.dispose();
    }
}
