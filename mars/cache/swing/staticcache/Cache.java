package mars.cache.swing.staticcache;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.CubicCurve2D;

import javax.swing.JPanel;

import mars.cache.swing.staticcache.components.And;
import mars.cache.swing.staticcache.components.Arrow;
import mars.cache.swing.staticcache.components.Bracket;
import mars.cache.swing.staticcache.components.Comparator;
import mars.cache.swing.staticcache.components.Decoder;
import mars.cache.swing.staticcache.components.Line;
import mars.cache.swing.staticcache.components.Multiplexer;
import mars.cache.swing.staticcache.components.Name;
import mars.cache.swing.staticcache.components.Or;

public class Cache extends JPanel{
	
	private Set[] sets;
	private int nSets;
	private int nBlocks;
	private int nWords;
	private int x, y;
	private int dimensionX;
	private int dimensionY;
	private Bracket bracket;
	private int widthWord;
	
	static final int WIDTH_SPACING = 20;
	static final int WITDH_INDEX = 30;
	static int INDEX = 100;
	static final int HEIGHT_ROW = Word.HEIGHT_ROW;
	static final int LINE_TAG = 60;
	static final int HEIGHT_NAMES = 50; 
	static int HEIGHT_SET;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public Cache(int nSets, int nBlocks, int nWords, int x, int y)
	{
		this.nSets = nSets;
		this.nBlocks = nBlocks/nSets;
		this.nWords = nWords;
		this.x = x;
		this.y = y;
		
		
		if(nWords < 8)
			widthWord = Word.WITDTH_WORD;
		else
			widthWord = Word.WITDTH_W;
		
		sets = new Set[nSets];
		HEIGHT_SET = (Block.WITDTH_M + Block.WITDTH_V + Block.WITDTH_TAG +nWords*widthWord+WIDTH_SPACING);
		INDEX = 140 + this.nBlocks*3;
		for(int i=0; i<nSets; i++)
		{
			sets[i] = new Set(this.nBlocks, nWords, (HEIGHT_SET*i)+WITDH_INDEX+ INDEX, HEIGHT_NAMES);
		}
		
		
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1.7f));
		int space = 0;
		boolean change = false;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Drawn all sets	
		for(int i =0; i<nSets; i++)
		{
			sets[i].paintComponent(g);
		}
		
		int tagPointX = Block.WITDTH_TAG/2 + Block.WITDTH_V + Block.WITDTH_M + WITDH_INDEX + INDEX;
				
		
		//this.add(brace);
		
		//brace.paintComponent(g2, 100, 100, 100);
		
		//g2.drawLine(15*x, 10*x, 19*x, 22*x);
		//g2.drawLine(15*x, 10*x, 20*x, 9*x);
		

		
		
		
		// Draw Line Tag
		int endBlockPointY = HEIGHT_ROW*nBlocks + Set.HEIGHT_NAMES + HEIGHT_NAMES;
		
		g2.drawString("tag", 10, endBlockPointY + LINE_TAG/2+5);
		g2.drawLine(40, 
				endBlockPointY + LINE_TAG/2, 
				HEIGHT_SET * (nSets-1) + Block.WITDTH_V + Block.WITDTH_M+ INDEX, 
				endBlockPointY + LINE_TAG/2);
		
		
		Multiplexer mux1 = new Multiplexer(INDEX + WITDH_INDEX + HEIGHT_SET*nSets/2 - WIDTH_SPACING/2, endBlockPointY + LINE_TAG + 120, nSets, nSets);
		if(nSets > 1) {
			mux1.paintComponent(g2);
			Name.paintComponent(g2, "block", mux1.getxOutput(), mux1.getyOutput(), false);
			g2.drawString("hit0", mux1.getxControler1() -40, mux1.getyControler1() +5);
			g2.drawString("hit"+(nSets-1), mux1.getxControler2() -40, mux1.getyControler2() +5);
		}
		
		
		for(int i= 0; i<nSets; i++)
		{
			Font font = new Font("Serif", Font.PLAIN, 14);
			g2.setFont(font);
			g2.drawString("way"+i, WIDTH_SPACING + INDEX + (Block.WITDTH_V + Block.WITDTH_M + Block.WITDTH_TAG + widthWord*nWords)/2-10 + HEIGHT_SET*i , 20);
			
			
			bracket = new Bracket(Block.WITDTH_V + Block.WITDTH_TAG + INDEX + WITDH_INDEX + HEIGHT_SET*i, 
						endBlockPointY + 5 , widthWord * nWords, true);
			if(nWords > 1 || nSets > 1)
			{
				bracket.paintComponent(g2);
			}
			
			
			
			if((bracket.getxOutput() >= mux1.getxInput(i)) && change  )
			{
				space -= 5;
			}
			else if ((bracket.getxOutput() < mux1.getxInput(i)) && !change )
			{
				space += 5;
			}
			else
			{
				change = true;
			}
			
			
			if(nSets > 1) {	
				g2.drawLine(bracket.getxOutput(), bracket.getyOutput(), bracket.getxOutput(), mux1.getyInput(i) - space);
				g2.drawLine(bracket.getxOutput(), mux1.getyInput(i) - space, mux1.getxInput(i), mux1.getyInput(i) - space);
				g2.drawLine(mux1.getxInput(i), mux1.getyInput(i)- space, mux1.getxInput(i), mux1.getyInput(i));
			}
			Name.paintComponent(g2, "block",  bracket.getxOutput(), mux1.getyInput(i) - 15 - space -40, false);
			
			
			// Draw the line to the comparator
			Arrow.paintComponent(g2, tagPointX + (HEIGHT_SET)*i, 
					endBlockPointY + 5, 
					tagPointX + (HEIGHT_SET)*i, 
					endBlockPointY + LINE_TAG - 10, true);
			// Draw the comparator
			Comparator.paintComponent(g2, tagPointX + (HEIGHT_SET)*i - 15, HEIGHT_ROW*nBlocks + LINE_TAG +Set.HEIGHT_NAMES+HEIGHT_NAMES-10);
			// Draw the tag line to the comparator

			if(i == nSets-1) {
				g2.drawLine(Block.WITDTH_V + Block.WITDTH_M + 60 + (HEIGHT_SET)*i-1 + INDEX-60, 
						HEIGHT_ROW*nBlocks + LINE_TAG/2-1+Set.HEIGHT_NAMES+HEIGHT_NAMES, 
						Block.WITDTH_V + Block.WITDTH_M + 60 + (HEIGHT_SET)*i-1 + INDEX-60, 
						endBlockPointY + LINE_TAG +14 -10);
			}
			else
			{
				Line.paintComponent(g2, Block.WITDTH_V + Block.WITDTH_M + 60 + (HEIGHT_SET)*i-1 + INDEX-60, 
						HEIGHT_ROW*nBlocks + LINE_TAG/2-1+Set.HEIGHT_NAMES+HEIGHT_NAMES, 
						Block.WITDTH_V + Block.WITDTH_M + 60 + (HEIGHT_SET)*i-1 + INDEX-60, 
						endBlockPointY + LINE_TAG +14 -10);
			}
			
			Arrow.paintComponent(g2, Block.WITDTH_V + Block.WITDTH_M + 60 + (HEIGHT_SET)*i-1 + INDEX-60, 
					endBlockPointY + LINE_TAG +14-10, 
					tagPointX + (HEIGHT_SET)*i -15, 
					endBlockPointY + LINE_TAG +14-10, false);
			
			
			
			
			Line.paintComponent(g2, tagPointX - Block.WITDTH_TAG/2 + (HEIGHT_SET)*i - 10, 
					endBlockPointY+ 5, 
					tagPointX + (HEIGHT_SET)*i - Block.WITDTH_TAG/2 - 10, 
					endBlockPointY +  LINE_TAG + 20);
			
			
			And and = new And(tagPointX + (HEIGHT_SET)*i - 30, 
					endBlockPointY + LINE_TAG  + 20,
					2);
			and.paintComponent(g2);
			
			g2.drawLine(and.getxInput(1), and.getyInput(1), 
					and.getxInput(1),  endBlockPointY + LINE_TAG  + 30);
			
			g2.drawLine(tagPointX + (HEIGHT_SET)*i - Block.WITDTH_TAG/2 - 10, and.getyInput(0), and.getxInput(0), and.getyInput(0));
			g2.drawString("hit" + i, and.getxOutput()-10, and.getyOutput()+13);
			
			
		
		}		
		
		if(nSets>1)
		{
			Multiplexer mux2 = new Multiplexer(INDEX + WITDH_INDEX + HEIGHT_SET*nSets/2 - WIDTH_SPACING/2, endBlockPointY + LINE_TAG + 250, nWords, 1);
			mux2.paintComponent(g2);
			g2.drawString("blockoffset", mux2.getxControler1() -70 , mux2.getyControler1() + 5);
			Name.paintComponent(g2, "word", mux2.getxOutput(), mux2.getyOutput()-10, false);
			
			g2.drawLine(mux1.getxOutput(), mux1.getyOutput(), mux1.getxOutput(), mux1.getyOutput()+ 30);
			
			g2.drawLine(mux2.getxInput(0), mux1.getyOutput()+ 30, mux2.getxInput(nWords-1), mux1.getyOutput()+ 30);
			for(int i=0; i<nWords; i++)
			{
				Line.paintComponent(g2, mux2.getxInput(i), mux1.getyOutput()+ 30, mux2.getxInput(i), mux2.getyInput(i));
				if(i == nWords-1)
				{
					Name.paintComponent(g2, "w"+i, mux2.getxInput(i), mux2.getyInput(i)-10, false);
				}
				if(i == 0)
				{
					Name.paintComponent(g2, "w"+i, mux2.getxInput(i), mux2.getyInput(i)-10, true);
				}
			}
		}
		else
		{
			if(nWords > 1)
			{
				Multiplexer mux2 = new Multiplexer(INDEX + WITDH_INDEX + Block.WITDTH_M + Block.WITDTH_V + Block.WITDTH_TAG +nWords*widthWord/2, endBlockPointY + LINE_TAG + 120, nWords, 1);
				mux2.paintComponent(g2);
				g2.drawString("blockoffset", mux2.getxControler1() -70 , mux2.getyControler1() + 5);
				Name.paintComponent(g2, "word", mux2.getxOutput(), mux2.getyOutput()-10, false);
				
				
				g2.drawLine(bracket.getxOutput(), bracket.getyOutput(), bracket.getxOutput(), mux2.getyInput(0)-50);
				
				
				
				//g2.drawLine(mux1.getxOutput(), mux1.getyOutput(), mux1.getxOutput(), mux1.getyOutput()+ 30);
				
				g2.drawLine(mux2.getxInput(0), mux2.getyInput(0)-50, mux2.getxInput(nWords-1), mux2.getyInput(0)-50);
				
				for(int i=0; i<nWords; i++)
				{
					Line.paintComponent(g2, mux2.getxInput(i), mux2.getyInput(0)-50, mux2.getxInput(i), mux2.getyInput(i));
					if(i == nWords-1)
					{
						Name.paintComponent(g2, "w"+i, mux2.getxInput(i), mux2.getyInput(i)-10, false);
					}
					if(i == 0)
					{
						Name.paintComponent(g2, "w"+i, mux2.getxInput(i), mux2.getyInput(i)-10, true);
					}
				}
			}
		}
		
		
		

		Font font = new Font("Serif", Font.PLAIN, 14);
		g2.setFont(font);	
		if(nBlocks > 1)
		{
			Decoder.paintComponent(g2, 80, nBlocks*Word.HEIGHT_ROW/2 + HEIGHT_NAMES + Set.HEIGHT_NAMES - (10+nBlocks*Word.HEIGHT_ROW /2)/2, nBlocks, INDEX, HEIGHT_ROW-10 +Set.HEIGHT_NAMES+HEIGHT_NAMES);
			
			g2.drawString("index", 5, nBlocks*Word.HEIGHT_ROW/2 + HEIGHT_NAMES + Set.HEIGHT_NAMES - (10+nBlocks*Word.HEIGHT_ROW /2)/2 + (10+nBlocks*Word.HEIGHT_ROW /2)/2+5);
			Arrow.paintComponent(g2, 55, 
					nBlocks*Word.HEIGHT_ROW/2 + HEIGHT_NAMES + Set.HEIGHT_NAMES - (10+nBlocks*Word.HEIGHT_ROW /2)/2 + (10+nBlocks*Word.HEIGHT_ROW /2)/2, 
					80, 
					nBlocks*Word.HEIGHT_ROW/2 + HEIGHT_NAMES + Set.HEIGHT_NAMES - (10+nBlocks*Word.HEIGHT_ROW /2)/2 + (10+nBlocks*Word.HEIGHT_ROW /2)/2, false);
			for(int i=0; i < nBlocks; i++)
			{
				g2.drawString("" + i, 0+4 + INDEX, 0+HEIGHT_ROW-4 + (i*HEIGHT_ROW)+Set.HEIGHT_NAMES+HEIGHT_NAMES);
			}
			
		}
		
		
		
		if(nSets > 1)
		{
		
			Or or = new Or(100, endBlockPointY + 230, nSets);
			or.paintComponent(g2);
		}
		
		
		
		
		
		

		dimensionX = HEIGHT_SET+WITDH_INDEX+INDEX;
		dimensionY =  endBlockPointY+80 + LINE_TAG + 500;
		this.setBounds(x, y, dimensionX , dimensionY);
		//System.out.println(dimensionX + "   " + dimensionY);

	}

	public int getDimensionX() {
		return dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}
	
	
	

}
