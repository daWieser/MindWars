package Entity;

import java.awt.Graphics;

import Logic.Vector;

public abstract class Entity {
	protected Vector position;
	protected Vector movement;
	
	
	
	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public Vector getMovement() {
		return movement;
	}

	public void setMovement(Vector movement) {
		this.movement = movement;
	}

	public Entity (Vector pos, Vector mov){
		this.position = new Vector(pos);
		this.movement = new Vector(mov);
	}
	
	public abstract void draw(Graphics g);
	
}
