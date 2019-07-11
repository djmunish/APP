import java.awt.Insets;


import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;


public class shipSetupController extends Application {

    static String shipnumname = null;
    static int startend = 0;


    Player humanPlayer;
    Player computer;

    public static void main(String[] args) {
        launch(args);

    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Set Ships for your play!");

        GridPane gridPane = new GridPane();

        StackPane stackpane = new StackPane();

        ArrayList<String> coordarr = new ArrayList<String>();

        ArrayList<String> shipsprocessed = new ArrayList<String>();


        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton(Constants.CARRIER);

        rb1.setToggleGroup(group);
        rb1.setPrefSize(150, 50);
        rb1.setTranslateX(60);
        rb1.setTranslateY(190);

        RadioButton rb2 = new RadioButton(Constants.BATTLESHIP);
        rb2.setToggleGroup(group);
        rb2.setPrefSize(150, 50);
        rb2.setTranslateX(60);
        rb2.setTranslateY(200);

        RadioButton rb3 = new RadioButton(Constants.CRUISER);
        rb3.setToggleGroup(group);
        rb3.setPrefSize(150, 50);
        rb3.setTranslateX(60);
        rb3.setTranslateY(210);

        RadioButton rb4 = new RadioButton(Constants.SUBMARINE);
        rb4.setToggleGroup(group);
        rb4.setPrefSize(150, 50);
        rb4.setTranslateX(60);
        rb4.setTranslateY(220);

        RadioButton rb5 = new RadioButton(Constants.DESTROYER);
        rb5.setToggleGroup(group);
        rb5.setTranslateX(60);
        rb5.setPrefSize(150, 50);
        rb5.setTranslateY(230);

        HBox hbox = new HBox();
        VBox vbox = new VBox();

        vbox.getChildren().add(rb1);
        vbox.getChildren().add(rb2);
        vbox.getChildren().add(rb3);
        vbox.getChildren().add(rb4);
        vbox.getChildren().add(rb5);

        vbox.setSpacing(10);


        Button btnok = new Button("I'm Ready!");
        btnok.setStyle("-fx-background-color: ");
        btnok.setTranslateX(300);
        btnok.setTranslateY(650);
        btnok.setPrefSize(150, 50);

        // Navigation to Arena.
        btnok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Arena a1 = new Arena();
                a1.humanPlayer = humanPlayer;
                a1.computer = computer;
                a1.start(primaryStage);
            }
        });


        btnok.setDisable(true);
        Label l1 = new Label("WELCOME " + humanPlayer.name + " ! SET UP YOUR SHIPS !");
        l1.setTextFill(Color.FLORALWHITE);
        l1.setFont(new Font("Arial", 20));
        Label l2 = new Label("PLEASE SELECT THE SHIP TYPE " + "!");
        l2.setTextFill(Color.FLORALWHITE);
        l2.setFont(new Font("Arial", 20));
        l1.setTranslateX(-330);
        l1.setTranslateY(50);
        l1.setWrapText(true);


        l2.setTranslateX(-700);
        l2.setTranslateY(50);
        l2.setWrapText(true);



        hbox.getChildren().add(vbox);
        hbox.getChildren().add(gridPane);
        hbox.getChildren().add(btnok);
        hbox.getChildren().add(l2);
        hbox.getChildren().add(l1);

        hbox.setSpacing(50);

        // Radio Button Method, Select ship to place.
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n) {


                RadioButton rb = (RadioButton) group.getSelectedToggle();

                if (rb != null) {
                    String s = rb.getText();

                    if (s == Constants.CARRIER) {
                        shipSetupController.shipnumname = "5c";
                        coordarr.clear();
                        gridPane.setDisable(false);

                    } else if (s == Constants.BATTLESHIP) {
                        shipSetupController.shipnumname = "4b";
                        coordarr.clear();
                        gridPane.setDisable(false);
                    } else if (s == Constants.CRUISER) {
                        shipSetupController.shipnumname = "3c";
                        coordarr.clear();
                        gridPane.setDisable(false);
                    } else if (s == Constants.SUBMARINE) {
                        shipSetupController.shipnumname = "3s";
                        coordarr.clear();
                        gridPane.setDisable(false);
                    } else if (s == Constants.DESTROYER) {
                        shipSetupController.shipnumname = "2d";
                        coordarr.clear();
                        gridPane.setDisable(false);
                    }
                }


            }
        });


        //Placement of ships on button click event in grid.
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

                if (humanPlayer.shipsArr.size() < 5) {//1


                    System.out.println("if ship size----" + humanPlayer.shipsArr.size());
                    System.out.println("inside action performed");

                    ButtonClicks buttonok = (ButtonClicks) e.getSource();
                    String xycor = null;


                    System.out.println(buttonok.getCoordX() + ", " + buttonok.getCoordY());
                    xycor = Constants.indexToAlpha.get(Integer.toString(buttonok.getCoordY())) + Integer.toString(buttonok.getCoordX() + 1);
                    coordarr.add(xycor);


                    if (coordarr.size() == 2) {//2

                        if (humanPlayer.shipsArr.size() == 4) {
                            btnok.setDisable(false);
                        }


                        boolean flag1 = false;

                        System.out.println("Inside sending function");
                        
                        String axisxystart0 = coordarr.get(0).substring(0,1);
                        String axisxyend0 = coordarr.get(1).substring(0,1);
                        
                        System.out.println("colll corrrrr====== "+ axisxystart0 + "    === "+ axisxyend0); 
                        
                        String axisxystart1 = coordarr.get(0).substring(1);
                        String axisxyend1 = coordarr.get(1).substring(1);
                        
                        System.out.println("row   corrrrr====== "+ axisxystart1 + "    === "+ axisxyend1);
                        

                        int axisxystartinty = Constants.mapInConstants.get(axisxystart0) + 1;
                        int axisxystartintx = Integer.parseInt(axisxystart1);

                        int axisxyendinty = Constants.mapInConstants.get(axisxyend0) + 1;
                        int axisxyendintx = Integer.parseInt(axisxyend1);


                        System.out.println(shipsprocessed.contains(shipSetupController.shipnumname));

                        if (!shipsprocessed.contains(shipSetupController.shipnumname)) {//3


                            System.out.println("inside shiparray doesnt contain");

                            shipsprocessed.add(shipSetupController.shipnumname);

                            String namenum[] = shipSetupController.shipnumname.split("");


                            if (axisxystartintx == axisxyendintx) {

                                System.out.println("x is equal");

                                System.out.println("SHIPSLOTS--:" + shipSetupController.shipnumname);

                                System.out.println("axisxyendinty :" + axisxyendinty);
                                System.out.println("axisxystartinty :" + axisxystartinty);

                                System.out.println("Y ka difference:--" + Math.abs(axisxyendinty - axisxystartinty));


                                if (Integer.parseInt(namenum[0]) == Math.abs(axisxyendinty - axisxystartinty) + 1) {

                                    System.out.println("Make the flag true");
                                    flag1 = true;
                                }

                            }// if x end

                            else if (axisxystartinty == axisxyendinty) {
                                System.out.println("y is equal");

                                System.out.println("axisxyendintx :" + axisxyendintx);
                                System.out.println("axisxystartintx :" + axisxystartintx);

                                System.out.println("Y ka difference:--" + Math.abs(axisxyendintx - axisxystartintx));

                                if (Integer.parseInt(namenum[0]) == Math.abs(axisxyendintx - axisxystartintx) + 1) {

                                    System.out.println("Make the flag true");
                                    flag1 = true;

                                }
                            }    //else if y end

                            if (flag1) {

                                System.out.println("After making flag true");

                                Ships s = new Ships(coordarr.get(0), coordarr.get(1));


                                if (humanPlayer.checkOverlap(s.coordinates)) {

                                    humanPlayer.shipsArr.add(s);
                                    System.out.println(humanPlayer.shipsArr.size());

                                    System.out.println("sending x and y :" + coordarr.get(0) + " " + coordarr.get(1));

                                    ArrayList<String> colorsh = s.coordinates;
                                    System.out.println("color=====" + s.hexColor);


                                    for (String c1 : colorsh) {

                                        String corsh0 = c1.substring(0,1);
                                        String corsh1 = c1.substring(1);

                                        
                                        int xcor = Integer.parseInt(corsh1);
                                        int ycor = Constants.mapInConstants.get(corsh0) + 1;

                                        ButtonClicks b = (ButtonClicks) getNodeFromGridPane(gridPane, ycor,xcor - 1);
                                        b.setStyle("-fx-background-color:" + s.hexColor);
                                        System.out.println(s.coordinates);
                                        System.out.println(s.shipColor);

                                        coordarr.clear();

                                    }
                                } else {

                                    Constants.showAlert("Please correct the overlapping ship coordinates!");
                                    shipsprocessed.remove(shipSetupController.shipnumname);
                                    coordarr.clear();

                                }

                             

                            }// flagif

                            else {
                                Constants.showAlert("Please select the correct ship coordinates!");

                                shipsprocessed.remove(shipSetupController.shipnumname);
                                coordarr.clear();
                            }


                          

                            buttonok.setVisible(true);


                        } else {

                            Constants.showAlert("This ship is already set, please select the other ship!");

                            coordarr.clear();
                        }
                    }
                }

            }
        };



        // Grid Creation
        int nRows, nCols;

        for (int i = 0; i < Constants.row + 1; i++) {
            for (int j = 0; j < Constants.col + 1; j++) {
                if (j == 0 && i != Constants.row + 1) {

                    if (i == Constants.row) {
                        Button button = new Button();
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setText("-");
                        button.setStyle("-fx-font-size: 2em; ");
                        gridPane.add(button, j, i);
                    } else {
                        Button button = new Button(Integer.toString(i + 1));
                        //button.setEnabled(false);
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    }
                } else if (i == Constants.row && j != 0) {

                    if (j == 1) {

                        Button button = new Button("A");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 2) {
                        Button button = new Button("B");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 3) {
                        Button button = new Button("C");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 4) {
                        Button button = new Button("D");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 5) {
                        Button button = new Button("E");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 6) {
                        Button button = new Button("F");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 7) {
                        Button button = new Button("G");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 8) {
                        Button button = new Button("H");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 9) {
                        Button button = new Button("I");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 10) {
                        Button button = new Button("J");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    } else if (j == 11) {
                        Button button = new Button("K");
                        button.setDisable(true);
                        gridPane.add(button, j, i);
                    }
                } else {

                    nRows = i;
                    nCols = j;

                    ButtonClicks buttonsclk = new ButtonClicks(nRows, nCols);
                    buttonsclk.setText("-");
                    Button button = new Button();


                  
                    gridPane.setDisable(true);

                    gridPane.add(buttonsclk, j, i);
                    buttonsclk.setOnAction(event);


                }


            }//inner for
        }//outer for

        gridPane.setVgap(5);
        gridPane.setHgap(5);

        gridPane.setGridLinesVisible(true);

        Scene scene = new Scene(hbox, 1000, 800);
        hbox.setStyle("-fx-background-color: Grey");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        gridPane.setTranslateX(600);
        gridPane.setTranslateY(200);
        primaryStage.show();
        primaryStage.setMaximized(true);

    }
}