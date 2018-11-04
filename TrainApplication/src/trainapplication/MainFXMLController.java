/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import trainapplication.CTCOffice.*;
import trainapplication.TrackController.*;
import trainapplication.TrackModel.*;
//import trainapplication.TrainController.*;
import trainapplication.TrainModel.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author burri
 */
public class MainFXMLController implements Initializable {
    //open all button
    private Stage ctcStage = new Stage();
    private Stage trackModelStage = new Stage();
    private ArrayList<Stage> trainModelStages = new ArrayList<Stage>();
    private ArrayList<Stage> trainControllerStages = new ArrayList<Stage>();
    private ArrayList<Stage> trackControllerStages = new ArrayList<Stage>();
    
    //individual button ones
    private Stage trackControllerStage = new Stage();
    private Stage trainControllerStage = new Stage();
    private Stage trainModelStage = new Stage();
    
    
    
     
    @FXML
    void handleOpenAll(ActionEvent event) throws IOException {
        //open CTC stage
        AnchorPane ctcpage = (AnchorPane) FXMLLoader.load(getClass().getResource("CTCOffice/CTCOffice.fxml"));
        Scene ctcscene = new Scene(ctcpage);
        ctcStage.setScene(ctcscene);
        ctcStage.setTitle("CTC Office");
        ctcStage.setResizable(true);
        ctcStage.show();
        
        //open track controller
        
        //open track model
//        AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrackModel/TrackModel.fxml"));
//        Scene scene = new Scene(page);
//        trackModelStage.setScene(scene);
//        trackModelStage.setTitle("Track Model");
//        trackModelStage.setResizable(true);
//        trackModelStage.show();
        
        //open train controller
//        AnchorPane trainconpage = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainController/TrainController.fxml"));
//        Scene trainconscene = new Scene(trainconpage);
//        trainControllerStage.setScene(trainconscene);
//        trainControllerStage.setTitle("Train Controller");
//        trainControllerStage.setResizable(true);
//
//        trainControllerStage.show();
        
        //open train model and train controller
        createNewTrain(1);
        
        for(Stage train : trainModelStages){
            train.show();
        }
        
        for(Stage trainCon : trainControllerStages){
            trainCon.show();
        }
        
        
        
        
    }
    
    
    
    private void createNewTrain(int number) throws IOException{
//        //create the train controller
//        TrainControllerController tr = new TrainControllerController();
//        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("TrainController/TrainController.fxml"));
//        loader1.setController(tr);
//        AnchorPane trainconpage = (AnchorPane) loader1.load();
////        AnchorPane trainconpage = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainController/TrainController.fxml"));
//        Scene trainconscene = new Scene(trainconpage);
//        Stage trc1 = new Stage();
//        trc1.setScene(trainconscene);
//        trc1.setTitle("Train Controller " + number);
//        trc1.setResizable(true);
//        trainControllerStages.add(trc1);
//        
//        
//        //create the train model
//        TrainModelController con = new TrainModelController(tr);
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainModel/TrainModel.fxml"));
//        loader.setController(con);
//        AnchorPane trainmodelpage1 = (AnchorPane) loader.load();
//        Scene trainmodelscene1 = new Scene(trainmodelpage1);
//        Stage trm1 = new Stage();
//        trm1.setScene(trainmodelscene1);
//        trm1.setTitle("Train Model "+ number);
//        trm1.setResizable(true);
//        trainModelStages.add(trm1);
    }
    
   @FXML
    private void handleCTCButton(ActionEvent event) throws IOException {
        
        if(! ctcStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("CTCOffice/CTCOffice.fxml"));
            Scene scene = new Scene(page);
            ctcStage.setScene(scene);
            ctcStage.setTitle("CTC Office");
            ctcStage.setResizable(true);
        
            ctcStage.show();
        }
        
    }  
      
    @FXML
    private void handleTrackControllerButton(ActionEvent event) throws IOException {
        
        if(! trackControllerStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrackController/TrackController.fxml"));
            Scene scene = new Scene(page);
            trackControllerStage.setScene(scene);
            trackControllerStage.setTitle("Track Controller");
            trackControllerStage.setResizable(true);
        
            trackControllerStage.show();
        }
        
    }  
    
    @FXML
    private void handleTrainControllerButton(ActionEvent event) throws IOException {
        System.out.println("train control main " + trainControllerStage.toString());
        
        if(! trainControllerStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainController/TrainController.fxml"));
            Scene scene = new Scene(page);
            trainControllerStage.setScene(scene);
            trainControllerStage.setTitle("Train Controller");
            trainControllerStage.setResizable(true);
        
            trainControllerStage.show();
        }
        
    }  
    
    @FXML
    private void handleTrackModelButton(ActionEvent event) throws IOException {

        if(! trackModelStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrackModel/TrackModel.fxml"));
            Scene scene = new Scene(page);
            trackModelStage.setScene(scene);
            trackModelStage.setTitle("Track Model");
            trackModelStage.setResizable(true);
        
            trackModelStage.show();
        }
        
    }  
    
    @FXML
    private void handleTrainModelButton(ActionEvent event) throws IOException {
        
        if(! trainModelStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainModel/TrainModel.fxml"));
            Scene scene = new Scene(page);
            trainModelStage.setScene(scene);
            trainModelStage.setTitle("Train Model");
            trainModelStage.setResizable(true);
        
            trainModelStage.show();
        }
        
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
