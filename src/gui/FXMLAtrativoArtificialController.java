package gui;

import exceptions.AtrativoJaExisteException;
import core.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;

public class FXMLAtrativoArtificialController implements Initializable {
    @FXML
    private TextField textFundacao;

    @FXML
    private TextField textFundador;
    
    @FXML
    private Text txtFundacao;

    @FXML
    private Text txtFundador;
    
    private FXMLMenuFuncoesController menufc;
    private FXMLAtrativoTuristicoController atraTuri;

    @FXML
    void btnCadastrarAtrativoArtificial(ActionEvent event) {
        //Recebendo os dados estaticos na classe mãe
        String [] dadosBasicos = atraTuri.getInformacoesAtrativoSuper();
        
        String nome = dadosBasicos[0];
        double latitude  = Double.parseDouble(dadosBasicos[1]);
        double longitude = Double.parseDouble(dadosBasicos[2]);
        String comoChegar = dadosBasicos[3];
        String site = dadosBasicos[4];
        String infoContato = dadosBasicos[5];
        //recebendo os dados para a classe atual
        
        int cont = 0;
        
        String fundacao = textFundacao.getText();
        
        if(fundacao.isEmpty()){
            setaCorETexto(txtFundacao,"Fundacao:",Color.RED);
        }else{
            setaCorETexto(txtFundacao,"Fundacao:",Color.WHITE);
            cont++;
        }
        String fundador = textFundador.getText();
        if(fundador.isEmpty()){
            setaCorETexto(txtFundador,"Fundador:",Color.RED);
        }else{
            setaCorETexto(txtFundador,"Fundador:",Color.WHITE);
            cont++;
        }
        
        if(cont == 2){
             //Criando o novo atrativo artificial
            AtrativoTuristico novoAtrativo = new AtrativoArtificial(nome,latitude,longitude,comoChegar,site,infoContato, fundacao, fundador);
            //Tentando cadastrar atrativo artificial
            try{  
                atraTuri.getMunicipioEscolhido().cadastrarAtrativoTuristico(novoAtrativo);
                JOptionPane.showMessageDialog(null, "Atrativo artificial "+nome+" cadastrado com sucesso!");

                MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
                MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAtrativoArtificial.fxml");

                MainStartSis.opArq.salvaAreaDeInteresseTuristico(FXMLAreaDeInteresse.areaDeInteresseTuristico);//Salvando modificações
            }catch(AtrativoJaExisteException erro){
                JOptionPane.showMessageDialog(null, "Atrativo "+nome+" já existe!");
            }   
        }

    }

    @FXML
    void btnVoltarClick(ActionEvent event) {
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAtrativoArtificial.fxml");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menufc = new FXMLMenuFuncoesController();
        atraTuri = new FXMLAtrativoTuristicoController();
        // TODO
    }
    
    public void setaCorETexto(Text texto, String textoAExibir, Color cor){
        texto.setText(textoAExibir);
        texto.setFill(cor); 
    }
}
