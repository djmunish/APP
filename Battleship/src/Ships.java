import java.util.ArrayList;

public class Ships {
	
	public ArrayList<String> coordinates;
	
	
	public Ships() {
		coordinates = new ArrayList<>();
	}
	
	public void setupShip(String start, String end) {
		String[] xy1 = start.split("");
		String[] xy2 = end.split("");
		
		if(xy1[0].equals(xy2[0])) {
			
			for(int i = Integer.parseInt(xy1[1]) ; i< Integer.parseInt(xy2[1]); i++) {
				coordinates.add(xy1[0] + i);
			}
		}
		else{
			for(int i = Integer.parseInt(xy1[1]) ; i< Integer.parseInt(xy2[1]); i++) {
				coordinates.add(xy1[0] + i);
			}
		}
		
	}
	
	
	

	
	
	
	
}
