import java.util.ArrayList;

public class Ships {
	
	public ArrayList<String> coordinates;
	
	
	public Ships() {
		coordinates = new ArrayList<>();
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
		else if(var < 0 && (xy1[1] == xy2[1])){
			int loci = findloc(xy1[0].charAt(0));
			int locf = findloc(xy2[0].charAt(0));
			for(int  i = loci ; i<= locf ; i++) {
			//for(char  alpha = xy1[0].charAt(0) ; alpha <= xy2[0].charAt(0) ; alpha++) {
				String s = Character.toString(arr[i]) + xy1[1];
				coordinates.add(s);
			}//for
		}else if(var > 0 && (xy1[1] == xy2[1])) {
			int loci = findloc(xy1[0].charAt(0));
			int locf = findloc(xy2[0].charAt(0));
			for(int  i = locf ; i>= loci ; i--) {
			//for(char  alpha = xy2[0].charAt(0) ; alpha >= xy1[0].charAt(0) ; alpha--) {
				String s = Character.toString(arr[i]) + xy1[1];
				coordinates.add(s);
			}//for		
		}//var>0
		
	}//setupship
	
	public boolean checkhit(String checkcordinate) {
		boolean flag = coordinates.contains(checkcordinate);
		return flag;
	}
	

}
