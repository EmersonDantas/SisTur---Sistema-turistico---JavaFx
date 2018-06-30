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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class FXMLMostraResultadosDePesquisaController implements Initializable {
    private FXMLMenuFuncoesController menuController;
    private ArchiveManager opArq;
    private AreaDeInteresseTuristico areaDeInteresse;
    private Municipio municipioEscolhido;

    private ObservableList <Object> obsResult;

    @FXML
    private Rectangle paneApagar;

    @FXML
    private Rectangle paneDetalhes;

    @FXML
    private Rectangle paneModificar;

    @FXML
    private Rectangle paneVoltar;
    
    @FXML
    private ListView<Object> lvResultado;
    
    @FXML
    private ComboBox<Municipio> cbSelecionar;
    
    @FXML
    private Pane paneCbSelection;
    
    @FXML
    private Text txtTitulo;
    
    private ObservableList<Municipio> obsSelection;
    
    @FXML
    void btnDetalhesClick(MouseEvent event) {
        Object objeto = lvResultado.getSelectionModel().getSelectedItem();
        if (objeto != null){
            
            switch(menuController.getOperacaoResultado()){
                case(FXMLMenuFuncoesController.MOSTRAR_ATRATIVOS_CADASTRADOS):
                    AtrativoTuristico selectedItemA = (AtrativoTuristico) lvResultado.getSelectionModel().getSelectedItem();
                    JOptionPane.showMessageDialog(null, selectedItemA.getDescricao(),"Descrição", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case(FXMLMenuFuncoesController.MOSTRAR_MUNICIPIOS_CADASTRADOS):
                    Municipio selectedItemM = (Municipio) lvResultado.getSelectionModel().getSelectedItem();
                    JOptionPane.showMessageDialog(null, selectedItemM.getDescricao(),"Descrição", JOptionPane.INFORMATION_MESSAGE);

                    break;

                case(FXMLMenuFuncoesController.MOSTRAR_HOSPEDAGENS_CADASTRADAS):
                    MeioDeHospedagem selectedItemH = (MeioDeHospedagem) lvResultado.getSelectionModel().getSelectedItem();
                    JOptionPane.showMessageDialog(null, selectedItemH.getDescricao(),"Descrição", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }else{
            JOptionPane.showMessageDialog(null, "Selecione algum item!","Alerta", JOptionPane.WARNING_MESSAGE);
        }
        
    }

    @FXML
    void btnApagarDragged(MouseEvent event) {
        paneApagar.setFill(Color.CADETBLUE);
    }

    @FXML
    void btnDetalhesDragged(MouseEvent event) {
        paneDetalhes.setFill(Color.CADETBLUE);
    }
    
    
    @FXML
    void btnVoltarPassou(MouseEvent event) {
        paneVoltar.setFill(Color.CADETBLUE);
    }
    
    @FXML
    void btnModificarDragged(MouseEvent event) {
        paneModificar.setFill(Color.CADETBLUE);
    }
    
    @FXML
    void retornaACorDosPane(MouseEvent event) {
        paneDetalhes.setFill(Color.web("#0d569a"));
        paneModificar.setFill(Color.web("#0d569a"));
        paneVoltar.setFill(Color.web("#0d569a"));
        paneApagar.setFill(Color.web("#0d569a"));

    }
    
    @FXML
    void btnClickVoltarMenu(MouseEvent event) {
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLMostraResultadosDePesquisa.fxml");
    }
    
    @FXML
    void btnApagarSelecionadoClick(MouseEvent event) {
        int opcao;
        String nome;
        
        Object objeto = lvResultado.getSelectionModel().getSelectedItem();
        if (objeto != null){

            switch(menuController.getOperacaoResultado()){
                case(FXMLMenuFuncoesController.MOSTRAR_ATRATIVOS_CADASTRADOS):

                    AtrativoTuristico selectedItemA = (AtrativoTuristico) lvResultado.getSelectionModel().getSelectedItem();
                    opcao = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja apagar o atrativo "+selectedItemA.getNome()+"?","Confirmação", JOptionPane.YES_NO_OPTION);
                    if(opcao == JOptionPane.OK_OPTION){
                        nome = selectedItemA.getNome();
                        this.municipioEscolhido.apagaAtrativo(selectedItemA);
                        JOptionPane.showMessageDialog(null,  "Atrativo "+nome+" apagado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case(FXMLMenuFuncoesController.MOSTRAR_MUNICIPIOS_CADASTRADOS):
                    Municipio selectedItemM = (Municipio) lvResultado.getSelectionModel().getSelectedItem();
                    nome = selectedItemM.getNome();
                    opcao = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja apagar o municipio "+nome+"?","Confirmação", JOptionPane.YES_NO_OPTION);
                    if(opcao == JOptionPane.OK_OPTION){
                       areaDeInteresse.removeMunicipio(selectedItemM);
                       JOptionPane.showMessageDialog(null,  "Municipio "+nome+" apagado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }

                    break;

                case(FXMLMenuFuncoesController.MOSTRAR_HOSPEDAGENS_CADASTRADAS):
                    MeioDeHospedagem selectedItemH = (MeioDeHospedagem) lvResultado.getSelectionModel().getSelectedItem();
                    nome = selectedItemH.getNome();
                    opcao = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja apagar a hospedagem "+nome+"?","Confirmação", JOptionPane.YES_NO_OPTION);
                    if(opcao == JOptionPane.OK_OPTION){
                       municipioEscolhido.removeHospedagem(selectedItemH);
                       JOptionPane.showMessageDialog(null,  "Hospedagem "+nome+" apagada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
            }
            opArq.salvaAreaDeInteresseTuristico(areaDeInteresse);
            carregaListViewer();
        }else{
            JOptionPane.showMessageDialog(null, "Selecione algum item!","Alerta", JOptionPane.WARNING_MESSAGE);
        }

    }

    @FXML
    void btnDetalhesSelecionadoClick(MouseEvent event) {

    }

    @FXML
    void btnModificarSelecionadoClick(MouseEvent event) {
        
    }

    @FXML
    void cbSelecionou(ActionEvent event) {
        this.municipioEscolhido = cbSelecionar.getSelectionModel().getSelectedItem();
        carregaListViewer();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.menuController = new FXMLMenuFuncoesController();
        this.opArq = new ArchiveManager();
        this.areaDeInteresse = menuController.getAreaDeInteresseTuristico();
        
        txtTitulo.setText(menuController.getOperacaoResultado());
        
        //Determina se devo carregar a lista de municipios no ComboBox ou não
        if(menuController.getOperacaoResultado().equals(FXMLMenuFuncoesController.MOSTRAR_MUNICIPIOS_CADASTRADOS)){
            carregaListViewer();
        }else{
            paneCbSelection.setVisible(true);
            List<Municipio> municipios = this.areaDeInteresse.getMunicipios();
            obsSelection = FXCollections.observableArrayList(municipios);
            cbSelecionar.setItems(obsSelection);
        }
    }    
    
    public void carregaListViewer(){
        switch(menuController.getOperacaoResultado()){
            case(FXMLMenuFuncoesController.MOSTRAR_MUNICIPIOS_CADASTRADOS):
                obsResult = FXCollections.observableArrayList(areaDeInteresse.getMunicipios());
                lvResultado.setItems(obsResult);
                break;
            case(FXMLMenuFuncoesController.MOSTRAR_ATRATIVOS_CADASTRADOS):
                obsResult = FXCollections.observableArrayList(municipioEscolhido.getAtrativosTuristicos());
                lvResultado.setItems(obsResult);
                break;
            case(FXMLMenuFuncoesController.MOSTRAR_HOSPEDAGENS_CADASTRADAS):
                obsResult = FXCollections.observableArrayList(municipioEscolhido.getMeiosDeHospedagem());
                lvResultado.setItems(obsResult);
                break;
        }
        
    }
    
}
