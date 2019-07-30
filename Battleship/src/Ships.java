import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.awt.Color;
import java.util.*;

/**
 * Ships.java deals with ship placement, ship hit and colouring of the ships.
 * @author harshkour
 * @since 2019-07-06
 * @version 1.0.1
 */
public class Ships {

    public ArrayList<String> coordinates;
    public Color shipColor;
    public String hexColor;

    public Ships(String start, String end) {
        colorShip();
        setupShip(start, end);
    }
    
    
    public int findloc(char a) { //Function to find location in Play area grid
        int loc = 0;
        char[] arr = Constants.alphabets.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (Character.toString(arr[i]).equals(Character.toString(a))) {
                loc = i;
                break;
            }//if
        }//for
        return loc;
    }
    /**
     * setupShip Function to setup a ship for Human Player.
     * @param start is the starting location of the ship sent from UI.
     * @param end is the ending location of the ship sent from UI.
     */
    public void setupShip(String start, String end) { 
        coordinates = new ArrayList<>();
        String xy10 = start.substring(0, 1);
        String xy11 = start.substring(1);
        String xy20 = end.substring(0, 1);
        String xy21 = end.substring(1);
        int var = xy10.compareTo(xy20);

        char[] arr = Constants.alphabets.toCharArray();
        if (var == 0) {

            if (Integer.parseInt(xy11) < Integer.parseInt(xy21)) {
                for (int i = Integer.parseInt(xy11); i <= Integer.parseInt(xy21); i++) {
                    coordinates.add(xy10 + i);
                }//for
            } else {
                for (int i = Integer.parseInt(xy11); i >= Integer.parseInt(xy21); i--) {
                    String s = xy10 + i;
                    coordinates.add(s);
                }//for
            }
        }//if equals
        else if (var < 0) {
            int loci = findloc(xy10.charAt(0));
            int locf = findloc(xy20.charAt(0));
            for (int i = loci; i <= locf; i++) {
                String s = Character.toString(arr[i]) + xy11;
                coordinates.add(s);
            }//for
        } else if (var > 0) {

            int loci = findloc(xy10.charAt(0));
            int locf = findloc(xy20.charAt(0));
            for (int i = loci; i >= locf; i--) {
                String s = Character.toString(arr[i]) + xy11;
                coordinates.add(s);
            }//for
        }//var>0

    }//setupship
    
    
    /**
     * Function to check if a ship gets hit or not.
     * @param checkcordinate is the location on which there is a hit done.
     * @param p is the player who's ship is to be checked.
     * @return true if there is a hit on the ship else return false.
     */
    public static boolean checkhit(String checkcordinate, Player p) { //t
        boolean flag = false;
        for (Ships s : p.shipsArr) {
            ArrayList<String> got = s.coordinates;
            for (int i = 0; i < got.size(); i++) {
                flag = got.get(i).contains(checkcordinate);
                if (flag) {
                    s.coordinates.remove(checkcordinate);
                    if (s.coordinates.size() == 0) {
                        p.shipsArr.remove(s);
                    }
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        return flag;
    }
    
    public static String checkHitSalva(ArrayList<String> hits, Player p) {
    	Iterator<String> it = hits.iterator();
    	String comphit = "";
    	String nohit = "";
    	while(it.hasNext()) {
    		String hit = it.next();
    		System.out.println("Hit is " + hit);
    		boolean flag = checkhit(hit, p);
    		if(flag) {
    			comphit = hit + ",";
    		}else {
    			nohit = hit + ",";
    		}
    	}//while-end
    	String finalhits = comphit + "!" + nohit;
    	return finalhits;
    }
    
    
    public static boolean checkifship(String checkcordinate, Player p) { //Function for computer to check if ship is positioned or not
        boolean flag = false;
        for (Ships s : p.shipsArr) {
            ArrayList<String> got = s.coordinates;
            if(got.contains(checkcordinate)){
                flag = true;
                break;
            }

        }
        return flag;
    }


    /**
     * Function to update color of the ship.
     */
    public void colorShip() { 
        // create random object - reuse this as often as possible
        Random random = new Random();
        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);
        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);
        if(!Constants.shipColors.contains(colorCode) || colorCode.equals("#ff0000")|| colorCode.equals("#d3d3d3")){
            Constants.shipColors.add(colorCode);
            hexColor = colorCode;
        }
        else{
        	colorShip();
        }
    }

    /**
     * Function to assign colours on the player grid: "RED"-if its a hit, "GREY"-if its a miss.
     * @param G1 Playing grid of the opposite player.
     * @param G2 Reference grid for the player who made the hit.
     * @param S location of the grid to be checked.
     * @param a object of class Arena.
     * @param playerRef object of the opposite player.
     * @return true if its hit else return false.
     */
    public static boolean colorButton(GridPane G1, GridPane G2, String S, Arena a, Player playerRef) {
        String s1 = S.substring(0, 1);
        String s2 = S.substring(1);
        int x = Constants.mapInConstants.get(s1);    //c
        int y = Integer.parseInt(s2);    //r
        System.out.println("Cordinates are: " + (x + 1) + " " + (y - 1));
        Button bActual = (Button) a.getNodeFromGridPane(G1, x + 1, y - 1);
        Button bReference = (Button) a.getNodeFromGridPane(G2, x + 1, y - 1);

        if (checkhit(S, playerRef)) {
            bActual.setStyle("-fx-background-color: Red");
            bReference.setStyle("-fx-background-color: Red");
            return true;
        } else {
            bActual.setStyle("-fx-background-color: Grey;");
            bReference.setStyle("-fx-background-color: Grey");
            return false;
        }
    }


}
