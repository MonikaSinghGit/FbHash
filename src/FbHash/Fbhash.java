package FbHash;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Hashtable;

public class Fbhash {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int availblPrcr= Runtime.getRuntime().availableProcessors();
		int processors = availblPrcr;
		if(args.length!=0) {
		if((args[0].equals("-h"))||(args[0].equals("--help"))) {
			System.out.println("Usage: fbhash <options> <files>\r\n" + 
					"Options:\r\n" + 
					"  -fd [ --file-digest ]           generate digests of a file\n" + 
					"  -d  [ --digest ]                generate digests of all files in the directory\r\n" + 
					"  -o  [ --output ]                stores digest to the file\r\n" + 
					"  -c  [ --compare ]               compare two digest files\r\n" + 
					"  -t  [ --threshold ]             show results >= threshold (only works with compare option)\r\n" + 
					"  -v  [ --version ]               print the version information \r\n" +
					"  -h  [ --help ]                  print help instructions");
			
		}else if((args[0].equals("-fd"))||(args[0].equals("--file-digest"))) {
			Boolean flgo=false;
			String flwrtr="";
			if(args.length<2) {
				System.out.println("Please enter file!!");
			}else {
				
				for (String element: args) {
		            if(flgo==true) {
		            	flwrtr=element;
		            	flgo=false;
		            }
		            if(element.equals("-o") || element.equals("--output")) {
		            	flgo=true;
		            }
		        }
				
				
				if(flwrtr=="") {
					System.out.println("Please enter the output file using option '-o'!");
				}else {
				
				String dataset=args[1];
				
		    	try {
		    		FileOutputStream fileWriter = new FileOutputStream(flwrtr);
		    		FileDigest(dataset,fileWriter);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				}
			}
			
			
			
		}else if((args[0].equals("-d"))||(args[0].equals("--digest"))) {
			Boolean flgo=false;
			String flwrtr="";
			if(args.length<2) {
				System.out.println("Please enter the correct arguments!!");
			}else {
				for (String element: args) {
		            if(flgo==true) {
		            	flwrtr=element;
		            	flgo=false;
		            }
		            if(element.equals("-o") || element.equals("--output")) {
		            	flgo=true;
		            }
		        }
				
				
				if(flwrtr=="") {
					System.out.println("Please enter the output file using option '-o'!");
				}else {
				String dataset=args[1]+"\\";
	
		    	try {
		    		FileOutputStream fileWriter = new FileOutputStream(flwrtr);
		    		Digest(dataset,fileWriter,processors);
				   	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			
		}else if((args[0].equals("-c"))||(args[0].equals("--compare"))) {
			int thrshld=1;
			Boolean flgt=false;
			
			if(args.length<3) {
				System.out.println("Please enter the both digest files!!");
			}else {
				if((args[1]==null)||(args[2]==null)) {
					System.out.println("Please enter the both digest files!!");
				}else {
					
					for (String element: args) {
			            if(flgt==true) {
			            	thrshld=Integer.parseInt(element);
			            	flgt=false;
			            }
			            if(element.equals("-t") || element.equals("--threshold")) {
			            	flgt=true;
			            }
			        }
				String dataset1=args[1];
				String dataset2=args[2];
				
					try {
						Comparision(dataset1,dataset2,thrshld,processors);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
			
		}else if((args[0].equals("-v"))||(args[0].equals("--version"))) {
			System.out.println("fbhash 2.0");
		}
		
		}else {
			System.out.println("Please select one of the following options.");
			System.out.println("Usage: fbhash <options> <files>\r\n" + 
					"Options:\r\n" + 
					"  -fd [ --file-digest ]           generate digests of a file\n" + 
					"  -d  [ --digest ]                generate digests of all files in the directory\r\n" + 
					"  -o  [ --output ]                stores digest to the file\r\n" + 
					"  -c  [ --compare ]               compare two digest files\r\n" + 
					"  -t  [ --threshold ]             show results >= threshold (only works with compare option)\r\n" + 
					"  -v  [ --version ]               print the version information \r\n" +
					"  -h  [ --help ]                  print help instructions");
		}
		
		
	}
	
	
	
	
	
	private static void FileDigest(String dataset, FileOutputStream fileWriter) {
		// TODO Auto-generated method stub
		File fl = new File(dataset);
		if (fl.isFile()) {
	        String refFile=dataset;
	try {
		Hashtable<Long, Float> hshtbl = new Hashtable<Long, Float>();
		RabinKarpNewFiveByte rk1 = new RabinKarpNewFiveByte(dataset, hshtbl);
		String rf=refFile+":"+hshtbl.toString()+"\n";
        fileWriter.write(rf.getBytes());
		hshtbl.clear();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}else {
		System.out.println("Please enter a valid file!!");
	}
		
		
	}





	private static void Comparision(String dataset1, String dataset2, int thrshld, int processors) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.gc();
	    String [] resultString = new String[processors];
	    Arrays.fill(resultString, "0");
    	Path path1 = Paths.get(dataset1);
		Path path2 = Paths.get(dataset2);
		
		if ((Files.exists(path1)) && (Files.exists(path2))) {
			
		String [] lines = new String[processors];
	    Arrays.fill(lines, "0");
	    
	    int count =0;
		int flag=1;
		String readLine=" ";
		try {
    		BufferedReader in = Files.newBufferedReader(path1, Charset.forName("UTF-8"));
    		while(true) {
    			count=0;
    		while (count<processors ) { 
    			 readLine = in.readLine();
    			 if(readLine==null) {
    				 flag=0;
    				 break;
    			 }
    			 else {
    		     lines[count] = readLine;
    		     count++; 
    			 }
    		     
    		   }
    		Hashtable<Long, Float>[] hshtbl1 = new Hashtable[count];
 		    for(int p=0;p<count;p++)
 			   hshtbl1[p]= new Hashtable<Long, Float>();
 		
	           String [][] fldtls1 = new String[count][];
	           double[] divisor1 = new double[count];
	 		   for(int i=0;i<count;i++)
	 			   fldtls1[i]=lines[i].split(":\\{");
	             
	 		   Thread [] t =  new Thread[count];
	 		   for (int i = 0; i < count; i++) {
	 			     t[i] = new Thread(new ComparisionThread1(lines[i],hshtbl1[i], divisor1, i));
	 			    t[i].start();     
	 		   }
	 		   
	 		   for (Thread thread : t) {
	 			    thread.join();
	 			}
	 		 
               try(BufferedReader in2 = Files.newBufferedReader(path2, Charset.forName("UTF-8"))){
      		    String line2;
                  while ((line2 = in2.readLine()) != null) {
                	    String []fldtls2=line2.split(":\\{");
                      if(line2.contains(", ")) {
            		    String []keyvals2=fldtls2[1].split(", ");
	          		    Thread [] t2 = new Thread[count];
	          		    for (int k = 0; k < count; k++) {
	          				
	         			     t2[k] = new Thread(new ComparisionThread2(hshtbl1[k],keyvals2,divisor1[k], fldtls1[k],fldtls2,thrshld)) ;
	         			     t2[k].start();     
	         		          }
	         		   
	         		        for (Thread thread2 : t2) {
	         			        thread2.join();
	         			      }
                      }
             		                		        
          		    }
                  in2.close();
                  }catch(IOException ex){
              		      ex.printStackTrace(); //handle an exception here
            	}
          		  
               if(flag==0)
          			   break;
            
    		}
        		in.close();
    		
		}catch (final IOException e) {
    	            e.printStackTrace();
    	        }
	}else {
		System.out.println("Please eneter a valid file!! One of the file doesn't exist!!");
	}
		
	}


	private static void Digest(String dataset, FileOutputStream fileWriter, int processors) throws IOException {
    	
    	File folder_ref = new File(dataset);
    	if(folder_ref.exists()) {
    		
    	File[] listOfFiles_ref = folder_ref.listFiles();
    	int num_loops = listOfFiles_ref.length/processors;
    	int extra = listOfFiles_ref.length % processors;
    	Thread [] t =  new Thread[processors];
    	for(int j=0;j<num_loops;j++) {
    		int incr = processors*j;
    		for(int i=0;i<processors;i++) {
    			t[i] = new Thread(new DigestGenerationThread(dataset, fileWriter, listOfFiles_ref[i+incr]));
    			t[i].start();
    		}
    		 for (Thread thread : t) {
 			    try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 			}
    	}
    	int prev_end = num_loops*processors;
    	Thread [] t2 =  new Thread[extra];
    	for(int i=0;i<extra;i++) {
    		t2[i] = new Thread(new DigestGenerationThread(dataset, fileWriter, listOfFiles_ref[prev_end+i]));
    		t2[i].start();
		}
		 for (Thread thread : t2) {
			    try {
					thread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 
    		
		}else {
			System.out.println("The folder doesn't exist!!");
		}
		 
    	}
    	
    	
    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
    }	
	
	

}
