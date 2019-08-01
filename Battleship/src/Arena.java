
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import javafx.scene.text.Text;

/**
 * Arena.java deals with the game arena, it records the actions on UI and performs the computation.
 * @author harshkour
 * @since 2019-07-06
 * @version 1.0.0
 */
public class Arena extends Application {

    Player humanPlayer;
    Player computer;
    long startTime;
    long finishTime;
    String selectedAddress;
    public Button hitBtn;
    public ComboBox inputComboBox;  

    int index = 0;
    GridPane salvaGrid;
    int salvaWindow = 5;
    
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;
    boolean timerstop = false;
    private final Text text = new Text((hours < 10 ? "0" : "")+Integer.toString(hours)+":"+ (minutes < 10 ? "0" : "")+Integer.toString(minutes)+":"+(seconds < 10 ? "0" : "")+Integer.toString(seconds));
   
    /**
     * Function to increment the timer.
     */
    private void incrementCount() {
    	if(!timerstop) {
    		seconds++;
    		if(seconds > 59) {
    			seconds = 0;
    			minutes = minutes + 1;
    		}
    		if(minutes > 59) {
    			minutes = 0;
    			hours = hours + 1;
    		}
    	}
        text.setText((hours < 10 ? "0" : "")+Integer.toString(hours)+":"+ (minutes < 10 ? "0" : "")+Integer.toString(minutes)+":"+(seconds < 10 ? "0" : "")+Integer.toString(seconds));
    }

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
            text.resize(150, 40);
            text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            //text.setUnderline(true);
            text.setTranslateX(-130);
            hbox.getChildren().add(text);
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
            
            // long running operation runs on different thread
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    Runnable updater = new Runnable() {

                        @Override
                        public void run() {
                            incrementCount();
                        }
                    };

                    while (true) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }

                        // UI update is run on the Application thread
                        Platform.runLater(updater);
                    }
                }

            });
            // don't let thread prevent JVM shutdown
            thread.setDaemon(true);
            thread.start();



            if(humanPlayer.gamePlayType) {
                salvaGrid = createGrid(1, salvaWindow, true);
                vbox.getChildren().add(salvaGrid);
               
                inputComboBox.valueProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue ov, String t, String t1) {
                        if(t1 == null && humanPlayer.salvaArr.size()<salvaWindow){
                            hitBtn.setDisable(true);
                        }
                        else{
                            hitBtn.setDisable(false);
                        }
                    }
                });
                if(humanPlayer.salvaArr.size()<salvaWindow){
                	System.out.println("here when salvarr < window---1");
                    hitBtn.setText("OK");
                    hitBtn.setDisable(false);

                    hitBtn.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {

                            if(humanPlayer.salvaArr.size()<salvaWindow){
                            	System.out.println("here when salvarr < window---2");
                                  updateSalvaGRid(salvaGrid,inputComboBox.getValue().toString());
                            }
                            else{
                            	System.out.println("here when salvarr < window----else");
                            	boolean flag3 = false;
                            	Iterator<String> it = humanPlayer.salvaArr.iterator();
                    			while(it.hasNext()) {
                    				String s = it.next();
                    				humanPlayer.updateDropdown(s, humanPlayer.inputs);
                    				boolean flag = Ships.colorButton(playerRefGrid, compGrid, s, Arena.this, computer);
                    				if(flag) {
                    					humanPlayer.hitscount++;
                    				}else {
                    					humanPlayer.misscount++;
                    				}
                    			} 
                                clearSalvaAfterHit(salvaGrid);
                                hitBtn.setText("OK");
                                index=0;
                                boolean flag2 = checkWinner(computer, humanPlayer);
                                if (!flag2) {
                                	for(int i = 0;i<salvaWindow;i++) {
                        				String s = computer.randomhitcompai(humanPlayer, i+1 , salvaWindow);
                            			System.out.println("computerhit is == " + s);
                            			boolean flag1 = Ships.colorButton(playerGrid, compRefGrid, s, Arena.this, humanPlayer);
                            			flag3 = checkWinner(humanPlayer, computer);
                                		if(flag3) {
                                			timerstop = true;
                                			finishTime = System.currentTimeMillis();
                                    		long elapsedtime = finishTime - startTime;
                                    		String score  = calcScore(elapsedtime, humanPlayer);
                                			Constants.showAlert(computer.name + " won the game!!!" + "\nYour score is " + score);
                                			break;
                                			}
                                		
                        			}//for
                                }//not flag2
                                else { //Human Player won the game!
                            		finishTime = System.currentTimeMillis();
                            		long elapsedtime = finishTime - startTime;
                            		System.out.println("elspsed time is : " + elapsedtime + " finishtime is :"+ finishTime + " start time is: "+startTime);
                            		String score  = calcScore(elapsedtime, humanPlayer);
                            		timerstop = true;
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

                            	}// if any player has won the game
                                 //No player won the game after the hits
                                int shipcount  = computer.shipsArr.size(); //check if any ship of computer is down
                                System.out.println("\n \n computer ship count is " + shipcount);
                                if(shipcount < salvaWindow) {
                                	salvaWindow = shipcount;
                                	System.out.println("\n \n Window size updated to " + salvaWindow);
                                	
                                	//update UI
//
                                    salvaGrid = createGrid(1, salvaWindow, true);

                                    vbox.getChildren().remove(1);
                                    vbox.getChildren().add(1,salvaGrid);

                                }
                                
                                
                            }//else
                        }
                    });
                }
            }
            else {
            	hitBtn.setOnAction(new EventHandler<ActionEvent>() {

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
                					humanPlayer.misscount++;
                				}else {
                					humanPlayer.hitscount++;
                				}
                				Constants.showAlert(message);
                			 }else {
                                 	Constants.showAlert(Constants.hit_Alert);
                             }
                		//}//else gamePlayType

                    	boolean flag2 = checkWinner(computer, humanPlayer);
                    	if (!flag2) {
                    			String s = computer.randomhitcompai(humanPlayer, 0, 0);
                    			System.out.println("computerhit is == " + s);
                    			boolean flag1 = Ships.colorButton(playerGrid, compRefGrid, s, Arena.this, humanPlayer);
                    			String messageComp;
                    			if (flag1) {
                    				messageComp = "It was a hit by Computer at " + s;
                    			} else {
                    				messageComp = "Wohoo!! Computer missed the shot and hit you at " + s;
                    			}
                    			Constants.showAlert(messageComp);
                    		//}//else

                    		flag3 = checkWinner(humanPlayer, computer);
                    		if(flag3) {
                    			timerstop = true;
                    			finishTime = System.currentTimeMillis();
                        		long elapsedtime = finishTime - startTime;
                        		String score  = calcScore(elapsedtime, humanPlayer);
                    			Constants.showAlert(computer.name + " won the game!!!" + "\nYour score is " + score);}
                    	}else {
                    		finishTime = System.currentTimeMillis();
                    		long elapsedtime = finishTime - startTime;
                    		String score  = calcScore(elapsedtime, humanPlayer);
                    		timerstop = true;
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
            }
            vbox.setSpacing(10);
           // vbox1.setTranslateX(-420);
            //vbox1.setSpacing(-100);
           // hbox.getChildren().add(vbox1);
            hbox.getChildren().add(vbox);
            hbox.getChildren().add(hitBtn);
            vbox.setPrefWidth(250);



            hbox.setSpacing(10);
            hbox.getChildren().add(split_pane2);



            // Creating scene
            Scene scene = new Scene(new Group(hbox), 1000, 800);
            scene.setFill(Color.GRAY);
            stage.setScene(scene);
            stage.setWidth(1000);
            stage.setHeight(800);


            stage.show();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Function to fetch each node from the grid.
     * @param gridPane Grid for which node value needs to be returned.
     * @param col column value for the grid.
     * @param row row value on the grid.
     * @return the node of the grid.
     */
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
    
    /**
     * Function to Show/Hide Computer Ships on the Play Arena
     * @param show true if ships needs to be visible on the Arena else false.
     * @param cGrid Input grid on which ships for the computer needs to be shown.
     */
    public void showHideComputerShip(Boolean show , GridPane cGrid) { 
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

                                   index = humanPlayer.salvaArr.indexOf(button.getText());
                                   humanPlayer.salvaArr.remove(button.getText());

                                   button.setDisable(true);
                                   humanPlayer.inputs.add(button.getText());
                                   inputComboBox.getItems().add(button.getText());
                                   Collections.sort(inputComboBox.getItems());
                                   button.setText("-");
                                   inputComboBox.setPromptText("Select Location");
                                   hitBtn.setText("OK");

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
            gridPane.setPrefSize(col*100, 20);
        }
        return gridPane;
    }



    public void updateSalvaGRid(GridPane g, String inp){
        if (humanPlayer.salvaArr.size() < salvaWindow && inp != null) {

            humanPlayer.salvaArr.add(index,inp);
            System.out.println(humanPlayer.salvaArr);


            Button b = (Button) getNodeFromGridPane(g, index, 0);
            b.setText(inp);
            b.setDisable(false);

            humanPlayer.updateDropdown(inp, humanPlayer.inputs);
            System.out.println(inp);

            inputComboBox.getItems().remove(inp);
            inputComboBox.setPromptText("Select Location");
            index ++ ;

            hitBtn.setText(humanPlayer.salvaArr.size() == salvaWindow ? "Hit" : "OK");
        }
    }


    public void clearSalvaAfterHit(GridPane g){
        for(int i = 0 ; i < salvaWindow; i++){
            Button b = (Button) getNodeFromGridPane(g, i, 0);
            b.setText("-");
            b.setDisable(true);
        }
        humanPlayer.salvaArr.clear();
    }
    
    /**
     * Function to check the winner of the game after every hit by player/computer.
     * @param p1 Player who has been hit.
     * @param p2 Player who did hit.
     * @return true if there is a winner of the game i.e. all ships are down else return false.
     */
    public boolean checkWinner(Player p1, Player p2) { 
        System.out.println("Ships array size is " + p1.shipsArr.size());
        System.out.println("Ships array is " + p1.shipsArr);
        if (p1.shipsArr.size() == 0) {
           // Constants.showAlert(p2.name + " won the game!!!");
            return true;
        } else {
            return false;
        }
    }//checkWinner
    
    /**
     * Function to calculate score for each game play.
     * @param elapsedtime - time taken by player for the entire game play
     * @return the calculated score in the form of a String.
     */
    public String calcScore(long elapsedtime, Player human) {
    	System.out.println("elapsed time: " + elapsedtime);
    	double minutes = (double)elapsedtime/60000; 
    	System.out.println("Time taken by player is " + Double.toString(minutes)); 
    	double scorecalc = ((1/minutes)*100) + (human.hitscount * 10) - (human.misscount * 1);
    	DecimalFormat d = new DecimalFormat("#.###");
    	System.out.println("score is "+ d.format(scorecalc));
    	return d.format(scorecalc);
    }

}//Arena