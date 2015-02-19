package cs4341proj4;

/*
 * The main class for this CSP.
 * It handles command-line arguments and decides what solvers to run (e.g. backtracking, MRV, etc)
 */
public class SolverMain {

	public static void main(String[] args) {
		if(args.length < 1 || args.length > 4){
			System.out.println("usage: infile MRV LCV forward");
			System.exit(1);
		}
		Solver s;
		if(args.length == 1){
			s = new Solver(args[0],false, false, false);
		} else if (args.length == 2){
			s = new Solver(args[0],true, false, false);
		} else if (args.length == 3){
			s = new Solver(args[0],true, true, false);
		} else {
			s = new Solver(args[0],true, true, true);
		}

		s.solve();
	}

}
