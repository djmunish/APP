import java.util.ArrayList;
import java.util.Random;

public class Player {
   
    enum playerType {
    	  HUMAN,
    	  COMPUTER,
    	}
    Random ran = new Random();
    
    public ArrayList<Ships> shipsArr;
    ArrayList<String> inputs;
    public Board playingBrd, referenceBrd;
    ArrayList<String> computerships = createInputs();
    String name;
    
    public playerType type;
    
	public Player() {
		shipsArr = new ArrayList<>();
		//inputs = new ArrayList<>();
		inputs = createInputs();
		playingBrd = new Board();
		referenceBrd = new Board();
	}
	
	public Ships setupShip(String start, String end) {
		Ships shipFormed = new Ships(start, end);
		return shipFormed;
	}
	
	public void updateShipsArr(Ships s){
		shipsArr.add(s);
	}
	

	public String randomblock() {
		int n = ran.nextInt(10) + 1;
		Character alpha = Constants.alphabets.charAt(ran.nextInt(Constants.alphabets.length()));
		String s = Character.toString(alpha) + n;
		return s;
	}
	
	/*public String randomhitcomp(Player computer) {
		int n = ran.nextInt(computer.inputs.size());
		return computer.inputs.get(n);
	}*/
	
	public String randomhitcomp() {
		int n = ran.nextInt(inputs.size());
		return inputs.get(n);
	}
	
	
	public String randomshipblocks (String s, int length) {	
		ArrayList<String> temp = new ArrayList<String>();
		String start = "";
		String end = "";
		String flagf = "T";
		String[] sarr = s.split("");
		int row = Integer.parseInt(sarr[1]);
		Character alph = sarr[0].charAt(0);
		int col = Constants.mapInConstants.get(sarr[0]);
		int j = ran.nextInt(10);
		String block = "";
		if(j>=5) { //same row
			for(int l=0; l<length; l++) {
				int col1 = col + l;
				if (col1 > 10) {
					flagf = "F";
					break;
				}else {
					Character k = Constants.alphabets.charAt(col1);	
					block = Character.toString(k) + row;
					boolean flag = computerships.contains(block);
					if (!flag) {
						flagf = "F";
						break;
					}else {
						temp.add(block);
					}
				}
				
			}
			start = s;
			end = block;	
		}else { //same col
			for(int l=0; l<length; l++) {
				int row1 = row + l;
				block = sarr[0] + row1;
				boolean flag = computerships.contains(block);
				if (!flag) {
					flagf = "F";
					break;
				}else {
					temp.add(block);
				}
			}	
			start = s;
			end = block;
		}
		if(flagf.equals("T")) {
			//System.out.println("Computer ship is: " + temp);
			computerships.removeAll(temp);
		}
		String f = flagf.concat(" "+start+" "+end);
		return f;
	}
	
	public String randomship() {
		int[] len = {2,3,3,4,5};
		int length = 0;
		for(int i = 0; i<len.length; i++) {
			length = len[i];
			boolean flag = true;
			while(flag) {
				String s = randomblock();
				String[] arr = randomshipblocks(s, length).split(" ");
				if (arr[0].equals("T")) {
					Ships shipcomp = setupShip(arr[1], arr[2]);
					shipsArr.add(shipcomp);
					flag =  false;
					break;
				}//if
			}//while
		}//for
	return null;
	//return s;
}
	
	public ArrayList<String> createInputs() {
		ArrayList<String> temp = new ArrayList<String>();
		String[] col = Constants.alphabets.split("");
		int[] rows = new int[Constants.row];
		for(int i = 1; i <= rows.length; i++) {
			rows[i-1] = i;
		}
		for(int i = 0; i < col.length; i++) {//11
			for(int j = 0; j < rows.length; j++) {//10
				temp.add(col[i] + rows[j]);
			}
		}
		return temp;
	}//createInputs

	public void updateDropdown(String s, ArrayList<String> drop) {
		drop.remove(s);
	}

	
}
