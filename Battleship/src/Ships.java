import javafx.scene.control.Button;

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
	
	public boolean checkhit(String checkcordinate, Player p) {
		boolean flag = false);
		for(Ships p : p.shipsArr){
			ArrayList<String> got = p.coordinates;
			for(int i= 0; i< got.size();i++){
				flag = got.get(i).contains(checkcordinate);
			}
		}
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

}
