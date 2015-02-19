package cs4341proj4;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

/*
 * UnaryExclusive constraint states that the item cannot be assigned to any of these bags.
 */
public class UnaryExclusive implements Constraint {
	Item item;
	ArrayList<Bag> bags = new ArrayList<Bag>();
	
	public UnaryExclusive(Item item, ArrayList<Bag> bags) {
		this.item = item;
		this.bags = bags;
	}

	/*
	 * Checks to make sure that the item is not in any of the bags in this constraint.
	 */
	@Override
	public boolean checkConstraint(HashMap<String, Bag> bags) {
		for(Bag b : bags.values()){
			boolean inlist = false;
			for(Bag bl : this.bags){
				if(bl.name.equals(b.name)){
					inlist = true;
				}
			}
			if(inlist){
				//System.out.println("bag " + b.name + " in list");
				if(b.contains(item.name)){
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Checks to make sure that an item is in this constraint
	 */
	@Override
	public boolean isInConstraint(String itemname) {
		return item.name.equals(itemname);
	}
}
