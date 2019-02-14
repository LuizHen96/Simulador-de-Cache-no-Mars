package mars.cache.swing.staticcache;
//package mars.cache.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Block extends JPanel {
	
	
	private Word[] words;
	private int nWords;
	private int x, y;
	private int widthWord;
	
	
	public static final int HEIGHT_ROW = Word.HEIGHT_ROW;
	static final int WITDTH_V = 20;
	static final int WITDTH_M = 0;
	static final int WITDTH_TAG = 50;
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Block(int n, int x, int y) {
		this.x = x;
		this.y = y;
		nWords = n;
		
		if(nWords < 8)
			widthWord = Word.WITDTH_WORD;
		else
			widthWord = Word.WITDTH_W;
		
		words = new Word[n];
		for(int i=0; i<n; i++)
		{
			words[i] = new Word((WITDTH_V + WITDTH_M + Block.WITDTH_TAG+(i*widthWord))+x, y, i, nWords);
		}
		
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		
		
		g2.drawRect(x, y, WITDTH_V, HEIGHT_ROW);
		g2.drawRect(x+WITDTH_V, y, WITDTH_M, HEIGHT_ROW);
		g2.drawRect(x+WITDTH_V+WITDTH_M, y, WITDTH_TAG, HEIGHT_ROW);
		
		
		Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);
		//g2.drawString("V", x+4, y+HEIGHT_ROW-4);
		//g2.drawString("M", x+4+WITDTH_V, y+HEIGHT_ROW-4);
		//g2.drawString("TAG", x+4+WITDTH_V+WITDTH_M, y+HEIGHT_ROW-4);
		
		


		for(int i =0; i<nWords; i++)
		{
			words[i].paintComponent(g);
		}
		
		this.setBounds(x, y, (nWords*widthWord)+WITDTH_V + WITDTH_M + WITDTH_TAG, Word.HEIGHT_ROW);

	}

}
