import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

    public static void saveHuman(Player humanPlayer, long elapsedtime, ArrayList<String> hits, ArrayList<String> miss) throws IOException {



        System.out.println("Player name:"+humanPlayer.name);

        for(Ships s : humanPlayer.shipsArr){
            System.out.println("Player ships:"+s.coordinates);
        }
        System.out.println("Player input:"+humanPlayer.inputs);
        System.out.println("Player hits:"+hits);
        System.out.println("Player miss:"+miss);
        System.out.println("Player time:"+elapsedtime);
        File file;
        Path currentRelativePath = Paths.get("");
        String sPath = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + sPath);

        if(humanPlayer.type == Player.playerType.HUMAN){
            new File(sPath+"/gameData").mkdirs();
            file = new File(sPath+"/gameData/human.txt");
        }
        else {
            new File(sPath+"/gameData").mkdirs();
            file = new File(sPath+"/gameData/computer.txt");
        }



        if (file.exists())
        {
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Write Content
        FileWriter writer = new FileWriter(file);
        writer.append(humanPlayer.name);
        writer.write(Constants.separator);

        for(Ships s : humanPlayer.shipsArr){
            writer.append(String.valueOf(s.coordinates));
            writer.write(Constants.separator);
        }

        writer.append(String.valueOf(humanPlayer.inputs));
        writer.write(Constants.separator);

        writer.append(String.valueOf(hits));
        writer.write(Constants.separator);
        writer.append(String.valueOf(miss));
        writer.write(Constants.separator);
        writer.append(String.valueOf(elapsedtime));

        writer.close();

    }
}
