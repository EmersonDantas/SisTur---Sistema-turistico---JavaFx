
package core;

import exceptions.*;
import exceptions.AtrativoNaoExisteException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Municipio implements Serializable{
	private String nome;
	private int populacao;
	private List<AtrativoTuristico> atrativosTuristicos;
	private double latitude;
	private double longitude;
	private List<MeioDeHospedagem> meiosDeHospedagem;
	private String estado;
	private String site;
	
	public Municipio(String nome, int populacao, List<AtrativoTuristico> atrativosTuristicos,
                         double latitude, double longitude, List<MeioDeHospedagem> meiosDeHospedagem, 
                         String estado, String site){
		this.nome = nome;
		this.populacao = populacao;
		this.atrativosTuristicos = atrativosTuristicos;
		this.latitude = latitude;
		this.longitude = longitude;
		this.meiosDeHospedagem = meiosDeHospedagem;
		this.estado = estado;
		this.site = site;
	}
        
        public Municipio(String nome, int populacao,double latitude, double longitude, String estado,String site) {
		this(nome, populacao, new ArrayList <AtrativoTuristico>(),latitude, longitude, new ArrayList <MeioDeHospedagem>(), estado,site);
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPopulacao() {
		return populacao;
	}

	public void setPopulacao(int populacao) {
		this.populacao = populacao;
	}

	public List<AtrativoTuristico> getAtrativosTuristicos(){
		return this.atrativosTuristicos;
	}

	public void setAtrativosTuristicos(List<AtrativoTuristico> atrativosTuristicos) {
		this.atrativosTuristicos = atrativosTuristicos;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public List<MeioDeHospedagem> getMeiosDeHospedagem() {
		return this.meiosDeHospedagem;
	}

	public void setMeiosDeHospedagem(List<MeioDeHospedagem> meiosDeHospedagem) {
		this.meiosDeHospedagem = meiosDeHospedagem;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public void cadastrarAtrativoTuristico(AtrativoTuristico atrativo) throws AtrativoJaExisteException{

		for(AtrativoTuristico atra:atrativosTuristicos) {
			if(atrativo.getNome().equals(atra.getNome())) {
				throw new AtrativoJaExisteException("O atrativo "+atrativo.getNome()+" já existe.");
			}
		}
		
		this.atrativosTuristicos.add(atrativo);
	
	}
        
	public AtrativoTuristico pesquisaAtrativoTuristico(String nome) throws AtrativoNaoExisteException{
		for(AtrativoTuristico atra:atrativosTuristicos) {
			if(atra.getNome().equals(nome)) {
				return atra;
			}
		}
		throw new AtrativoNaoExisteException("O atrativo "+nome+" não existe!");
	}
        
        public void cadastrarNovaHospedagem(MeioDeHospedagem meioDeHospedagem) throws MeioDeHospedagemJaExisteException{
            for(MeioDeHospedagem m:meiosDeHospedagem){
                if(meioDeHospedagem.getNome().equals(m.getNome())){
                    throw new MeioDeHospedagemJaExisteException("A hospedagem "+meioDeHospedagem.getNome()+" já existe!");
                }
            }
            this.meiosDeHospedagem.add(meioDeHospedagem);
        }
        
        public MeioDeHospedagem pesquisaMeioDeHospedagem(String nome) throws MeioDeHospedagemNaoExisteException{
            boolean encontrou = false;
            for(MeioDeHospedagem m:meiosDeHospedagem){
                if(m.getNome().equals(nome)){
                    encontrou = true;
                    return m;
                }
            }

            throw new MeioDeHospedagemNaoExisteException("O meio de hospedagem "+nome+ " não existe!");    
        }
        
        public void removeAtrativoTuristico(AtrativoTuristico atrativoTuristico){
            this.atrativosTuristicos.remove(atrativoTuristico);
        }
        
        public void removeHospedagem(MeioDeHospedagem meioDeHospedagem){
            this.meiosDeHospedagem.remove(meioDeHospedagem);
        }
        
        public String getDescricao() {
		return "Nome: "+this.nome+";\n"+ 
                        "População: "+this.populacao+";\n"+
                        "Estado: "+this.estado+";\n"+
                        "Site: "+this.site+";\n"+
			"Latitude: "+this.latitude+";\n"+
                        "Longitude: "+this.longitude+";\n"+
                        "Nº de hospedagens: "+this.meiosDeHospedagem.size()+";\n"+
                        "Nº de atrativos turisticos: "+this.atrativosTuristicos.size();
                        
	}
        
        public void apagaAtrativo(AtrativoTuristico atrativo){
            this.atrativosTuristicos.remove(atrativo);
        }
	
        @Override
	public String toString() {
		return this.nome;
	}
        
        
	
}

