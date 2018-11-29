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
    public ArrayList<Block> blockList = new ArrayList<Block>();
    
    public Track(String line){
       this.line = line;
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