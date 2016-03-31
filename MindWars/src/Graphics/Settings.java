package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.sun.glass.events.KeyEvent;

public class Settings extends JPanel implements ActionListener{

	JButton button_FullScreen;
	
	MindWars mindWars;
	
	private boolean fullScreen= true;
	private int windowHeight = 1080;
	private int windowWidth = 1920;
	private int volume = 100;
	
	private int key_moveLeft=KeyEvent.VK_A;
	private int key_moveRight= KeyEvent.VK_D;
	private int key_moveUp= KeyEvent.VK_SPACE;
	private int key_moveDown= KeyEvent.VK_S;
	
	
	private int ticks;
	
	
	
	
	public int getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	public Settings(MindWars m)
	{
		this.mindWars=m;

		
		this.setLayout(null);
		this.setSize(500,500);
		
		this.ticks = 60;
		//Times a Frame refreshes in one second

		this.button_FullScreen=new JButton("d"); //fost sche
		this.button_FullScreen.setBounds(200, 200, 100, 100);
		this.add(this.button_FullScreen);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int getKeyMoveLeft()
	{
		return this.key_moveLeft;
	}
	public int getKeyMoveRight()
	{
		return this.key_moveRight;
	}
	public int getKeyUp()
	{
		return this.key_moveUp;
	}
	
	public int getKeyDown()
	{
		return this.key_moveDown;
	}
}
