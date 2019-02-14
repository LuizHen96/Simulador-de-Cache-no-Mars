package mars.cache.swing.staticcache.components;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Bracket extends JPanel {

	private int xOutput;
	private int yOutput;
	private int x, y, k;
	private boolean inverse;
	
	public Bracket(int x, int y, int k, boolean inverse)
	{
		this.x = x;
		this.y = y;
		this.k = k;
		this.inverse = inverse;
		xOutput = 0;
		yOutput = 0;
	}
	
	
	public final void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	
		if(!inverse) {
			g2.drawLine(x, y, x, y-10);
			g2.drawLine(x, y-10, x+k, y-10);
			g2.drawLine(x+k, y-10, x+k, y);
			g2.drawLine(x+k/2, y-10, x+k/2, y-20);
			xOutput = x+k/2;
			yOutput = y-20;
		}
		else
		{
			g2.drawLine(x, y, x, y+10);
			g2.drawLine(x, y+10, x+k, y+10);
			g2.drawLine(x+k, y+10, x+k, y);
			g2.drawLine(x+k/2, y+10, x+k/2, y+20);
			xOutput = x+k/2;
			yOutput = y+20;
		}
	}


	public int getxOutput() {
		return xOutput;
	}


	public int getyOutput() {
		return yOutput;
	}
	
	
	
}
