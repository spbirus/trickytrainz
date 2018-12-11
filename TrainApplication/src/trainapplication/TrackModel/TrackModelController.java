/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackModel;

import com.sun.deploy.util.StringUtils;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import trainapplication.TrainApplication;

/**
 * FXML Controller class
 *
 * @author burri
 */
public class TrackModelController implements Initializable {
    
    private TrainApplication ta;
    
    public TrackModelController() {

    }
    
    public void setTrainApp(TrainApplication ta) {
        this.ta = ta;
    }

    //
    ArrayList<Track> trackList = new ArrayList<Track>();
    ArrayList<Block> sortedTrackList = new ArrayList<Block>();
    public int[] keyBlocks = {62, 76, 100, 29, 1, 57};
    public int[] greenSections = {58, 63, 77, 101, 28, 29};
    public boolean[] greenSectionOccupancy = {false, false, false, false, false, false};
    //TODO
    //Update to make new tracks "configurable"
    public Track greenTrack = new Track("green");
    public Track redTrack = new Track("red");
    public Track testTrack = new Track("test");
    
    
    HashMap<Integer, Block> greenMap = new HashMap<Integer, Block>();    
    
    
    //Gardcoded values for dropdown boxes 
    //TODO: Fill in with real data values
    ObservableList<String> trackLineList = FXCollections
            .observableArrayList("Green", "Red");
    ObservableList<String> trackSectionList = FXCollections
            .observableArrayList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K");
    ObservableList<Integer> trackBlockList = FXCollections
            .observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18);
    ObservableList<String> trackControllerList = FXCollections
            .observableArrayList("Green - 1", "Green - 2", "Green - 3", "Green - 4", "Green - 5", "Green - 6");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        trackLineComboBox.setItems(trackLineList);
        trackSectionComboBox.setItems(trackSectionList);
        trackBlockComboBox.setItems(trackBlockList);
        tcSelect.setItems(trackControllerList);
        
        line.setCellValueFactory(new PropertyValueFactory<>("line"));
        blockNumber.setCellValueFactory(new PropertyValueFactory<>("blockNumber"));
        blockLength.setCellValueFactory(new PropertyValueFactory<>("blockLength"));
        blockGrade.setCellValueFactory(new PropertyValueFactory<>("blockGrade"));
        speedLimit.setCellValueFactory(new PropertyValueFactory<>("speedLimit"));
        infrastructure.setCellValueFactory(new PropertyValueFactory<>("infrastructure"));
        elevation.setCellValueFactory(new PropertyValueFactory<>("elevation"));
        blockDirection.setCellValueFactory(new PropertyValueFactory<>("blockDirection"));
        blockState.setCellValueFactory(new PropertyValueFactory<>("blockState"));
        occupancy.setCellValueFactory(new PropertyValueFactory<>("occupancy"));
        blockHeat.setCellValueFactory(new PropertyValueFactory<>("blockHeat"));
        
       
    } 
    
        // Function used by CTC to get te distance to the destination block 
    public double getDistance(int start, int end) throws InterruptedException{
        Block b = getBlockAt("Green", start);
        Block target = getBlockAt("Green", end);
        Block prev = getBlockAt("Green", 0);
        double length = 0.0;
        System.out.println("Target; " + end);
        while(b.getBlockNumber() != target.getBlockNumber()){
            length += b.getBlockLength();
            Block curr = b;
            b = getNextBlock("Green", b.getBlockNumber(), prev.getBlockNumber());
            prev = curr;
            System.out.println("Distance Block: " + b.getBlockNumber() + " Previous Block: " + prev.getBlockNumber());
        }
        return length;
    }
    
    public void setTrackOccupancy(Block currBlock, Block prevBlock){
        currBlock.setOccupancy("Train");
        greenTrack.setOccupiedBlock(currBlock);
        currBlock.setBlockOccupancy(true);
        prevBlock.setOccupancy("");
        greenTrack.setPreviousBlock(prevBlock);
        prevBlock.setBlockOccupancy(false);
        trackTable.refresh();    
    }
    // Blocks off sections of the track when a train is on it
    // to set the signal for other trains 
    public void setSectionOccupancy(String line, Block block) throws InterruptedException{
      if(line.equals("Green")){
          for(int i = 0; i < 6; i++){
              if(block.getBlockNumber() == greenSections[i]){
                  if(!greenSectionOccupancy[i]){
                    ta.trkCtr[i].calculateSignal(true);
                    greenSectionOccupancy[i] = true;
                    ta.trkCtr[(i-1)%6].calculateSignal(false);
                    greenSectionOccupancy[(i-1)%6] = false;
                  }
              }
          }
      } 
    }
    
    // Function that will return the next block to the Train Model 
    // Also called from getDistance 
    public Block getNextBlock(String line, int number, int prevBlock) throws InterruptedException{
        Block b = getBlockAt(line, number);
        Block prev = getBlockAt(line, prevBlock);
        Block next = null;
        //Update GUI and information perteining to track state based on what block
        //the train is on
        if(line.equals("Green")){
            if(b.getBlockDirection() == 2 && b.getSection().equals("N")){
               if(prev.getBlockNumber() < b.getBlockNumber()){
                   //outbound (towards O)
                   next = b.nextBlock;
               }else{
                   //inbound (towards R)
                   next = b.previousBlock;
               }
            }else if(b.getBlockDirection() == 2){
                //Train is on section D, E, or F
                if(prev.getBlockNumber() < b.getBlockNumber()){
                    //inbound
                    next = b.nextBlock;
                }else{
                    //outbound
                    next = b.previousBlock;
                }
            }else{
                next = b.nextBlock;
            }
        }
        //System.out.println("Current Block " + b.getBlockNumber());
        //System.out.println("Next Block " + next.getBlockNumber());
        boolean switchState = false;
        boolean switchPresent = false;
        setTrackOccupancy(next, b);
        
        
        
        for( int i = 0; i < keyBlocks.length; i++){
            if(next.getBlockNumber() == keyBlocks[i] || b.getBlockNumber() == keyBlocks[i]){
                switchState = ta.trkCtr[i].calculateSwitch();
                System.out.print("\n\n\n\n" + switchState + "\n\n\n\n\n\n");
                switchPresent = (i == 5);
            }
        }
        
//        if(switchPresent){
//            if(switchState){
//                next = getBlockAt("Green", 58);
//                next.setOccupancy("Train");
//                next.setBlockOccupancy(true);
//                greenTrack.setOccupiedBlock(next);
//                getBlockAt("Green",0).setOccupancy("");
//                getBlockAt("Green",0).setBlockOccupancy(false);
//                return next;
//            }
//        }
        
        // If entering a new section of the track, set that signal to red 
        // to block off other trains 
        for(int i: greenSections){
            if(b.nextBlock.getBlockNumber() == i){
                setSectionOccupancy(line, b.nextBlock);
            }
        }       
        return next;
    }
    

    
    //Wrapper function for Train Model to receive information of block, and update GUI
    public Block getCurrentBlock(String line, int number){
        Block b = getBlockAt(line, number);       
        return b;
    }
    
    //Used by the Track Controller returns a Block object based on a Line and block number
    public Block getBlockAt(String line, int number){
        
        Block block = null;
        
        if(line.equals("Green")){
            return greenMap.get(number);
        }
        
        return block;
    }
    
    public void readTrackFile(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse for Train Schedule");
        File file = fileChooser.showOpenDialog(null);
        String csvFile = file.getPath();
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
                double trackSpeedLimit = Double.parseDouble(trackDataString[5]);
                String trackInfrastructure = trackDataString[6];
                int trackNextInbound = Integer.parseInt(trackDataString[7]);
                int trackNextOutbound = Integer.parseInt(trackDataString[8]);
                double trackElevation = Double.parseDouble(trackDataString[9]);
                double trackCumElevation = Double.parseDouble(trackDataString[10]);
                
                Block newBlock = new Block(trackLine, trackSection, trackBlock, 
                        trackBlockLength, trackBlockGrade, trackSpeedLimit, 
                        trackInfrastructure, trackNextInbound, trackNextOutbound, 
                        trackElevation, trackCumElevation);
//DEMO
//                newBlock.setBlockState("Green");
//                if(trackBlock == 13) newBlock.setOccupancy("Train");
//                else newBlock.setOccupancy("Open");
//                newBlock.setBlockHeat("ON");
//                if(trackInfrastructure.equals("SWITCH"))trackInfrastructure = "SWITCH-OPEN";
                greenMap.put(trackBlock, newBlock);
                greenTrack.blockList.add(newBlock);
                //sortedTrackList.add(newTrack);
                trackTable.getItems().add(newBlock);

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
        
        setBlockNeighbors();
        
        //DEMO
        trackLineComboBox.setValue("Green");
    }
    
    private void setBlockNeighbors(){
        
        int blockNum = 0;
        
        for(Block block : greenTrack.blockList){
            System.out.println("Block " + block.getNextInboundBlock());
            //Sets next Block for every block on the track
            block.setNextBlock(greenTrack.getTrackBlock(block.getNextInboundBlock()));
            
            //For Bidirectional tracks, sets the previous block to the next Outbound block
            //In other words, the next block for a train going away from the yard on a bidirectional track
            if(block.getNextInboundBlock() != block.getNextOutboundBlock()){
                block.setPreviousBlock(greenTrack.getTrackBlock(block.getNextOutboundBlock()));
            }
            
            //Sets the third block connected to a block in the case of a switch 
            // 
//            if(block.getInfrastructure().substring(0,6).equals("SWITCH")){
//                blockNum = Integer.parseInt(block.getInfrastructure().substring(block.getInfrastructure().lastIndexOf(";") + 1, 1));
//                block.setSwitchBlock(greenTrack.getBlockAt(blockNum));
//            }
            
        }
        
        
    }
    
    public void FiltersButtonClicked() throws InterruptedException, IOException{
        
//        ArrayList<Block> temp = trackList;
//        String selectedLine = (String)trackLineComboBox.getValue();
//        String selectedSection = (String)trackSectionComboBox.getValue();
//        //int selectedBlock = (int)trackBlockComboBox.getValue();
//        
//        for(Block track : trackList){
//            if(track.getSection().equals(selectedSection) == false){
//               trackTable.getItems().remove(track); 
//            } else {
//                sortedTrackList.add(track);
//            }     
//        }
//        
//        trackList = temp;
          String val = (String)tcSelect.getValue();
          int num = Integer.parseInt(val.split(" ", 3)[2]);
          ta.createTrackControllerGUI(num);
    
    
    }
    
    public void FilterResetButtonClicked(){
        
//        for(Block track : sortedTrackList){
//            sortedTrackList.remove(track);
//            trackTable.getItems().remove(track);
//        }
//        
//        for(Block track : trackList) trackTable.getItems().add(track);
    }
    
    public void LoadButtonClicked(){
        
        readTrackFile();
        
    }
    
    public void MainteneceButtonClicked(){
        
//        int selectedBlock = (int)trackBlockComboBox.getValue();
//        
//        for(Block track : sortedTrackList){
//            System.out.println("Selected: " + selectedBlock + "\nCurrent: " + track.getBlockNumber());
//            
//            if(selectedBlock == track.getBlockNumber()){
//                track.setBlockState("Red");
//                trackTable.refresh();
//            }
//        }


    int selectedBlock = (int)trackBlockComboBox.getValue();
    
    for(Block blk : greenTrack.blockList){
        trackTable.getItems().remove(blk);
        if(blk.getBlockNumber() == selectedBlock){
            sortedTrackList = greenTrack.getBlockNeighbors(blk);
            for(Block neighbor : sortedTrackList) trackTable.getItems().add(neighbor);
        }
    }

    }
    
    public void TrackCircuitButtonClicked(){
      
        int selectedBlock = (int)trackBlockComboBox.getValue();
         
        
        //        for(Block track : sortedTrackList){
//            sortedTrackList.remove(track);
//            trackTable.getItems().remove(track);
//        }
        for(Block track : sortedTrackList){
            System.out.println("Selected: " + selectedBlock + "\nCurrent: " + track.getBlockNumber());
            
            if(selectedBlock == track.getBlockNumber()){
                track.setOccupancy("Unknown");
                trackTable.refresh();
            }
        }  
    }
    
    public void PowerButtonClicked(){
        
        int selectedBlock = (int)trackBlockComboBox.getValue();
         
        for(Block track : sortedTrackList){
            System.out.println("Selected: " + selectedBlock + "\nCurrent: " + track.getBlockNumber());
            
            if(selectedBlock == track.getBlockNumber()){
                track.setBlockHeat("OFF");
                trackTable.refresh();
            }
        }    
    }
    
    public void FixButtonClicked(){
       
        int selectedBlock = (int)trackBlockComboBox.getValue();
        
        for(Block track : sortedTrackList){
            System.out.println("Selected: " + selectedBlock + "\nCurrent: " + track.getBlockNumber());
            
            if(selectedBlock == track.getBlockNumber()){
                track.setBlockState("Green");
                trackTable.refresh();
            }
        }
            
    }
    
    public void FixPowerButtonClicked(){
        
        int selectedBlock = (int)trackBlockComboBox.getValue();
        
        for(Block track : sortedTrackList){
            System.out.println("Selected: " + selectedBlock + "\nCurrent: " + track.getBlockNumber());
            
            if(selectedBlock == track.getBlockNumber()){
                track.setBlockHeat("ON");
                trackTable.refresh();
            }
        }
        
    }
     
    
    public void FixCircuitButtonClicked(){
        
        int selectedBlock = (int)trackBlockComboBox.getValue();
        
        for(Block track : sortedTrackList){
            System.out.println("Selected: " + selectedBlock + "\nCurrent: " + track.getBlockNumber());
            
            if(selectedBlock == track.getBlockNumber()){
                track.setOccupancy("Train");
                trackTable.refresh();
            }
        }
        
    }
    
    public void ResetButtonClicked(){
        
        for(Block track : sortedTrackList){
            if(track.getBlockNumber() == 13)track.setOccupancy("Train");
            else track.setOccupancy("Open");
            track.setBlockHeat("ON");
            track.setBlockState("Open");            
        }
        
    }    
    
    public void LineSelected(){
        
        
    }
    
    public void SectionSelected(){
        
    }
    
    public void BlockSelected(){
            
    }
    
    @FXML
    private ComboBox tcSelect;
    
    @FXML
    private TableView<Block> trackTable;
    
    @FXML 
    private TableColumn<Block, String> line;

    @FXML
    private TableColumn<Block, Integer> blockNumber;

    @FXML
    private TableColumn<Block, Double> blockLength;

    @FXML
    private TableColumn<Block, Double> blockGrade;

    @FXML
    private TableColumn<Block, Integer> speedLimit;

    @FXML
    private TableColumn<Block, String> infrastructure;

    @FXML
    private TableColumn<Block, Double> elevation;

    @FXML
    private TableColumn<Block, Double> blockDirection;

    @FXML
    private TableColumn<Block, String> blockState;
    
    @FXML
    private TableColumn<Block, String> occupancy;

    @FXML
    private TableColumn<Block, String> blockHeat;
    
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
    
    @FXML
    private Button fixCircuitButton;
    
    @FXML
    private Button fixPowerButton;
    
}
