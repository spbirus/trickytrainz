/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrackModelController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Track dummyTrack= new Track("Green", "A", 1, 100, 1.5, 55, "", 1.0, 1.0, "Open");
//        Track dummyTrack2 = new Track("Green", "A", 2, 100, 0.5, 55, "", 1.0, 1.0, "Open");
//        Track dummyTrack3 = new Track("Green", "A", 3, 100, 0.5, 55, "", 1.0, 1.0, "Open");
//        Track dummyTrack4 = new Track("Green", "A", 4, 100, 1.0, 55, "", 1.0, 1.0, "Open");
//        Track dummyTrack5 = new Track("Green", "A", 5, 100, 1.0, 55, "", 1.0, 1.0, "Closed");
//        Track dummyTrack6 = new Track("Green", "A", 6, 100, 0.0, 55, "Switch", 1.0, 1.0, "Open");
//        Track dummyTrack7 = new Track("Green", "A", 7, 100, 1.0, 55, "", 1.0, 1.0, "Open");
        
        line.setCellValueFactory(new PropertyValueFactory<>("line"));
        section.setCellValueFactory(new PropertyValueFactory<>("section"));
        block.setCellValueFactory(new PropertyValueFactory<>("block"));
        blockLength.setCellValueFactory(new PropertyValueFactory<>("blockLength"));
        blockGrade.setCellValueFactory(new PropertyValueFactory<>("blockGrade"));
        speedLimit.setCellValueFactory(new PropertyValueFactory<>("speedLimit"));
        infrastructure.setCellValueFactory(new PropertyValueFactory<>("infrastructure"));
        elevation.setCellValueFactory(new PropertyValueFactory<>("elevation"));
        cumElevation.setCellValueFactory(new PropertyValueFactory<>("cumElevation"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
 
        trackTable.getItems().add(dummyTrack);
//        trackTable.getItems().add(dummyTrack2);
//        trackTable.getItems().add(dummyTrack3);
//        trackTable.getItems().add(dummyTrack4);
//        trackTable.getItems().add(dummyTrack5);
//        trackTable.getItems().add(dummyTrack6);
//        trackTable.getItems().add(dummyTrack7);
        
    }  
    
    @FXML
    private TableView<Track> trackTable;
    
    @FXML
    private TableColumn<Track, String> line;

    @FXML
    private TableColumn<Track, String> section;

    @FXML
    private TableColumn<Track, Integer> block;

    @FXML
    private TableColumn<Track, Integer> blockLength;

    @FXML
    private TableColumn<Track, Double> blockGrade;

    @FXML
    private TableColumn<Track, Integer> speedLimit;

    @FXML
    private TableColumn<Track, String> infrastructure;

    @FXML
    private TableColumn<Track, Double> elevation;

    @FXML
    private TableColumn<Track, Double> cumElevation;

    @FXML
    private TableColumn<Track, String> state;
    
      
    
}
