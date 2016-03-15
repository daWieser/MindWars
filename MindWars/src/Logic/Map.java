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
						
						if(e.getMovement().getY() < 0){
							e.hitBox(2);
							e.setGrounded(true);
						} else {
							e.hitBox(4);
						}
						e.setPosition(e.getPosition().add(new Vector(0, ydiff)));
					}
					else{
						if (e.getMovement().getX() < 0)
							e.hitBox(1); //left
						else
							e.hitBox(3); //right
						e.setPosition(e.getPosition().add(new Vector(xdiff, 0)));
					}
				}
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
