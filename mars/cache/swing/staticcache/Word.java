package mars.cache.swing.staticcache;
//package mars.cache.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Word extends JPanel{

	private int x;
	private int y;
	private int number;
	
	private static final long serialVersionUID = 1L;
	
	
	static final int WITDTH_WORD = 45;
	static final int HEIGHT_ROW = 20;
	
	
	public Word(int x, int y, int number)
	{
		this.number = number;
		this.x = x;
		this.y = y;
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.drawRect(x, y, WITDTH_WORD, HEIGHT_ROW);
		
		
		/*Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);
		g2.drawString("Word " + number, x+4, y+HEIGHT_ROW-4);*/
	}
	
	public void movimenta(int a, int b)
	{
		x += a;
		y += b;
		this.setBounds(x, y, WITDTH_WORD, HEIGHT_ROW);
	}
}
