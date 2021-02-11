package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Gui extends GameObject {
	
	Font font = new Font("Verdana", Font.PLAIN, 15);
	public Gui(int x, int y, ID id) {
		super(x, y, id);

	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.YELLOW);
		g.drawString("Gold: " + Game.gold, Game.tileLoc(21)-10, Game.tileLoc(1));
		g.setColor(Color.RED);
		g.drawString("Lives: " + Game.lives, Game.tileLoc(21)-10, Game.tileLoc(0)+10);
		
	}

}
