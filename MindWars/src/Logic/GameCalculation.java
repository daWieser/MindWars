package Logic;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Entity.Character;
import Entity.Entity;
import Graphics.InputListener;
import Graphics.MindWars;
import Graphics.Settings;


public class GameCalculation implements Runnable, InputListener{
	private Character p1;
	private boolean flag;
	private ArrayList<Entity> entities;
	
	private Vector temp;

	private Map map;
	private MindWars mindWars;
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	
	public GameCalculation (MindWars mindWars){
		this.mindWars = mindWars;
		p1 = new Character(new Vector (1400,500));
		entities = new ArrayList<Entity>();
		entities.add(p1);
		flag = true;
		this.map = mindWars.getMap();
		
		Thread t = new Thread(this);
		t.start();
		
	}
	
	
	
	
	@Override
	public void run() {
		//flag = false;
		int sec = 0;
		while (flag){
			//Gravitation:
			p1.setMovement(p1.getMovement().add(map.getA_gravitation()));
			
			
			//Tr�gheit (negative Beschleunigung gegen Null der x-Ebene):
			temp = p1.getMovement();
			if(sec == 0)temp = temp.add(map.getA_gravitation());
			
			//horizontal movement
			horizontalMovement();
			
			if(this.left == false && this.right == false && p1.isGrounded()){
				if(sec%5 == 3)temp.setX(temp.getX()*0.5);
			}
			else{
				
			}
			
			minmaxSpeed();
			
			
			if(this.up && !this.down){
				jump();
			}
			
			
			
			
			
			p1.setMovement(temp);
			calcPlayerPos();
			
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sec++;
			if(sec == 15) sec = 0;
		}		
	}
	
	private void jump() {
		if (p1.isTouchWall() && !p1.isGrounded() && p1.isJumpReleased()){ //WallJump
			p1.setJumpTime(0);
			Vector wallJump = new Vector(p1.getWallJumpVelocity().getX(),p1.getWallJumpVelocity().getY());
			if (!p1.isLookleft()){
				wallJump = wallJump.mul(new Vector(-1,1));
			}
			temp = wallJump;
		}
		p1.setJumpReleased(false);
		if(p1.getJumpTime() != 0){	//Regular Jump
			
			temp = temp.add(p1.getJumpVelocity());
			p1.setJumpTime(p1.getJumpTime()-1);
		}
	}

	private void minmaxSpeed() {
		if(temp.getY() > p1.getMaxspeed().getY()){
			temp.setY(p1.getMaxspeed().getY());
		}
		if(temp.getX() > p1.getMaxspeed().getX()){
			temp.setX(p1.getMaxspeed().getX());
		}
		if(temp.getY() < (-1)*p1.getMaxspeed().getY()){
			temp.setY((-1)*p1.getMaxspeed().getY());
		}
		if(temp.getX() < (-1)*p1.getMaxspeed().getX()){
			temp.setX((-1)*p1.getMaxspeed().getX());
		}
		
		if (Math.abs(temp.getX())<p1.getMinspeed().getX()){
			temp.setX(0);
		}
		if (Math.abs(temp.getY())<p1.getMinspeed().getY()){
			temp.setY(0);
		}
	}

	private void horizontalMovement(){
		if(this.left ^ this.right){
			double taccel;
			if (this.left){
				if (p1.getMovement().getX()>=0){
					p1.setMovetime(1);
				}
				taccel=p1.getAccel()/(p1.getMovetime()*p1.getRedaccel());
				p1.setMovetime(p1.getMovetime()+1);
				taccel=taccel*(-1);
			} else {
				if (p1.getMovement().getX()<=0){
					p1.setMovetime(1);
				}
				taccel=p1.getAccel()/(p1.getMovetime()*p1.getRedaccel());
				p1.setMovetime(p1.getMovetime()+1);
			}
			temp=temp.add(new Vector (taccel,0));
		}
	}

	private void calcPlayerPos(){
		p1.setGrounded(false);
		p1.setTouchWall(false);
		p1.setPosition(p1.getPosition().add(p1.getMovement()));
		Entity e = map.checkHitbox(p1);
		
		p1.setPosition(e.getPosition());
		p1.setTouchWall(e.isTouchWall());
		p1.setMovement(e.getMovement());
		p1.setGrounded(e.isGrounded());
		
		
		if(p1.getPosition().getX() < 0) {
			Vector p = p1.getPosition();
			p.setX(0);
			p1.setPosition(p);
			p1.hitBox(1);
		}
		
		if(p1.getPosition().getX() > 1600-p1.getDimension().getX()) {
			Vector p = p1.getPosition();
			p.setX(1600-p1.getDimension().getX());
			p1.setPosition(p);
			p1.hitBox(1);
		}
		
		
		if(p1.getPosition().getY() < 0){
			Vector p = p1.getPosition();
			p.setY(0);
			p1.setPosition(p);
			p1.hitBox(2);
		}
		
		if(p1.isGrounded()){
			p1.setJumpTime(p1.getMaxJumpTime());
			//if(p1.getJumpTime() > p1.getMaxJumpTime()) p1.setJumpTime(p1.getMaxJumpTime());
		}
		
		if(p1.getPosition().getY() > 2000){
			p1.respawn();
		}
		

	}
	
	
	
	@Override
	public void left(boolean status) {
		this.left = status;
		
	}
	@Override
	public void right(boolean status) {
		this.right = status;
		
	}
	@Override
	public void up(boolean status) {
		this.up = status;
		
		if(status==false)p1.setJumpReleased(true);
		
	}
	@Override
	public void down(boolean status) {
		this.down = status;
		
	}
	@Override
	public void key(boolean status, KeyEvent k) {
		if(k.getKeyCode() == KeyEvent.VK_ESCAPE){
			//mindWars.toMenue();
		}
		
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public void stop(){
		this.flag = false;
	}
}
