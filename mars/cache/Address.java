package mars.cache;

public class Address {
	// são mascaras.
	private int tag;
	private int shiftTag;
	private int index;
	private int shiftIndex;
	private int blockOffset;
	private int shiftBlockOffset;
	private int byteOffset;
	
	public Address(int tag, int index, int blockOffset, int byteOffset) {
		this.tag = this.index = this.blockOffset = -1;
	
		this.tag = this.tag >> (32-tag);
		this.tag = this.tag << (32-tag);
		this.shiftTag = 32 - tag;
		
		
		this.index = this.index >> (32-index);
		this.index = this.index << (32-index);
		this.index = this.index >> tag;
		this.shiftIndex = 32 - index - tag;
		
		
		this.blockOffset = this.blockOffset >> (32-blockOffset);
		this.blockOffset = this.blockOffset << (32-blockOffset);
		this.blockOffset = this.blockOffset >> tag;
		this.blockOffset = this.blockOffset >> index;
		this.shiftBlockOffset = 32 - index - tag - blockOffset;
				
		if(byteOffset > 0)
		{
			this.byteOffset = 3;
		}
	}
	
	
	public String tag(int address)
	{
		return Integer.toBinaryString((address & this.tag) >> this.shiftTag); 
		
	}
	
	public String index(int address)
	{
		return Integer.toBinaryString((address & this.index) >> this.shiftIndex);
	}
	
	public String blockOffset(int address)
	{
		return Integer.toBinaryString((address & this.blockOffset) >> this.shiftBlockOffset);
	}
	
	public String byteOffset(int address)
	{
		return Integer.toBinaryString(address & this.byteOffset);
	}
	
	

}
