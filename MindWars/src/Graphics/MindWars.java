package Graphics;

import Logic.*;
import java.awt.Frame;

import javax.swing.*;

public class MindWars extends JFrame {
	private Menu menu;
	private GameGraphics gameGraphics;
	private GameCalculation gameCalculation;
	private Settings settings;
	private Input input;

	public MindWars(){
		this.setTitle("Milli sux dix");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		
		menu = new Menu(this);
		this.setContentPane(menu);
		this.setUndecorated(true);
		
		this.setVisible(true);
	}
	

	/**
	 * Sets the Frame to menu
	 */	
	public void toMenue()
	{
		this.setContentPane(menu);
	}
	
	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}	
	public static void main(String[] args) {
		new MindWars();
	}
	
	public GameGraphics getGameGraphics() {
		return gameGraphics;
	}

	public void setGameGraphics(GameGraphics gameGraphics) {
		this.gameGraphics = gameGraphics;
	}

	public GameCalculation getGameCalculation() {
		return gameCalculation;
	}

	public void setGameCalculation(GameCalculation gameCalculation) {
		this.gameCalculation = gameCalculation;
	}
	public Input getInput() {
		return input;
	}


	public void setInput(Input input) {
		this.input = input;
	}



}
