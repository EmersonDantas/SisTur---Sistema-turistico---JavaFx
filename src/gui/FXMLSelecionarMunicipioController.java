/*

Esta classe não está sendo utilizada no projeto, mas não apaguei pois ela pode ser útil mais para frente :)

package gui;

import core.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class FXMLSelecionarMunicipioController implements Initializable {
    protected static Municipio municipioEscolhido;
    
    private ObservableList<Municipio> obsMunicipios;
    
    private ObservableList<MeioDeHospedagem> obsMeioDeHospedagem;
    
    private ObservableList<AtrativoTuristico> obsAtrativos;
    
    @FXML
    private AnchorPane anchorPaneAtrativo;
    
    @FXML
    private AnchorPane anchorPaneHospedagem;
    
    @FXML
    private ComboBox<Municipio> cbMunicipios;
    
    @FXML
    private ComboBox<MeioDeHospedagem> cbHospedagem;

    @FXML
    private ComboBox<AtrativoTuristico> cbAtrativos;
    
    @FXML
    private Text txtTitulo;
    
    @FXML
    private Text txtAtrativo;
    
    @FXML
    private Text txtHospedagem;

    @FXML
    void btnAvancarClick(ActionEvent event) {
        //Obtendo o municipio a ser pesquisado

        this.municipioEscolhido = cbMunicipios.getSelectionModel().getSelectedItem();
        
        switch(FXMLMenuFuncoesController.operacao){
            case(FXMLMenuFuncoesController.APAGAR_ATRATIVO):
                AtrativoTuristico atrativoEscolhido = cbAtrativos.getSelectionModel().getSelectedItem();
                String nomeAtrativo = atrativoEscolhido.getNome();
                municipioEscolhido.removeAtrativoTuristico(atrativoEscolhido);
                //Mostrando que apagou, abrindo e fechando janelas
                JOptionPane.showMessageDialog(null, "Atrativo "+nomeAtrativo+" apagado com sucesso!");
                MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
                MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLSelecionarMunicipio.fxml");
                break;
            
            case(FXMLMenuFuncoesController.APAGAR_HOSPEDAGEM):
                MeioDeHospedagem meioDeHospedagemEscolhido = cbHospedagem.getSelectionModel().getSelectedItem();
                String nomeHospedagem = meioDeHospedagemEscolhido.getNome();
                municipioEscolhido.removeHospedagem(meioDeHospedagemEscolhido);
                //Mostrando que apagou, abrindo e fechando janelas
                JOptionPane.showMessageDialog(null, "Hospedagem "+nomeHospedagem+" apagada com sucesso!");
                MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
                MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLSelecionarMunicipio.fxml");
                break;
            
            case(FXMLMenuFuncoesController.APAGAR_MUNICIPIO):
                String nomeMunicipio = municipioEscolhido.getNome();
                FXMLAreaDeInteresse.areaDeInteresseTuristico.removeMunicipio(municipioEscolhido);
                //Mosntrando que foi apagado
                JOptionPane.showMessageDialog(null, "Municipio "+nomeMunicipio+" apagado com sucesso!");
                MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
                MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLSelecionarMunicipio.fxml");
                break;
                
            case(FXMLMenuFuncoesController.PESQUISAR_ATRATIVO_POR_MUNICIPIO):
                //Limpando buffer
                FXMLMenuFuncoesController.resultadoDaPesquisa = "";
                //Fazendo pesquisa
                for(AtrativoTuristico atrativo: municipioEscolhido.getAtrativosTuristicos()){
                    FXMLMenuFuncoesController.resultadoDaPesquisa += atrativo.getNome() + "\n";
                }
                
                MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMostraResultadosDePesquisa.fxml", "Resultado de pesquisa");
                MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLSelecionarMunicipio.fxml");
                break;
        }
        
        //Salvando no arquivo
        MainStartSis.opArq.salvaAreaDeInteresseTuristico(FXMLAreaDeInteresse.areaDeInteresseTuristico);

    }

    @FXML
    void btnVoltarClick(ActionEvent event) {
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLSelecionarMunicipio.fxml");
    }
    
    @FXML
    void escolheuMunicipio(ActionEvent event) {
        Municipio municipioEscolhidoPesquisa = cbMunicipios.getSelectionModel().getSelectedItem();
        switch(FXMLMenuFuncoesController.operacao){
            case(FXMLMenuFuncoesController.APAGAR_ATRATIVO):
                anchorPaneAtrativo.setVisible(true);
                carregarCategoriasAtrativos(municipioEscolhidoPesquisa);
                txtAtrativo.setVisible(true);
                cbAtrativos.setVisible(true);
                break;
                
            case(FXMLMenuFuncoesController.APAGAR_MUNICIPIO):
                break;
                
            case(FXMLMenuFuncoesController.APAGAR_HOSPEDAGEM):
                anchorPaneHospedagem.setVisible(true);
                carregarCategoriasHospedagem(municipioEscolhidoPesquisa);
                txtHospedagem.setVisible(true);
                cbHospedagem.setVisible(true);
                break;
                
            case(FXMLMenuFuncoesController.PESQUISAR_ATRATIVO_POR_MUNICIPIO):
                break;
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarCategoriasMunicipios();//Chamando o carregador :)
        txtTitulo.setText(FXMLMenuFuncoesController.operacao);
    }
    
    public void carregarCategoriasMunicipios(){
        //Carregando a lista de municipios no comboBox
        List <Municipio> municipios = FXMLAreaDeInteresse.areaDeInteresseTuristico.getMunicipios();
        obsMunicipios = FXCollections.observableArrayList(municipios);
        cbMunicipios.setItems(obsMunicipios);
        
    }
    
    public void carregarCategoriasAtrativos(Municipio municipioEscolhido){
        //Carregando a lista de atrativos no comboBox
        List <AtrativoTuristico> atrativosTuristicos = municipioEscolhido.getAtrativosTuristicos();
        obsAtrativos = FXCollections.observableArrayList(atrativosTuristicos);
        cbAtrativos.setItems(obsAtrativos);
        
    }
    
    public void carregarCategoriasHospedagem(Municipio municipioEscolhido){
        //Carregando a lista de hospedagens no comboBox
        List <MeioDeHospedagem> meiosDeHospedagem = municipioEscolhido.getMeiosDeHospedagem();
        obsMeioDeHospedagem = FXCollections.observableArrayList(meiosDeHospedagem);
        cbHospedagem.setItems(obsMeioDeHospedagem);
    }
    
}
*/