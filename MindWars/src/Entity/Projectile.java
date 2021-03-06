package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Logic.Vector;

public class Projectile extends Entity{
	
	
	
	public Projectile(Vector pos, Vector mov) {
		super(pos, mov, new Vector(10, 10));
		
		this.setFallVelocity(new Vector(0,0));
		this.setType(2);
		// TODO Auto-generated constructor stub
	}

	private BufferedImage img;

	@Override
	public void draw(Graphics g,  Vector resrelation) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.drawRect( (int)((position.getX())*resrelation.getX()), (int)((900-position.getY()-dimension.getY())*resrelation.getY()),(int)(dimension.getX() * resrelation.getX()), (int) (dimension.getY() * resrelation.getY()));
	}

	@Override
	public int hitBox(int id) {
		this.setMovement(new Vector(0,0));
		return 1;//delete
	}
}
