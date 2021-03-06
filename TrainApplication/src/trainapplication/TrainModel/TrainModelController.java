/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrainModel;

import javafx.application.Application;
import trainapplication.Train;
import trainapplication.TrainControllerController;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import trainapplication.TrackModel.Block;
import trainapplication.TrainApplication;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrainModelController implements Initializable {

    //will need to get this data from somewhere
    private Train t;
    private TrainControllerController tc;
    private String[] ads = {"Buy Thomas and Friends TODAY!!!!!", "How much wood could a wood chuck chuck??", "GET HEALTHCARE BY DECEMBER 15th!"};

    public Train getT() {
        return t;
    }

    public void setT(Train t) {
        this.t = t;
    }

    private TrainApplication ta;

//    public TrainModelController(TrainApplication ta, int id, String line, double suggestedSpeed, int targetBlock) {
//        this.ta = ta;
//        t = new Train(line, id, suggestedSpeed, targetBlock);
//    }
    public TrainModelController() {

    }

    public void setTrainApp(TrainApplication ta, int id, String line, double suggestedSpeed, int targetBlock, double authority) {
        this.ta = ta;
        t = new Train(line, id, suggestedSpeed, targetBlock, authority);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //initialize the information in the main section 
        trackId.setText(String.valueOf(t.getLine()));
        lengthId.setText(String.valueOf(Math.floor(t.getLength() * 100) / 100));
        widthId.setText(String.valueOf(Math.floor(t.getWidth() * 100) / 100));
        massId.setText(String.valueOf(Math.floor(t.getTotalMass() * 100) / 100));
        crewId.setText(String.valueOf(t.getCrewNum()));
//        passengerId.setText(String.valueOf(t.getPassNum()));
        maxCapId.setText(String.valueOf(t.getMaxCap()));
        carId.setText(String.valueOf(t.getCarNum()));
        doorId.setText(String.valueOf(t.getDoorNum()));
        accelId.setText(String.valueOf(Math.floor(t.getAccelLimit() * 100) / 100));
        decelId.setText(String.valueOf(Math.floor(t.getDeccelLimit() * 100) / 100));
        trainId.setText(String.valueOf(t.getNumber()));
        tempId.setText(String.valueOf(70));

        //initialize some of the other data
        passengerNumber.setText(String.valueOf(t.getPassNum()));
        currentSpeedNumber.setText(String.valueOf(t.getSpeed()));

        setPointSpeed.setText(String.valueOf(t.getSpeed()));
//        setpointSpeedBox.setText(String.valueOf(t.getSpeed()));
        //power requested will come from train controller

    }

    @FXML
    private Label passLeaving;

    @FXML
    private AnchorPane testPane2;

    @FXML
    private TextField setpointSpeedBox;

    @FXML
    private Button sendSpeed;

    @FXML
    private Button sendLeavingPass;

    @FXML
    private TextField passengersLeavingBox;

    @FXML
    private Text brakeActivated;

    @FXML
    private Text engineActivated;

    @FXML
    private Text signalActivated;

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
    private Button refreshSpeed;

    @FXML
    private Button submitOther;

    @FXML
    private Label advertisement;

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
    private Label enteringpassengersId;

    @FXML
    private Label trackelevationId;

    @FXML
    private Label gradeId;

    @FXML
    private Label waitingpassengersId;

    @FXML
    private Button changeAd;

    @FXML
    private Label blockId;

    @FXML
    private Label trainId;

    @FXML
    private Label passengerNumber;

    @FXML
    private Label currentSpeedNumber;

    @FXML
    private Label requestedPowerNumber;

    @FXML
    private Label mode;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Label setPointSpeed;

    @FXML
    private CheckBox testPanelCheck;

    private double storedVelocity = 0;
    private double storedPower = 0;
    private int[] allDoors = {0, 1, 2, 3, 4, 5, 6, 7};

    // used to run the train 
    // this is the meat and the potatoes of the train model
    public void runTrain() {
        tc = (TrainControllerController) ta.trainctrs.get(t.getNumber());

        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                Thread.sleep(2000); // needed to load the first block
                int prev = 0;
                boolean atAuthority = false;
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        //close the doors
                        tc.operateDoors(allDoors, 0);
                    }
                });
                while (!atAuthority) {
                    //set the block of the train

                    Block curr = ta.trkMdl.getCurrentBlock(t.getLine(), t.getBlock());
//                    System.out.println("Current block: " + curr.getBlockNumber());
                    double bLength = curr.getBlockLength();
                    tc.setBlockDistance(bLength);
                    prev = curr.getBlockNumber();
//                    t.setPreviousBlock(t.getBlock());
                    t.setBlock(curr.getBlockNumber());

                    //check if the current block has a signal
                    if (curr.isSignalPresent()) {
                        while (!curr.getSignal()) {
//                            System.out.println("Signal is red");
                            onBrake(3);
                            t.setBrakes(3);
                        }
//                        System.out.println("Signal is now green");
                        onBrake(0);
                        t.setBrakes(0);

                    }

                    //update the track model info
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            updateBlockFromTrackModel(curr);
                        }
                    });
                    //run until there is no distance left
                    tc.isDistanceLeft = true;
                    while (tc.isDistanceLeft) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                t.setMultiplier(ta.timeMultiplier);
                                //                           curSpeed = storedVelocity; //Double.parseDouble(currentSpeedNumber.getText());
                                //                           power = storedPower; //Double.parseDouble(requestedPowerNumber.getText());
                                if (storedPower > 120) { //check for the max power
                                    storedPower = 120.00;
                                }

//                                   System.out.println(storedPower);
                                int passengers = Integer.parseInt(passengerNumber.getText());

                                double newSpeed = t.calculateVelocity(storedPower, storedVelocity, curr.getBlockGrade(), 0, curr.getSpeedLimit(), passengers);
                                storedVelocity = newSpeed;
                                tc.setCurrentSpeed(storedVelocity); //send stuff to steve
                                tc.calculatePower();
                                storedPower = tc.powerVal; //send stuff to steve
                                //        System.out.println("velocity: "+ newSpeed + "mph");

                                currentSpeedNumber.setText(String.valueOf(Math.round(100 * newSpeed) / 100.0));
                                requestedPowerNumber.setText(String.valueOf(Math.round(100 * storedPower) / 100.0));
                            }

                        });
                        Thread.sleep(10);
                    }
                    //get the next block
                    Block next = ta.trkMdl.getNextBlock(t.getLine(), t.getBlock(), t.getPreviousBlock(), t.getTarget());
                    System.out.println("Current block: " + t.getBlock() + " Previous Block: " + t.getPreviousBlock());
                    System.out.println("Next block: " + next.getBlockNumber());
                    t.setBlock(next.getBlockNumber());
                    t.setPreviousBlock(prev);
                    //set the ctc stuff to show where the train is
                    ta.ctc.updateTrainTable(t);
                    if (t.getBlock() == t.getTarget()) {
                        atAuthority = true;

//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                                //show the speed as 0
//                                currentSpeedNumber.setText("0");
//                                tc.showTrainIdleSpeed();
//                            }
//                        });
                        //at a station so deal with the passengers and doors
                        if (next.isStation()) {
                            System.out.println("At a station");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    dealWithPassengers();
                                    //open the doors 
                                    tc.operateDoors(allDoors, 1);
                                    tc.setAnnouncements(t.getLine(), t.getBlock());
                                    //close the doors
//                                    tc.operateDoors(allDoors, 0);
                                }

                            });
                        }
                        
                        if(next.getBlockNumber() == 0){
                            //we are at the yard so deleted the train
                            ta.ctc.removeTrainFromOutbound(t);
                        }
                        tc.onTargetArrival(); //done to reset the distance values in train controller to move again
                    }
                }

                //continue running train if on a schedule or auto mode
                ta.ctc.continueTrain(t);

                return null;
            }

        };

        task.setOnSucceeded(e -> {
//            label.textProperty().unbind();
            // this message will be seen.
            currentSpeedNumber.setText(String.valueOf(Math.round(100 * storedVelocity) / 100.0));
            requestedPowerNumber.setText(String.valueOf(Math.round(100 * storedPower) / 100.0));
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }

    //updated the block information in gui from the track model
    public void updateBlockFromTrackModel(Block curr) {
        speedlimitId.setText("" + curr.getSpeedLimit());
        enteringpassengersId.setText("" + curr.getPassengersBoard());
        waitingpassengersId.setText("" + curr.getPassengersStation());
        trackelevationId.setText("" + curr.getElevation());
        gradeId.setText("" + curr.getBlockGrade());
        blockId.setText("" + curr.getBlockNumber());
    }

    //deal with passengers entering and leaving the train
    public void dealWithPassengers() {
        Block block = ta.trkMdl.getCurrentBlock(t.getLine(), t.getBlock());;
        int passOnTrain = t.getPassNum();
        //passangers leaving
        Random rand = new Random();
        int leaving = rand.nextInt((passOnTrain - 0) + 1);
        passOnTrain -= leaving;
        passLeaving.setText(String.valueOf(leaving));

        //passangers entering
        int wantToGetOn = block.getPassengersBoard();
        int spotsLeft = t.getSpotsLeft();
        if (spotsLeft > wantToGetOn) {
            t.setPassNum(passOnTrain + wantToGetOn);
            ta.ctc.incrementThroughput(wantToGetOn);
        } else {
            //not enough space
            t.setPassNum(passOnTrain + spotsLeft);
            ta.ctc.incrementThroughput(spotsLeft);
            block.setPassengersRemaining(wantToGetOn - spotsLeft);
        }
//        System.out.println("Passenger stuff: " + passOnTrain + ", " + wantToGetOn + ", " + spotsLeft);
        passengerNumber.setText("" + t.getPassNum());
        massId.setText(String.valueOf(Math.floor(t.getTotalMass() * 100) / 100));
    }

    //set the brake to engaged or disengaged
    public void onBrake(int brake) {
        if (brake == 0) {
            brakeId.setText("disengaged");
            emergencyBrakeId.setText("disengaged");
        } else if (brake == 1) {
            brakeId.setText("engaged");
        } else if (brake == 3) {
            emergencyBrakeId.setText("engaged");
        }
    }

    //operate the doors
    public void operateDoors(String side, int open) {
        String text = "Open";
        if (open == 1) {
            text = "Open";
        } else if (open == 0) {
            text = "Closed";
        }
        if (side.toLowerCase().equals("left")) {
            leftdoorId.setText(text);
        } else if (side.toLowerCase().equals("right")) {
            rightdoorId.setText(text);
        }
    }

    //operate the doors
    public void onDoors(String side) {
        if (side.toLowerCase().equals("left")) {
            leftdoorId.setText("Open");
        } else if (side.toLowerCase().equals("right")) {
            rightdoorId.setText("Open");
        } else {
            leftdoorId.setText("Closed");
            rightdoorId.setText("Closed");
        }
    }

    //murphy failure methods
    public void onBrakeFailure(boolean activated) {
        brakeActivated.setVisible(activated);
    }

    public void onEngineFailure(boolean activated) {
        engineActivated.setVisible(activated);
    }

    public void onSignalFailure(boolean activated) {
        signalActivated.setVisible(activated);
        if (activated) {
            setPointSpeed.setText("NaN");
        } else {
            setPointSpeed.setText(String.valueOf(t.getSpeed()));
        }

    }

    //set the temperature
    public void setTemp(double temperature) {
        tempId.setText(String.format("%.0f", temperature));
    }

    //set the lights
    public void setLights(boolean lights) {
        if (lights) {
            lightId.setText("On");
        } else {
            lightId.setText("Off");
        }

    }

    //deal with the ads
    public void rotateThroughAds() {
        Random rand = new Random();
        int r = rand.nextInt((2 - 0) + 1);
        advertisement.setText(ads[r]);
    }

    //set the automode text
    public void setAutoMode(boolean auto) {
        if (auto) {
            mode.setText("Auto");
        } else {
            mode.setText("Manual");
        }
    }

    // Button trigger events
    @FXML
    void changeAdAction(ActionEvent event) {
        rotateThroughAds();
    }

    @FXML
    void onRefreshSpeed(ActionEvent event) {
        tc = (TrainControllerController) ta.trainctrs.get(t.getNumber());
//        double curSpeed = 0;
//        double power = 0;
        Task<Void> task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {

                for (int i = 0; i < 5000; i++) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            //                           curSpeed = storedVelocity; //Double.parseDouble(currentSpeedNumber.getText());
                            //                           power = storedPower; //Double.parseDouble(requestedPowerNumber.getText());
                            if (storedPower > 120) { //check for the max power
                                storedPower = 120.00;
                            }
                            System.out.println(storedPower);
                            int passengers = Integer.parseInt(passengerNumber.getText());

                            double newSpeed = t.calculateVelocity(storedPower, storedVelocity, 0, 0, 300, passengers);
                            storedVelocity = newSpeed;
                            tc.setCurrentSpeed(storedVelocity); //send stuff to steve
                            storedPower = tc.powerVal; //send stuff to steve
                            //        System.out.println("velocity: "+ newSpeed + "mph");

                            currentSpeedNumber.setText(String.valueOf(Math.round(100 * newSpeed) / 100.0));
                            requestedPowerNumber.setText(String.valueOf(Math.round(100 * storedPower) / 100.0));
                        }

                    });
                    Thread.sleep(10);
                }

                return null;
            }
        };

        task.setOnSucceeded(e -> {
//            label.textProperty().unbind();
            // this message will be seen.
            currentSpeedNumber.setText(String.valueOf(Math.round(100 * storedVelocity) / 100.0));
            requestedPowerNumber.setText(String.valueOf(Math.round(100 * storedPower) / 100.0));
        });

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }

    @FXML
    void leftdoorsClick(ActionEvent event) {
        if (leftdoorsButton.isSelected()) {
            leftdoorId.setText("Open");
        } else {
            leftdoorId.setText("Closed");
        }
    }

    @FXML
    void rightdoorsClick(ActionEvent event) {
        if (rightdoorsButton.isSelected()) {
            rightdoorId.setText("Open");
        } else {
            rightdoorId.setText("Closed");
        }
    }

    @FXML
    void onBrakeClick(ActionEvent event) {
        if (brakeButton.isSelected()) {
            double curSpeed = Double.parseDouble(currentSpeedNumber.getText());
            double power = Double.parseDouble(requestedPowerNumber.getText());
            int passengers = Integer.parseInt(passengerNumber.getText());

            double newSpeed = t.calculateVelocity(power, curSpeed, 0, 1, 300, passengers); //1 is the normal brake

            currentSpeedNumber.setText(String.valueOf(Math.round(100 * newSpeed) / 100.0));
            brakeId.setText("engaged");
        } else {
            brakeId.setText("disengaged");
        }
    }

    @FXML
    void onEmergencyBrakeClick(ActionEvent event) {
        if (emergencyBrakeButton.isSelected()) {
            double curSpeed = Double.parseDouble(currentSpeedNumber.getText());
            double power = Double.parseDouble(requestedPowerNumber.getText());
            int passengers = Integer.parseInt(passengerNumber.getText());

            double newSpeed = t.calculateVelocity(power, curSpeed, 0, 3, 300, passengers); //3 is the emergency brake

            currentSpeedNumber.setText(String.valueOf(Math.round(100 * newSpeed) / 100.0));
            emergencyBrakeId.setText("engaged");
        } else {
            emergencyBrakeId.setText("disengaged");
        }
    }


    @FXML
    void onLightsClick(ActionEvent event) {
        if (lightsButton.isSelected()) {
            lightId.setText("On");
        } else {
            lightId.setText("Off");
        }
    }

    @FXML
    void onSetpointSpeedSubmit(ActionEvent event) {
        tc = (TrainControllerController) ta.trainctrs.get(t.getNumber());
        if (!"".equals(setpointSpeedBox.getText())) {
            tc.setSetPointSpeed(Double.parseDouble(setpointSpeedBox.getText()));
        }
    }

    @FXML
    void onPassengersLeavingSubmit(ActionEvent event) {
        if (Integer.parseInt(passengerNumber.getText()) - Integer.parseInt(passengersLeavingBox.getText()) < 0) {
            passengerNumber.setText(String.valueOf(0));
//            passengerId.setText(String.valueOf(0));
        } else {
            passengerNumber.setText(String.valueOf(Integer.parseInt(passengerNumber.getText()) - Integer.parseInt(passengersLeavingBox.getText())));
//            passengerId.setText(String.valueOf(Integer.parseInt(passengerNumber.getText()) - Integer.parseInt(passengersLeavingBox.getText())));
        }
    }

    @FXML
    void onSubmit(ActionEvent event) throws IOException {
        if (!"".equals(currentSpeedBox.getText()) && !"".equals(powerRequestBox.getText())) {
            double curSpeed = Double.parseDouble(currentSpeedBox.getText());
            double power = Double.parseDouble(powerRequestBox.getText());
            if (power > 120) { //check for the max power
                power = 120.00;
            }
//            System.out.println(power);
//            int passengers = Integer.parseInt(passengerNumber.getText());
//
//            double newSpeed = t.calculateVelocity(power, curSpeed, 0, 0, 300, passengers);
//    //        System.out.println("velocity: "+ newSpeed + "mph");

            storedVelocity = curSpeed;
            storedPower = power;

            currentSpeedNumber.setText(String.valueOf(Math.round(100 * curSpeed) / 100.0));
            requestedPowerNumber.setText(String.valueOf(Math.round(100 * power) / 100.0));
        }

    }

    @FXML
    void onSubmitOther(ActionEvent event) throws InterruptedException {
        if (!"".equals(authorityBox.getText())) {
            int target = Integer.parseInt(authorityBox.getText());
//            authorityId.setText(String.valueOf(target));

            double distance = ta.trkMdl.getDistance(t.getBlock(), target);
            System.out.println("Distance: " + distance);
            tc.onTargetArrival();
            t.setTarget(target);
            t.setAuthority(distance);
            runTrain();
        }
    }

    @FXML
    void onTrainSelectionClick(ActionEvent event) {

    }

    @FXML
    void onTestClick(ActionEvent event) {
        if (testPanelCheck.isSelected()) {
            testPane.setDisable(false);
            testPane2.setDisable(false);
        } else if (!testPanelCheck.isSelected()) {
            testPane.setDisable(true);
            testPane2.setDisable(true);
        }

    }
}
