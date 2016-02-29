package Logic;

import java.io.*;
import java.util.ArrayList;

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
	
	public Map (){
		hitbox = new ArrayList<rect>();
		
		//test
		hitbox.add(new rect(new Vector(500,100), new Vector(100,100)));
	}
	
	public boolean checkHitbox (Vector pos, Vector dim){
		double []sentity = new double[4]; //distance between side of entity and origin of map
		//0=left 1=bottom 2=top 3=right
		sentity[0]=pos.getX();
		sentity[1]=pos.getY();
		sentity[2]=pos.getY()+dim.getY();
		sentity[3]=pos.getX()+dim.getX();
		
		for (int i=0;i<hitbox.size();i++){
			double []shitbox = new double[4];
			//same as entity
			//0=left 1=bottom 2=top 3=right
			shitbox[0]=hitbox.get(i).position.getX();
			shitbox[1]=hitbox.get(i).position.getY();
			shitbox[2]=hitbox.get(i).position.getY()+hitbox.get(i).dimension.getY();
			shitbox[3]=hitbox.get(i).position.getX()+hitbox.get(i).dimension.getX();
			
			if ((sentity[0]>shitbox[0])&&(sentity[0]<shitbox[3]))
				return true;
			if ((sentity[3]>shitbox[0])&&(sentity[3]<shitbox[3]))
				return true;
			if ((sentity[1]>shitbox[1])&&(sentity[1]<shitbox[2]))
				return true;
			if ((sentity[2]>shitbox[1])&&(sentity[2]<shitbox[2]))
				return true;
		}
		return false;
	}
}
