import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import de.saxsys.javafx.test.JfxRunner;
import javafx.scene.control.Button;

/**
 * @author kajal
 *
 */

public class ButtonClicksTest {

	ButtonClicks coor = new ButtonClicks(4, 5);
	
	@Test
	public void CheckForXCoordinates() {
		assertEquals(4 ,coor.getCoordX());
		
	}
	
	@Test
	public void CheckForXCoordinates2() {
		assertNotEquals(3 ,coor.getCoordX());
	}
	
	@Test
    public void CheckForYCoordinates() {
		
		assertEquals(5,coor.getCoordY());
	}

	@Test
    public void CheckForYCoordinates2() {
		
		assertNotEquals(6,coor.getCoordY());
	}

}
