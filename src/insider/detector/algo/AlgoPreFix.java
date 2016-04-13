package insider.detector.algo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

 
public class AlgoPreFix {
	 
	estTree prefixtree;
	
	 
	private long miningTime = 0; 
	double sumTransactionInsertionTime = 0; // sum of time for inserting transactions
	
	private double maxMemory = 0;

	 
	public AlgoPreFix(double mins) {
		// create the "Monitoring Lattice" prefixtree
		prefixtree = new estTree(mins);
	}

	
	/**
	 * Run the algorithm by loading the click stream transactions from an input file.
	 * @param input   the input file path
	 * @param output  the output file path for saving the result
	 * @param mins    the minsup threshold as a double value in [0, 1]
	 * @throws FileNotFoundException  if error opening the input file
	 * @throws IOException if error reading/writing files
	 */
	public void processTransactionFromFile(String input)
			throws FileNotFoundException, IOException {

		// read the input file
		BufferedReader reader = new BufferedReader(new FileReader(input));
		String line;
		
		// for each line (transaction)
		while (((line = reader.readLine()) != null)) { 
			String[] lineSplited = line.split(" ");
			int[] transaction = getVector(lineSplited);
			
			processTransaction(transaction);

		}// while
		reader.close();
	}


	/**
	 * Mine recent frequent itemsets from the current prefixtree and 
 save the result to a file
	 * @throws IOException
	 * @param outputPath the output file path
	 */
	public void performMining_saveResultToFile(String outputPath) throws IOException {
		// Perform mining
		long startMiningTimeStamp = System.currentTimeMillis();
		 long mindspan = System.currentTimeMillis();
		prefixtree.patternMining_saveToFile(outputPath);
		
		checkMemory();
		miningTime = System.currentTimeMillis() - startMiningTimeStamp+mindspan;
	}
	
	/**
	 * Mine recent frequent itemsets from the current prefixtree and 
 save the result to memory
	 * @throws IOException
	 * @param outputPath the output file path
	 * @return 
	 */
	public Hashtable<List<Integer>, Double> performMining_saveResultToMemory() throws IOException {
		// Perform mining
		long startMiningTimeStamp = System.currentTimeMillis();
		
		Hashtable<List<Integer>, Double> patterns = prefixtree.patternMining_saveToMemory();
		
		checkMemory();
		miningTime = System.currentTimeMillis() - startMiningTimeStamp;
		
		return patterns;
	}

	/**
	 * Process a transaction (add it to the prefixtree and update itemsets
	 * @param transaction an array of integers
	 */
	public void processTransaction(int[] transaction) {
                double mindspan = System.currentTimeMillis();
		double startCTimestamp = System.currentTimeMillis();
		// process the transaction
		prefixtree.updateParams(transaction);
		prefixtree.insertItemset(transaction);
		
		// force pruning every 1000 transactions
		if (prefixtree.getK() % 1000 == 0)
			prefixtree.forcePruning(prefixtree.root);
		
		sumTransactionInsertionTime += (System.currentTimeMillis() - startCTimestamp)+mindspan;
	}

	
	/**
	 * Transform an array of strings to an array of integers
	 * @param line an array of strings
	 * @return an array of integers
	 */
	int[] getVector(String[] line) {
		int[] output = new int[line.length];
		for (int i=0; i< line.length; i++) {
			output[i] = Integer.parseInt(line[i]);
		}
		return output;
	}

	/**
	 * Check the current memory consumption to record the maximum memory usage.
	 */
	private void checkMemory() {
		// Runtime.getRuntime().gc();
		double currentMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory())
				/ 1024d / 1024d;
		if (currentMemory > maxMemory) {
			maxMemory = currentMemory;
		}
	}
	
	/**
	 * Set the decay rate
	 * @param b  decay base 
	 * @param h decay-base life
	 */
	public void setDecayRate(double b, double h) {
            
           // remove the decay Rate Paramter , so that the 
            //algorithm  conducts full database scan .
            prefixtree.setDecayRate(0,0);
	
        
        
        }
 
	public void printStats() {
		System.out.println("============= Prefix Insider Attack Detection Report =============");
                System.out.println(" Abnormal Itemsets count : " + prefixtree.patternCount);
                System.out.println(" Normal  Itemsets count : " + (prefixtree.N-prefixtree.patternCount));
                System.out.println(" All  Itemsets count : " + prefixtree.N);
                               
		System.out.println(" Maximum Memory Usage : " + maxMemory + " mb");
		System.out.println(" Trace Analysis Construct time ~ " + sumTransactionInsertionTime / prefixtree.getK() + " ms");
		System.out.println(" Trace Mining time ~ " + miningTime + " ms");
		System.out.println("===================================================");
	}
}
