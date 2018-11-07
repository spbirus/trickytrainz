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
import javafx.stage.FileChooser;
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
    private boolean switchState=true;
    private int mergeNum=0;
    private int splitNum=0;
    private int defaultNum=0;
    private boolean mergePresent=false;
    private boolean splitPresent=false;
    private boolean occupied=false;
    private boolean signalBool=true;
    private boolean crossingBool= true;
    private TrainApplication ta;
    private String plcFile;
    private int authority=0;
    private double speed=0;
    private PLC plc;
    
    public void setTrainApp(TrainApplication ta,String plcFile)throws IOException{
        this.ta = ta;
        this.plcFile = plcFile;
        readPLC(plcFile);
    }
    void readPLC(String plcFile)throws IOException{
        File plcF = new File(plcFile);
        Scanner scan = new Scanner(plcF);
        String test = scan.nextLine().trim().toLowerCase();
        if(test.equals("merge")){
            mergePresent=true;
            mergeNum=scan.nextInt();
            test=scan.nextLine().trim().toLowerCase();
            if(test.equals("split")){
                splitPresent=true;
                splitNum=scan.nextInt();
                scan.nextLine();
                defaultNum=scan.nextInt();
            }else{
                splitPresent=false;
                splitNum = scan.nextInt();
                defaultNum = scan.nextInt();
            }
        }else if(test.equals("split")){
            mergePresent=false;
            splitPresent=true;
            splitNum = scan.nextInt();
            scan.nextLine();
            mergeNum = scan.nextInt();
            defaultNum = scan.nextInt();
        }
        plc = new PLC(mergePresent, splitPresent);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        reset();
    }  
    private void reset(){
        mergeBlock.setText(Integer.toString(this.mergeNum));
        splitBlock.setText(Integer.toString(this.splitNum));
        defaultBlock.setText(Integer.toString(this.defaultNum));
        mergeBool.setText(Boolean.toString(this.mergePresent));
        splitBool.setText(Boolean.toString(this.splitPresent));
        if(signalBool)
            signalState.setText("Green");
        else
            signalState.setText("Red");
        if(switchState)
            outputSwitch.setText("Block #" +splitBlock.getText() + " is Connected to "+ defaultBlock.getText());
        else
            outputSwitch.setText("Block #" +splitBlock.getText() + " is Connected to "+ mergeBlock.getText());
        if(crossingBool)
            crossingState.setText("Raised");
        else
            crossingState.setText("Lowered");
        outputSpeed.setText(Double.toString(speed));
        outputAuthority.setText(Integer.toString(authority));
        commandedSpeed.setText(Double.toString(speed));
        commandedAuthority.setText(Integer.toString(authority));
        if(occupied)
            trackOccupancy.setText("Occupied");
        else
            trackOccupancy.setText("Not Occupied");
        changeColor();
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
    void onPLCClick(ActionEvent event)throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse for PLC file");
        File file =fileChooser.showOpenDialog(null);
        String plcFile = file.getPath();
        readPLC(plcFile);
        reset();
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
            switchState = true;
    }
    @FXML
    void manNotDefaultSwitchClick(ActionEvent event) {
            outputSwitch.setText("Block #"+splitBlock.getText() +"is Connected to Block #"+mergeBlock.getText());
            switchState = false;
    }
    @FXML
    void manRaiseCrossingClick(ActionEvent event) {
        crossingState.setText("Raised");
        crossingBool = true;
    }
    @FXML
    void manLowerCrossingClick(ActionEvent event) {
        crossingState.setText("Lowered");
        crossingBool = false;
    }
    @FXML
    void manGreenSignalClick(ActionEvent event) {
        signalState.setText("Green");
        outputLights.setText("Green");
        signalBool = true;
        changeColor();
    }
    @FXML
    void manRedSignalClick(ActionEvent event) {
        signalState.setText("Red");
        outputLights.setText("Red");
        signalBool = false;
        changeColor();
    }
    /*boolean calculateSwitch(){
        String line = "Green";
        if(mergePresent&&splitPresent){
            line = "Red";
        }
        boolean test1= plc.calculateSwitch(TrackModel.getBlockAt(line, mergeNum).getTrackCircuit(), TrackModel.getBlockAt(line, splitNum).getTrackCircuit(), Train.getToYard());
        boolean test2 = plc.calculateSwitch(TrackModel.getBlockAt(line, mergeNum).getTrackCircuit(), TrackModel.getBlockAt(line, splitNum).getTrackCircuit(), Train.getToYard());
        boolean test3 = plc.calculateSwitch(TrackModel.getBlockAt(line, mergeNum).getTrackCircuit(), TrackModel.getBlockAt(line, splitNum).getTrackCircuit(), Train.getToYard());
        if(test1==test2)
            return test1;
        else if(test1==test3)
            return test3;
        else if(test2==test3)
            return test2;
        return true;
                
        

    }*/
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
