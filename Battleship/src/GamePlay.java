

public class GamePlay {
	public static Player p1,p2;
	public GamePlay() {
		
		p1 = new Player();
		p2 = new Player();

		p1.setupShip("C2", "C5");
		System.out.println(p1.shipsArr);
		
		p1.createInputs();
		p2.createInputs();
		
	}
	
	

}
