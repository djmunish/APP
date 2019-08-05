import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

/**
 * @author kajal
 *
 */
class shipSetupControllerTest {

    shipSetupController test = new shipSetupController();
    Player p1 = new Player();
    Player p2 = new Player();
    Button btnok;
    RadioButton rb;
    GridPane gridPane;

    //check for availability
    @Test public void checkForAvailability()
    {

        assertEquals(false,test.checkAvailability(3,7,5,true));
    }

    //check for availability
    @Test public void checkForAvailability1()
    {

        assertEquals(true,test.checkAvailability(3,1,5,true));
    }

    @Test public void checkForAvailability2()
    {

        assertNotEquals(false,test.checkAvailability(4,5,6,true));
    }

    @Test public void checkForAvailability3()
    {

        assertNotEquals(false,test.checkAvailability(6,5,2,false));
    }
    
    @Test public void checkNeighborsForComputer()
    {
  //  	p.shipsArr.add(s);
    	String expected = "A2 A4 ";
 //   	assertEquals(expected,checkneighbors("A3",p));
    }
    
    @Test public void checkNeighborsForComputer2()
    {
   // 	 p.shipsArr.add(f);
    	String expected = "A5 A7 ";
   // 	assertEquals(expected,checkneighbors("A6",p));
    }

}