package FbHash;
/******** RabinKarp 
 * Java Program to Implement Rolling Hash
 **/
 
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.Buffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;




class RabinKarpNewFiveByte implements DocFreqArraysFiveBytes_2,DocFreqArraysFiveBytes_4,DocFreqArraysFiveBytes_6,DocFreqArraysFiveBytes_7,DocFreqArraysFiveBytes_83
{
    /** Window length **/
    private int M;  
    private long Q; 
    private int R;    
    private long RM;        
    static int tstt=0;
    private static final int BLOCK_SIZE = 20971520; 
    

    public RabinKarpNewFiveByte(String flpth, Hashtable<Long, Float> hshtble) throws IOException 
    {
        
        R = 256; 
        M = 5; // chunk size
        Q=1099511627689L; 

        RM = 1;
        for (int i = 1; i <= M-1; i++)
           RM = (R * RM) % Q;
        
        try {
			chuncking(flpth,hshtble);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        if(tstt==0){
       
        }
        tstt++;
       
        
    }
   	


 
    private void chuncking(String flpth,Hashtable<Long, Float> hshtble) throws IOException 
    {
    	float idf=0f;
    	int docfreq=0;
    	
    	String filePath = flpth.trim();
    	Path path = Paths.get(filePath);
    	
        FileChannel fileChannel =  FileChannel.open(path);
        ByteBuffer buffer = ByteBuffer.allocate(BLOCK_SIZE);
        int noOfBytesRead = fileChannel.read(buffer);
         int i=0;
         String wndw="";
         long txtHash=0;
        while (noOfBytesRead != -1) {
            ((Buffer)buffer).flip();
         
            while (buffer.hasRemaining()) {
            	
            	char r= (char) buffer.get();
            	if(i<M) {
    		    	wndw=wndw+r;
              		txtHash = (R * txtHash + r) % Q;
         	    }else {
    		    	
    		    	txtHash = (txtHash + Q - RM*wndw.charAt(0) % Q) % Q; 
    	            txtHash = (txtHash * R + r) % Q; 
    	            wndw=wndw.substring((1), (M))+r;
    	            docfreq=DocFreqBloomFilter(Long.toBinaryString(txtHash));
    				   if(docfreq==0) {
    					   idf=1;
    				   }else {
    					   idf=(float) Math.log10(711/docfreq);
    				   }
    	            
    	            if(hshtble.get(txtHash)==null){
    	            	hshtble.put(txtHash,  round(idf,2));
    	            }else{
    	            	hshtble.put(txtHash, round((hshtble.get(txtHash)+idf),2));
    	            }
    	 	    	
    		    }
            	
            	i++;
            }
 
            ((Buffer)buffer).clear();
            noOfBytesRead = fileChannel.read(buffer);
        }
        fileChannel.close();
    
    
    }
    
    @SuppressWarnings("deprecation")
	public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
   }
    
    private static int DocFreqBloomFilter(String bnr) {
    	// TODO Auto-generated method stub
    	
    		int setBlm1=0;
    	    int setBlm2=0;
    	    int setBlm3=0;
    	    int docFreq=0;
    		
    	    if(bnr.length()<57) {
    	    	  while(bnr.length()<57) {
    	    	  	  bnr="0"+bnr;
    	    	  }
    	      }
    	   		
    	      setBlm1= Integer.parseInt(bnr.substring(0, 19),2);
    	      setBlm2= Integer.parseInt(bnr.substring(19, 38),2);
    	      setBlm3= Integer.parseInt(bnr.substring(38, 57),2);
    	     
    	     
    	      if((blm_arr4_0[setBlm1]==1) && (blm_arr4_0[setBlm2]==1) && (blm_arr4_0[setBlm3]==1)) {
    		   	  	  docFreq=64;
    	      }else if((blm_arr4_1[setBlm1]==1) && (blm_arr4_1[setBlm2]==1) && (blm_arr4_1[setBlm3]==1)) {
       		  	  docFreq=64;
        	  }else if((blm_arr2_0[setBlm1]==1) && (blm_arr2_0[setBlm2]==1) && (blm_arr2_0[setBlm3]==1)) {
    	    	  docFreq=81;
    	      }else if((blm_arr2_1[setBlm1]==1) && (blm_arr2_1[setBlm2]==1) && (blm_arr2_1[setBlm3]==1)) {
    	    	  docFreq=81;
    	      }else if((blm_arr7_0[setBlm1]==1) && (blm_arr7_0[setBlm2]==1) && (blm_arr7_0[setBlm3]==1)) {
    	    	  docFreq=48;
    	      }else if((blm_arr7_1[setBlm1]==1) && (blm_arr7_1[setBlm2]==1) && (blm_arr7_1[setBlm3]==1)) {
    	    	  docFreq=48;
    	      }else if((blm_arr6[setBlm1]==1) && (blm_arr6[setBlm2]==1) && (blm_arr6[setBlm3]==1)) {
    		   	  	  docFreq=106;
    	      }else if((blm_arr8[setBlm1]==1) && (blm_arr8[setBlm2]==1) && (blm_arr8[setBlm3]==1)) {
    	   		  docFreq=167;
    	   	  }else if((blm_arr3[setBlm1]==1) && (blm_arr3[setBlm2]==1) && (blm_arr3[setBlm3]==1)) {
    	   		  docFreq=262;
    	   	  }
    		    
      	
    	return docFreq;
    }
    

}
 
