package mars.cache;
public class Address {

	private int tag;
	private int tagLenght;
	private int shiftTag;
	private int index;
	private int indexLenght;
	private int shiftIndex;
	private int blockOffset;
	private int blockOffsetLenght;
	private int shiftBlockOffset;
	private int byteOffset;
	
	public Address(int tag, int index, int blockOffset, int byteOffset) {
		
		this.tag = this.index = this.blockOffset = 0;
		this.tagLenght = tag;
		this.indexLenght = index;
		this.blockOffsetLenght= blockOffset;
	
		
		for(int i=0; i< tag; i++)
		{
			this.tag = this.tag << 1;
			this.tag += 1;
		}
		this.tag  = this.tag << (32-tag);
		this.shiftTag = 32 - tag;
		
		for(int i=0; i< index; i++)
		{
			this.index = this.index << 1;
			this.index += 1;
		}
		this.index = this.index << (32-index - tag);
		this.shiftIndex = 32 - index - tag;
		
		
		for(int i=0; i< blockOffset; i++)
		{
			this.blockOffset = this.blockOffset << 1;
			this.blockOffset += 1;
		}
		this.blockOffset = this.blockOffset << (32-index - tag - blockOffset);
		this.shiftBlockOffset = 32 - index - tag - blockOffset;
				
		if(byteOffset > 0)
		{
			this.byteOffset = 3;
		}
	}
	
	
	public String tag(int address)
	{
		String aux;
		int aux2 = (address & this.tag) >> this.shiftTag;
		aux = "%0"+tagLenght+"d";
		return aux2 + ""; 
		
	}
	
	public String index(int address)
	{
		String aux;
		int aux2 = (address & this.index) >> this.shiftIndex;
		aux = "%0"+indexLenght+"d";
		return aux2 + "";
	}
	
	public String blockOffset(int address)
	{
		String aux;
		int aux2 = (address & this.blockOffset) >> this.shiftBlockOffset;
		aux = "%0"+blockOffsetLenght+"d";
		return aux2 + "";
	}
	
	public String byteOffset(int address)
	{
		return (address & this.byteOffset) + "";
	}


	public int getTagLenght() {
		return tagLenght;
	}


	public int getIndexLenght() {
		return indexLenght;
	}


	public int getBlockOffsetLenght() {
		return blockOffsetLenght;
	}
	
	public String blockNumber(int address, int nPalavrasBloco)
	{
		int nPalavra = address / 4;
		int nBloco;
		if(nPalavrasBloco > 1)
		{
			nBloco = nPalavra / nPalavrasBloco;
		}
		else
		{
			nBloco = nPalavra;
		}
		
		return nBloco + "";
	}
	
	
	

}
