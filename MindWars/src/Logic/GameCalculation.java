package Logic;

import java.util.ArrayList;

import Entity.Character;
import Entity.Entity;
import Graphics.Settings;


public class GameCalculation implements Runnable{
	private Character p1;
	private boolean flag;
	private ArrayList<Entity> entities;
	
	
	private int characterMovement;

	private Map map;
	private Settings settings;
	private Input input;
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	public GameCalculation (Map map, Settings settings, Input input){
		p1 = new Character(new Vector (5,0));
		flag = true;
		this.map = map;
		
		this.settings=settings;
		
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
			temp = temp.mul(new Vector(0.75,0));
			
			//character speed
			if(input.isLeft() ^ input.isRight()){
				//^=XOR if both horizontal movementbuttons are pressed programm will recognize it as neither
				double taccel;
				if (input.isLeft()){
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
					taccel=p1.getAccel()/p1.getMovetime();
					p1.setMovetime(p1.getMovetime()+1);
					temp=temp.add(new Vector (taccel,0));
				}
				
				
			}
			
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
	/* FAGGIT BENNI METHODEN WERDEN AUSKOMMENTIERT WÖHS SCHLECHT SAN
	public void changeCharacterMovement(int change)
	{
		this.characterMovement=change;
	}
	
	public void characterJump()
	{
		//TODO: implement  Jump 
		
		this.p1.setMovement(this.p1.getMovement().add(new Vector(0,6)));
	}
	
	public void stopCharactermovement()
	{
		//TODO: Stop Character Movement
		this.characterMovement=0;
	}
	*/
}
