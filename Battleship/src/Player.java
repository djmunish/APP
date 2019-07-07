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
	

	public String randomblock() {
		int n = ran.nextInt(10);
		Character alpha = Constants.alphabets.charAt(ran.nextInt(Constants.alphabets.length()));
		String s = Character.toString(alpha) + n;
		return s;
	}
	
	public String randomhitcomp(Player computer) {
		int n = ran.nextInt(computer.inputs.size());
		return computer.inputs.get(n);
	}
	
	
	public String randomshipblocks (String s, int length, Player computer) {	
		String start = "";
		String end = "";
		String flagf = "T";
		String[] sarr = s.split("");
		int row = Integer.parseInt(sarr[1]);
		Character alph = sarr[0].charAt(0);
		int col = Constants.mapInConstants.get(Character.toString(alph));
		int j = ran.nextInt(1);
		String block = "";
		if(j==0) { //same row
			for(int l=0; l<length; l++) {
				int col1 = col + l;
				if (col1>11) {
					flagf = "F";
					break;
				}else {
					Character k = Constants.alphabets.charAt(col1);	
					block = Character.toString(k) + row;
					boolean flag = computer.inputs.contains(block);
					if (!flag) {
						flagf = "F";
						break;
					}
				}
				System.out.print(block + " ");
			}
			start = s;
			end = block;
		//	computer.setupShip(start, end);	
		}else { //same col
			start = s;
			for(int l=0; l<length; l++) {
				int row1 = row + length;
				block = sarr[0] + l;
				boolean flag = computer.inputs.contains(block);
				if (!flag) {
					flagf = "F";
					break;
				}
				System.out.print(block + " ");
			}					
			end = block;
			//computer.setupShip(start, end);
		}
		String f = flagf.concat(" " + start + " " + end);
		return f;
	}
	
/*	public String randomship(Player computer) {
			int[] len = {2,3,3,4,5};
			int length = 0;
			for(int i = 0; i<len.length; i++) {
				length = len[i];
				String s = randomblock();
				String[] sarr = s.split("");
				int row = Integer.parseInt(sarr[1]);
				Character alph = sarr[0].charAt(0);
				int col = Constants.mapInConstants.get(Character.toString(alph));
				int j = ran.nextInt(1);
				String block = null;
				if(j==0) { //same row
					for(int l=0; l<length; l++) {
						int col1 = col + l;
						Character k = Constants.alphabets.charAt(col1);	
						block = Character.toString(k) + row;
						System.out.print(block + " ");
					}
					String start = s;
					String end = block;
					computer.setupShip(start, end);	
				}else { //same col
					String start = s;
					for(int l=0; l<length; l++) {
						int row1 = row + length;
						block = sarr[0] + l;
						System.out.print(block + " ");
					}					
					String end = block;
					computer.setupShip(start, end);
				}
			}//for
		return null;
		//return s;
	} */
	
	public String randomship(Player computer) {
		int[] len = {2,3,3,4,5};
		int length = 0;
		for(int i = 0; i<len.length; i++) {
			length = len[i];
			boolean flag = true;
			String s = randomblock();
			while(flag) {
				String[] arr = randomshipblocks(s, length, computer).split(" ");
				if (arr[0].equals("T")) {
					computer.setupShip(arr[1], arr[2]);
					flag =  false;  
				}//if
			}//while
		}//for
	return null;
	//return s;
}
	
	public void createInputs() {
		//String[] col = {"A","B","C","D","E","F","G","H","I","J","K"};
		String[] col = Constants.alphabets.split("");
		int[] rows = new int[Constants.row];
		for(int i = 1; i <= rows.length; i++) {
			rows[i-1] = i;
		}
		for(int i = 0; i < col.length; i++) {//11
			for(int j = 0; j < rows.length; j++) {//10
				inputs.add(col[i] + rows[j]);
			}
		}
	}//createInputs


	
}
