package cs4341proj4;
/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

public class BagFitLimit extends Constraint{
	int lowerLimit;
	int higherLimit;
	
	public BagFitLimit(int low, int high) {
		this.lowerLimit = low;
		this.higherLimit = high;
	}
}
