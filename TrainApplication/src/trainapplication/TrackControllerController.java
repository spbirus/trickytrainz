/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.util.*;
import java.io.*;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
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
    Track block;
    Train t;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //From other module
        block= new Track("Green", "A", 1, 100, 1.5, 55, "", 1.0, 1.0, "Open");
        trackName.setText(block.getLine());
        sectionName.setText(block.getSection());
        blockName.setText(Integer.toString(block.getBlockNumber()));
        //stationBool.setText(block.getStationPresent());
        //switchBool.setText(block.getSwitchPresent());
        //crossingBool.setText(block.getCrossingPresent());
        //crossingState.setText(block.getCrossing());
        //railState.setText(block.getRailState());
        //circuitState.setText(block.getCircuit());
        //trackOccupancy.setText(block.getOccupancy());
        //From other module
        t = new Train("red", 20, 100, 1000, 1, 5);
        outputSpeed.setText(Double.toString(t.getSpeed())+" mph");
        outputAuthority.setText(Double.toString(t.getAuthority())+" ft");
        commandedSpeed.setText(Double.toString(t.getSpeed())+" mph");
        authority.setText(Double.toString(t.getAuthority())+" ft");
        switchPosition.setText(block.getSwitchState());
        outputSwitch.setText(block.getSwitchState());
        outputLights.setText("Red");
        if(t.getBlock()==block.getBlockNumber()){
            trackOccupancy.setText("Occupied");
        }else{
            trackOccupancy.setText("Not Occupied");
        }
        changeColor();
        
    }     
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
    private Menu redLine;
    
    @FXML
    private MenuItem greenLine;
    
    @FXML
    private ObservableList<MenuItem> list = FXCollections.observableArrayList();    
    @FXML
    void onPLCClick(ActionEvent event) {
        
    }
    

    @FXML
    void trackMenuControl() {
        redLine.
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
        changeColor();
    }
    @FXML
    void murphyCloseSwitchClick(ActionEvent event) {
        switchPosition.setText("Closed");
        outputSwitch.setText("Closed");
        changeColor();
    }
    @FXML
    void murphyRaiseCrossingClick(ActionEvent event) {
        crossingState.setText("Raised");
        changeColor();
    }
    @FXML
    void murphyLowerCrossingClick(ActionEvent event) {
        crossingState.setText("Lowered");
        changeColor();
    }
    @FXML
    void murphyFixRailClick(ActionEvent event) {
        railState.setText("Functional");
        changeColor();
    }
    @FXML
    void murphyBreakRailClick(ActionEvent event) {
        railState.setText("Faulty");
        changeColor();
    }
    @FXML
    void murphyFixCircuitClick(ActionEvent event) {
        circuitState.setText("Functional");
        changeColor();
    }
    @FXML
    void murphyBreakCircuitClick(ActionEvent event) {
        circuitState.setText("Faulty");
    }
}
