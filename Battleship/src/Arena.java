
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Arena extends Application {

    Player humanPlayer;
    Player computer;

    String selectedAddress;
    public void start(Stage stage) {

        try {

            // set title for the stage
            stage.setTitle("LET'S PLAY");
            stage.setWidth(400);
            stage.setHeight(180);

            HBox hbox = new HBox(70);
            hbox.setTranslateX(20);
            hbox.setTranslateY(20);

            Label right=new Label(humanPlayer.name);

            right.setFont(new Font("Arial", 30));

            Label left=new Label("COMPUTER");
            left.setFont(new Font("Arial", 30));

            left.setPrefHeight(50);
            EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    System.out.println("button pressed");
                }
            };

            // create split pane 1

            SplitPane split_pane1 = new SplitPane();
            split_pane1.setOrientation(Orientation.VERTICAL);
            split_pane1.setPrefSize(300, 40);

            GridPane playerGrid = createGrid();

            split_pane1.getItems().addAll(createGrid(), playerGrid, right);

            hbox.getChildren().add(split_pane1);

            // create split pane 2

            SplitPane split_pane2 = new SplitPane();
            split_pane2.setPrefSize(300, 400);
            split_pane2.setOrientation(Orientation.VERTICAL);

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
                    selectedAddress = t1;
                }
            });

            Button btn = new Button();
            btn.setText("Hit");
            btn.setStyle("-fx-border-color: #000000 ; -fx-border-width: 3px;");
            btn.setStyle("-fx-border-color: #000000; -fx-background-color: #CD853F");
            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    humanPlayer.updateDropdown(selectedAddress,humanPlayer.inputs);
                    inputComboBox.getItems().remove(selectedAddress);
                    inputComboBox.setPromptText("Select Location");
                    System.out.println("chako == " + selectedAddress);
                    String s = computer.randomhitcomp();
                    System.out.println("computerinputs are: "+ computer.inputs);
                    System.out.println("computerhit is"+ s);
                    computer.inputs.remove(s);
                    System.out.println("computerinputs updated are: "+ computer.inputs);
                }
            });



            // HIT / MISS


            for(Ships p : humanPlayer.shipsArr){

                ArrayList<String> got = p.coordinates;

                for(int i= 0; i< got.size();i++){

                    String s[] = got.get(i).split("");
                    int x = Constants.mapInConstants.get(s[0]);	//c
                    int y = Integer.parseInt(s[1]);	//r
                    Button b = (Button) getNodeFromGridPane(playerGrid,x+1,y);
                    b.setStyle( "-fx-background-color:"+p.hexColor);
                }
            }

            split_pane2.getItems().addAll(createGrid(),createGrid(),left);
            hbox.getChildren().add(inputComboBox);
            hbox.getChildren().add(btn);
            hbox.setSpacing(50);

            hbox.getChildren().add(split_pane2);

            // Creating scene

            Scene scene = new Scene(new Group(hbox), 800, 800);
            scene.setFill(Color.GRAY);
            stage.setScene(scene);
            stage.setWidth(1000);
            stage.setHeight(800);


            stage.show();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
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

    // Create Grid
    public GridPane createGrid(){
        GridPane gridPane = new GridPane();


        int nRows,nCols;

        for(int i=0;i<Constants.row+1;i++){
            for(int j=0;j<Constants.col+1;j++){
                if(j==0 && i!=Constants.row){
                    String buttonname="button"+i+j;
                    Button button  = new Button(Integer.toString(i+1));
                    //button.setEnabled(false);
                    button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                    button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                    button.setDisable(true);
                    gridPane.add(button, j, i);
                }
                else if (i==Constants.row && j!=0){

                    if(j==1){

                        Button	button  = new Button("A");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==2){
                        Button	button  = new Button("B");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==3){
                        Button	button  = new Button("C");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==4){
                        Button	button  = new Button("D");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==5){
                        Button	button  = new Button("E");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==6){
                        Button	button  = new Button("F");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==7){
                        Button	button  = new Button("G");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==8){
                        Button	button  = new Button("H");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==9){
                        Button	button  = new Button("I");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                    else if(j==10){
                        Button	button  = new Button("J");
                        //button.setEnabled(false);
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);

                    }

                    else if(j==11){
                        Button	button  = new Button("K");
                        button.setDisable(true);
                        button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                        button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                        gridPane.add(button, j, i);
                    }
                }

                else{

                    nRows = i;
                    nCols = j;

                    //ButtonClicks buttonsclk = new ButtonClicks(nRows,nCols);
                    Button	button  = new Button("-");
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




}