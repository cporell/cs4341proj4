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
		int wastedCapacity = this.weightLimit - usedCapacity();
		return wastedCapacity;
	}
	
	private int usedCapacity(){
		int usedCapacity = 0;
		for(Item i : items.values()){
			usedCapacity += i.weight;
		}
		return usedCapacity;
	}
	
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
	
	public void unassign(String i){
		items.remove(i);
	}
	
	public boolean contains(String s){
		if(items.get(s) != null){
			return true;
		} else {
			return false;
		}
	}
	
	public int numitems(){
		return items.entrySet().size();
	}
	
	public void setCapacity(int capacity){
		this.capacity = capacity;
	}
	
	public int weightused(){
		int weight = 0;
		for(Item i : items.values()){
			weight += i.weight;
		}
		return weight;
	}
}
