import java.util.ArrayList;

public class Player {
   
    enum playerType {
    	  HUMAN,
    	  COMPUTER,
    	}
    
    public ArrayList<Ships> shipsArr;
    ArrayList<String> inputs;
    public Board playingBrd, referenceBrd;
    
    
    public playerType type;
    
	public Player() {
		shipsArr = new ArrayList<>();
		inputs = new ArrayList<>();
		playingBrd = new Board();
		referenceBrd = new Board();
	}
	
	public void setupShip(String start, String end) {
		Ships s = new Ships();
		s.setupShip(start, end);
		shipsArr.add(s);
	}
	
	
	public void createInputs() {
		String[] col = {"A","B","C","D","E","F","G","H","I","J","K"};
		int[] rows = new int[Constants.row];
		for(int i = 1; i <= rows.length; i++) {
			rows[i-1] = i;
		}
		for(int i = 0; i < col.length; i++) {//11
			for(int j = 0; j < rows.length; j++) {//10
				inputs.add(col[i] + rows[j]);
			}
		}
	}

	
}
