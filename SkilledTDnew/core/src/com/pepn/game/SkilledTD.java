package com.pepn.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pepn.game.States.GameStateManager;
import com.pepn.game.States.MenuState;
import com.pepn.game.controller.IInputHandler;

public class SkilledTD extends ApplicationAdapter {
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "SKILLER TD";
	private GameStateManager gsm;
	private SpriteBatch batch;
    public static IInputHandler ih;

	public SkilledTD(IInputHandler ih)
    {
        this.ih = ih;
    }

	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.Push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gsm.Update(Gdx.graphics.getDeltaTime());
		gsm.Render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();

	}
}
