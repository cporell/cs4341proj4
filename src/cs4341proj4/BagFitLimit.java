package cs4341proj4;

import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

/*
 * Class for the BagFitLimit constraint
 * Contains fields for a lower limit and higher limit
 */
public class BagFitLimit{
	int lowerLimit;
	int higherLimit;
	
	public BagFitLimit(int low, int high) {
		this.lowerLimit = low;
		this.higherLimit = high;
	}

	/*
	 * Checks a bag to make sure its item count is within the BagFitLimit for this CSP
	 */
	public boolean checkConstraint(HashMap<String, Bag> bags) {
		for (Bag b : bags.values()){
			if(b.numitems() > higherLimit || b.numitems() < lowerLimit){
				return false;
			}
		}
		
		return true;
	}
	
	/*
	 * Specifically checks to see if a bag has more than the BagFitLimit allows
	 */
	public boolean checkConstraintHigh(HashMap<String, Bag> bags) {
		for (Bag b : bags.values()){
			if(b.numitems() > higherLimit){
				return false;
			}
		}
		
		return true;
	}
	
}
