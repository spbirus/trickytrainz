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
            
 }