package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Logic.GameCalculation;
import Logic.Vector;


public class Menu extends JPanel implements ActionListener{

	private JButton play;
	private JButton levelEditor;
	private JButton settings;
	private JButton exit;
	
	private MindWars mindWars;
	
	public Menu (MindWars mindWars){
		
		this.mindWars=mindWars;
		
		this.setLayout(null);
		
		this.play = new JButton("play");
		this.play.setBounds(0,0,100,100);
		this.play.addActionListener(this);
		this.add(this.play);
		
		this.levelEditor= new JButton("Leveleditor");
		this.levelEditor.setBounds(100,0,100,100);
		this.levelEditor.addActionListener(this);
		this.add(this.levelEditor);
		
		this.settings = new JButton ("Settings");
		this.settings.setBounds(200,0,100,100);
		this.settings.addActionListener(this);
		this.add(this.settings);
		
		this.exit= new JButton("Exit");
		this.exit.setBounds(300,0,100,100);
		this.exit.addActionListener(this);
		this.add(this.exit);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if(arg0.getSource()==this.play)
		{
			
			GameGraphics gr= new GameGraphics(); 
			GameCalculation gc = new GameCalculation();
			
			this.mindWars.setContentPane(gr);
			this.mindWars.setGameGraphics(gr);
			this.mindWars.setGameCalculation(gc);
			
			
		}
		if(arg0.getSource()==this.levelEditor)
		{
			
		}
		if(arg0.getSource()==this.settings)
		{
			Settings s = new Settings(this.mindWars);
			this.mindWars.setSettings(s);
			
			this.mindWars.getContentPane().removeAll();
			this.mindWars.getContentPane().add(s);
			this.mindWars.setContentPane(s);
		
		}
		if(arg0.getSource()==this.exit)
		{
			System.exit(0);
		}
		
	}
}
