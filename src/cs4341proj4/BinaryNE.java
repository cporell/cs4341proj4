package cs4341proj4;

import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

/*
 * BinaryNotEquals Constraint states that item1 and item2 cannot be in the same bag
 */
public class BinaryNE implements Constraint {
	Item item1;
	Item item2;
	
	public BinaryNE(Item item1, Item item2) {
		this.item1 = item1;
		this.item2 = item2;
	}

	/*
	 * Checks to make sure that item1 is not in the same bag as item2
	 */
	@Override
	public boolean checkConstraint(HashMap<String, Bag> bags) {
		boolean item1found = false;
		//boolean item2found = false;
		boolean samebag = false;
		for(Bag b : bags.values()){
			if(b.contains(item1.name)){
				item1found = true;
				samebag = true;
			}
			if(b.contains(item2.name)){
				//item2found = true;
				if(item1found && samebag){
					return false;
				}
			}
			samebag = false;
		}
		return true;
	}

	/*
	 * Checks to make sure that an item is in this constraint
	 */
	@Override
	public boolean isInConstraint(String itemname) {
		return item1.name.equals(itemname) || item2.name.equals(itemname);
	}
}
