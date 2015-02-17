package cs4341proj4;

import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

public class BagFitLimit{
	int lowerLimit;
	int higherLimit;
	
	public BagFitLimit(int low, int high) {
		this.lowerLimit = low;
		this.higherLimit = high;
	}

	public boolean checkConstraint(HashMap<String, Bag> bags) {
		for (Bag b : bags.values()){
			if(b.numitems() > higherLimit || b.numitems() < lowerLimit){
				return false;
			}
		}
		
		return true;
	}
	
	public boolean checkConstraintHigh(HashMap<String, Bag> bags) {
		for (Bag b : bags.values()){
			if(b.numitems() > higherLimit){
				return false;
			}
		}
		
		return true;
	}
	
}
