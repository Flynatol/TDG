package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class StartUI extends ClickObject {
	
	Font bigFont = new Font("Verdana", Font.PLAIN, 100);
	Font littleFont = new Font("Verdana", Font.PLAIN, 50);
	Rectangle button = new Rectangle(Game.WIDTH/2-160, Game.HEIGHT/2+50, 300, 70);
	Graphics2D g2d = null;
	
	public StartUI(int x, int y, ID id) {
		super(x, y, id);
		this.clickable = true;
		// TODO Auto-generated constructor stub
	}


	public void tick() {
		// TODO Auto-generated method stub
		
	}

	
	public void render(Graphics g) {
		g.setColor(new Color(51, 51, 153));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(new Color(166, 166, 166));
		g2d = (Graphics2D) g;
		g2d.fill(button);
		g.setFont(bigFont);
		g.setColor(Color.WHITE);
		g.drawString("Tower Defender", Game.WIDTH/2-400, Game.HEIGHT/2-100);
		g.setColor(Color.WHITE);
		g.setFont(littleFont);
		g.drawString("PLAY", Game.WIDTH/2-72, Game.HEIGHT/2+ 100);
		
	}

	public Rectangle getBounds() {
		return new Rectangle(button);
	}

	public void click() {
		Game.gameLogic();
		System.out.println("cicked");
		Game.handler.removeObject(this);
	}

}
