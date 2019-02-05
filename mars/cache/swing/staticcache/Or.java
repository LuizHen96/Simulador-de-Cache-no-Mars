package mars.cache.swing.staticcache;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.CubicCurve2D;

import javax.swing.JPanel;

public class Or extends JPanel{
		private int nInput;
		private int[] xInput;
		private int[] yInput;
		private int xOutput;
		private int yOutput;
		private int x, y;
		private int height;
		private int width;
	
	public Or(int x, int y, int nInput)
	{
		this.x = x;
		this.y = y;
		this.nInput = nInput;
		
		xInput = new int[nInput];
		yInput = new int[nInput];
		
		
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		
		CubicCurve2D shape = new CubicCurve2D.Float();
		CubicCurve2D shape2 = new CubicCurve2D.Float();
		CubicCurve2D shape3 = new CubicCurve2D.Float();
		int modx = nInput * 0;
		int mody = nInput * 0;
		
		shape.setCurve(x, y, x, y+20+mody, x+50+modx, y+20, x+50+modx, y);
		shape2.setCurve(x, y, x, y+20+mody, x, y+40+mody*2, x+25 + modx/2, y+60+mody*3);
		shape3.setCurve(x+50 + modx, y, x+50 + modx, y+20+mody, x+50 + modx, y+40+mody*2, x+25 + modx/2, y+60+mody*3);

		
		
		g2.draw(shape);
		g2.draw(shape2);
		g2.draw(shape3);

		if(nInput == 2)
		{
			g2.drawString("hit0", x, y-25);
			g2.drawString("hit1", x+30, y-25);
			
			g2.drawLine(x+10, y+12, x+10, y-20);
			g2.drawLine(x+40, y+12, x+40, y-20);
		}
		
		/*if(nInput == 4)
		{
			g2.drawString( "hit0",  x, y-25);
			g2.drawString( "hit3", x+30, y-25);
			
			g2.drawLine(x+10, y+12, x+10, y-20);
			g2.drawLine(x+20, y+15, x+20, y-20);
			g2.drawLine(x+30, y+15, x+30, y-20);
			g2.drawLine(x+40, y+12, x+40, y-20);
		}*/
		if(nInput > 2)
		{
			g2.drawLine(x+10, y+12, x+10, y-20);
			g2.drawLine(x+40, y+12, x+40, y-20);
			
			g2.drawOval(x+20, y, 2, 2);
			g2.drawOval(x+25, y, 2, 2);
			g2.drawOval(x+30, y, 2, 2);
			
			g2.drawString("hit0",  x, y-25);
			g2.drawString("hit"+(nInput-1), x+30, y-25);

		}
		
		g2.drawLine(x+25, y+60, x+25, y+80);
		g2.drawString("hit", x+20, y+95);
		
		
	}
	

}
