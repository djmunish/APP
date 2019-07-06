import java.util.ArrayList;
import java.util.Random;

public class Player {
   
    enum playerType {
    	  HUMAN,
    	  COMPUTER,
    	}
    Random ran = new Random();
    
    public ArrayList<Ships> shipsArr;
    
    public playerType type;
    
	public Player() {
		shipsArr = new ArrayList<>();
	}
	
	
	public void setupShip(String start, String end) {
		Ships s = new Ships();
		s.setupShip(start, end);
		shipsArr.add(s);
	}
	
	public String randomblock() {
		int n = ran.nextInt(10);
		Character alpha = Constants.alphabets.charAt(ran.nextInt(Constants.alphabets.length()));
		String s = Character.toString(alpha) + n;
		return s;
	}
	
	public String randomhit() {
		boolean flag = true;
		char[] arr = Constants.alphabets.toCharArray();
		int row = 0;
		int col = 0;
		while(flag) {
			String s = randomblock();
			String[] sarr = s.split("");
			row = Integer.parseInt(sarr[1]);
			Character alph = sarr[0].charAt(0);
			for(int i=0; i<arr.length;i++) {
				if (Character.toString(arr[i]).equals(Character.toString(alph))) {
					col = i;
					break;
				}//if
			}//for	
		}
		return null;
		//return s;
	}
	
	
	
}
