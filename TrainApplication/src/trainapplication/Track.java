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
   
    
    String line;
    String section;
    Integer blockNumber;
    Integer blockLength;
    Double blockGrade;
    Integer speedLimit;
    String infrastructure;
    Double elevation;
    Double cumElevation;
    String state;

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

    public void setBlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Integer getBlockLength() {
        return blockLength;
    }

    public void setBlockLength(Integer blockLength) {
        this.blockLength = blockLength;
    }

    public Double getBlockGrade() {
        return blockGrade;
    }

    public void setBlockGrade(Double blockGrade) {
        this.blockGrade = blockGrade;
    }

    public Integer getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(Integer speedLimit) {
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

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getCumElevation() {
        return cumElevation;
    }

    public void setCumElevation(Double cumElevation) {
        this.cumElevation = cumElevation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
   
}
