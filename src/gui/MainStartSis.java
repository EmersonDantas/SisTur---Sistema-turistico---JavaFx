package gui;

import core.*;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class MainStartSis extends Application {

    protected static ArchiveManager opArq;
    protected static AreaDeInteresseTuristico areaDeInteresseCarregada;    
    protected static WindowManager gerenciadorDeJanelas;

    public MainStartSis() {
        gerenciadorDeJanelas = new WindowManager();
        opArq = new ArchiveManager();
    }
    
    @Override
    public void start(Stage stage) {
        
        try {
            //Verifica se o arquivo existe
            this.areaDeInteresseCarregada = opArq.carregaAreaDeInteresseTurArquivo();
            //Se o arquivo ja existe ele vai para o menu
            gerenciadorDeJanelas.abreJanela("FXMLMenuFuncoes.fxml", "Sistema da área de interesse turistico");
            System.out.println("Arquivo existe, indo para menu inicial.");
        }catch (Exception ex) {//Se não, ele vai para a janela de criar nova area de interesse
            gerenciadorDeJanelas.abreJanela("FXMLAreaDeInteresse.fxml", "Nova área de interesse turistico");
            System.out.println("Arquivo não existe, indo para janela AreaDeInteresse.");
        }
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public ArchiveManager getArquiveManager(){
        return this.opArq;
    }
    
    public AreaDeInteresseTuristico getAreaDeInteresse(){
        return this.areaDeInteresseCarregada;
    }
    
    public void setAreaDeinteresse(AreaDeInteresseTuristico areaDeInteresseTuristico){
        this.areaDeInteresseCarregada = areaDeInteresseTuristico;
    }
    
    public WindowManager getWindowManager(){
        return this.gerenciadorDeJanelas;
    }
    
}