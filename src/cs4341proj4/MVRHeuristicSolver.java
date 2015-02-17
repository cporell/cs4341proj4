package cs4341proj4;

import cs4341proj4.Solver.SolverType;

public class MVRHeuristicSolver {

	public static void main(String[] args) {
		if(args.length != 1){
			System.out.println("Only 1 argument expected");
		}
		Solver s = new Solver(args[0],SolverType.MVR);
		s.solve();

	}

}
