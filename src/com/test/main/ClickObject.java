package com.test.main;

import java.awt.Rectangle;

public abstract class ClickObject extends GameObject{

	public ClickObject(int x, int y, ID id) {
		super(x, y, id);
	
	}
	
	public abstract Rectangle getBounds();
	
	public abstract void click();

}
