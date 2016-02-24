package Entity;

import java.awt.Graphics;

import Logic.Vector;

public abstract class Entity {
	Vector positioin;
	Vector movement;
	
	public abstract void draw(Graphics g);
	

}
