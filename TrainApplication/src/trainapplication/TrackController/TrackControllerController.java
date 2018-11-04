/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackController;

import trainapplication.Train;
import java.util.*;
import java.io.*;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import trainapplication.TrackModel.Block;
import trainapplication.TrainApplication;


/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrackControllerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int mergeNum=2;
    private int splitNum=1;
    private int defaultNum=3;
    private boolean mergePresent=true;
    private boolean splitPresent=true;
    private TrainApplication ta;
    private String plcFile;
    private int authority;
    private double speed;
    
    public TrackControllerController(){

    }
    
    public void setTrainApp(TrainApplication ta, String plcFile) {
        this.ta = ta;
        this.plcFile = plcFile;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mergeBlock.setText(Integer.toString(this.mergeNum));
        splitBlock.setText(Integer.toString(this.splitNum));
        defaultBlock.setText(Integer.toString(this.defaultNum));
        mergeBool.setText(Boolean.toString(this.mergePresent));
        splitBool.setText(Boolean.toString(this.splitPresent));
        signalState.setText("Green");
        outputSwitch.setText("Block #" +splitBlock.getText() + " is Connected to "+ defaultBlock.getText());
        crossingState.setText("Raised");
        
    }  
    private void reset(int num){
        /*
        blockNum=num;
        block=blockArray[blockNum];
        trackName.setText(block.getLine());
        sectionName.setText(block.getSection());
        blockName.setText(Integer.toString(block.getBlockNumber()));
        stationBool.setText(Boolean.toString(block.isStationPresent()));
        switchBool.setText(Boolean.toString(block.isSwitchPresent()));
        crossingBool.setText(Boolean.toString(block.isCrossingPresent()));
        if(block.isCrossingPresent()){  
            crossingMurphyLabel.setVisible(true);
            raiseCrossing.setVisible(true);
            lowerCrossing.setVisible(true);
            if(block.isCrossingState()){
                crossingState.setText("Lowered");
            }else{
                crossingState.setText("Raised");
            }
        }else{
            crossingState.setText("No Crossing");
            crossingMurphyLabel.setVisible(false);
            raiseCrossing.setVisible(false);
            lowerCrossing.setVisible(false);
        }
        if(block.isRailState()){
            railState.setText("Functional");
        }else{
            railState.setText("Faulty");
        }
        if(block.isCircuitState()){
            circuitState.setText("Functional");
        }else{
            circuitState.setText("Faulty");
        }
        
        outputSpeed.setText(Double.toString(t.getSpeed())+" mph");
        outputAuthority.setText(Double.toString(t.getAuthority())+" ft");
        commandedSpeed.setText(Double.toString(t.getSpeed())+" mph");
        authority.setText(Double.toString(t.getAuthority())+" ft");
        if(block.isSwitchPresent()){
            switchMurphyLabel.setVisible(true);
            openSwitch.setVisible(true);
            closeSwitch.setVisible(true);
            if(block.getSwitchState()){
                switchPosition.setText("Connected to Block #"+block.getNextOutboundBlock());
                outputSwitch.setText("Connected to Block #"+block.getNextOutboundBlock());
                
            }else{
                switchPosition.setText("Not Connected to Current Block");
                outputSwitch.setText("Not Connected to Current Block");
            }
        }else{
            switchPosition.setText("No Switch on Current Block");
            outputSwitch.setText("No Switch on Current Block");
            switchMurphyLabel.setVisible(false);
            openSwitch.setVisible(false);
            closeSwitch.setVisible(false);
        }
        
        
        outputLights.setText(block.getSignal());
        if(t.getBlock()==block.getBlockNumber()){
            trackOccupancy.setText("Occupied");
        }else{
            trackOccupancy.setText("Not Occupied");
        }
        changeColor();
        blockNumSlider.setMin(0);
        blockNumSlider.setMax(14);
        blockValue.setText("Block #"+Long.toString(Math.round(blockNumSlider.getValue())));
        */
    }
    @FXML
    private RadioButton manualMode;
    @FXML
    private Label switchMurphyLabel;
    
    @FXML
    private Label crossingMurphyLabel;
    @FXML
    private Label signalMurphyLabel;
    @FXML
    private MenuBar waysideMenuBar;
    
    @FXML
    private MenuItem green1;
    
    @FXML
    private MenuItem green2;
    
    @FXML
    private MenuItem green3;
    
    @FXML
    private MenuItem green4;
    
    @FXML
    private MenuItem green5;
    
    @FXML
    private MenuItem green6;
    
    @FXML
    private MenuItem red1;
    
    @FXML
    private MenuItem red2;
    
    @FXML
    private MenuItem red3;
    
    @FXML
    private MenuItem red4;
    
    @FXML
    private MenuItem red5;
    
    @FXML
    private MenuItem red6;
    
    @FXML
    private MenuItem red7;
    
    @FXML
    private Label trackName;
    
    @FXML
    private Label mergeBool;
    
    @FXML
    private Label splitBool;
    
    @FXML
    private Label mergeBlock;
    
    @FXML
    private Label splitBlock;
    
    @FXML
    private Label defaultBlock;
    
    @FXML
    private Label crossingState;
    
    @FXML
    private Label signalState;
    
    @FXML
    private Label outputSpeed;
    
    @FXML
    private Label outputAuthority;
    
    @FXML
    private Label outputLights;
    
    @FXML
    private Label outputSwitch;
    
    @FXML
    private Label trackOccupancy;
    
    @FXML
    private Label commandedSpeed;
    
    @FXML
    private Label commandedAuthority;
    
    @FXML
    private Button defaultSwitch;
    
    @FXML
    private Button notDefaultSwitch;
    
    @FXML
    private Button raiseCrossing;
    
    @FXML
    private Button lowerCrossing;
    
    @FXML
    private Button greenLightSet;
    
    @FXML
    private Button redLightSet;
    
    @FXML
    private Circle greenLight;
    
    @FXML
    private Circle redLight;
    
    @FXML
    private Button importPLC;
    
      
    @FXML
    void onPLCClick(ActionEvent event) {
        
    }
    private void changeColor() {
        if(outputLights.getText().equals("Green")){
            redLight.setVisible(false);
            greenLight.setVisible(true);
        }else{
            redLight.setVisible(true);
            greenLight.setVisible(false);
        }
    }
    @FXML
    void setManualMode(ActionEvent event){
        if(manualMode.isSelected()){
            switchMurphyLabel.setVisible(true);
            crossingMurphyLabel.setVisible(true);
            signalMurphyLabel.setVisible(true);
            defaultSwitch.setVisible(true);
            notDefaultSwitch.setVisible(true);
            raiseCrossing.setVisible(true);
            lowerCrossing.setVisible(true);
            greenLightSet.setVisible(true);
            redLightSet.setVisible(true);
        }else{
            switchMurphyLabel.setVisible(false);
            crossingMurphyLabel.setVisible(false);
            signalMurphyLabel.setVisible(false);
            defaultSwitch.setVisible(false);
            notDefaultSwitch.setVisible(false);
            raiseCrossing.setVisible(false);
            lowerCrossing.setVisible(false);
            greenLightSet.setVisible(false);
            redLightSet.setVisible(false);
        }
    }
    @FXML
    void manDefaultSwitchClick(ActionEvent event) {
            outputSwitch.setText("Block #" +splitBlock.getText() + " is Connected to "+ defaultBlock.getText());
    }
    @FXML
    void manNotDefaultSwitchClick(ActionEvent event) {
            outputSwitch.setText("Block #"+splitBlock.getText() +"is Connected to Block #"+mergeBlock.getText());

    }
    @FXML
    void manRaiseCrossingClick(ActionEvent event) {
        crossingState.setText("Raised");
    }
    @FXML
    void manLowerCrossingClick(ActionEvent event) {
        crossingState.setText("Lowered");
    }
    @FXML
    void manGreenSignalClick(ActionEvent event) {
        signalState.setText("Green");
        outputLights.setText("Green");
        changeColor();
    }
    @FXML
    void manRedSignalClick(ActionEvent event) {
        signalState.setText("Red");
        outputLights.setText("Red");
        changeColor();
    }
    /*public void setSpeedAuthority(double speed, int authority){
        this.speed = speed;
        this.authorityVar = authority;
    }*/
    /*@FXML
    void run(ActionEvent event){
        boolean[] route = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        int b=-1;
        Block current;
        Block end = blockArray[14];
        boolean brk = true;
        while(brk){
            b++;
            current= blockArray[b];  
            if(current.isSwitchPresent()){
                if(current.isCrossingPresent()){
                    if(current.isRailState()&&current.isCircuitState()){
                        current.setCrossingState(true);
                        current.setSwitchState(route[b]);
                        current.setSignal("Green");
                    }else{
                        brk=sendFailure(route);
                    }
                }else{
                    if(current.isRailState()&&current.isCircuitState()){
                        current.setSwitchState(route[b]);
                        current.setSignal("Green");
                    }else{
                        brk=sendFailure(route);
                    }
                }
            }else{
                if(current.isCrossingPresent()){
                    if(current.isRailState()&&current.isCircuitState()){
                        current.setCrossingState(true);
                        current.setSignal("Green");
                    }else{
                        brk=sendFailure(route);
                    }
                }else{
                    if(!current.isRailState()||!current.isCircuitState()){
                        brk=sendFailure(route);
                    }else{
                        current.setSignal("Green");
                    }
                }
            }
            if(current.isBlockEqual(end.getLine(),end.getBlockNumber())){
                break;
            }
        }
        reset(blockNum);
    }*/
    /*private boolean sendFailure(boolean[] route){
        int b = -1;
        Block current;
        Block end = blockArray[14];
        while(true){
            b++;
            current=blockArray[b];
            current.setSignal("Red");
            current.setSwitchState(true);
            current.setCrossingState(true);
            if(current.isBlockEqual(end.getLine(),end.getBlockNumber())){
                break;
            }
        }
        return false;
    }*/
}
