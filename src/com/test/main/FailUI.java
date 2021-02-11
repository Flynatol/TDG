package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FailUI extends ClickObject {
	
	Font bigFont = new Font("Verdana", Font.PLAIN, 100);
	Font littleFont = new Font("Verdana", Font.PLAIN, 50);
	Rectangle button = new Rectangle(Game.WIDTH/2-160, Game.HEIGHT/2+50, 300, 70);
	Graphics2D g2d = null;
	
	public FailUI(int x, int y, ID id) {
		super(x, y, id);
		this.clickable = true;
		//Game.gameLogic();
		// TODO Auto-generated constructor stub
		//Game.regenerate();
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g2d = (Graphics2D) g;
		g2d.fill(button);
		g.setFont(bigFont);
		g.setColor(Color.RED);
		g.drawString("GAME OVER", Game.WIDTH/2-300, Game.HEIGHT/2-100);
		g.setColor(Color.WHITE);
		g.setFont(littleFont);
		g.drawString("TRY AGAIN", Game.WIDTH/2-150, Game.HEIGHT/2+ 100);
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
