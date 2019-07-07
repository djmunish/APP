import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class dummyfx extends Application {

    Player p1;
    String selectedAddress;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drop!");
        p1 = new Player();
        p1.createInputs();

        final ComboBox emailComboBox = new ComboBox();
        emailComboBox.getItems().addAll(
                p1.inputs
        );

        emailComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                selectedAddress = t1;
            }
        });

        Button btn = new Button();
        btn.setText("Hit");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("chako ==" + selectedAddress);
            }
        });



        StackPane root = new StackPane();
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));

        grid.add(emailComboBox, 1, 0);
        grid.add(btn, 2, 0);

        root.getChildren().add(grid);

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

}
