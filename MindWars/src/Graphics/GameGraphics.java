package Graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Entity.Entity;
import Logic.GameCalculation;
import Logic.Map;
import Logic.Vector;

public class GameGraphics extends JPanel implements Runnable{
	
	private GameCalculation gameCalc; 
	private ArrayList<Entity> entities;
	private Map map;
	private boolean flag;
	private Vector resrelation;
	private MindWars mindWars;
	//relation between display resolution and logical game resolution (1600 x 900)
	
	
	public GameGraphics(GameCalculation gameCalc, MindWars mindWars)
	{
		this.gameCalc=gameCalc;
		this.mindWars=mindWars;
		resrelation = new Vector(mindWars.getResolution().getX()/1600,mindWars.getResolution().getY()/900);
		this.entities=this.gameCalc.getEntities();
		this.map=this.gameCalc.getMap();
		flag=true;
		Thread t = new Thread(this);
		t.start();
		
		this.setBackground(Color.BLUE);
		
		this.setVisible(true);
		
	}
	
	@Override 
	public void paint(Graphics g) //Paint a Frame of the Game (other relations than 16:9 not yet added)
	{
		g.drawImage(map.getMappic(), 0, 0, (int)mindWars.getResolution().getX(), (int)mindWars.getResolution().getY(), null);
		for(int i=0;i< this.entities.size();i++)
		{
			this.entities.get(i).draw(g, resrelation);
		}
		
	}

	@Override
	public void run() {
		while (true){
			repaint();
		}
		
	}

}
