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
	private BufferedImage[] run;
	private int runanim, runprog;
	
	
	private Vector jumpVelocity;
	private Vector wallJumpVelocity;
	private Vector spawn;
	private double accel; //rate at which the character accelerates (first step)
	private double redaccel; //rate at which the acceleration reduces over time rate at which movetime increases
	private int movetime; //number of ticks for which character has been running horizontally
	
	private Vector maxspeed;
	private Vector minspeed;
	
	private boolean lookleft;
	


	private boolean jumpReleased;
	private int jumpTime;
	private int maxJumpTime;
	
	public Character (Vector pos){
		super(pos, new Vector(0,0), new Vector(50,100));
		this.setSpawn(pos);
		this.setFallVelocity(new Vector(1,1));
		this.setJumpVelocity(new Vector(0, 5));
		this.setWallJumpVelocity(new Vector(30,30));
		
		lookleft = true;
		jumpReleased = false;
		
		maxspeed = new Vector(200,400);
		minspeed = new Vector(2,2);
		
		runanim = 2;
		runprog = 0;
		try {
			body = ImageIO.read(new File("resources/character1_0.png"));
		} catch (IOException e) {
			body = null;
			System.out.println("Characterimage loading Error");
		}
		run = new BufferedImage [runanim];
		for (int i=0;i<runanim;i++){
			try {
				run[i] = ImageIO.read(new File("resources/character1_"+Integer.toString(i)+".png"));
			} catch (IOException e) {
				run[i] = null;
				System.out.println("Failed to load Image character1_"+Integer.toString(i)+" for running animation");
			}
		}
		accel=5;
		redaccel=0.75;
		movetime=1;
		maxJumpTime=10;
	}
	
	public Vector getWallJumpVelocity() {
		return wallJumpVelocity;
	}

	public boolean isJumpReleased() {
		return jumpReleased;
	}

	public void setJumpReleased(boolean jumpReleased) {
		this.jumpReleased = jumpReleased;
	}
	
	public void setWallJumpVelocity(Vector wallJumpVelocity) {
		this.wallJumpVelocity = wallJumpVelocity;
	}

	public Vector getMinspeed() {
		return minspeed;
	}

	public void setMinspeed(Vector minspeed) {
		this.minspeed = minspeed;
	}

	public Vector getMaxspeed() {
		return maxspeed;
	}

	public void setMaxspeed(Vector maxspeed) {
		this.maxspeed = maxspeed;
	}

	public int getMaxJumpTime() {
		return maxJumpTime;
	}

	public void setMaxJumpTime(int maxJumpTime) {
		this.maxJumpTime = maxJumpTime;
	}

	public Vector getSpawn() {
		return spawn;
	}

	public void setSpawn(Vector spawn) {
		this.spawn = spawn;
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
		if (redaccel>0)
			this.redaccel = redaccel;
		else
			System.out.println("Redaccel must be greater than 0");
	}
	@Override
	public void draw(Graphics g, Vector resrelation) {
		if (this.getMovement().getX()==0){
			if (lookleft){
				g.drawImage(body, (int)((position.getX()+dimension.getX())*resrelation.getX()), (int)((900-position.getY()-dimension.getY())*resrelation.getY()), (-1)*(int)(dimension.getX() * resrelation.getX()), (int) (dimension.getY() * resrelation.getY()), null);
			}else{
				g.drawImage(body, (int)((position.getX())*resrelation.getX()), (int)((900-position.getY()-dimension.getY())*resrelation.getY()),(int)(dimension.getX() * resrelation.getX()), (int) (dimension.getY() * resrelation.getY()), null);
	
			}
		}
		if (this.getMovement().getX()<0){
			g.drawImage(run[runprog], (int)((position.getX()+dimension.getX())*resrelation.getX()), (int)((900-position.getY()-dimension.getY())*resrelation.getY()), (-1)*(int)(dimension.getX() * resrelation.getX()), (int) (dimension.getY() * resrelation.getY()), null);
			runprog++;
			if (runprog>=runanim)
				runprog=0;
			lookleft=true;
		}
		if (this.getMovement().getX()>0){
			g.drawImage(run[runprog], (int)((position.getX())*resrelation.getX()), (int)((900-position.getY()-dimension.getY())*resrelation.getY()),(int)(dimension.getX() * resrelation.getX()), (int) (dimension.getY() * resrelation.getY()), null);
			runprog++;
			if (runprog>=runanim)
				runprog=0;
			lookleft=false;
		}
	}

	@Override
	public int hitBox(int id) {
		if(id == 1 || id == 3){
			//1 = left 3 = right
			this.getMovement().setX(0);
			this.setTouchWall(true);
		}
		if(id == 2 || id == 4){
			if(id == 2) this.setGrounded(true);
			this.getMovement().setY(0);
		}
		return 0;
	}

	public boolean isLookleft() {
		return lookleft;
	}

	public void setLookleft(boolean lookleft) {
		this.lookleft = lookleft;
	}

	public Vector getJumpVelocity() {
		return jumpVelocity;
	}

	public void setJumpVelocity(Vector jumpVelocity) {
		this.jumpVelocity = jumpVelocity;
	}

	public int getJumpTime() {
		return jumpTime;
	}

	public void setJumpTime(int jumpTime) {
		this.jumpTime = jumpTime;
	}

	
	public void respawn(){
		this.setPosition(this.getSpawn());
		this.setMovement(new Vector(0,0));
	}
	

}
