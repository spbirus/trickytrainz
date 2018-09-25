/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
        //TODO: need a for loop that will go through database and add all of the trains
        Train t = new Train("red",1,2,3,4,5,6,7,8,9,10,11,12);        
        track.setCellValueFactory(new PropertyValueFactory<>("track"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        width.setCellValueFactory(new PropertyValueFactory<>("width"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        mass.setCellValueFactory(new PropertyValueFactory<>("mass"));
        crewNum.setCellValueFactory(new PropertyValueFactory<>("crewNum"));
        passNum.setCellValueFactory(new PropertyValueFactory<>("passNum"));
        maxCap.setCellValueFactory(new PropertyValueFactory<>("maxCap"));
        carNum.setCellValueFactory(new PropertyValueFactory<>("carNum"));
        doorNum.setCellValueFactory(new PropertyValueFactory<>("doorNum"));
        accelLimit.setCellValueFactory(new PropertyValueFactory<>("accelLimit"));
        deccelLimit.setCellValueFactory(new PropertyValueFactory<>("deccelLimit"));
        trainTable.getItems().add(t);
    }   
    
     @FXML
    private RadioButton emergencyBrakeButton;

    @FXML
    private TextField passengersLeavingBox;

    @FXML
    private TextField setpointSpeedBox;

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
    private Button sendSpeed;

    @FXML
    private Button sendLeavingPass;

    @FXML
    private Button submitButton;

    @FXML
    private TextArea trackModelBox1;

    @FXML
    private TextField suggestSpeedBox;

    @FXML
    private Button dactivateBrakeFailure;

    @FXML
    private Button dectivateEngineFailure;

    @FXML
    private Button deactivateSignalFailure;

    @FXML
    private TableView<Train> trainTable;
    
    @FXML
    private Button refresh;
    
    @FXML
    private TableColumn<Train, String> track;
    
    @FXML
    private TableColumn<Train, Integer> number;

    @FXML
    private TableColumn<Train, Integer> length;

    @FXML
    private TableColumn<Train, Integer> width;

    @FXML
    private TableColumn<Train, Integer> height;

    @FXML
    private TableColumn<Train, Integer> mass;

    @FXML
    private TableColumn<Train, Integer> crewNum;

    @FXML
    private TableColumn<Train, Integer> passNum;

    @FXML
    private TableColumn<Train, Integer> maxCap;

    @FXML
    private TableColumn<Train, Integer> carNum;

    @FXML
    private TableColumn<Train, Integer> doorNum;

    @FXML
    private TableColumn<Train, Integer> accelLimit;

    @FXML
    private TableColumn<Train, Integer> deccelLimit;

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
    void onSetpointSpeedSubmit(ActionEvent event) {

    }
    
    @FXML
    void onPassengersLeavingSubmit(ActionEvent event) {

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
    
    @FXML
    void onRefresh(ActionEvent event) {
        //TODO: need a for loop that will go through database and add all of the trains
        Train t = new Train("red",1,2,3,4,5,6,7,8,9,10,11,12);
        track.setCellValueFactory(new PropertyValueFactory<>("track"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        width.setCellValueFactory(new PropertyValueFactory<>("width"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        mass.setCellValueFactory(new PropertyValueFactory<>("mass"));
        crewNum.setCellValueFactory(new PropertyValueFactory<>("crewNum"));
        passNum.setCellValueFactory(new PropertyValueFactory<>("passNum"));
        maxCap.setCellValueFactory(new PropertyValueFactory<>("maxCap"));
        carNum.setCellValueFactory(new PropertyValueFactory<>("carNum"));
        doorNum.setCellValueFactory(new PropertyValueFactory<>("doorNum"));
        accelLimit.setCellValueFactory(new PropertyValueFactory<>("accelLimit"));
        deccelLimit.setCellValueFactory(new PropertyValueFactory<>("deccelLimit"));
        trainTable.getItems().add(t);
    }
    
}
