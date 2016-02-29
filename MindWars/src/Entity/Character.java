package Entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Logic.Vector;

public class Character extends Entity{

	private BufferedImage head;
	private BufferedImage body;
	
	public Character (Vector pos){
		super(pos, new Vector(0,0));
		
	}
	
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int hitBox(int id) {
		
		return 0;
	}

}
