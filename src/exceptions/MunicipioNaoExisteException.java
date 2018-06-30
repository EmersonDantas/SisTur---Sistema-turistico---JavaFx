package exceptions;

public class MunicipioNaoExisteException extends Exception{
	public MunicipioNaoExisteException(String msgErro) {
		super(msgErro);
	}
	
	public MunicipioNaoExisteException() {
		this("O municipio não existe");
	}
}
