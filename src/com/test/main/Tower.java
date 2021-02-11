package com.test.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Tower extends ClickObject {
	
	static int range = 150;
	int targetUUID;
	float furthest = 0;
	boolean firing = false;
	private int targX = 0;
	private int targY = 0;
	public static int TowerD = 30;
	int fcd = 30; //fire cooldown
	int damage = 10;
	boolean drawattack = false;
	int attacktime = 20;
	int cd = 0, atcd=0; //cooldown, attacktimecooldown
	GameObject tempObject;
	boolean showOverlay = false;
	Enemy target = null;
	
	public Tower(int x, int y, ID id){
		super(x, y, id); //initialise parent class first
		this.clickable = true;
		//this.addMouseListener(new MouseInput(null));
		
		

	}

	public void tick() {//what to do on tick
		firing = false;
		drawattack = false;
		Enemy target = null;
		furthest = 0;
		
		for (int i = Game.handler.object.size(); i > 0; i--) {
			try {
				 tempObject = Game.handler.object.get(i-1);//this can cause null pointers if tower is placed at the wrong time.
			}catch(NullPointerException e) {										
										
			}
			if (tempObject instanceof Enemy) {
				if (Math.sqrt(Math.pow(this.x-tempObject.x, 2)+Math.pow(this.y-tempObject.y, 2))<range) {
					//System.out.println("In range" + ((Enemy) tempObject).dist);
					if (((Enemy) tempObject).dist > furthest) {
						firing = true;
						furthest = ((Enemy) tempObject).dist;
						targetUUID = tempObject.UUID;
						//System.out.println("range: " + (int)Math.sqrt(Math.pow(this.x-tempObject.x, 2)+Math.pow(this.y-tempObject.y, 2)));
						targX = tempObject.x;
						targY = tempObject.y;
						target = (Enemy) tempObject;
					
					}
				}
			}					
		}
		
		if (cd == 0 && firing) {
			target.health = target.health - damage;
			System.out.println("BLAM");
			if (target.health < 0) {
				target.health = 0;
			}
			cd = fcd;
			atcd = attacktime;
		}
		if (cd != 0) {
			cd--;
		}
		if (atcd != 0) {
			atcd--;
			drawattack = true;
		}
		if (this.getBounds().contains(MouseInput.mx,MouseInput.my)) {
			//Game.handler.removeObject(this);
			//Game.handler.addObject(this);
			if (!showOverlay) {
				Game.handler.priorityRender.add(this);
				Game.handler.removeObject(this);
			}
			showOverlay = true;
			//System.out.println("IN");
		}else if (showOverlay == true && !(this.getBounds().contains(MouseInput.mx,MouseInput.my))){
			showOverlay = false;
			Game.handler.object.add(this);
			Game.handler.priorityRender.remove(this);
		}
	}

	public void render(Graphics g) {//what to do on render
		if(showOverlay) {
			g.setColor(new Color(225, 0, 0, 130));
			g.fillOval(x-range, y-range, 2*range, 2*range);
		}
		g.setColor(Color.blue);
		g.fillOval(x-TowerD/2, y-TowerD/2, TowerD, TowerD);
		g.setColor(Color.red);
		if (drawattack) {
			g.drawLine(targX, targY, x, y);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x-Tower.TowerD/2,y-Tower.TowerD/2,Tower.TowerD,Tower.TowerD);
	}

	public void click() {
		System.out.println("CLICKED");
	}

}
