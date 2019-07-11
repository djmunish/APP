
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

            Label right = new Label(humanPlayer.name);

            right.setFont(new Font("Arial", 30));

            Label left = new Label("COMPUTER");
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
            split_pane1.setPrefSize(300, 40);

            GridPane playerGrid = createGrid();
            GridPane playerRefGrid = createGrid();

            GridPane compGrid = createGrid();
            GridPane compRefGrid = createGrid();

            split_pane1.getItems().addAll(playerRefGrid, playerGrid, right);

            hbox.getChildren().add(split_pane1);

            // create split pane 2

            SplitPane split_pane2 = new SplitPane();
            split_pane2.setPrefSize(300, 400);
            split_pane2.setOrientation(Orientation.VERTICAL);
            // split_pane2.getItems().addAll(createGrid(),createGrid(),left);
            split_pane2.getItems().addAll(compRefGrid, compGrid, left);
            //humanPlayer.createInputs();

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
                    //selectedAddress = t1;
                    //System.out.println("Value is: " + inputComboBox.getValue());
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
                			System.out.println("chako == " + selectedAddress);
                		}
                		catch(Exception e){
                			System.out.print("");
                		} 
                    	if(selectedAddress!= null){
                    	System.out.println("chako == " + selectedAddress);
                    	humanPlayer.updateDropdown(selectedAddress, humanPlayer.inputs);
                    	System.out.println("humanPlayerinputs updated are: " + humanPlayer.inputs);
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
                    		// String s = computer.randomhitcomp();
                    		String s = computer.randomhitcompai(humanPlayer);
                    
                    		System.out.println("computerinputs are: " + computer.inputs);
                    		System.out.println("computerhit is == " + s);
                        //computer.inputs.remove(s);
                    		System.out.println("computerinputs updated are: " + computer.inputs);

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

                        // option != null.
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


            // HIT / MISS


            for (Ships p : humanPlayer.shipsArr) {

                ArrayList<String> got = p.coordinates;

                for (int i = 0; i < got.size(); i++) {

//                    String s[] = got.get(i).split("");
                	String s0 = got.get(i).substring(0,1);
                	String s1 = got.get(i).substring(1);
                	
                    int x = Constants.mapInConstants.get(s0);    //c
                    int y = Integer.parseInt(s1) - 1;    //r
                    Button b = (Button) getNodeFromGridPane(playerGrid, x + 1 , y);
                    b.setStyle("-fx-background-color:" + p.hexColor);
                }
            }

            showHideComputerShip(true, compGrid); // show hide Computer ships

            hbox.getChildren().add(inputComboBox);
            hbox.getChildren().add(btn);
            hbox.setSpacing(50);

            hbox.getChildren().add(split_pane2);

            // Creating scene

            Scene scene = new Scene(new Group(hbox), 1000, 800);
            scene.setFill(Color.GRAY);
            stage.setScene(scene);
            stage.setWidth(2000);
            stage.setHeight(800);


            stage.show();
//            stage.setMaximized(true);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
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

    public void showHideComputerShip(Boolean show , GridPane cGrid) {
        if (show) {
            {
                for (Ships p : computer.shipsArr) {

                    ArrayList<String> got = p.coordinates;

                    for (int i = 0; i < got.size(); i++) {

//                    String s[] = got.get(i).split("");

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

    // Create Grid
    public GridPane createGrid() {
        GridPane gridPane = new GridPane();


        int nRows, nCols;

        for (int i = 0; i < Constants.row + 1; i++) {
            for (int j = 0; j < Constants.col + 1; j++) {
                if (j == 0 && i != Constants.row) {
                    String buttonname = "button" + i + j;
                    Button button = new Button(Integer.toString(i + 1));
                    //button.setEnabled(false);
                    button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                    button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                    button.setDisable(true);
                    gridPane.add(button, j, i);
                } else if (i == Constants.row && j != 0) {

                    if (j == 1) {

                        Button button = new Button("A");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 2) {
                        Button button = new Button("B");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 3) {
                        Button button = new Button("C");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 4) {
                        Button button = new Button("D");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 5) {
                        Button button = new Button("E");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 6) {
                        Button button = new Button("F");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 7) {
                        Button button = new Button("G");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 8) {
                        Button button = new Button("H");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 9) {
                        Button button = new Button("I");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    } else if (j == 10) {
                        Button button = new Button("J");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);

                    } else if (j == 11) {
                        Button button = new Button("K");
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                } else {

                    nRows = i;
                    nCols = j;

                    //ButtonClicks buttonsclk = new ButtonClicks(nRows,nCols);
                    Button button = new Button("-");
                    button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                    button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFFFFF");
                    button.setDisable(true);

                    gridPane.add(button, j, i);

//                    button.setOnAction(event);

                }


            }//inner for
        }//outer for

        return gridPane;
    }

    public boolean checkWinner(Player p1, Player p2) {
        System.out.print("Ships array size is " + p1.shipsArr.size());
        if (p1.shipsArr.size() == 0) {
            Constants.showAlert(p2.name + " won the game!!!");
            return true;
        } else {
            return false;
        }
    }//checkWinner

}//Arena