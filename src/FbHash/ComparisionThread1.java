package FbHash;
import java.io.FileWriter;
import java.util.Hashtable;

public class ComparisionThread1 implements Runnable{ 
		String line;
		Long key1;
		Float val2;
		FileWriter fileWriter;
		Hashtable<Long, Float> hshtbl1;
		double[] divisor1;
		int index;
		public ComparisionThread1(String s, Hashtable<Long, Float> hshtbl, double[] divisor, int i) {
			line = s;
			hshtbl1 = hshtbl;
			divisor1 = divisor;
			index = i;
			
		}


		public void run() {  
            
            double tf1;
            double tfIdf1;
            float val1;
			
			divisor1[index]=0;
            String []fldtls=line.split(":\\{");
            String []keyvals=fldtls[1].split(", ");
		    val1=0f;
		    if(keyvals.length>1) {
		    for(int i=0;i<keyvals.length;i++) {
		    	String []keyval=keyvals[i].split("=");
		    	if(i==(keyvals.length-1)) {
		    		hshtbl1.put(Long.parseLong(keyval[0]), Float.parseFloat(keyval[1].substring(0, keyval[1].length()-1)));
		    		val1=Float.parseFloat(keyval[1].substring(0, keyval[1].length()-1));
		    	}else {
		    		hshtbl1.put(Long.parseLong(keyval[0]), Float.parseFloat(keyval[1]));
		    		val1=Float.parseFloat(keyval[1]);
		    	}
		    	tf1=1+Math.log10(val1);;
	      	    tfIdf1=tf1;
	      	    divisor1[index]=divisor1[index]+(tfIdf1*tfIdf1);
	      		
		    }
		    }	    
    	
		}
			
		
		
	
}
