package ca.pfv.spmf.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import ca.pfv.spmf.tools.dataset_converter.SequenceDatabaseConverter;
import ca.pfv.spmf.tools.dataset_generator.SequenceDatabaseGenerator;

/**
 * Example of how to use the random sequence database generator, from
 * the source code, to generate a database with timestamps.

 */
public class TimeStamps {
	public static void main(String [] arg) throws IOException{
		
		// BMS, Kosarak
             System.out.println("A");
		String outputFile = "D:\\Data\\output3.txt";
		try{
		SequenceDatabaseGenerator generator = new SequenceDatabaseGenerator();
		generator.generateDatabase(5, 1500, 2, 8, outputFile, true);
                
             
                
                System.out.println("A");
                }catch(Exception e){
                
                    System.out.println(e.getMessage());
                
                }
                
                
	}

	

	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = SequenceDatabaseConverter.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
