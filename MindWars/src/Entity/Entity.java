package Entity;

import java.awt.Graphics;

import Logic.Vector;

public abstract class Entity {
	protected Vector position;
	protected Vector movement;
	protected Vector dimension;
	
	
	
	
	
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
	
	public Vector getDimension(){
		return this.dimension;
	}
	
	public void setDimension(Vector dim){
		this.dimension = new Vector(dim);
	}

	public Entity (Vector pos, Vector mov, Vector dim){
		this.position = new Vector(pos);
		this.movement = new Vector(mov);
		this.dimension = new Vector(dim);
	}
	
	public abstract void draw(Graphics g);
	
	/**
	 * 
	 * @param id: Hitbox Type
	 * @return  0: Stop
	 * 			1: Continue
	 */
	public abstract int hitBox(int id);
	
}
