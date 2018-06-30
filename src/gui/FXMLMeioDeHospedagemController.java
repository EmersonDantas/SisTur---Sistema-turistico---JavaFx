package gui;

import core.*;
import exceptions.MeioDeHospedagemJaExisteException;
import exceptions.MeioDeHospedagemNaoExisteException;
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
import javax.swing.JOptionPane;

public class FXMLMeioDeHospedagemController implements Initializable {
    private FXMLMenuFuncoesController menuController;
    private AreaDeInteresseTuristico areaDeInteresse;
    
    public FXMLMeioDeHospedagemController(){
        this.menuController = new FXMLMenuFuncoesController();
    }

    @FXML
    private TextField textComoChegar;

    @FXML
    private TextField textLatitude;

    @FXML
    private TextField textLongitude;

    @FXML
    private TextField textSite;

    @FXML
    private TextField textNome;

    @FXML
    private ToggleGroup tipoHospedagem;

    @FXML
    private TextField textLogradouro;

    @FXML
    private TextField textNumero;

    @FXML
    private TextField textBairro;
    
    @FXML
    private TextField textInfoContato;
    
    @FXML
    private ComboBox<Municipio> cbMunicipios;
    
    private ObservableList<Municipio> obsMunicipios;
    
    @FXML
    private Text txtComoChegar;

    @FXML
    private Text txtLatitude;

    @FXML
    private Text txtLongitude;

    @FXML
    private Text txtSite;

    @FXML
    private Text txtlogra;

    @FXML
    private Text txtNumero;

    @FXML
    private Text txtBairro;

    @FXML
    private Text txtMun;

    @FXML
    private Text txtNome;

    @FXML
    private Text txtContato;

    @FXML
    void btnProximoClick(ActionEvent event) {
        int cont = 0;
        boolean hospedagemExiste = false;
        
        String logradouro = textLogradouro.getText();
        
        if(logradouro.isEmpty()){
            setaCorETexto(txtlogra,"Logradouro:",Color.RED);
        }else{
            setaCorETexto(txtlogra,"Logradouro:",Color.WHITE);
            cont++;
        }
        
        String numero = textNumero.getText();
        
        if(numero.isEmpty()){
            setaCorETexto(txtNumero,"Número:",Color.RED);
        }else{
            setaCorETexto(txtNumero,"Número:",Color.WHITE);
            cont++;
        }
        
        
        String bairro = textBairro.getText();
        
        if(bairro.isEmpty()){
            setaCorETexto(txtBairro,"Bairro:",Color.RED);
        }else{
            setaCorETexto(txtBairro,"Bairro:",Color.WHITE);
            cont++;
        }
        
        
        double latitude = 0; double longitude = 0;
        
        String nome = textNome.getText();
        
        if(nome.isEmpty()){
            setaCorETexto(txtNome,"Nome:",Color.RED);
        }else{
            if(!hospedagemExiste){
                setaCorETexto(txtNome,"Nome:",Color.WHITE);
                cont++;
            } 
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
        
        String infoContato = textInfoContato.getText();
        
        if(infoContato.isEmpty()){
            setaCorETexto(txtContato,"Contato:",Color.RED);
        }else{
            setaCorETexto(txtContato,"Contato:",Color.WHITE);
            cont++;
        }
        
        String site = textSite.getText();
        
        if(site.isEmpty()){
            setaCorETexto(txtSite,"Site:",Color.RED);
        }else{
            setaCorETexto(txtSite,"Site:",Color.WHITE);
            cont++;
        }
        
        String comoChegar = textComoChegar.getText();
        
        if(comoChegar.isEmpty()){
            setaCorETexto(txtComoChegar,"Como chegar:",Color.RED);
        }else{
            setaCorETexto(txtComoChegar,"Como chegar:",Color.WHITE);
            cont++;
        }
        
        Municipio municipioEscolhido = cbMunicipios.getSelectionModel().getSelectedItem();//Obtendo municipio escolhido apra cadastrar nova hospedagem
        
        if(municipioEscolhido != null){
            setaCorETexto(txtMun,"Municipio:",Color.WHITE);
            
            try{
                municipioEscolhido.pesquisaMeioDeHospedagem(nome);
                hospedagemExiste = true;
            }catch(MeioDeHospedagemNaoExisteException erro){
                hospedagemExiste = false;
            }
            if(!hospedagemExiste){
                if(cont == 9){
                    Endereco enderecoDaHospedagem = new Endereco(logradouro, numero, bairro);//Criando endereco da hospedagem

                    RadioButton radioTipoHospedagem = (RadioButton) tipoHospedagem.getSelectedToggle();//Obtendo tipo de hospedagem selecionado
                    String tipo = radioTipoHospedagem.getText();

                    MeioDeHospedagem novoMeioDeHospedagem = new MeioDeHospedagem(nome, latitude, longitude, infoContato, site, tipo, enderecoDaHospedagem);

                    try{
                        municipioEscolhido.cadastrarNovaHospedagem(novoMeioDeHospedagem);//Adicionando novo meio de hospedagem na lista do municipio
                        JOptionPane.showMessageDialog(null, "Meio de hospedagem "+textNome.getText()+" cadastrado com sucesso!");
                        MainStartSis.opArq.salvaAreaDeInteresseTuristico(areaDeInteresse);//Salvando modificações

                        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
                        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMeioDeHospedagem.fxml");

                    }catch(MeioDeHospedagemJaExisteException erro){
                        setaCorETexto(txtNome,"Nome: Já existe essa hospedagem nesse municipio!",Color.RED);
                    }

                }
            }else{
                setaCorETexto(txtNome,"Nome: Essa hospedagem já existe!",Color.RED);
            }
            
        }else{
           setaCorETexto(txtMun,"Municipio:",Color.RED); 
        }

    }

    @FXML
    void btnVoltarClick(ActionEvent event) {
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMeioDeHospedagem.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuController = new FXMLMenuFuncoesController();
        areaDeInteresse = menuController.getAreaDeInteresseTuristico();
        carregarCategorias();
    }
    
    public void carregarCategorias(){
        List <Municipio> municipios = areaDeInteresse.getMunicipios();
        obsMunicipios = FXCollections.observableArrayList(municipios);
        cbMunicipios.setItems(obsMunicipios);
        
    }  
    
    public void setaCorETexto(Text texto, String textoAExibir, Color cor){
        texto.setText(textoAExibir);
        texto.setFill(cor); 
    }
    
}
