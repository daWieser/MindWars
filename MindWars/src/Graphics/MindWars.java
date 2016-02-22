package Graphics;

import Logic.*;
import java.awt.Frame;

import javax.swing.*;

public class MindWars extends JFrame {
	private Menu menu;
	private GameGraphics gameGraphics;
	private GameCalculation gameCalculation;

	public static void main(String[] args) {
		new MindWars();
	}
	
	public MindWars(){
		this.setTitle("Milli sux dix");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		
		menu = new Menu(this);
		this.setContentPane(menu);
		this.setUndecorated(true);
		
		this.setVisible(true);
	}

}
