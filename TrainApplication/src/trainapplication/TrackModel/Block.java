/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackModel;

/**
 *
 * @author Michael
 */
public class Block {
    
    //Physical Block Parameters Determined by the Excel or csv input
    private String line;
    private String section;
    private int blockNumber;
    private double blockLength;
    private double blockGrade;
    private int speedLimit;
    private String infrastructure;
    private double elevation;
    private double cumElevation;
    //Additional CSV inputs
    //Maybe change type to Block
    private int nextInboundBlock;
    private int nextOutboundBlock;

    
    //Track State Parameters Determined by the state of the system
    private boolean switchState= false;
    private boolean switchPresent;
    private int switchDefault;
    private String blockState;
    private boolean blockOccupancy;
    private boolean stationPresent;
    private boolean crossingPresent;
    private boolean crossingState= true;
    private boolean circuitState= true;
    private boolean railState = true;

    public boolean isRailState() {
        return railState;
    }

    public void setRailState(boolean railState) {
        this.railState = railState;
    }
    private String signal="Green";
    private String beacon;
    private int passengersStation;
    private int passengersBoard;
    private String blockHeat;
    private String occupancy;
    private int blockDirection;
    
    
    //Data from csv file
    public Block(String line, String section, int blockNumber, double blockLength, double blockGrade, int speedLimit, String infrastructure, int nextInbound, int nextOutbound, double elevation, double cumElevation) {
        this.line = line;
        this.section = section;
        this.blockNumber = blockNumber;
        this.blockLength = blockLength;
        this.blockGrade = blockGrade;
        this.speedLimit = speedLimit;
        this.infrastructure = infrastructure;
        this.elevation = elevation;
        this.cumElevation = cumElevation;
        if(this.nextInboundBlock == nextOutboundBlock) blockDirection = 1;
        else blockDirection = 2;
    }
    //Jon Gramley - Block Controller
    public Block(String line, String section, int blockNumber, int nextInboundBlock, int nextOutboundBlock, boolean switchPresent, boolean stationPresent, boolean crossingPresent) {
        this.line = line;
        this.section = section;
        this.blockNumber = blockNumber;
        this.nextInboundBlock = nextInboundBlock;
        this.nextOutboundBlock = nextOutboundBlock;
        this.switchPresent = switchPresent;
        this.stationPresent = stationPresent;
        this.crossingPresent = crossingPresent;
        if(this.nextInboundBlock == nextOutboundBlock) blockDirection = 1;
        else blockDirection = 2;
    }
    
    //Jon Galaxy Dick  - CTC   
    public Block(String line, String section, int blockNumber, int blockLength, int speedLimit, String blockState) {
        this.line = line;
        this.section = section;
        this.blockNumber = blockNumber;
        this.blockLength = blockLength;
        this.speedLimit = speedLimit;
        this.blockState = blockState;
    }
   
    //Track Controller 
    //Check if two blocks are the same to deterimine if it is the last block in the trains route 
    public boolean isBlockEqual(String line, int  block){
        return (this.line.equals(line))  && (this.blockNumber == block);
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

    public double getBlockLength() {
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

    public boolean getSwitchState() {
        return switchState;
    }

    public void setSwitchState(boolean switchState) {
        this.switchState = switchState;
    }

    //Beacon can only send 64 bits, so if a beacon is set to be longer than
    //8 acii characters, it will be trimmed to the appropriate length
    public String getBeacon() {
        return beacon.substring(0, Math.min(beacon.length(), 8));
    }

    public void setBeacon(String beacon) {
        String trimmedBeacon = beacon.substring(0, Math.min(beacon.length(), 8));
        this.beacon = trimmedBeacon;    }

    public int getNextInboundBlock() {
        return nextInboundBlock;
    }

    public void setNextInboundBlock(int nextInboundBlock) {
        this.nextInboundBlock = nextInboundBlock;
    }

    public int getNextOutboundBlock() {
        return nextOutboundBlock;
    }

    public void setNextOutboundBlock(int nextOutboundBlock) {
        this.nextOutboundBlock = nextOutboundBlock;
    }

    public boolean isSwitchPresent() {
        return switchPresent;
    }

    public void setSwitchPresent(boolean switchPresent) {
        this.switchPresent = switchPresent;
    }

    public int getSwitchDefault() {
        return switchDefault;
    }

    public void setSwitchDefault(int switchDefault) {
        this.switchDefault = switchDefault;
    }

    public boolean isBlockOccupancy() {
        return blockOccupancy;
    }

    public void setBlockOccupancy(boolean blockOccupancy) {
        this.blockOccupancy = blockOccupancy;
    }

    public boolean isStationPresent() {
        return stationPresent;
    }

    public void setStationPresent(boolean stationPresent) {
        this.stationPresent = stationPresent;
    }

    public boolean isCrossingPresent() {
        return crossingPresent;
    }

    public void setCrossingPresent(boolean crossingPresent) {
        this.crossingPresent = crossingPresent;
    }

    public boolean isCrossingState() {
        return crossingState;
    }

    public void setCrossingState(boolean crossingState) {
        this.crossingState = crossingState;
    }

    public boolean isCircuitState() {
        return circuitState;
    }

    public void setCircuitState(boolean circuitState) {
        this.circuitState = circuitState;
    }

    public String getSignal() {
        return signal;
    }

    public void setSignal(String signal) {
        this.signal = signal;
    }

    public int getPassengersStation() {
        return passengersStation;
    }

    public void setPassengersStation(int passengersStation) {
        this.passengersStation = passengersStation;
    }

    public int getPassengersBoard() {
        return passengersBoard;
    }

    public void setPassengersBoard(int passengersBoard) {
        this.passengersBoard = passengersBoard;
    }

    public String getBlockHeat() {
        return blockHeat;
    }

    public void setBlockHeat(String blockHeat) {
        this.blockHeat = blockHeat;
    }

    public String isOccupancy() {
        return occupancy;
    }

    public void setOccupancy(String occupancy) {
        this.occupancy = occupancy;
    }

    public int getBlockDirection() {
        return blockDirection;
    }

    public void setBlockDirection(int blockDirection) {
        this.blockDirection = blockDirection;
    }
   
    
    
}