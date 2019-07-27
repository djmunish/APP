import java.util.ArrayList;
import java.util.Random;
import java.util.Iterator;

public class Player {

    enum playerType {
        HUMAN,
        COMPUTER,
    }

    Random ran = new Random();

    public ArrayList<Ships> shipsArr;
    ArrayList<String> inputs;
    ArrayList<String> inputsfirst;

    ArrayList<String> computerships = createInputs();
    String name;

    public playerType type;

    public Player() { // Constructor to create Player object
        shipsArr = new ArrayList<>();
        //inputs = new ArrayList<>();
        inputs = createInputs();
        inputsfirst = new ArrayList<>();


    }

    public Ships setupShip(String start, String end) { // Create ship for player
        Ships shipFormed = new Ships(start, end);
        return shipFormed;
    }

    public void updateShipsArr(Ships s) { // Update ships array of player
        shipsArr.add(s);
    }



	
	/*public String randomhitcomp(Player computer) {
		int n = ran.nextInt(computer.inputs.size());
		return computer.inputs.get(n);
	}*/

    public String randomhitcomp() { // random selection of location for computer
        int n = ran.nextInt(inputs.size());
        return inputs.get(n);
    }
    
    public String checkneighbors(String s, Player human) { // AI -- Check nearby ship location for computer
    	ArrayList<String> neighbor = new ArrayList<String>();
    	String f = "";
    	String s1 = s.substring(0, 1);
    	String s2 = s.substring(1);
    	//string left
    	int index1 = Constants.mapInConstants.get(s1);
    	int index2 = Integer.parseInt(s2);
    	if(index1== 0 && index2 ==1) { //TOPLEFT
    		neighbor.add("D");
    		neighbor.add("R");
    	}else if(index1== 10 && index2 ==1) { //TOPRIGHT
    		neighbor.add("D");
    		neighbor.add("L");
    	}else if(index1 == 1 && index2 ==10) { //BOTTOMLEFT
    		neighbor.add("U");
    		neighbor.add("R");
    	}else if(index1 == 10 && index2 == 10) { //BOTTOMRIGHT
    		neighbor.add("U");
    		neighbor.add("L");
    	}else if(index1 == 0 ) { //leftcol
    		neighbor.add("U");
    		neighbor.add("D");
    		neighbor.add("R");
    	}else if(index1 == 10) { //rightcol
    		neighbor.add("U");
    		neighbor.add("D");
    		neighbor.add("L");
    	}else if(index2 == 1) { //toprow
    		neighbor.add("R");
    		neighbor.add("D");
    		neighbor.add("L");
    	}else if(index2 == 10) { //bottomrow
    		neighbor.add("U");
    		neighbor.add("R");
    		neighbor.add("L");
    	}else {
    		neighbor.add("U");
    		neighbor.add("R");
    		neighbor.add("L");
    		neighbor.add("D");
    	}
    	Iterator i = neighbor.iterator(); 
    	System.out.println("Neighbors with ships are:");
    	while(i.hasNext()) {
    		String s3 = (String)i.next();
    		if(s3.equals("U")) {
    			int in = index2-1;
    			String t = s1 + Integer.toString(in);
    			System.out.println(t);
    			boolean flag = Ships.checkifship(t, human);
    			if(flag && inputs.contains(t)) {
    				f = f.concat(t + " ");
    			}
    			
    		}else if(s3.equals("D")){
    			int in = index2+1;
    			String t = s1 + Integer.toString(in);
    			System.out.print(t);
    			boolean flag = Ships.checkifship(t, human);
    			if(flag && inputs.contains(t)) {
    				f = f.concat(t + " ");
    			}	
    		}else if(s3.equals("L")) {
    			Character st = Constants.alphabets.charAt(index1-1);
    			String t = Character.toString(st) + index2;
    			System.out.print(t);
    			boolean flag = Ships.checkifship(t, human);
    			if(flag && inputs.contains(t)) {
    				f = f.concat(t + " ");
    			}	
    		}else if(s3.equals("R")) {
    			Character st = Constants.alphabets.charAt(index1+1);
    			String t = Character.toString(st) + index2;
    			System.out.print(t);
    			boolean flag = Ships.checkifship(t, human);
    			if(flag && inputs.contains(t)) {
    				f = f.concat(t + " ");
    			}
    		}//R
    	}//while
    	return f;
    }
    
    public String randomhitcompai(Player human) {  // Generate Priority list of random computer hits.
    	System.out.println("Inputsfirst is: " + inputsfirst);
    	System.out.println("Inputs is: " + inputs);
    	String s = "";
    	if(inputsfirst.size() == 0) {
    		int n = ran.nextInt(inputs.size()); 
    		s = inputs.get(n);
    		inputs.remove(s);
        }else {
        	int n = ran.nextInt(inputsfirst.size()); 
    		s = inputsfirst.get(n);
    		inputsfirst.remove(s);
        }
    	System.out.println("Randomcomphit is" + s);
        boolean flag = Ships.checkifship(s, human);
        if(flag) {
        	String f = checkneighbors(s, human).trim();
        	String[] farr = f.split(" ");
        	System.out.print("Neighbors with ships are: ");
        	for(int j=0; j<farr.length; j++) {
        		System.out.print(farr[j] + " ");
        		if(farr[j].equals("")) {
        			System.out.print("");
        		}else {
        		inputsfirst.add(farr[j].trim());
        		inputs.remove(farr[j]);}
        	}//for
        	System.out.println("Inputsfirst is in: " + inputsfirst);
        	System.out.println("Inputs is in: " + inputs);
        	return s;
        }else {
        	System.out.println("Inputsfirst is: " + inputsfirst);
        	System.out.println("Inputs is: " + inputs);
        	return s;
        }     
    }
    


    public String randomshipblocks(String s, int length) { // Check the length of the ship and the valid neighbors
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
        if (j >= 5) { //same row
            for (int l = 0; l < length; l++) {
                int col1 = col + l;
                if (col1 > 10) {
                    flagf = "F";
                    break;
                } else {
                    Character k = Constants.alphabets.charAt(col1);
                    block = Character.toString(k) + row;
                    boolean flag = computerships.contains(block);
                    if (!flag) {
                        flagf = "F";
                        break;
                    } else {
                        temp.add(block);
                    }
                }

            }
            start = s;
            end = block;
        } else { //same col
            for (int l = 0; l < length; l++) {
                int row1 = row + l;
                block = sarr[0] + row1;
                boolean flag = computerships.contains(block);
                if (!flag) {
                    flagf = "F";
                    break;
                } else {
                    temp.add(block);
                }
            }
            start = s;
            end = block;
        }
        if (flagf.equals("T")) {
            //System.out.println("Computer ship is: " + temp);
            computerships.removeAll(temp);
        }
        String f = flagf.concat(" " + start + " " + end);
        return f;
    }


    public boolean checkOverlap(ArrayList<String> newShip) { // Check overlapping of ships for player
        for (Ships s : shipsArr) {
            for (String i : s.coordinates) {
                for (String g : newShip) {
                    if (i.equals(g)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public String randomblock() { //generate random block from the Play Area grid
        int n = ran.nextInt(10) ;
        System.out.println("n is :" + n);
        Character alpha = Constants.alphabets.charAt(ran.nextInt(Constants.alphabets.length()));
        String s = Character.toString(alpha) + n;
        return s;
    }

    public String randomship() { //Create Random ships for the computer for length 2/3/3/4/5.
        int[] len = {2, 3, 3, 4, 5};
        int length = 0;
        for (int i = 0; i < len.length; i++) {
            length = len[i];
            System.out.println("length is :" + length);
            boolean flag = true;
            while (flag) {
                String s = randomblock();
                System.out.println("random block is :" + s);
                String[] arr = randomshipblocks(s, length).split(" ");
                if (arr[0].equals("T")) {
                    Ships shipcomp = setupShip(arr[1], arr[2]);
                    shipsArr.add(shipcomp);
                    flag = false;
                    break;
                }//if
            }//while
        }//for
        return null;
        //return s;
    }

    public ArrayList<String> createInputs() { // Create whole set of input location for player
        ArrayList<String> temp = new ArrayList<String>();
        String[] col = Constants.alphabets.split("");
        int[] rows = new int[Constants.row];
        for (int i = 1; i <= rows.length; i++) {
            rows[i - 1] = i;
        }
        for (int i = 0; i < col.length; i++) {//11
            for (int j = 0; j < rows.length; j++) {//10
                temp.add(col[i] + rows[j]);
            }
        }
        return temp;
    }//createInputs

    public void updateDropdown(String s, ArrayList<String> drop) { // Update human player input drop down
        drop.remove(s);
    }


}
