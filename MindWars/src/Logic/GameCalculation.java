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
		while (flag){
			
			//Gravitation:
			p1.setMovement(p1.getMovement().add(map.getA_gravitation()));
			
			
			//Trägheit (negative Beschleunigung gegen Null der x-Ebene):
			Vector temp = p1.getMovement();
			//temp.add(map.getA_inertia().turn(temp.getAngle()+180));
			temp = temp.add(map.getA_gravitation());
			//temp = temp.mul(new Vector(0.75,0));
			
			//character speed
			if(this.left ^ this.right){
				//^=XOR if both horizontal movementbuttons are pressed programm will recognize it as neither
				double taccel;
				if (this.left){
					if (p1.getMovement().getX()>0){
						p1.setMovetime(1);
					}
					taccel=p1.getAccel()/(p1.getMovetime()*p1.getRedaccel());
					p1.setMovetime(p1.getMovetime()+1);
					temp=temp.sub(new Vector (taccel,0));
				} else {
					if (p1.getMovement().getX()<0){
						p1.setMovetime(1);
					}
					taccel=p1.getAccel()/p1.getMovetime()*p1.getRedaccel();
					p1.setMovetime(p1.getMovetime()+1);
					temp=temp.add(new Vector (taccel,0));
				}
				
				
			}
			if(temp.getY() > 10){
				temp.setY(10);
			}
			if(temp.getX() > 10){
				temp.setX(10);
			}
			
			if(temp.getY() < -10){
				temp.setY(-10);
			}
			if(temp.getX() < -10){
				temp.setX(-10);
			}
			
			//p1.setPosition(p1.getPosition().add(temp));
			p1.setMovement(temp);
			
			calcPlayerPos();
			
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}		
	}
	

	private void calcPlayerPos(){
		Character p1t = new Character(p1.getPosition().add(p1.getMovement()));
		p1t.setMovement(p1.getMovement());
		Entity e = map.checkHitbox(p1t);
		
		//System.out.println(map.checkHitbox(p1t.getMovement(), p1t.getDimension()));
		
		p1.setPosition(e.getPosition());
		p1.setMovement(e.getMovement());

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
