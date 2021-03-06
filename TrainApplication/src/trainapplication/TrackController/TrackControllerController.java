/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackController;

import java.util.*;
import java.io.*;
import java.net.URL;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
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
    //private variables
    private boolean switchState = true;
    private int mergeNum = 0;
    private int splitNum = 0;
    private int defaultNum = 0;
    private int id = 0;
    private boolean mergePresent = false;
    private boolean splitPresent = false;
    private boolean occupied = false;
    private boolean signalBool = true;
    private boolean crossingBool = true;
    private boolean crossingPresent = false;
    private int crossingNum = 0;
    private boolean signalPresent = true;
    private int signalNum = 0;
    private TrainApplication ta;
    private String plcFile;
    private int authority = 0;
    private double speed = 0;
    private WaysidePLC plc;
    int titleNum;
    @FXML
    private Label title;
    //constructor
    public void setTrainApp(TrainApplication ta, String plcFile, int titleNum) throws IOException {
        this.ta = ta;
        this.plcFile = plcFile;
        this.titleNum = titleNum;
        readPLC(plcFile);
    }
    //reads in data about switches signals and crossings
    //plc file is a txt file with key block numbers and information
    void readPLC(String plcFile) throws IOException {
        File plcF = new File(plcFile);
        Scanner scan = new Scanner(plcF);
        String test = scan.nextLine().trim().toLowerCase();
        if (test.equals("merge")) {
            mergePresent = true;
            mergeNum = Integer.parseInt(scan.nextLine());
            test = scan.nextLine().trim().toLowerCase();
            if (test.equals("split")) {
                splitPresent = true;
                splitNum = Integer.parseInt(scan.nextLine());
                scan.nextLine();
                defaultNum = Integer.parseInt(scan.nextLine());
            } else {
                splitPresent = false;
                splitNum = Integer.parseInt(scan.nextLine());
                defaultNum = Integer.parseInt(scan.nextLine());
            }
        } else if (test.equals("split")) {
            mergePresent = false;
            splitPresent = true;
            splitNum = Integer.parseInt(scan.nextLine());
            scan.nextLine();
            mergeNum = Integer.parseInt(scan.nextLine());
            defaultNum = Integer.parseInt(scan.nextLine());
        }
        if(scan.hasNextLine()){
            test = scan.nextLine();
            if(test.equals("crossing")){
                crossingNum = Integer.parseInt(scan.nextLine());
                crossingPresent = true;
                if(scan.nextLine().equals("signal")){
                    signalNum = Integer.parseInt(scan.nextLine());
                    signalPresent = true;
                }
            }
            if(test.equals("signal")){
                signalNum = Integer.parseInt(scan.nextLine());
                signalPresent = true;
            }
        }
        plc = new WaysidePLC(mergePresent, splitPresent);
    }
    //GUI initializer
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String line = "Green";
        if (mergePresent && splitPresent) {
            line = "Red";
        }
        trackName.setText(line);
        title.setText("Wayside Controller: " + line + " " + titleNum);
        reset();
    }
    //GUI value reset for updating
    public void reset() {
        mergeBlock.setText(Integer.toString(this.mergeNum));
        splitBlock.setText(Integer.toString(this.splitNum));
        defaultBlock.setText(Integer.toString(this.defaultNum));
        mergeBool.setText(Boolean.toString(this.mergePresent));
        splitBool.setText(Boolean.toString(this.splitPresent));
        if (signalBool) {
            signalState.setText("Green");
        } else {
            signalState.setText("Red");
        }
        if (switchState) {
            outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + defaultBlock.getText());
        } else {
            outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + mergeBlock.getText());
        }
        if(crossingPresent){
            
            if (crossingBool) {
                crossingState.setText("Raised");
            } else {
                crossingState.setText("Lowered");
            }
        }else{
            crossingState.setText("No Crossing");
        }
        outputSpeed.setText(Double.toString(speed));
        outputAuthority.setText(Integer.toString(authority));
        commandedSpeed.setText(Double.toString(speed));
        commandedAuthority.setText(Integer.toString(authority));
        if (occupied) {
            trackOccupancy.setText("Occupied");
        } else {
            trackOccupancy.setText("Not Occupied");
        }
        changeColor();
    }
    
    @FXML
    void onPLCClick(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse for PLC file");
        File file = fileChooser.showOpenDialog(null);
        String plcFile = file.getPath();
        readPLC(plcFile);
        reset();
    }

    private void changeColor() {
        if (signalBool) {
            redLight.setVisible(false);
            greenLight.setVisible(true);
        } else {
            redLight.setVisible(true);
            greenLight.setVisible(false);
        }
    }

    @FXML
    void setManualMode(ActionEvent event) {
        if (manualMode.isSelected()) {
            switchMurphyLabel.setVisible(true);
            signalMurphyLabel.setVisible(true);
            defaultSwitch.setVisible(true);
            notDefaultSwitch.setVisible(true);
            if(crossingPresent){
                crossingMurphyLabel.setVisible(true);
                raiseCrossing.setVisible(true);
                lowerCrossing.setVisible(true);
            }
            greenLightSet.setVisible(true);
            redLightSet.setVisible(true);
        } else {
            switchMurphyLabel.setVisible(false);
            signalMurphyLabel.setVisible(false);
            defaultSwitch.setVisible(false);
            notDefaultSwitch.setVisible(false);
            if(crossingPresent){
                crossingMurphyLabel.setVisible(false);
                raiseCrossing.setVisible(false);
                lowerCrossing.setVisible(false);
            }
            greenLightSet.setVisible(false);
            redLightSet.setVisible(false);
        }
    }
    //manual buttons 
    @FXML
    void manDefaultSwitchClick(ActionEvent event) {
        outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + defaultBlock.getText());
        switchState = true;
    }

    @FXML
    void manNotDefaultSwitchClick(ActionEvent event) {
        outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + mergeBlock.getText());
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
    //For CTC to set switches for maintenance
    public void setSwitch(){
        switchState = !switchState;
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (switchState) {
                            outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + defaultBlock.getText());
                        } else {
                            outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + mergeBlock.getText());
                        }
    
                    }
                });

                return null;
            }
        };
        task.setOnSucceeded(e -> {
            if (switchState) {
                outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + defaultBlock.getText());
            } else {
                outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + mergeBlock.getText());
            }
        });
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
    }
    //for Track Model to move switches as train approaches
    public boolean calculateSwitch(boolean toYard) throws InterruptedException {
        String line = "Green";
        if (mergePresent && splitPresent) {
            line = "Red";
        }
        //redundancy for vital quality
        boolean test1 = plc.calculateSwitch(ta.trkMdl.getBlockAt(line, mergeNum).isBlockOccupancy(), ta.trkMdl.getBlockAt(line, splitNum).isBlockOccupancy(), toYard);
        boolean test2 = plc.calculateSwitch(ta.trkMdl.getBlockAt(line, mergeNum).isBlockOccupancy(), ta.trkMdl.getBlockAt(line, splitNum).isBlockOccupancy(), toYard);
        boolean test3 = plc.calculateSwitch(ta.trkMdl.getBlockAt(line, mergeNum).isBlockOccupancy(), ta.trkMdl.getBlockAt(line, splitNum).isBlockOccupancy(), toYard);
        if(test1==test2)
            switchState = test1;
        else if(test1==test3)
            switchState = test3;
        else
            switchState = test2;
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (switchState) {
                            outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + defaultBlock.getText());
                        } else {
                            outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + mergeBlock.getText());
                        }
                        
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(TrackControllerController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                    }
                });

                return null;
            }
        };
        task.setOnSucceeded(e -> {
            if (switchState) {
                outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + defaultBlock.getText());
            } else {
                outputSwitch.setText("Block #" + splitBlock.getText() + " is Connected to Block #" + mergeBlock.getText());
            }
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TrackControllerController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        });
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        return switchState;

    }
    //For Track Model to set lights when section is occupied
    public boolean calculateSignal(boolean sectionOccupancy) throws InterruptedException {
        occupied = sectionOccupancy;
        //redundancy for vital quality
        boolean test1 = plc.calculateSignalCrossing(sectionOccupancy);
        boolean test2 = plc.calculateSignalCrossing(sectionOccupancy);
        boolean test3 = plc.calculateSignalCrossing(sectionOccupancy);
        if(test1==test2)
            signalBool = test1;
        else if(test1==test3)
            signalBool = test3;
        else
            signalBool = test2;
        crossingBool = signalBool;
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (signalBool) {
                            outputLights.setText("Green");
                        } else {
                            outputLights.setText("Red");
                        }
                        if(crossingPresent){
                            if(crossingBool){
                                crossingState.setText("Raised");
                            }else{
                                crossingState.setText("Lowered");
                            }
                        }
                        changeColor();
                        if(occupied)
                            trackOccupancy.setText("Occupied");
                        else
                            trackOccupancy.setText("Not Occupied");
//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(TrackControllerController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                    }
                });

                return null;
            }
        };
        task.setOnSucceeded(e -> {
            if (signalBool) {
                outputLights.setText("Green");
            } else {
                outputLights.setText("Red");
            }
            if(crossingPresent){
                if(crossingBool){
                    crossingState.setText("Raised");
                }else{
                    crossingState.setText("Lowered");
                }
            }
            changeColor();
            if(occupied)
                trackOccupancy.setText("Occupied");
            else
                trackOccupancy.setText("Not Occupied");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TrackControllerController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        });
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        return signalBool;
    }
    //Set speed and authority used by CTC to pass speed and authority to 
    //Track Controller
    public void setSpeedAuthority(int id, double speed, int authority) {
        this.id = id;
        this.speed = speed;
        this.authority = authority;
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        commandedSpeed.setText(speed+" mph");
                        commandedAuthority.setText("Block " + authority);
                        outputSpeed.setText(speed+" mph");
                        outputAuthority.setText("Block " + authority);

//                        try {
//                            Thread.sleep(3000);
//                        } catch (InterruptedException ex) {
//                            Logger.getLogger(TrackControllerController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                    }
                });

                return null;
            }
        };
        task.setOnSucceeded(e -> {
            commandedSpeed.setText(speed+" mph");
            commandedAuthority.setText(authority + " ft");
            outputSpeed.setText(speed+" mph");
            outputAuthority.setText(authority + " ft");
//            try {
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(TrackControllerController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        });
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        
    }

    //FXML IDs
    @FXML
    private RadioButton manualMode;
    @FXML
    private Label switchMurphyLabel;

    @FXML
    private Label crossingMurphyLabel;
    @FXML
    private Label signalMurphyLabel;
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

}
