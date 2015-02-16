package cs4341proj4;
/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

public class BinaryME extends Constraint{
	Item item1;
	Item item2;
	Bag bag1;
	Bag bag2;
	
	public BinaryME(Item item1, Item item2, Bag bag1, Bag bag2) {
		this.item1 = item1;
		this.item2 = item2;
		this.bag1 = bag1;
		this.bag2 = bag2;
	}
}
