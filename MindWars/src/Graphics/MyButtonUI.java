package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicButtonUI;

public class MyButtonUI extends BasicButtonUI implements java.io.Serializable, MouseListener, KeyListener {

	private final static MyButtonUI m_buttonUI = new MyButtonUI();

	protected Border m_borderRaised = UIManager.getBorder("Button.border");

	protected Border m_borderLowered = UIManager.getBorder("Button.borderPressed");

	protected Color m_backgroundNormal = UIManager.getColor("Button.background");

	protected Color m_backgroundPressed = UIManager.getColor("Button.pressedBackground");

	protected Color m_foregroundNormal = UIManager.getColor("Button.foreground");

	protected Color m_foregroundActive = UIManager.getColor("Button.activeForeground");

	protected Color m_focusBorder = UIManager.getColor("Button.focusBorder");

	public static ComponentUI createUI(JComponent c) {
		return m_buttonUI;
	}

	public void installUI(JComponent c) {
		super.installUI(c);

		c.addMouseListener(this);
		c.addKeyListener(this);
	}
	
	private ImageIcon currentIcon;
	private ImageIcon pressedIcon, enteredIcon, defaultIcon;
	
	public MyButtonUI(){
		super();
		defaultIcon = new ImageIcon("resources\\character1_0.png");
		enteredIcon = new ImageIcon("resources\\character1_1.png");
		pressedIcon = new ImageIcon("resources\\character2_0.png");
		currentIcon = defaultIcon;
	}
	
	public void uninstallUI(JComponent c) {
		super.uninstallUI(c);
		c.removeMouseListener(this);
		c.removeKeyListener(this);
	}

	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		Dimension d = b.getSize();

		//g.setFont(c.getFont());
		Graphics2D g2d= (Graphics2D) g;
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		
		g2d.setFont(new Font("Arial", Font.PLAIN, 30));
		FontMetrics fm = g2d.getFontMetrics();
		
		//Button Label:
		g2d.setColor(b.getForeground());
		String caption = b.getText();
		int x = (d.width - fm.stringWidth(caption)) / 2; // Text in 
		int y = (d.height + fm.getAscent()) / 2;		 // middle
		g2d.drawString(caption, x, y);
		
		//Button Icon:
		g2d.drawImage(currentIcon.getImage(), 0, 0, currentIcon.getIconWidth(), currentIcon.getIconHeight(), null);

	}

	public Dimension getPreferredSize(JComponent c) {
		Dimension d = super.getPreferredSize(c);
		if (m_borderRaised != null) {
			Insets ins = m_borderRaised.getBorderInsets(c);
			d.setSize(d.width + ins.left + ins.right, d.height + ins.top + ins.bottom);
		}
		return d;
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		c.setBorder(m_borderLowered);
		c.setBackground(m_backgroundPressed);
		currentIcon = pressedIcon;
	}

	public void mouseReleased(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		c.setBorder(m_borderRaised);
		c.setBackground(m_backgroundNormal);
		currentIcon = defaultIcon;
	}

	public void mouseEntered(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		c.setForeground(m_foregroundActive);
		c.repaint();
		currentIcon = enteredIcon;
	}

	public void mouseExited(MouseEvent e) {
		JComponent c = (JComponent) e.getComponent();
		c.setForeground(m_foregroundNormal);
		c.repaint();
		currentIcon = defaultIcon;
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			JComponent c = (JComponent) e.getComponent();
			c.setBorder(m_borderLowered);
			c.setBackground(m_backgroundPressed);
		}
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
			JComponent c = (JComponent) e.getComponent();
			c.setBorder(m_borderRaised);
			c.setBackground(m_backgroundNormal);
		}
	}
}
