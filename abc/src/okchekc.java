import java.util.*;


public class okchekc {

	public static void main(String args[]) {
		// TODO Auto-generated method stub
		ArrayList<String> ab = new ArrayList<String>();
		ab.add("f3");
		String arr[]=new String[1];
		String f = "";
		f= f.trim();
		String[] a= f.split(" ");
		for(int i=0;i<a.length;i++){
			if (a[i].equals("")){
				//ab.add(a[i]);
			}else{
			System.out.println("space:-"+a[i]);
			ab.add(a[i]);}
		}
		
		System.out.println("Arraylist is" + ab);
		
	}
}
