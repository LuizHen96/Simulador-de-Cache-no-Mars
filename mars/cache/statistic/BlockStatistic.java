package mars.cache.statistic;

public class BlockStatistic {

	private boolean bit_validade;
	private int ordem_acesso;
	private int tag;
	
	
	public BlockStatistic()
	{
		this.bit_validade = false;
	}
	public int getValidate()
	{
		if(bit_validade)
			return 1;
		else
			return 0;
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
