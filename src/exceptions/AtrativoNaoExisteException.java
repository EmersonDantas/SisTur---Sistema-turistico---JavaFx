package exceptions;

public class AtrativoNaoExisteException extends Exception{
	public AtrativoNaoExisteException(String erroMsg) {
		super(erroMsg);
	}
	
	public AtrativoNaoExisteException() {
		this("O atrativo pesquisado não existe.");
	}

}
