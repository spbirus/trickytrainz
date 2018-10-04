/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.io.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class CTCOfficeController implements Initializable {

    
    private int trainIDIterator = 1;
    private int redLineThroughput = 0;
    private int greenLineThroughput = 0;
    private int allLineThroughput = 0;
    private int hourlyPassengers = 0;
    private int hourlyTicketSales = 0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //set column to be a drop down to select red or green line (only in all table)
        ObservableList<String> list = 
        FXCollections.observableArrayList();
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
        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        final Timeline timeline = new Timeline(
            new KeyFrame(
                Duration.millis(500), event -> {
                    systemTimeText.setText(timeFormat.format(System.currentTimeMillis()*4));
                    //can speedup time by multiplying speedup by System.currentTimeMillis()
                    //this however also changes the base system time, so not sure if that would work
                }
            )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        
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

    //GUI ActionEvent Handlers
    @FXML
    void autoModeButtonClick(ActionEvent event) {
        //TODO later
    }

    @FXML
    void dispatchButtonClick(ActionEvent event) {
        Train train1 = new Train("Red", 12, 33, 13, 1, 24);
        Train train2 = new Train("Green", 22, 26, 27, 34, 79);
        Track track1 = new Track("Red", "A", 1, 175, 35, "Occupied");
        Track track2 = new Track("Green", "I", 34, 175, 35, "Occupied");

        addTrainToTable(train1);
        addTrainToTable(train2);
        trackTableAll.getItems().add(track1);
        trackTableAll.getItems().add(track2);
        trackTableRed.getItems().add(track1);
        trackTableGreen.getItems().add(track2);
    }

    @FXML
    void loadScheduleButtonClick(ActionEvent event) {
        String csvFile;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse for Train Schedule");
        File file = fileChooser.showOpenDialog(null);

        csvFile = file.getPath();
        System.out.println(csvFile);

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] trainInfo = line.split(cvsSplitBy);
                createTrainFromSchedule(trainInfo);

            }
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
        
        //temp dummy stuff to show what should happen I think?
        //do I want this to only fill in certain info and the user determines speed/authority?
            //that way the schedule only has to figure out current and target block
            
//        Train train1 = new Train("Red", 12, 33, 13, 1, 24);
//        Train train2 = new Train("Green", 22, 26, 27, 34, 79);
//        addTrainToTable(train1);
//        addTrainToTable(train2);
//        
//        System.out.println("currently not actually reading in a file. just setting random things");
//        
//        createTrainFromSchedule(new String[2]);

    }

    @FXML
    void newTrainButtonClick(ActionEvent event) {
        //TODO create row in table
        //or a popup gui where the user can select train info
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

    //rest of methods
    
    /*
    create a train object based on the information
    train info passed in will be as follows:
    line, section(not used), current block, target block(=authority), time to target
    */
    private void createTrainFromSchedule(String[] trainAttributes) {
        String line = trainAttributes[0];
        int number = trainIDIterator++;
        int speed = 0;
        int authority = Integer.parseInt(trainAttributes[2]);
        int block = Integer.parseInt(trainAttributes[2]);
        int target = Integer.parseInt(trainAttributes[3]);
        double timeToNextBlock = Double.parseDouble(trainAttributes[4]);
        Train train = new Train(line, number, speed, authority, block, target);
        
//        temp sample items
//        String line = "RED";
//        int number = trainIDIterator++;
//        int speed = 3;
//        int authority = 0;
//        int block = 1;
//        int target = 5;
//        Train train = new Train(line, number, speed, authority, block, target);
        
        addTrainToTable(train);
    }
    
    private void addTrainToTable(Train train) {
        if (train.getLine().equalsIgnoreCase("Red")){
            trainTableRed.getItems().add(train);
        } else if (train.getLine().equalsIgnoreCase("Green")) {
            trainTableGreen.getItems().add(train);
        }
        trainTableAll.getItems().add(train);
        
        System.out.println(train);
    }

}
