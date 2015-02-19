package cs4341proj4;

import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */

/*
 * Constraint interface serves as a way for all the different types of constraints to inherit some important functions
 */
public interface Constraint {
	
	boolean checkConstraint(HashMap<String, Bag> bags);
	boolean isInConstraint(String itemname);
}
