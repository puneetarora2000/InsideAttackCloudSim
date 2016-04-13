package insider.detector.algo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

 
public class AlgoPropose {
	// the "monitoring lattice" tree
	estTree tree;

    // for stats
    public estTree getTree() {
        return tree;
    }

    public void setTree(estTree tree) {
        this.tree = tree;
    }

    
    public long getMiningTime() {
        return miningTime;
    }

    public void setMiningTime(long miningTime) {
        this.miningTime = miningTime;
    }

    public double getSumTransactionInsertionTime() {
        return sumTransactionInsertionTime;
    }

    public void setSumTransactionInsertionTime(double sumTransactionInsertionTime) {
        this.sumTransactionInsertionTime = sumTransactionInsertionTime;
    }

    public double getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(double maxMemory) {
        this.maxMemory = maxMemory;
    }
	private long miningTime = 0; 
	double sumTransactionInsertionTime = 0; // sum of time for inserting transactions
	
	private double maxMemory = 0;

	/**
	 * Constructor
	 * @param mins minimum support
	 */
	public AlgoPropose(double mins) {
		// create the "Monitoring Lattice" tree
		tree = new estTree(mins);
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
	 * Mine recent frequent itemsets from the current tree and 
	 * save the result to a file
	 * @throws IOException
	 * @param outputPath the output file path
	 */
	public void performMining_saveResultToFile(String outputPath) throws IOException {
		// Perform mining
		long startMiningTimeStamp = System.currentTimeMillis();
		
		tree.patternMining_saveToFile(outputPath);
		
		checkMemory();
		miningTime = System.currentTimeMillis() - startMiningTimeStamp;
	}
	
	/**
	 * Mine recent frequent itemsets from the current tree and 
	 * save the result to memory
	 * @throws IOException
	 * @param outputPath the output file path
	 * @return 
	 */
	public Hashtable<List<Integer>, Double> performMining_saveResultToMemory() throws IOException {
		// Perform mining
		long startMiningTimeStamp = System.currentTimeMillis();
		
		Hashtable<List<Integer>, Double> patterns = tree.patternMining_saveToMemory();
		
		checkMemory();
		miningTime = System.currentTimeMillis() - startMiningTimeStamp;
		
		return patterns;
	}

	/**
	 * Process a transaction (add it to the tree and update itemsets
	 * @param transaction an array of integers
	 */
	public void processTransaction(int[] transaction) {
		double startCTimestamp = System.currentTimeMillis();
		// process the transaction
		tree.updateParams(transaction);
		tree.insertItemset(transaction);
		
		// force pruning every 1000 transactions
		if (tree.getK() % 1000 == 0)
			tree.forcePruning(tree.root);
		
		sumTransactionInsertionTime += (System.currentTimeMillis() - startCTimestamp);
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
	 * Set the freshness  rate
	 * @param b  freshness base 
	 * @param h freshness -base life
	 */
	public void setFreshnessRate(double b, double h) {
		tree.setDecayRate(b,h);
	}
 
	public void printStats() {
		
                System.out.println("=============  Insider Attack Detection Report =============");
                System.out.println("=============**************************** =============");
		System.out.println(" Abnormal Itemsets count : " + tree.patternCount);
                System.out.println(" Normal  Itemsets count : " + (tree.N-tree.patternCount));
                System.out.println(" All  Itemsets count : " + tree.N);
                System.out.println(" Maximum Memory Usage : " + maxMemory + " mb");
		System.out.println(" Trace Analysis Construct time ~ " + sumTransactionInsertionTime / tree.getK() + " ms");
		System.out.println(" Trace Mining time ~ " + miningTime + " ms");
		System.out.println("=========================================================");
	}
}
