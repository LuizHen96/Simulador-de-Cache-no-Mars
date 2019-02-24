package mars.cache.statistic;


public class SetStatistic {
	private BlockStatistic[] blocks;
	
	public SetStatistic(int nBlocks)
	{
		blocks = new BlockStatistic[nBlocks];
		for(int i=0; i<nBlocks; i++)
		{
			blocks[i] = new BlockStatistic();
		}
		
	}
	
	public BlockStatistic getBlock(int i)
	{
		return blocks[i];
	}
	
	public BlockStatistic[] getBlocks()
	{
		return blocks;
	}	

}
