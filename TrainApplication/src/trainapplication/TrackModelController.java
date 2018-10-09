/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        
        ArrayList<Track> trackList = new ArrayList<Track>() ;
        
        
        // TODO
        /*
        Track dummyTrack1= new Track("Green", "A", 1, 100, 1.5, 55, "", 1.0, 1.0, "Open");
        Track dummyTrack2 = new Track("Green", "A", 2, 100, 0.5, 55, "", 1.0, 1.0, "Open");
        Track dummyTrack3 = new Track("Green", "A", 3, 100, 0.5, 55, "", 1.0, 1.0, "Open");
        Track dummyTrack4 = new Track("Green", "A", 4, 100, 1.0, 55, "", 1.0, 1.0, "Open");
        Track dummyTrack5 = new Track("Green", "A", 5, 100, 1.0, 55, "", 1.0, 1.0, "Closed");
        Track dummyTrack6 = new Track("Green", "A", 6, 100, 0.0, 55, "Switch", 1.0, 1.0, "Open");
        Track dummyTrack7 = new Track("Green", "A", 7, 100, 1.0, 55, "", 1.0, 1.0, "Open");

        
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
        
        trackTable.getItems().add(dummyTrack1);
        trackTable.getItems().add(dummyTrack2);
        trackTable.getItems().add(dummyTrack3);
        trackTable.getItems().add(dummyTrack4);
        trackTable.getItems().add(dummyTrack5);
        trackTable.getItems().add(dummyTrack6);
        trackTable.getItems().add(dummyTrack7);
        */
        readTrackFile("Documents/TestTrackData.csv", trackList);
    } 
    
    public void readTrackFile(String filename, ArrayList<Track> trackList){

        String csvFile = filename;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                
                
                String[] trackDataString = line.split(cvsSplitBy);
                
                String trackLine = trackDataString[0];
                String trackSection = trackDataString[1];
                int  trackBlock = Integer.parseInt(trackDataString[2]);
                double trackBlockLength = Double.parseDouble(trackDataString[3]);
                double trackBlockGrade = Double.parseDouble(trackDataString[4]);
                int trackSpeedLimit = Integer.parseInt(trackDataString[5]);
                String trackInfrastructure = trackDataString[6];
                int trackNextInbound = Integer.parseInt(trackDataString[7]);
                int trackNextOutbound = Integer.parseInt(trackDataString[8]);
                double trackElevation = Double.parseDouble(trackDataString[9]);
                double trackCumElevation = Double.parseDouble(trackDataString[10]);
                
                Track newTrack = new Track(trackLine, trackSection, trackBlock, 
                        trackBlockLength, trackBlockGrade, trackSpeedLimit, 
                        trackInfrastructure, trackNextInbound, trackNextOutbound, 
                        trackElevation, trackCumElevation);
                
                trackList.add(newTrack);
                

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
    private TableColumn<Track, Double> blockLength;

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
