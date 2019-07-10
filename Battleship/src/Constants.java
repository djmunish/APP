import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.HashMap;

public final class Constants {

    private Constants() {
        // restrict instantiation
    }

    public static final int row = 10;
    public static final int col = 11;


    public static final String CARRIER = "Carrier(5)";
    public static final String BATTLESHIP = "Battleship(4)";
    public static final String CRUISER = "Cruiser(3)";
    public static final String SUBMARINE = "Submarine(3)";
    public static final String DESTROYER = "Destroyer(2)";

    public static final String alphabets = "ABCDEFGHIJK";

    public static HashMap<String, Integer> mapInConstants;

    static {
        mapInConstants = new HashMap<>();

        mapInConstants.put("A", 0);
        mapInConstants.put("B", 1);
        mapInConstants.put("C", 2);
        mapInConstants.put("D", 3);
        mapInConstants.put("E", 4);
        mapInConstants.put("F", 5);
        mapInConstants.put("G", 6);
        mapInConstants.put("H", 7);
        mapInConstants.put("I", 8);
        mapInConstants.put("J", 9);
        mapInConstants.put("K", 10);

    }


    public static HashMap<String, String> indexToAlpha;

    static {
        indexToAlpha = new HashMap<>();

        indexToAlpha.put("1", "A");
        indexToAlpha.put("2", "B");
        indexToAlpha.put("3", "C");
        indexToAlpha.put("4", "D");
        indexToAlpha.put("5", "E");
        indexToAlpha.put("6", "F");
        indexToAlpha.put("7", "G");
        indexToAlpha.put("8", "H");
        indexToAlpha.put("9", "I");
        indexToAlpha.put("10", "J");
        indexToAlpha.put("11", "K");

    }


    public static void showAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ALERT");
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }

    public static ArrayList<String> shipColors = new ArrayList<>();

}