package com.test.main;

import java.awt.Color;
import java.awt.Graphics;

public class Tile extends GameObject {
	
	private int type;
	boolean taken = false;
	int gx = Game.whereAmI(x);
	int gy = Game.whereAmI(y);
	
	public Tile(int x, int y, int t, ID id){
		super(x, y, id);						//initialise parent class first
		type = t;

		if (type == 1 || type == 6 || type == 5 || type == 7 || type == 3){
			this.taken = true;
		}
	}

	public void tick() {
		
	}

	public void render(Graphics g) {			//what to do on render
		g.setColor(Color.GREEN);
		if (type == 0){
			g.setColor(Color.GREEN);
		}
		
		if (type == 1 || type == 6 || type == 5){
			g.setColor(Color.YELLOW);
		}
		
		if (type == 2){
			g.setColor(Color.RED);
		}
		
		if (type == 3){
			g.setColor(Color.BLUE);
		}
		if (type == 7) {
			g.setColor(new Color(100, 100, 100));
		}
		
		if (type == 5){
			Game.sx = 7;
		}
		
		g.fillRect(x, y, Game.WIDTH/Game.TPL, Game.WIDTH/Game.TPL);
		
	}

}
