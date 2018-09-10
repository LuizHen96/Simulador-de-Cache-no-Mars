package mars.cache;

public class Word {
	private int word;
	private String inst;
	private boolean isInst;
	
	
	public Word()
	{
		isInst = false;
	}
		
	
	public void setWord(int word)
	{
		isInst = false;
		this.word = word;
	}
	
	public void setWord(String inst)
	{
		isInst = true;
		this.inst = inst;
	}
	
	
	public String getWord()
	{
		if(isInst)
		{
			return inst;
		}
		else
		{
			return Integer.toHexString(word);
		}
	}
	
	
	

}
