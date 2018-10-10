/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackController;

import trainapplication.TrackModel.Track;
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
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;


/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrackControllerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Track block;
    private Train t;
    private int blockNum;
    private Track[] blockArray = new Track[15];
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //From other module
        blockArray[0]=new Track("Green", "YARD", 0, 1, 1, true, false, false);
        blockArray[1]=new Track("Green", "A", 1, 2, 2, false, false, false);
        blockArray[2]=new Track("Green", "A", 2, 3, 3, false, true, false);
        blockArray[3]=new Track("Green", "A", 3, 4, 4, false, false, false);
        blockArray[4]=new Track("Green", "B", 4, 5, 5, false, false, false);
        blockArray[5]=new Track("Green", "B", 5, 6, 6, false, false, true);
        blockArray[6]=new Track("Green", "B", 6, 7, 7, false, false, false);
        blockArray[7]=new Track("Green", "C", 7, 8, 8, false, false, true);
        blockArray[8]=new Track("Green", "C", 8, 9, 9, false, false, false);
        blockArray[9]=new Track("Green", "C", 9, 10, 10, false, false, false);
        blockArray[10]=new Track("Green", "C", 10, 11, 11, false, true, false);
        blockArray[11]=new Track("Green", "C", 11, 12, 12, false, false, false);
        blockArray[12]=new Track("Green", "C", 12, 13, 13, true, false, false);
        blockArray[13]=new Track("Green", "D", 13, 14, 14, false, false, false);
        blockArray[14]=new Track("Green", "D", 14, 15, 15, false, false, false);
        t = new Train("Green", 20, 55, 1500, 0, 5);
        reset(0);
        
    }  
    private void reset(int num){
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
        
    }
    @FXML
    private Label switchMurphyLabel;
    
    @FXML
    private Label crossingMurphyLabel;
    @FXML
    private Button sliderSubmit;
    
    @FXML
    private Label blockValue;
    
    @FXML
    private Slider blockNumSlider;
    
    @FXML
    private MenuBar waysideMenuBar;
    
    
    @FXML
    private Label trackName;
    
    @FXML
    private Label sectionName;
    
    @FXML
    private Label blockName;
    
    @FXML
    private Label stationBool;
    
    @FXML
    private Label switchBool;
    
    @FXML
    private Label crossingBool;
    
    @FXML
    private Label crossingState;
    
    @FXML
    private Label railState;
    
    @FXML
    private Label circuitState;
    
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
    private Label switchPosition;
    
    @FXML
    private Label commandedSpeed;
    
    @FXML
    private Label authority;
    
    @FXML
    private Button openSwitch;
    
    @FXML
    private Button closeSwitch;
    
    @FXML
    private Button raiseCrossing;
    
    @FXML
    private Button lowerCrossing;
    
    @FXML
    private Button fixRail;
    
    @FXML
    private Button breakRail;
    
    @FXML
    private Button fixCircuit;
    
    @FXML
    private Button breakCircuit;
    
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
        outputLights.setText(block.getSignal());
        if(block.getSignal().equals("Green")){
            redLight.setVisible(false);
            greenLight.setVisible(true);
        }else{
            redLight.setVisible(true);
            greenLight.setVisible(false);
        }
    }
    
    @FXML
    void murphyOpenSwitchClick(ActionEvent event) {
        if(block.isSwitchPresent()){
            switchPosition.setText("Not Connected to Current Block");
            outputSwitch.setText("Not Connected to Current Block");
            block.setSwitchState(false);
        }
        changeColor();
    }
    @FXML
    void murphyCloseSwitchClick(ActionEvent event) {
        if(block.isSwitchPresent()){
            switchPosition.setText("Connected to Block #"+block.getNextOutboundBlock());
            outputSwitch.setText("Connected to Block #"+block.getNextOutboundBlock());
            block.setSwitchState(true);
        }
        changeColor();
    }
    @FXML
    void murphyRaiseCrossingClick(ActionEvent event) {
        crossingState.setText("Raised");
        block.setCrossingState(false);
        changeColor();
    }
    @FXML
    void murphyLowerCrossingClick(ActionEvent event) {
        crossingState.setText("Lowered");
        block.setCrossingState(true);
        changeColor();
    }
    @FXML
    void murphyFixRailClick(ActionEvent event) {
        railState.setText("Functional");
        block.setRailState(true);
        block.setSignal("Green");
        changeColor();
    }
    @FXML
    void murphyBreakRailClick(ActionEvent event) {
        railState.setText("Faulty");
        block.setRailState(false);
        block.setSignal("Red");
        changeColor();
    }
    @FXML
    void murphyFixCircuitClick(ActionEvent event) {
        circuitState.setText("Functional");
        block.setCircuitState(true);
        block.setSignal("Green");
        changeColor();
    }
    @FXML
    void murphyBreakCircuitClick(ActionEvent event) {
        circuitState.setText("Faulty");
        block.setCircuitState(false);
        block.setSignal("Red");
        changeColor();
    }
    @FXML
    void sliderSubmit(ActionEvent event){
        blockValue.setText("Block #" +Long.toString(Math.round(blockNumSlider.getValue())));
        reset((int)Math.round(blockNumSlider.getValue()));
    }
    @FXML
    private Button routeButton;
    @FXML
    void run(ActionEvent event){
        boolean[] route = {true,true,true,true,true,true,true,true,true,true,true,true,true,true,true};
        int b=-1;
        Track current;
        Track end = blockArray[14];
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
    }
    private boolean sendFailure(boolean[] route){
        int b = -1;
        Track current;
        Track end = blockArray[14];
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
    }
}
