import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Ships {
	
	public ArrayList<String> coordinates;
	public Color shipColor;
	
	public Ships() {
		coordinates = new ArrayList<>();
		colorShip();
	}
	
	public void setupShip(String start, String end) {
		String[] xy1 = start.split("");
		String[] xy2 = end.split("");
		int var = xy1[0].compareTo(xy2[0]);
		
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
		else if(var < 0 && (xy1[1] == xy2[1])){
			for(char  alpha = xy1[0].charAt(0) ; alpha <= xy2[0].charAt(0) ; alpha++) {
				String s = Character.toString(alpha) + xy1[1];
				coordinates.add(s);
			}//for
		}else if(var > 0 && (xy1[1] == xy2[1])) {
			for(char  alpha = xy2[0].charAt(0) ; alpha >= xy1[0].charAt(0) ; alpha--) {
				String s = Character.toString(alpha) + xy1[1];
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
	}

}
