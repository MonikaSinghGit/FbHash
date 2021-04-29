package FbHash;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;


public class DigestGenerationThread implements Runnable{ 
		String dataset;
		FileOutputStream fileWriter;
		File file_ref;

		public DigestGenerationThread(String dataset2, FileOutputStream fileWriter2, File listOfFiles_ref) {
			dataset = dataset2;
			fileWriter=fileWriter2;
			file_ref=listOfFiles_ref;
			
		}
	
		
		

		public void run() {  
			
			//String refFileName=file_ref.getName();
    		if (file_ref.isFile()) {
    	        String refFile=dataset+file_ref.getName();
    	       
		    try {	
					Hashtable<Long, Float> hshtbl = new Hashtable<Long, Float>();
					RabinKarpNewFiveByte rk1 = new RabinKarpNewFiveByte(refFile, hshtbl);
					String rf=refFile+":"+hshtbl.toString()+"\n";
			        fileWriter.write(rf.getBytes());
					hshtbl.clear();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		}
	
}
