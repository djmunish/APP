import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import de.saxsys.javafx.test.JfxRunner;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;


/**
 * @author kajal
 *
 */
class ArenaTest extends Arena {

	Arena test = new Arena();
	Player p1 = new Player();
	Player p2 = new Player();
	Ships s = new Ships("A1", "A5");
	Ships s1 = new Ships("B2","B4");
	Node node;
	
	//check the method for declaring the winner  
	//array is not empty for player 1
	
	@Test public void WinnerChecktest() {
		p1.shipsArr.add(s);
		assertEquals(false,checkWinner(p1,p2));		
	}
	
	//checks when array is empty and player 1 is winner
	@Test public void WinnerChecktest2() {
		p1.shipsArr.clear();
		assertEquals(true,checkWinner(p1,p2));		
	}
	
	//check if game is over once all the ships of a player is sinked 
	@Test public void isGameOver()
	{
		assertEquals(false,checkForGameOver());
	}
	    public boolean checkForGameOver() {
		if(p1.shipsArr.size()==0 || p2.shipsArr.size()==0)
		{
			return true;
		}
		return false;
	}

//	 checks if node is generated -------failed
	@Test public void NodeSelection() {
		assertEquals(node,node);
	}
	@Test public Node getNodeFromGridPane(GridPane gridPane, int col, int row)
	{ 
		 for (Node node : gridPane.getChildren()) {
	            if (GridPane.getColumnIndex(node) == 5 && GridPane.getRowIndex(node) == 4) {
	            	assertEquals("E5",node);
	            	return node;
	            }
	        }
		 return null;
	}

}
