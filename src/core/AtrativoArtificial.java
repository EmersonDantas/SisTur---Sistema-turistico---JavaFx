
package core;

import java.io.Serializable;


public class AtrativoArtificial extends AtrativoTuristico implements Serializable{
    private String fundacao;
    private String fundador;
    
    public AtrativoArtificial(String nome, double latitude, double longitude, String comoChegar, String site, String infoContato, String fundacao, String fundador) {
            super(nome, latitude, longitude, comoChegar, site, infoContato);
            this.fundacao = fundacao;
            this.fundador = fundador;
    }
    
    public String getType(){
        return "AtrativoArtificial";
    }

    public String getFundacao() {
        return this.fundacao;
    }

    public void setFundacao(String fundacao) {
        this.fundacao = fundacao;
    }

    public String getFundador() {
        return this.fundador;
    }

    public void setFundador(String fundador) {
        this.fundador = fundador;
    }
	
    public String getDescricao(){
        return "Nome: "+super.getNome()+";\n"+
                "Latitude: "+super.getLatitude()+";\n"+
                "Longitude: "+super.getLongitude()+";\n"+
                "Como chegar: "+super.getComoChegar()+";\n"+
                "Site: "+super.getSite()+";\n"+
                "Contato: "+super.getInfoContato()+";\n"+
                "Fundacao: "+this.fundacao+";\n"+
                "Fundador: "+this.fundador;
    }
}
