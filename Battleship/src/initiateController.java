import java.awt.Insets;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Initial controller to start the game and choose game play options.
 * @author mohit
 * @since 2019-07-06
 * @version 1.0.1
 */
public class initiateController extends Application {

    Player humanPlayer;
    Player computer;
    Udp u1 = new Udp();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Window to Choose Players");
        FileInputStream input = new FileInputStream("battleship.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setTranslateX(-150);
        imageView.setTranslateY(10);
        imageView.setFitHeight(540);
        imageView.setFitWidth(720);
        imageView.setPreserveRatio(true);



        Button btn2 = new Button();
        btn2.setText("Play with Another Player");
        btn2.setStyle("-fx-background-color: Skyblue");
        btn2.setTranslateX(344);
        btn2.setTranslateY(450);
        btn2.setPrefSize(250, 70);

        Button btn1 = new Button();
        btn1.setText("Play with Computer");
        btn1.setStyle("-fx-background-color: Skyblue");
        btn1.setTranslateX(600);
        btn1.setTranslateY(350);
        btn1.setPrefSize(250, 70);


        btn1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                humanPlayer = new Player();
                computer = new Player();
                humanPlayer.type = Player.playerType.HUMAN;
                computer.name = "COMPUTER";
                computer.type = Player.playerType.COMPUTER;
                humanPlayer.playWithHuman = false;



                Path currentRelativePath = Paths.get("");
                String s = currentRelativePath.toAbsolutePath().toString();
                System.out.println("Current relative path is: " + s);


                String filePath = s+"/gameData";
                File f = new File(filePath); //Change Path

                if (f.exists()) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Select");
                    alert.setHeaderText(Constants.load_Alert);
                    ButtonType yes = new ButtonType("Yes");
                    ButtonType no = new ButtonType("No");

                    // Remove default ButtonTypes
                    alert.getButtonTypes().clear();
                    alert.getButtonTypes().addAll(yes, no);
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get() == yes) {
                        loadGame(primaryStage, filePath);
                    } else if (option.get() == no) {
                        startNewGame(primaryStage);
                    }
                }
                else{
                    startNewGame(primaryStage);
                }
            }
        });


        btn2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                humanPlayer = new Player();
                humanPlayer.type = Player.playerType.HUMAN;


                TextInputDialog dialog = new TextInputDialog("Enter your name");

                dialog.setHeaderText("Enter your name:");
                dialog.setContentText("Name:");

                Optional<String> result = dialog.showAndWait();

                result.ifPresent(name -> {

                    if (name.length() > 0 && !name.equals("Enter your name")) {
                        humanPlayer.name = name;
                        humanPlayer.playWithHuman = true;
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Select");
                        alert.setHeaderText(Constants.salva_Alert);
                        ButtonType yes = new ButtonType("Yes");
                        ButtonType no = new ButtonType("No");

                        // Remove default ButtonTypes
                        alert.getButtonTypes().clear();
                        alert.getButtonTypes().addAll(yes, no);
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get() == yes) {
                            humanPlayer.initiateSalva();
                        } else if (option.get() == no) {
                            humanPlayer.gamePlayType = false;
                        }

                        humanPlayer.playerPort = 8888; //change port
                        try {
                           // humanPlayer.createConnection(7777);
                        	u1.startServer(7777);
                        	
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        shipSetupController fx2 = new shipSetupController();
                        fx2.humanPlayer = humanPlayer;
                        fx2.u1 = u1;

                        try {
                            fx2.start(primaryStage);
                        }
                        catch (Exception e){
                            System.out.println(e);
                        }


                    } else {
                        Constants.showAlert(Constants.name_Alert);
                    }
                });

            }
        });


        FlowPane flow = new FlowPane();
        btn1.setLayoutX(350);
        btn1.setLayoutY(350);

        flow.setHgap(5);
        flow.getChildren().addAll(btn1, btn2, imageView);
        flow.setStyle("-fx-background-color: Grey");
        primaryStage.setScene(new Scene(flow, 1000, 800));
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

        public void loadGame(Stage primaryStage, String path) {

            //player ships

            // The name of the file to open.

            // This will reference one line at a time
            String line = null;

            File f_human = new File(path + "/human.txt"); //Change Path
            File f_Computer = new File(path + "/computer.txt"); //Change Path
            String humanData[] = {};
            String compData[] = {};
            {

                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReaderHuman =
                            new FileReader(f_human);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader =
                            new BufferedReader(fileReaderHuman);

                    while ((line = bufferedReader.readLine()) != null) {
                        humanData = line.split(Constants.separator);
                    }
                    FileReader fileReaderComputer = new FileReader(f_Computer);
                    BufferedReader bufferedReaderComputer =
                            new BufferedReader(fileReaderComputer);
                    while ((line = bufferedReaderComputer.readLine()) != null) {
                        compData = line.split(Constants.separator);
                    }

                    // Always close files.
                    bufferedReader.close();

                    for (String d : humanData) {
                        System.out.println(d);
                    }

                    humanPlayer.name = humanData[0];
                    computer.name = compData[0];
                    String[] ships = humanData[1].split("-");
                    for (String d : ships) {
                        if(!d.equals("")) {
                            String ships_Val = d.substring(1, d.length() - 1);
                            Ships loadShip = new Ships(ships_Val.substring(0, 2), ships_Val.substring(ships_Val.length() - 2));
                            humanPlayer.shipsArr.add(loadShip);
                        }
                    }



                    String h_inputs = humanData[2].substring(1,humanData[2].length()-1);

                    humanPlayer.inputs.clear();
                    for (String d : h_inputs.split(",")) {
                        humanPlayer.inputs.add(d.trim());
                    }






                    String[] cships = compData[1].split("-");
                    for (String d : cships) {
                        if(!d.equals("")) {
                            String ships_Val = d.substring(1, d.length() - 1);
                            Ships loadShip = new Ships(ships_Val.substring(0, 2), ships_Val.substring(ships_Val.length() - 2));
                            computer.shipsArr.add(loadShip);
                        }
                    }

                    String c_inputs = compData[2].substring(1,compData[2].length()-1);
                    computer.inputs.clear();
                    for (String d : c_inputs.split(",")) {
                        computer.inputs.add(d.trim());
                    }


                    Arena fx2 = new Arena();
                    fx2.humanPlayer = humanPlayer;
                    fx2.computer = computer;


                    String hits = humanData[3].substring(1,humanData[3].length()-1);

                    for (String d : hits.split(",")) {
                        if(d.length()>0) {
                            fx2.loadhitsHuman.add(d.trim());
                        }

                    }

                    String miss = humanData[4].substring(1,humanData[4].length()-1);
                    for (String d : miss.split(",")) {
                        if(d.length()>0) {
                            fx2.loadmissHuman.add(d.trim());
                        }

                    }


                    String Chits = compData[3].substring(1,compData[3].length()-1);

                    for (String d : Chits.split(",")) {
                        if(d.length()>0) {
                            fx2.loadhitsComputer.add(d.trim());
                        }

                    }

                    String Cmiss = compData[4].substring(1,compData[4].length()-1);
                    for (String d : Cmiss.split(",")) {
                        if(d.length()>0) {
                            fx2.loadmissComputer.add(d.trim());
                        }

                    }



                    try {
                        fx2.start(primaryStage);
                    } catch (Exception e) {
                        System.out.println(e);
                    }




                } catch (FileNotFoundException ex) {
                    System.out.println(
                            "Unable to open file '");
                } catch (IOException ex) {
                    System.out.println(
                            "Error reading file ");
                    // Or we could just do this:
                    // ex.printStackTrace();
                }



            }
        }

        public void startNewGame(Stage primaryStage){
        computer.computerRandomShip();
//                for (Ships s : computer.shipsArr) {
//                    System.out.println("random ships");
//                    System.out.println(s.coordinates);
//                    System.out.println(s.hexColor);
//                }

        TextInputDialog dialog = new TextInputDialog("Enter your name");

        dialog.setHeaderText("Enter your name:");
        dialog.setContentText("Name:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {

            if (name.length() > 0 && !name.equals("Enter your name")) {
                humanPlayer.name = name;

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Select");
                alert.setHeaderText(Constants.salva_Alert);
                ButtonType yes = new ButtonType("Yes");
                ButtonType no = new ButtonType("No");

                // Remove default ButtonTypes
                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(yes, no);
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get() == yes) {
                    humanPlayer.initiateSalva();
                } else if (option.get() == no) {
                    humanPlayer.gamePlayType = false;
                }


                shipSetupController fx2 = new shipSetupController();
                fx2.humanPlayer = humanPlayer;
                fx2.computer = computer;

                try {
                    fx2.start(primaryStage);
                }
                catch (Exception e){
                    System.out.println(e);
                }


            } else {
                Constants.showAlert(Constants.name_Alert);
            }
        });
    }

}
