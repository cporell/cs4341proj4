package cs4341proj4;

import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
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

	@Override
	public int compareTo(Item i) {
		if(this.bags.size() - i.bags.size() != 0){
			return this.bags.size() - i.bags.size();
		} else {
			return this.constraints - i.constraints;
		}
	}
}
