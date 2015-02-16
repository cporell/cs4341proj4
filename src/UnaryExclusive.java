import java.util.ArrayList;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

public class UnaryExclusive extends Constraint {
	Item item;
	ArrayList<Bag> bags = new ArrayList<Bag>();
	
	public UnaryExclusive(Item item, ArrayList<Bag> bags) {
		this.item = item;
		this.bags = bags;
	}
}
