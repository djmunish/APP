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

		char[] arr = Constants.alphabets.toCharArray();
		if(var == 0) {
		//if(xy1[0].equals(xy2[0])) {
			
			if(Integer.parseInt(xy1[1]) < Integer.parseInt(xy2[1])) {
				for(int i = Integer.parseInt(xy1[1]) ; i<= Integer.parseInt(xy2[1]); i++) {
					coordinates.add(xy1[0] + i);
				}//for
			}else {
				for(int i = Integer.parseInt(xy2[1]) ; i>= Integer.parseInt(xy2[1]); i--) {
					coordinates.add(xy1[0] + i);
				}//for
			}	
		}//if equals
		else if(var < 0){
			int loci = findloc(xy1[0].charAt(0));
			int locf = findloc(xy2[0].charAt(0));
			for(int  i = loci ; i<= locf ; i++) {
				String s = Character.toString(arr[i]) + xy1[1];
				coordinates.add(s);
			}//for
		}else if(var > 0) {
			
			int loci = findloc(xy1[0].charAt(0));
			int locf = findloc(xy2[0].charAt(0));
			for(int  i = locf ; i>= loci ; i--) {
				String s = Character.toString(arr[i]) + xy1[1];
				coordinates.add(s);
			}//for		
		}//var>0
		
	}//setupship
	
	public boolean checkhit(String checkcordinate) {
		boolean flag = coordinates.contains(checkcordinate);
		return flag;
	}
	
	
	public void colorShip() {
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		shipColor = new Color(r, g, b);
		hexColor = String.format("#%02x%02x%02x", r, g, b);
	}

}
