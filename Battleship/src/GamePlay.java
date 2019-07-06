import java.util.ArrayList;

public class GamePlay {


	public GamePlay() {
		
		
		
		
		
	}
	
	public ArrayList<String> createInputs() {
		String[] col = {"A","B","C","D","E","F","G","H","I","J","K"};
		int[] rows = new int[Constants.row];
		for(int i = 0; i < rows.length; i++) {
			rows[i] = i;
		}
		ArrayList<String> inputs = new ArrayList<>();
		
		for(int i = 0; i < col.length; i++) {//11
			for(int j = 0; j < rows.length; j++) {//10
				inputs.add(col[i] + rows[j]);
			}
		}
		return inputs;
	}
	
	
	
}
