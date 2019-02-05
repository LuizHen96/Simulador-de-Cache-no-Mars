package mars.cache.swing.staticcache;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Set extends JPanel {

	private Block[] blocks;
	private int nBlocks;
	private int nWords;
	private int x, y;

	
	static final int HEIGHT_NAMES = 50; 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	public Set(int nBlocks, int nWords, int x, int y)
	{
		this.nWords = nWords;
		this.nBlocks = nBlocks;
		this.x = x;
		this.y = y;
		
		blocks = new Block[nBlocks];
		for(int i=0; i<nBlocks; i++)
		{
			blocks[i] = new Block(nWords, x, (Word.HEIGHT_ROW*i)+y+HEIGHT_NAMES);
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;



		for(int i =0; i<nBlocks; i++)
		{
			blocks[i].paintComponent(g2);
		}

		g.drawArc(Block.WITDTH_V + Block.WITDTH_TAG +x,HEIGHT_NAMES +y, Word.WITDTH_WORD*nWords, 10, 0, 0);
		
		
		Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);
		g2.drawString("v", 5 +x, HEIGHT_NAMES-5+y);
		g2.drawString("tag",Block.WITDTH_TAG/2 +10 +x, HEIGHT_NAMES-5+y);
		
		//CurlyBraces.paintComponent(g2, Block.WITDTH_V + Block.WITDTH_TAG + x, y+40-10, Word.WITDTH_WORD * nWords-15);
		Bracket bracket1 = new Bracket(Block.WITDTH_V + Block.WITDTH_TAG + x, y+35, Word.WITDTH_WORD * nWords, false);
		bracket1.paintComponent(g2);
		g2.drawString("block", Block.WITDTH_V + Block.WITDTH_TAG + x+ (Word.WITDTH_WORD * nWords)/2-23+5, y+10);
		//CurlyBraces.paintComponent(g2, 100, 100, 100);
		//g2.drawLine(100, 100, 200, 100);
		for(int i=0; i<nWords; i++)
		{
			g2.drawString("word" + i, Block.WITDTH_V + Block.WITDTH_TAG + Word.WITDTH_WORD*i +5+x , HEIGHT_NAMES-5+y);
		}
		
		
		this.setBounds(x, y, (nWords*Word.WITDTH_WORD)+Block.WITDTH_M + Block.WITDTH_V+ Block.WITDTH_TAG, Word.HEIGHT_ROW*nBlocks+HEIGHT_NAMES);

	}
	
	
	
	
}
