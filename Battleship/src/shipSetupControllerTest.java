import static org.junit.Assert.assertTrue;
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

	/*@Test public void nodeFromGrid()
	{
		assertNotNull(test.getNodeFromGridPane(null, 0, 0));
	}
	*/
	
	
	/*
	
	// checks if ready button is enabled on placing all the 5 ships
	@Test public void ReadyButtonEnabled() {
		assertEquals(true, checkForReady());	
	}
	
	public boolean checkForReady() {	
       if(!(p1.shipsArr.size()==5 && p2.shipsArr.size()==5))
       {
    	   btnok.isDisabled();
    	   return true;
       }
        return false;
	}
	
	//for disabling of grid pane 
	
	@Test public void GridEnabled() {
		
		assertEquals(true,isGridEnabled());	
	}

	public boolean isGridEnabled() {
		boolean flag = rb.isSelected();
		if(flag)
		{
			gridPane.setDisable(false);
			return true;
		}
		return false;
	}
	
	*/
	
}
