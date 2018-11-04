/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.CTCOffice;

import trainapplication.TrackModel.Track;
import trainapplication.Train;
import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import trainapplication.TrainApplication;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class CTCOfficeController implements Initializable {

    private int trainIDIterator = 0;
    private int redLineThroughput = 0;
    private int greenLineThroughput = 0;
    private int allLineThroughput = 0;
    private int hourlyPassengers = 0;
    private int hourlyTicketSales = 0;
    private Schedule[] scheduleArray = new Schedule[1]; //array of schedules, will hold all schedules loaded in
    private Train[] trainArray = new Train[1]; //array of trains, will hold all trains that were ever created
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private long currentTime = System.currentTimeMillis();
    private Timeline timeline = new Timeline();
    
    
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
        // TODO

        //set column to be a drop down to select red or green line (only in all table)
//        ObservableList<String> list
//                = FXCollections.observableArrayList();
//        list.add("Red");
//        list.add("Green");
//        queueTableLine.setCellFactory(ComboBoxTableCell.forTableColumn(list));
        //init train table
        trainTableAllLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trainTableAllNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        trainTableAllSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        trainTableAllCurrent.setCellValueFactory(new PropertyValueFactory<>("block"));
        trainTableAllTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        trainTableAllLine.setStyle("-fx-alignment: CENTER;");
        trainTableAllNumber.setStyle("-fx-alignment: CENTER;");
        trainTableAllSpeed.setStyle("-fx-alignment: CENTER;");
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
        trackTableAllState.setCellValueFactory(new PropertyValueFactory<>("state"));
        trackTableAllLine.setStyle("-fx-alignment: CENTER;");
        trackTableAllSection.setStyle("-fx-alignment: CENTER;");
        trackTableAllBlock.setStyle("-fx-alignment: CENTER;");
        trackTableAllLength.setStyle("-fx-alignment: CENTER;");
        trackTableAllLimit.setStyle("-fx-alignment: CENTER;");
        trackTableAllState.setStyle("-fx-alignment: CENTER;");

        //set system time to display
        multiplierTextField.setText("1");
        setTime(Integer.parseInt(multiplierTextField.getText()));

        //init line choicebox in the new train popup screen
        newTrainLineBox.getItems().addAll("Red", "Green");
        newTrainLineBox.getSelectionModel().select("Red");
        
        //init station choicebox in the new train popup screen
        stationChoiceBox.getItems().addAll("Select...", "Shadyside:7", "Herron Ave:16", "Swissville:21",
                "(U)Penn Station:25", "(U)Steel Plaza:35",
                "(U)First Ave:45", "Station Square:48", "South Hills Jct:60");
        stationChoiceBox.getSelectionModel().select("Select...");
        
        //hide newTrainPane. will appear on add train button press
        newTrainPane.setVisible(false);

//        **testing of clock feature
//        long offsetTime = (long) (System.currentTimeMillis() + 3.7*60*1000);
//        System.out.println(timeFormat.format(System.currentTimeMillis()));
//        System.out.println(timeFormat.format(offsetTime));
    }

    //track table information for all lines
    @FXML
    private TableView<Track> trackTableAll;

    @FXML
    private TableColumn<Track, String> trackTableAllLine;

    @FXML
    private TableColumn<Track, String> trackTableAllSection;

    @FXML
    private TableColumn<Track, Integer> trackTableAllBlock;

    @FXML
    private TableColumn<Track, Integer> trackTableAllLength;

    @FXML
    private TableColumn<Track, Integer> trackTableAllLimit;

    @FXML
    private TableColumn<Track, String> trackTableAllState;

    //train table information for all lines
    @FXML
    private TableView<Train> trainTableAll;

    @FXML
    private TableColumn<Train, String> trainTableAllLine;

    @FXML
    private TableColumn<Train, Integer> trainTableAllNumber;

    @FXML
    private TableColumn<Train, Integer> trainTableAllSpeed;

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
    private Label systemTimeText;

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
    private Button testShowSchedulesButton;
    
    @FXML
    private Button getThroughputButton;

    @FXML
    private ChoiceBox<String> stationChoiceBox;

    @FXML
    private Button deleteTrainButton;

    @FXML
    private Button changeTrackStateButton;

    //GUI ActionEvent Handlers
    @FXML
    void autoModeButtonClick(ActionEvent event) {
        //TODO later
        timeline.stop();
        setTime(Integer.parseInt(multiplierTextField.getText()));
    }

    @FXML
    void dispatchButtonClick(ActionEvent event) {

        //dummy info for just testing occupying the track table
//        //will delete this eventually...
//        Train train1 = new Train("Red", 12, 33, 13, 1, 24);
//        Train train2 = new Train("Green", 22, 26, 27, 34, 79);
//        Track track1 = new Track("Red", "A", 1, 175, 35, "Occupied");
//        Track track2 = new Track("Green", "I", 34, 175, 35, "Occupied");
//        addTrainToTable(train1);
//        addTrainToTable(train2);
//        trackTableAll.getItems().add(track1);
//        trackTableAll.getItems().add(track2);
//        trackTableRed.getItems().add(track1);
//        trackTableGreen.getItems().add(track2);
        //get selected train from the train table
        Train dispatchTrain = queueTrainTable.getSelectionModel().getSelectedItem();
        int dispatchNumber = dispatchTrain.getNumber(); //train ID
        double dispatchSpeed = dispatchTrain.getSpeed();
        int dispatchCurrentBlock = dispatchTrain.getBlock();
        int dispatchTargetBlock = dispatchTrain.getTarget();

        //move train to outbound table
        dispatchTrainFromQueue(dispatchTrain);

        //
        Schedule schedule = getScheduleInfoFromTrainTableSelected(dispatchTrain);
        schedule.dispatchTime = System.currentTimeMillis();
        //conversion from timetoNext block is going to be sloppy... need to do it properly in the future
        //actually it might be okay... not sure
        long arrivalTime = (long) (schedule.dispatchTime + schedule.timeToNextBlock[schedule.scheduleIndex] * 60 * 1000);
        System.out.println("departure time: " + timeFormat.format(schedule.dispatchTime));
        System.out.println("arrival time: " + timeFormat.format(arrivalTime));

        //popup box with CTC dispatch info
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CTC Outputs");
        alert.setHeaderText("Train " + dispatchNumber + " is being dispatched from block " + dispatchCurrentBlock);
        alert.setContentText("Suggested speed: " + dispatchSpeed + "\nTarget Block: "
                + dispatchTargetBlock + "\nArrival Time: " + timeFormat.format(arrivalTime));
        alert.showAndWait();

    }

    //schedule loaded in will be one train per file
    @FXML
    void loadScheduleButtonClick(ActionEvent event) {
        String csvFile;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse for Train Schedule");
        File file = fileChooser.showOpenDialog(null);

        csvFile = file.getPath();
        //System.out.println(csvFile);

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

    @FXML
    void newTrainButtonClick(ActionEvent event) {
        newTrainPane.setVisible(true);
        newTrainLineBox.getSelectionModel().select("Red");
        stationChoiceBox.getSelectionModel().select("Select...");
        suggestedSpeedSlider.setValue(0);
        suggestedSpeedLabel.setText("0");
        newTrainTargetBlock.setText("");
    }
    
        
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
    
    @FXML
    void newTrainLineBoxChange(MouseEvent event) {
        stationChoiceBox.getSelectionModel().select("Select...");
    }
    
    @FXML
    void newTrainTargetBlockType(KeyEvent event) {
        stationChoiceBox.getSelectionModel().select("Select...");
    }
    
    
    @FXML
    void deleteTrainButtonClick(ActionEvent event) {
        Train train = queueTrainTable.getSelectionModel().getSelectedItem();
        queueTrainTable.getItems().remove(train);
    }

    @FXML
    void newTrainSubmitClick(ActionEvent event) {
        //try {
            int newTrainNumber = trainIDIterator++;
            String newTrainLine = newTrainLineBox.getValue();
            double newTrainSpeed = (int) suggestedSpeedSlider.getValue();
            int newTrainAuthority = getTargetBlockFromStation();
            
            //System.out.println(newTrainNumber + "   " + newTrainLine + "   " + newTrainSpeed + "   " + newTrainAuthority);
            
            //TrainApplication ta1 = new TrainApplication();
            //ta1.stuff();
            
            ta.stuff();
            ta.potatoes();
            
            ta.addTrain(newTrainNumber, newTrainLine, newTrainSpeed, newTrainAuthority);
            Train newTrain = ta.getTrain(newTrainNumber);
            addTrainToQueue(newTrain);

            //add new train info to schedule here
            Schedule manualTrainAddSchedule = newTrainMakeSchedule(newTrain);
            scheduleArray = Arrays.copyOf(scheduleArray, scheduleArray.length + 1);
            scheduleArray[newTrainNumber] = manualTrainAddSchedule;

            newTrainPane.setVisible(false);
//        } catch (Exception ex) {
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Train Target Invalid!");
//            alert.setContentText(ex.getMessage());
//            alert.showAndWait();
//            trainIDIterator--; //have to reset the trainID value
//            //newTrainPane.setVisible(false);
//        }

    }
    
    @FXML
    void newTrainCloseClick(ActionEvent event) {
        newTrainPane.setVisible(false);
    }

    @FXML
    void suggestedSpeedSliderDrag(MouseEvent event) {
        int suggestedSpeed = (int) suggestedSpeedSlider.getValue();
        suggestedSpeedLabel.setText(Integer.toString(suggestedSpeed));
    }

    @FXML
    void menuCloseClick(ActionEvent event) {

    }

    @FXML
    void menuDispatchClick(ActionEvent event) {
        dispatchButtonClick(event);
    }

    @FXML
    void menuLoadScheduleClick(ActionEvent event) {
        loadScheduleButtonClick(event);
    }

    @FXML
    void menuNewTrainClick(ActionEvent event) {
        newTrainButtonClick(event);
    }

    @FXML
    void menuUserManualClick(ActionEvent event) {

    }
    
    @FXML
    void changeTrackStateButtonClick(ActionEvent event) {
        //change state of selected track from open to maintenance or vice versa
    }
    
    @FXML
    void getThroughputButtonClick(ActionEvent event) {
        //get throughput information from Track Controller
    }


    @FXML
    void testShowSchedulesClick(ActionEvent event) {
        for (Schedule schedule : scheduleArray) {
            System.out.println("Line, ID, current block array, target block array, dispatch time, time to next block array, index for the arrays");
            System.out.println("Line " + schedule.line);
            System.out.println("Train Number " + schedule.trainID);
            System.out.println("");
            System.out.print("Target block array: ");
            for (int tBlock : schedule.targetBlock) {
                System.out.print(tBlock + ", ");
            }
            System.out.println("");
            System.out.println("Dispatch time: " + timeFormat.format(schedule.dispatchTime));
            System.out.print("Time to next block array: ");
            for (double timeBlock : schedule.timeToNextBlock) {
                System.out.print(timeBlock + ", ");
            }
            System.out.println("");
            System.out.println("Schedule index " + schedule.scheduleIndex);
            System.out.println("\n\n");

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
    private void setTime(int multiplier) {

        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000), event -> {
                    //systemTimeText.setText(timeFormat.format(System.currentTimeMillis()));
                    currentTime += multiplier * 1000;
                    systemTimeText.setText(timeFormat.format(currentTime));
                    //can speedup time by multiplying speedup by System.currentTimeMillis()
                    //this however also changes the base system time, so not sure if that would work
                }
                )
        );

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /*
    create a train object based on the information
    train info passed in will be as follows:
    line, section(not used), current block, target block(=authority), time to target
    sends train to be added to the queue
     */
    private void createTrainFromSchedule(Schedule schedule) {

        String line = schedule.line;
        int number = schedule.trainID;
        int speed = 25; //temp value, not sure how I'm setting this yet
        int authority = schedule.targetBlock[schedule.scheduleIndex];

        //Train train = new Train(line, number, speed, authority, block, target);
        ta.addTrain(number, line, speed, authority);
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
        schedule.timeToNextBlock[index] = Double.parseDouble(trainAttributes[4]);

        return schedule;
    }

    //increase the index of the schedule component arrays to avoid null pointer exceptions while adding schedule elements
    private Schedule resizeScheduleArray(Schedule schedule) {
        schedule.targetBlock = Arrays.copyOf(schedule.targetBlock, schedule.targetBlock.length + 1);
        schedule.timeToNextBlock = Arrays.copyOf(schedule.timeToNextBlock, schedule.timeToNextBlock.length + 1);
        return schedule;
    }

    //takes train information as an input and adds a train to the appropriate table
    private void addTrainToQueue(Train train) {
        queueTrainTable.getItems().add(train);
        int trainID = train.getNumber();
        trainArray[trainID] = train;
        trainArray = Arrays.copyOf(trainArray, trainArray.length + 1);    
    }

    //removes the selected train from the queue and adds it to the outbound table
    private void dispatchTrainFromQueue(Train train) {
        queueTrainTable.getItems().remove(train);
        train.setSpeed(0);
        trainTableAll.getItems().add(train);
    }

    //updates outbound table when a train reaches its target
    private void updateTrainTable() {

    }

    //get the schedule associated with the selected train
    private Schedule getScheduleInfoFromTrainTableSelected(Train train) {
        //need to search schedule array and to find the scheudle with the same trainID as the train parameter
        int trainID = train.getNumber();
        Schedule schedule = scheduleArray[trainID];
        return schedule;
    }

    private Schedule newTrainMakeSchedule(Train train) {
        Schedule schedule = new Schedule("", 0, new int[1], 0, new double[1], 0);
        resizeScheduleArray(schedule); //need to add some size to the original arrays created
        schedule.line = train.getLine();
        schedule.trainID = train.getNumber();
        schedule.targetBlock[0] = train.getTarget();
        schedule.dispatchTime = currentTime;
        schedule.timeToNextBlock[0] = 1.5; //dont really have a number for this yet...
        schedule.scheduleIndex = 0;
        return schedule;
    }
    
    //get the speed and authority of the train that is being dispatched
    //part of dispatching train when sending that info to a track controller
    private void getSpeedAuthority() {
        
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
}
