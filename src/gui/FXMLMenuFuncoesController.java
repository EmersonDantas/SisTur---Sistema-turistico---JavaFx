package gui;

import core.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class FXMLMenuFuncoesController implements Initializable {

    protected static AreaDeInteresseTuristico areaDeInteresse;
    
    protected static String operacaoResultado = "";
    
    protected static final String MOSTRAR_MUNICIPIOS_CADASTRADOS = "Municipios Cadastrados";
    protected static final String MOSTRAR_ATRATIVOS_CADASTRADOS = "Atrativos Turisticos";
    protected static final String MOSTRAR_HOSPEDAGENS_CADASTRADAS = "Meios de Hospedagem";
    
    WindowManager gerenWin;
    
    public FXMLMenuFuncoesController() {
    }

    @FXML
    private Text txtAreaDeInteresse;
    

    @FXML
    void btnMostraHospedagens(ActionEvent event){
        operacaoResultado = MOSTRAR_HOSPEDAGENS_CADASTRADAS;
        //Criando janela que pede o muncipio e mostra as hospedagens dele, para o usuario apagar a escolhida.
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMostraResultadosDePesquisa.fxml", "Hospedagens");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMenuFuncoes.fxml");
    }
    
    @FXML
    void btnClickCadastrarHospedagem(ActionEvent event){ // cadastra uma nova hospedagem
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMeioDeHospedagem.fxml", "Cadastrar nova hospedagem");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMenuFuncoes.fxml");
    }

    @FXML
    void btnClickAtrativosPorMunicipio(ActionEvent event){      
        this.operacaoResultado = "";
        this.operacaoResultado = MOSTRAR_ATRATIVOS_CADASTRADOS;
        //Criando janela que pede o muncipio a se pesquisar, exibindo-a e fechando a atual
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMostraResultadosDePesquisa.fxml", "Atrativos Turisticos");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMenuFuncoes.fxml");
    }

    @FXML
    void btnClickCadastrarAtrativoTuristico(ActionEvent event){
        //Criando janela para cadastrar atrativo turistico, abrindo-a e fechando a atual
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLAtrativoTuristico.fxml", "Cadastrar novo atrativo turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMenuFuncoes.fxml");
    }

    @FXML
    void btnClickCadastrarMunicipio(ActionEvent event) throws IOException {
        //Criando janela para cadastrar um novo município, abrindo-a e fechando a atual
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMunicipio.fxml", "Cadastrar novo municipio");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMenuFuncoes.fxml");
    }

    @FXML
    void btnClickMunicipiosCadastrados(ActionEvent event) throws IOException {
        //Fazendo a pesquisa e salvando em uma String
        this.operacaoResultado = "";
        this.operacaoResultado = MOSTRAR_MUNICIPIOS_CADASTRADOS;
        
        //Criando janela para mostrar o resultado da pesquisa, abrindo-a e fechando a atual
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMostraResultadosDePesquisa.fxml", "Municipios");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMenuFuncoes.fxml");

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MainStartSis start = new MainStartSis();
        areaDeInteresse = start.getAreaDeInteresse();
        gerenWin = start.getWindowManager() ;
        this.txtAreaDeInteresse.setText("Área de interesse turistico: "+areaDeInteresse.getNome()); // Mostrando a area de interesse carregada
    }
    
    public String getOperacaoResultado(){
        return this.operacaoResultado;
    }
    
    public AreaDeInteresseTuristico getAreaDeInteresseTuristico(){
        return this.areaDeInteresse;
    }
    
}
