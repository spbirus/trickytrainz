/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.awt.Color;
import java.awt.Paint;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;


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
//        Train t = new Train("red",1,2,3,4,5,6,7,8,9,10,11,12);
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
//        //trainTable.getItems().add(t);
//        trainTable.getItems().add(t);
        train1 = new Train("red",1,0.0,1,1,1);
        
        currSpeedVal = Double.parseDouble(currentSpeedLabel.getText());
        setpointSpeedVal = Double.parseDouble(setpointSpeedLabel.getText());
        uVal = 0;
        speedErr = Math.abs(setpointSpeedVal - currSpeedVal);
         System.out.println("first Speed Error: "+speedErr);

    }

    @FXML
    private Label currentSpeedLabel;

    @FXML
    private Label setpointSpeedLabel;

    @FXML
    private TextField kpVal;

    @FXML
    private TextField kiVal;

    @FXML
    private Label powerLabel;

    @FXML
    private Button emergBrakeButton;

    @FXML
    private Label emergBrakeLabel;
    
    @FXML
    private Label servBrakeLabel;
    
    @FXML
    private Circle lightStatusCircle;

    @FXML
    private AnchorPane testPane;
    
    @FXML
    private CheckBox testPanelCheck;
    
    @FXML
    private MenuButton trainNumDropdown;


     @FXML
    void onTrainSelectionClick(ActionEvent event) {

    }
@FXML
    void onServBrakeButtonClick(ActionEvent event) {
        if(servBrakeLabel.getText().equalsIgnoreCase("Engaged")){
//                    double curSpeed = Double.parseDouble(currentSpeedLabel.getText());
//                    double power = Double.parseDouble(requestedPowerNumber.getText());
//                    int passengers = Integer.parseInt(passengerNumber.getText());
//
//                    double newSpeed = t.calculateVelocity(power, curSpeed, 0, 3, 300, passengers); //3 is the emergency brake
//
//
//                    currentSpeedLabel.setText(String.valueOf(Math.round(100*newSpeed)/100.0));
                    servBrakeLabel.setText("Disengaging");
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException e){
                        
                    }
                    
                    servBrakeLabel.setText("Disengaged");
        }else if(servBrakeLabel.getText().equalsIgnoreCase("Disengaged")){
            servBrakeLabel.setText("Engaging");
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                        
            }
                    
            servBrakeLabel.setText("Engaged");
        }
    }
     @FXML
    void onEmergBrakeButtonClick(ActionEvent event) {
        if(emergBrakeLabel.getText().equalsIgnoreCase("Engaged")){
//                    double curSpeed = Double.parseDouble(currentSpeedLabel.getText());
//                    double power = Double.parseDouble(requestedPowerNumber.getText());
//                    int passengers = Integer.parseInt(passengerNumber.getText());
//
//                    double newSpeed = t.calculateVelocity(power, curSpeed, 0, 3, 300, passengers); //3 is the emergency brake
//
//
//                    currentSpeedLabel.setText(String.valueOf(Math.round(100*newSpeed)/100.0));
                    emergBrakeLabel.setText("Disengaging");
                    try{
                        Thread.sleep(2000);
                    }catch(InterruptedException e){
                        
                    }
                    
                    emergBrakeLabel.setText("Disengaged");
        }else if(emergBrakeLabel.getText().equalsIgnoreCase("Disengaged")){
            emergBrakeLabel.setText("Engaging");
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                        
            }
                    
            emergBrakeLabel.setText("Engaged");
        }
    }
     
    //==========================Speed and Power Variables ======================
    private final double MAX_POWER = 120; //kW
    private double currSpeedVal;
    double setpointSpeedVal;
    double uVal;
    double speedErr;
    Train train1;

    //==========================================================================

    @FXML
    void setKVals(ActionEvent event) {


        //double power = Double.parseDouble(p.getText());
        //int passengers = Integer.parseInt(passengerNumber.getText());
        double kp = Double.parseDouble(kpVal.getText());
        double ki = Double.parseDouble(kiVal.getText());
        double oldSpeedErr = speedErr;
        System.out.println("oldSpeedErr: "+oldSpeedErr);
        double oldUval = uVal;
        System.out.println("old uVal: " + oldUval);
        
        currSpeedVal = Double.parseDouble(currentSpeedLabel.getText());
        System.out.println("CurrentSpeed: "+currSpeedVal);
        setpointSpeedVal = Double.parseDouble(setpointSpeedLabel.getText());
        System.out.println("Setpoint Speed: "+setpointSpeedVal);
        speedErr = Math.abs(setpointSpeedVal - currSpeedVal);
        System.out.println("Speed Error: "+speedErr);
        


        uVal = oldUval + 0.01/2*(speedErr + oldSpeedErr); 
        System.out.println("uVal: "+ uVal);


        double oldPowerVal = Double.parseDouble(powerLabel.getText());
        System.out.println("Old Power Val: " + oldPowerVal);
        double powerVal = (kp*speedErr)+(ki*uVal);
        System.out.println("Power Val: " + powerVal);


        if(powerVal > MAX_POWER){
            System.out.println("Power Val greater than max power! Uval is set to: "+oldUval);
            uVal = oldUval;
        }
        powerVal = (kp*speedErr)+(ki*uVal);
        System.out.println("powerVal = "+kp+"*"+speedErr+"+"+ki+"*"+uVal);
        
        if(powerVal > MAX_POWER){
            powerVal = MAX_POWER;
        }
        powerLabel.setText(String.format("%.3f",powerVal));
        
        
        //========FOR TESTING PURPOSES================
        
        double v = train1.calculateVelocity(powerVal, currSpeedVal, 0, 1, setpointSpeedVal, 50);
                
        //============================================
                
                
        //String.format("%.3f", value.doubleValue())
        if(currSpeedVal < setpointSpeedVal)
            currentSpeedLabel.setText(String.valueOf(v));
        System.out.println("=================================================");

    }
    @FXML
    void turnLightsOff(ActionEvent event) {

        lightStatusCircle.setFill(javafx.scene.paint.Color.RED);
    }

    @FXML
    void turnLightsOn(ActionEvent event) {
        lightStatusCircle.setFill(javafx.scene.paint.Color.web("#2bff1f"));
    }

    @FXML
    void onTestClick(ActionEvent event) {
        if(testPanelCheck.isSelected()){
            testPane.setDisable(false);
        }else if(!testPanelCheck.isSelected()){
            testPane.setDisable(true);
        }
        
    }
}
