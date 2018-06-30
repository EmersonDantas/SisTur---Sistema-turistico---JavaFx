
package exceptions;

public class MeioDeHospedagemNaoExisteException extends Exception{
    public MeioDeHospedagemNaoExisteException(String msgErro){
        super(msgErro);
        
    }
    
    public MeioDeHospedagemNaoExisteException(){
        this("Esse meio de hospedagem não existe!");
    }
    
}
