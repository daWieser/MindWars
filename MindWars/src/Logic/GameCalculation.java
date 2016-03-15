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
	
	
	
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	@Override
	public void run() {
		//flag = false;
		int sec = 0;
		while (flag){
			
			//Gravitation:
			p1.setMovement(p1.getMovement().add(map.getA_gravitation()));
			
			
			//Trägheit (negative Beschleunigung gegen Null der x-Ebene):
			Vector temp = p1.getMovement();
			//temp.add(map.getA_inertia().turn(temp.getAngle()+180));
			if(sec == 0)temp = temp.add(map.getA_gravitation());
			//temp = temp.mul(new Vector(0.75,0));
			
			//character speed
			if(this.left ^ this.right){
				//^=XOR if both horizontal movementbuttons are pressed programm will recognize it as neither
				double taccel;
				if (this.left){
					if (p1.getMovement().getX()>=0){
						p1.setMovetime(1);
					}
					taccel=p1.getAccel()/(p1.getMovetime()*p1.getRedaccel());
					p1.setMovetime(p1.getMovetime()+1);
					temp=temp.sub(new Vector (taccel,0));
				} else {
					if (p1.getMovement().getX()<=0){
						p1.setMovetime(1);
					}
					taccel=p1.getAccel()/p1.getMovetime()*p1.getRedaccel();
					p1.setMovetime(p1.getMovetime()+1);
					temp=temp.add(new Vector (taccel,0));
				}
			}
			
			//If maxSpeed is reached Movement will stay at max speed
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
			
			//If minSpeed is reached Movement will be 0
			
			if (Math.abs(temp.getX())<p1.getMinspeed().getX()){
				temp.setX(0);
			}
			if (Math.abs(temp.getY())<p1.getMinspeed().getY()){
				temp.setY(0);
			}
			
			
			
			if(this.up && !this.down){ //Jump
				if(p1.getJumpTime() != 0){
					temp = temp.add(p1.getJumpVelocity());
					p1.setJumpTime(p1.getJumpTime()-1);
				}
			}
			
			
			
			
			//p1.setPosition(p1.getPosition().add(temp));
			p1.setMovement(temp);
			//p1.setGrounded(false);
			calcPlayerPos();
			
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sec++;
			if(sec == 30) sec = 0;
		}		
	}
	

	private void calcPlayerPos(){
		p1.setGrounded(false);
		//Character p1t = new Character(p1.getPosition().add(p1.getMovement()));
		p1.setPosition(p1.getPosition().add(p1.getMovement()));
		Entity e = map.checkHitbox(p1);
		
		
		//System.out.println(map.checkHitbox(p1t.getMovement(), p1t.getDimension()));
		
		p1.setPosition(e.getPosition());
		p1.setMovement(e.getMovement());
		p1.setGrounded(e.isGrounded());
		if(p1.isGrounded()){
			p1.setJumpTime(p1.getMaxJumpTime());
			//if(p1.getJumpTime() > p1.getMaxJumpTime()) p1.setJumpTime(p1.getMaxJumpTime());
		}
		
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
			p1.respawn();
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
	
	public void stop(){
		this.flag = false;
	}
}
