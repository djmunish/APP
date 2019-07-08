
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class Arena extends Application {

    public void start(Stage stage) {

        try {

            // set title for the stage
            stage.setTitle("Split Pane");

            HBox hbox = new HBox(20);
            hbox.setTranslateX(20);
            hbox.setTranslateY(20);

            // create a splitpane
            SplitPane split_pane1 = new SplitPane();
            split_pane1.setOrientation(Orientation.VERTICAL);
            split_pane1.setPrefSize(400, 400);

            Label label1 = new Label("1");
            label1.setPrefHeight(200);
            Label label2 = new Label("2");
            label2.setPrefHeight(200);
            split_pane1.getItems().addAll(label1, label2);

            hbox.getChildren().add(split_pane1);

            SplitPane split_pane2 = new SplitPane();
            split_pane2.setPrefSize(400, 400);
            split_pane2.setOrientation(Orientation.VERTICAL);

            Label label3 = new Label("3");
            label3.setPrefHeight(200);
            Label label4 = new Label("4");
            label4.setPrefHeight(200);

            split_pane2.getItems().addAll(label3, label4);


            hbox.getChildren().add(split_pane2);

            Scene scene = new Scene(new Group(hbox), 400, 800);
            scene.setFill(Color.GHOSTWHITE);
            stage.setScene(scene);
            stage.setTitle("SplitPane");

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
}