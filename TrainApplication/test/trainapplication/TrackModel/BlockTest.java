/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackModel;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michael
 */
public class BlockTest {
    public BlockTest() {
    }
    

    /**
     * Test of isStation method, of class Block.
     */
    @Test
    public void testIsStation() {
        System.out.println("isStation");
        Block instance = new Block("Green","A",2,328.1,3.281,180.455,"STATION; PIONEER",1,1,3.281,4.9215);
        boolean expResult = true;
        boolean result = instance.isStation();
        assertEquals(expResult, result);
    }
    
    @Test 
    public void testCalculatePassengers() {
        System.out.println("calculatePassengers");
        Block b = new Block();
        int result = b.calculatePassengers();
        System.out.println("Result: " + result);
        assertTrue(result > 1);
    }
    
    //@Test
    public void testGetPassengersBoard() {
        System.out.println("getPassengersBoard");
        Block instance = new Block("Green","A",2,328.1,3.281,180.455,"STATION; PIONEER",1,1,3.281,4.9215);
        boolean expResult = true;
        int num = instance.getPassengersBoard();
        System.out.println("Result: " + num);
        boolean result = (num < 100 && num > 0);
        assertEquals(expResult, result);
    }

   
}
