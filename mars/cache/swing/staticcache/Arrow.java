package mars.cache.swing.staticcache;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Arrow extends JPanel{
	
	public static final void paintComponent(Graphics g, int x1, int y1, int x2, int y2, boolean ponto)
	{
		int d = 5, h = 5;
		int dx = x2-x1, dy = y2-y1;
		double D = Math.sqrt(dx*dx + dy*dy);
		double xm = D - d, xn = xm, ym = h, yn = -h, x;
		double sin = dy / D, cos = dx / D;
		
		x = xm*cos - ym*sin+x1;
		ym = xm * sin + ym*cos + y1;
		xm = x;
		
		x = xn*cos - yn*sin + x1;
		yn = xn * sin + yn * cos + y1;
		xn = x;
		
		int [] xpoints = {x2, (int) xm, (int) xn};
		int [] ypoints = {y2, (int) ym, (int) yn};
		
		g.drawLine(x1, y1, x2, y2);
		if(ponto == true)
			g.drawOval(x1-1, y1-1, 3, 3);
		g.fillPolygon(xpoints, ypoints, 3);
		
		
	}

}