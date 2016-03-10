package Logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Graphics.Settings;

public class Input implements KeyListener,MouseListener, MouseMotionListener{
	
	private Settings settings;
	private boolean left, right, jump, down;
	
	
	public Input(Settings settings)
	{
		this.settings=settings;
		left=false;
		right=false;
		jump=false;
		down=false;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==settings.getKeyMoveLeft())
		{
			left=true;
		}
		if(arg0.getKeyCode()==settings.getKeyMoveRight())
		{
			right=true;

		}
		if(arg0.getKeyCode()==settings.getKeyJump())
		{
			jump=true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==settings.getKeyMoveLeft())
		{
			left=false;
		}
		if(arg0.getKeyCode()==settings.getKeyMoveRight())
		{
			right=false;
		}
		if(arg0.getKeyCode()==settings.getKeyJump())
		{
			jump=false;
		}
	
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
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
