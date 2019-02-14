package mars.cache.swing.staticcache.components;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Multiplexer extends JPanel {
		private int nInput;
		private int[] xInput;
		private int[] yInput;
		private int nController;
		private int xOutput;
		private int yOutput;
		private int x, y;
		private int height;
		private int width;
		private int xControler1;
		private int xControler2;
		private int yControler1;
		private int yControler2;
		
	public Multiplexer(int x, int y, int nInput, int nController)
	{
		this.x = x  - (40 + nInput * 12)/2;
		this.y = y;
		this.nInput = nInput;
		
		xInput = new int[nInput];
		yInput = new int[nInput];
		
		width = 40 + nInput * 12;
		height = 30 + nInput/2;
		
		this.nController = nController;
		
	}
	
	

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//g2.drawOval(x, y, 40 + nInput * 10, 30 + nInput);
		g2.drawRoundRect(x, y, width , height, 40, 160);
		
		Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);
		g2.drawString("MUX", x + 5 + nInput * 6 , y + 20 + nInput/4 );
		
		
		for(int i=0; i<nInput; i++)
		{
			g2.drawLine(x + i*12 + 26, y, x + i*12 + 26, y - 15);
			xInput[i] = x + i*12 + 26;
			yInput[i] = y - 10;
		}
		
		
		g2.drawLine(x + 20 + nInput * 6, 30 + nInput/2+y, x + 20 + nInput * 6, 30 + nInput/2 + 30+y);
		xOutput = x + 20 + nInput * 6;
		yOutput = 30 + nInput/2 + 30+y;
		
		if(nController > 1)
		{
			g2.drawLine(x - 30 , y + 5 + nInput /4, x + 3, y +5 + nInput /4);
			g2.drawLine(x - 30 , y + 25 + nInput/4, x + 3, y +25  + nInput/4);
			xControler1 = x - 30 ;
			yControler1 = y + 5 + nInput /4;
			xControler2 = x - 30;
			yControler2 = y + 25 + nInput/4;
			
			
			
			
			if(nController > 2)
			{
				g2.drawOval(x-20, y + 15 + nInput /4, 2, 2);
				g2.drawOval(x-15, y + 15 + nInput /4, 2, 2);
				g2.drawOval(x-10, y + 15 + nInput /4, 2, 2);
				
			}
			
		}
		if(nController == 1)
		{
			g2.drawLine(x - 30 , y + 15 + nInput /4, x, y +15 + nInput /4);
			xControler1 = x - 30 ;
			yControler1 = y + 15 + nInput /4;
		}
				
	}	
	
	public int getnInput() {
		return nInput;
	}
	
	
	
	
	public int getxInput(int i) {
		return xInput[i];
	}
	
	
	
	
	public int getyInput( int i) {
		return yInput[i];
	}
	
	
	
	
	public int getxOutput() {
		return xOutput;
	}
	
	
	
	
	public int getyOutput() {
		return yOutput;
	}
	
	
	
	
	public int getHeight() {
		return height;
	}
	
	
	
	
	public int getWidth() {
		return width;
	}



	public int getxControler1() {
		return xControler1;
	}



	public int getxControler2() {
		return xControler2;
	}



	public int getyControler1() {
		return yControler1;
	}



	public int getyControler2() {
		return yControler2;
	}


}
