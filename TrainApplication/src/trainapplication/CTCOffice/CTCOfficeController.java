/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.CTCOffice;

import trainapplication.Track;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

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
    private Schedule[] scheduleArray = new Schedule[1]; //array of schedules, will hold all schedules laoded in
    private DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
    private long currentTime = System.currentTimeMillis();
    private Timeline timeline = new Timeline();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        //set column to be a drop down to select red or green line (only in all table)
        ObservableList<String> list
                = FXCollections.observableArrayList();
        list.add("Red");
        list.add("Green");
        trainTableAllLine.setCellFactory(ComboBoxTableCell.forTableColumn(list));

        //init train tables for each line
        trainTableAllLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trainTableAllNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        trainTableAllSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        trainTableAllAuthority.setCellValueFactory(new PropertyValueFactory<>("authority"));
        trainTableAllCurrent.setCellValueFactory(new PropertyValueFactory<>("block"));
        trainTableAllTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        trainTableAllLine.setStyle("-fx-alignment: CENTER;");
        trainTableAllNumber.setStyle("-fx-alignment: CENTER;");
        trainTableAllSpeed.setStyle("-fx-alignment: CENTER;");
        trainTableAllAuthority.setStyle("-fx-alignment: CENTER;");
        trainTableAllCurrent.setStyle("-fx-alignment: CENTER;");
        trainTableAllTarget.setStyle("-fx-alignment: CENTER;");
        //trainTableAllLine.setCellFactory(TextFieldTableCell.forTableColumn()); //this sets things as editable, but it doesnt work for non-strings yet

        trainTableRedLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trainTableRedNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        trainTableRedSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        trainTableRedAuthority.setCellValueFactory(new PropertyValueFactory<>("authority"));
        trainTableRedCurrent.setCellValueFactory(new PropertyValueFactory<>("block"));
        trainTableRedTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        trainTableRedLine.setStyle("-fx-alignment: CENTER;");
        trainTableRedNumber.setStyle("-fx-alignment: CENTER;");
        trainTableRedSpeed.setStyle("-fx-alignment: CENTER;");
        trainTableRedAuthority.setStyle("-fx-alignment: CENTER;");
        trainTableRedCurrent.setStyle("-fx-alignment: CENTER;");
        trainTableRedTarget.setStyle("-fx-alignment: CENTER;");

        trainTableGreenLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trainTableGreenNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        trainTableGreenSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        trainTableGreenAuthority.setCellValueFactory(new PropertyValueFactory<>("authority"));
        trainTableGreenCurrent.setCellValueFactory(new PropertyValueFactory<>("block"));
        trainTableGreenTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        trainTableGreenLine.setStyle("-fx-alignment: CENTER;");
        trainTableGreenNumber.setStyle("-fx-alignment: CENTER;");
        trainTableGreenSpeed.setStyle("-fx-alignment: CENTER;");
        trainTableGreenAuthority.setStyle("-fx-alignment: CENTER;");
        trainTableGreenCurrent.setStyle("-fx-alignment: CENTER;");
        trainTableGreenTarget.setStyle("-fx-alignment: CENTER;");

        //init track tables for each line
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

        trackTableRedLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trackTableRedSection.setCellValueFactory(new PropertyValueFactory<>("section"));
        trackTableRedBlock.setCellValueFactory(new PropertyValueFactory<>("blockNumber"));
        trackTableRedLength.setCellValueFactory(new PropertyValueFactory<>("blockLength"));
        trackTableRedLimit.setCellValueFactory(new PropertyValueFactory<>("speedLimit"));
        trackTableRedState.setCellValueFactory(new PropertyValueFactory<>("state"));
        trackTableRedLine.setStyle("-fx-alignment: CENTER;");
        trackTableRedSection.setStyle("-fx-alignment: CENTER;");
        trackTableRedBlock.setStyle("-fx-alignment: CENTER;");
        trackTableRedLength.setStyle("-fx-alignment: CENTER;");
        trackTableRedLimit.setStyle("-fx-alignment: CENTER;");
        trackTableRedState.setStyle("-fx-alignment: CENTER;");

        trackTableGreenLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trackTableGreenSection.setCellValueFactory(new PropertyValueFactory<>("section"));
        trackTableGreenBlock.setCellValueFactory(new PropertyValueFactory<>("blockNumber"));
        trackTableGreenLength.setCellValueFactory(new PropertyValueFactory<>("blockLength"));
        trackTableGreenLimit.setCellValueFactory(new PropertyValueFactory<>("speedLimit"));
        trackTableGreenState.setCellValueFactory(new PropertyValueFactory<>("state"));
        trackTableGreenLine.setStyle("-fx-alignment: CENTER;");
        trackTableGreenSection.setStyle("-fx-alignment: CENTER;");
        trackTableGreenBlock.setStyle("-fx-alignment: CENTER;");
        trackTableGreenLength.setStyle("-fx-alignment: CENTER;");
        trackTableGreenLimit.setStyle("-fx-alignment: CENTER;");
        trackTableGreenState.setStyle("-fx-alignment: CENTER;");

        //set system time to display
        multiplierTextField.setText("1");
        setTime(Integer.parseInt(multiplierTextField.getText()));
        
        //init choicebox in the new train popup screen
        newTrainLineBox.getItems().addAll("Red", "Green");
        newTrainLineBox.getSelectionModel().select("Red");
        
        //hide newTrainPane. will appear on add train button press
        newTrainPane.setVisible(false);
        

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
    private TableColumn<Train, Integer> trainTableAllAuthority;

    @FXML
    private TableColumn<Train, Integer> trainTableAllCurrent;

    @FXML
    private TableColumn<Train, Integer> trainTableAllTarget;

    //track table information for red line
    @FXML
    private TableView<Track> trackTableRed;

    @FXML
    private TableColumn<Track, String> trackTableRedLine;

    @FXML
    private TableColumn<Track, String> trackTableRedSection;

    @FXML
    private TableColumn<Track, Integer> trackTableRedBlock;

    @FXML
    private TableColumn<Track, Integer> trackTableRedLength;

    @FXML
    private TableColumn<Track, Integer> trackTableRedLimit;

    @FXML
    private TableColumn<Track, String> trackTableRedState;

    //train table information for red line
    @FXML
    private TableView<Train> trainTableRed;

    @FXML
    private TableColumn<Train, String> trainTableRedLine;

    @FXML
    private TableColumn<Train, Integer> trainTableRedNumber;

    @FXML
    private TableColumn<Train, Integer> trainTableRedSpeed;

    @FXML
    private TableColumn<Train, Integer> trainTableRedAuthority;

    @FXML
    private TableColumn<Train, Integer> trainTableRedCurrent;

    @FXML
    private TableColumn<Train, Integer> trainTableRedTarget;

    //track table information for green line
    @FXML
    private TableView<Track> trackTableGreen;

    @FXML
    private TableColumn<Track, String> trackTableGreenLine;

    @FXML
    private TableColumn<Track, String> trackTableGreenSection;

    @FXML
    private TableColumn<Track, Integer> trackTableGreenBlock;

    @FXML
    private TableColumn<Track, Integer> trackTableGreenLength;

    @FXML
    private TableColumn<Track, Integer> trackTableGreenLimit;

    @FXML
    private TableColumn<Track, String> trackTableGreenState;

    //train table information for green line
    @FXML
    private TableView<Train> trainTableGreen;

    @FXML
    private TableColumn<Train, String> trainTableGreenLine;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenNumber;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenSpeed;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenAuthority;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenCurrent;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenTarget;

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
        Train dispatchTrain = trainTableAll.getSelectionModel().getSelectedItem();
        int dispatchNumber = dispatchTrain.getNumber(); //train ID
        double dispatchSpeed = dispatchTrain.getSpeed();
        int dispatchCurrentBlock = dispatchTrain.getBlock();
        int dispatchTargetBlock = dispatchTrain.getTarget();
        
        //
        Schedule schedule = getScheduleInfoFromTrainTableSelected(dispatchTrain);
        schedule.dispatchTime = System.currentTimeMillis();
        //conversion from timetoNext block is going to be sloppy... need to do it properly in the future
        //actually it might be okay... not sure
        long arrivalTime = (long) (schedule.dispatchTime + schedule.timeToNextBlock[schedule.scheduleIndex]*60*1000);
        System.out.println("departure time: " + timeFormat.format(schedule.dispatchTime));
        System.out.println("arrival time: " + timeFormat.format(arrivalTime));
        
        //popup box with CTC dispatch info
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("CTC Outputs");
        alert.setHeaderText("Train " + dispatchNumber + " is being dispatched from block " + dispatchCurrentBlock);
        alert.setContentText("Suggested speed: " + dispatchSpeed + "\nTarget Block: " + dispatchTargetBlock + "\nArrival Time: " + timeFormat.format(arrivalTime));
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

            Schedule schedule = new Schedule("", 0, new int[1], new int[1], 0, new double[1], 0);
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
            scheduleArray = Arrays.copyOf(scheduleArray, scheduleArray.length + 1); //increment array size to avoid null pointers when another schedule loaded in
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
        suggestedSpeedSlider.setValue(0);
        suggestedSpeedLabel.setText("0");
        newTrainTargetBlock.setText("");
    }
    
    @FXML
    void newTrainSubmitClick(ActionEvent event) {
        String newTrainLine = newTrainLineBox.getValue();
        int newTrainNumber = trainIDIterator++;
        double newTrainSpeed = (int) suggestedSpeedSlider.getValue();
        int newTrainAuthority = Integer.parseInt(newTrainTargetBlock.getText());
        int newTrainCurrentBlock = 0; //start in the yard
        int newTrainTarget = newTrainAuthority;
                //currently authority and target are the same, but this might change
        Train newTrain = new Train(newTrainLine, newTrainNumber, newTrainSpeed, newTrainAuthority, newTrainCurrentBlock, newTrainTarget);
        addTrainToTable(newTrain);
        
        //add new train info to schedule here
        Schedule manualTrainAddSchedule = newTrainMakeSchedule(newTrain);
        scheduleArray = Arrays.copyOf(scheduleArray, scheduleArray.length + 1);
        scheduleArray[newTrainNumber] = manualTrainAddSchedule;
        
        
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
    void testShowSchedulesClick(ActionEvent event) {
        for (Schedule schedule : scheduleArray) {
            System.out.println("Line, ID, current block array, target block array, dispatch time, time to next block array, index for the arrays");
            System.out.println ("Line " + schedule.line);
            System.out.println ("Train Number " + schedule.trainID);
            System.out.print("Current block array: ");
            for (int block : schedule.currentBlock) {
                System.out.print(block + ", ");
            }
            System.out.println("");
            System.out.print("Target block array: ");
            for (int tBlock : schedule.targetBlock) {
                System.out.print(tBlock + ", ");
            }
            System.out.println("");
            System.out.println ("Dispatch time: " + timeFormat.format(schedule.dispatchTime));
            System.out.print("Time to next block array: ");
            for (double timeBlock : schedule.timeToNextBlock) {
                System.out.print(timeBlock + ", ");
            }
            System.out.println("");
            System.out.println ("Schedule index " +schedule.scheduleIndex);
            System.out.println("\n\n");
            
        }
    }
    
    
    
    
    
    
    //rest of methods, non event-handlers
    
    private void setTime(int multiplier) {
                
        timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(1000), event -> {
                    //systemTimeText.setText(timeFormat.format(System.currentTimeMillis()));
                    currentTime += multiplier*1000;
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
     */
    private void createTrainFromSchedule(Schedule schedule) {
        
        String line = schedule.line;
        int number = schedule.trainID;
        int speed = 25; //temp value, not sure how I'm setting this yet
        int authority = schedule.targetBlock[schedule.scheduleIndex];
        int block = schedule.currentBlock[schedule.scheduleIndex];
        int target = schedule.targetBlock[schedule.scheduleIndex];
        
        Train train = new Train(line, number, speed, authority, block, target);
        addTrainToTable(train);
    }

    private Schedule createTrainSchedule(Schedule schedule, String[] trainAttributes, int index) {
       
        //need to put some sort of current_index variable into the schedule objects
        //so I know what part of the schedule to load in once a train reaches its destination
        schedule = resizeScheduleArray(schedule);
        schedule.line = trainAttributes[0];
        schedule.trainID = trainIDIterator;
        schedule.currentBlock[index] = Integer.parseInt(trainAttributes[2]);
        schedule.targetBlock[index] = Integer.parseInt(trainAttributes[3]);
        schedule.dispatchTime = System.currentTimeMillis();
        schedule.timeToNextBlock[index] = Double.parseDouble(trainAttributes[4]);

        return schedule;

    }

    //increase the index of the schedule component arrays to avoid null pointer exceptions while adding schedule elements
    private Schedule resizeScheduleArray(Schedule schedule) {
        schedule.currentBlock = Arrays.copyOf(schedule.currentBlock, schedule.currentBlock.length + 1);
        schedule.targetBlock = Arrays.copyOf(schedule.targetBlock, schedule.targetBlock.length + 1);
        schedule.timeToNextBlock = Arrays.copyOf(schedule.timeToNextBlock, schedule.timeToNextBlock.length + 1);
        return schedule;
    }

    //takes train information as an input and adds a train to the appropriate table
    private void addTrainToTable(Train train) {
        if (train.getLine().equalsIgnoreCase("Red")) {
            trainTableRed.getItems().add(train);
        } else if (train.getLine().equalsIgnoreCase("Green")) {
            trainTableGreen.getItems().add(train);
        }
        trainTableAll.getItems().add(train);
    }

    //updates table when a train reaches its target
    //for demonstration purposes, will base it on a time factor in the schedule
    private void updateTrainTable() {

    }
    
    private Schedule getScheduleInfoFromTrainTableSelected(Train train) {
        //need to search schedule array and to find the scheudle with the same trainID as the train parameter
        int trainID = train.getNumber();
        Schedule schedule = scheduleArray[trainID];
        return schedule;
    }
    
    private Schedule newTrainMakeSchedule(Train train) {
        Schedule schedule = new Schedule("", 0, new int[1], new int[1], 0, new double[1], 0);
        resizeScheduleArray(schedule); //need to add some size to the original arrays created
        schedule.line = train.getLine();
        schedule.trainID = train.getNumber();
        schedule.currentBlock[0] = train.getBlock();
        schedule.targetBlock[0] = train.getTarget();
        schedule.dispatchTime = currentTime;
        schedule.timeToNextBlock[0] = 1.5; //dont really have a number for this yet...
        schedule.scheduleIndex = 0;
        return schedule;
    }
}
