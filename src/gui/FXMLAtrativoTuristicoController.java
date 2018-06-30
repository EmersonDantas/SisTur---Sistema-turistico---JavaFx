package gui;

import core.*;
import exceptions.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FXMLAtrativoTuristicoController implements Initializable {
    
    public FXMLAtrativoTuristicoController(){
        
    }
    
    private FXMLMenuFuncoesController menuFc;
    
    protected static String [] informacoesAtrativoSuper;
    
    protected static Municipio municipioEscolhido;

    private ObservableList<Municipio> obsMunicipios;
    
    @FXML
    private TextField textNome;

    @FXML
    private TextField textComoChegar;

    @FXML
    private TextField textContato;

    @FXML
    private TextField textLatitude;

    @FXML
    private TextField textLongitude;

    @FXML
    private TextField textSite;

    @FXML
    private ToggleGroup grupoAtrativos;
    
    @FXML
    private ComboBox<Municipio> cbMunicipio;
    
    @FXML
    private Text txtLongitude;
    
    @FXML
    private Text txtLatitude;
    
    @FXML
    private Text txtNome;

    @FXML
    private Text txtComoChegar;
    
    @FXML
    private Text txtContato;
    
    @FXML
    private Text txtSite;
    
    
    @FXML
    private Text txtMuni;

    public String[] getInformacoesAtrativoSuper(){
        return this.informacoesAtrativoSuper;
    }
    
    public Municipio getMunicipioEscolhido(){
        return this.municipioEscolhido;
    }
    
    @FXML
    void btnCadastrarAtrativoTuristico(ActionEvent event) throws IOException {
        double latitude = 0; double longitude = 0;int cont = 0;
        boolean atrativoExiste = false;
        String nome = textNome.getText();
        
        if(nome.isEmpty()){
           setaCorETexto(txtNome,"Nome: ",Color.RED);
        }else{
            if(!atrativoExiste){
                setaCorETexto(txtNome,"Nome: ",Color.WHITE);  
            }
            cont++;
        }
        
        String comoChegar = textComoChegar.getText();
        
        if(comoChegar.isEmpty()){
           setaCorETexto(txtComoChegar,"Como chegar: ",Color.RED);
        }else{
           cont++;
           setaCorETexto(txtComoChegar,"Como chegar: ",Color.WHITE); 
        }
        
        String site = textSite.getText();
        
        if(site.isEmpty()){
           setaCorETexto(txtSite,"Site: ",Color.RED);
        }else{
           cont++;
           setaCorETexto(txtSite,"Site: ",Color.WHITE); 
        }
        
        String infoContato = textContato.getText();
        
        if(infoContato.isEmpty()){
           setaCorETexto(txtContato,"Contato: ",Color.RED);
        }else{
           cont++;
           setaCorETexto(txtContato,"Contato: ",Color.WHITE); 
        }
        
        try{
            latitude = Double.parseDouble(textLatitude.getText());
            setaCorETexto(txtLatitude,"Latitude:",Color.WHITE);
            cont++;
        }catch(NumberFormatException erro){
            setaCorETexto(txtLatitude,"Latitude: Digite apenas números.",Color.RED);
        }
        
        try{
            longitude = Double.parseDouble(textLongitude.getText());
            setaCorETexto(txtLongitude,"Longitude:",Color.WHITE);
            cont++;
        }catch(NumberFormatException erro){
            setaCorETexto(txtLongitude,"Longitude: Digite apenas números.",Color.RED);
        }

        municipioEscolhido = cbMunicipio.getSelectionModel().getSelectedItem();
        RadioButton radioOpcoes = (RadioButton) grupoAtrativos.getSelectedToggle();
        
        if (municipioEscolhido != null){
            this.setaCorETexto(txtMuni,"Municipio:",Color.WHITE);

            try{
                municipioEscolhido.pesquisaAtrativoTuristico(nome);
                atrativoExiste = true;
            }catch(AtrativoNaoExisteException erro){
                atrativoExiste = false;
            }

            if(!atrativoExiste){
                this.setaCorETexto(txtNome, "Nome: ", Color.WHITE);
                if (cont == 6){// Verificando as entradas foram digitadas corretamente
                    String [] infors = {nome,Double.toString(latitude), Double.toString(longitude),comoChegar,site,infoContato};
                    this.informacoesAtrativoSuper = infors;
                    switch (radioOpcoes.getText()){
                      case "Natural":
                          //Fechando janela atual e abrindo janela de cadastro de atrativo natural
                          MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLAtrativoNatural.fxml", "Cadastrar novo atrativo natural");
                          MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAtrativoTuristico.fxml");
                          break;

                      case "Artificial":
                          //Fechando janela atual e abrindo janela de cadastro de evento programado
                          MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLAtrativoArtificial.fxml", "Cadastrar novo atrativo artificial");
                          MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAtrativoTuristico.fxml");
                          break;

                      case "Evento":
                          //Fechando janela atual e abrindo janela de cadastro de evento programado
                          MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLEventoProgramado.fxml", "Cadastrar novo evento programado");
                          MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAtrativoTuristico.fxml");
                          break;

                      }  
                  }
                
            }else{
                this.setaCorETexto(txtNome, "Nome: O atrativo " + nome + " já existe!", Color.RED);
            }
        }else{
            this.setaCorETexto(txtMuni,"Municipio:",Color.RED);
            
        }
        
    }

    @FXML
    void btnVoltarClick(ActionEvent event) {
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAtrativoTuristico.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuFc = new FXMLMenuFuncoesController();
        carregarCategorias();
    }

    public void carregarCategorias(){
        List <Municipio> municipios = menuFc.getAreaDeInteresseTuristico().getMunicipios();
        obsMunicipios = FXCollections.observableArrayList(municipios);
        cbMunicipio.setItems(obsMunicipios);
        
    }
    
    public void setaCorETexto(Text texto, String textoAExibir, Color cor){
        texto.setText(textoAExibir);
        texto.setFill(cor); 
    }
    
}
