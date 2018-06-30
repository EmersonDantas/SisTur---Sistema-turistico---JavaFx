
package core;

import java.io.Serializable;

public abstract class AtrativoTuristico implements Serializable{
	private String nome;
	private double latitude;
	private double longitude;
	private String comoChegar;
	private String site;
	private String infoContato;
	
	public AtrativoTuristico() {
		this.nome = "";
		this.latitude = 0;
		this.longitude = 0;
		this.comoChegar = "";
		this.site = "";
		this.infoContato = "";
	}
	
	public AtrativoTuristico(String nome, double latitude, double longitude, String comoChegar, String site, String infoContato) {
		this.nome = nome;
		this.latitude = latitude;
		this.longitude = longitude;
		this.comoChegar = comoChegar;
		this.site = site;
		this.infoContato = infoContato;
	}
        
	public abstract String getType();
        
	public abstract String getDescricao();
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public void setLongetude(double longetude){
		this.longitude = longetude;
	}
	
	public void setComoChegar(String comoChegar) {
		this.comoChegar = comoChegar;
	}
	
	public void setSite(String site){
		this.site = site;
	}
	
	public void setInfoContato(String infoContato) {
		this.infoContato = infoContato;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public double getLatitude() {
		return this.latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}
	
	public String getComoChegar() {
		return this.comoChegar;
	}
	
	public String getSite() {
		return this.site;
	}
	
	public String getInfoContato() {
		return this.infoContato;
	}
        
        
    /**
     *
     * @return
     */
        @Override
        public String toString(){
                return this.nome;
        }
        
        
	
	
}
