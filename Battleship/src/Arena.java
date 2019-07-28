
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
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

public class Arena extends Application {

    Player humanPlayer;
    Player computer;
    long startTime;
    long finishTime;
    String selectedAddress;
    public Button hitBtn;
    public ComboBox inputComboBox;
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
            GridPane playerGrid = createGrid(Constants.row + 1,Constants.col + 1,false);
            GridPane playerRefGrid = createGrid(Constants.row + 1,Constants.col + 1,false);




            GridPane compGrid = createGrid(Constants.row + 1,Constants.col + 1,false);
            GridPane compRefGrid = createGrid(Constants.row + 1,Constants.col + 1,false);
            split_pane1.getItems().addAll(playerRefGrid, playerGrid, right);
            hbox.getChildren().add(split_pane1);

            // create split pane 2
            SplitPane split_pane2 = new SplitPane();
            split_pane2.setPrefSize(500, 500);
            split_pane2.setOrientation(Orientation.VERTICAL);
            split_pane2.getItems().addAll(compRefGrid, compGrid, left);

            inputComboBox = new ComboBox();
            inputComboBox.setPromptText("Select Location");
            inputComboBox.setStyle("-fx-border-color: #000000 ; -fx-border-width: 3px;");
            inputComboBox.setStyle("-fx-border-color: #000000 ; -fx-background-color: #CD853F;");
            inputComboBox.getItems().addAll(
               humanPlayer.inputs
            );



            hitBtn = new Button();
            hitBtn.setText("Hit");
            hitBtn.setStyle("-fx-border-color: #000000 ; -fx-border-width: 3px;");
            hitBtn.setStyle("-fx-border-color: #000000; -fx-background-color: #CD853F");
            if(humanPlayer.gamePlayType){
                hitBtn.setDisable(true);
            }
            hitBtn.setOnAction(new EventHandler<ActionEvent>() {

                	@Override
                	public void handle(ActionEvent event) {
                		boolean flag3 = false;
                		if (humanPlayer.gamePlayType) {
                			Iterator<String> it = humanPlayer.salvaArr.iterator();
                			while(it.hasNext()) {
                				String s = it.next();
                				humanPlayer.updateDropdown(s, humanPlayer.inputs);
                				Ships.colorButton(playerRefGrid, compGrid, s, Arena.this, computer);
                			}  			
                		}
                		else {
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
                			 }else {
                             	String serror = "Please select a location and then click 'Hit' Button!";
                                 	Constants.showAlert(serror);
                             }
                		}//else gamePlayType

                    	boolean flag2 = checkWinner(computer, humanPlayer);
                    	if (!flag2) {
                    		if (humanPlayer.gamePlayType) {
                    			for(int i = 0;i<5;i++) {
                    				String s = computer.randomhitcompai(humanPlayer);
                        			System.out.println("computerhit is == " + s);
                        			boolean flag1 = Ships.colorButton(playerGrid, compRefGrid, s, Arena.this, humanPlayer);
                    			}//for	
                    		}else {
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
                    		}//else

                    		flag3 = checkWinner(humanPlayer, computer);
                    		if(flag3) {
                    			Constants.showAlert(computer.name + " won the game!!!");}
                    	}else {
                    		finishTime = System.currentTimeMillis();
                    		long elapsedtime = finishTime - startTime;
                    		String score  = calcScore(elapsedtime);
                    		Constants.showAlert(humanPlayer.name + " won the game!!!" + "\nYour score is " + score);
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


            if(humanPlayer.gamePlayType) {
                GridPane salvaGrid = createGrid(1, 5, true);
                vbox.getChildren().add(salvaGrid);



                inputComboBox.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {
                        if(humanPlayer.gamePlayType){
                            updateSalvaGRid(salvaGrid,t1);
                        }
                    }
                });



            }
            vbox.setSpacing(10);
            hbox.getChildren().add(vbox);
            hbox.getChildren().add(hitBtn);
            vbox.setPrefWidth(250);



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
    public GridPane createGrid(int rows, int col, Boolean isSalva) {
        GridPane gridPane = new GridPane();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j <  col; j++) {
                if(!isSalva){
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

                    }else {

                        Button button = new Button("-");
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFFFFF");
                        button.setDisable(true);
                        button.setPrefSize(40, 15);

                        gridPane.add(button, j, i);
                    }
                    }
                    else {
                        Button button = new Button("-");
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFFFFF");
                        button.setDisable(true);
                        button.setPrefSize(100, 15);
                        button.setOnAction(new EventHandler<ActionEvent>() {
                           @Override
                           public void handle(ActionEvent event) {


                               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                               alert.setTitle("Select");
                               alert.setHeaderText("Do you wish to remove this in Salva entry?");
                               ButtonType yes = new ButtonType("Yes");
                               ButtonType no = new ButtonType("No");

                               // Remove default ButtonTypes
                               alert.getButtonTypes().clear();
                               alert.getButtonTypes().addAll(yes, no);
                               Optional<ButtonType> option = alert.showAndWait();

                               if (option.get() == yes) {
                                   System.out.println(button.getText());
                                   humanPlayer.salvaArr.remove(button.getText());
                                   button.setDisable(true);

                                   humanPlayer.inputs.add(button.getText());
//                                   inputComboBox.getItems().add(button.getText());
//                                   inputComboBox.setPromptText("Select Location");
                                   hitBtn.setDisable(true);
//                                   Collections.sort(inputComboBox.getItems());
                                   button.setText("-");

                               }
                           }
                           });

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



    public void updateSalvaGRid(GridPane g, String inp){
        if (humanPlayer.salvaArr.size() < 5) {

            humanPlayer.salvaArr.add(inp);
            System.out.println(humanPlayer.salvaArr);

            Button b = (Button) getNodeFromGridPane(g, humanPlayer.salvaArr.size() - 1, 0);
            b.setText(inp);
            b.setDisable(false);

//            humanPlayer.updateDropdown(inp, humanPlayer.inputs);
//            System.out.println(inp);
//
//            inputComboBox.getItems().remove(inp);
//            inputComboBox.setPromptText("Select Location");

            hitBtn.setDisable(!(humanPlayer.salvaArr.size() == 5));
        }

    }

    public boolean checkWinner(Player p1, Player p2) { //Function to check the winner of the game after every hit by each player
        System.out.print("Ships array size is " + p1.shipsArr.size());
        if (p1.shipsArr.size() == 0) {
           // Constants.showAlert(p2.name + " won the game!!!");
            return true;
        } else {
            return false;
        }
    }//checkWinner
    
    public String calcScore(long elapsedtime) {
    	System.out.println("elapsed time: " + elapsedtime);
    	double minutes = (double)elapsedtime/60000; 
    	double scorecalc = (1/minutes)*100;
    	DecimalFormat d = new DecimalFormat("#.###");
    	System.out.print(d.format(scorecalc));
    	System.out.print("score is "+ scorecalc);
    	System.out.println("Time taken by player is " + Double.toString(minutes)); 	
    	return d.format(scorecalc);
    }

}//Arena