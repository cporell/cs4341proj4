package cs4341proj4;
import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

/*
 * Bag class represents the values of this CSP
 * Each bag has a name and a weight limit (both given by the input file), as well as a current capacity 
 * and a count of the items in it
 */
public class Bag implements Comparable<Bag>{
	String name; // Name of the bag
	int weightLimit; // The weight of the items in the bag cannot exceed this
	int capacity;
	HashMap<String, Item> items = new HashMap<String, Item>();
	int possibleAssignments = 0;
	
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
		int wastedCapacity = this.weightLimit - usedCapacity();
		return wastedCapacity;
	}
	
	/*
	 * Calculates the current used capacity of a bag
	 */
	private int usedCapacity(){
		int usedCapacity = 0;
		for(Item i : items.values()){
			usedCapacity += i.weight;
		}
		return usedCapacity;
	}
	
	/*
	 * Assigns an item i to the bag
	 * Does some validation checking first to make sure the item acutally fits in the bag
	 */
	public boolean assign(Item i){
		items.put(i.name, i);
		if(usedCapacity() > weightLimit){
			return false;
		}
		if(this.numitems() > capacity){
			return false;
		}
		return true;
	}
	
	/*
	 * Removes the item i from the bag
	 */
	public void unassign(String i){
		items.remove(i);
	}
	
	/*
	 * Checks to see if the bag contains a specific item
	 */
	public boolean contains(String s){
		if(items.get(s) != null){
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Returns the number of items in the bag
	 */
	public int numitems(){
		return items.entrySet().size();
	}
	
	/*
	 * Sets the weight capacity of this bag 
	 */
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	
	/*
	 * Calculates the amount of weight used in this bag
	 */
	public int weightused(){
		int weight = 0;
		for(Item i : items.values()){
			weight += i.weight;
		}
		return weight;
	}

	/*
	 * Compares this bag to another bag.
	 * Returns the difference between their possible assignments (0 means identical)
	 */
	@Override
	public int compareTo(Bag o) {
		// TODO Auto-generated method stub
		return this.possibleAssignments - o.possibleAssignments;
	}
}
