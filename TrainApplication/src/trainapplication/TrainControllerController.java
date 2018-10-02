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
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrainControllerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO: need a for loop that will go through database and add all of the trains
        Train t = new Train("red",1,2,3,4,5,6,7,8,9,10,11,12);        
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
        //trainTable.getItems().add(t);
        trainTable.getItems().add(t);
    }   
    
    @FXML
    private Label currentSpeed;

    @FXML
    private Label setpointSpeed;

    @FXML
    private TextField kpVal;

    @FXML
    private TextField kiVal;
    
    @FXML
    private Label power;
    
    @FXML
    private TableView<Train> trainTable;

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
    private TableColumn<Train, String> track;
    
    @FXML
    private MenuButton trainNumDropdown;
    
    
     @FXML
    void onTrainSelectionClick(ActionEvent event) {

    }

     @FXML
    void onRefresh(ActionEvent event) {
        //TODO: need a for loop that will go through database and add all of the trains
        Train t = new Train("red",1,2,3,4,5,6,7,8,9,10,11,12);
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
    
    private final double MAX_POWER = 120; //kW
    
    @FXML
    void setKVals(ActionEvent event) {
        double currSpeedVal = Double.parseDouble(currentSpeed.getText());
        double setpointSpeedVal = Double.parseDouble(setpointSpeed.getText());
        
        //double power = Double.parseDouble(p.getText());
        //int passengers = Integer.parseInt(passengerNumber.getText());
        double kp = Double.parseDouble(kpVal.getText());
        double ki = Double.parseDouble(kiVal.getText());
        double oldPowerVal = Double.parseDouble(power.getText());
        
        double speedErr = setpointSpeedVal - currSpeedVal;
        double uVal= 10; //will need to be calculated
        
        
        
        double powerVal = (kp*speedErr)+(ki*uVal);
        
        
        if(powerVal > MAX_POWER){
            powerVal = oldPowerVal;
        }
        power.setText(String.valueOf(powerVal));
        

    }
    
}
