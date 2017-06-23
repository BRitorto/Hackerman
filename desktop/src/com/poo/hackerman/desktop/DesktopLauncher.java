package com.poo.hackerman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.poo.hackerman.controller.HackerGame;
import com.poo.hackerman.model.gameWorld.GameMap;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		//config.fullscreen = true;
		config.width  = 23*GameMap.CELL_SIZE/2;
		config.height = 17*GameMap.CELL_SIZE/2;
		new LwjglApplication(new HackerGame(), config);
	}
}
