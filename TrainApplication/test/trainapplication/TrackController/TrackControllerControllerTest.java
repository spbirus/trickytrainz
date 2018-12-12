/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainapplication.TrackController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import trainapplication.TrainApplication;

/**
 *
 * @author JD2298
 */
public class TrackControllerControllerTest {
    
    public TrackControllerControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setTrainApp method, of class TrackControllerController.
     */
    @Test
    public void testSetTrainApp() throws Exception {
        System.out.println("setTrainApp");
        TrainApplication ta = null;
        String plcFile = "";
        int titleNum = 0;
        TrackControllerController instance = new TrackControllerController();
        instance.setTrainApp(ta, plcFile, titleNum);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readPLC method, of class TrackControllerController.
     */
    @Test
    public void testReadPLC() throws Exception {
        System.out.println("readPLC");
        String plcFile = "";
        TrackControllerController instance = new TrackControllerController();
        instance.readPLC(plcFile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class TrackControllerController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        TrackControllerController instance = new TrackControllerController();
        instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class TrackControllerController.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        TrackControllerController instance = new TrackControllerController();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of onPLCClick method, of class TrackControllerController.
     */
    @Test
    public void testOnPLCClick() throws Exception {
        System.out.println("onPLCClick");
        ActionEvent event = null;
        TrackControllerController instance = new TrackControllerController();
        instance.onPLCClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setManualMode method, of class TrackControllerController.
     */
    @Test
    public void testSetManualMode() {
        System.out.println("setManualMode");
        ActionEvent event = null;
        TrackControllerController instance = new TrackControllerController();
        instance.setManualMode(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of manDefaultSwitchClick method, of class TrackControllerController.
     */
    @Test
    public void testManDefaultSwitchClick() {
        System.out.println("manDefaultSwitchClick");
        ActionEvent event = null;
        TrackControllerController instance = new TrackControllerController();
        instance.manDefaultSwitchClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of manNotDefaultSwitchClick method, of class TrackControllerController.
     */
    @Test
    public void testManNotDefaultSwitchClick() {
        System.out.println("manNotDefaultSwitchClick");
        ActionEvent event = null;
        TrackControllerController instance = new TrackControllerController();
        instance.manNotDefaultSwitchClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of manRaiseCrossingClick method, of class TrackControllerController.
     */
    @Test
    public void testManRaiseCrossingClick() {
        System.out.println("manRaiseCrossingClick");
        ActionEvent event = null;
        TrackControllerController instance = new TrackControllerController();
        instance.manRaiseCrossingClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of manLowerCrossingClick method, of class TrackControllerController.
     */
    @Test
    public void testManLowerCrossingClick() {
        System.out.println("manLowerCrossingClick");
        ActionEvent event = null;
        TrackControllerController instance = new TrackControllerController();
        instance.manLowerCrossingClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of manGreenSignalClick method, of class TrackControllerController.
     */
    @Test
    public void testManGreenSignalClick() {
        System.out.println("manGreenSignalClick");
        ActionEvent event = null;
        TrackControllerController instance = new TrackControllerController();
        instance.manGreenSignalClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of manRedSignalClick method, of class TrackControllerController.
     */
    @Test
    public void testManRedSignalClick() {
        System.out.println("manRedSignalClick");
        ActionEvent event = null;
        TrackControllerController instance = new TrackControllerController();
        instance.manRedSignalClick(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSwitch method, of class TrackControllerController.
     */
    @Test
    public void testCalculateSwitch() throws Exception {
        System.out.println("calculateSwitch");
        TrackControllerController instance = new TrackControllerController();
        boolean expResult = false;
        boolean result = instance.calculateSwitch();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSignal method, of class TrackControllerController.
     */
    @Test
    public void testCalculateSignal() throws Exception {
        System.out.println("calculateSignal");
        boolean sectionOccupancy = false;
        TrackControllerController instance = new TrackControllerController();
        boolean expResult = false;
        boolean result = instance.calculateSignal(sectionOccupancy);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpeedAuthority method, of class TrackControllerController.
     */
    @Test
    public void testSetSpeedAuthority() {
        System.out.println("setSpeedAuthority");
        int id = 0;
        double speed = 0.0;
        int authority = 0;
        TrackControllerController instance = new TrackControllerController();
        instance.setSpeedAuthority(id, speed, authority);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
