import java.awt.Color;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
//import javafx.scene.paint.Color;

 
public class checkingfx2 extends Application {
	
	static int shipnum=0;
	static int startend=0;
	
	Player humanPlayer;
	
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
        primaryStage.setTitle("Window to program the play area");
        
        GridPane gridPane=new GridPane();
        
        StackPane stackpane=new StackPane();
        
        ArrayList<String> coordarr=new ArrayList<String>();
       
        //Setting size for the pane  
        //gridPane.setMinSize(400, 200);  
        //Text text1 = new Text("Email");       
        //creating label password 
        //Text text2 = new Text("Password"); 
        //Setting the padding  
        // gridPane.setPadding(new Insets(10, 10, 10, 10)); 
        
        
        final ToggleGroup group = new ToggleGroup();

        RadioButton rb1 = new RadioButton("carrier");
        rb1.setToggleGroup(group);
        //rb1.setSelected(true);

        RadioButton rb2 = new RadioButton("Battleship");
        rb2.setToggleGroup(group);

        RadioButton rb3 = new RadioButton("Cruiser");
        rb3.setToggleGroup(group);

        RadioButton rb4 = new RadioButton("Submarine");
        rb4.setToggleGroup(group);

        RadioButton rb5 = new RadioButton("Destroyer");
        rb5.setToggleGroup(group);
        
        HBox hbox = new HBox();
        VBox vbox = new VBox();

        vbox.getChildren().add(rb1);
        vbox.getChildren().add(rb2);
        vbox.getChildren().add(rb3);
        vbox.getChildren().add(rb4);
        vbox.getChildren().add(rb5);
        
        vbox.setSpacing(10);


        Button btnok=new Button("OK");
        
        btnok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        btnok.setDisable(true);
        
        hbox.getChildren().add(vbox);
        hbox.getChildren().add(gridPane);
        hbox.getChildren().add(btnok);
        hbox.setSpacing(50);
        //hbox.setPadding(new Insets(200, 10, 10, 20));

        //stackpane.getChildren().add(hbox);
        
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)  {


                RadioButton rb = (RadioButton)group.getSelectedToggle();

                if (rb != null) {
                    String s = rb.getText();

                    if(s=="carrier"){
                    	checkingfx2.shipnum=5;
                    }
                    else if(s=="Battleship"){
                    	checkingfx2.shipnum=4;
                    }
                    else if(s=="Cruiser"){
                    	checkingfx2.shipnum=3;
                    }
                    else if(s=="Submarine"){
                    	checkingfx2.shipnum=3;
                    }
                    else if(s=="Destroyer"){
                    	checkingfx2.shipnum=2;
                    }
                    
                    // change the label
                    //System.out.println(s + " selected");
                }

               
            }
        });
        
             
        
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	System.out.println(humanPlayer.name);
            	
            	if(humanPlayer.shipsArr.size() < 5){
            		
            	
            	System.out.println("inside action performed");
            	
            	
                ButtonClicks buttonok = (ButtonClicks) e.getSource();  
                String xycor=null;
                
                buttonok.setText(buttonok.getCoordX() + ", " + buttonok.getCoordY());
                        
                System.out.println(buttonok.getCoordX() + ", " + buttonok.getCoordY());
                xycor=Constants.indexToAlpha.get(Integer.toString(buttonok.getCoordY()))+Integer.toString(buttonok.getCoordX());
                coordarr.add(xycor);
                              
                if(coordarr.size()==2){
                	
                	System.out.println("Inside sending function");
                	
                	Ships s = new Ships(coordarr.get(0),coordarr.get(1));
                               		
                	System.out.println("sending x and y :"+coordarr.get(0)+" "+ coordarr.get(1));
                	
                	ArrayList<String> colorsh=s.coordinates;
                	System.out.println("color====="+s.hexColor);
                	
                	for(String c1:colorsh){
                		
						String corsh[]=c1.split("");
						int xcor=Integer.parseInt(corsh[1]);
						int ycor=Constants.mapInConstants.get(corsh[0])+1;
						
						ButtonClicks b=(ButtonClicks)getNodeFromGridPane(gridPane,ycor,xcor);
						b.setStyle( "-fx-background-color:"+s.hexColor);
						
                	}
                	
                	
                	System.out.println(s.coordinates);
                	System.out.println(s.shipColor);
                	              	
                	//s.setupShip( ,coordarr.get(1));
                	      
                	coordarr.clear();
                	
                }//if end
                
                
                               
               // System.out.println("abc"+buttonok.getCoordX() + ", " + buttonok.getCoordY());
               
                buttonok.setVisible(true);
                               
            } 
            	
                
            else{
            	
            	btnok.setDisable(false);
            	
            }
            
            }
        };
        
        
		int nRows,nCols;
		
		for(int i=0;i<Constants.row+1;i++){
			for(int j=0;j<Constants.col+1;j++){
				if(j==0 && i!=Constants.row){
					String buttonname="button"+i+j;
					Button	button  = new Button(Integer.toString(i+1));
					//button.setEnabled(false);
					button.setDisable(true);
					gridPane.add(button, j, i);					
				}
				else if (i==Constants.row && j!=0){
					
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
		           
		            ButtonClicks buttonsclk = new ButtonClicks(nRows,nCols);
		            Button	button  = new Button();
		            
		            gridPane.add(buttonsclk, j, i);
		            
		            
		            buttonsclk.setOnAction(event);
		            
		         				
					//buttonsclk.addActionListener(this);
				}
				
				
			}//inner for
		}//outer for
				
        gridPane.setVgap(5); 
        gridPane.setHgap(5); 
        gridPane.setGridLinesVisible(true);
            
            
        //Setting the Grid alignment 
        //gridPane.setAlignment(Pos.TOP_CENTER); 
        Scene scene = new Scene(hbox, 1000, 800);
        hbox.setStyle("-fx-background-color: Grey");
        primaryStage.setScene(scene);
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        //gridPane.setAlignment(Pos.CENTER_RIGHT);
        gridPane.setTranslateX(160);
        gridPane.setTranslateY(160);
        primaryStage.show();    
    }        
}