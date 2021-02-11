package com.test.main;

import java.awt.Graphics;

public class Spawner extends GameObject{

	int delay;
	int startdelay;
	int startup;
	int count;
	int spawned=0;
	int j;
	float ospeed;
	Enemy enemy = null;
	
	public Spawner(int x, int y, int count, int delay, int startdelay, int type, ID id) {
		super(x, y, id);
		this.delay = delay;
		this.count = count;
		this.startdelay = startdelay;
		if (type == 1) {
			this.enemy = new BasicEnemy(x, y, ID.Enemy);
		} else if (type == 2) {
			this.enemy = new FastEnemy(x, y ,ID.Enemy);
		} else if (type == 3) {
			this.enemy = new BigEnemy(x, y, ID.Enemy);
		}
		ospeed = this.enemy.speed;
	}

	public void tick() {
		if (startup != startdelay) {
			startup++;
		}else {
		
			j++;
			if (j==delay) {
				j=0;
				this.enemy.speed = (float) ospeed + Game.getRandFloat();
				this.enemy.tpt = (int) ((Game.WIDTH/Game.TPL)/this.enemy.speed);
				this.enemy.speed = (float)(Game.WIDTH/Game.TPL)/this.enemy.tpt;			
				Game.handler.addObject((GameObject) enemy.clone());
				spawned++;
			}
			if (spawned==count) {
				Game.handler.removeObject(this);
				System.out.println("Spawner terminated");
			}
		}
	}

	public void render(Graphics g) {
		
	}

}
