package gui;

import exceptions.*;
import javax.swing.JOptionPane;
import core.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class FXMLMunicipioController implements Initializable {
    
    @FXML
    private TextField textNome;

    @FXML
    private TextField textPopulacao;

    @FXML
    private TextField textEstado;

    @FXML
    private TextField textLatitude;

    @FXML
    private TextField textLongitude;

    @FXML
    private TextField textSite;
    
    @FXML
    private Text txtNome;
    
    @FXML
    private Text txtPopulacao;
    
    @FXML
    private Text txtLatitude;
    
    @FXML
    private Text txtLongitude;
    
    @FXML
    private Text txtEstado;

    @FXML
    private Text txtSite;
    
    private FXMLMenuFuncoesController menuFunc;
    
    private AreaDeInteresseTuristico areaDeInteresse;

    @FXML
    void btnCadastrarMunicipio(ActionEvent event){
        int cont = 0;
        int populacao = 0;
        double latitude = 0; double longitude = 0;
        
        boolean munExiste = false;
        String nome = textNome.getText();
        
        if(nome.isEmpty() && !munExiste){
           setaCorETexto(txtNome,"Nome: ",Color.RED);
        }else{
            if(!munExiste){
                setaCorETexto(txtNome,"Nome: ",Color.WHITE);
            }
           cont++;
        }

        try{
            populacao = Integer.parseInt(textPopulacao.getText());
            setaCorETexto(txtPopulacao,"População:",Color.WHITE);
            cont++;
        }catch(NumberFormatException erro){
            setaCorETexto(txtPopulacao,"População: Digite apenas números inteiros.",Color.RED);
        }
	
	List<AtrativoTuristico> atrativosTuristicos = new ArrayList<>();
	
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

	List<MeioDeHospedagem> meiosDeHospedagem = new ArrayList<>();
	String estado = textEstado.getText();
        if(estado.isEmpty()){
           setaCorETexto(txtEstado,"Estado: ",Color.RED);
        }else{
           cont++;
           setaCorETexto(txtEstado,"Estado: ",Color.WHITE); 
        }
        
	String site = textSite.getText();
        if(site.isEmpty()){
           setaCorETexto(txtSite,"Site: ",Color.RED);
        }else{
           cont++;
           setaCorETexto(txtSite,"Site: ",Color.WHITE); 
        }
        
        try {
            Municipio mun = areaDeInteresse.pesquisaMunicipio(nome);
            munExiste = true;
            setaCorETexto(txtNome,"Nome: " + "Municipio "+nome+" já está cadastrado!",Color.RED);
        } catch (MunicipioNaoExisteException ex) {
            munExiste = false;
        }
        
        if(cont == 6){//Se ele pegou os valores inteiros sem erros

            Municipio novoMunicipio = new Municipio(nome,populacao,atrativosTuristicos,latitude,longitude,meiosDeHospedagem,estado,site);
            try{
               setaCorETexto(txtNome,"Nome: ",Color.WHITE);
               areaDeInteresse.cadastrarMunicipio(novoMunicipio);//Exception
               JOptionPane.showMessageDialog(null, "Municipio "+nome+" cadastrado com sucesso!");
               
               MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
               MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMunicipio.fxml");
               
               MainStartSis.opArq.salvaAreaDeInteresseTuristico(FXMLAreaDeInteresse.areaDeInteresseTuristico);//salvando modificações
            }catch(MunicipioJaExisteException e){
               setaCorETexto(txtNome,"Nome: " + e.getMessage(),Color.RED);
            }  
        }
    }

    @FXML
    void btnVoltarClick(ActionEvent event) {
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMunicipio.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuFunc = new FXMLMenuFuncoesController();
        areaDeInteresse = menuFunc.getAreaDeInteresseTuristico();
    }
    
    public void setaCorETexto(Text texto, String textoAExibir, Color cor){
       texto.setText(textoAExibir);
       texto.setFill(cor); 
    }
    
}
