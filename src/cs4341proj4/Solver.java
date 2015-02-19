package cs4341proj4;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */


// Solver where the heavy lifting happens
public class Solver {
	
	HashMap<String, Item> items = new HashMap<String, Item>(); // All the items in this CSP
	HashMap<String, Bag> bags = new HashMap<String, Bag>(); // All the bags in this CSP
    ArrayList<Constraint> constraints = new ArrayList<Constraint>();
    BagFitLimit bfl;
    //SolverType type;
    boolean MRV;
    boolean LCV;
    boolean forward;
    
	
	public Solver(String in, boolean MRV, boolean LCV, boolean forward) {
		this.items = new HashMap<String, Item>(); // All the items in this CSP
		this.bags = new HashMap<String, Bag>(); // All the bags in this CSP
	    this.constraints = new ArrayList<Constraint>();
	    //this.type = type;
	    this.MRV = MRV;
	    this.LCV = LCV;
	    this.forward = forward;
		parseFile(in);
	}

	/*
	 * Parses a text file from the command line to get the variables, values, and constraints
	 * for this CSP.
	 */
	private void parseFile(String file) {
		String line = null;
		int fileSection = 0;
		FileReader in;
		BufferedReader buf;
		try {
			in = new FileReader(file);
			buf = new BufferedReader(in);
			while((line = buf.readLine()) != null) {
				if(line.contains("#####")) {
					fileSection++;
				} else {
					String[] var = line.split(" ");
					switch(fileSection){
					// Case 1 handles the variables (items)
					case 1 : 
							Item item = new Item(var[0], Integer.parseInt(var[1]));
							items.put(var[0], item);
							break;
					// Case 2 handles the values (bags)
					case 2 : 
							Bag bag = new Bag(var[0], Integer.parseInt(var[1]));
							bags.put(var[0], bag);
							break;
					// Case 3 handles the fitting limits
					case 3 : 
							int lowLim = Integer.parseInt(var[0]);
							int hiLim = Integer.parseInt(var[1]);
							BagFitLimit fitLimits = new BagFitLimit(lowLim, hiLim);
							bfl = fitLimits;
							break;
					// Case 4 handles the unary inclusives
					case 4 : 
							Item uiItem = items.get(var[0]);
							ArrayList<Bag> uiBags = new ArrayList<Bag>();
							for(int i = 1; i < var.length - 1; i++){
								uiBags.add(bags.get(var[i]));
							}
							UnaryInclusive uiConstraint = new UnaryInclusive(uiItem, uiBags);
							constraints.add(uiConstraint);
							break;					
					// Case 5 handles the unary exclusives
					case 5 : 
							Item uxItem = items.get(var[0]);
							ArrayList<Bag> uxBags = new ArrayList<Bag>();
							for(int i = 1; i < var.length - 1; i++){
								uxBags.add(bags.get(var[i]));
							}
							UnaryExclusive uxConstraint = new UnaryExclusive(uxItem, uxBags);
							constraints.add(uxConstraint);
							break;
					// Case 6 handles the binary equals
					case 6 : 
							Item beItem1 = items.get(var[0]);
							Item beItem2 = items.get(var[1]);
							BinaryEquals beConstraint = new BinaryEquals(beItem1, beItem2);
							constraints.add(beConstraint);
							break;
					// Case 7 handles the binary not equals
					case 7 : 
							Item bneItem1 = items.get(var[0]);
							Item bneItem2 = items.get(var[1]);
							BinaryNE bneConstraint = new BinaryNE(bneItem1, bneItem2);
							constraints.add(bneConstraint);
							break;
					// Case 8 handles the binary mutual exclusions
					case 8 : 
							Item bmxItem1 = items.get(var[0]);
							Item bmxItem2 = items.get(var[1]);
							Bag bmxBag1 = bags.get(var[2]);
							Bag bmxBag2 = bags.get(var[3]);
							BinaryME bmxConstraint = new BinaryME(bmxItem1, bmxItem2, bmxBag1, bmxBag2);
							constraints.add(bmxConstraint);
							break;
					}
				}
			}
			if (items.size() == 0 || bags.size() == 0){
				System.exit(1);
			}
			if (bfl == null){
				bfl = new BagFitLimit(Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
			
			for(Bag b: bags.values()){
				b.setCapacity(bfl.higherLimit);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		for(Item i: items.values()){
			for(Constraint c: constraints){
				if(c.isInConstraint(i.name)){
					i.constraints++;
				}
			}
		}
	}
	
	/*
	 * Writes out the solution to a solved CSP on the command line.
	 * NOTE: The "no solution" option isn't accounted for in this function, as the program will only get here
	 * if there actually is a solution.
	 */
	private void writeOutput() {
		System.out.println();
		for(Bag b : bags.values()){
			System.out.println(b.writeItems());
			System.out.println("number of items: " + b.items.size());
			System.out.println("total weight/capacity of the bag: " + b.weightused() + "/" + b.weightLimit);
			System.out.println("wasted capacity: " + b.calcWastedCapacity() + "\n");
		}
	}
	
	// Solves the CSP and prints the results
	public void solve(){
		boolean solveable;	
		
		solveable = backtrackingSolve(items);
		
		if(solveable){
			writeOutput();
		} else {
			System.out.println("No Solveable Assignment");
		}
		
	}
	
	/*
	 * Recursively solves The CSP according to the settings for items in the given HashMap
	 */
	private boolean backtrackingSolve(HashMap<String, Item> items){
		//System.out.println(items.size() + " items in HashMap");
		if(items.size() == 0){
			//System.out.println("Got to terminating clause");
			return bfl.checkConstraint(bags);
		}
		//Make copies of items so valid value calculations aren't affected
		for(Item i: items.values()){
			items.put(i.name, new Item(i.name, i.weight));
		}
		//Compute Valid Values for each item
		for(Item i: items.values()){
			for(Bag b: bags.values()){
				boolean consistent = b.assign(i);
				consistent = consistent && checkConstraints();
				if(consistent){
					i.bags.put(b.name, b);
				}
				b.unassign(i.name);
			}
		}
		//Forward Checking - terminate if an item has no valid values
		if(forward){
			for(Item i: items.values()){
				if(i.bags.size() == 0){
					//return false;
				}
			}
		}
		Item[] temparray = new Item[items.size()];
		temparray = (items.values().toArray(temparray));
		ArrayList<Item> itemarray = new ArrayList<Item> (Arrays.asList(temparray));
		//If MRV, sort array
		if(MRV){
			Collections.sort(itemarray);
		}
		for(Item i : itemarray){
			Bag[] tempbarray = new Bag[i.bags.size()];
			tempbarray = (i.bags.values().toArray(tempbarray));
			ArrayList<Bag> bagarray = new ArrayList<Bag> (Arrays.asList(tempbarray));
			//If LCV, sort bag array
			if (LCV){
				for(Bag b: bagarray){
					b.assign(i);
					for(Item i2: items.values()){
						if(i2 != i){
						for(Bag b2: bags.values()){
							boolean consistent = b2.assign(i2);
							consistent = consistent && checkConstraints();
							if(consistent){
								b.possibleAssignments++;
							}
							b2.unassign(i2.name);
						}
						}
					}
					b.unassign(i.name);
				}
				Collections.sort(bagarray, Collections.reverseOrder());
			}
			for(Bag b : bagarray){
				System.out.println("Checking Item " + i.name + " in bag " + b.name);
				boolean consistent = b.assign(i);
				//System.out.println("consistent = " + consistent);
				consistent = consistent && checkConstraints();
				//System.out.println("consistent = " + consistent);
				if(!consistent){
					b.unassign(i.name);
				} else {
					//writeOutput();
					HashMap<String, Item> temp = new HashMap<String, Item>();
					temp.putAll(items);
					temp.remove(i.name);
					boolean result = backtrackingSolve(temp);
					if (result){
						return true;
					} else {
						b.unassign(i.name);
					}
				}
			}
			
		}
		return false;
	}

	
	private boolean checkConstraints(){
		boolean pass = true;
		for (Constraint c : constraints){
			c.checkConstraint(bags);
		}
		return pass;
	}
}
