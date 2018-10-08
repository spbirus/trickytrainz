/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrainModel;

import trainapplication.Train;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
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
public class TrainModelController implements Initializable {
    //will need to get this data from somewhere
    private Train t = new Train("red",12, 200, 20, 15, 500, 1, 100, 200, 7, 28, 8, 8); 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialize the information in the main section 
        trackId.setText(String.valueOf(t.getLine()));
        lengthId.setText(String.valueOf(t.getLength()));
        widthId.setText(String.valueOf(t.getWidth()));
        massId.setText(String.valueOf(t.getMass()));
        crewId.setText(String.valueOf(t.getCrewNum()));
        passengerId.setText(String.valueOf(t.getPassNum()));
        maxCapId.setText(String.valueOf(t.getMaxCap()));
        carId.setText(String.valueOf(t.getCarNum()));
        doorId.setText(String.valueOf(t.getDoorNum()));
        accelId.setText(String.valueOf(t.getAccelLimit()));
        decelId.setText(String.valueOf(t.getDeccelLimit()));
        trainId.setText(String.valueOf(t.getNumber()));
        
        tempId.setText(String.valueOf(t.getTemperature()));
//        currentpowerId.setText(String.valueOf(t)); will come from train controller
        
        
        //initialize some of the other data
        passengerNumber.setText(String.valueOf(t.getPassNum()));
        currentSpeedNumber.setText(String.valueOf(t.getSpeed()));
        //power requested will come from train controller
        
        
        
        //TODO: need a for loop that will go through database and add all of the trains
//        Train t = new Train("red",12, 200, 20, 15, 500, 1, 100, 200, 7, 28, 8, 8);        
//        track.setCellValueFactory(new PropertyValueFactory<>("line"));
//        number.setCellValueFactory(new PropertyValueFactory<>("number"));
//        length.setCellValueFactory(new PropertyValueFactory<>("length"));
//        width.setCellValueFactory(new PropertyValueFactory<>("width"));
//        height.setCellValueFactory(new PropertyValueFactory<>("height"));
//        mass.setCellValueFactory(new PropertyValueFactory<>("mass"));
//        crewNum.setCellValueFactory(new PropertyValueFactory<>("crewNum"));
//        passNum.setCellValueFactory(new PropertyValueFactory<>("passNum"));
//        maxCap.setCellValueFactory(new PropertyValueFactory<>("maxCap"));
//        carNum.setCellValueFactory(new PropertyValueFactory<>("carNum"));
//        doorNum.setCellValueFactory(new PropertyValueFactory<>("doorNum"));
//        accelLimit.setCellValueFactory(new PropertyValueFactory<>("accelLimit"));
//        deccelLimit.setCellValueFactory(new PropertyValueFactory<>("deccelLimit"));
//        trainTable.getItems().add(t);
    }   
    
    @FXML
    private Button refresh;

    @FXML
    private TextField setpointSpeedBox;

    @FXML
    private Button sendSpeed;

    @FXML
    private Button sendLeavingPass;

    @FXML
    private TextField passengersLeavingBox;

    @FXML
    private Button activateBrakeFailure;

    @FXML
    private Button dactivateBrakeFailure;

    @FXML
    private Button activateEngineFailure;

    @FXML
    private Button dectivateEngineFailure;

    @FXML
    private Button deactivateSignalFailure;

    @FXML
    private Button activateSignalFailure;

    @FXML
    private AnchorPane testPane;

    @FXML
    private TextField trackElevationBox;

    @FXML
    private TextField authorityBox;

    @FXML
    private TextField temperatureBox;

    @FXML
    private TextField powerRequestBox;

    @FXML
    private TextField announcementBox;

    @FXML
    private TextField currentSpeedBox;

    @FXML
    private RadioButton lightsButton;

    @FXML
    private RadioButton brakeButton;

    @FXML
    private RadioButton emergencyBrakeButton;

    @FXML
    private RadioButton leftdoorsButton;

    @FXML
    private RadioButton rightdoorsButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label advertisement;

    @FXML
    private MenuButton trainNumDropdown;

    @FXML
    private Label trackId;

    @FXML
    private Label lengthId;

    @FXML
    private Label widthId;

    @FXML
    private Label massId;

    @FXML
    private Label crewId;

    @FXML
    private Label passengerId;

    @FXML
    private Label maxCapId;

    @FXML
    private Label carId;

    @FXML
    private Label doorId;

    @FXML
    private Label accelId;

    @FXML
    private Label decelId;

    @FXML
    private Label announcementId;

    @FXML
    private Label leftdoorId;

    @FXML
    private Label rightdoorId;

    @FXML
    private Label lightId;

    @FXML
    private Label brakeId;

    @FXML
    private Label emergencyBrakeId;

    @FXML
    private Label tempId;

    @FXML
    private Label currentpowerId;

    @FXML
    private Label speedlimitId;

    @FXML
    private Label authorityId;

    @FXML
    private Label enteringpassengersId;

    @FXML
    private Label trackelevationId;

    @FXML
    private Label gradeId;

    @FXML
    private Label passengerNumber;

    @FXML
    private Label currentSpeedNumber;

    @FXML
    private Label requestedPowerNumber;

    @FXML
    private Label mode;
    
    @FXML
    private Label trainId;

    @FXML
    private MenuBar menuBar;

    @FXML
    private CheckBox testPanelCheck;

    @FXML
    private TableView<Train> trainTable;
   
    
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
    private Button refreshSpeed;
    
    @FXML
    private Button submitOther;
    
    private double storedVelocity = 0;
    private double storedPower = 0;
    
    @FXML
    void onRefreshSpeed(ActionEvent event) {
        double curSpeed = storedVelocity; //Double.parseDouble(currentSpeedNumber.getText());
        double power = storedPower; //Double.parseDouble(requestedPowerNumber.getText());
        if(power > 120){ //check for the max power
            power = 120.00;
        }
        System.out.println(power);
        int passengers = Integer.parseInt(passengerNumber.getText());

        double newSpeed = t.calculateVelocity(power, curSpeed, 0, 0, 300, passengers);
        storedVelocity = newSpeed;
        storedPower = power;
//        System.out.println("velocity: "+ newSpeed + "mph");

        currentSpeedNumber.setText(String.valueOf(Math.round(100*newSpeed)/100.0));
        requestedPowerNumber.setText(String.valueOf(Math.round(100*power)/100.0));
    }

    @FXML
    void leftdoorsClick(ActionEvent event) {
        if(leftdoorsButton.isSelected()){
            leftdoorId.setText("Open");
        }else{
            leftdoorId.setText("Closed");
        }
    }
    
    @FXML
    void rightdoorsClick(ActionEvent event) {
        if(rightdoorsButton.isSelected()){
            rightdoorId.setText("Open");
        }else{
            rightdoorId.setText("Closed");
        }
    }

    @FXML
    void onBrakeClick(ActionEvent event) {
        if(brakeButton.isSelected()){
            double curSpeed = Double.parseDouble(currentSpeedNumber.getText());
            double power = Double.parseDouble(requestedPowerNumber.getText());
            int passengers = Integer.parseInt(passengerNumber.getText());

            
            double newSpeed = t.calculateVelocity(power, curSpeed, 0, 1, 300, passengers); //1 is the normal brake


            currentSpeedNumber.setText(String.valueOf(Math.round(100*newSpeed)/100.0));
            brakeId.setText("engaged");
        }else{
            brakeId.setText("disengaged");
        }
    }

    @FXML
    void onBrakeFailure(ActionEvent event) {

    }

    @FXML
    void onEmergencyBrakeClick(ActionEvent event) {
        if(emergencyBrakeButton.isSelected()){
            double curSpeed = Double.parseDouble(currentSpeedNumber.getText());
            double power = Double.parseDouble(requestedPowerNumber.getText());
            int passengers = Integer.parseInt(passengerNumber.getText());

            double newSpeed = t.calculateVelocity(power, curSpeed, 0, 3, 300, passengers); //3 is the emergency brake


            currentSpeedNumber.setText(String.valueOf(Math.round(100*newSpeed)/100.0));
            emergencyBrakeId.setText("engaged");
        }else{
            emergencyBrakeId.setText("disengaged");
        }
    }

    @FXML
    void onEngineFailure(ActionEvent event) {

    }

    @FXML
    void onLightsClick(ActionEvent event) {
        if(lightsButton.isSelected()){
            lightId.setText("On");
        }else{
            lightId.setText("Off");
        }
    }

    @FXML
    void onSignalFailure(ActionEvent event) {

    }
    
    @FXML
    void onSetpointSpeedSubmit(ActionEvent event) {

    }
    
    @FXML
    void onPassengersLeavingSubmit(ActionEvent event) {
        passengerNumber.setText(String.valueOf(Integer.parseInt(passengerNumber.getText()) - Integer.parseInt(passengersLeavingBox.getText())));
        passengerId.setText(String.valueOf(Integer.parseInt(passengerNumber.getText()) - Integer.parseInt(passengersLeavingBox.getText())));
    }
    

    @FXML
    void onSubmit(ActionEvent event) throws IOException {
        if(!"".equals(currentSpeedBox.getText()) && !"".equals(powerRequestBox.getText())){
            double curSpeed = Double.parseDouble(currentSpeedBox.getText());
            double power = Double.parseDouble(powerRequestBox.getText());
            if(power > 120){ //check for the max power
                power = 120.00;
            }
//            System.out.println(power);
//            int passengers = Integer.parseInt(passengerNumber.getText());
//
//            double newSpeed = t.calculateVelocity(power, curSpeed, 0, 0, 300, passengers);
//    //        System.out.println("velocity: "+ newSpeed + "mph");
    
            storedVelocity = curSpeed;
            storedPower = power;

            currentSpeedNumber.setText(String.valueOf(Math.round(100*curSpeed)/100.0));
            requestedPowerNumber.setText(String.valueOf(Math.round(100*power)/100.0));
        }
        
               
    }
    
    @FXML
    void onSubmitOther(ActionEvent event) {
        if(!"".equals(authorityBox.getText())){
            authorityId.setText(String.valueOf(authorityBox.getText()));
        } 
        
        if(!"".equals(trackElevationBox.getText())){
            trackelevationId.setText(String.valueOf(trackElevationBox.getText()));
        }
        
        if(!"".equals(temperatureBox.getText())){
            tempId.setText(String.valueOf(temperatureBox.getText()));
        }
        
        if(!"".equals(announcementBox.getText())){
            announcementId.setText(String.valueOf(announcementBox.getText()));
        }
    }

    @FXML
    void onTrainSelectionClick(ActionEvent event) {

    }
    
    @FXML
    void onTestClick(ActionEvent event) {
        if(testPanelCheck.isSelected()){
            testPane.setDisable(false);
        }else if(!testPanelCheck.isSelected()){
            testPane.setDisable(true);
        }
        
    }
    
    @FXML
    void onRefresh(ActionEvent event) {
        //TODO: need a for loop that will go through database and add all of the trains      
        track.setCellValueFactory(new PropertyValueFactory<>("line"));
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