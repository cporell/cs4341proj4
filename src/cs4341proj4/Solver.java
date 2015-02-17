package cs4341proj4;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * CS 4341
 * Project 4 - CSP
 * Connor Porell and Andrew Roskuski
 */


// Solver is the main class, runs the CSP
public class Solver {
	public enum SolverType{
    	BACKTRACKING, MVR, LEASTCONSTRAINING
    }
	
	HashMap<String, Item> items = new HashMap<String, Item>(); // All the items in this CSP
	HashMap<String, Bag> bags = new HashMap<String, Bag>(); // All the bags in this CSP
    ArrayList<Constraint> constraints = new ArrayList<Constraint>();
    SolverType type;
    
	
	public Solver(String in, SolverType type) {
		this.items = new HashMap<String, Item>(); // All the items in this CSP
		this.bags = new HashMap<String, Bag>(); // All the bags in this CSP
	    this.constraints = new ArrayList<Constraint>();
	    this.type = type;
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
							constraints.add(fitLimits);
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/*
	 * Writes out the solution to a solved CSP on the command line.
	 * NOTE: The "no solution" option isn't accounted for in this function, as the program will only get here
	 * if there actually is a solution.
	 */
	private void writeOutput() {
		for(Bag b : bags.values()){
			System.out.println(b.writeItems());
			System.out.println("number of items: " + b.items.size());
			System.out.println("total weight/capacity of the bag: " + b.capacity + "/" + b.weightLimit);
			System.out.println("wasted capacity: " + b.calcWastedCapacity() + "\n");
		}
	}
	
	public void solve(){
		boolean solveable;
		switch (type){
		case BACKTRACKING:
			solveable = backtrackingSolve();
			break;
		case LEASTCONSTRAINING:
			solveable = leastConstrainingSolve();
			break;
		case MVR:
			solveable = MVRSolve();
			break;
		default:
			solveable = false;
			break;
		
		}
		
		if(solveable){
			writeOutput();
		} else {
			System.out.println("No Solveable Assignment");
		}
		
	}
	
	private boolean backtrackingSolve(){
		return false;
	}
	
	private boolean MVRSolve(){
		return false;
	}
	
	private boolean leastConstrainingSolve(){
		return false;
	}
}
