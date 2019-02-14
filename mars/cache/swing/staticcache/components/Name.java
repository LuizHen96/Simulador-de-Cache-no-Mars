package mars.cache.swing.staticcache.components;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Name extends JPanel{

	
	public static final void paintComponent(Graphics g, String name, int x, int y, boolean left) {
		Graphics2D g2 = (Graphics2D) g;
	
		if(!left)
		{	
			g2.drawLine(x-6, y+5, x+8, y-10);
			g2.drawString(name, x + 15, y);
		}
		else {
			g2.drawLine(x-6, y+5, x+8, y-10);
			g2.drawString(name, x - 35, y);
		}
		
	
	}
	
	
	
	
	
	
	
	
	
}
