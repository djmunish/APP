import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Ships {
	
	public ArrayList<String> coordinates;
	public Color shipColor;
	public String hexColor;
	
	public Ships(String start, String end) {
		colorShip();
		setupShip(start,end);
	}
	
	public int findloc(char a) {
		int loc = 0;
		char[] arr = Constants.alphabets.toCharArray();
		for(int i=0; i<arr.length;i++) {
			if (Character.toString(arr[i]).equals(Character.toString(a))) {
				loc = i;
				break;
			}//if
		}//for	
		return loc;
	}

	public void setupShip(String start, String end) {
		coordinates = new ArrayList<>();

		String[] xy1 = start.split("");
		String[] xy2 = end.split("");

		int var = xy1[0].compareTo(xy2[0]);
		//System.out.println("var is" + var);

		char[] arr = Constants.alphabets.toCharArray();
		if(var == 0) {
		//if(xy1[0].equals(xy2[0])) {
			
			if(Integer.parseInt(xy1[1]) < Integer.parseInt(xy2[1])) {
				for(int i = Integer.parseInt(xy1[1]) ; i<= Integer.parseInt(xy2[1]); i++) {
					coordinates.add(xy1[0] + i);
				}//for
			}else {
				//System.out.println("added are :");
				for(int i = Integer.parseInt(xy1[1]) ; i>= Integer.parseInt(xy2[1]); i--) {
					//System.out.println("i =" + i);
					String s = xy1[0] + i;
					//System.out.println(s + " ");
					coordinates.add(s);
				}//for
			}	
		}//if equals
		else if(var < 0){
			int loci = findloc(xy1[0].charAt(0));
			int locf = findloc(xy2[0].charAt(0));
			//System.out.println("added are :");
			for(int  i = loci ; i<= locf ; i++) {
				String s = Character.toString(arr[i]) + xy1[1];
				//System.out.println(s + " ");
				coordinates.add(s);
			}//for
		}else if(var > 0) {
			
			int loci = findloc(xy1[0].charAt(0));
			int locf = findloc(xy2[0].charAt(0));
			//System.out.println("added are :");
			for(int  i = loci ; i>= locf ; i--) {
				String s = Character.toString(arr[i]) + xy1[1];
				//System.out.println(s + " ");
				coordinates.add(s);
			}//for		
		}//var>0
		
	}//setupship
	
	public static boolean checkhit(String checkcordinate, Player p) {
		boolean flag = false;
		for(Ships s : p.shipsArr){
			ArrayList<String> got = s.coordinates;
			for(int i= 0; i< got.size();i++){
				flag = got.get(i).contains(checkcordinate);
				System.out.print("flag is : " + flag);
				if(flag) {
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		System.out.print("checkhit is : " + flag);
		return flag;
	}
	
	
//	public void colorShip() {
//		Random rand = new Random();
//		float r = rand.nextFloat();
//		float g = rand.nextFloat();
//		float b = rand.nextFloat();
//		shipColor = new Color(r, g, b);
////		hexColor = String.format("#%02x%02x%02x", r, g, b);
//	}
	
	
	public void colorShip() {

        // create random object - reuse this as often as possible
        Random random = new Random();

        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(0xffffff + 1);

        // format it as hexadecimal string (with hashtag and leading zeros)
        String colorCode = String.format("#%06x", nextInt);

        // print it
        System.out.println(colorCode);
        hexColor = colorCode;
    }


    public static boolean colorButton(GridPane G1, GridPane G2, String S, Arena a, Player playerRef){

		//String s[] = S.split("");
		String s1 = S.substring(0, 1);
		String s2 = S.substring(1);
		//System.out.println("Split new is: " + s1 +" "+ s2);
		
		//System.out.println("Split is: " + s[0] +" "+ s[1]);
	//	int x = Constants.mapInConstants.get(s[0]);	//c
		int x = Constants.mapInConstants.get(s1);	//c
		//int y = Integer.parseInt(s[1]);	//r
		int y = Integer.parseInt(s2);	//r
		System.out.println("Cordinates are: " + (x+1) +" "+ (y-1));
		Button bActual = (Button) a.getNodeFromGridPane(G1,x+1,y-1);
		Button bReference = (Button) a.getNodeFromGridPane(G2,x+1,y-1);

		if(checkhit(S,playerRef)){
			bActual.setStyle( "-fx-background-color: Red");
			bReference.setStyle( "-fx-background-color: Red");
			return true;
		}
		else{
			bActual.setStyle( "-fx-background-color: Grey;");
			bReference.setStyle( "-fx-background-color: Grey");
			return false;
		}
	}








}
