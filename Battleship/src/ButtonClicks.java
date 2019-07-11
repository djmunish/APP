
import javafx.scene.control.Button;


public class ButtonClicks extends Button {
	//Class to find coordinates of the clicked Button

    int coordX;
    int coordY;

    public ButtonClicks(int coordX, int coordY) {
    	//function to set x and y coordinates of the button
    	
        // super(buttonText);
        this.coordX = coordX;
        this.coordY = coordY;
    }


    public int getCoordX() {
    	//function to get the x coordinates.
        return coordX;
    }

    public int getCoordY() {
    	//function to get the y coordinates.
        return coordY;
    }
}


