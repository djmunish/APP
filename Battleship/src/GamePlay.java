

public class GamePlay {
	public static Player p1,p2;
	public GamePlay() {
		
		p1 = new Player();
		p2 = new Player();


		p1.setupShip("C2", "C5");
		p1.setupShip("B2", "B7");
		p1.setupShip("F2", "F5");
		p1.setupShip("A1", "A5");
		p1.setupShip("A1", "F1");
		
		//p2.randomship(p2);
		
		for(Ships s:p1.shipsArr) {
			System.out.println(s.coordinates);
			System.out.println(s.shipColor);
		}
		
		p1.createInputs();
		p2.createInputs();
		
	}
	
	

}
