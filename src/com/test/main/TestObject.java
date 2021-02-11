package com.test.main;

import java.awt.Color;
import java.awt.Graphics;

public class TestObject extends GameObject{
	
	public TestObject(int x, int y, ID id){
		super(x, y, id); //initialise parent class first
	}

	public void tick() {//what to do on tick
		
	}

	public void render(Graphics g) {//what to do on render
		g.setColor(Color.GREEN);
		g.fillRect(x, y, Game.WIDTH/Game.TPL, Game.WIDTH/Game.TPL);
	}
}
