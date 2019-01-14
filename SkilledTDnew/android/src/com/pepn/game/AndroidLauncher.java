package com.pepn.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.pepn.game.SkilledTD;
import com.pepn.game.controller.IInputHandler;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		IInputHandler ih = new InputHandler();
		initialize(new SkilledTD(ih), config);
	}
}
