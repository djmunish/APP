
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Arena extends Application {

    String selectedAddress;
    public void start(Stage stage) {

        try {

            // set title for the stage
            stage.setTitle("LET'S PLAY");

            HBox hbox = new HBox(70);
            hbox.setTranslateX(20);
            hbox.setTranslateY(20);

            //Creating Grid



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

            split_pane1.getItems().addAll(createGrid(),createGrid());

            hbox.getChildren().add(split_pane1);

            // create split pane 2

            SplitPane split_pane2 = new SplitPane();
            split_pane2.setPrefSize(300, 400);
            split_pane2.setOrientation(Orientation.VERTICAL);




            Player p1 = new Player();
            p1.createInputs();

            final ComboBox inputComboBox = new ComboBox();
            inputComboBox.setStyle("-fx-border-color: #000000 ; -fx-border-width: 3px;");
            inputComboBox.setStyle("-fx-border-color: #000000 ; -fx-background-color: #CD853F;");
            inputComboBox.getItems().addAll(
                    p1.inputs
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
                    System.out.println("chako == " + selectedAddress);
                }
            });



            split_pane2.getItems().addAll(createGrid(),createGrid());
            hbox.getChildren().add(inputComboBox);
            hbox.getChildren().add(btn);
            hbox.setSpacing(50);

            hbox.getChildren().add(split_pane2);

            // Creating scene

            Scene scene = new Scene(new Group(hbox), 800, 800);
            scene.setFill(Color.YELLOWGREEN);
            stage.setScene(scene);


            stage.show();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
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

        for(int i=0;i<Constants.row;i++){
            for(int j=0;j<Constants.col+1;j++){
                if(j==0 && i!=Constants.row-1){
                    String buttonname="button"+i+j;
                    Button button  = new Button(Integer.toString(i+1));
                    //button.setEnabled(false);
                    button.setStyle("-fx-border-color: #000000 ; -fx-border-width: 2px;");
                    button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFD700");
                    button.setDisable(true);
                    gridPane.add(button, j, i);
                }
                else if (i==Constants.row-1 && j!=0){

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
                    button.setStyle("-fx-border-color: #000000; -fx-background-color: #FFDAB9");
                    button.setDisable(true);

                    gridPane.add(button, j, i);

//                    button.setOnAction(event);

                }


            }//inner for
        }//outer for

        return gridPane;
    }
}