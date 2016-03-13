package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Logic.Vector;

public class Character extends Entity{

	private BufferedImage head;
	private BufferedImage body;
	
	private Vector jumpVelocity;
	private double accel; //rate at which the character accelerates (first step)
	private double redaccel; //rate at which the acceleration reduces over time rate at which movetime increases
	private int movetime; //number of ticks for which character has been running horizontally
	

	
	public Character (Vector pos){
		super(pos, new Vector(0,0), new Vector(50,100));
		
		this.setFallVelocity(new Vector(1,1));
		this.setJumpVelocity(new Vector(0, 70));
		try {
			body = ImageIO.read(new File("resources/character1_0.png"));
		} catch (IOException e) {
			body = null;
			System.out.println("Characterimage loading Error");
		}
		accel=3;
		redaccel=1;
		movetime=1;
	}
	
	public int getMovetime() {
		return movetime;
	}
	public void setMovetime(int movetime) {
		//movetime cannot be zero because redaccel gets divided by it (sry4badenglish)
		if (movetime!=0)
			this.movetime = movetime;
		else
			this.movetime = 1;
	}
	
	public double getAccel() {
		return accel;
	}

	public void setAccel(double accel) {
		this.accel = accel;
	}

	public double getRedaccel() {
		return redaccel;
	}

	public void setRedaccel(double redaccel) {
		this.redaccel = redaccel;
	}
	@Override
	public void draw(Graphics g, Vector resrelation) {
		if(body != null) g.drawImage(body, (int)((position.getX())*resrelation.getX()), (int)((900-position.getY())*resrelation.getY()), (int)(dimension.getX() * resrelation.getX()), (int) (dimension.getY() * resrelation.getY()), null);
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
