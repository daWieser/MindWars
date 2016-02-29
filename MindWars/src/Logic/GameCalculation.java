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
	public GameCalculation (){
		p1 = new Character(new Vector (5,0));
		flag = true;
		map = new Map();
		
		Thread t = new Thread(this);
		t.start();
		
	}
	@Override
	public void run() {
		while (flag){
			Character p1t = new Character(p1.getPosition().add(p1.getMovement()));
			if (map.checkHitbox(p1t.getPosition(), p1t.getDimension())){
				if (p1t.hitBox(0)==0){
					
				}
			} else {
				p1.setPosition(p1t.getPosition());
			}
		}		
	}

}
