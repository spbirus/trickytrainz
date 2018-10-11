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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrackModelController implements Initializable {

    //
    ArrayList<Track> trackList = new ArrayList<Track>();
    ArrayList<Track> sortedTrackList = new ArrayList<Track>();
    
    //Gardcoded values for dropdown boxes 
    //TODO: Fill in with real data values
    ObservableList<String> trackLineList = FXCollections
            .observableArrayList("Green", "Red");
    ObservableList<String> trackSectionList = FXCollections
            .observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K");
    ObservableList<Integer> trackBlockList = FXCollections
            .observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        trackLineComboBox.setItems(trackLineList);
        trackSectionComboBox.setItems(trackSectionList);
        trackBlockComboBox.setItems(trackBlockList);
        
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
        
        // TODO
        /*
        Track dummyTrack1= new Track("Green", "A", 1, 100, 1.5, 55, "", 1, 1, 1.0, 1.0);
        Track dummyTrack2 = new Track("Green", "A", 1, 100, 1.5, 55, "", 1, 1, 1.0, 1.0);
        Track dummyTrack3 = new Track("Green", "A", 1, 100, 1.5, 55, "", 1, 1, 1.0, 1.0);
        Track dummyTrack4 = new Track("Green", "A", 1, 100, 1.5, 55, "", 1, 1, 1.0, 1.0);
        Track dummyTrack5 = new Track("Green", "A", 1, 100, 1.5, 55, "", 1, 1, 1.0, 1.0);
        Track dummyTrack6 = new Track("Green", "A", 1, 100, 1.5, 55, "", 1, 1, 1.0, 1.0);
        Track dummyTrack7 = new Track("Green", "A", 1, 100, 1.5, 55, "", 1, 1, 1.0, 1.0);

        trackTable.getItems().add(dummyTrack1);
        trackTable.getItems().add(dummyTrack2);
        trackTable.getItems().add(dummyTrack3);
        trackTable.getItems().add(dummyTrack4);
        trackTable.getItems().add(dummyTrack5);
        trackTable.getItems().add(dummyTrack6);
        trackTable.getItems().add(dummyTrack7);
        
        */
    } 
    
    public void readTrackFile(String filename){

        String csvFile = filename;
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                
                
                String[] trackDataString = line.split(csvSplitBy);
                
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
                //sortedTrackList.add(newTrack);
                trackTable.getItems().add(newTrack);

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
    
    public void FiltersButtonClicked(){
        
        String selectedLine = (String)trackLineComboBox.getValue();
        String selectedSection = (String)trackSectionComboBox.getValue();
        int selectedBlock = (int)trackBlockComboBox.getValue();
        
        String currSection = "";
        
        System.out.println("Block: " + selectedBlock + "\nSection: " + selectedSection
                            + "\nLine:" + selectedLine);
        
        for(Track track : trackList){
            System.out.println("Current Section" + currSection);
            if(track.getSection().equals(selectedSection) == false){
               trackTable.getItems().remove(track);
               System.out.println("NOTSAME");
            } 
              
        }
    }
    
    public void LoadButtonClicked(){
        
        String filename = filenameTextField.getText();
        readTrackFile(filename);
          
    }
    
    public void MainteneceButtonClicked(){
        
    }
    
    public void TrackCircuitButtonClicked(){
        
    }
    
    public void PowerButtonClicked(){
           
    }
    
    public void FixButtonClicked(){
        
    }
    
    public void ResetButtonClicked(){
        
    }    
    
    public void LineSelected(){
        
        
    }
    
    public void SectionSelected(){
        
    }
    
    public void BlockSelected(){
            
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
    
    @FXML 
    private ComboBox trackLineComboBox;
    
    @FXML
    private ComboBox trackSectionComboBox;
    
    @FXML
    private ComboBox trackBlockComboBox;
    
    @FXML
    private Button maintenenceButton;
    
    @FXML
    private Button trackCircuitButton;
    
    @FXML
    private Button powerButton;
    
    @FXML
    private Button fixButton;
    
    @FXML
    private Button resetButton;
    
    @FXML
    private Button filterButton;
    
    @FXML
    private TextField filenameTextField;
    
    @FXML
    private Button loadButton;
    
}
