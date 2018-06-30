
package core;

import java.io.Serializable;

public class EventoProgramado extends AtrativoTuristico implements Serializable{
	private String dataInicio;
	private String dataFim;
	private String tipo;
	private Endereco endereco;
	public static final String TIPO_FESTA = "Festa";
	public static final String TIPO_FEIRA_GASTRONOMICA = "Feira gastronômica";
	
	public EventoProgramado(String nome, double latitude, double longitude, String comoChegar,
			String site, String infoContato, String dataInicio, String dataFim, String tipo, Endereco endereco){
		super(nome, latitude, longitude, comoChegar, site, infoContato);
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.tipo = tipo;
		this.endereco = endereco;
	}
        
        public String getType(){
            return "EventoProgramado";
        }

	public String getDataInicio() {
		return this.dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
        
        public String getDescricao(){
            return "Nome: "+super.getNome()+";\n"+
                    "Latitude: "+super.getLatitude()+";\n"+
                    "Longitude: "+super.getLongitude()+";\n"+
                    "Como chegar: "+super.getComoChegar()+";\n"+
                    "Site: "+super.getSite()+";\n"+
                    "Contato: "+super.getInfoContato()+";\n"+
                    "Data de inicio: "+this.dataInicio+";\n"+
                    "Data de fim: "+this.dataFim+";\n"+
                    "Tipo: "+this.tipo+";\n"+
                    "Logradouro: "+this.endereco.getLogradouro()+";\n"+
                    "Bairro: "+this.endereco.getBairro()+";\n"+
                    "Número: "+this.endereco.getNumero();
        }
	
}


