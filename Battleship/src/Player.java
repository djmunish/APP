import java.util.ArrayList;

public class Player {
   
    enum playerType {
    	  HUMAN,
    	  COMPUTER,
    	}
    
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
}
