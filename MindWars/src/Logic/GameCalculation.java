package Logic;

import java.util.ArrayList;

import Entity.Character;
import Entity.Entity;


public class GameCalculation implements Runnable{
	private Character p1;
	private boolean flag;
	private ArrayList<Entity> entities;

	private Map map;
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	public GameCalculation (Map map){
		p1 = new Character(new Vector (5,0));
		flag = true;
		this.map = map;
		
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
		while (flag){
			calcPlayerPos();
			
			//Gravitation:
			p1.setMovement(p1.getMovement().add(map.getA_gravitation()));
			
			
			//Trägheit (negative Beschleunigung gegen Null der x-Ebene):
			Vector temp = p1.getMovement();
			//temp.add(map.getA_inertia().turn(temp.getAngle()+180));
			temp = temp.add(map.getA_gravitation());
			//temp = temp.mul(new Vector(0.75,0));
			p1.setMovement(temp);
			
			
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
		Entity e = map.checkHitbox(p1t);
		
		p1.setPosition(e.getPosition());
		p1.setMovement(e.getMovement());
	}
	
	public void changeCharacterMovement(Vector change)
	{
		this.p1.setMovement(this.p1.getMovement().add(change));
	}
	
	public void characterJump()
	{
		//TODO: implement  Jump 
	}
	
	public void stopCharactermovement(int m)
	{
		//TODO: Stop Character Movement
	}
}
