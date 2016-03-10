package Graphics;

import Logic.*;
import java.awt.Frame;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MindWars extends JFrame implements MenuListener{
	private Menu menu;
	private GameGraphics gameGraphics;
	private GameCalculation gameCalculation;
	private Settings settings;
	private Input input;
	private Vector resolution;

	public MindWars(){
		this.setTitle("Milli sux dix");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		settings = new Settings(this);
		
		
		input = new Input(settings);
		
		resolution = new Vector(this.getSize().getWidth(),this.getSize().getHeight());
		//Auflösungsvektor für Gamegraphics
		this.addKeyListener(input);
		this.getContentPane().addMouseListener(input);
		
		menu = new Menu(this);
		
		
		this.setContentPane(menu);
		
		input.setInputListener(menu);
		
		//this.setUndecorated(true);
		
		this.setVisible(true);
	}
	

	public Vector getResolution() {
		return resolution;
	}


	public void setResolution(Vector resolution) {
		this.resolution = resolution;
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


	@Override
	public void play() {
		GameCalculation gc= new GameCalculation(new Map(""));
		GameGraphics gr= new GameGraphics(gc,this);
		this.getInput().setInputListener(gc);
		gr.addKeyListener(this.getInput());
		gr.addMouseListener(this.getInput());
		
		this.setContentPane(gr);
		this.setGameGraphics(gr);
		this.setGameCalculation(gc);
		
	}


	@Override
	public void leveleditor() {
		this.getContentPane().removeAll();
		this.getContentPane().add(this.getSettings());
		this.setContentPane(this.getSettings());
	}


	@Override
	public void settings() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void exit() {
		System.exit(0);
	}
	
	
	
	

	


	



}
