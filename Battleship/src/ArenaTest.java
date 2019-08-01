import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import javafx.scene.Node;

class ArenaTest extends Arena{

    Arena test = new Arena();;
    Player p1 = new Player();;
    Player p2 = new Player();;
    Ships s = new Ships("A1","A5");;
    Ships s1= new Ships("B2","B4");;
    Node node;

//	@Before public void testbefore() {
//		test = new Arena();
//		p1 = new Player();
//		p2 = new Player();
//		s = new Ships("A1","A5");
//		s1 = new Ships("B2","B4");
//	}
//
    //check the method for declaring the winner
    //array is not empty for player 1

    @Test public void WinnerChecktest()
    {
        System.out.println("hii");
        System.out.print("ship is " + s.coordinates);
        p1.shipsArr.add(s);
        assertEquals(false,checkWinner(p1,p2));
    }



    //checks when array is empty and player 1 is winner
    @Test public void WinnerChecktest2() {
        p1.shipsArr.clear();
        assertEquals(true,checkWinner(p1,p2));
    }

    //check if game is over once all the ships of a player is sinked
    @Test public void isGameOver()
    {
        assertEquals(true,checkForGameOver());
    }
    public boolean checkForGameOver() {
        if(p1.shipsArr.size()==0 || p2.shipsArr.size()==0)
        {
            return true;
        }
        return false;
    }

    @Test public void isGameOver1()
    {

        assertEquals(false,checkForGameOver1());
    }
    public boolean checkForGameOver1() {
        if(p1.shipsArr.size()==1 || p2.shipsArr.size()==2)
        {
            return true;
        }
        return false;
    }


    @Test public void calculateScore(){

//        double minutes = 1;
        p1.hitscount = 17;
        p1.misscount = 0;
        String score= "270";

        assertEquals(score,calcScore(60000,p1));
    }

    @Test public void calculateScore1(){

//        double minutes = 1;
        p1.hitscount = 17;
        p1.misscount = 30;
        String score= "340";

        assertEquals(score,calcScore(30000,p1));
    }

    @Test public void calculateScoreAgain(){

//        double minutes = 1;
        p1.hitscount = 17;
        p1.misscount = 10;
        String score= "260";

        assertEquals(score,calcScore(60000,p1));
    }

}




