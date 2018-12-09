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
        passengerId.setText(String.valueOf(t.getPassNum()));
        maxCapId.setText(String.valueOf(t.getMaxCap()));
        carId.setText(String.valueOf(t.getCarNum()));
        doorId.setText(String.valueOf(t.getDoorNum()));
        accelId.setText(String.valueOf(Math.floor(t.getAccelLimit() * 100) / 100));
        decelId.setText(String.valueOf(Math.floor(t.getDeccelLimit() * 100) / 100));
        trainId.setText(String.valueOf(t.getNumber()));
        tempId.setText(String.valueOf(t.getTemperature()));

        //initialize some of the other data
        passengerNumber.setText(String.valueOf(t.getPassNum()));
        currentSpeedNumber.setText(String.valueOf(t.getSpeed()));
//        setpointSpeedBox.setText(String.valueOf(t.getSpeed()));
        //power requested will come from train controller

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
    private AnchorPane testPane2;

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
    
    // method calls
    public void setTrackInfo(){
        
    }
    
    public void runTrain(){
        tc = (TrainControllerController) ta.trainctrs.get(t.getNumber());
        Task <Void> task = new Task<Void>() {
            @Override public Void call() throws InterruptedException {
                Thread.sleep(2000); // needed to load the first block
                boolean atAuthority = false;
                while(!atAuthority){
                    //set the block of the train
                    
                    Block curr = ta.trkMdl.getCurrentBlock(t.getLine(), t.getBlock());
                    System.out.println("Current block: " + curr.getBlockNumber());
                    double bLength = curr.getBlockLength();
                    tc.setBlockDistance(bLength);
                    t.setBlock(curr.getBlockNumber());
                    
                    //run until there is no distance left
                    // TODO: need to talk to steve about this
                    tc.isDistanceLeft = true;
                    while(tc.isDistanceLeft){
//                    for(int i = 0; i < 100; i++){
                        Platform.runLater(new Runnable() {
                            @Override public void run() {
                                t.setMultiplier(ta.timeMultiplier);
        //                           curSpeed = storedVelocity; //Double.parseDouble(currentSpeedNumber.getText());
        //                           power = storedPower; //Double.parseDouble(requestedPowerNumber.getText());
                                   if(storedPower > 120){ //check for the max power
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
                                  

                                   currentSpeedNumber.setText(String.valueOf(Math.round(100*newSpeed)/100.0));
                                   requestedPowerNumber.setText(String.valueOf(Math.round(100*storedPower)/100.0));
                               }

                        });
                        Thread.sleep(10);
                    }
                    //get the next block
                    Block next = ta.trkMdl.getNextBlock(t.getLine(), t.getBlock(), t.getPreviousBlock());
                    System.out.println("Next block: " + next.getBlockNumber());
                    t.setBlock(next.getBlockNumber());
                    //set the ctc stuff to show where the train is
                    ta.ctc.updateTrainTable(t);
                    if(t.getBlock() == t.getTarget()){
                        atAuthority = true;
                        //at a station so deal with the passengers
                        if(curr.isStation()){
                            dealWithPassengers();
                            //open the doors here too
                            leftdoorId.setText("Open");
//                            Thread.sleep(2000);
                            leftdoorId.setText("Closed");
                        }
                        tc.onTargetArrival(); //done to reset the distance values in train controller to move again
                    }
                }

                return null;
            }
                
         };
        
        task.setOnSucceeded(e -> {
//            label.textProperty().unbind();
            // this message will be seen.
            currentSpeedNumber.setText(String.valueOf(Math.round(100*storedVelocity)/100.0));
            requestedPowerNumber.setText(String.valueOf(Math.round(100*storedPower)/100.0));
         });
        
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        
        
        
    }
    
    public void dealWithPassengers(){
        Block block = ta.trkMdl.getCurrentBlock(t.getLine(), t.getBlock());;
        int passOnTrain = t.getPassNum();
        int wantToGetOn =  block.getPassengersBoard();
        int spotsLeft = t.getSpotsLeft();
        if(spotsLeft > wantToGetOn){
            t.setPassNum(passOnTrain + wantToGetOn);
        }else{
            //not enough space
            t.setPassNum(passOnTrain + spotsLeft);
            block.setPassengersRemaining(wantToGetOn - spotsLeft);
        }
        passengerNumber.setText("" + t.getPassNum());
    }
    
    public void onBrake(int brake){
        if(brake == 0){
            brakeId.setText("disengaged");
            emergencyBrakeId.setText("disengaged");
        }else if(brake == 1){
            brakeId.setText("engaged");
        }else if(brake == 3){
            emergencyBrakeId.setText("engaged");
        }
    }
    
    public void onDoors(String side){
        if(side.toLowerCase().equals("left")){
            leftdoorId.setText("Open");
        }else if(side.toLowerCase().equals("right")){
            rightdoorId.setText("Open");
        }else{
            leftdoorId.setText("Closed");
            rightdoorId.setText("Closed");
        }
    }
    
    // Button trigger events
    
    @FXML
    void onRefreshSpeed(ActionEvent event) {
        tc = (TrainControllerController) ta.trainctrs.get(t.getNumber());
//        double curSpeed = 0;
//        double power = 0;
        Task <Void> task = new Task<Void>() {
            @Override public Void call() throws InterruptedException {
                
                for(int i = 0; i < 5000; i++){
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
    //                           curSpeed = storedVelocity; //Double.parseDouble(currentSpeedNumber.getText());
    //                           power = storedPower; //Double.parseDouble(requestedPowerNumber.getText());
                               if(storedPower > 120){ //check for the max power
                                   storedPower = 120.00;
                               }
                               System.out.println(storedPower);
                               int passengers = Integer.parseInt(passengerNumber.getText());

                               double newSpeed = t.calculateVelocity(storedPower, storedVelocity, 0, 0, 300, passengers);
                               storedVelocity = newSpeed;
                               tc.setCurrentSpeed(storedVelocity); //send stuff to steve
                               storedPower = tc.powerVal; //send stuff to steve
                       //        System.out.println("velocity: "+ newSpeed + "mph");

                               currentSpeedNumber.setText(String.valueOf(Math.round(100*newSpeed)/100.0));
                               requestedPowerNumber.setText(String.valueOf(Math.round(100*storedPower)/100.0));
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
            currentSpeedNumber.setText(String.valueOf(Math.round(100*storedVelocity)/100.0));
            requestedPowerNumber.setText(String.valueOf(Math.round(100*storedPower)/100.0));
         });
        
        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();
        
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
    void onBrakeFailure(ActionEvent event) {

    }


    @FXML
    void onEngineFailure(ActionEvent event) {

    }
    
    @FXML
    void onSignalFailure(ActionEvent event) {

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
    void onSetpointSpeedSubmit(ActionEvent event) {
        tc = (TrainControllerController) ta.trainctrs.get(t.getNumber());
        if(!"".equals(setpointSpeedBox.getText())){
            tc.setSetPointSpeed(Double.parseDouble(setpointSpeedBox.getText()));
        }
    }
    
    @FXML
    void onPassengersLeavingSubmit(ActionEvent event) {
        if(Integer.parseInt(passengerNumber.getText()) - Integer.parseInt(passengersLeavingBox.getText()) < 0){
            passengerNumber.setText(String.valueOf(0));
            passengerId.setText(String.valueOf(0));
        }else{
            passengerNumber.setText(String.valueOf(Integer.parseInt(passengerNumber.getText()) - Integer.parseInt(passengersLeavingBox.getText())));
            passengerId.setText(String.valueOf(Integer.parseInt(passengerNumber.getText()) - Integer.parseInt(passengersLeavingBox.getText())));
        }
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
    void onSubmitOther(ActionEvent event) throws InterruptedException {
        if(!"".equals(authorityBox.getText())){
            int target = Integer.parseInt(authorityBox.getText());
            authorityId.setText(String.valueOf(target));
            
            double distance = ta.trkMdl.getDistance(t.getBlock(), target);
            System.out.println("Distance: " + distance);
            t.setTarget(target);
            t.setAuthority(distance);
            runTrain();
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
            testPane2.setDisable(false);
        }else if(!testPanelCheck.isSelected()){
            testPane.setDisable(true);
            testPane2.setDisable(true);
        }
        
    }
    
//    @FXML
//    void onRefresh(ActionEvent event) {
//        //TODO: need a for loop that will go through database and add all of the trains      
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
//    }
    
}
