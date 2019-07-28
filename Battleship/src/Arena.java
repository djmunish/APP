
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.Optional;

public class Arena extends Application {

    Player humanPlayer;
    Player computer;

    String selectedAddress;

    public void start(Stage stage) { 

        try {

            // set title for the stage
            stage.setTitle("LET'S PLAY");

            HBox hbox = new HBox(70);
            hbox.setTranslateX(200);
            hbox.setTranslateY(20);

            Label right = new Label(humanPlayer.name.toUpperCase());
            right.setFont(new Font("Arial", 30));
            Label left = new Label(computer.name.toUpperCase());
            left.setFont(new Font("Arial", 30));
            left.setPrefHeight(50);
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    System.out.println("button pressed");
                }
            };

            // create split pane 1

            SplitPane split_pane1 = new SplitPane();
            split_pane1.setOrientation(Orientation.VERTICAL);
            split_pane1.setPrefSize(500, 500);
            GridPane playerGrid = createGrid(Constants.row + 1,Constants.col + 1);
            GridPane playerRefGrid = createGrid(Constants.row + 1,Constants.col + 1);


            GridPane salvaGrid = createGrid( 1,5);



            GridPane compGrid = createGrid(Constants.row + 1,Constants.col + 1);
            GridPane compRefGrid = createGrid(Constants.row + 1,Constants.col + 1);
            split_pane1.getItems().addAll(playerRefGrid, playerGrid, right);
            hbox.getChildren().add(split_pane1);

            // create split pane 2
            SplitPane split_pane2 = new SplitPane();
            split_pane2.setPrefSize(500, 500);
            split_pane2.setOrientation(Orientation.VERTICAL);
            split_pane2.getItems().addAll(compRefGrid, compGrid, left);

            final ComboBox inputComboBox = new ComboBox();
            inputComboBox.setPromptText("Select Location");
            inputComboBox.setStyle("-fx-border-color: #000000 ; -fx-border-width: 3px;");
            inputComboBox.setStyle("-fx-border-color: #000000 ; -fx-background-color: #CD853F;");
            inputComboBox.getItems().addAll(
               humanPlayer.inputs
            );

            inputComboBox.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue ov, String t, String t1) {
                }
            });

            Button btn = new Button();
            btn.setText("Hit");
            btn.setStyle("-fx-border-color: #000000 ; -fx-border-width: 3px;");
            btn.setStyle("-fx-border-color: #000000; -fx-background-color: #CD853F");
            btn.setOnAction(new EventHandler<ActionEvent>() {

                	@Override
                	public void handle(ActionEvent event) {
                		boolean flag3 = false;
                		try {
                			selectedAddress = (String) inputComboBox.getValue();
                		}
                		catch(Exception e){
                			System.out.print("");
                		} 
                    	if(selectedAddress!= null){
                    	System.out.println("Human Player hit is == " + selectedAddress);
                    	humanPlayer.updateDropdown(selectedAddress, humanPlayer.inputs);
                    	inputComboBox.getItems().remove(selectedAddress);
                    	inputComboBox.setPromptText("Select Location");
                    	boolean flag = Ships.colorButton(playerRefGrid, compGrid, selectedAddress, Arena.this, computer);



                    	String message = "Wohoo!! Its a hit!!";
                    	if (!flag) {
                    		message = "Bohoo!! You missed it!!";
                    	}
                    	Constants.showAlert(message);


                    	boolean flag2 = checkWinner(computer, humanPlayer);
                    	if (!flag2) {
                    		String s = computer.randomhitcompai(humanPlayer);
                    		System.out.println("computerhit is == " + s);
                    		boolean flag1 = Ships.colorButton(playerGrid, compRefGrid, s, Arena.this, humanPlayer);


                    		String messageComp;
                    		if (flag1) {
                    			messageComp = "It was a hit by Computer at " + s;
                    		} else {
                    			messageComp = "Wohoo!! Computer missed the shot and hit you at " + s;
                    		}
                    		Constants.showAlert(messageComp);

                    		flag3 = checkWinner(humanPlayer, computer);
                    	}

                    	if (flag2 || flag3) {
                    		Alert alert = new Alert(AlertType.CONFIRMATION);
                    		alert.setTitle("Select");
                    		alert.setHeaderText("Do you wish to continue?");
                    		ButtonType yes = new ButtonType("Yes");
                    		ButtonType no = new ButtonType("No");

                        // Remove default ButtonTypes
                    		alert.getButtonTypes().clear();
                    		alert.getButtonTypes().addAll(yes, no);
                    		Optional<ButtonType> option = alert.showAndWait();

                    		if (option.get() == yes) {
                    			initiateController fx2 = new initiateController();
                    			try {
                    				fx2.start(stage);
                    			} catch (FileNotFoundException e) {
                    				e.printStackTrace();
                    			}
                    		} else if (option.get() == no) {
                    			Platform.exit();
                    		}

                    	}
                    }else {
                    	String serror = "Please select a location and then click 'Hit' Button!";
                        	Constants.showAlert(serror);
                    }

                }
            });


            // To check if it is a HIT / MISS by any player
            for (Ships p : humanPlayer.shipsArr) {
                ArrayList<String> got = p.coordinates;
                for (int i = 0; i < got.size(); i++) {
                	String s0 = got.get(i).substring(0,1);
                	String s1 = got.get(i).substring(1);
                	
                    int x = Constants.mapInConstants.get(s0);    //c
                    int y = Integer.parseInt(s1) - 1;    //r
                    Button b = (Button) getNodeFromGridPane(playerGrid, x + 1 , y);
                    b.setStyle("-fx-background-color:" + p.hexColor);
                }
            }

            showHideComputerShip(true, compGrid); // show hide Computer ships


            VBox vbox = new VBox();
            vbox.getChildren().add(inputComboBox);
            vbox.getChildren().add(salvaGrid);
            vbox.setSpacing(10);
            hbox.getChildren().add(vbox);
            hbox.getChildren().add(btn);
            vbox.setPrefWidth(80);



            hbox.setSpacing(10);
            hbox.getChildren().add(split_pane2);





            // Creating scene
            Scene scene = new Scene(new Group(hbox), 1000, 800);
            scene.setFill(Color.GRAY);
            stage.setScene(scene);
            stage.setWidth(2000);
            stage.setHeight(800);


            stage.show();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) { //Function to fetch each node from the grid
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }


    // Main Method
    public static void main(String args[]) {
        // launch the application
        launch(args);
    }

    public void showHideComputerShip(Boolean show , GridPane cGrid) { //Function to Show/Hide Computer Ships on the Play Arena
        if (show) {
            {
                for (Ships p : computer.shipsArr) {

                    ArrayList<String> got = p.coordinates;

                    for (int i = 0; i < got.size(); i++) {
                        String s0 = got.get(i).substring(0, 1);
                        String s1 = got.get(i).substring(1);
                        int x = Constants.mapInConstants.get(s0);    //c
                        int y = Integer.parseInt(s1);    //r
                        Button b = (Button) getNodeFromGridPane(cGrid, x + 1, y - 1);
                        b.setStyle("-fx-background-color:" + p.hexColor);
                    }
                }
            }
        }
    }

    // Function to Create Grid to setup the ships by the Human Player
    public GridPane createGrid(int rows, int col) {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <  col; j++) {
                if (j == 0 && i != Constants.row) {
                    String buttonname = "button" + i + j;
                    Button button = new Button(Integer.toString(i + 1));
                    button.setPrefSize(40, 15);
                    button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                    button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                    button.setDisable(true);
                    gridPane.add(button, j, i);
                } else if (i == Constants.row && j != 0) {

                    if (j == 1) {

                        Button button = new Button("A");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 2) {
                        Button button = new Button("B");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 3) {
                        Button button = new Button("C");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 4) {
                        Button button = new Button("D");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 5) {
                        Button button = new Button("E");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 6) {
                        Button button = new Button("F");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 7) {
                        Button button = new Button("G");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 8) {
                        Button button = new Button("H");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 9) {
                        Button button = new Button("I");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 10) {
                        Button button = new Button("J");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);

                    } else if (j == 11) {
                        Button button = new Button("K");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                } else {

                    Button button = new Button("-");
                    button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                    button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFFFFF");
                    button.setDisable(true);
                    button.setPrefSize(40, 15);

                    gridPane.add(button, j, i);
                }
            }//inner for
        }//outer for
        if(rows>1) {
            gridPane.setPrefSize(500, 500);
        }
        else{
            gridPane.setPrefSize(500, 20);
        }
        return gridPane;
    }



    public boolean checkWinner(Player p1, Player p2) { //Function to check the winner of the game after every hit by each player
        System.out.print("Ships array size is " + p1.shipsArr.size());
        if (p1.shipsArr.size() == 0) {
            Constants.showAlert(p2.name + " won the game!!!");
            return true;
        } else {
            return false;
        }
    }//checkWinner

}//Arena