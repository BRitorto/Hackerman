package com.poo.hackerman.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.poo.hackerman.controller.Manager;
import com.poo.hackerman.controller.ModelManager;
import com.poo.hackerman.view.Application;
import com.poo.hackerman.view.HackerGame;
import com.poo.hackerman.view.UIManager;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Manager(), config);
	}
}
