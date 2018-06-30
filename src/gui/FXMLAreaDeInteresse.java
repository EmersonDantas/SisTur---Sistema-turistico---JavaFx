package gui;

import core.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLAreaDeInteresse implements Initializable{
    
    protected static Stage stageMenuFuncoes;
    
    public static AreaDeInteresseTuristico areaDeInteresseTuristico;
    private MainStartSis start;
    
    @FXML
    private TextField textNome;

    @FXML
    void btnProximoClick(ActionEvent event) throws IOException {
        areaDeInteresseTuristico = new AreaDeInteresseTuristico(textNome.getText());
        start.setAreaDeinteresse(areaDeInteresseTuristico);
        //Salvando objeto em arquivo
        
        MainStartSis.opArq.salvaAreaDeInteresseTuristico(areaDeInteresseTuristico);
        
        //Criando e abrindo janela no menu do sistema, e fechando janela atual
        MainStartSis.gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
        MainStartSis.gerenciadorDeJanelas.fechaJanela("FXMLAreaDeInteresse.fxml");
        
    }

    @FXML
    void btnVoltarClick(ActionEvent event) {
        //Coming soon
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        start = new MainStartSis();
        
        File file = new File(".files");
        file.mkdir();
    }
    
}
