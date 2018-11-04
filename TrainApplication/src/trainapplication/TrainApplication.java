/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import trainapplication.CTCOffice.*;
import trainapplication.TrackController.*;
import trainapplication.TrackModel.*;
//import trainapplication.TrainController.*;
import trainapplication.TrainModel.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author burri
 */
public class TrainApplication <E> extends Application {
    
    private static TrainApplication ta;
    public CTCOfficeController ctc = new CTCOfficeController(ta);
    public TrackControllerController trkCtr = new TrackControllerController(ta, "plc1.txt");
    public TrackModelController trkMdl = new TrackModelController(ta);
    public ArrayList<TrainModelController> trainmodels;
    public ArrayList<TrainControllerController> trainctls;
    

//    private ArrayList<TrainModelMain> trainmodels;
//    private ArrayList<TrainController> trainctls;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
//        launch(args)
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                ta = new TrainApplication();
        
                try {
                    ta.runThis();
                } catch (IOException ex) {
                    System.out.println(ex);
                    System.out.println("Should not happen");
                }
            }
        });;
       
    }
    
    private void runThis() throws IOException{
        Stage ctcStage = createStage("CTCOffice/CTCOffice.fxml", "CTC Office", (E) ctc);
        Stage trkCtrStage1 = createStage("TrackController/TrackController.fxml", "Track Controller 1", (E) trkCtr);
        //will need more track controllers
        Stage trkMdlStage = createStage("TrackModel/TrackModel.fxml", "Track Model", (E) trkMdl);
        
        
        
        ctcStage.show();
        trkCtrStage1.show();
        trkMdlStage.show();
        
        
    }

    private Stage createStage(String path, String title, E cont) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        loader.setController(cont);
        AnchorPane page = (AnchorPane) loader.load();
        Scene scene = new Scene(page);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(true);
        return stage;
    }
    
    public void addTrain(int id, String line, double suggestedSpeed, int targetBlock) {
        TrainModelController tr = new TrainModelController(ta, id, line, suggestedSpeed, targetBlock);
        trainmodels.add(id, tr);
        trainctls.add(id, new TrainControllerController(ta, tr.getT()));
    }
    
    public Train getTrain(int id){
        return trainmodels.get(id).getT();
    }
}
