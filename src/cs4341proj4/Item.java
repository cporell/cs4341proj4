package cs4341proj4;

import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

/*
 * Item class is a representation of the variables of this CSP.
 * They are given a name and a weight (as indicated in the input file), as well as the number of constraints on the item.
 */
public class Item implements Comparable<Item>{
	String name; // Name of the item
	int weight; // Weight of the item in kg
	HashMap<String, Bag> bags;
	int constraints = 0;
	
	public Item(String name, int weight) {
		this.name = name;
		this.weight = weight;
		this.bags = new HashMap<String, Bag>();
	}

	/*
	 * Compare this item with another item.
	 */
	@Override
	public int compareTo(Item i) {
		if(this.bags.size() - i.bags.size() != 0){
			return this.bags.size() - i.bags.size();
		} else {
			return this.constraints - i.constraints;
		}
	}
}
