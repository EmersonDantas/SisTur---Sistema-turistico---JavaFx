package core;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArchiveManager {
     
     public boolean salvaAreaDeInteresseTuristico(AreaDeInteresseTuristico areaDeInteresseTuristico){
         ObjectOutputStream objeto = null;
         try{
            FileOutputStream arquivo = new FileOutputStream(".files/AreaDeInteresse.arq");
            objeto = new ObjectOutputStream(arquivo);
            objeto.writeObject(areaDeInteresseTuristico);
            objeto.flush();
            objeto.close();
            System.out.println("Objeto areaDeinteresseTuristico salvo com sucesso!");
            return true;
        }catch(IOException ex){
            System.out.println("Objeto areaDeinteresseTuristico não salvo, erro: "+ex);
            return false;
        }
         
     }
     
     public AreaDeInteresseTuristico carregaAreaDeInteresseTurArquivo() throws Exception{
        AreaDeInteresseTuristico areaDeInteresseTuristico;
        ObjectInputStream objeto = null;
        try {
            FileInputStream arquivo = new FileInputStream(".files/AreaDeInteresse.arq");
            objeto = new ObjectInputStream(arquivo);
            areaDeInteresseTuristico = (AreaDeInteresseTuristico) objeto.readObject();
            System.out.println("Arquivo encontrado!");
            return areaDeInteresseTuristico;
            
        }finally{
            if(objeto != null){
                objeto.close();  
            }
        }
     }
}
