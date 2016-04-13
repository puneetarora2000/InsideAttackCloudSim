package insider.detector.algo;
import java.util.ArrayList;
import java.util.List;

 
public class estNode {
	Integer itemID; // item id
	double counter; // frequency counter
	int tid; // last tid

	List<estNode> children; // children nodes

	/**
	 * constructor
	 * @param item  the item
	 * @param count the count 
	 * @param k  the last transaction id
	 */
	estNode(Integer item, double count, int k) {
		itemID = item;
		counter = count;
		tid = k;
		children = new ArrayList<estNode>();
	}

	/**
	 * Default constructor
	 */
	estNode() {
		itemID = -1;
		counter = 0;
		tid = 0;
		children = new ArrayList<estNode>();
	}

	/**
	 * Return the immediate child of this node having a given ID. If there is no
	 * such child, return null;
	 * 
	 * @param id the id
	 * @return the node or null
	 */
	public estNode getChildWithID(int id) {
		if (children == null)
			return null;
		for (estNode child : children) {
			if (child.itemID == id) {
				return child;
			}
		}
		return null;
	}

	/**
	 * Return the immediate index of the node having a given ID. If there is no
	 * such child, return -1;
	 * 
	 * @param id
	 */
	public int getChildIndexWithID(int id) {
		if (children == null)
			return -1;
		int i = 0;
		for (estNode child : children) {
			if (child.itemID == id) {
				return i;
			}
			i++;
		}
		return -1;
	}

	/**
	 * Update the count of a node
	 * 
	 * @param k the current transaction id
	 * @param value the value to be added to the count
	 * @param d  the decay rate
	 */
	public void update(int k, int value, double d) {
		counter = counter * Math.pow(d, k - tid) + value;
		tid = k;
	}

	/**
	 * Compute the support of this node as a percentage.
	 * 
	 * @param N an integer representing a transaction count.
	 */
	public double computeSupport(double N) {
		return counter / N;
	}
}
