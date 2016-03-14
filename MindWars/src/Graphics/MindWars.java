package Graphics;

import Logic.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class MindWars extends JFrame implements MenuListener{
	private Menu menu;
	private GameGraphics gameGraphics;
	private GameCalculation gameCalculation;
	private Settings settings;
	private Input input;
	private Vector resolution;
	private Map map;

	public Map getMap() {
		return map;
	}


	public void setMap(Map map) {
		this.map = map;
	}


	public MindWars(){
		this.setTitle("Milli sux dix");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		//this.setUndecorated(true);
		
		this.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent arg0) {}
			public void windowClosed(WindowEvent arg0) {}
			public void windowClosing(WindowEvent arg0) {
				exit();
			}
			public void windowDeactivated(WindowEvent arg0) {}
			public void windowDeiconified(WindowEvent arg0) {}
			public void windowIconified(WindowEvent arg0) {}
			public void windowOpened(WindowEvent arg0) {}
		});
		
		this.settings = new Settings(this);
		
		
		this.input = new Input(settings);
		
		//Auflösungsvektor für Gamegraphics
		this.addKeyListener(input);
		this.addMouseListener(input);
		
		this.menu = new Menu(this);
		this.menu.setBackground(Color.WHITE);
		
		this.map = new Map("Map");
		
		this.getContentPane().add(menu);
		this.input.setInputListener(menu);
		//play();
		
		this.gameGraphics = null;
		this.gameCalculation = null;
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.resolution = new Vector(dim.getWidth(), dim.getHeight());//this.getSize().getWidth(),this.getSize().getHeight()); //des ged nimme xD
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
		this.stop();
		this.getContentPane().removeAll();
		this.getContentPane().add(menu);
		this.getInput().setInputListener(menu);
		update();
		
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
		this.getContentPane().removeAll();
		this.getContentPane().add(gameGraphics);
		update();
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
		GameCalculation gc = new GameCalculation(this);
		this.setGameCalculation(gc);
		this.getInput().setInputListener(gc);
		
		GameGraphics gr= new GameGraphics(this);
		/*gr.addKeyListener(this.getInput());
		gr.addMouseListener(this.getInput());*/
		this.setGameGraphics(gr);
	}


	@Override
	public void leveleditor() {
		//this.getContentPane().removeAll();
		//this.getContentPane().add(this.getSettings());
		this.setContentPane(this.getSettings());
	}


	@Override
	public void settings() {
		// TODO Auto-generated method stub
		
	}

	public void stop(){
		if(this.getGameGraphics() != null) this.getGameGraphics().stop();
		if(this.getGameCalculation() != null) this.getGameCalculation().stop();
		
	}
	
	@Override
	public void exit() {
		this.stop();
		System.exit(0);
	}
	
	
	private void update(){
		//this.pack();
		this.validate();
		this.repaint();
	}
	

	


	



}
