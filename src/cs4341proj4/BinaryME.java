package cs4341proj4;

import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

public class BinaryME implements Constraint{
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

	@Override
	public boolean checkConstraint(HashMap<String, Bag> bags) {
		if(bags.get(bag1.name).contains(item1.name) && bags.get(bag2.name).contains(item2.name)){
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isInConstraint(String itemname) {
		return item1.name.equals(itemname) || item2.name.equals(itemname);
	}
}
