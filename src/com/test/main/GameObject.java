package com.test.main;

import java.awt.Graphics;

public abstract class GameObject {
	
	protected int x, y;
	protected ID id;
	float velY;
	float velX;
	protected int UUID;
	protected boolean clickable;
	public static int counter = 0;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
		this.UUID = counter;
		this.clickable = false;
		counter++;
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return x;		
	}
	
	public int getY(){
		return y;
	}
	
	public void setId(ID id){
		this.id = id;	
	}
	
	public ID getId(){
		return id;
	}
	
}
