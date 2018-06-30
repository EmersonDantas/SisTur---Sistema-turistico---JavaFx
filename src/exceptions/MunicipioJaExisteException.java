
package exceptions;

public class MunicipioJaExisteException extends Exception {
    public MunicipioJaExisteException(String mensagemDeErro){
        super(mensagemDeErro);
    }
    
    public MunicipioJaExisteException(){
        this("Erro: Municipio já existe.");
    }
}