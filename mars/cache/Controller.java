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
	
	
	public Controller(String tipoCache, String mapeamento, String politicaSubstituicao, int blocosConjunto, int nBlocos,
			int nPalavrasBloco, int tempoAcessoCache, int tempoAcessoMemoria) {
		super();
		this.tipoCache = tipoCache;
		this.mapeamento = mapeamento;
		this.politicaSubstituicao = politicaSubstituicao;
		this.blocosConjunto = blocosConjunto;
		this.nBlocos = nBlocos;
		this.nPalavrasBloco = nPalavrasBloco;
		this.tempoAcessoCache = tempoAcessoCache;
		this.tempoAcessoMemoria = tempoAcessoMemoria;
	}
	
	
	
	public void setController(String tipoCache, String mapeamento, String politicaSubstituicao, int blocosConjunto, int nBlocos,
			int nPalavrasBloco, int tempoAcessoCache, int tempoAcessoMemoria) {
		this.tipoCache = tipoCache;
		this.mapeamento = mapeamento;
		this.politicaSubstituicao = politicaSubstituicao;
		this.blocosConjunto = blocosConjunto;
		this.nBlocos = nBlocos;
		this.nPalavrasBloco = nPalavrasBloco;
		this.tempoAcessoCache = tempoAcessoCache;
		this.tempoAcessoMemoria = tempoAcessoMemoria;
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
	
	
	
	
	
	

}
