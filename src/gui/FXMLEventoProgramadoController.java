package gui;

import core.*;
import exceptions.AtrativoJaExisteException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class FXMLEventoProgramadoController implements Initializable {
    private FXMLMenuFuncoesController menuController;
    private FXMLAtrativoTuristicoController atraContr;
    
    public FXMLEventoProgramadoController() {
        this.atraContr = new FXMLAtrativoTuristicoController();
        this.menuController = new FXMLMenuFuncoesController();
    }
    
    @FXML
    private Text txtLogra;
    
    @FXML
    private Text txtDataIni;

    @FXML
    private Text txtNum;
    
    @FXML
    private Text txtBairro;

    @FXML
    private Text txtDataFim;

    @FXML
    private TextField textogradouro;

    @FXML
    private DatePicker dataDeInicio;

    @FXML
    private DatePicker dataDeFim;

    @FXML
    private TextField textNumero;

    @FXML
    private TextField textBairro;

    @FXML
    private ToggleGroup grupoTipoDeEvento;

    @FXML
    void btnCadastrar(ActionEvent event) {
        //Obtendo dados basicos
        String [] informacoesAtrativoSuper = atraContr.getInformacoesAtrativoSuper();
        
        String nome = informacoesAtrativoSuper[0];
        double latitude  = Double.parseDouble(informacoesAtrativoSuper[1]);
        double longitude = Double.parseDouble(informacoesAtrativoSuper[2]);
        String comoChegar = informacoesAtrativoSuper[3];
        String site = informacoesAtrativoSuper[4];
        String infoContato = informacoesAtrativoSuper[5];
        
        int cont  = 0;
        
        //Criando endereco do evento
        
        String logradouro = textogradouro.getText();
        
        if(logradouro.isEmpty()){
            setaCorETexto(txtLogra,"Logradouro:",Color.RED);
        }else{
            setaCorETexto(txtLogra,"Logradouro:",Color.WHITE);
            cont++;
        }
        
        String numero = textNumero.getText();
        
        if(numero.isEmpty()){
            setaCorETexto(txtNum,"Número:",Color.RED);
        }else{
            setaCorETexto(txtNum,"Número:",Color.WHITE);
            cont++;
        }
        
        
        String bairro = textBairro.getText();
        
        if(bairro.isEmpty()){
            setaCorETexto(txtBairro,"Bairro:",Color.RED);
        }else{
            setaCorETexto(txtBairro,"Bairro:",Color.WHITE);
            cont++;
        }
        
        //Obtendo as datas
        
        String dataInicio = ""; String dataFim = "";
        if(dataDeInicio.getValue() == null){
            txtDataIni.setFill(Color.RED);
        }else{
            dataInicio = dataDeInicio.getValue().toString();
            txtDataIni.setFill(Color.WHITE);
            cont++;
        }
        
        
        if(dataDeFim.getValue() == null){
            txtDataFim.setFill(Color.RED);
        }else{
            dataFim = dataDeFim.getValue().toString();
            txtDataFim.setFill(Color.WHITE);
            cont++;
        }
        
        //Obtendo o tipo
        RadioButton radioTipoDeEvento = (RadioButton) grupoTipoDeEvento.getSelectedToggle();
        
        if(cont == 5){
            //Criando novo endereco
            Endereco enderecoDoEvento = new Endereco(logradouro, numero, bairro);
            //Criando evento programado
            AtrativoTuristico novoAtrativo = new EventoProgramado(nome,latitude,longitude,comoChegar,site,infoContato,dataInicio,dataFim,radioTipoDeEvento.getText(), enderecoDoEvento);

            //Adicionando evento no municipio escolhido
            try{
                atraContr.getMunicipioEscolhido().cadastrarAtrativoTuristico(novoAtrativo);
                        
                //Mostrando que o atrativo foi cadastrado e voltando ao menu
                JOptionPane.showMessageDialog(null, "Evento programado "+nome+" cadastrado com sucesso!");
                MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
                MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLEventoProgramado.fxml");

                //Salvando no arquivo
                MainStartSis.opArq.salvaAreaDeInteresseTuristico(menuController.getAreaDeInteresseTuristico());
            }catch(AtrativoJaExisteException erro){
                JOptionPane.showMessageDialog(null, erro.getMessage());
            } 
        }

    }

    @FXML
    void btnVoltarClick(ActionEvent event) {
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLEventoProgramado.fxml");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setaCorETexto(Text texto, String textoAExibir, Color cor){
        texto.setText(textoAExibir);
        texto.setFill(cor); 
    }
    
}
