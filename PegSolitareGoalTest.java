import aima.core.search.framework.problem.GoalTest;

public class PegSolitareGoalTest implements GoalTest {

    public static final PegSolitareBoard GOAL_STATE = new PegSolitareBoard(
            new int[] { 0, 0, 2, 2, 2, 0, 0, 
                        0, 0, 2, 2, 2, 0, 0, 
                        2, 2, 2, 2, 2, 2, 2, 
                        2, 2, 2, 1, 2, 2, 2, 
                        2, 2, 2, 2, 2, 2, 2, 
                        0, 0, 2, 2, 2, 0, 0, 
                        0, 0, 2, 2, 2, 0, 0 });
    @Override
    public boolean isGoalState(Object arg0) {
        PegSolitareBoard board = (PegSolitareBoard) arg0;
        return board.equals(GOAL_STATE);
    }
    
}