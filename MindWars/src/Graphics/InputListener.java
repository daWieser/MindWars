package Graphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Logic.Vector;

public interface InputListener {
	public void left(boolean status);
	public void right(boolean status);
	public void up(boolean status);
	public void down(boolean status);
	public void key(boolean status, KeyEvent k);
	public void mousePressed(Vector me);
}
