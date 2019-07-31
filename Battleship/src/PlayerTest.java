import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

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
		assertEquals("A2",randomhitcompai(p,5,4));
	}
	
	
	/*  update the drop down once the coordinate is clicked or hit by the player
	 public void updateDropdown(String s, ArrayList<String> drop) { // Update human player input drop down
	        drop.remove(s);
	    }
	*/

}
