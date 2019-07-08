import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;  
import javafx.scene.image.ImageView;  
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
public class checkingfx extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Window to Choose Players");
        FileInputStream input= new FileInputStream("battleship.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image); 
        imageView.setTranslateX(-220);
        imageView.setTranslateY(10);
        imageView.setFitHeight(450); 
        imageView.setFitWidth(450); 
        imageView.setPreserveRatio(true); 
        Button btn1 = new Button();
        Button btn2 = new Button();
        btn1.setText("Play with Computer");
        btn2.setText("Play with Another Player");
        btn1.setStyle("-fx-background-color: Skyblue");
        btn2.setStyle("-fx-background-color: Skyblue");
        btn1.setTranslateX(400);
        btn1.setTranslateY(350);
        btn2.setTranslateX(145);
        btn2.setTranslateY(450);
        btn1.setPrefSize(250, 70);
        btn2.setPrefSize(250, 70);
        btn1.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
            	System.out.println("okay boy thats fine");
            	checkingfx2 fx2=new checkingfx2();
            	fx2.start(primaryStage);
            }
        });
        
        
        btn2.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                
            }
        });
        
        
        FlowPane flow = new FlowPane();
        //flow.setPadding(new Insets(10, 10, 10, 10));
        btn1.setLayoutX(350);
        btn1.setLayoutY(350);
        
        flow.setHgap(5);
        flow.getChildren().addAll(btn1, btn2,imageView);
        flow.setStyle("-fx-background-color: Grey");
        primaryStage.setScene(new Scene(flow, 1000, 1000));
        primaryStage.show();
    }
}
