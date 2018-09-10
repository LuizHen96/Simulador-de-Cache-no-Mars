package mars.cache;

public class Block {
	private boolean validate;
	private Word[] blocks;
	
	public Block(int nPalavras)
	{
		validate = false;
		blocks = new Word[nPalavras];
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
