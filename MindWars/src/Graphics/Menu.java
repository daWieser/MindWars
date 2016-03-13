package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import Logic.GameCalculation;
import Logic.Map;
import Logic.Vector;


public class Menu extends JPanel implements ActionListener, InputListener{

	private JButton play;
	private JButton levelEditor;
	private JButton settings;
	private JButton exit;
	private MindWars mindWars;
	private MenuListener menuListener;
	
	
	public Menu (MenuListener mL){
		
		this.menuListener = mL;
		
		this.setLayout(null);
		
		
		
		this.play = new JButton("Play");
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

			this.menuListener.play();
			
			
			
		}
		else if(arg0.getSource()==this.settings)
		{
			
			
			this.menuListener.leveleditor();
		
		}
		else if(arg0.getSource()==this.exit)
		{
			this.menuListener.exit();
		}
		
	}
	
	

	@Override
	public void left(boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void right(boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void down(boolean status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void key(boolean status, KeyEvent k) {
		System.out.println("2");
		
	}

	@Override
	public void up(boolean status) {
		// TODO Auto-generated method stub
		
	}
	
}
