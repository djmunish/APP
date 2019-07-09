import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class test extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> Platform.exit());


        VBox root = new VBox();
        root.getChildren().add(exitBtn);

        Scene scene = new Scene(root, 300, 50);
        stage.setScene(scene);
        stage.setTitle("Hello JavaFX Application with a Scene");
        stage.show();
    }
}