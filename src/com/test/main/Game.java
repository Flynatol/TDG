package com.test.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = -2681032978929841738L;
	
	public static final int map[] ={0,0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,//0-23,0-16
									0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,7,7,7,//23*16
									0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,1,0,1,0,0,7,7,7,
									3,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,7,7,7,
									3,3,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,0,0,7,7,7,
									3,3,3,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,1,0,0,7,7,7,
									3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,7,7,7,
									3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,7,7,7,
									3,3,3,3,0,0,0,0,0,0,0,1,1,1,1,0,0,0,1,0,0,7,7,7,
									3,3,3,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,7,7,7,
									3,3,3,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,1,0,0,7,7,7,
									3,3,0,0,0,1,1,1,1,1,1,1,0,0,1,0,0,0,1,0,0,7,7,7,
									3,3,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,7,7,7,
									3,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,7,7,7,
									3,0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,1,1,0,0,7,7,7,
									0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,
									0,0,0,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7};
	
	public static final int WIDTH = 840, HEIGHT = 624, TPL = 24, AH = map.length/TPL;
		
	public static int[][] formap = new int[17][TPL];
	
	public static int sx=0,sy=0,gold=0,lives=0,alive=0,wave=0; //spawn x spawn y
    
	
	static double amountOfTicks = 60.0;
	static long lastTime = System.nanoTime();
	static double fpsCap = 120.0;
	static double ns = 1000000000 / amountOfTicks;
	static double ns2 = 1000000000 / fpsCap;
	static double delta = 0;
	static long timer = System.currentTimeMillis();
	static int frames = 0;
	static int ticks = 0;
	static long lastRender = System.nanoTime();
    
	
	public static final int waves[] = {5, 20, 120, 0, 0, 0, 0, 0, 0, 
									   8, 20, 120, 2, 1, 100, 0, 0, 0, 
									   8, 20, 120, 2, 1, 100, 5, 60, 0,
									   10 , 20, 120, 5, 1, 75, 5, 60, 0,
									   100, 1, 1, 100, 1, 1, 100, 1, 1}; 		//in format count, delay, startdelay, count, delay, startdelay, count, delay, startdelay
	
	static Random rand = new Random();
	
	public static List<Integer> path = new ArrayList<Integer>(); 				//My path list
	
	public static MouseInput mouseInput = null;
	public static Font mainFont = new Font("Verdana", Font.PLAIN, 15);
	
	private Thread thread;
	private boolean running = false;
	
	public static Handler handler;
	
	public Game(){
		new Window(WIDTH, HEIGHT, "lets play a game", this);
		handlerGen();
		gameLogic();
		startMenu();
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join(); //kills thread
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int tileLoc(double d) {
		return (int)(WIDTH/Game.TPL*(d+0.5));
	}
	
	public static int whereAmI(int i) {										//centres co-ordinates to the grid.
		return (int)(i/(Game.WIDTH/Game.TPL));
	}
	
	public void run() //main game loop
	{
		deltaTPS();
	    while(running){
			long now = System.nanoTime();
	        delta += (now - lastTime) / ns;
	 
	        while(delta >=1){//decouples logic and renderer
				tick();
				ticks++;
	            delta--;
	        }
	        
	        //now = System.nanoTime();
	        if(running && now-lastRender >= ns2) {//fps counter + render call
				render();
				frames++;
				lastRender = now;
	        }
	                            
	        if(System.currentTimeMillis() - timer > 1000){//end of second
				timer += 1000;
	            System.out.println("FPS: "+ frames +" TPS: "+ ticks + "  Objects: " + handler.object.size() + "  Cooldown: " + Dummy.cooldown + " Gold: " + gold + " lives: " + lives + " Alive: " + alive + " Wave: " + wave);
	            frames = 0;
	            ticks = 0;
	        }
	        lastTime = now;
	    }
	    stop();
	}
	
	public static void deltaTPS() {
		lastTime = System.nanoTime();
	    fpsCap = 120.0;
	    ns = 1000000000 / amountOfTicks;
	    ns2 = 1000000000 / fpsCap;
	    delta = 0;
	    timer = System.currentTimeMillis();
	    frames = 0;
	    ticks = 0;
	    lastRender = System.nanoTime();
	}
	private void tick(){
		handler.tick();
		if (Game.lives == 0) {
			failState();
			Game.lives = 25;
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();//just back-end java graphics stuff
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D)g;
        
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //Provides lovely AA to the game  mmm smooth
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int getlist(int loc) {
		return path.get(loc);
	}
	 
	public static float getRandFloat(){
		return rand.nextFloat();
	}
	
	public void failState() {
		handlerGen();
		Game.handler.addObject(new FailUI(0,0, null));
	}
	
	public void startMenu() {
		handlerGen();
		Game.handler.addObject(new StartUI(0,0, null));
	}
	
	public void handlerGen() {
		if (mouseInput != null) {
			this.removeMouseListener(mouseInput);
			this.removeMouseMotionListener(mouseInput);
		}
		Game.handler = new Handler();
		mouseInput = new MouseInput(handler);
		this.addMouseListener(new MouseInput(handler));
		this.addMouseMotionListener(new MouseInput(handler));
	}
	
	public static void main(String args[]){
		new Game(); // or   Game game = new Game();
	}
	
	
	public static void gameLogic() {
		int x=0, y=0;
		amountOfTicks = 60;
		deltaTPS();
		gold = 500;
		lives = 25;
		alive = 0;
		wave=0;
		path.clear();
		for(int i=0; i < map.length; i++){			//generates a nice 2d array from the flat input from the excel map generation.
			formap[y][x] = map[i]; 					//formap is the formatted map.
			x++;
			if ((i+1)%TPL == 0) {
				x=0;
				y++;
			}
		}
		
		//tile generation
		
		x=0;
		y=0;
		
		for(int i=0; i < map.length; i++){
			handler.addObject(new Tile(WIDTH/TPL * x, WIDTH/TPL * y, map[i], ID.Tile));
			System.out.println("BUILDING WORLD" + i);
			x++;
			if ((i+1)%TPL == 0) {
				x=0;
				y++;
			}		
		}
		
		int ptx=0,pty=0; 					//pt = previous tile. 
		int escape=0,ntx=0,nty=0;
		for(y=0; y < AH; y++) { 			//find start tile
			for(x=0; x < TPL; x++) {
				//System.out.println(x + " " + y);
				if (formap[y][x]==5){
				ntx = x;
				nty = y;
				}
			}
		}									// end find start tile
		
		path.add(2); //initial move down
		System.out.println(ntx + " " + nty);
		
		while (escape == 0) {//start path finding
			//System.out.println("PATHING");
			y=nty;
			x=ntx;
			if (formap[y][x] == 0 || formap[y][x] == 6) {
				escape = 1;
				System.out.println("Thats not good... ");
			}
			if(x < 23) {	
				if (((formap[y][x+1]==1) || (formap[y][x+1]==6)) && !((y==pty)&&(x+1==ptx))) {
					path.add(0);//right
					if (formap[y][x+1]==6) {
						escape = 1;
						path.add(0);
						path.add(4);
					}
					ntx = x+1;
					System.out.println("Right ");
				}
			}	
			
			if (x > 0) {
				if (((formap[y][x-1]==1) || (formap[y][x-1]==6)) && !((y==pty)&&(x-1==ptx))) {
					path.add(1);//left
					if (formap[y][x-1]==6) {
						escape = 1;
						path.add(1);
						path.add(4);
					}
					ntx = x-1;
					System.out.println("Left");
				}
			}
			
			if(y<16) {
				if (((formap[y+1][x]==1) || (formap[y+1][x]==6)) && !((y+1==pty)&&(x==ptx))) {
					path.add(2);//down
					if (formap[y+1][x]==6) {
						escape = 1;
						System.out.println("Down");
						path.add(2);
						path.add(4);
					}
					nty=y+1;
					System.out.println("Down");
				}
			}
			
			if (y > 0) {
				if (((formap[y-1][x]==1) || (formap[y-1][x]==6))  && !((y-1==pty)&&(x==ptx))) {
					path.add(3);//up
					if (formap[y-1][x]==6) {
						escape = 1;
						path.add(3);
						path.add(4);
					}
					nty=y-1;
					System.out.println("up");
				}
			}
			ptx = x; //set previous tile x to x
			pty = y;
		}//end path finding

		System.out.println("Done!");
		
		handler.addObject(new WaveLoop(0,0,ID.GUI));
		//handler.addObject(new Enemy((int) ((WIDTH/TPL)*(3.5)), -(int)(WIDTH/TPL*0.5), 20, ID.Enemy));
		//handler.addObject(new Enemy(tileLoc(3), tileLoc(-1), 20, ID.Enemy));
		//handler.addObject(new Tower(tileLoc(3), tileLoc(3), ID.Tower));
		// x = (width/tpl)*(TILENUM-0.5) y = 
		handler.addObject(new Dummy(tileLoc(22), tileLoc(3), false, ID.Dummy));
		//handler.addObject(new Spawner(tileLoc(3), tileLoc(-1), 1, 1, 1, 1, null));
		//handler.addObject(new Spawner(tileLoc(3), tileLoc(-1), 1, 1, 1, 2, null));
		//handler.addObject(new Spawner(tileLoc(3), tileLoc(-1), 200, 2, 1, 2, null));
				
		//handler.addObject(new Spawner(tileLoc(3), tileLoc(-1), 5, 75, 60, 3, null));
		//handler.addObject(new Spawner(tileLoc(3), tileLoc(-1), 15, 50, 120, 2, null));
		//handler.addObject(new Spawner(tileLoc(3), tileLoc(-1), 5, 40, 600, 1, null));
		handler.priorityRender.add(new Gui(0,0, null));
		handler.addObject(new SpeedButton(0, 0, ID.GUI));
		Game.lives = 20;
	}

}
