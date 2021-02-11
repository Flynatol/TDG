package com.test.main;

import java.awt.Color;
import java.awt.Graphics;

public class BigEnemy extends Enemy{

	public BigEnemy(int x, int y, ID id) {
		super(x, y, id);
		this.speed = (float) 0.5;
		//this.speed = (float) this.speed + Game.getRandFloat();
		System.out.println(this.speed);
		this.health = 150;
		this.maxhealth = health;
		this.r = 30;
		//this.tpt = (int) ((Game.WIDTH/Game.TPL)/speed);
		this.value = 50;
	}

	public void renderA(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x-(r/2), y-(r/2), r, r);
		
	}

}
