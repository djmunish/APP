import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.*;
/**
 *
 */

/**
 * @author kajal
 *
 */
class PlayerTest extends Player{

    Player test = new Player();

    Player p = new Player();
    Ships s = new Ships("A1", "A5");
    Ships d = new Ships("A2","A5");
    Ships f = new Ships("A5","A9");
    ArrayList<String> ship = new ArrayList<>();
//    ArrayList<String>  = new ArrayList<>();

//    @Before public void clearTT() {
//        System.out.println("here");
//    }

    // checks if ships are overlapping
    @Test public void testOverLapping() {
        p.shipsArr.add(s);
        p.shipsArr.add(d);
        for (Ships s : p.shipsArr) {
        }
        assertEquals(true, checkOverlap(computerships));
    }
    // checks if ships are overlapping
    @Test public void testOverLapping1() {
        p.shipsArr.add(s);
        p.shipsArr.add(f);
        for (Ships s : p.shipsArr) {
        }
        assertEquals(true, checkOverlap(computerships));
    }

    //checks if method is returning the location of ships of the player
    @Test public void inputLocationsOfPlayer()
    {
        assertNotNull(createInputs());
    }

    //checks is random block of computer is generated
    @Test public void computerShipGeneration()
    {
        assertNotNull(randomblock());
    }

    @Test public void aiValidation() {                       
        assertNotNull(randomhitcompai(p,5,4));
    }

    @Test public void aiValidation2() {                       

        assertNotNull(randomhitcompai(p,1,1));
    }

    @Test public void aiValidation3() {                       

        assertNotNull(randomhitcompai(p,3,1));
    }

    @Test public void testComputerRandomShip(){

        Player computer = new Player();
        computer.computerRandomShip();

    }
    @Test public void testComputerRandomShip1(){

        Player computer1 = new Player();
        computer1.computerRandomShip();

    }
    
    @Test public void checkNeighborsForComputer()
    {
    	p.shipsArr.add(s);
    	String expected = "A2 A4 ";
    	assertEquals(expected,checkneighbors("A3",p));
    }
    
    @Test public void checkNeighborsForComputer2()
    {
    	 p.shipsArr.add(f);
    	String expected = "A5 A7 ";
    	assertEquals(expected,checkneighbors("A6",p));
    }
    
    @Test public void checkForShipAvailable()
    {
    	ship.add("A6");
    	ship.add("B8");
    	ship.add("C3");
    	computerships.add("A6");
    	assertTrue(shipAvailable(ship));
    }
    @Test public void checkForShipAvailable2()
    {
    	ship.add("F5");
    	ship.add("A6");
    	ship.add("A7");
    	computerships.add("F5");
    	assertTrue(shipAvailable(ship));
    }
    
    
}