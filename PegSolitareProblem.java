import aima.core.search.framework.problem.Problem;

public class PegSolitareProblem extends Problem {


	public PegSolitareProblem(PegSolitareBoard initialState) {
		super(initialState, PegSolitareFunctionFactory.getActionsFunction(), PegSolitareFunctionFactory.getResultFunction(), new PegSolitareGoalTest());

	}

}