package mars.cache.swing.staticcache.components;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class And extends JPanel{
	private int nInput;
	private int x, y;
	private int[] xInput;
	private int[] yInput;
	private int xOutput;
	private int yOutput;
	
	public And(int x, int y, int nInput)
	{
		this.x = x;
		this.y = y;
		this.nInput = nInput;
		
		xInput = new int[nInput];
		yInput = new int[nInput];
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		
		g2.drawArc(x, y, 40, 40, 180, 180);
		g2.drawLine(x, y+20, x+40, y+20);
		g2.drawLine(x+10, y+20, x+10, y);
		g2.drawLine(x+30, y+20, x+30, y);
		g2.drawLine(x+20, y+40, x+20, y+60);
		
		xInput[0] = x+10;
		yInput[0] = y;
		xInput[1] = x+30 ;
		yInput[1] = y;
		
		xOutput = x+20;
		yOutput = y+60;
	}

	
	public int getxInput(int i) {
		return xInput[i];
	}

	public int getyInput(int i) {
		return yInput[i];
	}

	public int getxOutput() {
		return xOutput;
	}

	public int getyOutput() {
		return yOutput;
	}
	
}
