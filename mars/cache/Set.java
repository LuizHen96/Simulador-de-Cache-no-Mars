package mars.cache;

public class Set {
	private Block[] set;
	
	public Set(int nBlocos, int nPalavras)
	{
		set = new Block[nBlocos];
		for(int i=0; i<nBlocos; i++)
		{
			set[i] = new Block(nPalavras);
		}
		
	}
	
	public Block[] getBlocks()
	{
		return set;
	}	

}
