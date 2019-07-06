import javax.swing.*;

public class Board {

	private static String[][] brd;

	public static void main(String[] args) {
		GamePlay g = new GamePlay();
		System.out.println(g.createInputs());
		
		Board b = new Board();
		brd = b.createBoard();
		b.printBoard(brd);
		
		b.getConstants("C5");
		b.getConstants("K9");
		b.update(5, 6, true);
		b.update(2, 6, true);
		b.update(7, 6, false);
		b.update(9, 6, true);

		JFrame f = new JFrame();
		f.setSize(400, 500);
		f.setVisible(true);

	}

	public String[][] createBoard() {
		String[][] temp = new String[Constants.row][Constants.col];
		for (int i = 0; i < Constants.row; i++) {// 11
			for (int j = 0; j < Constants.col; j++) {// 10
				temp[i][j] = "X";
			}
		}
		return temp;

	}

	public void update(int r, int c, boolean hit) {
		if (hit) {
			brd[r][c] = "1";
		} else {
			brd[r][c] = "0";
		}
		printBoard(brd);
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
	
	public void getConstants(String str) {
		
		String s[] = str.split("");
	    int x = Constants.mapInConstants.get(s[0]);	//c
	    int y = Integer.parseInt(s[1]);	//r
	    System.out.println(x);
		System.out.println(y);
	    update(y , x, false);
		
	}
	

}
