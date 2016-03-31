package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.*;

import Logic.GameCalculation;
import Logic.Map;
import Logic.Vector;


public class Menu extends JPanel implements InputListener{

	
	private ActionListener actionListener;
	
	private int x, y;
	
	
	public Menu (ActionListener aL){
		
		this.setLayout(null);
		this.actionListener = aL;
		
		
		
		
		
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
		
	}

	@Override
	public void up(boolean status) {
		// TODO Auto-generated method stub
		
	}
	
	public void addJButton(JButton button){
		button.addActionListener(actionListener);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
		button.setUI(new MyButtonUI());
		this.add(button);
	}





	@Override
	public void mousePressed(Vector me) {
		// TODO Auto-generated method stub
		
	}
	
}
