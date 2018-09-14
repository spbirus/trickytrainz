/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.io.IOException;
import java.net.URL;
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
    private Stage ctcStage = new Stage();
    private Stage trackControllerStage = new Stage();
    private Stage trackModelStage = new Stage();
    static Stage trainControllerStage = new Stage();
    
    private Stage trainModelStage = new Stage();
    
   @FXML
    private void handleCTCButton(ActionEvent event) throws IOException {
        
        if(! ctcStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("CTCOffice.fxml"));
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
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrackController.fxml"));
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
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainController.fxml"));
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
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrackModel.fxml"));
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
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainModel.fxml"));
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
