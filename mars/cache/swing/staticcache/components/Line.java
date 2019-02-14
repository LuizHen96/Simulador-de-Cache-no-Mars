package mars.cache.swing.staticcache.components;
//package mars.cache.swing;

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

	
	public static final void paintComponent(Graphics g, int x1, int y1, int x2, int y2) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawOval(x1-1, y1-1, 3, 3);
		g2.drawLine(x1, y1, x2, y2);
	}
	
}
