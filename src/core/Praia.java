
package core;

import java.io.Serializable;

public class Praia extends AtrativoNatural implements Serializable{
	private boolean perigoTubarao;
	private boolean propriaParaBanho;
	private String tipoDeOrla;
	public static final String TIPO_MAR_ABERTO = "Mar aberto";
	public static final String TIPO_PEQUENAS_ONDAS = "Pequenas ondas";
	public static final String TIPO_MAR_ABRIGADO = "Mar abrigado";
	public static final String TIPO_ONDAS_MEDIAS = "Ondas médias";
	public static final String TIPO_PISCINAS_NATURAIS = "Piscinas naturais";
	public static final String TIPO_ONDAS_FORTES = "Ondas fortes";
	
	public Praia(String nome, double latitude, double longitude, String comoChegar, String site, String infoContato, boolean perigoTubarao, boolean propriaParaBanho, String tipoDeOrla) {
		super(nome, latitude, longitude, comoChegar, site, infoContato);
		this.perigoTubarao = perigoTubarao;
		this.propriaParaBanho = propriaParaBanho;
                this.tipoDeOrla = tipoDeOrla;
	}
	
	public boolean getPerigoTubarao() {
		return this.perigoTubarao;
	}
	
	public void setPerigoTubarao(boolean perigoTubarao) {
		this.perigoTubarao = perigoTubarao;
	}

	public boolean getPropriaParaBanho() {
		return this.propriaParaBanho;
	}
	
	public void setPropriaParaBanho(boolean propriaParaBanho) {
		this.propriaParaBanho = propriaParaBanho;
	}
	
	public String getTipoDeOrla() {
		return this.tipoDeOrla;
	}
	
	public void setTipoDeOrla(String tipoDeOrla) {
		this.tipoDeOrla = tipoDeOrla;
	}
        
        @Override
        public String getDescricao(){
            return "Nome: "+super.getNome()+";\n"+
                    "Latitude: "+super.getLatitude()+";\n"+
                    "Longitude: "+super.getLongitude()+";\n"+
                    "Como chegar: "+super.getComoChegar()+";\n"+
                    "Site: "+super.getSite()+";\n"+
                    "Contato: "+super.getInfoContato()+";\n"+
                    "Tipo de orla: "+this.tipoDeOrla+";\n"+
                    "Própria para banho: "+mudaBooleamParaString(this.propriaParaBanho)+";\n"+
                    "Perigo de tubarão: "+mudaBooleamParaString(this.perigoTubarao);
        }
        
        public String mudaBooleamParaString(boolean valor){
            if(valor){
                return "Sim";
            }else{
                return "Não";
            }
        }
	
	
}

