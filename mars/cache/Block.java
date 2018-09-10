package mars.cache;

public class Block {
	private Word[] blocks;
	
	public Block(int x)
	{
		blocks = new Word[x];
	}
	
	public Word[] getBlocks()
	{
		return blocks;
	}
	
	
	
}
