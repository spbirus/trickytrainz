/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import trainapplication.Train;
import trainapplication.TrainModel.TrainModelController;

import java.awt.Color;
import java.awt.Paint;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
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

    
    private TrainApplication ta;
    public Train t;
    
    double currSpeedVal;
    public double setpointSpeedVal = 0;
    public double powerVal;

    public TrainControllerController(){
        
    }
//    public TrainControllerController(TrainApplication ta, Train t) {
//        this.ta = ta;
//        this.t = t;
//    }
    
    public void setTrainApp(TrainApplication ta, Train t) {
        this.ta = ta;
        this.t = t;
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        setSetPointSpeed(t.getSpeed());
//        setpointSpeedLabel.setText(String.valueOf(t.getSpeed()));

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
    private Button servBrakeButton;

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
    private Button setKValsButton;
    
    @FXML
    private Button runSimulationButton;
    
     @FXML
    private TextField temperatureVal;

    @FXML
    private Label temperatureLabel;
    
    @FXML
    private TextField test_setpointSpeedVal;
    
    @FXML
    private Button setSetpointButton;
    
    @FXML
    private Button setDefaultKValsButton;
    
    @FXML
    private Label fld;

    @FXML
    private Label frd;

    @FXML
    private Label fmld;

    @FXML
    private Label fmrd;

    @FXML
    private Label bmld;

    @FXML
    private Label bmrd;

    @FXML
    private Label bld;

    @FXML
    private Label brd;
    
    @FXML
    private Label modeLabel;
    
    public void setAutoMode(boolean ifAuto){
        if(ifAuto){
            modeLabel.setText("Auto");
        }else{
            modeLabel.setText("Manual");
        }
    }
    
    //==========activates and disactivates service brake=======
    @FXML
    void onServBrakeButtonClick(ActionEvent event) {
        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        if(servBrakeLabel.getText().equalsIgnoreCase("Engaged")){
            servBrakeLabel.setText("Disengaged");
            t.setBrakes(0); //disengage the service brake
            train.onBrake(0);
        }else if(servBrakeLabel.getText().equalsIgnoreCase("Disengaged")){
            servBrakeLabel.setText("Engaged");
            train.onBrake(1);
            t.setBrakes(1); //set the service brake
        }
    }
    //==========toggles emergency brake=======
     @FXML
    void onEmergBrakeButtonClick(ActionEvent event) {
        toggleEmergBrake();
    }
    public void toggleEmergBrake(){
        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        if(emergBrakeLabel.getText().equalsIgnoreCase("Engaged")){
            emergBrakeLabel.setText("Disengaged");
            train.onBrake(0);
            t.setBrakes(0); //disengage the e brake
        }else if(emergBrakeLabel.getText().equalsIgnoreCase("Disengaged")){
            emergBrakeLabel.setText("Engaged");
            t.setBrakes(3); //set the e brake
            train.onBrake(3);
        }    }
     
    //==========================Speed,Power, Distance Variables ================
    private final double MAX_POWER = 120; //kW
    private final double serviceBrakeDecel = 1.2 * 3.2808399; //ft/s^2
    final double FPS_MS = 0.3048;
    final double MPH_MS = 0.44704;
    final double MPH_FPS = 1.46667;  
    double uVal;
    double oldUval;
    double speedErr;
    double oldSpeedErr;
    double oldPowerVal;
    double TIME_MULTIPLIER = 200;
    double ki;
    double kp;
    double DEFAULT_KP = 50;
    double DEFAULT_KI = .004; //.0052 for 10 mph .004 for 25 mph .0038 for 50 mph
    public boolean isDistanceLeft = true;
    public double distanceLeft;
    public double distanceTraveled = 0;
    public double distanceTraveledInBlock = 0;
    public double blockDistance = 0;
    
    public void setSetPointSpeed(double newSpeed){
        setpointSpeedVal = newSpeed;
        setpointSpeedLabel.setText(String.valueOf(newSpeed));
    }
    
    public void setCurrentSpeed(double currSpeed){
        currSpeedVal = currSpeed;
        currentSpeedLabel.setText(String.format("%.2f",currSpeed));
    }

    //==========================================================================

    public void initPower(){
        currSpeedVal = Double.parseDouble(currentSpeedLabel.getText());
//        setpointSpeedVal = Double.parseDouble(setpointSpeedLabel.getText());
        uVal = 0;
        oldUval = 0;
        powerVal = 0;
        oldPowerVal = 0;
        speedErr = Math.abs(setpointSpeedVal - currSpeedVal);
        oldSpeedErr = 0;
        System.out.println("first Speed Error: "+speedErr);
    }
    @FXML
    void setDefaultKVals(ActionEvent event) {
        kpVal.setText(String.format("%.4f",DEFAULT_KP));
        kiVal.setText(String.format("%.4f",DEFAULT_KI));
        kp = DEFAULT_KP;
        ki = DEFAULT_KI;
        setDefaultKValsButton.setDisable(true);
        setKValsButton.setDisable(true);
        kpVal.setEditable(false);
        kiVal.setEditable(false);
        runSimulationButton.setDisable(true);
        
    }
    @FXML
    void setKVals(ActionEvent event) {
//         set:
//        SpeedErr - check
//        Uval
//        PowerVal 
        
        //int passengers = Integer.parseInt(passengerNumber.getText());
        
        //===============grab values from UI======
        
        kp = Double.parseDouble(kpVal.getText());
        ki = Double.parseDouble(kiVal.getText());
        if(kp <= 0 || ki <= 0){
            kp = DEFAULT_KP;
            ki = DEFAULT_KI;
            kpVal.setText(String.format("%.4f",kp));
            kiVal.setText(String.format("%.4f",ki));
        }
        setKValsButton.setDisable(true);
        setDefaultKValsButton.setDisable(true);
        kpVal.setEditable(false);
        kiVal.setEditable(false);
        runSimulationButton.setDisable(true);
       
            
    }
    // DELETE WHEN DONE
//     @FXML DELETE 
//    void runSim(ActionEvent event) {
//        runSimulationButton.setDisable(true);
//        test_setpointSpeedVal.setDisable(true);
//        setSetpointButton.setDisable(true);
//        initPower();
//        System.out.println("Current Speed(mph): "+currSpeedVal);
//        System.out.println("Setpoint speed(mph): "+setpointSpeedVal);
//        
//        //======================MULTI THREAD CALL===============================
//        Task <Void> task = new Task<Void>() {
//            @Override public Void call() throws InterruptedException {
//                while(currSpeedVal < setpointSpeedVal){
//                    Platform.runLater(new Runnable() {
//                      @Override public void run() {
//                          //=================function call======================
//                           
//                          //calculatePower();
//                          
//                          
//                          //====================================================
//                      }
//                        
//                    }); 
//                    Thread.sleep(10);
//                }
//                return null;
//            }
//        };
//        
//         task.setOnSucceeded(e -> {
//
//            // this message will be seen.
//            powerLabel.setText(String.format("%.3f",powerVal));
//        
//            currentSpeedLabel.setText(String.format("%.2f",currSpeedVal));
//            
//         });
//        
//        Thread thread = new Thread(task);
//        thread.setDaemon(true);
//        thread.start();
//        
//        //======================================================================
////        for(int i = 0; i < TIME_MULTIPLIER; i++)   {
////            calculatePower();
////        }
//    }
    
    

    public void setBlockDistance(double blockDistance) {
        this.blockDistance = blockDistance;
    }
    
    //=================calculates Power using Control Law=======================
    public void calculatePower(){
        
        
        speedErr = setpointSpeedVal*MPH_MS - currSpeedVal*MPH_MS;
        
        
        //========for testing purposes:======
//        System.out.println("CurrentSpeed(m/s): "+currSpeedVal*MPH_MS);
//        System.out.println("Setpoint Speed(m/s): "+setpointSpeedVal*MPH_MS);
        
//        System.out.println("Speed Error(m/s): "+speedErr);
//        System.out.println("(Delta T)/2 Should be .005: " + t.getDeltaT()/2.0);
        //==============uVal calc===========
          if(oldPowerVal < MAX_POWER){
//              System.out.println(String.format("uVal Calculation: %f = %f + (%f/(2.0))*(%f*%f + %f*%f)",uVal,oldUval,t.getDeltaT(),speedErr,MPH_MS,oldSpeedErr,MPH_MS));
//              System.out.println(String.format("uVal Calculation: %f = %f + (%f)*(%f + %f)",uVal,oldUval,t.getDeltaT()/2.0,speedErr*MPH_MS,oldSpeedErr*MPH_MS));
              uVal = oldUval + (t.getDeltaT()/(2.0))*(speedErr*MPH_MS + oldSpeedErr*MPH_MS);
          }else{
              uVal = oldUval;
          }
        
//        System.out.println("oldUval: "+oldUval);
//        System.out.println("uVal: "+ uVal);

       //================power calc===============
       
        
        double testPower = (kp*speedErr*MPH_MS)+(ki*uVal);
        //powerVal = (kp*speedErr)+(ki*uVal);
        if(testPower < MAX_POWER){
            powerVal = testPower;
//            System.out.println(String.format("Test Power Calculation: %f = %f*%f*%f+%f*%f",testPower,kp,speedErr,MPH_MS,ki,uVal));
//            System.out.println(String.format("Test Power Calculation: %f = %f+%f",testPower,kp*speedErr*MPH_MS,ki*uVal));
            
        }else{
//            System.out.println(String.format("Test Power Calculation: %f = %f*%f*%f+%f*%f",testPower,kp,speedErr,MPH_MS,ki,uVal));
//            System.out.println(String.format("Test Power Calculation: %f = %f+%f",testPower,kp*speedErr*MPH_MS,ki*uVal));
//            System.out.println("Power larger than 120 kW.... reducing to 120 kw...");
            powerVal = MAX_POWER;
            
        }
        
//        System.out.println("Old Power Val: " + oldPowerVal);
//        System.out.println("Power Val: " + powerVal);
        
        distanceTraveledInBlock += currSpeedVal * MPH_FPS * t.getDeltaT();
        distanceLeft = blockDistance - distanceTraveledInBlock;
//        System.out.println("\tblockDistance: "+blockDistance);
//        System.out.println("\tdistanceTraveled: "+distanceTraveled);
//        System.out.println("\tdistanceTraveledInBlock: " + distanceTraveledInBlock);
//        System.out.println("\tdistanceLeft: " + distanceLeft);
        if(distanceLeft <= 0){
            isDistanceLeft = false;
            distanceTraveled+=blockDistance;
            distanceTraveledInBlock = 0;
        }
            
        double distanceNeededToStop_ft = calculateDistanceToStop(currSpeedVal);
        
//        System.out.println("\tdistance stopping: " + distanceNeededToStop_ft + "    " + (t.getAuthority()-distanceTraveled-distanceTraveledInBlock));
        if(distanceNeededToStop_ft > (t.getAuthority()-distanceTraveled-distanceTraveledInBlock)){
            System.out.println("\t\tService brakes engaged");
            t.setBrakes(1); //set the service brake
            TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
            train.onBrake(1);
        }else{
//            System.out.println("\t\tService brakes disengaged");
            TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
            if(servBrakeLabel.getText().equalsIgnoreCase("Disengaged") && emergBrakeLabel.getText().equalsIgnoreCase("Disengaged")){
                train.onBrake(0);
                t.setBrakes(0);
            }
           
        }
        
        //========FOR TESTING PURPOSES================
        
        double currSpeedValNew = t.calculateVelocity(powerVal, currSpeedVal, 0, 0, setpointSpeedVal, 50);
//        System.out.println("\t\tCurrent Speed according to Train Model: "+currSpeedValNew);
        //============================================
        if(t.isBrakeFailure() || t.isEngineFailure()){
            powerVal = 0;
        }
        //=====================set my GUI values================================
        powerLabel.setText(String.format("%.3f",powerVal));
        
        currentSpeedLabel.setText(String.format("%.2f",currSpeedValNew));
        //======================================================================
    
//        System.out.println("=================================================");
        
        oldSpeedErr = speedErr;
        oldUval = uVal; 
        oldPowerVal = powerVal;
        currSpeedVal = currSpeedValNew;
        
        
        
        
    }
    
    public void showTrainIdleSpeed(){
        double speed = 0;
        currentSpeedLabel.setText(String.format("%.2f",speed));
    }
    double calculateDistanceToStop(double currentSpeed){
        double s_mph = currentSpeed;
        double s_ms = s_mph*MPH_MS;
        double decel_ms = serviceBrakeDecel*0.3048;
        double d_m = Math.pow(s_ms,2)/(2.0*decel_ms);  
        double d_ft = d_m/.3048;
        return d_ft;
    }
    
    public void onTargetArrival(){
        //needed to reset the distance stuff to make a train go again
        System.out.println("Reset the distances");
        distanceTraveledInBlock = 0;
        distanceLeft = 0;
        distanceTraveled = 0;
        isDistanceLeft = true;
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
    @FXML
    void setTemperature(ActionEvent event) {
        double temp = Double.parseDouble(temperatureVal.getText());
        temperatureLabel.setText(String.format("%.0f",temp));
    }
    
    @FXML
    void setSetpointSpeed_Test(ActionEvent event) {
        setpointSpeedLabel.setText(String.format("%.2f",Double.parseDouble(test_setpointSpeedVal.getText())));
    }
    
    @FXML
    void onBrakeFailActivate(ActionEvent event) {
        if(emergBrakeLabel.getText().equalsIgnoreCase("Disengaged")){
            toggleEmergBrake();
        }
        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        train.onBrakeFailure(true);
        train.onBrake(3);
        servBrakeButton.setDisable(true);
        t.setBrakeFailure(true);
        
        
        
        

    }

    @FXML
    void onBrakeFailDeactivate(ActionEvent event) {
        if(emergBrakeLabel.getText().equalsIgnoreCase("Engaged")){
            toggleEmergBrake();
        }
        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        train.onBrakeFailure(false);
        train.onBrake(0);
        servBrakeButton.setDisable(false);
        t.setBrakeFailure(false);
        
        
    }
    
    @FXML
    void onEngineFailActivate(ActionEvent event) {
        if(emergBrakeLabel.getText().equalsIgnoreCase("Disengaged")){
            toggleEmergBrake();
        }
        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        train.onEngineFailure(true);
        train.onBrake(3);
//        t.setEngineFailure(true);
//        t.setBrakes(3);
    }

    @FXML
    void onEngineFailDeactivate(ActionEvent event) {
        if(emergBrakeLabel.getText().equalsIgnoreCase("Engaged")){
            toggleEmergBrake();
        }
        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        train.onEngineFailure(false);
        train.onBrake(0);
//        t.setEngineFailure(false);
//        t.setBrakes(0);
    }
    
    @FXML
    void onSignalFailActivate(ActionEvent event) {

        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        train.onSignalFailure(true);
    }

    @FXML
    void onSignalFailDeactivate(ActionEvent event) {

        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        train.onSignalFailure(false);
    }
    
    //============LEFT DOORS=============
    
    @FXML
    void openLD(ActionEvent event) {
        int[] arr = {0,1,2,3};
        operateDoors(arr,1);
    }
     @FXML
    void closeLD(ActionEvent event) {
        int[] arr = {0,1,2,3};
        operateDoors(arr,0);
    }
    @FXML
    void openFLD(ActionEvent event) {
        int[] arr = {0};
        operateDoors(arr,1);
    }
    @FXML
    void closeFLD(ActionEvent event) {
        int[] arr = {0};
        operateDoors(arr,0);
    }
    @FXML
    void openFMLD(ActionEvent event) {
        int[] arr = {1};
        operateDoors(arr,1);
    }
     @FXML
    void closeFMLD(ActionEvent event) {
        int[] arr = {1};
        operateDoors(arr,0);
    }
    @FXML
    void openBMLD(ActionEvent event) {
        int[] arr = {2};
        operateDoors(arr,1);
    }
    @FXML
    void closeBMLD(ActionEvent event) {
        int[] arr = {2};
        operateDoors(arr,0);
    }
    @FXML
    void openBLD(ActionEvent event) {
        int[] arr = {3};
        operateDoors(arr,1);
    }
    @FXML
    void closeBLD(ActionEvent event) {
        int[] arr = {3};
        operateDoors(arr,0);
    }
//============RIGHT DOORS=============
    @FXML
    void openRD(ActionEvent event) {
        int[] arr = {4,5,6,7};
        operateDoors(arr,1);
    }
    @FXML
    void closeRD(ActionEvent event) {
        int[] arr = {4,5,6,7};
        operateDoors(arr,0);
    }
    @FXML
    void openFRD(ActionEvent event) {
        int[] arr = {4};
        operateDoors(arr,1);
    }
    @FXML
    void closeFRD(ActionEvent event) {
        int[] arr = {4};
        operateDoors(arr,0);
    }
    @FXML
    void openFMRD(ActionEvent event) {
        int[] arr = {5};
        operateDoors(arr,1);
    }
     @FXML
    void closeFMRD(ActionEvent event) {
        int[] arr = {5};
        operateDoors(arr,0);
    }
    @FXML
    void openBMRD(ActionEvent event) {
        int[] arr = {6};
        operateDoors(arr,1);
    }
    @FXML
    void closeBMRD(ActionEvent event) {
        int[] arr = {6};
        operateDoors(arr,0);
    }
    @FXML
    void openBRD(ActionEvent event) {
        int[] arr = {7};
        operateDoors(arr,1);
    }
    @FXML
    void closeBRD(ActionEvent event) {
        int[] arr = {7};
        operateDoors(arr,0);
    }

    public void operateDoors(int[] doorIDs, int doorOp){
        String text = "OPEN";
        boolean rightDoorOpen = false;
        boolean leftDoorOpen = false;
        if(doorOp == 0){
            text = "CLOSED";
        }else if(doorOp == 1){
            text = "OPEN";
        }
        for(int i = 0; i < doorIDs.length; i++){
            switch(doorIDs[i]){
                case 0: 
                    fld.setText(text);
                    break;
                case 1:
                    fmld.setText(text);
                    break;
                case 2: 
                    bmld.setText(text);
                    break;
                case 3:
                    bld.setText(text);
                    break;
                case 4: 
                    frd.setText(text);
                    break;
                case 5:
                    fmrd.setText(text);
                    break;
                case 6: 
                    bmrd.setText(text);
                    break;
                case 7:
                    brd.setText(text);
                    break;
            }
        }
        if(fld.getText().equalsIgnoreCase("OPEN"))
            leftDoorOpen = true;
            
        if(fmld.getText().equalsIgnoreCase("OPEN"))
           leftDoorOpen = true;
        
        if(bmld.getText().equalsIgnoreCase("OPEN"))
            leftDoorOpen = true;
            
        if(bld.getText().equalsIgnoreCase("OPEN"))
            leftDoorOpen = true;
            
        if(frd.getText().equalsIgnoreCase("OPEN"))
            rightDoorOpen = true;
           
        if(fmrd.getText().equalsIgnoreCase("OPEN"))
            rightDoorOpen = true;
            
        if(bmrd.getText().equalsIgnoreCase("OPEN"))
            rightDoorOpen = true;
            
        if(brd.getText().equalsIgnoreCase("OPEN"))
            rightDoorOpen = true;
           
        
        TrainModelController train = (TrainModelController) ta.trainmodels.get(t.getNumber());
        if(rightDoorOpen){
            train.operateDoors("right",1);
        }else{
            train.operateDoors("right",0);
        }
        if(leftDoorOpen){
            train.operateDoors("left",1);
        }else{
            train.operateDoors("left",0);
        }
        
        
    }
    
}
