package ca.pfv.spmf.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import ca.pfv.spmf.tools.dataset_converter.SequenceDatabaseConverter;
import ca.pfv.spmf.tools.dataset_generator.TransactionDatabaseGenerator;

/**
 * Example of how to use the random transaction database generator
 * from the source code.
 */
public class TransactionDatabase {

    
    
    
	public static void RunCloudUserTransactions(String [] arg) throws IOException{
		
                String outputFile = "D:\\Data\\output4.txt";
		
                
                TransactionDatabaseGenerator generator = new TransactionDatabaseGenerator();
		
                
                generator.generateDatabase(6000, 6, 6, outputFile);
                             
                System.out.println("========================");
                
        
        }

	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = SequenceDatabaseConverter.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
