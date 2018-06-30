
package core;
import exceptions.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AreaDeInteresseTuristico implements Serializable{
	private List<Municipio> municipios;
	private String nome;
	
	public AreaDeInteresseTuristico(String nome) {
		this.municipios = new ArrayList<Municipio>();
		this.nome = nome;
	}

	public AreaDeInteresseTuristico(List<Municipio> municipios, String nome) {
		this.municipios = municipios;
		this.nome = nome;
	}

	public List<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void cadastrarMunicipio(Municipio municipio) throws MunicipioJaExisteException{
            for(Municipio m:municipios) {
                    if(m.getNome().equals(municipio.getNome())) {
                            throw new MunicipioJaExisteException("O municipio "+municipio.getNome()+" já existe.");
                    }
            }
            this.municipios.add(municipio);
		
	}
        
        public void removeMunicipio(Municipio municipio){
            this.municipios.remove(municipio);
        }
	
	public Municipio pesquisaMunicipio(String nomeMunicipio) throws MunicipioNaoExisteException{
            for(Municipio m:municipios) {
                    if(m.getNome().equals(nomeMunicipio)) {
                            return m;
                    }
            }
            throw new MunicipioNaoExisteException("O muninicpio "+nomeMunicipio+" não existe.");
	}
        
        public void cadastrarAtrativoTuristico(AtrativoTuristico atrativo, String nomeMun, String estado) throws MunicipioNaoExisteException, AtrativoJaExisteException{
            boolean conseguiu = false;
            for(Municipio m : municipios) {
                if(m.getNome().equals(nomeMun) && m.getEstado().equals(estado)) {
                    m.cadastrarAtrativoTuristico(atrativo);
                    conseguiu = true;
                }
            }
            
            if(!conseguiu) {
                    throw new MunicipioNaoExisteException("O municipio "+nomeMun+" do estado "+estado+" não existe!");
            }

	}
	
	public List<AtrativoTuristico> getAtrativosTuristicosDoMunicipio(String nomeMun, String estadoMun) throws MunicipioNaoExisteException{
		for(Municipio m : municipios) {
			if(m.getNome().equals(nomeMun) && m.getEstado().equals(estadoMun)) {
				return m.getAtrativosTuristicos();
			}
		}
		throw new MunicipioNaoExisteException("O municipio "+nomeMun+" não existe");
	}
}

