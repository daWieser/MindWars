package Logic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Graphics.InputListener;
import Graphics.Settings;

public class Input implements KeyListener,MouseListener, MouseMotionListener{
	
	private Settings settings;
	//private boolean left, right, jump, down;
	
	private InputListener inputListener;
	
	
	public Input(Settings settings)
	{
		this.settings=settings;
		this.inputListener = null;
		
	}

	public void setInputListener(InputListener inputListener) {
		this.inputListener = inputListener;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(this.inputListener ==  null) return;
		
		if(arg0.getKeyCode()==settings.getKeyMoveLeft())
		{
			
			this.inputListener.left(true);
		}
		else if(arg0.getKeyCode()==settings.getKeyMoveRight())
		{
			
			this.inputListener.right(true);
		}
		else if(arg0.getKeyCode()==settings.getKeyUp())
		{
			this.inputListener.up(true);
		}
		else if(arg0.getKeyCode() == settings.getKeyDown()){
			this.inputListener.down(true);
		}
		else{
			this.inputListener.key(true, arg0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(this.inputListener ==  null) return;
		
		if(arg0.getKeyCode()==settings.getKeyMoveLeft())
		{
			this.inputListener.left(false);
		}
		else if(arg0.getKeyCode()==settings.getKeyMoveRight())
		{
			this.inputListener.right(false);
		}
		else if(arg0.getKeyCode()==settings.getKeyUp())
		{
			this.inputListener.up(false);
		}
		else if(arg0.getKeyCode() == settings.getKeyDown()){
			this.inputListener.down(false);
		}
		else{
			this.inputListener.key(false, arg0);
		}
	
	}
	/*
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
	*/
	
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
