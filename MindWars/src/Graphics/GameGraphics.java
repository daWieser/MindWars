package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Entity.Entity;
import Logic.GameCalculation;
import Logic.Map;

public class GameGraphics extends JPanel implements Runnable{
	
	private GameCalculation gameCalc; 
	private ArrayList<Entity> entities;
	private Map map;
	private boolean flag;
	
	
	
	public GameGraphics(GameCalculation gameCalc)
	{
		this.gameCalc=gameCalc;
		
		this.entities=this.gameCalc.getEntities();
		this.map=this.gameCalc.getMap();
		flag=true;
		Thread t = new Thread(this);
		t.start();
		
		this.setBackground(Color.BLUE);
		
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

	@Override
	public void run() {
		while (true){
			repaint();
		}
		
	}

}
