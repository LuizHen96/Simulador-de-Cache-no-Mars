package mars.cache.swing.dinamiccache;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import mars.cache.statistic.SetStatistic;
import mars.cache.statistic.StatisticCache;
import mars.cache.swing.staticcache.components.Bracket;

public class Set extends JPanel {

	private Block[] blocks;
	private int nBlocks;
	private int nWords;
	private int widthWord;
	private int x, y;
	private String[][] values;

	
	static final int HEIGHT_NAMES = 50; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	public Set(int nBlocks, int nWords, int x, int y, String[][] values, SetStatistic set)
	{
		this.nWords = nWords;
		this.nBlocks = nBlocks;
		this.x = x;
		this.y = y;
		
		blocks = new Block[nBlocks];
		for(int i=0; i<nBlocks; i++)
		{
			blocks[i] = new Block(nWords, x, (Word.HEIGHT_ROW*i)+y+HEIGHT_NAMES, values[i], set.getBlocks()[i]);
		}
		
		if(nWords < 8)
			widthWord = Word.WITDTH_WORD;
		else
			widthWord = Word.WITDTH_W;
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;



		for(int i =0; i<nBlocks; i++)
		{
			blocks[i].paintComponent(g2);
		}

		g.drawArc(Block.WITDTH_V + Block.WITDTH_TAG +x,HEIGHT_NAMES +y, widthWord*nWords, 10, 0, 0);
		
		
		Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);
		g2.drawString("v", 5 +x, HEIGHT_NAMES-5+y);
		g2.drawString("tag",Block.WITDTH_TAG/2 +10 +x, HEIGHT_NAMES-5+y);
		
		//CurlyBraces.paintComponent(g2, Block.WITDTH_V + Block.WITDTH_TAG + x, y+40-10, Word.WITDTH_WORD * nWords-15);
		Bracket bracket1 = new Bracket(Block.WITDTH_V + Block.WITDTH_TAG + x, y+35, widthWord * nWords, false);
		bracket1.paintComponent(g2);
		g2.drawString("block", Block.WITDTH_V + Block.WITDTH_TAG + x+ (widthWord * nWords)/2-23+5, y+10);
		//CurlyBraces.paintComponent(g2, 100, 100, 100);
		//g2.drawLine(100, 100, 200, 100);
		for(int i=0; i<nWords; i++)
		{
			if(nWords < 8)
				g2.drawString("word" + i, Block.WITDTH_V + Block.WITDTH_TAG + widthWord*i +5+x , HEIGHT_NAMES-5+y);
			else
				g2.drawString("w" + i, Block.WITDTH_V + Block.WITDTH_TAG + widthWord*i +5+x , HEIGHT_NAMES-5+y);
			
		}
		
		
		this.setBounds(x, y, (nWords*widthWord)+Block.WITDTH_M + Block.WITDTH_V+ Block.WITDTH_TAG, Word.HEIGHT_ROW*nBlocks+HEIGHT_NAMES);

	}
	
	
	
	
}
