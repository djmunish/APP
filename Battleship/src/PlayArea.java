import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import javax.swing.JComboBox;
import javax.swing.JFrame;
//import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;


public class PlayArea extends JFrame implements ActionListener {

	private final JSplitPane splitPane1;
	private final JSplitPane splitPane2;
	private final JPanel topRight;       
    private final JPanel topLeft;
    private final JPanel bottomRight;
    private final JPanel bottomLeft;    
	private JScrollPane scrollPane1;
	private JScrollPane scrollPane2;
	private JScrollPane scrollPane3;
	private JScrollPane scrollPane4;
	
	final JTable table1 = new JTable(new String[][]{
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"}
    }, new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"});
	
	final JTable table2 = new JTable(new String[][]{
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"}
    }, new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"});
	
	final JTable table3 = new JTable(new String[][]{
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"}
    }, new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"});
	
	final JTable table4 = new JTable(new String[][]{
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"},
        {"*", "*", "*","*", "*", "*","*", "*", "*","*", "*"}
    }, new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K"});

	
	public static void main(String[] args) {
		 new PlayArea().setVisible(true);	 
	}
	
	 public void actionPerformed(ActionEvent e) {
	        JComboBox cb = (JComboBox)e.getSource();
//	        String petName = (String)cb.getSelectedItem();
//	        updateLabel(petName);
	    }
	
	PlayArea() {
		
		
		JFrame f = new JFrame("PLAY AREA");
		splitPane1 = new JSplitPane();
		
		splitPane2 = new JSplitPane();

		
		topLeft = new JPanel();         
        topRight = new JPanel();      
        bottomLeft = new JPanel();
        bottomRight = new JPanel();
       
        setPreferredSize(new Dimension(800, 800)); 
            
        getContentPane().setLayout(new GridLayout()); 
                
        getContentPane().add(splitPane1);
        getContentPane().add(splitPane2);        
        
        scrollPane1 = new JScrollPane(table1);
        scrollPane2 = new JScrollPane(table2);
        scrollPane3 = new JScrollPane(table3);
        scrollPane4 = new JScrollPane(table4);
       
        topLeft.add(scrollPane1);
        topRight.add(scrollPane2);
        bottomLeft.add(scrollPane3);
        bottomRight.add(scrollPane4); 
       
        splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
        

        
        
        splitPane1.setDividerLocation(400);
        splitPane2.setDividerLocation(400);
        
        
        splitPane1.setTopComponent(topLeft);                  
        splitPane1.setBottomComponent(bottomLeft); 
        splitPane2.setTopComponent(topRight);        
        splitPane2.setBottomComponent(bottomRight);        
        
        

        String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

		// Create the combo box, select item at index 4.
		// Indices start at 0, so 4 specifies the pig.
		JComboBox<String> petList = new JComboBox<>(petStrings);
		petList.setSelectedIndex(4);
		petList.addActionListener(this);
        
        
		bottomLeft.add(petList);
        
        
        pack(); 
            
        
	}
}
            
            
            

            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
		
           
		
	