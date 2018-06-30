
package core;

import java.io.Serializable;

public class AtrativoNatural extends AtrativoTuristico implements Serializable{
	public AtrativoNatural(String nome, double latitude, double longitude, String comoChegar, String site, String infoContato) {
		super(nome, latitude, longitude, comoChegar, site, infoContato);
	}
	
    @Override
    
    public String getDescricao(){
        return "Nome: "+super.getNome()+";\n"+
                "Latitude: "+super.getLatitude()+";\n"+
                "Longitude: "+super.getLongitude()+";\n"+
                "Como chegar: "+super.getComoChegar()+";\n"+
                "Site: "+super.getSite()+";\n"+
                "Contato: "+super.getInfoContato();
    }
    
    public String getType(){
        return "AtrativoNatural";
    }

}

