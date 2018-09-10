package mars.cache;

public class Block {
	private boolean validate;
	private Word[] blocks;
	
	public Block(int nWords)
	{
		validate = false;
		blocks = new Word[nWords];
	}
	
	public Word[] getBlocks()
	{
		return blocks;
	}
	
	public boolean getValidate()
	{
		return validate;
	}
	
}
