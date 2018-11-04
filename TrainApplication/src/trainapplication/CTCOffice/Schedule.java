/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.CTCOffice;

/**
 *
 * @author jtgal
 */
public class Schedule {
    
    String line;
    int trainID;
    int[] targetBlock;
    long dispatchTime; //not sure what type of variable, but this will be used for knowing when a train is supposed to arrive at the next block
    double[] timeToNextBlock; //array to hold all of timing information for one train
    int scheduleIndex;

    public Schedule() {
        
    }

    public Schedule(String line, int trainID, int[] targetBlock, long dispatchTime, double[] timeToNextBlock, int scheduleIndex) {
        this.line = line;
        this.trainID = trainID;
        this.targetBlock = targetBlock;
        this.dispatchTime = dispatchTime;
        this.timeToNextBlock = timeToNextBlock;
        this.scheduleIndex = scheduleIndex;
    }
    
    

    public int getTrainID() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public int[] getTargetBlock() {
        return targetBlock;
    }

    public void setTargetBlock(int[] targetBlock) {
        this.targetBlock = targetBlock;
    }

    public long getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(long dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public double[] getTimeToNextBlock() {
        return timeToNextBlock;
    }

    public void setTimeToNextBlock(double[] timeToNextBlock) {
        this.timeToNextBlock = timeToNextBlock;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getScheduleIndex() {
        return scheduleIndex;
    }

    public void setScheduleIndex(int scheduleIndex) {
        this.scheduleIndex = scheduleIndex;
    }
    
}
