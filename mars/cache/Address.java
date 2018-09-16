package mars.cache;

public class Address {
	// são mascaras.
	private int tag;
	private int index;
	private int blockOffset;
	private int byteOffset;
	
	public Address(int tag, int index, int blockOffset, int byteOffset) {
		this.tag = this.index = this.blockOffset = -1;
	
		this.tag = this.tag >> (32-tag);
		this.tag = this.tag << (32-tag);
		
		
		this.index = this.index >> (32-index);
		this.index = this.index << (32-index);
		this.index = this.index >> tag;
		
		
		this.blockOffset = this.blockOffset >> (32-blockOffset);
		this.blockOffset = this.blockOffset << (32-blockOffset);
		this.blockOffset = this.blockOffset >> tag;
		this.blockOffset = this.blockOffset >> index;
		
		if(byteOffset > 0)
		{
			this.byteOffset = 3;
		}
	}
	
	
	public int tag(int address)
	{
		return address & this.tag; 
	}
	
	public int index(int address)
	{
		return address & this.index;
	}
	
	public int blockOffset(int address)
	{
		return address & this.blockOffset;
	}
	
	public int byteOffset(int address)
	{
		return address & this.byteOffset;
	}
	
	

}
