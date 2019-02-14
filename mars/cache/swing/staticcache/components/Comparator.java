package mars.cache.swing.staticcache.components;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Comparator extends JPanel{
	

	
	public static final void paintComponent(Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawOval(x,y, 30, 30);
		Font font = new Font("Serif", Font.PLAIN, 30);
		g2.setFont(font);
		g2.drawString("=", x + 7, y + 25);	
		font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);
	}
	
	
	
}
