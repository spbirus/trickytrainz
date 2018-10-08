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
    
    int trainID;
    int[] currentBlock;
    int[] targetBlock;
    long dispatchTime; //not sure what type of variable, but this will be used for knowing when a train is supposed to arrive at the next block
    double[] timeToNextBlock; //array to hold all of timing information for one train

    public Schedule() {
        
    }

    public Schedule(int trainID, int[] currentBlock, int[] targetBlock, int dispatchTime, double[] timeToNextBlock) {
        this.trainID = trainID;
        this.currentBlock = currentBlock;
        this.targetBlock = targetBlock;
        this.dispatchTime = dispatchTime;
        this.timeToNextBlock = timeToNextBlock;
    }
    
    

    public int getTrainID() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public int[] getCurrentBlock() {
        return currentBlock;
    }

    public void setCurrentBlock(int[] currentBlock) {
        this.currentBlock = currentBlock;
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
    
    
    
}
