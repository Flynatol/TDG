package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TextObject extends GameObject{
	
	public int size = 10;
	Font f;
	public String fname;
	
	public TextObject(int x, int y, int size, String fname, ID id) {
		super(x, y, id);
		this.x = x;
		this.y = y;
		this.fname = fname;
		this.size = size;
		this.f = new Font(this.fname, Font.PLAIN, this.size);
	}
		
	public void tick() {
		
	}
	

	
	public void render(Graphics g) {
		g.setFont(f);
		g.setColor(Color.RED);
		g.drawString(fname +": " + this.size, x, y);
	}

}
