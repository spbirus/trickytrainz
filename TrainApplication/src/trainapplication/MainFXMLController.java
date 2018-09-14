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
    private Stage newStage;
    
   @FXML
    private void handleCTCButton(ActionEvent event) throws IOException {
        newStage = new Stage();
        
        if(! newStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("CTCOffice.fxml"));
            Scene scene = new Scene(page);
            newStage.setScene(scene);
            newStage.setTitle("CTC Office");
            newStage.setResizable(true);
        
            newStage.show();
        }
        
    }  
    
    @FXML
    private void handleTrackControllerButton(ActionEvent event) throws IOException {
        newStage = new Stage();
        
        if(! newStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrackController.fxml"));
            Scene scene = new Scene(page);
            newStage.setScene(scene);
            newStage.setTitle("Track Controller");
            newStage.setResizable(true);
        
            newStage.show();
        }
        
    }  
    
    @FXML
    private void handleTrainControllerButton(ActionEvent event) throws IOException {
        newStage = new Stage();
        
        if(! newStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainController.fxml"));
            Scene scene = new Scene(page);
            newStage.setScene(scene);
            newStage.setTitle("Train Controller");
            newStage.setResizable(true);
        
            newStage.show();
        }
        
    }  
    
    @FXML
    private void handleTrackModelButton(ActionEvent event) throws IOException {
        newStage = new Stage();
        
        if(! newStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrackModel.fxml"));
            Scene scene = new Scene(page);
            newStage.setScene(scene);
            newStage.setTitle("Track Model");
            newStage.setResizable(true);
        
            newStage.show();
        }
        
    }  
    
    @FXML
    private void handleTrainModelButton(ActionEvent event) throws IOException {
        newStage = new Stage();
        
        if(! newStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainModel.fxml"));
            Scene scene = new Scene(page);
            newStage.setScene(scene);
            newStage.setTitle("Train Model");
            newStage.setResizable(true);
        
            newStage.show();
        }
        
    }  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
