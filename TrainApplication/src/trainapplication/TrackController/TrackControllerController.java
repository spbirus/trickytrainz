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
    Track2 block;
    Train t;
    int blockNum;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //From other module
        //block=getTrack((int)blockNumSlider.getValue());
        block= new Track2("Green", "A", 1, 100, 1.5, 55, "", 1.0, 1.0, "Open");
        trackName.setText(block.getLine());
        sectionName.setText(block.getSection());
        blockName.setText(Integer.toString(block.getBlockNumber()));
        stationBool.setText(Boolean.toString(block.getStationPresent()));
        switchBool.setText(Boolean.toString(block.getSwitchPresent()));
        crossingBool.setText(Boolean.toString(block.getCrossingPresent()));
        if(block.getCrossingState()){
            crossingState.setText("Lowered");
        }else{
            crossingState.setText("Raised");
        }
        if(block.getRailState()){
            railState.setText("Functional");
        }else{
            railState.setText("Faulty");
        }
        if(block.getCircuitState()){
            circuitState.setText("Functional");
        }else{
            circuitState.setText("Faulty");
        }
        //From other module
        t = new Train("red", 20, 100, 1000, 1, 5);
        outputSpeed.setText(Double.toString(t.getSpeed())+" mph");
        outputAuthority.setText(Double.toString(t.getAuthority())+" ft");
        commandedSpeed.setText(Double.toString(t.getSpeed())+" mph");
        authority.setText(Double.toString(t.getAuthority())+" ft");
        switchPosition.setText(Boolean.toString(block.getSwitchState()));
        outputSwitch.setText(Boolean.toString(block.getSwitchState()));
        outputLights.setText("Red");
        if(t.getBlock()==block.getBlockNumber()){
            trackOccupancy.setText("Occupied");
        }else{
            trackOccupancy.setText("Not Occupied");
        }
        changeColor();
        blockNumSlider.setMin(0);
        blockNumSlider.setMax(50);
        blockValue.setText(Integer.toString((int)blockNumSlider.getValue()));

    }  
    @FXML
    private Button sliderSubmit;
    
    @FXML
    private Label blockValue;
    
    @FXML
    private Slider blockNumSlider;
    
    @FXML
    private MenuBar waysideMenuBar;
    
    @FXML
    private MenuButton trackMenu;
    
    @FXML
    private MenuButton blockMenu;
    
     @FXML
    private MenuButton sectionMenu;
    
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
    private MenuButton redLine;
    
    @FXML
    private MenuItem greenLine;
    
    @FXML
    private ObservableList<MenuItem> list = FXCollections.observableArrayList();    
    @FXML
    void onPLCClick(ActionEvent event) {
        
    }
    

    @FXML
    void trackMenuControl() {
//        redLine.
    }
    
    void changeColor() {
        if(outputLights.getText().equals("Green")){
            redLight.setVisible(false);
            greenLight.setVisible(true);
        }else{
            redLight.setVisible(true);
            greenLight.setVisible(false);
        }
    }
    
    @FXML
    void murphyOpenSwitchClick(ActionEvent event) {
        switchPosition.setText("Open");
        outputSwitch.setText("Open");
        //getBlock(blockNum).setSwitchState(true);
        changeColor();
    }
    @FXML
    void murphyCloseSwitchClick(ActionEvent event) {
        switchPosition.setText("Closed");
        outputSwitch.setText("Closed");
        //getBlock(blockNum).setSwitchState(false);
        changeColor();
    }
    @FXML
    void murphyRaiseCrossingClick(ActionEvent event) {
        crossingState.setText("Raised");
        //getBlock(blockNum).setCrossingState(false);
        changeColor();
    }
    @FXML
    void murphyLowerCrossingClick(ActionEvent event) {
        crossingState.setText("Lowered");
        //getBlock(blockNum).setCrossingState(true);
        changeColor();
    }
    @FXML
    void murphyFixRailClick(ActionEvent event) {
        railState.setText("Functional");
        //getBlock(blockNum).setRailState(true);
        changeColor();
    }
    @FXML
    void murphyBreakRailClick(ActionEvent event) {
        railState.setText("Faulty");
        //getBlock(blockNum).setRailState(false);
        changeColor();
    }
    @FXML
    void murphyFixCircuitClick(ActionEvent event) {
        circuitState.setText("Functional");
        //getBlock(blockNum).setCircuitState(true);
        changeColor();
    }
    @FXML
    void murphyBreakCircuitClick(ActionEvent event) {
        circuitState.setText("Faulty");
        //getBlock(blockNum).setCircuitState(false);
        changeColor();
    }
    @FXML
    void sliderSubmit(ActionEvent event){
        int sliderValue=(int)blockNumSlider.getValue();
       
        //initialize(getBlock(sliderValue));
    }
    @FXML
    void sliderMoved(ActionEvent event){
        //blockValue.setText(Long.toString(Math.round(blockNumSlider.getValue())));
        
    }
    void run(Train t){
        /*
        boolean[] route = t.getRoute();
        Track2 current = t.getStart();
        Track2 end = t.getEnd();
        int b;
        while(!current.isEqual(end)){
            b = current.getBlockNumber();
            if(current.getSwitchPresent()){
                if(current.getCrossingPresent()){
                    if(current.getRailState()&&current.getCircuitState()){
                        current.setCrossingState(true);
                        current.setSwitchState(route[b]);
                    }else{
                        t.sendFailure();
                    }
                }else{
                    if(current.getRailState()&&current.getCircuitState()){
                        current.setSwitchState(route[b]);
                    }else{
                        t.sendFailure();
                    }
                }
            }else{
                if(current.getCrossingPresent()){
                    if(current.getRailState()&&current.getCircuitState()){
                        current.setCrossingState(true);
                    }else{
                        t.sendFailure();
                    }
                }else{
                     if(!current.getRailState()||!current.getCircuitState()){
                        t.sendFailure();
                    }
                }
            }
            current = current.getNextBlock();
        }
        */
    }
}
