import aima.core.search.framework.problem.Problem;


public class HW3 {
    public static void main(String[] args) {
        PegSolitareDemo();
    }

    private static void PegSolitareDemo() {
		System.out.println("PegSolitareProblem Depth First Search");
		try {
			Problem problem = new PegSolitareProblem(new PegSolitareBoard(new int[] {0, 0, 1, 1, 1, 0, 0,
																					 0, 0, 1, 1, 1, 0, 0,
																					 1, 1, 1, 1, 1, 1, 1,
																					 1, 1, 1, 2, 1, 1, 1,
																					 1, 1, 1, 1, 1, 1, 1,
																					 0, 0, 1, 1, 1, 0, 0,
																					 0, 0, 1, 1, 1, 0, 0}));
			PegSolitareAlgorithtm PSA = new PegSolitareAlgorithtm(problem);
			
			System.out.println("Was Goal Reached: " + PSA.wasGoalReached());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}