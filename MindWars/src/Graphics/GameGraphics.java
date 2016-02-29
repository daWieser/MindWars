package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Entity.Entity;
import Logic.GameCalculation;

public class GameGraphics extends JPanel{
	
	private GameCalculation gameCalc; 
	private ArrayList<Entity> entities;
	
	
	GameGraphics(GameCalculation gameCalc)
	{
		this.gameCalc=gameCalc;
		
		//this.entities=this.gameCalc.getEntities();
		
		
		this.setBackground(Color.BLACK);
		
		this.setVisible(true);
		
	}
	
	@Override 
	public void paint(Graphics g)
	{
		for(int i=0;i< this.entities.size();i++)
		{
			this.entities.get(i).draw(g);
		}
		
	}
	

}
