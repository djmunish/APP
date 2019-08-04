import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Saving {

    Player humanPlayer;
    Saving(){
        humanPlayer = new Player();
    }

    public static void printfc(Player humanPlayer, long elapsedtime, ArrayList<String> hits, ArrayList<String> miss) throws IOException {

        System.out.println("Player name:"+humanPlayer.name);

        for(Ships s : humanPlayer.shipsArr){
            System.out.println("Player ships:"+s.coordinates);
        }
        System.out.println("Player input:"+humanPlayer.inputs);
        System.out.println("Player hits:"+hits);
        System.out.println("Player miss:"+miss);
        System.out.println("Player time:"+elapsedtime);

        File file = new File("G:\\SOEN 6441\\APP\\testFile1.txt");








    }
}
