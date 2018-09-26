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
    
    private String line;
    private String section;
    private int blockNumber;
    private int blockLength;
    private Double blockGrade;
    private int speedLimit;
    private String infrastructure;
    private double elevation;
    private double cumElevation;
    private String state;

    public Track(String line, String section, Integer blockNumber, Integer blockLength, Double blockGrade, Integer speedLimit, String infrastructure, Double elevation, Double cumElevation, String state) {
        this.line = line;
        this.section = section;
        this.blockNumber = blockNumber;
        this.blockLength = blockLength;
        this.blockGrade = blockGrade;
        this.speedLimit = speedLimit;
        this.infrastructure = infrastructure;
        this.elevation = elevation;
        this.cumElevation = cumElevation;
        this.state = state;
    }

    public Track(String line, String section, Integer blockNumber, Integer blockLength, Integer speedLimit, String state) {
        this.line = line;
        this.section = section;
        this.blockNumber = blockNumber;
        this.blockLength = blockLength;
        this.speedLimit = speedLimit;
        this.state = state;
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

    public Integer getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(int blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Integer getBlockLength() {
        return blockLength;
    }

    public void setBlockLength(int blockLength) {
        this.blockLength = blockLength;
    }

    public Double getBlockGrade() {
        return blockGrade;
    }

    public void setBlockGrade(double blockGrade) {
        this.blockGrade = blockGrade;
    }

    public Integer getSpeedLimit() {
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

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    public Double getCumElevation() {
        return cumElevation;
    }

    public void setCumElevation(double cumElevation) {
        this.cumElevation = cumElevation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
   
}
