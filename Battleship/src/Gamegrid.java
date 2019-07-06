import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;

public class Gamegrid extends JFrame{
	
	Gamegrid(){
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
			JFrame frame = new JFrame("Game Grid");
			JPanel panel1= new JPanel();
			JPanel panel2= new JPanel();
			JPanel panel3= new JPanel();
			
			panel1.setLayout( new GridLayout(Constants.row,Constants.col));
			panel2.setLayout( new BorderLayout());
			panel3.setLayout( new BorderLayout());
									
								
			frame.setSize(800,800);  
			frame.setVisible(true);
			
			
			for(int i=0;i<Constants.row;i++){
				for(int j=0;j<Constants.col+1;j++){
					if(j==0 && i!=Constants.row-1){
						JButton	button  = new JButton(Integer.toString(i+1));
						button.setEnabled(false);
						panel1.add(button);					
					}
					else if (i==Constants.row-1 && j!=0){
						
						if(j==1){
							
						JButton	button  = new JButton("A");
						button.setEnabled(false);
						panel1.add(button);
						}
						else if(j==2){
							JButton	button  = new JButton("B");
							button.setEnabled(false);
							panel1.add(button);
						}
						else if(j==3){
							JButton	button  = new JButton("C");
							button.setEnabled(false);
							panel1.add(button);
						}
						else if(j==4){
							JButton	button  = new JButton("D");
							button.setEnabled(false);
							panel1.add(button);
						}
						else if(j==5){
							JButton	button  = new JButton("E");
							button.setEnabled(false);
							panel1.add(button);
						}
						else if(j==6){
							JButton	button  = new JButton("F");
							button.setEnabled(false);
							panel1.add(button);
						}
						else if(j==7){
							JButton	button  = new JButton("G");
							button.setEnabled(false);
							panel1.add(button);
						}
						else if(j==8){
							JButton	button  = new JButton("H");
							button.setEnabled(false);
							panel1.add(button);
						}						
						else if(j==9){
							JButton	button  = new JButton("I");
							button.setEnabled(false);
							panel1.add(button);
						}
						else if(j==10){
							JButton	button  = new JButton("J");
							button.setEnabled(false);
							panel1.add(button);
						}
						
						else if(j==11){
							JButton	button  = new JButton("K");
							button.setEnabled(false);
							panel1.add(button);
						}
					}
					
					else{
						JButton	button  = new JButton();
						panel1.add(button);
					}
					
					
				}
			}	
			
			/*
			JLabel label1 =new JLabel("A B C D E F G H I J ");			
			panel2.add(label1);
			
			JLabel label2 =new JLabel("1 2 3 4 5 6 7 8 9 10 11 ");			
			panel2.add(label1);	
			*/
			
			frame.getContentPane().add(panel1, "Center"); 
			//frame.getContentPane().add(panel2, "North");
			//frame.getContentPane().add(panel3, "South");
			
		}
	/*
		public static void main(String[] args) {
			new Gamegrid();
		}
		
	*/	
	}