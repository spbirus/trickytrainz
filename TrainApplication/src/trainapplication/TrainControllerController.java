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
public class TrainControllerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO: need a for loop that will go through database and add all of the trains
        Train t = new Train("red",1,2,3,4,5,6,7,8,9,10,11,12);        
        track.setCellValueFactory(new PropertyValueFactory<>("track"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        width.setCellValueFactory(new PropertyValueFactory<>("width"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        mass.setCellValueFactory(new PropertyValueFactory<>("mass"));
        crewNum.setCellValueFactory(new PropertyValueFactory<>("crewNum"));
        passNum.setCellValueFactory(new PropertyValueFactory<>("passNum"));
        maxCap.setCellValueFactory(new PropertyValueFactory<>("maxCap"));
        carNum.setCellValueFactory(new PropertyValueFactory<>("carNum"));
        doorNum.setCellValueFactory(new PropertyValueFactory<>("doorNum"));
        accelLimit.setCellValueFactory(new PropertyValueFactory<>("accelLimit"));
        deccelLimit.setCellValueFactory(new PropertyValueFactory<>("deccelLimit"));
        //trainTable.getItems().add(t);
        trainTable.getItems().add(t);
    }   
    
    @FXML
    private TableView<Train> trainTable;

    @FXML
    private TableColumn<Train, Integer> number;

    @FXML
    private TableColumn<Train, Integer> length;

    @FXML
    private TableColumn<Train, Integer> width;

    @FXML
    private TableColumn<Train, Integer> height;

    @FXML
    private TableColumn<Train, Integer> mass;

    @FXML
    private TableColumn<Train, Integer> crewNum;

    @FXML
    private TableColumn<Train, Integer> passNum;

    @FXML
    private TableColumn<Train, Integer> maxCap;

    @FXML
    private TableColumn<Train, Integer> carNum;

    @FXML
    private TableColumn<Train, Integer> doorNum;

    @FXML
    private TableColumn<Train, Integer> accelLimit;

    @FXML
    private TableColumn<Train, Integer> deccelLimit;

    @FXML
    private TableColumn<Train, String> track;

    
}
