/*
CTC OFFICE
Mostly written by Jon Galaydick
 */
package trainapplication.CTCOffice;

import trainapplication.Train;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import trainapplication.TrackController.*;
import trainapplication.TrainApplication;
import trainapplication.TrainModel.*;
import trainapplication.TrackModel.*;
import trainapplication.TrainController.*;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class CTCOfficeController implements Initializable {

    private int trainIDIterator = 0;
    private int totalThroughput = 0;
    private int totalTicketSales = 0;
    private int hourlyThroughput = 0;
    private int hourlyTicketSales = 0;
    private Schedule[] scheduleArray = new Schedule[1]; //array of schedules, will hold all schedules loaded in
    private Train[] trainArray = new Train[1]; //array of trains, will hold all trains that were ever created
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    public long currentTime = System.currentTimeMillis();
    private long dispatchTimeCheck = System.currentTimeMillis(); //used to try and auto dispatch a train every 30 seconds
    public boolean autoMode = false; //if true, system is in auto mode
    public Timeline timeline = new Timeline();
    private long hourlyResetTime = System.currentTimeMillis() + 3600000;
    
    
    private TrainApplication ta;
    
    public CTCOfficeController() {
        //this.ta = ta;
    }
    
    public void setTrainApp(TrainApplication ta) {
        this.ta = ta;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //init train table
        trainTableAllLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trainTableAllNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        trainTableAllCurrent.setCellValueFactory(new PropertyValueFactory<>("block"));
        trainTableAllTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        trainTableAllLine.setStyle("-fx-alignment: CENTER;");
        trainTableAllNumber.setStyle("-fx-alignment: CENTER;");
        trainTableAllCurrent.setStyle("-fx-alignment: CENTER;");
        trainTableAllTarget.setStyle("-fx-alignment: CENTER;");

        //init queue table
        queueTableLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        queueTableNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        queueTableSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        queueTableTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        queueTableLine.setStyle("-fx-alignment: CENTER;");
        queueTableNumber.setStyle("-fx-alignment: CENTER;");
        queueTableSpeed.setStyle("-fx-alignment: CENTER;");
        queueTableTarget.setStyle("-fx-alignment: CENTER;");

        //init track table
        trackTableAllLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trackTableAllSection.setCellValueFactory(new PropertyValueFactory<>("section"));
        trackTableAllBlock.setCellValueFactory(new PropertyValueFactory<>("blockNumber"));
        trackTableAllLength.setCellValueFactory(new PropertyValueFactory<>("blockLength"));
        trackTableAllLimit.setCellValueFactory(new PropertyValueFactory<>("speedLimit"));
        trackTableAllState.setCellValueFactory(new PropertyValueFactory<>("blockState"));
        trackTableAllLine.setStyle("-fx-alignment: CENTER;");
        trackTableAllSection.setStyle("-fx-alignment: CENTER;");
        trackTableAllBlock.setStyle("-fx-alignment: CENTER;");
        trackTableAllLength.setStyle("-fx-alignment: CENTER;");
        trackTableAllLimit.setStyle("-fx-alignment: CENTER;");
        trackTableAllState.setStyle("-fx-alignment: CENTER;");

        //set system time to display
        multiplierTextField.setText("1");
        ta.setTime(Integer.parseInt(multiplierTextField.getText()));

        //init line choicebox in the new train popup screen
        newTrainLineBox.getItems().addAll("Green", "Red");
        newTrainLineBox.getSelectionModel().select("Green");
        
        //init station choicebox (green line) in the new train popup screen
        stationChoiceBox.getItems().addAll("Select...", "Pioneer:2", "Edgebrook:9", "Sussex:16", "Whited:22", "South Bank:31",
                    "(U)Central:39", "(U)Inglewood:48", "(U)Overbrook:57", "Glenbury:65", "Dormont:73", "Mt Lebanon:77",
                    "Poplar:88", "Castle Shannon:96", "Dormont:105", "Glenbury:114",
                    "(U)Overbrook:123", "(U)Inglewood:132", "(U)Central:141");
        stationChoiceBox.getSelectionModel().select("Select...");
        
        //init switch choice box for CTC functionality to change switch states
        switchChoiceBox.getItems().addAll("Green12", "Green16", "Green29", "Green58(ToYard)", "Green62(FromYard)", "Green76", "Green86",
                "Red09(Yard)", "Red15", "Red27(U)", "Red32(U)", "Red38(U)", "Red52");
        
        //hide newTrainPane. will appear on add train button press
        newTrainPane.setVisible(false);

    }

    //track table information for all lines
    @FXML
    private TableView<Block> trackTableAll;

    @FXML
    private TableColumn<Block, String> trackTableAllLine;

    @FXML
    private TableColumn<Block, String> trackTableAllSection;

    @FXML
    private TableColumn<Block, Integer> trackTableAllBlock;

    @FXML
    private TableColumn<Block, Integer> trackTableAllLength;

    @FXML
    private TableColumn<Block, Integer> trackTableAllLimit;

    @FXML
    private TableColumn<Block, String> trackTableAllState;

    //train table information for all lines
    @FXML
    private TableView<Train> trainTableAll;

    @FXML
    private TableColumn<Train, String> trainTableAllLine;

    @FXML
    private TableColumn<Train, Integer> trainTableAllNumber;

    @FXML
    private TableColumn<Train, Integer> trainTableAllCurrent;

    @FXML
    private TableColumn<Train, Integer> trainTableAllTarget;

    @FXML
    private TableView<Train> queueTrainTable;

    @FXML
    private TableColumn<Train, String> queueTableLine;

    @FXML
    private TableColumn<Train, Integer> queueTableNumber;

    @FXML
    private TableColumn<Train, Integer> queueTableSpeed;

    @FXML
    private TableColumn<Train, Integer> queueTableTarget;
    //GUI elements (non-table) for the rest of the CTC Office
    @FXML
    private Button dispatchButton;

    @FXML
    private Button autoModeButton;

    @FXML
    private TextField multiplierTextField;

    @FXML
    private MenuItem menuLoadSchedule;

    @FXML
    private MenuItem menuDispatch;

    @FXML
    private MenuItem menuClose;

    @FXML
    private MenuItem menuNewTrain;

    @FXML
    private MenuItem menuUserManual;

    @FXML
    public Label systemTimeText;

    @FXML
    private Label redThroughputLabel;

    @FXML
    private Label greenThroughputLabel;

    @FXML
    private Label allThroughputLabel;

    @FXML
    private Slider suggestedSpeedSlider;

    @FXML
    private ChoiceBox<String> newTrainLineBox;

    @FXML
    private TextField newTrainTargetBlock;

    @FXML
    private Label suggestedSpeedLabel;

    @FXML
    private AnchorPane newTrainPane;

    @FXML
    private Button getTrackInfoButton;
    
    @FXML
    private Button getThroughputButton;

    @FXML
    private ChoiceBox<String> stationChoiceBox;

    @FXML
    private Button deleteTrainButton;

    @FXML
    private Button changeTrackStateButton;
    
    @FXML
    private Button multiplierButton;
    
    @FXML
    private Button moveSwitchButton;

    @FXML
    private ChoiceBox<String> switchChoiceBox;

    //GUI ActionEvent Handlers
    
    //set the system to auto mode
    @FXML
    void autoModeButtonClick(ActionEvent event) {
        
        if (autoMode) { //turns off auto mode (multiplier back to 1)
            autoModeButton.setText("Enter Automatic Mode");
            autoMode = false;
        } else { //turns on auto mode based on multiplier value
            autoModeButton.setText("Enter Manual Mode");
            autoMode = true;
            dispatchTimeCheck = currentTime + 600000; //try to dispatch trains every 10 minutes
        }
        
        //send current state of auto mode to train model/controller
        ta.trainmodels.stream().forEach((tModel) -> {
            tModel.setAutoMode(autoMode);
        });
        
        ta.trainctrs.stream().forEach((tModel) -> {
            tModel.setAutoMode(autoMode);
        });
        
    }
    
    //update the multiplier based on the value entered in the box
    @FXML
    void changeMultiplierClick(ActionEvent event) {
        timeline.stop();
        int multiplier = Integer.parseInt(multiplierTextField.getText());
        if (multiplier >= 0) {
            ta.setTime(multiplier);
        }
        else {
            multiplierTextField.setText("1");
            ta.setTime(1);
        }
    }

    //get selected train from the train table and dispatch it
    @FXML
    void dispatchButtonClick(ActionEvent event) {
        Train train = queueTrainTable.getSelectionModel().getSelectedItem();
        
        dispatchTrain(train);        
    }
    
    //CTC change switch state for track controller
    @FXML
    void clickSwitchButton(ActionEvent event) {
        String selectedSwitch = switchChoiceBox.getSelectionModel().getSelectedItem();
        //System.out.println(selectedSwitch);
        switch(selectedSwitch) 
        { 
            case "Green12": 
                System.out.println("Green12"); 
                break; 
            case "Green16": 
                System.out.println("Green16"); 
                break; 
            case "Green29": 
                System.out.println("Green29"); 
                break;
            case "Green58(ToYard)": 
                System.out.println("Green58"); 
                break; 
            case "Green62(FromYard)": 
                System.out.println("Green62"); 
                break; 
            case "Green76": 
                System.out.println("Green76"); 
                break; 
            case "Green86": 
                System.out.println("Green86"); 
                break; 
            case "Red09(Yard)": 
                System.out.println("Red09"); 
                break; 
            case "Red15": 
                System.out.println("Red15"); 
                break; 
            case "Red27(U)": 
                System.out.println("Red27"); 
                break; 
            case "Red32(U)": 
                System.out.println("Red32"); 
                break; 
            case "Red38(U)": 
                System.out.println("Red38"); 
                break; 
            case "Red52": 
                System.out.println("Red52"); 
                break; 
            default: 
                System.out.println("not sure what happened"); 
        } 
    }

    /*
    load in a schedule for a train
    schedule loaded in will be one train per file
    */
    @FXML
    void loadScheduleButtonClick(ActionEvent event) throws InterruptedException {
        String csvFile;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse for Train Schedule");
        File file = fileChooser.showOpenDialog(null);

        csvFile = file.getPath();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            Schedule schedule = new Schedule("", 0, new int[1], 0, new double[1], 0);
            int index = 0; //index for elements of one schedule (multiple stops)

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                //use comma as separator
                String[] scheduleInfo = line.split(cvsSplitBy);
                schedule = createTrainSchedule(schedule, scheduleInfo, index);
                index++;
            }
            createTrainFromSchedule(schedule);
            scheduleArray[trainIDIterator] = schedule;
            //increment array size to avoid null pointers when another schedule loaded in
            scheduleArray = Arrays.copyOf(scheduleArray, scheduleArray.length + 1);
            trainIDIterator++;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //manually create a train
    @FXML
    void newTrainButtonClick(ActionEvent event) {
        newTrainPane.setVisible(true);
        newTrainLineBox.getSelectionModel().select("Green");
        stationChoiceBox.getSelectionModel().select("Select...");
        suggestedSpeedSlider.setValue(0);
        suggestedSpeedLabel.setText("0");
        newTrainTargetBlock.setText("");
    }
    
    //changing the choice box for the station options in manual train creation
    @FXML
    void stationChoiceBoxChange(MouseEvent event) {

        newTrainTargetBlock.setText("");
        stationChoiceBox.getItems().clear();
        if (newTrainLineBox.getSelectionModel().getSelectedItem().equals("Red")) {
            stationChoiceBox.getItems().addAll("Select...", "Shadyside:7", "Herron Ave:16", "Swissville:21",
                    "(U)Penn Station:25", "(U)Steel Plaza:35",
                    "(U)First Ave:45", "Station Square:48", "South Hills Jct:60");
        }
        if (newTrainLineBox.getSelectionModel().getSelectedItem().equals("Green")) {
            stationChoiceBox.getItems().addAll("Select...", "Pioneer:2", "Edgebrook:9", "Sussex:16", "Whited:22", "South Bank:31",
                    "(U)Central:39", "(U)Inglewood:48", "(U)Overbrook:57", "Glenbury:65", "Dormont:73", "Mt Lebanon:77",
                    "Poplar:88", "Castle Shannon:96", "Dormont:105", "Glenbury:114",
                    "(U)Overbrook:123", "(U)Inglewood:132", "(U)Central:141");
        }
    }
    
    //reseting things in manual train creation
    @FXML
    void newTrainLineBoxChange(MouseEvent event) {
        stationChoiceBox.getSelectionModel().select("Select...");
    }
    
    //reseting things in manual train creation
    @FXML
    void newTrainTargetBlockType(KeyEvent event) {
        stationChoiceBox.getSelectionModel().select("Select...");
    }
    
    //delete the selected train from the queue
    @FXML
    void deleteTrainButtonClick(ActionEvent event) {
        Train train = queueTrainTable.getSelectionModel().getSelectedItem();
        queueTrainTable.getItems().remove(train);
    }

    //add the manually created train to the queue
    @FXML
    void newTrainSubmitClick(ActionEvent event) throws Exception {
        try {
            int newTrainNumber = trainIDIterator++;
            String newTrainLine = newTrainLineBox.getValue();
            double newTrainSpeed = (int) suggestedSpeedSlider.getValue();
            if ((newTrainLine.equalsIgnoreCase("red") && newTrainSpeed > 24) || 
                    (newTrainLine.equalsIgnoreCase("green") && newTrainSpeed > 43)) {
                throw new Exception("Speed Limit Exceeded on the selected line\n"
                        + "Speed Limit Red: 24mph... Speed Limit Green: 43mph\n");
            }
            int newTrainTarget = getTargetBlockFromStation();
            if ((newTrainLine.equalsIgnoreCase("red") && newTrainTarget > 60) || 
                    (newTrainLine.equalsIgnoreCase("green") && newTrainTarget > 141)) {
                throw new Exception("Authority Limit Exceeded on the selected line\n"
                        + "Max on Red: 60... Max on Green: 141\n");
            }
            double newTrainAuthority = 1000; //100 is a dummy variable for now. have to work with trkMdl get actual distance
            
            TrackModelController trackModel = (TrackModelController) ta.trkMdl;
            newTrainAuthority = trackModel.getDistance(0, newTrainTarget);
            System.out.println("Total Distance: " + newTrainAuthority);
            
            ta.addTrain(newTrainNumber, newTrainLine, newTrainSpeed, newTrainTarget, newTrainAuthority);
            Train newTrain = ta.getTrain(newTrainNumber);
            addTrainToQueue(newTrain);

            //add new train info to schedule here
            Schedule manualTrainAddSchedule = newTrainMakeSchedule(newTrain);
            scheduleArray = Arrays.copyOf(scheduleArray, scheduleArray.length + 1);
            scheduleArray[newTrainNumber] = manualTrainAddSchedule;

            newTrainPane.setVisible(false);
        } catch (Exception ex) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Something is Invalid!");
            System.out.println("Something went wrong with the data entered!");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
            trainIDIterator--; //have to reset the trainID value so things don't screw up
            //newTrainPane.setVisible(false);
        }
    }
    
    //close the popup for manual train creation
    @FXML
    void newTrainCloseClick(ActionEvent event) {
        newTrainPane.setVisible(false);
    }

    //suggested speed for manual train creation
    @FXML
    void suggestedSpeedSliderDrag(MouseEvent event) {
        int suggestedSpeed = (int) suggestedSpeedSlider.getValue();
        suggestedSpeedLabel.setText(Integer.toString(suggestedSpeed));
    }

    //menu button for clicking dispatch while a train is selected
    @FXML
    void menuDispatchClick(ActionEvent event) throws IOException {
        dispatchButtonClick(event);
    }

    //menu button for loading in a schedule
    @FXML
    void menuLoadScheduleClick(ActionEvent event) throws InterruptedException {
        loadScheduleButtonClick(event);
    }

    //menu button for manually creating a new train
    @FXML
    void menuNewTrainClick(ActionEvent event) {
        newTrainButtonClick(event);
    }

    //menu button for opening the user's manual (does nothing worthwhile)
    @FXML
    void menuUserManualClick(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("User Manual?");
        alert.setHeaderText("There should probably be a user's manual here, but there isn't");
        alert.show();
    }
    
    //set the state of the selected track to maintenance or not maintenance
    @FXML
    void changeTrackStateButtonClick(ActionEvent event) {
        //track state is blank if open, otherwise says 'maint' or 'train'
        Block block = trackTableAll.getSelectionModel().getSelectedItem();
        if (block.getBlockState().equalsIgnoreCase("")){
            block.setBlockState("Maint");
            System.out.println("Switching to Main");
        }
        else if (block.getBlockState().equalsIgnoreCase("Maint")) {
            block.setBlockState("");
            System.out.println("Switching to not main");
        }
        else {
            //do nothing here?
            System.out.println("Why am I here?");
        }
        
        int bIndex = trackTableAll.getItems().indexOf(block);
        trackTableAll.getItems().set(bIndex, block);
        trackTableAll.refresh();
        
    }
    
    //display metrics (throughput and ticket sales, as well as hourly for each)
    @FXML
    void getThroughputButtonClick(ActionEvent event) {
        
        //display throughput information
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Displaying Metrics");
        alert.setHeaderText("Total Throughput: " + totalThroughput + "\nTotal Ticket Sales: " + totalTicketSales);
        alert.setContentText("Hourly Throughput Sales: " + hourlyThroughput + "\nHourly Ticket Sales: " + hourlyTicketSales);
        alert.show();
    }

    //load in the track data from the track model
    @FXML
    void getTrackInfoClick(ActionEvent event) {
        TrackModelController trackModel = (TrackModelController) ta.trkMdl;
        
        trackTableAll.getItems().clear();
        
        for (Block block : trackModel.redTrack.blockList) {
            block.setBlockState("");
            trackTableAll.getItems().add(block);
        }
        for (Block block : trackModel.greenTrack.blockList) {
            block.setBlockState("");
            trackTableAll.getItems().add(block);
        }

    }

    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    //rest of methods, non event-handlers
    
    /*
    try to dispatch the 1st train in the queue (if there is one)
    this is done with automode enabled
    */
    public void tryToDispatchTrain() {
        if (currentTime > dispatchTimeCheck) {
            dispatchTimeCheck += 600000; // increment 10 minutes
            //its time to try and dispatch again
            //check queue for train (dispatch if possible), and increment dispatchTimeCheck by x(TBD) sec
            try{
                queueTrainTable.getSelectionModel().selectFirst();
                Train train = queueTrainTable.getSelectionModel().getSelectedItem();
                System.out.println("TRAIN IS BEING DISPATCHED AUTOMATICALLY");
                dispatchTrain(train);
            }
            catch (Exception ex) {
                System.out.println("No train was in the queue. Add a train to the queue be auto dispatched");
            }
            
        }
    }
    
    //dispatches train from the queue
    private void dispatchTrain(Train train) {
        
        int dispatchNumber = train.getNumber(); //train ID
        double dispatchSpeed = train.getSpeed();
        int dispatchCurrentBlock = train.getBlock();
        int dispatchTargetBlock = train.getTarget();

        //move train to outbound table
        dispatchTrainFromQueue(train);
        
        TrainModelController tModelCont = (TrainModelController) ta.trainmodels.get(dispatchNumber);
        tModelCont.runTrain();
        
        //send Speed and Authority to the track controllers
        sendToTrackControllers(dispatchNumber, dispatchSpeed, dispatchTargetBlock);
        
        Schedule schedule = getScheduleInfoFromTrainTableSelected(train);
        schedule.dispatchTime = System.currentTimeMillis();
        
        //popup box with CTC dispatch info
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CTC Outputs");
        alert.setHeaderText("Train " + dispatchNumber + " is being dispatched from block " + dispatchCurrentBlock);
        alert.setContentText("Suggested speed: " + dispatchSpeed + "\nTarget Block: " + dispatchTargetBlock);
        alert.showAndWait();
    }

    /*
    create a train object based on the information
    train info passed in will be as follows:
    line, section(not used), current block, target block(=authority), time to target
    sends train to be added to the queue
     */
    private void createTrainFromSchedule(Schedule schedule) throws IOException, InterruptedException {

        String line = schedule.line;
        int number = schedule.trainID;
        double suggestedSpeed;
        if (line.equalsIgnoreCase("red")) {
            suggestedSpeed = 24;
        }
        else {
            suggestedSpeed = 43;
        }
        int targetBlock = schedule.targetBlock[schedule.scheduleIndex];
        
        double authority = 1000; //100 used for testing, gets overwritten anyways
        TrackModelController trackModel = (TrackModelController) ta.trkMdl;
        authority = trackModel.getDistance(0, targetBlock);

        ta.addTrain(number, line, suggestedSpeed, targetBlock, authority); //dummy 100 as authority for now
        Train train = ta.getTrain(number);
        
        addTrainToQueue(train);
    }

    //sets up a schedule object when a schedule file is loaded in for a train
    private Schedule createTrainSchedule(Schedule schedule, String[] trainAttributes, int index) {
        schedule = resizeScheduleArray(schedule);
        schedule.line = trainAttributes[0];
        schedule.trainID = trainIDIterator;
        schedule.targetBlock[index] = Integer.parseInt(trainAttributes[3]);
        schedule.dispatchTime = System.currentTimeMillis();
        schedule.dwellTime[index] = Double.parseDouble(trainAttributes[4]);

        return schedule;
    }

    //increase the index of the schedule component arrays to avoid null pointer exceptions while adding schedule elements
    private Schedule resizeScheduleArray(Schedule schedule) {
        schedule.targetBlock = Arrays.copyOf(schedule.targetBlock, schedule.targetBlock.length + 1);
        schedule.dwellTime = Arrays.copyOf(schedule.dwellTime, schedule.dwellTime.length + 1);
        return schedule;
    }

    //takes train information as an input and adds a train to the appropriate table
    private void addTrainToQueue(Train train) throws IOException {
        queueTrainTable.getItems().add(train);
        int trainID = train.getNumber();
        trainArray[trainID] = train;
        trainArray = Arrays.copyOf(trainArray, trainArray.length + 1);
        
        ta.createTrainGUI(trainID);
    }

    //removes the selected train from the queue and adds it to the outbound table
    private void dispatchTrainFromQueue(Train train) {
        queueTrainTable.getItems().remove(train);
        train.setSpeed(0);
        trainTableAll.getItems().add(train);
    }

    //updates outbound table when a train reaches its target
    public void updateTrainTable(Train train) {
        int tIndex = trainTableAll.getItems().indexOf(train);
        trainTableAll.getItems().set(tIndex, train);
        trainTableAll.refresh();
    }
    
    //when a train returns to the yard after driving for long enough, remove it from the table
    public void removeTrainFromOutbound(Train train) {
        trainTableAll.getItems().remove(train);
    }

    //get the schedule associated with the selected train
    private Schedule getScheduleInfoFromTrainTableSelected(Train train) {
        //need to search schedule array and to find the scheudle with the same trainID as the train parameter
        int trainID = train.getNumber();
        Schedule schedule = scheduleArray[trainID];
        return schedule;
    }

    //create a schedule object for a manually created train
    private Schedule newTrainMakeSchedule(Train train) {
        Schedule schedule = new Schedule("", 0, new int[1], 0, new double[1], 0);
        resizeScheduleArray(schedule);
        schedule.line = train.getLine();
        schedule.trainID = train.getNumber();
        schedule.targetBlock[0] = train.getTarget();
        schedule.dispatchTime = currentTime;
        schedule.dwellTime[0] = 1.5; //just picking a random number...
        schedule.scheduleIndex = 0;
        return schedule;
    }
    
    
    //get the target block value of the station of what is entered in the user field
    private int getTargetBlockFromStation() {
        String targetString = stationChoiceBox.getSelectionModel().getSelectedItem();
        if (targetString.equals("Select...")) {
            return Integer.parseInt(newTrainTargetBlock.getText());
        } else {
            String[] splitString = targetString.split(":");
            return Integer.parseInt(splitString[1]);
        }
    } 
    
    //throughput updates from train model
    public void incrementThroughput(int current) {
        if (currentTime > hourlyResetTime) {
            resetHourly();
        }
        totalThroughput += current;
        hourlyThroughput += current;
    }
    
    //ticket sales update from track model
    public void incrementTicketSales(int current) {
        if (currentTime > hourlyResetTime) {
            resetHourly();
        }
        totalTicketSales += current;
        hourlyTicketSales += current;
    }
    
    //reset hourly metrics information
    private void resetHourly() {
        hourlyThroughput = 0;
        hourlyTicketSales = 0;
        hourlyResetTime += 3600000;
    }
    
    /*
    called if the train reaches its target
    if the train was created via schedule, use the schedule to keep moving
    if the train was not created via schedule (manual), use beacon to get next station
    */
    public void continueTrain(Train t) {
        
        System.out.println("Called continue train");
        
        Schedule currentSchedule = scheduleArray[t.getNumber()];
        
        try {
            int target;
            //System.out.println("Whats the size of the target block array: " + currentSchedule.targetBlock.length);
            if (currentSchedule.targetBlock.length < 5) {
                if (autoMode) {
                Block b = ta.trkMdl.getBlockAt(t.getLine(), t.getBlock());
                target = b.getBeacon();
                System.out.println("Beacon Target: " + target);
                double distance = ta.trkMdl.getDistance(t.getBlock(), target);
                System.out.println("Distance to new target: " + distance);
                t.setTarget(target);
                t.setAuthority(distance);
                TrainModelController tModelCont = (TrainModelController) ta.trainmodels.get(t.getNumber());
                tModelCont.runTrain();
                }
                
            }
            else {
                currentSchedule.scheduleIndex++;
                target = currentSchedule.targetBlock[currentSchedule.scheduleIndex];
                System.out.println("This is the schedule target... " + target);
                double distance = ta.trkMdl.getDistance(t.getBlock(), target);
                System.out.println("Distance to new target: " + distance);
                t.setTarget(target);
                t.setAuthority(distance);
                TrainModelController tModelCont = (TrainModelController) ta.trainmodels.get(t.getNumber());
                tModelCont.runTrain();
            }
                        
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    //send speed and authority of the train to the track controller
    private void sendToTrackControllers(int dispatchNumber, double dispatchSpeed, int dispatchTargetBlock) {
        
        TrackControllerController t1 = (TrackControllerController) ta.trkCtr1;
        t1.setSpeedAuthority(dispatchNumber, dispatchSpeed, dispatchTargetBlock);
        TrackControllerController t2 = (TrackControllerController) ta.trkCtr2;
        t2.setSpeedAuthority(dispatchNumber, dispatchSpeed, dispatchTargetBlock);
        TrackControllerController t3 = (TrackControllerController) ta.trkCtr3;
        t3.setSpeedAuthority(dispatchNumber, dispatchSpeed, dispatchTargetBlock);
        TrackControllerController t4 = (TrackControllerController) ta.trkCtr4;
        t4.setSpeedAuthority(dispatchNumber, dispatchSpeed, dispatchTargetBlock);
        TrackControllerController t5 = (TrackControllerController) ta.trkCtr5;
        t5.setSpeedAuthority(dispatchNumber, dispatchSpeed, dispatchTargetBlock);
        TrackControllerController t6 = (TrackControllerController) ta.trkCtr6;
        t6.setSpeedAuthority(dispatchNumber, dispatchSpeed, dispatchTargetBlock);
        
    }
}
