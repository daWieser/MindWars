package Logic;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import Entity.Character;
import Entity.Entity;
import Entity.Projectile;
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
			//Playermovement:
			temp = p1.getMovement();
			//horizontal movement
			horizontalMovement();
			
			if(this.left == false && this.right == false && p1.isGrounded()){
				if(sec%5 == 3)temp.setX(temp.getX()*0.5);
			}
			else{
				
			}
			
			
			
			
			if(this.up && !this.down){
				jump();
			}
			
			
			
			
			
			p1.setMovement(temp);
			//Physix (All Entities):
			for(int i = 0; i < entities.size(); i++){
				Entity en = entities.get(i);
				temp = en.getMovement();
				
				//Gravitation:
				temp = temp.add(map.getA_gravitation().mul(en.getFallVelocity()));
				//en.setMovement(en.getMovement().add(map.getA_gravitation().mul(en.getFallVelocity())));
				
				
				//Trägheit (negative Beschleunigung gegen Null der x-Ebene):
				
				if(sec == 0)temp = temp.add(map.getA_inertia().mul(en.getFallVelocity()));
				if(en.getType() == 1)minmaxSpeed();
				en.setMovement(temp);
				calcPlayerPos(en);
				
			}
			
			
			
			
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cleanEntities();
			
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

	private void calcPlayerPos(Entity en){
		en.setGrounded(false);
		en.setTouchWall(false);
		en.setPosition(en.getPosition().add(en.getMovement()));
		Entity e = map.checkHitbox(en);
		
		en.setPosition(e.getPosition());
		en.setTouchWall(e.isTouchWall());
		en.setMovement(e.getMovement());
		en.setGrounded(e.isGrounded());
		
		
		if(en.getPosition().getX() < 0) {
			Vector p = en.getPosition();
			p.setX(0);
			en.setPosition(p);
			en.hitBox(1);
		}
		
		if(en.getPosition().getX() > 1600-en.getDimension().getX()) {
			Vector p = en.getPosition();
			p.setX(1600-en.getDimension().getX());
			en.setPosition(p);
			en.hitBox(1);
		}
		
		
		if(en.getPosition().getY() < 0){
			Vector p = en.getPosition();
			p.setY(0);
			en.setPosition(p);
			en.hitBox(2);
		}
		
		if(en.isGrounded()){ //v
			if(en.getType() == 1){
				p1.setJumpTime(p1.getMaxJumpTime());
				//if(p1.getJumpTime() > p1.getMaxJumpTime()) p1.setJumpTime(p1.getMaxJumpTime());
			}
			else if(en.getType() == 2){
				
			}
			
		}
		
		if(en.getPosition().getY() > 2000){ //v
			if(en.getType() == 1){
				p1.respawn();
			}
			else if(en.getType() == 2){
				en.hitBox(4);
			}
		}
		

	}
	
	private void cleanEntities(){
		for(int i = 0; i < entities.size(); i++){
			Entity en = entities.get(i);
			if(en.getType() == 1) {
				continue;
			}
			if(en.getMovement().getX() == 0 && en.getMovement().getY() == 0){
				entities.remove(i);
				i--;
			}
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

	@Override
	public void mousePressed(Vector me) {
		
		Vector mov = new Vector(100,0);
		
		Vector pp = new Vector(p1.getPosition());
		pp.setY(pp.getY()+p1.getDimension().getY()*0.7);
		//me.setX(me.getX()*-1);
		me = me.sub(pp);
		double angle = Math.atan(me.getY()/me.getX())*180/Math.PI;
		if(me.getX() < 0) angle += 180;
		mov = mov.turn(angle);
		Projectile p = new Projectile(pp, mov.mul(0.2));
		
		entities.add(p);
	}
}
