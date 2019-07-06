import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

public final class Constants {

    private Constants() {
            // restrict instantiation
    }
    
    public static final int row = 10;
    public static final int col = 11;
    
    public static HashMap<String, Integer> mapInConstants ;
    static {
    	mapInConstants = new HashMap<>();
    	
    	mapInConstants.put("A",0);
        mapInConstants.put("B",1);
        mapInConstants.put("C",2);
        mapInConstants.put("D",3);
        mapInConstants.put("E",4);
        mapInConstants.put("F",5);
        mapInConstants.put("G",6);
        mapInConstants.put("H",7);
        mapInConstants.put("I",8);
        mapInConstants.put("J",9);
        mapInConstants.put("K",10);
    	
    }
    
   
    
    
}