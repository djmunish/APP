import java.util.ArrayList;

import javax.swing.*;

public class Board {
	
	public static String[][] brd;

	public static void main(String[] args) {
		Board b = new Board();
		GamePlay g = new GamePlay();

		System.out.println(g.createInputs());
		ArrayList<String> drop = g.createInputs();
		
		brd = b.createBoard();
		b.printBoard(brd);
		
		b.getConstants("C5", drop);
		b.getConstants("K9", drop);
		b.getConstants("A0", drop);

		JFrame f = new JFrame();
		f.setSize(400, 500);
		f.setVisible(true);

	}

	public static String[][] createBoard() {
		String[][] temp = new String[Constants.row][Constants.col];
		for (int i = 0; i < Constants.row; i++) {// 11
			for (int j = 0; j < Constants.col; j++) {// 10
				temp[i][j] = "X";
			}
		}
		return temp;

	}

	public void update(int r, int c, boolean hit, String s, ArrayList<String> arL) {
		if (hit) {
			brd[r][c] = "1";
		} else {
			brd[r][c] = "0";
		}
		printBoard(brd);
		updateDropdown(s, arL);
	}
	

	public void updateDropdown(String s, ArrayList<String> drop) {
		
		drop.remove(s);
		System.out.println(drop);

	}

	public void printBoard(String[][] b) {

		for (int i = 0; i < Constants.row; i++) {// 11
			for (int j = 0; j < Constants.col; j++) {// 10
				System.out.print(b[i][j]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
	public void getConstants(String str, ArrayList<String> arL) {
		
		String s[] = str.split("");
	    int x = Constants.mapInConstants.get(s[0]);	//c
	    int y = Integer.parseInt(s[1]);	//r
	    System.out.println(x);
		System.out.println(y);
	    update(y , x, false , str, arL);
		
	}
	
}
