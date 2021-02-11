package com.test.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {																//updates and renders all gameObjects
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	public LinkedList<GameObject> priorityRender = new LinkedList<GameObject>();
	
	public void tick(){
		for(int i=0; i < object.size(); i++){
			GameObject tempObject = object.get(i);									//sets tempObject to object from linked list
			
			tempObject.tick();
		}
		for(int i=0; i < priorityRender.size(); i++){
			GameObject tempObject = priorityRender.get(i);							//sets tempObject to object from linked list
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for(int i=0; i < object.size(); i++){
			try {
				GameObject tempObject = object.get(i);
				tempObject.render(g);
			}catch(NullPointerException e) {										// It is possible for objects to be removed while during render
				System.out.println("NULL POINTER OH WELL");							// this just ignores the problems
			}catch(IndexOutOfBoundsException e) {		   							// which is bad
				System.out.println("OUT OF BOUNDS OH WELL");						// but it seems to work
			}
		}
		for(int i=0; i < priorityRender.size(); i++){
			try {
				GameObject tempObject = priorityRender.get(i);
				tempObject.render(g);
			}catch(NullPointerException e) {										
							
			}catch(IndexOutOfBoundsException e) {		   							
						
			}
		}
		
		
	}
	
	
	public void addObject(GameObject object){
		this.object.add(object);													//adds an object to linked list
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
		object = null;
	}
}
