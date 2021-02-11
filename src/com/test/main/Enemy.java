package com.test.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Enemy extends GameObject implements Cloneable{

	int r;
	int tickcount = 0;
	float speed = 1;
	int lloc = 0;//list location
	int tpt = (int) ((Game.WIDTH/Game.TPL)/speed);  // ticks per turn... probably
	float dist = 0;
	int health = 100;
	int value;
	Boolean showOverlay = false;
	float realX;
	float realY;
	int maxhealth = health;
	
	Color healthcolour = new Color(0, 100, 0);
	
	public Enemy(int x, int y, ID id) {
		super(x, y,id);	
		//speed = (Game.WIDTH/Game.TPL)/tpt;
		realX = x;
		realY = y;
	}

	public void tick() {
		if (tickcount == tpt || tickcount == 0) {
			tickcount = 0;
			velX=0;
			velY=0;
			
			//System.out.println("Making a move of " + Game.WIDTH/Game.TPL + " by moving at " + speed + " for " + tpt + "ticks");
			
			if (lloc != 0) {
				//this.realX = Game.tileLoc(Game.whereAmI(this.x));
				//this.realY = Game.tileLoc(Game.whereAmI(this.y));
				//System.out.println("MARK");
			}
			
			if (Game.getlist(lloc) == 0) {
				velX=speed;
				//System.out.println("right");
			}
			if (Game.getlist(lloc) == 1) {
				velX=speed*-1;
				//System.out.println("left");
			}
			if (Game.getlist(lloc) == 2) {
				velY=speed;
				//System.out.println("down");
			}
			if (Game.getlist(lloc) == 3) {
				velY=speed*-1;
				//System.out.println("up");
			}
			if (Game.getlist(lloc) == 4) {
				//System.out.println("Goodbye cruel world");
				Game.handler.removeObject(this);
				Game.lives--;
				Game.alive--;
			}
			lloc++;
		}
		//System.out.println("tickcount " + tickcount + " RealX: " + realX + " X: " + x + " RealY " + realY + "  Y: " + y );
		
		realX += velX;
		realY += velY;
		
		x = (int) realX;
		y = (int) realY;	
		
		dist = (dist+Math.abs(velX)+Math.abs(velY));
		
		if (health == 0) {
			Game.handler.removeObject(this);
			Game.gold = Game.gold+this.value;
			Game.alive--;
		}
		//tickcount += 1;
		
		if (this.getBounds().contains(MouseInput.mx,MouseInput.my)) {//displaying health
			
			if (!showOverlay) {
				//Game.handler.priorityRender.add(this);
				//Game.handler.removeObject(this);
			}
			showOverlay = true;
			//System.out.println("IN");
		}else if (showOverlay == true && !(this.getBounds().contains(MouseInput.mx,MouseInput.my))){
			showOverlay = false;
			//Game.handler.object.add(this);
			//Game.handler.priorityRender.remove(this);
		}
		tickcount++;
	}
	
	protected Object clone() {
        try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return 0;
    }
	
	public Rectangle getBounds() {
		return new Rectangle(x-this.r/2,y-this.r/2,r,r);
	}
	

	public void render(Graphics g) {
		this.renderA(g);
		this.renderOverlay(g);
	}
	
	public void renderOverlay(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x-r/2, y+r/2+1, maxhealth/5, 10);
		g.setColor(healthcolour);
		g.fillRect(x-r/2, y+r/2+1, health/5, 10);
	}
	
	public abstract void renderA(Graphics g);

}
