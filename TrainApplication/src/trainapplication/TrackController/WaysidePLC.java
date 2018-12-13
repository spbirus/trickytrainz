/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackController;

/**
 *
 * @author jonat
 */
public class WaysidePLC {
    //boolean variables
    private boolean merge;
    private boolean split;
    private boolean switchState=true;
    private boolean crossingState;
    private boolean signalState;
    //Constructor
    WaysidePLC(boolean merge,boolean split){
        this.merge=merge;
        this.split=split;
    }
    //getters and setters
    public boolean isSwitchState() {
        return switchState;
    }

    public void setSwitchState(boolean switchState) {
        this.switchState = switchState;
    }

    public boolean isCrossingState() {
        return crossingState;
    }

    public void setCrossingState(boolean crossingState) {
        this.crossingState = crossingState;
    }

    public boolean isSignalState() {
        return signalState;
    }

    public void setSignalState(boolean signalState) {
        this.signalState = signalState;
    }
    //moves switch when train is approaching
     public boolean calculateSwitch(boolean mergeTrackCircuit,boolean splitTrackCircuit,boolean toYard){
         if(merge&&split){
             if(mergeTrackCircuit){
                 switchState= false;
             }else if (splitTrackCircuit){
                 if(toYard){
                     switchState=true;
                 }else{
                     switchState=false;
                 }
             }else{
                 switchState=true;
             }
         }else if(merge){
             if(mergeTrackCircuit){
                 switchState= false;
             }else{
                 switchState = true;
             }
         }else if(split){
             if(splitTrackCircuit){
                 if(toYard){
                     switchState=false;
                 }else{
                     switchState=true;
                 }
             }
         }
         return switchState;
     }
     //occupancy calculation dictates most of the logic in the signal calclation
     //Crossing and signal booleans are equal
    public boolean calculateSignalCrossing(boolean occupancy){
        return !occupancy;
    }
    
}
