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
	
	public void checkHitbox (Vector pos){
		
	}
}
