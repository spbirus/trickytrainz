/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication;

/**
 *
 * @author Michael
 */
public class Track {
    
    //Physical Track Parameters Determined by the Excel or csv input
    private String line;
    private String section;
    private int blockNumber;
    private int blockLength;
    private double blockGrade;
    private int speedLimit;
    private String infrastructure;
    private double elevation;
    private double cumElevation;
    
    //Track State Parameters Determined by the state of the system
    private String switchState;
    private String blockState;
    private String beacon;
    

    public Track(String line, String section, int blockNumber, int blockLength, double blockGrade, int speedLimit, String infrastructure, double elevation, double cumElevation, String blockState) {
        this.line = line;
        this.section = section;
        this.blockNumber = blockNumber;
        this.blockLength = blockLength;
        this.blockGrade = blockGrade;
        this.speedLimit = speedLimit;
        this.infrastructure = infrastructure;
        this.elevation = elevation;
        this.cumElevation = cumElevation;
    }

    public Track(String line, String section, int blockNumber, int blockLength, int speedLimit, String blockState) {
        this.line = line;
        this.section = section;
        this.blockNumber = blockNumber;
        this.blockLength = blockLength;
        this.speedLimit = speedLimit;
        this.blockState = blockState;
    }
   

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public int getBlockLength() {
        return blockLength;
    }

    public void setBlockLength(int blockLength) {
        this.blockLength = blockLength;
    }

    public double getBlockGrade() {
        return blockGrade;
    }

    public void setBlockGrade(double blockGrade) {
        this.blockGrade = blockGrade;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public String getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public double getCumElevation() {
        return cumElevation;
    }

    public void setCumElevation(double cumElevation) {
        this.cumElevation = cumElevation;
    }

    public String getBlockState() {
        return blockState;
    }

    public void setBlockState(String blockState) {
        this.blockState = blockState;
    }

    public String getSwitchState() {
        return switchState;
    }

    public void setSwitchState(String switchState) {
        this.switchState = switchState;
    }

    public String getBeacon() {
        return beacon;
    }

    public void setBeacon(String beacon) {
        this.beacon = beacon;
    }
   
}
