package com.test.main;

import java.awt.Color;
import java.awt.Graphics;

public class FakeObject extends GameObject{

	public FakeObject(int x, int y, ID id) {
		super(x, y, id);
		x=-1;
		y=-1;
	}


	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, 1000, 1000);
		
	}

}
