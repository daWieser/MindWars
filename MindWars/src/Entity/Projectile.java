package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Logic.Vector;

public class Projectile extends Entity{

	public Projectile(Vector pos, Vector mov) {
		super(pos, mov, new Vector(10, 10));
		
		this.setFallVelocity(new Vector(0,0));
		// TODO Auto-generated constructor stub
	}

	private BufferedImage img;

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hitBox(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
}
