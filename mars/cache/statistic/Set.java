package mars.cache.statistic;


public class Set {
	private Block[] blocks;
	
	public Set(int nBlocks)
	{
		blocks = new Block[nBlocks];
		for(int i=0; i<nBlocks; i++)
		{
			blocks[i] = new Block();
		}
		
	}
	
	public Block getBlock(int i)
	{
		return blocks[i];
	}
	
	public Block[] getBlocks()
	{
		return blocks;
	}	

}
