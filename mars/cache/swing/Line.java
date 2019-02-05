package mars.cache.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Line extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		g2.setStroke(new BasicStroke(3));
		g2.drawRect(10, 10, 100, 100);
	}
	
}
