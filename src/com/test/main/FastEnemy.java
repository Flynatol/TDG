package com.test.main;

import java.awt.Color;
import java.awt.Graphics;

public class FastEnemy extends Enemy{

	public FastEnemy(int x, int y, ID id) {
		super(x, y, id);
		this.speed = 2;
		this.health = 50;
		this.maxhealth = health;
		this.r = 10;
		//this.tpt = (int) ((Game.WIDTH/Game.TPL)/speed);
		System.out.println("W/T: " + (Game.WIDTH/Game.TPL) + " TPT: " + tpt);
		//this.speed = (Game.WIDTH/Game.TPL)/tpt;         //meant to correct for rounding errors.
		System.out.println("Speed: " + speed);
		this.value = 10;
	}

	public void renderA(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval(x-(r/2), y-(r/2), r, r);
	}

}
