package Graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class Settings extends JPanel implements ActionListener{

	JButton fullScreen;
	
	MindWars mindWars;
	
	Settings(MindWars m)
	{
		this.mindWars=m;
		
		this.setLayout(null);
		
		this.fullScreen=new JButton("d");
		this.fullScreen.setBounds(200, 200, 100, 100);
		this.add(this.fullScreen);
		
		this.mindWars.validate();
		this.mindWars.setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
