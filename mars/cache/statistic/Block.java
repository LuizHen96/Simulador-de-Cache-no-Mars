package mars.cache.statistic;

public class Block {

	private boolean bit_validade;
	private int ordem_acesso;
	private int tag;
	
	
	public Block()
	{
		this.bit_validade = false;
	}


	public boolean isBit_validade() {
		return bit_validade;
	}


	public void setBit_validade(boolean bit_validade) {
		this.bit_validade = bit_validade;
	}


	public int getOrdem_acesso() {
		return ordem_acesso;
	}


	public void setOrdem_acesso(int ordem_acesso) {
		this.ordem_acesso = ordem_acesso;
	}


	public int getTag() {
		return tag;
	}


	public void setTag(int tag) {
		this.tag = tag;
	}
	
	
	
	
	
	
	
	
	
	
}
