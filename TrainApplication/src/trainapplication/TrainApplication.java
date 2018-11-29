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
    public static CTCOfficeController ctc = new CTCOfficeController();
    public static TrackControllerController trkCtr1 = new TrackControllerController();
    public static TrackControllerController trkCtr2 = new TrackControllerController();    
    public static TrackControllerController trkCtr3 = new TrackControllerController();    
    public static TrackControllerController trkCtr4 = new TrackControllerController();
    public static TrackControllerController trkCtr5 = new TrackControllerController();
    public static TrackControllerController trkCtr6 = new TrackControllerController();
    public static TrackControllerController[] trkCtr = new TrackControllerController[6];

    //public TrackControllerController trkCtr = new TrackControllerController(ta, "plc1.txt");
    //public TrackModelController trkMdl = new TrackModelController(ta);
    public static TrackModelController trkMdl = new TrackModelController();
    public ArrayList<TrainModelController> trainmodels = new ArrayList<TrainModelController>();
    public ArrayList<TrainControllerController> trainctrs = new ArrayList<TrainControllerController>();
    

//    private ArrayList<TrainModelMain> trainmodels;
//    private ArrayList<TrainController> trainctls;

    public TrainApplication() {
        
    }
    
    
    
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
                ctc.setTrainApp(ta);
                try {
                    trkCtr1.setTrainApp(ta, "plc1.txt",1);
                    trkCtr[0]= trkCtr1;
                    trkCtr2.setTrainApp(ta, "plc2.txt",2);                    
                    trkCtr[1]= trkCtr2;
                    trkCtr3.setTrainApp(ta, "plc3.txt",3);
                    trkCtr[2]= trkCtr3;
                    trkCtr4.setTrainApp(ta, "plc4.txt",4);
                    trkCtr[3]= trkCtr4;
                    trkCtr5.setTrainApp(ta, "plc5.txt",5);
                    trkCtr[4]= trkCtr5;
                    trkCtr6.setTrainApp(ta, "plc6.txt",6);
                    trkCtr[5]= trkCtr6;
                } catch (IOException ex) {
                    Logger.getLogger(TrainApplication.class.getName()).log(Level.SEVERE, null, ex);
                }
                trkMdl.setTrainApp(ta);
                
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
        Stage trkCtrStage1 = createStage("TrackController/TrackController.fxml", "Track Controller 1", (E) trkCtr1);
        Stage trkCtrStage2 = createStage("TrackController/TrackController.fxml", "Track Controller 1", (E) trkCtr2);
        Stage trkCtrStage3 = createStage("TrackController/TrackController.fxml", "Track Controller 1", (E) trkCtr3);
        Stage trkCtrStage4 = createStage("TrackController/TrackController.fxml", "Track Controller 1", (E) trkCtr4);
        Stage trkCtrStage5 = createStage("TrackController/TrackController.fxml", "Track Controller 1", (E) trkCtr5);
        Stage trkCtrStage6 = createStage("TrackController/TrackController.fxml", "Track Controller 1", (E) trkCtr6);
        //will need more track controllers
        Stage trkMdlStage = createStage("TrackModel/TrackModel.fxml", "Track Model", (E) trkMdl);
        
        
        
        ctcStage.show();
        trkCtrStage2.show();
        trkMdlStage.show();
        
        
    }

    public Stage createStage(String path, String title, E cont) throws IOException {
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
    
    public void addTrain(int id, String line, double suggestedSpeed, int targetBlock, double authority) {
        TrainModelController tr = new TrainModelController();
        tr.setTrainApp(ta, id, line, suggestedSpeed, targetBlock, authority);
        trainmodels.add(id, tr);
        
        TrainControllerController trc = new TrainControllerController();
        System.out.println("Train: " + tr.getT().getLine());
        trc.setTrainApp(ta, tr.getT());
        trainctrs.add(id, trc);
    }
    
    public Train getTrain(int id){
        return trainmodels.get(id).getT();
    }
    
    public void createTrainGUI(int id) throws IOException{
        Stage trainMdlStage = createStage("TrainModel/TrainModel.fxml", "Train Model " + id, (E) trainmodels.get(id));
        trainMdlStage.show();
        
        Stage trainCtrStage = createStage("TrainController/TrainController.fxml", "Train Controller " + id, (E) trainctrs.get(id));
        trainCtrStage.show();
    }
    
    public void stuff() {
        System.out.println("HELLO FROM STUFF");
    }
    
    public void potatoes() {
        System.out.println("oahgoaishgoaishaosihgaosihgaoishoaivhviob");
    }
}
