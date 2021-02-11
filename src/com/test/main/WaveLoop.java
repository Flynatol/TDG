package com.test.main;

import java.awt.Graphics;

public class WaveLoop extends GameObject {

	public WaveLoop(int x, int y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		if (Game.alive == 0) {
			if (Game.wave<Game.waves.length/9) {
				for (int i=0; i<3; i++) {
					Game.handler.addObject(new Spawner(Game.tileLoc(3), Game.tileLoc(-1), Game.waves[9*Game.wave+3*i], Game.waves[9*Game.wave+3*i+1], Game.waves[9*Game.wave+3*i+2], i+1, ID.GUI));
					Game.alive = Game.alive+Game.waves[9*Game.wave+3*i];
				}
				Game.wave++;
			}
		}
		
	}

	public void render(Graphics g) {
		
	}

}
