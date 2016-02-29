package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class Settings extends JPanel implements ActionListener{

	JButton button_FullScreen;
	
	MindWars mindWars;
	
	private boolean fullScreen= true;
	private int windowHeight = 1080;
	private int windowWidth = 1920;
	private int volume = 100;
	
	
	
	Settings(MindWars m)
	{
		this.mindWars=m;

		
		this.setLayout(null);
		this.setSize(500,500);

		this.button_FullScreen=new JButton("d");
		this.button_FullScreen.setBounds(200, 200, 100, 100);
		this.add(this.button_FullScreen);
		
		this.mindWars.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
