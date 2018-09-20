package mars.cache;

public class Controller {
	private String tipoCache;
	private String mapeamento;
	private String politicaSubstituicao;
	private int blocosConjunto;
	private int nBlocos;
	private int nPalavrasBloco;
	private int tempoAcessoCache;
	private int tempoAcessoMemoria;
	
	private Cache cache;
	private Address address;
	
	
	
	
	
	public Controller() {
		this.address = new Address(0,0,0,0);
	}
	
	
	
	public void setController(String tipoCache, String mapeamento, String politicaSubstituicao, int blocosConjunto, int nBlocos,
		int nPalavrasBloco, int tempoAcessoCache, int tempoAcessoMemoria) {
		int tag, index, blockOffset, byteOffset;
		index = (int) (Math.log(nBlocos/blocosConjunto)/Math.log(2));
		blockOffset = (int) (Math.log(nPalavrasBloco)/Math.log(2));
		byteOffset = 0;
		tag = 32 - index - blockOffset - byteOffset;
		
		
		this.tipoCache = tipoCache;
		this.mapeamento = mapeamento;
		this.politicaSubstituicao = politicaSubstituicao;
		this.blocosConjunto = blocosConjunto;
		this.nBlocos = nBlocos;
		this.nPalavrasBloco = nPalavrasBloco;
		this.tempoAcessoCache = tempoAcessoCache;
		this.tempoAcessoMemoria = tempoAcessoMemoria;
		
		this.address = new Address(tag, index, blockOffset, byteOffset);
		
	}
	
	
	


	public String getTipoCache() {
		return tipoCache;
	}


	public String getMapeamento() {
		return mapeamento;
	}


	public String getPoliticaSubstituicao() {
		return politicaSubstituicao;
	}


	public int getBlocosConjunto() {
		return blocosConjunto;
	}


	public int getnBlocos() {
		return nBlocos;
	}


	public int getnPalavrasBloco() {
		return nPalavrasBloco;
	}


	public int getTempoAcessoCache() {
		return tempoAcessoCache;
	}


	public int getTempoAcessoMemoria() {
		return tempoAcessoMemoria;
	}
	
	public Address getAddress()
	{
		return address;
	}
	
	
	
	
	

}
