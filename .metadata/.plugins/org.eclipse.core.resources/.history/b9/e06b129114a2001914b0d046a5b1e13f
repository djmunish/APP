

public class GamePlay {
	public static Player p1,p2;
	public GamePlay() {
		p1 = new Player();
		p2 = new Player();
		
		//System.out.println("Calling ship1 :");
		p1.setupShip("C5", "A2");
		//System.out.println("Calling ship2 :");
		p1.setupShip("B7", "B2");
		//System.out.println("Calling ship3 :");
		p1.setupShip("F2", "F5");
		p1.setupShip("A1", "A5");
		p1.setupShip("A1", "F1");
		
		p2.randomship();

		
		for(Ships s:p1.shipsArr) {
			System.out.println(s.coordinates);
			System.out.println(s.shipColor);
		}
		

		for(Ships s:p2.shipsArr) {
			System.out.println(s.coordinates);
			System.out.println(s.shipColor);
			
		}
		
		p1.createInputs();
		p2.createInputs();

	}
	
	

}
