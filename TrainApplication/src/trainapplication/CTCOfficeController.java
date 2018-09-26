/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class CTCOfficeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Train train1 = new Train ("red", 12, 36, 13, 1, 24);
        trainTableAllLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trainTableAllNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        trainTableAllSpeed.setCellValueFactory(new PropertyValueFactory<>("speed"));
        trainTableAllAuthority.setCellValueFactory(new PropertyValueFactory<>("authority"));
        trainTableAllCurrent.setCellValueFactory(new PropertyValueFactory<>("block"));
        trainTableAllTarget.setCellValueFactory(new PropertyValueFactory<>("target"));
        trainTableAll.getItems().add(train1);
        
        Track track1 = new Track ("red", "A", 1, 175, 35, "Occupied");
        trackTableAllLine.setCellValueFactory(new PropertyValueFactory<>("line"));
        trackTableAllSection.setCellValueFactory(new PropertyValueFactory<>("section"));
        trackTableAllBlock.setCellValueFactory(new PropertyValueFactory<>("blockNumber"));
        trackTableAllLength.setCellValueFactory(new PropertyValueFactory<>("blockLength"));
        trackTableAllLimit.setCellValueFactory(new PropertyValueFactory<>("speedLimit"));
        trackTableAllState.setCellValueFactory(new PropertyValueFactory<>("state"));
        trackTableAll.getItems().add(track1);
        
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
    private TableColumn<Train, Integer> trainTableGreenNumber;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenSpeed;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenAuthority;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenCurrent;

    @FXML
    private TableColumn<Train, Integer> trainTableGreenTarget;
    
}
