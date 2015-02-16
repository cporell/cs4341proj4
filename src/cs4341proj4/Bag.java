package cs4341proj4;
import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

public class Bag {
	String name; // Name of the bag
	int weightLimit; // The weight of the items in the bag cannot exceed this
	int capacity;
	HashMap<String, Item> items = new HashMap<String, Item>();
	
	// Create a new bag
	public Bag(String name, int weightLimit){
		this.name = name;
		this.weightLimit = weightLimit;
		this.capacity = 0;
		this.items = new HashMap<String, Item>();
	}
	
	/*
	 * Writes all the contents of the Bag into a string and returns it
	 */
	public String writeItems(){
		String items = this.name;
		
		for(Item i : this.items.values()) {
			items += " " + i.name;
		}
		
		return items;
	}
	
	/*
	 * Calculates the wasted capacity of the bag
	 */
	public int calcWastedCapacity(){
		int wastedCapacity = this.weightLimit - this.capacity;
		return wastedCapacity;
	}
}
