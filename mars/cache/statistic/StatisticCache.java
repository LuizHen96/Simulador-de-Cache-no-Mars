package mars.cache.statistic;

//import testeSt.Set;

public class StatisticCache {
	private SetStatistic[] set;
	private int nBlocos;
	private int associatividade;
	private int nPalavrasBloco;
	private int nConjuntos;
	private int address, index, tag, byteOffset, blockOffset;
	private int blocoSubstituido;
	private int lastResult;


	public StatisticCache(int nBlocos, int associatividade, int nPalavrasBloco)
	{
		this.nBlocos= nBlocos;
		this.associatividade = associatividade;
		this.nPalavrasBloco = nPalavrasBloco;
		this.nConjuntos = nBlocos/associatividade;
		
		
		set = new SetStatistic[nConjuntos];
		for(int i=0; i<(nConjuntos); i++)
		{
			set[i] = new SetStatistic(associatividade);
		}
		
		
	}
	
	public int buscaInsereCache(char tipo_acesso, int endereco)
	{
		int endereco_palavra;
		int endereco_bloco;
		int indice;
		int tag;
		int pos;
		int pos_acerto;
		int pos_acesso;
		int pos_livre;
		int pos_lru;
		
		int ordem_lru;
		int ordem_mru;
		int result;
		char acerto;
		
		
		endereco_palavra = endereco / 4;
		endereco_bloco = endereco_palavra / nPalavrasBloco;
		indice = endereco_bloco % nConjuntos;
		tag = endereco_bloco / nConjuntos;
		
		
		address = endereco;
		index = indice;
		this.tag = tag;
		byteOffset = endereco%4;
		blockOffset = endereco_bloco;
		
		
		
		
		pos = 0;
		pos_acerto = 0;
		pos_livre = associatividade;
		pos_lru = 0;
		ordem_lru = -1;
		ordem_mru = -1;
		acerto = 0;
		
		for(pos = 0; pos< associatividade; pos++)
		{
			if(set[indice].getBlock(pos).isBit_validade())
			{
				if(set[indice].getBlock(pos).getTag() == tag)
				{
					acerto = 1;
					pos_acerto = pos;
					
					if((ordem_mru == -1) || (ordem_mru < set[indice].getBlock(pos).getOrdem_acesso()))
					{
						ordem_mru = set[indice].getBlock(pos).getOrdem_acesso();
					}
					
					if((ordem_mru == -1) || (ordem_mru > set[indice].getBlock(pos).getOrdem_acesso()))
					{
						ordem_lru = set[indice].getBlock(pos).getOrdem_acesso();
					}
					
				}
				else
				{
					if((ordem_mru == -1) || (ordem_mru < set[indice].getBlock(pos).getOrdem_acesso()))
					{
						ordem_mru = set[indice].getBlock(pos).getOrdem_acesso();
					}
					
					if((ordem_mru == -1) || (ordem_mru > set[indice].getBlock(pos).getOrdem_acesso()))
					{
						pos_lru = pos;
						ordem_lru = set[indice].getBlock(pos).getOrdem_acesso();
					}
				}
			}
			else
			{
				if(pos_livre == associatividade)
				{
					pos_livre = pos;
				}
			}
		}
		
		if(acerto == 1)
		{
			pos_acesso = pos_acerto;
			
			if(set[indice].getBlock(pos_acesso).getOrdem_acesso() != ordem_mru)
			{
				set[indice].getBlock(pos_acesso).setOrdem_acesso(ordem_mru+1);
			}
			result = 0;
			lastResult = 0;
		}
		else
		{
			if(pos_livre == associatividade)
			{
				pos_acesso = pos_lru;
				blocoSubstituido = set[indice].getBlock(pos_acesso).getTag();
				blocoSubstituido = blocoSubstituido * nConjuntos;
				blocoSubstituido = blocoSubstituido + indice;
				set[indice].getBlock(pos_acesso).setOrdem_acesso(ordem_mru +1);
				set[indice].getBlock(pos_acesso).setTag(tag);
				result = 2;		
				lastResult = 2;
			}
			else
			{
				pos_acesso = pos_livre;
				set[indice].getBlock(pos_acesso).setBit_validade(true);
				set[indice].getBlock(pos_acesso).setOrdem_acesso(ordem_mru +1);
				set[indice].getBlock(pos_acesso).setTag(tag);
				result = 1;
				lastResult = 1;
			}
		}
		
		
		return result;
	}

	public int getAddress() {
		return address;
	}

	public int getIndex() {
		return index;
	}

	public int getTag() {
		return tag;
	}

	public int getByteOffset() {
		return byteOffset;
	}

	public int getBlockOffset() {
		return blockOffset;
	}
	public String getBlocoSubstituido() {
		if(lastResult == 2)
			return blocoSubstituido + "";
		return "";
	}

	public SetStatistic[] getSet() {
		return set;
	}

	public int getnBlocos() {
		return nBlocos;
	}

	public int getAssociatividade() {
		return associatividade;
	}

	public int getnPalavrasBloco() {
		return nPalavrasBloco;
	}

	public int getnConjuntos() {
		return nConjuntos;
	}
	
	
}
