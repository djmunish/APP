import java.awt.Insets;

import javax.swing.JButton;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class checkingfx2 extends Application {
	
    public static void main(String[] args) {
        launch(args);
        
    }
  
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Window to program the play area");
        
        GridPane gridPane=new GridPane();
        //Setting size for the pane  
        //gridPane.setMinSize(400, 200); 
         
        //Text text1 = new Text("Email");       
        //creating label password 
        //Text text2 = new Text("Password"); 
        //Setting the padding  
        // gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                System.out.println("button pressed");
            } 
        };
  		
		int nRows,nCols;
		
		for(int i=0;i<Constants.row;i++){
			for(int j=0;j<Constants.col+1;j++){
				if(j==0 && i!=Constants.row-1){
					String buttonname="button"+i+j;
					Button	button  = new Button(Integer.toString(i+1));
					//button.setEnabled(false);
					button.setDisable(true);
					gridPane.add(button, j, i);					
				}
				else if (i==Constants.row-1 && j!=0){
					
					if(j==1){
						
						Button	button  = new Button("A");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);	
					}
					else if(j==2){
						Button	button  = new Button("B");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);	
					}
					else if(j==3){
						Button	button  = new Button("C");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);	
					}
					else if(j==4){
						Button	button  = new Button("D");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);
					}
					else if(j==5){
						Button	button  = new Button("E");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);	
					}
					else if(j==6){
						Button	button  = new Button("F");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);	
					}
					else if(j==7){
						Button	button  = new Button("G");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);	
					}
					else if(j==8){
						Button	button  = new Button("H");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);	
					}						
					else if(j==9){
						Button	button  = new Button("I");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);	
					}
					else if(j==10){
						Button	button  = new Button("J");
						//button.setEnabled(false);
						button.setDisable(true);
						gridPane.add(button, j, i);
					}
					
					else if(j==11){
						Button	button  = new Button("K");
						button.setDisable(true);
						gridPane.add(button, j, i);
					}
				}
				
				else{
						
		            nRows = i;
		            nCols = j;
		           
		            //ButtonClicks buttonsclk = new ButtonClicks(nRows,nCols);
		            Button	button  = new Button();
		            
		            gridPane.add(button, j, i);
		            
		            button.setOnAction(event);
		            
		         				
					//buttonsclk.addActionListener(this);
				}
				
				
			}//inner for
		}//outer for
		
 
        
       // gridPane.add(text1, 0, 0);
       // gridPane.add(text2, 0, 1);
       //Setting the vertical and horizontal gaps between the columns 
		
        gridPane.setVgap(5); 
        gridPane.setHgap(5); 
        gridPane.setGridLinesVisible(true);
                
        //Setting the Grid alignment 
        gridPane.setAlignment(Pos.TOP_CENTER); 
       
        primaryStage.setScene(new Scene(gridPane, 300, 250));
        primaryStage.show();    
    }        
}