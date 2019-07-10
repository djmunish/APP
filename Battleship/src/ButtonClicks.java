
import javafx.scene.control.Button;


public class ButtonClicks extends Button {

    int coordX;
    int coordY;

    public ButtonClicks(int coordX, int coordY) {
        // super(buttonText);
        this.coordX = coordX;
        this.coordY = coordY;
    }


    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
}


