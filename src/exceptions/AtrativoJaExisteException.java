
package exceptions;

public class AtrativoJaExisteException extends Exception{
    public AtrativoJaExisteException(String mensagemDeErro){
        super(mensagemDeErro);
    }
    public AtrativoJaExisteException(){
        this("Erro: o atrativo já existe!");
    }
}
