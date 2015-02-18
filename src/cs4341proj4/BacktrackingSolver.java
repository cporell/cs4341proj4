package cs4341proj4;

import cs4341proj4.Solver.SolverType;

public class BacktrackingSolver {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Only 1 argument expected");
			System.exit(1);
		}
		Solver s = new Solver(args[0],SolverType.BACKTRACKING);
		s.solve();
	}

}
