
package core;

import java.io.Serializable;

public class MeioDeHospedagem implements Serializable{
	private String nome;
	private double latitude;
	private double longitude;
	private String infoContato;
	private String site;
	private String tipoHospedagem;
	private Endereco endereco;
	public static final String TIPO_HOTEL = "Hotel";
	public static final String TIPO_POUSADA = "Pousada";
	public static final String TIPO_ALBERGUE = "Albergue";
	
	public MeioDeHospedagem(String nome, double latitude, double longitude, String infoContato, String site, String tipoHospedagem, Endereco endereco) {
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this. infoContato = infoContato;
		this.site = site;
		this.tipoHospedagem = tipoHospedagem;
		this.endereco = endereco;
		
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getInfoContato() {
		return infoContato;
	}

	public void setInfoContato(String infoContato) {
		this.infoContato = infoContato;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTipoHospedagem() {
		return tipoHospedagem;
	}

	public void setTipoHospedagem(String tipoHospedagem) {
		this.tipoHospedagem = tipoHospedagem;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
        
        @Override
        public String toString(){ //Para ficar melhor de usar no Combobox
            return this.nome;
        }
        
        public String getDescricao(){
            return "Nome: "+this.nome+";\n"+ 
                    "Site: "+this.site+";\n"+
                    "Latitude: "+this.latitude+";\n"+
                    "Longitude: "+this.longitude+";\n"+
                    "Contato: "+this.infoContato+";\n"+
                    "Tipo de hospedagems: "+this.tipoHospedagem+";\n"+
                    "Logradouro: "+this.endereco.getLogradouro()+";\n"+
                    "Bairro: "+this.endereco.getBairro()+";\n"+
                    "Número: "+this.endereco.getNumero();
        }
}

