package com.test.main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInput extends MouseAdapter {
	
	private Handler handler;
	
	static int mx=0;
	static int my=0;
	
	public MouseInput(Handler handler) {
		this.handler = handler;
		
	}
	
	public void mouseClicked(MouseEvent e) {
		mx = (int) (e.getX());
		my = (int) (e.getY());
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.clickable) {
				if (((ClickObject)tempObject).getBounds().contains(mx, my)) {
					((ClickObject)tempObject).click();
				}
			}
		}
	}
	
	public void mouseReleased(MouseEvent e) {
	
	}
	
	public void mouseMoved(MouseEvent e) {
		mx = (int) (e.getX());
		my = (int) (e.getY());
	}
	
	public void mouseEntered(MouseEvent e) {
				
	}
	
	public void mouseExited(MouseEvent e) {
				
	}
}
