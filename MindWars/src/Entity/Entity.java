package Entity;

import java.awt.Graphics;

import Logic.Vector;

public abstract class Entity {
	protected Vector position;
	protected Vector movement;
	protected Vector dimension;
	
	private Vector fallVelocity;
	protected boolean grounded;
	protected boolean touchWall;
	
	
	
	public boolean isTouchWall() {
		return touchWall;
	}

	public void setTouchWall(boolean touchWall) {
		this.touchWall = touchWall;
	}

	public boolean isGrounded() {
		return grounded;
	}

	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}

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
		grounded = false;
		touchWall = false;
	}
	
	
	/**
	 * 
	 * @param id: Hitbox Type
	 * @return  0: Stop
	 * 			1: Continue
	 */
	public abstract int hitBox(int id);
	
	
	
	public Vector getFallVelocity() {
		return fallVelocity;
	}

	public void setFallVelocity(Vector fallVelocity) {
		this.fallVelocity = fallVelocity;
	}

	public abstract void draw(Graphics g, Vector resrelation);

	
	
}
