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
	private int widthWord;
	
	private static final long serialVersionUID = 1L;
	
	
	public static int WITDTH_WORD = 45;
	public static int WITDTH_W = 30;
	public static final int HEIGHT_ROW = 20;
	
	
	public Word(int x, int y, int number, int nWords)
	{
		this.number = number;
		this.x = x;
		this.y = y;
		if(nWords < 8)
			widthWord = 45;
		else
			widthWord = 30;
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.drawRect(x, y, widthWord, HEIGHT_ROW);
		
		
		/*Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);
		g2.drawString("Word " + number, x+4, y+HEIGHT_ROW-4);*/
	}
	
	public void movimenta(int a, int b)
	{
		x += a;
		y += b;
		this.setBounds(x, y, widthWord, HEIGHT_ROW);
	}
}
