package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Logic.GameCalculation;
import Logic.Map;
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

			GameCalculation gc= new GameCalculation(new Map(""), this.mindWars.getSettings() );
			GameGraphics gr= new GameGraphics(gc,mindWars); 
			
			this.mindWars.setContentPane(gr);
			this.mindWars.setGameGraphics(gr);
			this.mindWars.setGameCalculation(gc);
			
			
			
		}
		if(arg0.getSource()==this.settings)
		{
			
			
			this.mindWars.getContentPane().removeAll();
			this.mindWars.getContentPane().add(this.mindWars.getSettings());
			this.mindWars.setContentPane(this.mindWars.getSettings());
		
		}
		if(arg0.getSource()==this.exit)
		{
			System.exit(0);
		}
		
	}
}
