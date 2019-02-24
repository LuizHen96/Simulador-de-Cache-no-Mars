package mars.cache.swing.dinamiccache;
//package mars.cache.swing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import mars.cache.statistic.BlockStatistic;

public class Block extends JPanel {
	
	
	private Word[] words;
	private int nWords;
	private int x, y;
	private int widthWord;
	private String[] values;
	private int v;
	private int tag;
	
	
	public static final int HEIGHT_ROW = Word.HEIGHT_ROW;
	static final int WITDTH_V = 20;
	static final int WITDTH_M = 0;
	static final int WITDTH_TAG = 80;
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Block(int n, int x, int y, String[] values, BlockStatistic block) {
		this.x = x;
		this.y = y;
		nWords = n;
		this.v= block.getValidate();
		this.tag = block.getTag();
		
		if(nWords < 8)
			widthWord = Word.WITDTH_WORD;
		else
			widthWord = Word.WITDTH_W;
		
		words = new Word[n];
		this.values = values;
		
		for(int i=0; i<n; i++)
		{
			words[i] = new Word((WITDTH_V + WITDTH_M + Block.WITDTH_TAG+(i*widthWord))+x, y, i, values[i]);
		}
		
		
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.black);
		
		
		g2.drawRect(x, y, WITDTH_V, HEIGHT_ROW);
		g2.drawString( v + "" ,x+5, y+15);
		g2.drawRect(x+WITDTH_V, y, WITDTH_M, HEIGHT_ROW);
		
		g2.drawRect(x+WITDTH_V+WITDTH_M, y, WITDTH_TAG, HEIGHT_ROW);
		g2.drawString( tag + "" ,x+WITDTH_V+WITDTH_M+5, y+15);
		
		
		Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);

		
		


		for(int i =0; i<nWords; i++)
		{
			words[i].paintComponent(g);
		}
		
		this.setBounds(x, y, (nWords*widthWord)+WITDTH_V + WITDTH_M + WITDTH_TAG, Word.HEIGHT_ROW);

	}

}
