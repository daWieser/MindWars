package Logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Graphics.Settings;

public class Input implements KeyListener,MouseListener, MouseMotionListener{
	
	private Settings settings;
	private GameCalculation gameCalc;
	
	public Input(Settings settings, GameCalculation gameCalc)
	{
		this.gameCalc=gameCalc;
		this.settings=settings;
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==settings.getKeyMoveLeft())
		{
			this.gameCalc.changeCharacterMovement(1);
		}
		if(arg0.getKeyCode()==settings.getKeyMoveRight())
		{
			this.gameCalc.changeCharacterMovement(-1);

		}
		if(arg0.getKeyCode()==settings.getKeyJump())
		{
			this.gameCalc.characterJump();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==settings.getKeyMoveLeft())
		{
			this.gameCalc.stopCharactermovement();
		}
		if(arg0.getKeyCode()==settings.getKeyMoveRight())
		{
			this.gameCalc.stopCharactermovement();
		}
	
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
