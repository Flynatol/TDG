package com.test.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Dummy extends ClickObject{
	
	//Ellipse2D oval = new Ellipse2D.Double(x, y, Tower.TowerD, Tower.TowerD);
	boolean dumb = false;
	static boolean spawned = false; 	//these two bools stop the spawning and clicking happening out of sync as to stop nullpointers
	//static boolean ready = true;		//it might work?
	static int cooldown = 0;			//still not working...
	int cost = 150;						//FIXED using try catch in renderer 
	
	public Dummy(int x, int y, boolean dumb, ID id) {
		super(x, y, id);
		this.dumb = dumb;
		this.clickable = true;
	}

	public void tick() {
		if (dumb) {
			this.x = MouseInput.mx;
			this.y = MouseInput.my;
		}
		if (cooldown > 0) {
			cooldown--;
		}
	}

	public void render(Graphics g) {
		if(dumb) {
			g.setColor(new Color(225, 0, 0, 130));
			g.fillOval(x-Tower.range, y-Tower.range, 2*Tower.range, 2*Tower.range);
		}else {
			g.setColor(Color.yellow);
			g.setFont(Game.mainFont);
			g.drawString("" + cost, x+15, y+4);
		}
		
		g.setColor(Color.blue);
		g.fillOval(x-Tower.TowerD/2, y-Tower.TowerD/2, Tower.TowerD, Tower.TowerD);
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x-Tower.TowerD/2,y-Tower.TowerD/2,Tower.TowerD,Tower.TowerD);
	}

	public void click() {
		
		
		if (!dumb && !spawned && cooldown == 0 && (Game.gold-cost)>=0) {
			Game.handler.addObject(new Dummy(-100, -100, true, ID.Dummy));
			spawned = true;
			cooldown = 10;
		}
		if (dumb && spawned) {
			Game.gold = Game.gold-cost;
			//Game.handler.addObject(new Tower(this.x, this.y, ID.Tower));
			//ready = false;
			for (int i=0; i<Game.handler.object.size(); i++) {			//this is crashy sometimes?
				//System.out.println(i);
				GameObject tempObject = Game.handler.object.get(i);
				if (tempObject.id == ID.Tile) {
					if (((Tile)tempObject).gx == Game.whereAmI(this.x) && ((Tile)tempObject).gy == Game.whereAmI(this.y)){
						if(((Tile)tempObject).taken == false) {
							Game.handler.addObject(new Tower(Game.tileLoc(Game.whereAmI(this.x)), Game.tileLoc(Game.whereAmI(this.y)), ID.Tower));
							((Tile)tempObject).taken = true;
						}else {
							Game.gold = Game.gold+cost;
							System.out.println("FAILED");
						}
						i = Game.map.length+1;//escape
					}
					
				}
			}
			//System.out.println("TERMINATION");
			Game.handler.removeObject(this);
			spawned = false;
			//ready = true;
			//System.out.println("COMPLETE");
		}
	}



}