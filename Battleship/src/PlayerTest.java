import static org.junit.jupiter.api.Assertions.*;


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

    @Test public void aiValidation() {                       //failing
        assertNotNull(randomhitcompai(p,5,4));
    }

    @Test public void aiValidation2() {                       //failing

        assertNotNull(randomhitcompai(p,1,1));
    }

    @Test public void aiValidation3() {                       //failing

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
}