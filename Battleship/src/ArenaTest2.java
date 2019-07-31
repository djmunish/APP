import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * @author kajal
 *
 */
class ArenaTest2 extends Arena{
	Arena test = new Arena();
	Player p1 = new Player();
	Player p2 = new Player();
	Ships s = new Ships("A1","A5");

	@Test
	public void winnerCheckTest() {	
		p1.shipsArr.add(s);
		assertFalse(test.checkWinner(p1, p2));
	}
	
	@Test public void winnerChecktest2() {
		p1.shipsArr.clear();
		assertTrue(test.checkWinner(p1,p2));		
	}
	
}
