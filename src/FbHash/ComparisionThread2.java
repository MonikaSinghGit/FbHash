package FbHash;
import java.util.Hashtable;

public class ComparisionThread2 implements Runnable{ 
		Hashtable<Long, Float> hshtbl1;
		String[] keyvals2;
		String[] fldtls1;
		String[] fldtls2;
		String resultString="";
		//FileWriter fileWriter;
		Long key2;
		Double divisor1;
		int t;
		public ComparisionThread2(Hashtable<Long, Float> hshtbl, String[] keyvals, Double divisor, String[] f1, String[] f2, int thrshld) {
			hshtbl1 = hshtbl;
			keyvals2 = keyvals;
			fldtls1 = f1;
			fldtls2 = f2;
			//fileWriter = fileWriter1;
			divisor1= divisor;
			t=thrshld;

		}


		public void run() {  
			
            double dotProduct =0;
            double divisor2 =0;
            double tf2 =0;
            double tfIdf2 =0;
            double divisor;
            double cosineScore=0;
            float val2;
            double tf1=0;
            double tfIdf1 =0;
    	    
		    for(int i=0;i<keyvals2.length;i++) {
		    	String []keyval2=keyvals2[i].split("=");
		    	if(i==(keyvals2.length-1)) {
		    		key2=Long.parseLong(keyval2[0]);
    		    	val2=Float.parseFloat(keyval2[1].substring(0, keyval2[1].length()-1) );
		    	}else {
		    		key2=Long.parseLong(keyval2[0]);
    		    	val2=Float.parseFloat(keyval2[1]);
		    	}
		    	 
		    	 if(hshtbl1.get(key2)!=null){
		        	   tf1=1+Math.log10((double)hshtbl1.get(key2));
		        	   tf2=1+Math.log10(val2);
		        	   tfIdf1=tf1;
		        	   tfIdf2=tf2;
		        	   dotProduct=dotProduct+ (tfIdf1*tfIdf2);
		        	   divisor2=divisor2+(tfIdf2*tfIdf2);
		        	   
		           }else{

		        	   tf2=1+Math.log10(val2);
		        	   //tf2=1+val2;
		        	   tfIdf2=tf2;
		        	   divisor2=divisor2+(tfIdf2*tfIdf2);
		           }
		        }
		        
		        divisor=Math.sqrt(divisor1)*Math.sqrt(divisor2);
		        cosineScore=dotProduct/divisor;
		        int finalscore=(int) (cosineScore*100);
		        if(finalscore>=t) {
		        resultString=fldtls2[0]+" | "+fldtls1[0]+" | "+finalscore+System.lineSeparator();
		                
		        try{
		        //fileWriter.write(resultString);
		        System.out.print(resultString);	
		        }catch  (Exception e){
		    		e.printStackTrace();
		    	}
		        }
    		    	
		}
	
}
