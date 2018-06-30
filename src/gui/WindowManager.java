package gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WindowManager {
    private static final String[] janelasArquivos = {"FXMLAreaDeInteresse.fxml","FXMLAtrativoArtificial.fxml","FXMLMenuFuncoes.fxml","FXMLMostraResultadosDePesquisa.fxml","FXMLMeioDeHospedagem.fxml",
                                        "FXMLAtrativoTuristico.fxml","FXMLMunicipio.fxml","FXMLSelecionarMunicipio.fxml","FXMLAtrativoNatural.fxml","FXMLEventoProgramado.fxml"};
    private static final List <Stage> janelas  = new ArrayList();
    
    public WindowManager(){
        for (String janelasArquivo : janelasArquivos) {
            Stage novoStage = new Stage();
            this.janelas.add(novoStage);
        }
    }
    
    public void abreJanela(String FXML, String title){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(FXML));

            Scene scene = new Scene(root);
            scene.setRoot(root);
            Stage stage = new Stage();    
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(title);
            Image applicationIcon = new Image(getClass().getResourceAsStream("/imagens/icon.png"));
            stage.getIcons().add(applicationIcon);
            stage.show();
            substituiStage(FXML, stage);
            
            
            
        }catch(IOException erro){
            System.out.println("Janela " + FXML + " não foi carregada. Erro: "+ erro.getMessage());
        }
        
    }
    
    public void fechaJanela(String FXML){
        Stage stagePedido = procuraStage(FXML);
        if (stagePedido != null){
            stagePedido.close();
            System.out.println("Janela " + FXML + " foi fechada.");
        }else{
            System.out.println("Janela " +FXML+ " não encontrada!");
        }
    }


    public Stage procuraStage(String fxml){
        for(int k = 0; k < janelasArquivos.length;k++){
            if(janelasArquivos[k].equals(fxml)){
                return janelas.get(k);
            }
        }
        return null;
    }
    
    public void substituiStage(String FXML, Stage novoStage){
        for(int k = 0; k < janelasArquivos.length;k++){
            if(janelasArquivos[k].equals(FXML)){
                janelas.set(k,novoStage);
            }
        }
    }
    
}
