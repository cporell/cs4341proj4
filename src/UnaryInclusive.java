import java.util.ArrayList;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

public class UnaryInclusive extends Constraint {
	Item item;
	ArrayList<Bag> bags = new ArrayList<Bag>();
	
	public UnaryInclusive(Item item, ArrayList<Bag> bags){
		this.item = item;
		this.bags = bags;
	}
}
