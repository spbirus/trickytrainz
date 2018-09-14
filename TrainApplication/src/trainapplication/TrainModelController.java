/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrainModelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private TextField passengersLeavingBox;
    private Button submitButton;
    private TextField speedLimitBox;
    private TextField passengersEnteringBox;
    private TextField authorityBox;
    private TextArea trainInfoBox;
    private TextArea trainControllerBox;
    private TextArea trackModelBox;
    private TextField temperatureBox;
    private TextField powerRequestBox;
    private TextField announcementBox;
    private TextField trackElevationBox;
    private MenuButton trainNumDropdown;
    private RadioButton lightsButton;
    private RadioButton brakeButton;
    private RadioButton emergencyBrakeButton;
    private RadioButton leftdoorsButton;
    private RadioButton rightdoorsButton;
    private Button activateBrakeFailure;
    private Button activateEngineFailure;
    private Button activateSignalFailure;
    private Label passengerNumber;
    private Label requestedPowerNumber;
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
    void onSubmit(ActionEvent event) {

    }

    @FXML
    void onTrainSelectionClick(ActionEvent event) {

    }

    @FXML
    void rightdoorsClick(ActionEvent event) {

    }
    
}
