package Logic;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

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
	private BufferedImage mappic;
	
	public BufferedImage getMappic() {
		return mappic;
	}

	public void setMappic(BufferedImage mappic) {
		this.mappic = mappic;
	}

	public Map (String name){
		
		a_gravitation = new Vector (0,-2);
		hitbox = new ArrayList<rect>();
		try {
			mappic=ImageIO.read (new File("resources/" + name + ".png"));
		} catch (IOException e) {
			mappic = null;
			System.out.println("Map Picture loading error");
		}
		//test
		hitbox.add(new rect(new Vector(1300,0), new Vector(300,300)));
		hitbox.add(new rect(new Vector(400,300), new Vector(600,150)));
		hitbox.add(new rect(new Vector(0,550), new Vector(200,100)));
		hitbox.add(new rect(new Vector(500,700), new Vector(500,50)));
		
	}
	
	/**
	 * 
	 * @param e: Entity to check
	 * @return e: optimized entity
	 */
	public Entity checkHitbox (Entity e){
				
		for (int i=0;i<hitbox.size();i++){
			Vector pos = new Vector(e.getPosition());
			Vector dim = new Vector(e.getDimension());
			double eleft = pos.getX();
			double ebottom = pos.getY();
			double eright = pos.getX() + dim.getX();
			double etop = pos.getY() + dim.getY();

			double hleft =hitbox.get(i).position.getX();
			double hbottom =hitbox.get(i).position.getY();
			double htop =hitbox.get(i).position.getY()+hitbox.get(i).dimension.getY();
			double hright =hitbox.get(i).position.getX()+hitbox.get(i).dimension.getX();
			
			double xdiff=0, ydiff = 0 ;
			if((ebottom > hbottom && ebottom < htop) || (etop > hbottom && etop < htop)){
				/*if((eleft > hright && eright < hleft)){ //collision right of entity
					System.out.println(e.getPosition());
					if (e.getMovement().getX()>0){
						diff = hleft - eright;
					} else {
						diff = hright - eleft;
					}
					e.hitBox(1);
					System.out.println(diff);
					e.setPosition(e.getPosition().add(new Vector(diff, 0)));
				}*/
				if((eleft < hright && eleft > hleft) || (eright < hright && eright > hleft)){
					if (e.getMovement().getY()>0){
						ydiff = hbottom-etop;
					}else{
						ydiff = htop-ebottom;
					}
					
					if (e.getMovement().getX()>0){
						xdiff = hleft - eright;
					} else {
						xdiff = hright - eleft;
					}
					
					
					
					
					if(Math.abs(ydiff) < Math.abs(xdiff)){ //differenzieren zw. vertikaler und horizontaler überschneidung; bei dünner sochn kritisch
						
						if(e.getMovement().getY() < 0) e.hitBox(2);
						else e.hitBox(4);
						e.setPosition(e.getPosition().add(new Vector(0, ydiff)));
					}
					else{
						e.setPosition(e.getPosition().add(new Vector(xdiff, 0)));
						e.hitBox(1);
					}
				}
			}
			/*else if(eleft > hleft && eright < hright){
				//if(!(hbottom > etop || ebottom > htop)){ //collision bottom of entity
				if(hbottom < etop && ebottom < htop){
					if (e.getMovement().getY()>0){
						diff = hbottom-etop;
					}else{
						diff = htop-ebottom;
					}
					
					
					
					e.hitBox(4);
					System.out.println(diff);
					e.setPosition(e.getPosition().add(new Vector(0, diff)));
				}
			}*/
			
		}
		return e;
	}
	
	/*public boolean checkHitbox (Vector pos, Vector dim){
		double []sentity = new double[4]; //distance between side of entity and origin of map
		//0=left 1=bottom 2=top 3=right
		sentity[0]=pos.getX();
		sentity[1]=pos.getY();
		sentity[2]=pos.getY()+dim.getY();
		sentity[3]=pos.getX()+dim.getX();
		
		for (int i=0;i<hitbox.size();i++){
			if (pos.getX()+dim.getX()>hitbox.get(i).position.getX()){
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
 		}
 		return false;
 	}*/
	
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
