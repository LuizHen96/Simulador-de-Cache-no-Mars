package mars.cache.swing.staticcache;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Decoder extends JPanel {
	
	public static final void paintComponent(Graphics g, int x, int y, int nIndex, int x1, int y1) {
		Graphics2D g2 = (Graphics2D) g;
		int space = 0;
		boolean change = false;
		
		g2.drawRect(x, y, 40, 10+nIndex*Word.HEIGHT_ROW /2);
		g2.drawString("dec.", x+5, y +5+ nIndex*Word.HEIGHT_ROW /4);
		
		
		
		for(int i= 0; i<nIndex; i++)
		{
			
			int px, py;
			
			
			px = x+40;
			py = y+10+10*i;
			
			if((py < (y1 + Block.HEIGHT_ROW*i)) && change  )
			{
				space -= 5;
			}
			else if (py >= y1 + Block.HEIGHT_ROW*i && !change)
			{
				space += 5;
			}
			else
			{
				change = true;
			}
			
			
			if(py != y1 + Block.HEIGHT_ROW*i )
			{
				g2.drawLine(px, py, px+5+space, py);
				g2.drawLine(px+5+space, py, px+5+space, y1 + Block.HEIGHT_ROW*i);
				g2.drawLine(px+5+space, y1 + Block.HEIGHT_ROW*i, x1, y1 + Block.HEIGHT_ROW*i);
			}
			else {
				g2.drawLine(px, py, x1, y1 + Block.HEIGHT_ROW*i);
			}			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
