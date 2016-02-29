package Logic;

import java.io.*;
import java.util.ArrayList;

import Entity.Entity;

public class Map {
	private ArrayList<rect> hitbox;
	private class rect{
		Vector position;
		Vector dimension;
		public rect(Vector pos, Vector dim){
			this.position = new Vector (pos);
			this.dimension = new Vector (dim);
		}
	}
	
	private Vector a_gravitation;
	private Vector a_inertia;
	
	public Map (String name){
		
		
		hitbox = new ArrayList<rect>();
		
		//test
		hitbox.add(new rect(new Vector(500,100), new Vector(100,100)));
	}
	
	/**
	 * 
	 * @param e: Entity to check
	 * @return e: optimized entity
	 */
	public Entity checkHitbox (Entity e){
		
		
		//double []sentity = new double[4]; //distance between side of entity and origin of map
		//0=left 1=bottom 2=top 3=right
		/*sentity[0]=pos.getX();
		sentity[1]=pos.getY();
		sentity[2]=pos.getY()+dim.getY();
		sentity[3]=pos.getX()+dim.getX();*/
		
		
		for (int i=0;i<hitbox.size();i++){
			Vector pos = new Vector(e.getPosition());
			Vector dim = new Vector(e.getDimension());
			double eleft = pos.getX();
			double ebottom = pos.getY();
			double eright = pos.getX() + dim.getX();
			double etop = pos.getY() + dim.getY();
			//double []shitbox = new double[4];
			//same as entity
			//0=left 1=bottom 2=top 3=right
			double hleft =hitbox.get(i).position.getX();
			double hbottom =hitbox.get(i).position.getY();
			double htop =hitbox.get(i).position.getY()+hitbox.get(i).dimension.getY();
			double hright =hitbox.get(i).position.getX()+hitbox.get(i).dimension.getX();
			
			if(!(eleft > hright)){ //collision left of entity
				double diff = eleft-hright;
				e.getPosition().add(new Vector(diff, 0));
				e.hitBox(1);
				i--;
				continue;
			}
			if(!(ebottom > htop)){ // collision top of entity
				double diff = htop-ebottom;
				e.getPosition().add(new Vector(0, diff));
				e.hitBox(2);
			}
			if(!(hleft > eright)){ //collision right of entity
				double diff = eright-hleft;
				e.getPosition().add(new Vector(diff, 0));
				e.hitBox(3);
			}
			if(!(hbottom > etop)){ //collision bottom of entity
				double diff = hbottom-etop;
				e.getPosition().add(new Vector(0, diff));
				e.hitBox(4);
			}
		}
		return e;
	}
	
	public void readMap(String name){
		a_gravitation = new Vector(0,-3);
		a_inertia = new Vector(3,0);
		hitbox.add(new rect(new Vector(500,100), new Vector(100,100)));
	}

	public Vector getA_inertia() {
		return a_inertia;
	}

	public void setA_inertia(Vector a_inertia) {
		this.a_inertia = a_inertia;
	}

	public Vector getA_gravitation() {
		return a_gravitation;
	}

	public void setA_gravitation(Vector a_gravitation) {
		this.a_gravitation = a_gravitation;
	}
	
}
