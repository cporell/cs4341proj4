import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

public class Bag {
	String name; // Name of the bag
	int weightLimit; // The weight of the items in the bag cannot exceed this
	HashMap<String, Item> items;
	
	// Create a new bag
	public Bag(String name, int weightLimit){
		this.name = name;
		this.weightLimit = weightLimit;
		this.items = new HashMap<String, Item>();
	}
}
