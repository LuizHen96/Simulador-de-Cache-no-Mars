package mars.cache.swing.dinamiccache;
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
	private String value;
	
	private static final long serialVersionUID = 1L;
	
	
	public static int WITDTH_WORD = 60;
	public static int WITDTH_W = 60;
	public static final int HEIGHT_ROW = 20;
	
	
	public Word(int x, int y, int number, String value)
	{
		this.number = number;
		this.x = x;
		this.y = y;
		this.value = value;
	}

	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		g2.drawRect(x, y, WITDTH_WORD, HEIGHT_ROW);
		g2.drawString(value, x+5,y+15);
	}
	
	public void movimenta(int a, int b)
	{
		x += a;
		y += b;
		this.setBounds(x, y, widthWord, HEIGHT_ROW);
	}
}
