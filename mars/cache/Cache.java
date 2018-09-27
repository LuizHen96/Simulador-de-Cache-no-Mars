package mars.cache;

public class Cache {
	
	private int nBlocks;
	private int blockSize;
	private int setSize;
	private String cacheStorage;
	private String placementPolicy;
	private String replacementPolicy;
	
	
	
	public Cache(int nBlocks, int blockSize, int setSize, String cacheStorage, String placementPolicy,
			String replacementPolicy) {
		super();
		this.nBlocks = nBlocks;
		this.blockSize = blockSize;
		this.setSize = setSize;
		this.cacheStorage = cacheStorage;
		this.placementPolicy = placementPolicy;
		this.replacementPolicy = replacementPolicy;
	}

	
	
	
	
	
	
	
	

}
