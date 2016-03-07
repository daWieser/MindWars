package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Logic.Vector;

public class Character extends Entity{

	private BufferedImage head;
	private BufferedImage body;
	
	private Vector jumpVelocity;
	
	public Character (Vector pos){
		super(pos, new Vector(0,0), new Vector(50,100));
		
		this.setFallVelocity(new Vector(1,1));
		this.setJumpVelocity(new Vector(0, 70));
		//Load images
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hitBox(int id) {
		if(id == 1 || id == 3){
			this.getMovement().sub(new Vector(this.getMovement().getX(), 0));
		}
		if(id == 2 || id == 4){
			this.getMovement().sub(new Vector(0,this.getMovement().getY()));
		}
		return 0;
	}

	public Vector getJumpVelocity() {
		return jumpVelocity;
	}

	public void setJumpVelocity(Vector jumpVelocity) {
		this.jumpVelocity = jumpVelocity;
	}
	
	

}
