package mars.cache;

public class Set {
	private Block[] set;
	
	public Set(int nBlocks, int nWords)
	{
		set = new Block[nBlocks];
		for(int i=0; i<nBlocks; i++)
		{
			set[i] = new Block(nWords);
		}
		
	}
	
	public Block[] getBlocks()
	{
		return set;
	}	

}
