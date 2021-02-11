package com.test.main;

import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends Enemy {

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		this.r = 20;
		this.value = 15;
		this.maxhealth = health;
	}

	public void renderA(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x-(r/2), y-(r/2), r, r);
	}
	

}
