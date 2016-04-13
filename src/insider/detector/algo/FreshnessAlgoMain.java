/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package insider.detector.algo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;

import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.AlgoPrefixSpan;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.SequentialPattern;
import ca.pfv.spmf.algorithms.sequentialpatterns.BIDE_and_prefixspan.SequentialPatterns;
import ca.pfv.spmf.input.sequence_database_list_integers.SequenceDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Ecologic
 */
public class FreshnessAlgoMain {
    
    public static void Execute(String [] arg) throws IOException{    
		// Load a sequence database
        
                String MainPath = "D:\\Data\\";
		String clickstream= MainPath+"output4.txt";
		String output = MainPath+"result.txt"; 
                
                double threshold = 58;
        
        
		SequenceDatabase sequenceDatabase = new SequenceDatabase(); 
		sequenceDatabase.loadFile(fileToPath("contextPrefixSpan.txt"));
		 
	 	//sequenceDatabase.print();
		
		// Create an instance of the algorithm 
		AlgoPrefixSpan algo = new AlgoPrefixSpan(); 
//		algo.setMaximumPatternLength(3);
		
		// execute the algorithm with minsup = 50 %
		SequentialPatterns patterns = algo.runAlgorithm(sequenceDatabase, 0.5, null);    
		algo.printStatistics(sequenceDatabase.size());
		/*
                System.out.println(" == PATTERNS ==");
		for(List<SequentialPattern> level : patterns.levels) {
			for(SequentialPattern pattern : level){
				System.out.println(pattern + " support : " + pattern.getAbsoluteSupport());
			}
		} */
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		URL url = FreshnessAlgoMain.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
        
        public static void main(String args []){
            String [] arg = null;
        try {
            Execute(arg);
        } catch (IOException ex) {
            Logger.getLogger(FreshnessAlgoMain.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        
        
}
