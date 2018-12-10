/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackModel;

import java.util.ArrayList;

/**
 *
 * @author Michael
 */
public class Track {
    
    private String line;
    private int[] switches;
    private int[] stations;
    private Block occupiedBlock;
    private Block previousBlock;
    private int[] sections;
    private boolean[] sectionOccupancy;

    public boolean[] getSectionOccupancy() {
        return sectionOccupancy;
    }

    public void setSectionOccupancy(boolean[] sectionOccupancy) {
        this.sectionOccupancy = sectionOccupancy;
    }
            
            

    public int[] getSections() {
        return sections;
    }

    public void setSections(int[] sections) {
        this.sections = sections;
    }

    public int[] getSwitches() {
        return switches;
    }

    public void setSwitches(int[] switches) {
        this.switches = switches;
    }

    public Block getOccupiedBlock() {
        return occupiedBlock;
    }

    public void setOccupiedBlock(Block occupiedBlock) {
        this.occupiedBlock = occupiedBlock;
    }

    public Block getPreviousBlock() {
        return previousBlock;
    }

    public void setPreviousBlock(Block previousBlock) {
        this.previousBlock = previousBlock;
    }
    public ArrayList<Block> blockList = new ArrayList<Block>();
    
    
    public Track(String line){
       this.line = line;
       int[] greenSwitches = {62, 76, 100, 29, 1, 57};
       int[] greenSections = {58, 63, 77, 101, 28, 29};
       boolean[] greenSectionOccupancies = {false, false, false, false, false, false};
       if(line.equals("Green")){
           setSwitches(greenSwitches);
           setSections(greenSections);
           setSectionOccupancy(greenSectionOccupancies);
       }

           
    }
    
    public void setSwitchState(int block){
        
    }
    
    public Block getTrackBlock(int blkNumber){
        for(Block blk : blockList){
         if(blk.getBlockNumber() == blkNumber){
             //System.out.println("Returned Block " + blk.getBlockNumber());
             //System.out.println("Requested Block " + blkNumber);
             return blk;
         }               
        }
        return null;
    }
    
    public ArrayList<Block> getBlockNeighbors(Block blk){
        
        ArrayList<Block> blockNeighbors = new ArrayList<Block>();
        try{
            blockNeighbors.add(blk.getNextBlock());
            blockNeighbors.add(blk.getPreviousBlock());
            blockNeighbors.add(blk.getSwitchBlock());
        } catch(NullPointerException e) {
           System.out.println("Not all the blocks are there");
        }
        
        return blockNeighbors;
    }       
 }