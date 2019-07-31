
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * @author kajal
 *
 */
class ShipsTest {

	Player p1 = new Player();
	Ships s = new Ships("A1", "A5");
	char[] arr = {'a','b','c'};
	ArrayList<String> hits;
	
	//method to check if it is a hit or not
	//if it is true
	@Test public void checkForHit()
	{
		p1.shipsArr.add(s);
		for (Ships s : p1.shipsArr) {
		}
		
		assertEquals(true, Ships.checkhit("A2", p1));
	}
	
	//if it is false
	@Test public void checkForHit2()
	{
		p1.shipsArr.add(s);
		for (Ships s : p1.shipsArr) {
		}
		
		assertEquals(false, Ships.checkhit("A6", p1));
	}
	
	
	//function for computer to check if ship is positioned or not 
	// if it is true
	@Test public void checkForshipPosition() { 
		p1.shipsArr.add(s);
		for (Ships s : p1.shipsArr) {
            }		
		assertEquals(true,Ships.checkifship("A1", p1));
	}
	
	// if it is false
	@Test public void checkForshipPosition2() { 
		p1.shipsArr.add(s);
		for (Ships s : p1.shipsArr) {
            }		
		assertEquals(false,Ships.checkifship("A6", p1));
	}
	
	
	//test for salva hits
		@Test public void checkForSalvaHits()
		{
			hits.add("A1");
			assertEquals("",Ships.checkHitSalva(hits, p1));
		}
	
	//find the location of the ship
//	  public void LocationFinder() {
//		  
//		assertNotNull(Ships.setupShip("A2","A6"));   
//	    }
	
	
	
	
}
