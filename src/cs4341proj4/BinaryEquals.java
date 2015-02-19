package cs4341proj4;

import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

/*
 * BinaryEquals Constraint ensures two items are in the same bag.
 */
public class BinaryEquals implements Constraint {
	Item item1;
	Item item2;
	
	public BinaryEquals(Item item1, Item item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	/*
	 * Checks all the bags to make sure that if item1 exists in a bag, then item2 exists in it as well
	 */
	@Override
	public boolean checkConstraint(HashMap<String, Bag> bags) {
		// TODO Auto-generated method stub
		boolean item1found = false;
		boolean item2found = false;
		boolean samebag = false;
		for(Bag b : bags.values()){
			if(b.contains(item1.name)){
				item1found = true;
				samebag = true;
				if(item2found){
					return false;
				}
			}
			if(b.contains(item2.name)){
				item2found = true;
				if(item1found && !samebag){
					return false;
				}
			}
			samebag = false;
		}
		return true;
	}

	/*
	 * Checks to see if a given item is in this constraint
	 */
	@Override
	public boolean isInConstraint(String itemname) {
		return item1.name.equals(itemname) || item2.name.equals(itemname);
	}
}
