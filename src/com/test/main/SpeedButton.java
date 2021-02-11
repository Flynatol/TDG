package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SpeedButton extends ClickObject {
	
	Rectangle button = new Rectangle(Game.tileLoc(21), Game.tileLoc(15), 2*Game.WIDTH/Game.TPL, Game.WIDTH/Game.TPL);
	boolean turbo = false;
	Graphics2D g2d = null;
	int cooldown = 0;
	Color colour = Color.BLUE;
	
	public SpeedButton(int x, int y, ID id) {
		super(x, y, id);
		this.clickable = true;
	}


	public Rectangle getBounds() {
		return new Rectangle(button);
	}

	public void click() {
		if (cooldown == 0) {
			cooldown = 5;
			turbo = !turbo;
			if (turbo == true) {
				Game.amountOfTicks = 120;
				colour = Color.RED;
			}else {
				Game.amountOfTicks = 60;
				colour = Color.BLUE;
			}
			Game.deltaTPS();
			System.out.println("ClickedTPS");
		}
	}

	public void tick() {
		if (cooldown > 0) {
			cooldown--;
		}
		
	}

	public void render(Graphics g) {
		g.setColor(colour);
		g2d = (Graphics2D) g;
		g2d.fill(button);
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Verdana", Font.PLAIN, 13));
		g.drawString("Time warp", Game.tileLoc(21), Game.tileLoc(15)+20);
	}

}
