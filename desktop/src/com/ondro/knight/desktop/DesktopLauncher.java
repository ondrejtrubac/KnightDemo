package com.ondro.knight.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ondro.knight.KnightDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = KnightDemo.WIDTH;
		config.height = KnightDemo.HEIGHT;
		config.title = KnightDemo.TITLE;

		new LwjglApplication(new KnightDemo(), config);
	}
}
