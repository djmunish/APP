import java.awt.GridLayout;
import java.awt.*; 

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

public class PlayArea extends JFrame{
	
	private final JSplitPane splitPane;  // split the window in top and bottom
    private final JPanel topPanel;       // container panel for the top
    private final JPanel bottomPanel;    // container panel for the bottom
	private JScrollPane scrollPane;
	
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
	
	PlayArea() {
		
		
		JFrame f = new JFrame("PLAY AREA");
		splitPane = new JSplitPane();
		
        topPanel = new JPanel();         // our top component
        bottomPanel = new JPanel();      // our bottom component
        
        JScrollPane jp = new JScrollPane(table1); 
        setPreferredSize(new Dimension(800, 800)); 
            
        getContentPane().setLayout(new GridLayout()); 
                
        getContentPane().add(splitPane);
        scrollPane = new JScrollPane(table1);
        scrollPane = new JScrollPane(table2);
//        scrollPane = new JScrollPane(table3);
//        scrollPane = new JScrollPane(table4);
//            
            
        JLabel jf = new JLabel("Hello");
       
        topPanel.add(jf);
        bottomPanel.add(scrollPane); 
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        
        splitPane.setDividerLocation(400); 
        splitPane.setTopComponent(topPanel);                  
        splitPane.setBottomComponent(bottomPanel); 
           
        pack(); 
            
            
	}
}
            
            
            

            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
		
           
		
	