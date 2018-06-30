package gui;

import exceptions.AtrativoJaExisteException;
import core.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class FXMLAtrativoNaturalController implements Initializable {
    private FXMLMenuFuncoesController menuController;
    private final String [] tiposDeAtrativosNaturais;
    private final String [] tiposDeOrla;
    private ObservableList <String> obsTiposDeAtrativosNaturais;
    private FXMLAtrativoTuristicoController atraContr;
    
    private ObservableList <String> obsTiposDeOrla;
    
    @FXML
    private ComboBox<String> cbTipoDeAtrativoNatural;
    
    @FXML
    private ComboBox<String> cbTipoDeOrla;
    
    @FXML
    private AnchorPane AnchorPaneGeositio;

    @FXML
    private AnchorPane AnchorPanePraia;

    @FXML
    private ToggleGroup grupoPerigoTubarao;

    @FXML
    private ToggleGroup grupoPropriaParaBanho;
    
    @FXML
    private Text txtTipoAtrativo;
    
    @FXML
    private Text txtTipoDeOrla;
    
    public FXMLAtrativoNaturalController() {
        this.atraContr = new FXMLAtrativoTuristicoController();
        this.menuController = new FXMLMenuFuncoesController();
        this.tiposDeAtrativosNaturais = new String[]{"Praia", "Generico"};
        this.tiposDeOrla = new String[]{Praia.TIPO_MAR_ABERTO, Praia.TIPO_MAR_ABRIGADO, Praia.TIPO_ONDAS_FORTES, Praia.TIPO_ONDAS_MEDIAS,Praia.TIPO_PEQUENAS_ONDAS,Praia.TIPO_PISCINAS_NATURAIS};
    }

    @FXML
    void btnCadastrarAtrativoNatural(ActionEvent event) {
        
        boolean cadastrou = false;
        
        //Obtendo os dados da classe mãe diitados na janela anterior
        String [] informacoesAtrativoSuper = atraContr.getInformacoesAtrativoSuper();
        String nome = informacoesAtrativoSuper[0];
        double latitude  = Double.parseDouble(informacoesAtrativoSuper[1]);
        double longitude = Double.parseDouble(informacoesAtrativoSuper[2]);
        String comoChegar = informacoesAtrativoSuper[3];
        String site = informacoesAtrativoSuper[4];
        String infoContato = informacoesAtrativoSuper[5];
        
        String tipoDeAtrativoNaturalEscolhido = cbTipoDeAtrativoNatural.getSelectionModel().getSelectedItem(); //Obtendo o tipo de atrativo natural escolhido
        
        if(tipoDeAtrativoNaturalEscolhido == null){
            txtTipoAtrativo.setFill(Color.RED);
        }else{
            txtTipoAtrativo.setFill(Color.WHITE);
            switch(tipoDeAtrativoNaturalEscolhido){    
                case("Praia"):
                    //Obtendo dados dos botões
                                        
                    boolean perigoDeTubarao;
                    boolean propriaParaBanho;
                    
                    RadioButton radioOpcoesTubarao = (RadioButton) grupoPerigoTubarao.getSelectedToggle();//Pegando seleção do botão
                    RadioButton radioOpcoesBanho = (RadioButton) grupoPropriaParaBanho.getSelectedToggle();//Pegando seleção do botão

                    String tipoDeOrlaEscolhido = cbTipoDeOrla.getSelectionModel().getSelectedItem();//Pegando tipo de orla escolhida
                    if(tipoDeOrlaEscolhido == null){
                        txtTipoDeOrla.setFill(Color.RED);
                    }else{
                        txtTipoDeOrla.setFill(Color.WHITE);
                        //Transformando os dados em booleanos
                        perigoDeTubarao = radioOpcoesTubarao.getText().equals("Perigo de tubarão");//UOUUUUUUU melhor que if
                        propriaParaBanho = !radioOpcoesBanho.getText().equals("Imprópria para banho");//UOUUUUU
                        //Criando atrativo natural praia
                        AtrativoNatural novaPraia = new Praia(nome,latitude,longitude,comoChegar,site,infoContato,perigoDeTubarao,propriaParaBanho,tipoDeOrlaEscolhido);
                        try{
                            atraContr.getMunicipioEscolhido().cadastrarAtrativoTuristico(novaPraia);
                            cadastrou = true;
                        }catch(AtrativoJaExisteException erro){
                            JOptionPane.showMessageDialog(null, erro.getMessage());
                        }
                    }
                    break;
                case("Generico"):
                    AtrativoTuristico novoAtrativo = new AtrativoNatural(nome,latitude,longitude,comoChegar,site,infoContato);
                    try {
                        atraContr.getMunicipioEscolhido().cadastrarAtrativoTuristico(novoAtrativo);
                        cadastrou = true;
                    } catch (AtrativoJaExisteException erro) {
                        JOptionPane.showMessageDialog(null, erro.getMessage());
                    }
                    break;
                    
            }
            
        }
        
        
        
        //Mostrando que o atrativo natural foi criado
        if(cadastrou){
            JOptionPane.showMessageDialog(null, "Atrativo natural "+cbTipoDeAtrativoNatural.getSelectionModel().getSelectedItem()+" de nome "+nome+ " cadastrado com sucesso!");
            MainStartSis.opArq.salvaAreaDeInteresseTuristico(menuController.getAreaDeInteresseTuristico());//Salvando modificações
            MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
            MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAtrativoNatural.fxml");
        }

    }

    @FXML
    void btnVoltarClick(ActionEvent event) {
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAtrativoNatural.fxml");
    }

    @FXML
    void escolheuTipoDeAtrativoNatural(ActionEvent event) {
        String tipoDeAtrativoNaturalEscolhido = cbTipoDeAtrativoNatural.getSelectionModel().getSelectedItem();
        
        switch(tipoDeAtrativoNaturalEscolhido){
            case("Praia"):
                AnchorPanePraia.setVisible(true);
                carregarCategoriasTiposDeOrla();
                break;
            case("Generico"):
                AnchorPanePraia.setVisible(false);
                break;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        carregarCategoriasTiposDeAtrativos();
    }
    
    public void carregarCategoriasTiposDeAtrativos(){
        obsTiposDeAtrativosNaturais = FXCollections.observableArrayList(tiposDeAtrativosNaturais);
        cbTipoDeAtrativoNatural.setItems(obsTiposDeAtrativosNaturais);
    }
    
    public void carregarCategoriasTiposDeOrla(){
        obsTiposDeOrla = FXCollections.observableArrayList(tiposDeOrla);
        cbTipoDeOrla.setItems(obsTiposDeOrla);
    }
    
}
