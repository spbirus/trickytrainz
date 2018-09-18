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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrainModelController extends MainFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     @FXML
    private RadioButton emergencyBrakeButton;

    @FXML
    private TextField passengersLeavingBox;

    @FXML
    private Button submitButton;

    @FXML
    private TextField speedLimitBox;

    @FXML
    private TextField passengersEnteringBox;

    @FXML
    private TextField authorityBox;

    @FXML
    private TextArea trainInfoBox;

    @FXML
    private TextArea trainControllerBox;

    @FXML
    private TextArea trackModelBox;

    @FXML
    private TextField temperatureBox;

    @FXML
    private TextField powerRequestBox;

    @FXML
    private TextField announcementBox;

    @FXML
    private TextField trackElevationBox;

    @FXML
    private MenuButton trainNumDropdown;

    @FXML
    private RadioButton lightsButton;

    @FXML
    private RadioButton brakeButton;

    @FXML
    private RadioButton leftdoorsButton;

    @FXML
    private RadioButton rightdoorsButton;

    @FXML
    private Button activateBrakeFailure;

    @FXML
    private Button activateEngineFailure;

    @FXML
    private Button activateSignalFailure;

    @FXML
    private Label passengerNumber;

    @FXML
    private Label requestedPowerNumber;

    @FXML
    private Label currentSpeedNumber;

    @FXML
    void leftdoorsClick(ActionEvent event) {

    }

    @FXML
    void onBrakeClick(ActionEvent event) {

    }

    @FXML
    void onBrakeFailure(ActionEvent event) {

    }

    @FXML
    void onEmergencyBrakeClick(ActionEvent event) {

    }

    @FXML
    void onEngineFailure(ActionEvent event) {

    }

    @FXML
    void onLightsClick(ActionEvent event) {

    }

    @FXML
    void onSignalFailure(ActionEvent event) {

    }

    @FXML
    void onSubmit(ActionEvent event) throws IOException {
        System.out.println("train control second " + trainControllerStage.toString());

        //send data to the train controller
        if(! MainFXMLController.trainControllerStage.isShowing()) 
        {
            AnchorPane page = (AnchorPane) FXMLLoader.load(getClass().getResource("TrainController.fxml"));
            Scene scene = new Scene(page);
            MainFXMLController.trainControllerStage.setScene(scene);
            MainFXMLController.trainControllerStage.setTitle("Train Controller");
            MainFXMLController.trainControllerStage.setResizable(true);
        
            MainFXMLController.trainControllerStage.show();
        }
    }

    @FXML
    void onTrainSelectionClick(ActionEvent event) {

    }

    @FXML
    void rightdoorsClick(ActionEvent event) {

    }
    
}
