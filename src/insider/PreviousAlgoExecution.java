package insider;

import insider.detector.algo.AlgoPreFix;
import insider.detector.algo.AlgoPropose;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;

//import ca.pfv.spmf.algorithms.frequentpatterns.estDec.AlgoPropose;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to test the estDec algorithm and save the result to a file.
 */
public class PreviousAlgoExecution {

 
        
       public static void Execute(String [] arg) throws FileNotFoundException, IOException{
		String MainPath = "D:\\Data\\";
		String clickstream= MainPath+"output4.txt";
		String output = MainPath+"result.txt"; 
             
                 AlgoPreFix  algo = new AlgoPreFix(50);
                algo.processTransactionFromFile(clickstream);
		               
                algo.performMining_saveResultToFile(output);
                 
                
		algo.printStats();
                System.exit(0);
               
	}
	
        
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = PreviousAlgoExecution.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
        
        public static void main(String args[]){
            try {
                Execute(args);
            } catch (IOException ex) {
                Logger.getLogger(PreviousAlgoExecution.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        
        
}
